(defproject om-websocket "0.1.1"
  :description "om component for websocket use!"
  :url "https://github.com/jobez/om-websocket"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [om "0.6.4"]
                 [org.clojure/clojurescript "0.0-2227"]
                 [prismatic/om-tools "0.2.2"]]

  :plugins [[lein-cljsbuild "1.0.3"]]

  :cljsbuild {:build [{:id "dev"
                       :source-paths ["src/cljs"]
                       :compiler {
                                  :output-to "resources/public/javascripts/main/om-websocket.js"
                                  :output-dir "resources/public/javascripts/main/out"
                                  :source-map "resources/public/javascripts/main/om-websocket.js.map"
                                  :optimizations :none
                                  }

                       }]}


  )
