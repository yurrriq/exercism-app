(defproject exercism "0.1.0-SNAPSHOT"
  :description  "FIXME: write description"
  :url          "https://github.com/yurrriq/exercism-app"
  :license      {:name "MIT"
                 :url  "http://yurrriq.mit-license.org"}
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [org.clojure/clojurescript "1.7.228"]
                 [org.omcljs/om "1.0.0-alpha30"]
                 [org.omcljs/ambly "0.7.0"]
                 [natal-shell "0.1.6"]]
  :plugins      [[lein-cljsbuild "1.1.2"]]
  :cljsbuild    {:builds
                 {:dev {:source-paths ["src"]
                        :compiler     {:output-to     "target/out/main.js"
                                       :output-dir    "target/out"
                                       :optimizations :none}}}})
