(ns atmos-web-kernel-reitit.ring.middleware
  (:require
    [atmos-kernel.configuration :as config]
    [atmos-web-kernel-reitit.core :as c]
    [clojure.spec.alpha :as s]
    [ring.middleware.cors :refer [wrap-cors]]
    [clojure.string :as string]))

(def settings (config/read-edn :settings))

(s/def ::status-with-response (s/map-of pos-int? any? :count 1))


(def web-response-middleware {:name        ::web-response
                              :description "Middleware to add a ring-valid response"
                              :wrap        (fn [handler]
                                             (fn [request]
                                               (let [response (handler request)
                                                     [http-status data] (if (s/valid? ::status-with-response response)
                                                                          (into [] (first response)) [200 response])]
                                                 (c/web-response data (keyword (str http-status))))))})

(def web-cors-middleware {:name        ::web-cors
                          :description "Middleware to add CORS functionality"
                          :wrap        (fn [ring-handler]
                                         (let [origins (map re-pattern (string/split (get-in settings [:cors :origins] "") #","))
                                               methods (get-in settings [:cors :methods] [])]
                                           (wrap-cors ring-handler
                                                      :access-control-allow-origin origins
                                                      :access-control-allow-methods methods)))})