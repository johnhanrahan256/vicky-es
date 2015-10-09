(ns victorious-es.es.core
  (:require 
    [clojurewerkz.elastisch.rest          :as esr]
    [clojurewerkz.elastisch.rest.index    :as esi]
    [clojurewerkz.elastisch.rest.response :as esrsp]
    [clojurewerkz.elastisch.query         :as q]
    [clojurewerkz.elastisch.rest.document :as esd]))

(def conn (esr/connect "http://127.0.0.1:9200"))
