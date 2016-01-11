(ns steamid.util
  #?(:cljs (:require [goog.math.Long :as google-long])))

(defonce ^:const base-num
  #?(:clj 76561197960265728
     :cljs (google-long/fromString "76561197960265728")))

(defn parse-int [string]
  #?(:clj (Integer/parseInt string)
     :cljs (js/parseInt string)))

(defn parse-long [string]
  #?(:clj (Long/parseLong string)
     :cljs (google-long/fromString string)))
