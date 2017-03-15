(ns clj-ops.compojure
  (:require [clj-ops.impl :as ops :refer
             [json-response html-response seq->hiccup-table]]
            [compojure.core :refer [context GET]]))

(defn ops-routes
  "Generate RentPath's standard ops routes.

  ARGS:
  - build-info :: the build information for this particular jar.
  - env :: a map of environment variables
  - config :: the app specific configuration (ie. Confusion)"
  ([{:keys [build-info env app-config] :as m}]
   (ops-routes build-info env app-config))

  ([build-info env config]
   (context "/ops" []
            (GET "/heartbeat" [] "OK")
            (GET "/version" []
                 (json-response build-info))
            (GET "/env" []
                 (html-response (seq->hiccup-table (sort-by first env))))
            (GET "/env.json" []
                 (json-response env))
            (GET "/config" []
                 (html-response (seq->hiccup-table config)))
            (GET "/config.json" []
                 (json-response config)))))
