(ns component.pokemon-grid
  (:require [clojure.data.json :as json]
            [clojure.java.io :as io]))


;; Se lee el archivo .json que es una bd local.
(def data-pk (json/read (io/reader "resources/public/data/local-pokemon-db.json")))

;; Componente base para mostrar un card.
(defn card-component [id, name, description, img]
  [:div {:class "card text-bg-dark h-100"}
   [:img.card-img-top {:src img :alt name}]
   [:div.card-body
    [:h5.card-title name]
    [:p.card-text description]]
   [:a.btn.btn-primary {:class "m-2" :href (str "/poke-inf/" id) :role "button"} "Link"]
   ]
  )

(defn grid-component []
  [:div {:class "container-sm mb-2"}
   [:div {:class "row row-cols-1 row-cols-md-4 g-4"}
    (for [i (range (count data-pk))]
      ;; Se crea dos variables locales = id y pokemon.
      (let
        [id (+ i 1)
         pokemon (str "pokemon-" id)
         ]
        [:div.col
         (card-component
           ;; Con 'get-in' obtenemos los valores del mapa.
           pokemon                                          ;; Se pasa el id del pokemon (pokemon-1) para poder usarlo en la info.
           (get-in data-pk [pokemon "name"])                ;; Se pasa el nombre del pokemon.
           (get-in data-pk [pokemon "body"])                ;; Se pasa la descripción del pokemon.
           (get-in data-pk [pokemon "img"]))                ;; Se pasa la ruta del pokemon.
         ]
        )
      )
    ]]
  )