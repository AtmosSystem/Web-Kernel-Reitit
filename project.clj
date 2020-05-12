(defproject atmos-web-kernel-reitit "0.1.0-SNAPSHOT"
  :description "This library is the web core of atmos systems, it has commons functionality using *Reitit*."
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url  "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.10.0"]
                 [atmos-kernel "2.0"]
                 [metosin/muuntaja "0.6.7"]
                 [metosin/spec-tools "0.10.2"]
                 [javax.servlet/javax.servlet-api "4.0.1"]
                 ; Ring
                 [metosin/reitit-ring "0.4.2"]
                 [metosin/reitit-middleware "0.4.2"]
                 [ring-cors "0.1.13"]]
  :repositories [["releases" {:url           "https://clojars.org/repo"
                              :username      :env/CLOJAR_USERNAME
                              :password      :env/CLOJAR_PASSWORD
                              :sign-releases false}]])