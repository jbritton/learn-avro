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


(defn serialize-entity-to-file
  [schema-file data-file output-file]
  (let [schema (-> schema-file
                   s/read-schema-file
                   avro/parse-schema)
        data (-> data-file
                 io/resource
                 slurp)
        ;; :avro/uncheck - tells writer not to perform strict validation on datum/schema keys
        entity (with-meta (json/read-str data :key-fn keyword)
                          {:avro/unchecked true})]
    (with-open [adf (avro/data-file-writer schema output-file)]
      (.append adf entity))))


(defn serialize-complex-entity-to-file
  [schema-files data-file output-file]
  (let [schema (apply avro/parse-schema
                      (map s/read-schema-file schema-files))
        data (-> data-file
                 io/resource
                 slurp)
        ;; :avro/uncheck - tells writer not to perform strict validation on datum/schema keys
        entity (with-meta (json/read-str data :key-fn keyword)
                          {:avro/unchecked true})]
    (with-open [adf (avro/data-file-writer schema output-file)]
      (.append adf entity))))


(defn serialize-channel []
  (let [schema-file "entity/channel-v2.schema.json"
        data-file "data/channel-yt-1.json"
        output-file "channel-v2.avro"]
    (serialize-entity-to-file schema-file data-file output-file)))

(defn serialize-source []
  (let [schema-file "entity/source-v2.schema.json"
        data-file "data/source-yt.json"
        output-file "source-v2.avro"]
    (serialize-entity-to-file schema-file data-file output-file)))

(defn serialize-interaction []
  (let [schema-file "entity/interaction-v2.schema.json"
        data-file "data/interaction-yt.json"
        output-file "interaction-v2.avro"]
    (serialize-entity-to-file schema-file data-file output-file)))

(defn serialize-consumer []
  (let [schema-file "entity/consumer-v2.schema.json"
        data-file "data/consumer-yt.json"
        output-file "consumer-v2.avro"]
    (serialize-entity-to-file schema-file data-file output-file)))

(defn serialize-twitter-profile []
  (let [schema-files ["classifier/age-classification-v1.schema.json"
                      "classifier/category-classification-v1.schema.json"
                      "classifier/ethnicity-classification-v1.schema.json"
                      "classifier/gender-classification-v1.schema.json"
                      "classifier/sentiment-classification-v1.schema.json"
                      "entity/twitter-v3.schema.json"]
        data-file "data/profile-tw.json"
        output-file "twitter-profile.avro"]
    (serialize-complex-entity-to-file schema-files data-file output-file)))

(defn serialize-twitter-tweet []
  (let [schema-files ["entity/tweet-v3.schema.json"]
        data-file "data/tweet-tw.json"
        output-file "twitter-tweet.avro"]
    (serialize-complex-entity-to-file schema-files data-file output-file)))
