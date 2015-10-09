(ns victorious-es.routes.migrations
  (:require [ring.util.http-response :refer :all]
            [compojure.api.sweet :refer :all]
            [schema.core :as s]
            [victorious-es.libraries.migrate :as migrate]))


(defapi migration-routes
  (ring.swagger.ui/swagger-ui
   "/swagger-ui")
  ;JSON docs available at the /swagger.json route
  (swagger-docs
    {:info {:title "Sample api"}})
  (context* "/api/migrations" []
            :tags ["thingie"]
          
            
            (GET* "/users" []
                  :return {s/Any s/Any}
                  :summary      "x+y with query-parameters. y defaults to 1."
                  (let [res (migrate/import-users)]
                    (ok res)))


   ))
