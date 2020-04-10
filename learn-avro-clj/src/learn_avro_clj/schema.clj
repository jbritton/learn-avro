(ns learn-avro-clj.schema
  (:require [deercreeklabs.lancaster :as l]))

;; TODO: read in from an environment variable
(def schema-dir "/Users/jeffbritton/Code/personal/learn-avro/schema/")

(def base-entity-schema "base-entity.schema.json")

;(def youtube-channel-schema "youtube-channel.schema.json")

(defn read-schema-file [file-name]
  (slurp (str schema-dir file-name)))

