(ns atmos-web-kernel-reitit.core-test
  (:require [clojure.test :refer :all]
            [atmos-web-kernel-reitit.core :refer :all]
            [clojure.spec.alpha :as s]))

(deftest web-response-test
  (testing "Ok response."
    (let [ok-response (web-response "Hello test")]
      (is (= 200 (ok-response :status)))))

  (testing "Bad request response."
    (let [ok-response (web-response "Hello test" :bad-request)]
      (is (= 400 (ok-response :status)))))

  (testing "Server error request response."
    (let [ok-response (web-response "Hello test" :server-error)]
      (is (= 500 (ok-response :status))))))