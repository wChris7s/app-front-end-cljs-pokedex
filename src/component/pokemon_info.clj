(ns component.pokemon-info
  (:require [clojure.data.json :as json]
            [clojure.java.io :as io]))

(def data-pk (json/read (io/reader "resources/public/data/local-pokemon-db.json")))

(defn pokemon-info [id]
  (let [pokemon-data (data-pk id)]
    [:div.container-sm
     [:div.row
      [:div.col-4
       [:div {:class "card text-bg-dark h-100"}
        [:img.card-img {:src (get-in pokemon-data ["img"]) :alt (get-in pokemon-data ["name"])}]
        [:div.card-body
         [:h1.text-center (get-in pokemon-data ["name"])]
         [:p.card-text (get-in pokemon-data ["body"])]]
        [:a.btn.btn-danger {:href "/index" :role "button"} "Regresar"]
        ]]
      [:div.col
       [:div.card.bg-danger.text-white.text-center
        [:div.row
         [:div.col
          [:p {:class "fw-bold"} "Altura:"]
          [:p.text-white (get-in pokemon-data ["info" "altura"])]]
         [:div.col
          [:p {:class "fw-bold"} "Categoría:"]
          [:p.text-white (get-in pokemon-data ["info" "categoria"])]]
         ]
        [:div.row
         [:div.col
          [:p {:class "fw-bold"} "Habilidad:"]
          [:p.text-white (get-in pokemon-data ["info" "habilidad"])]]
         [:div.col
          [:p {:class "fw-bold"} "Tipo:"]
          [:p.text-white (get-in pokemon-data ["info" "tipo"])]]
         ]
        [:div.row
         [:div.col
          [:p {:class "fw-bold"} "Debilidad:"]
          [:p.text-white (get-in pokemon-data ["info" "debilidad"])]]
         [:div.col
          [:p {:class "fw-bold"} "Peso:"]
          [:p.text-white (get-in pokemon-data ["info" "peso"])]]
         ]
        ]
       [:div.row.center
        [:div.col
         [:p.text-white {:class "fw-bold"} "Puntos de Salud: "]
         [:div.progress.
          [:div.progress-bar {:style (str "width: " (get-in pokemon-data ["estadisticas" "ps"]) "%")} ]]]
        [:div.col
         [:p.text-white {:class "fw-bold"} "Ataque: "]
         [:div.progress.
          [:div.progress-bar.bg-success {:style (str "width: " (get-in pokemon-data ["estadisticas" "ataque"]) "%")} ]]]
        ]
       [:div.row.center
        [:div.col
         [:p.text-white {:class "fw-bold"} "Defensa: "]
         [:div.progress.
          [:div.progress-bar.bg-danger {:style (str "width: " (get-in pokemon-data ["estadisticas" "defensa"]) "%")} ]]]
        [:div.col
         [:p.text-white {:class "fw-bold"} "Ataque Especial: "]
         [:div.progress.
          [:div.progress-bar.bg-warning {:style (str "width: " (get-in pokemon-data ["estadisticas" "ae"]) "%")}]]]
        ]
       [:div.row.center
        [:div.col
         [:p.text-white {:class "fw-bold"} "Defensa Especial: "]
         [:div.progress.
          [:div.progress-bar.bg-warning {:style (str "width: " (get-in pokemon-data ["estadisticas" "de"]) "%")} ]]]
        [:div.col
         [:p.text-white {:class "fw-bold"} "Velocidad: "]
         [:div.progress.
          [:div.progress-bar.bg-success {:style (str "width: " (get-in pokemon-data ["estadisticas" "velocidad"]) "%")}]]]
        ]
       ]]
     ]))