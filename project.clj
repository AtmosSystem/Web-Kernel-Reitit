(defproject atmos-web-kernel-reitit "2.0-SNAPSHOT"
  :description "This library is the web core of atmos systems, it has commons functionality using *Reitit*."
  :url "https://github.com/AtmosSystem/atmos-web-kernel-reitit"
  :license {:name "Eclipse Public License"
            :url  "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.11.1"]
                 [atmos-kernel "2.2-SNAPSHOT"]
                 [metosin/reitit-spec "0.6.0"]
                 [metosin/spec-tools "0.10.5"]
                 [javax.servlet/javax.servlet-api "4.0.1"]
                 ; Ring
                 [metosin/reitit-ring "0.5.18"]]
  :profiles {:dev {:dependencies [[org.clojure/test.check "0.9.0"]]}})