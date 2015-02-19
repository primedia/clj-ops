(ns clj-ops.impl
  (:require [cheshire.core :as json]
            [ring.util.response :as r]
            [hiccup.page :refer [html5]]))

(defn json-response
  [content]
  (-> content
      json/generate-string
      r/response
      (r/content-type "application/json")))

(defprotocol HtmlResponse
  (html-response [content]))

(extend-protocol HtmlResponse
  String
  (html-response [content]
    (-> (r/response content)
        (r/content-type "text/html")))

  clojure.lang.IPersistentVector
  (html-response [v]
    (html-response (html5 v))))


(defn seq->hiccup-table
  "transforms a sequence of sequences into a hiccup-style html table"
  [tbl-vals]
  (reduce
   (fn [tbl vals]
     (conj tbl
           (reduce (fn [tr v] (conj tr [:td (pr-str v)])) [:tr] vals)))
   [:table]
   tbl-vals))
