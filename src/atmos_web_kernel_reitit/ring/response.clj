(ns atmos-web-kernel-reitit.ring.response
  (:require [ring.util.response :refer [response]]))


(defn bad-request-response*
  "Create a bad request response."
  [data]
  {:status  400
   :headers {}
   :body    data})

(defn server-error-request-response*
  "Create a server error request response."
  [data]
  {:status  500
   :headers {}
   :body    data})

(def responses {:ok           response
                :bad-request  bad-request-response*
                :server-error server-error-request-response*})