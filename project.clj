(defproject steamid "0.1.0"
  :url "https://github.com/horsemd/clj-steamid"
  :description "SteamID Converter"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}

  :dependencies [[org.clojure/clojure "1.7.0"]
                 [org.clojure/clojurescript "1.7.170"]]

  :plugins [[lein-cljsbuild "1.1.1"]]

  :source-paths ["src"]
  :test-paths ["test"]
  :cljsbuild {
              :builds [{:source-paths ["src" "test"]
                         :compiler {:output-to "build/cljs/out.js"
                                    :optimizations :whitespace
                                    :pretty-print true}}]})
