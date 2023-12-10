(ns component.pokemon-info
  (:require [clojure.data.json :as json]
            [clojure.java.io :as io]))

(def data-pk (json/read (io/reader "resources/public/data/local-pokemon-db.json")))

(defn pokemon-info [id]
  (let [pokemon-data (data-pk id)]
    [:div.container.mx-auto.text-center
     [:div.row
      [:div.col-md-4
       [:img.img-thumbnail {:src (get-in pokemon-data ["img"]) :alt (get-in pokemon-data ["name"])}]
       [:img.img-thumbnail {:src (get-in pokemon-data ["img2"]) :alt (get-in pokemon-data ["name"]) :style {:margin-top "10px"}}]
       [:a.btn.btn-primary {:href "/index" :role "button"} "Regresar"]]
      [:div.col-md-7.text-md-start
       [:h1.text-center (get-in pokemon-data ["name"])]
       [:p (get-in pokemon-data ["body"])]
       [:div.card.bg-info.text-white {:style {:width "60%" :margin "50px auto" :padding "20px"}}
        [:div.row
         [:div.col-md-3
          [:p {:style {:font-size "2em" :font-weight "bold"}} "Altura:"]
          [:p.text-dark (get-in pokemon-data ["altura"])]]
         [:div.col-md-3
          [:p {:style {:font-size "1.2em"}} "Categoría:"]
          [:p.text-dark (get-in pokemon-data ["categoria"])]]
         [:div.col-md-3
          [:p {:style {:font-size "1.2em"}} "Peso:"]
          [:p.text-dark (get-in pokemon-data ["peso"])]]
         [:div.col-md-3
          [:p {:style {:font-size "1.2em"}} "Habilidad:"]
          [:p.text-dark (get-in pokemon-data ["habilidad"])]]
         ]]
       [:p {:style {:font-size "2em"}}"Tipo: "]
       [:p.font-weight-bold (get-in pokemon-data ["tipo"])]
       [:p {:style {:font-size "2em"}}"Debilidad: " ]
       [:p {:class "font-weight-bold"} (get-in pokemon-data ["debilidad"])]
       ]]
     ]))

