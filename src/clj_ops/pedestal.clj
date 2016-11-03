(ns clj-ops.pedestal
  (:require [clj-ops.impl :as ops :refer
             [json-response html-response seq->hiccup-table]]))

(defn response
  [name static-response]
  {:name name
   :enter (fn [context]
            (assoc context :response static-response))})

(defn ops-routes
  "Generate RentPath's standard ops routes.

  ARGS:
  - build-info :: build information for this particular jar
  - env        :: map of environment variables
  - config     :: app-specific configuration"
  [build-info env app-config]
  #{["/ops/heartbeat" :any (response ::heartbeat (html-response "OK"))]
    ["/ops/version" :any (response ::version (json-response build-info))]
    ["/ops/env" :any (response ::env (->> env
                                          (sort-by first)
                                          (seq->hiccup-table)
                                          (html-response)))]
    ["/ops/env.json" :any (response ::env.json (json-response env))]
    ["/ops/config" :any (response ::config (-> app-config
                                               (seq->hiccup-table)
                                               (html-response)))]
    ["/ops/config.json" :any (response ::config.json (json-response app-config))]})
