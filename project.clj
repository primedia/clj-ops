(defproject com.rentpath/rp-ops-clj "2.0.1"
  :description "Ops utilities for Clojure apps"
  :url "https://github.com/rentpath/rp-ops-clj"
  :dependencies [[hiccup "1.0.5"]]
  :repositories [["releases" {:url "https://nexus.tools.rentpath.com/repository/maven-releases/"
                              :sign-releases false
                              :username [:gpg :env/nexus_username]
                              :password [:gpg :env/nexus_password]}]]
  :profiles {:provided {:dependencies [[compojure "1.6.0"]
                                       [cheshire  "5.8.0"]
                                       [ring/ring-core "1.6.3"]]}
             :dev {:dependencies [[org.clojure/clojure "1.8.0"]
                                  [ring/ring-mock "0.3.2"]]}})
