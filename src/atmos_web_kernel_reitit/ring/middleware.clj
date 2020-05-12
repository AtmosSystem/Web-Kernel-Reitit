(ns atmos-web-kernel-reitit.ring.middleware
  (:require
    [atmos-web-kernel-reitit.ring.cors :refer [cors-access-control]]
    [ring.middleware.cors :refer [wrap-cors]]
    [reitit.ring.middleware.muuntaja :as muuntaja]
    [reitit.ring.middleware.parameters :as parameters]
    [reitit.ring.middleware.multipart :as multipart]))

(def wrap-cors-middleware {:name        ::wrap-cors-middleware
                           :description "Ring CORS middleware."
                           :wrap        (fn [handler] (wrap-cors handler
                                                                 :access-control-allow-origin
                                                                 (cors-access-control :origins)
                                                                 :access-control-allow-methods
                                                                 (cors-access-control :methods)))})

(def default-middleware [;;; query-params & form-params
                         parameters/parameters-middleware
                         ;;; content-negotiation
                         muuntaja/format-middleware
                         ;;; File handling
                         multipart/multipart-middleware
                         ;;; CORS handling
                         wrap-cors-middleware])