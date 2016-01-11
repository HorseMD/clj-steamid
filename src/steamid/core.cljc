(ns steamid.core
  (:require [steamid.util :as util :refer [parse-int parse-long]]
            [clojure.string :as str :refer [split]]))

(def ^:const regex-steamid64 #"^[0-9]{17}$")
(def ^:const regex-steamid   #"^STEAM_[0-5]:[01]:\d+$")
(def ^:const regex-steamid3  #"^\[U:1:[0-9]+\]$")

(defn steamid?   [id] (re-find regex-steamid   id))
(defn steamid64? [id] (re-find regex-steamid64 id))
(defn steamid3?  [id] (re-find regex-steamid3  id))

(defn any-steamid?
  "Determine if the given String is a SteamID of any sort. Takes optional
:target param. To add: :default and :not"
  [id & {:keys [target]}]
  (letfn [(callback [fn id] (if (nil? fn) id (fn id)))]
    (callback target ((some-fn
                       #(steamid? %)
                       #(steamid3? %)
                       #(steamid64? %))
                      id))))

(defn steamid3->steamid
  "Convert a SteamID3 to SteamID."
  [id]
  (when (steamid3? id)
    (let [split (str/split id #":")
          last  (parse-int (str/join (butlast (nth split 2))))]
      (str "STEAM_0:" (mod last 2) ":" (long (Math/floor (/ last 2)))))))

(defn steamid
  "Convert any type of SteamID to... SteamID."
  [sid]
  (when (any-steamid? sid)
    (let [sid (str sid)]
      (if (steamid3? sid)
        (steamid3->steamid sid)
        (let [id (parse-long sid)
              v util/base-num
              y (mod id 2)
              w (- id y v)]
          (str "STEAM_0:" y ":" (/ w 2)))))))

(defn steamid3
  "Convert any type of SteamID to SteamID3."
  [sid3]
  (when (any-steamid? sid3)
    (let [id (if (steamid64? (str sid3))
               (steamid (str sid3))
               sid3)
          split (str/split id #":")]
      (str "[U:1:" (+
                    (parse-int (nth split 1))
                    (* 2
                       (parse-int (nth split 2))))
           "]"))))

(defn steamid64
  "Convert any type of SteamID to SteamID64.
  Value returned is a string representing a SteamID64."
  [sid]
  (when (any-steamid? sid)
    (let [id (if (steamid3? sid)
               (steamid3->steamid sid)
               sid)
          split (str/split id #":")
          v util/base-num
          z (parse-int (nth split 2))
          y (parse-int (nth split 1))]
      (str (+ v (* 2 z) y)))))
