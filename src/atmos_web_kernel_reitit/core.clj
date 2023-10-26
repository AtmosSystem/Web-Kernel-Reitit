(ns atmos-web-kernel-reitit.core
  (:require [atmos-web-kernel-reitit.ring.response :refer [responses]]
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

(defn request-handler
  "Create a web request handler."
  [handler-fn]
  (fn [request] (handler-fn request)))

(defn web-router
  "Create a web router."
  ([routes options]
   (ring/router routes options))
  ([routes]
   (ring/router routes)))

(defn ring-app
  "Create a ring app."
  ([router default-handler options]
   (ring/ring-handler router default-handler options))
  ([router default-handler]
   (ring/ring-handler router default-handler)))