(ns clj-unzip.core
  (:use clojure.pprint)
  (:require [clojure.tools.cli :as cli]
              [clojure.java.io :as io])
  (:import [org.apache.commons.compress.archivers.zip ZipFile]
           [org.apache.commons.io IOUtils])
  (:gen-class))

(defn decompress-file [options]
  (let [{:keys [infile encoding]} options]
    (with-open [zip (ZipFile. (io/file infile) encoding)]
      (doseq [entry (enumeration-seq (.getEntries zip))]
        (let [in-stream (.getInputStream zip entry)
              local-file (io/file (.getName entry))]
          (io/make-parents local-file)
          (with-open [out-stream (io/output-stream local-file)]
            (IOUtils/copy in-stream out-stream)))
        ))
    ))

(defn -main [& args]
  (let [[options rest banner]
        (cli/cli args
                 ["-i" "--infile" "specify input file."]
                 ["-e" "--encoding" "specify encoding." :default "UTF8"]
                 ["-?" "--help" "show help." :default false :flag true])]
    (let [{:keys [help]} options]
      (cond help (println banner)
            :else (decompress-file options)))
    ))
