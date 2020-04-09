(ns learn-avro-clj.core
  (:require [learn-avro-clj.schema :as s]
            [abracad.avro :as avro]
            [clojure.java.io :as io]
            [clojure.data.json :as json]
            [deercreeklabs.lancaster :as l]
            [camel-snake-kebab.core :as csk]))


;; avro lancaster

;(defn serialize-youtube-channel []
;  (let [data (-> (io/resource "data/channel-yt.json") slurp)
;        schema (l/json->schema s/youtube-channel-schema)
;        youtube-channel (json/read-str data :key-fn csk/->kebab-case-keyword)]
;    (l/serialize schema youtube-channel)))
;
;(defn deserialize-youtube-channel [encoded-youtube-channel]
;  (let [schema (l/json->schema s/youtube-channel-schema)
;        youtube-channel (l/deserialize schema schema encoded-youtube-channel)]
;    youtube-channel))
;
;(defn write-youtube-channel-avro-file []
;  (let [out-file "/Users/jeffbritton/Code/personal/learn-avro/youtube-channel.avro"]
;    (with-open [o (io/output-stream out-file)]
;      (.write o (serialize-youtube-channel)))))
;
;(defn file->bytes [file]
;  (with-open [in (io/input-stream file)
;              out (java.io.ByteArrayOutputStream.)]
;    (io/copy in out)
;    (.toByteArray out)))
;
;(defn read-youtube-channel-avro-file []
;  (let [in-file "/Users/jeffbritton/Code/personal/learn-avro/youtube-channel.avro"
;        bytes (file->bytes in-file)]
;    (deserialize-youtube-channel bytes)))


;; avro - abracad


(defn serialize-youtube-channel []
  (let [schema (-> s/youtube-channel-schema
                   s/read-schema-file
                   avro/parse-schema)
        data (-> "data/channel-yt-simple.json"
                 io/resource
                 slurp)
        entity (json/read-str data :key-fn keyword)]
    (with-open [adf (avro/data-file-writer schema "youtube-channel.avro")]
      (.append adf entity))))



(defn serialize-base-entity []
  (let [schema (-> s/base-entity-schema
                   s/read-schema-file
                   avro/parse-schema)
        data (-> "data/channel-base-entity.json"
                 io/resource
                 slurp)
        entity (json/read-str data :key-fn keyword)]
    (with-open [adf (avro/data-file-writer schema "base-entity.avro")]
      (.append adf entity))))
