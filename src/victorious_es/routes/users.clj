(ns victorious-es.routes.users
  (:require [ring.util.http-response :refer :all]
            [compojure.api.sweet :refer :all]
            [schema.core :as s]
            [victorious-es.es.users :as users]
            [victorious-es.libraries.migrate :as migrate]))

(s/defschema Thingie {:id Long
                      :hot Boolean
                      :tag (s/enum :kikka :kukka)
                      :chief [{:name String
                               :type #{{:id String}}}]})

(defapi user-routes
  (ring.swagger.ui/swagger-ui
   "/swagger-ui")
  ;JSON docs available at the /swagger.json route
  (swagger-docs
   {:info {:title "Sample api"}})
  (context* "/api/users" []
            :tags ["thingie"]

            (GET* "/" []
                  :return {s/Any s/Any}
                  :summary      "x+y with query-parameters. y defaults to 1."
                  (let [res (users/get-users)]
                    (ok res)))

            (GET* "/nuke" []
                  :summary     "deletes the users index"
                  (ok (users/delete-index)))


            (GET* "/stats" []
                  :summary     "deletes the users index"
                  (ok (users/stats)))

            (GET* "/" []
                  :summary     "deletes the users index"
                  (ok (users/stats)))


            ))

