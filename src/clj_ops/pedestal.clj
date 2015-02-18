(ns clj-ops.pedestal
  (:require [clj-ops.impl :as ops :refer
             [json-response html-response seq->hiccup-table]]))

(defn ops-routes
  "Generate RentPath's standard ops routes.

  ARGS:
  - build-info :: function returning the build information for this particular jar.
  - env :: function returning a map of environment variables
  - config :: function returning the app specific configuration (ie. Confusion)"
  [build-info env config]
  `["/ops"
    ["/heartbeat" {:get [::heartbeat (constantly (html-response "OK"))]}]
    ["/version" {:get [::version (constantly (json-response ~(build-info)))]}]
    ["/env" {:get [::env (constantly (->> ~(env)
                                          (sort-by first)
                                          (seq->hiccup-table)
                                          (html-response)))]}]
    ["/env.json" {:get [::env-json (constantly (json-response
                                                ~(env)))]}]
    ["/config" {:get [::config (constantly (-> ~(config)
                                               (seq->hiccup-table)
                                               (html-response)))]}]
    ["/config.json" {:get [::config-json (constantly (json-response ~(config)))]}]])
