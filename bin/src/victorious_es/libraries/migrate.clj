(ns victorious-es.libraries.migrate
  (:require 
    [victorious-es.db.core :as mysql]
    [victorious-es.es.users :as esu]))

(defn import-users []
  (let [db-users (mysql/get-users)
        partitioned-users (partition 500 db-users)]
    (map esu/index-users partitioned-users)))
