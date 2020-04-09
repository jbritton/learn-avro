(defproject learn-avro-clj "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.10.0"]
                 [deercreeklabs/lancaster "0.9.2"]
                 [camel-snake-kebab "0.4.1"]
                 [com.damballa/abracad "0.4.13"]]
  :main ^:skip-aot learn-avro-clj.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
