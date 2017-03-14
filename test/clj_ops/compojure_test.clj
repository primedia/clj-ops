(ns clj-ops.compojure-test
  (:require [clojure.test :refer :all]
            [clj-ops.compojure :refer :all]
            [cheshire.core :as json]
            [ring.mock.request :as mock]))

(def +env+ {"ENV_FOO" "42" "ENV_BAR" "true"})
(def +configuration+ {"some" "configuration"})
(def app (ops-routes {:build-time "NOW"}
                     +env+
                     +configuration+))

(deftest test-app
  (testing "heartbeat"
    (let [response (app (mock/request :get "/ops/heartbeat"))]
      (is (= (:status response) 200))
      (is (= (:body response) "OK"))))

  (testing "version"
    (let [response (app (mock/request :get "/ops/version"))]
      (is (= (:status response) 200))
      (is (= (:body response) "{\"build-time\":\"NOW\"}"))))

  (testing "env"
    (let [response (app (mock/request :get "/ops/env.json"))]
      (is (= +env+ (json/parse-string (:body response))))))

  (testing "config"
    (let [response (app (mock/request :get "/ops/config.json"))]
      (is (= +configuration+ (json/parse-string (:body response))))))

  (testing "not-found route"
    (let [response (app (mock/request :get "/invalid"))]
      (is (= response nil)))))
