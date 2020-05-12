(ns atmos-web-kernel-reitit.core
  (:require [reitit.ring :as ring]
            [muuntaja.core :as m]
            [atmos-web-kernel-reitit.ring.middleware :refer :all]
            [atmos-web-kernel-reitit.ring.response :refer [responses]]
            [atmos-web-kernel-reitit.exception :refer [handle-exception]]
            [clojure.spec.alpha :as s]))

(defn web-response
  "Create a web response."
  ([data response-type]
   (let [response-fn (responses response-type)]
     (response-fn data)))
  ([data]
   (web-response data :ok)))

(s/fdef web-response
        :args (s/cat :data any? :response-type (s/? keyword?))
        :ret map?)

(defmacro web-request
  "Create a web request handler."
  [handler-fn]
  `(fn [request#]
     (try
       (web-response (~handler-fn request#))

       (catch Exception e# (handle-exception e# request#)))))

(defn web-router
  "Create a web router."
  [routes & {:keys [data middleware] :or {data       {}
                                          middleware []}}]

  (let [middleware (if (empty? middleware) default-middleware
                                           (into [] (concat middleware default-middleware)))
        data (assoc data :muuntaja m/instance
                         :middleware middleware)]

    (ring/router routes {:data data})))

(defn ring-app
  "Create a ring app."
  [router default-handler]
  (ring/ring-handler router default-handler))