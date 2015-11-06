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
    ~(with-meta [{:name :filter-HEAD-response
                  :leave (fn [{req :request :as ctx}]
                           (cond-> ctx
                             (= :head (:request-method req)) (assoc-in [:response :body] nil)))}]
       {:interceptors true})

    ["/heartbeat" {:any [::heartbeat (constantly (html-response "OK"))]}]
    ["/version" {:any [::version (constantly (json-response ~(build-info)))]}]
    ["/env" {:any [::env (constantly (->> ~(env)
                                 (sort-by first)
                                 (seq->hiccup-table)
                                 (html-response)))]}]
    ["/env.json" {:any [::env-json (constantly (json-response ~(env)))]}]
    ["/config"   {:any [::config (constantly (-> ~(config) (seq->hiccup-table) (html-response)))]}]
    ["/config.json" {:any [::config-json (constantly (json-response ~(config)))]}]])










