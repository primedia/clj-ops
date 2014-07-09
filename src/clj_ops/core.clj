(ns clj-ops.core
  (:require [compojure.core :refer [context GET]]
            [cheshire.core :as json]))

(defn json-response
  [content]
  {:headers {"Content-Type" "application/json"}
   :status 200
   :body (json/generate-string content)})

(defn ops-routes
  [build-info env]
  (context "/ops" []
    (GET "/heartbeat" [] "OK")
    (GET "/version" []
      (json-response build-info))
    #_(GET "/env" []
        (html5 (seq->hiccup-table (sort-by first @env))))
    (GET "/env.json" []
      (json-response @env))
    #_(GET "/config" []
        (html5 (seq->hiccup-table {})))
    #_(GET "/config.json" []
        (json-response {}))))
