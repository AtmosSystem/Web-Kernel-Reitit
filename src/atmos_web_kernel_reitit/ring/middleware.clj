(ns atmos-web-kernel-reitit.ring.middleware
  (:require [atmos-web-kernel-reitit.core :as c]
            [clojure.spec.alpha :as s]))

(s/def ::status-with-response (s/map-of pos-int? any? :count 1))

(def web-response-middleware {:name        ::web-response
                              :description "Middleware to add a ring-valid response"
                              :wrap        (fn [handler]
                                             (fn [request]
                                               (let [response (handler request)
                                                     [http-status data] (if (s/valid? ::status-with-response response)
                                                                          (into [] (first response)) [200 response])]
                                                 (c/web-response data (keyword (str http-status))))))})
