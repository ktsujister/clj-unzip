(defproject clj-unzip "0.1.0"
  :description "unzip with encoding specified."
  :dependencies [[org.clojure/clojure			"1.3.0"]
                 [org.clojure/tools.cli			"0.2.1"]
                 [org.apache.commons/commons-compress	"1.3"]
                 [org.apache.commons/commons-io		"1.3.2"]]
  :dev-dependencies [[swank-clojure		"1.4.0"]]
  :jvm-opts ["-Dfile.encoding=utf-8"]
  :main zip-encoding.core)
