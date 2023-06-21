(ns atmos-web-kernel-reitit.core
  (:require [atmos-web-kernel-reitit.exception :refer [handle-exception]]
            [atmos-web-kernel-reitit.ring.response :refer [responses]]
            [clojure.spec.alpha :as s]
            [reitit.ring :as ring]))

(defn web-response
  "Create a web response."
  ([data status]
   (let [response-fn (status responses)]
     (response-fn data)))
  ([data]
   (web-response data :200)))

(s/fdef web-response
        :args (s/cat :data any? :status (s/? keyword?))
        :ret map?)

(defmacro web-handler
  "Create a web request handler."
  [handler-fn]
  `(fn [request#]
     (try
       (~handler-fn request#)
       (catch Exception e# (handle-exception e# request#)))))

(defn web-router
  "Create a web router."
  ([routes options]
   (ring/router routes options))
  ([routes]
   (ring/router routes)))

(defn ring-app
  "Create a ring app."
  [router default-handler]
  (ring/ring-handler router default-handler))