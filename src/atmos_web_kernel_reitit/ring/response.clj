(ns atmos-web-kernel-reitit.ring.response)

(def responses (let [response-> (fn [status] (fn [data] (into {:status status :headers {} :body data})))]
                 {:200 (response-> 200)
                  :201 (response-> 201)
                  :204 (response-> 204)
                  :400 (response-> 400)
                  :401 (response-> 401)
                  :404 (response-> 404)
                  :500 (response-> 500)}))