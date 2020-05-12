(ns atmos-web-kernel-reitit.ring.cors
  (:require [atmos-kernel.configuration :refer [read-edn]]))

(def cors-config (read-edn :cors))

(defmulti cors-access-control (fn [type-to-allow] type-to-allow))

(defmethod cors-access-control :origins [_] (if cors-config (map #(re-pattern %) (cors-config :origins))))
(defmethod cors-access-control :methods [_] (if cors-config (cors-config :methods)))