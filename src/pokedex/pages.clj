(ns pokedex.pages
  (:require [hiccup.page :refer [html5]]
            [component.pokemon-navbar :as nav]
            [component.pokemon-grid :as grid]
            [component.pokemon-info :as inf]))


(defn base-page [title, & body]
  (html5
    [:head
     [:meta {:charset "UTF-8"}]
     [:meta {:name "viewport" :content "width=device-width, initial-scale=1.0"}]
     [:link {:href "https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" :rel "stylesheet" :integrity "sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" :crossorigin "anonymous"}]
     [:script {:src "https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" :integrity "sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" :crossorigin "anonymous"}]
     [:title title]]
    body
    ))

; La función 'index' se encarga de cargar el main page.
; El main page contiene la barra de navegación y la grid con la info de los Pokemon.
(defn index []
    (base-page "Pokedex"
               (nav/navbar-component)
               (grid/grid-component)
               ))

; La función llama a la función pokemon-info del namespace pokemon_info.clj
(defn pokemon-info [id]
  (base-page "Información"
             (inf/pokemon-info id)))