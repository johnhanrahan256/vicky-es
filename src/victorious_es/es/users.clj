(ns victorious-es.es.users
  (:require 
   [victorious-es.es.core :as escore]
   [clojurewerkz.elastisch.query         :as q]
   [clojurewerkz.elastisch.rest.index         :as esi]
   [clojurewerkz.elastisch.rest.bulk :as esbulk]
   [clojurewerkz.elastisch.rest.document :as esd]))


(defn delete-index []
  (esi/delete escore/conn "users"))

(defn create-aliases [user_ids]
  (esi/update-aliases escore/conn (map (fn [user_id] {:add {:index "users"
                                                            :alias (str "user_" user_id)
                                                            :body {:routing user_id
                                                                   :filter {:term {:user_id user_id}}
                                                                   }
                                                            }
                                                      }) users)))

(defn get-users []
  (let  [res (esd/search escore/conn "users" "user" :query (q/match-all))]
    (println res)
    res))

(defn index-user [user]
  (index-users [user]))

(defn index-users [users]
  (do (esbulk/bulk escore/conn (mapcat (fn [user] [
                                                   { :index
                                                    { :_index  "users"
                                                     :_type  "user"
                                                     :_id  (:id user)
                                                     }
                                                    }
                                                   user]) users))
      (create-aliases (map :id users))))

(defn stats []
  (esi/stats escore/conn "users"))


(defn add-follow-sequence []
  ())

