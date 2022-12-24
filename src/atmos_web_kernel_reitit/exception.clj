(ns atmos-web-kernel-reitit.exception
  (:import (clojure.lang ExceptionInfo)))

(defprotocol ExceptionHandlerProtocol
  (handle-exception [exception request]))

(defn exception-data
  "Get exception data."
  [^Exception exception]
  (let [exception-message (.getMessage exception)
        exception-type (.getCanonicalName (.getClass exception))

        exception-data (if (instance? ExceptionInfo exception) (ex-data exception) {})
        exception-data (assoc exception-data :type exception-type)

        data {:message        exception-message
              :exception-data exception-data}]

    data))
