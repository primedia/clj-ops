(defproject clj-ops "0.1.0"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [hiccup    "1.0.5"]
                 [compojure "1.1.8"]]

  :repositories
  [["releases"
    {:url "http://nexus.idg.primedia.com/nexus/content/repositories/primedia"
     :sign-releases false}]]

  :profiles
  {:dev {:dependencies [[ring-mock "0.1.5"]
                        [cheshire  "5.3.1"]]}})
