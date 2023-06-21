(ns atmos-web-kernel-reitit.ring.middleware
  (:require [atmos-web-kernel-reitit.core :as c]))

(def web-response {:name        ::web-response
                   :description "Middleware to add a ring-valid response"
                   :wrap        (fn [handler]
                                  (fn [request]
                                    (let [response (handler request)
                                          {:keys [data http-status]} (if (and (map? response)
                                                                              (contains? response :data)
                                                                              (contains? response :http-status))
                                                                       response
                                                                       {:data response :http-status :200})]
                                      (c/web-response data http-status))))})
