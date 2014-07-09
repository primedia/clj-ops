(ns clj-ops.core-test
  (:require [clojure.test :refer :all]
            [clj-ops.core :refer :all]
            [ring.mock.request :as mock]))

(def app (ops-routes {:build-time "NOW"} {"ENV_FOO" "42" "ENV_BAR" "true"}))

(deftest test-app
  (testing "heartbeat"
    (let [response (app (mock/request :get "/ops/heartbeat"))]
      (is (= (:status response) 200))
      (is (= (:body response) "OK"))))

  (testing "version"
    (let [response (app (mock/request :get "/ops/version"))]
      (is (= (:status response) 200))
      (is (= (:body response) "{\"build-time\":\"NOW\"}"))))
  
  (testing "not-found route"
    (let [response (app (mock/request :get "/invalid"))]
      (is (= response nil)))))
