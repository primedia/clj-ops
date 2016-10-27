(ns clj-ops.bidi
  (:require [clj-ops.impl :as ops :refer
             [json-response html-response seq->hiccup-table]]))

(defn ops-routes
  "Generate RentPath's standard ops routes.

  ARGS:
  - build-info :: map of build information for this particular jar.
  - env :: map of environment variables
  - app-config :: map of app-specific configuration (ie. Confusion)"
  [build-info env app-config]
  ["/ops"
   {"/heartbeat" {:get (constantly (html-response "OK"))}
    "/version" {:get (constantly (json-response build-info))}
    "/env" {:get (constantly (->> env
                                  (sort-by first)
                                  (seq->hiccup-table)
                                  (html-response)))}
    "/env.json" {:get (constantly (json-response env))}
    "/config" {:get (constantly (-> app-config (seq->hiccup-table) (html-response)))}
    "/config.json" {:get (constantly (json-response app-config))}}])
