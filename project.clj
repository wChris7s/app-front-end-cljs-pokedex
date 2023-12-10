(defproject pokedex "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :min-lein-version "2.0.0"
  :dependencies [[org.clojure/clojure "1.10.0"]
                 [compojure "1.6.1"]
                 [ring/ring-defaults "0.3.2"]
                 [hiccup "1.0.5"]
                 [org.clojure/data.json "2.4.0"]
                 [org.clojure/clojurescript "1.11.60"]
                 [cheshire "5.10.0"]
                 [com.fasterxml.jackson.core/jackson-core "2.13.0"]
                 [com.fasterxml.jackson.core/jackson-databind "2.13.0"]
                 [reagent "1.2.0"]
                 ]
  :plugins [
            [lein-ring "0.12.5"]
            [lein-cljsbuild "1.1.8"]
            ]
  :ring {:handler       pokedex.handler/app
         :auto-reload?  true
         :auto-refresh? true}

  :cljsbuild {
              :builds [{
                        :source-paths ["src/scripts"]
                        :compiler     {
                                       :output-to     "resources/public/js/main.js"
                                       :optimizations :whitespace
                                       :pretty-print  true}}]}
  :profiles
  {:dev {:dependencies [[javax.servlet/servlet-api "2.5"]
                        [ring/ring-mock "0.3.2"]]}})
