(defproject com.rentpath/rp-ops-clj "2.0.1-SNAPSHOT"
  :description "Ops utilities for Clojure apps"
  :url "https://github.com/rentpath/rp-ops-clj"
  :dependencies [[hiccup "1.0.5"]]
  :repositories [["releases" {:url "http://nexus.idg.primedia.com/nexus/content/repositories/primedia"
                              :sign-releases false
                              :username [:gpg :env/nexus_username]
                              :password [:gpg :env/nexus_password]}]]
  :profiles {:provided {:dependencies [[compojure "1.1.8"]
                                       [cheshire  "5.4.0"]
                                       [ring/ring-core "1.3.2"]]}
             :dev {:dependencies [[org.clojure/clojure "1.8.0"]
                                  [ring-mock "0.1.5"]]}})
