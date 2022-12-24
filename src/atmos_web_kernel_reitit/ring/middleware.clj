(ns atmos-web-kernel-reitit.ring.middleware
  (:require [atmos-web-kernel-reitit.core :as c]))

(def web-response {:name        ::web-response
                   :description "Middleware to add a ring-valid response"
                   :wrap        (fn [handler]
                                  (fn [request]
                                    (let [response (handler request)
                                          [data status] (if (vector? response) response [response :200])]
                                      (c/web-response data status))))})
