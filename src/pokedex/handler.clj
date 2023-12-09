(ns pokedex.handler
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.util.response :refer [redirect]]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]
            [pokedex.pages :as pg]))

(defroutes app-routes
  (GET "/" [] (redirect "/index"))
  (GET "/index" [] (pg/index))
  (GET "/poke-inf/:id" [id] (pg/pokemon-info id))
  (route/not-found "Not Found"))

(def app
  (wrap-defaults app-routes site-defaults))

