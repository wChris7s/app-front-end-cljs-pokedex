(ns component.pokemon-info
  (:require [clojure.data.json :as json]
            [clojure.java.io :as io]))

(def data-pk (json/read (io/reader "resources/public/data/local-pokemon-db.json")))

(defn pokemon-info [id]
  (let [pokemon-data (data-pk id)]
    [:div {:class "container-sm position-absolute top-50 start-50 translate-middle"}
     [:div.row
      [:div.col-4
       [:div {:class "card text-bg-dark h-100"}
        [:img.card-img {:src (get-in pokemon-data ["img"]) :alt (get-in pokemon-data ["name"])}]
        [:div.card-body
         [:h1.text-center (get-in pokemon-data ["name"])]
         [:p.card-text (get-in pokemon-data ["body"])]]
        [:a.btn.btn-outline-danger {:class "m-2" :href "/index"  :role "button"} "Regresar"]
        ]]
      [:div.col {:class "table-responsive-md" :style "padding-top: 6%"}
       [:table {:class "table table-bordered table-striped text-center border-dark table-hover"}
        [:tbody
         [:tr
          [:td.fw-bold  {:class "bg-danger"} "Altura"]
          [:td.fw-bold {:class "bg-danger"} "Categoría"]
          ]
         [:tr
          [:td {:class "text-light bg-dark"} (get-in pokemon-data ["info" "altura"]) " m"]
          [:td {:class "text-light bg-dark"} (get-in pokemon-data ["info" "categoria"])]
          ]
         [:tr
          [:td.fw-bold {:class "bg-danger"} "Habilidad"]
          [:td.fw-bold {:class "bg-danger"} "Tipo"]
          ]
         [:tr
          [:td {:class "text-light bg-dark"} (get-in pokemon-data ["info" "habilidad"])]
          [:td {:class "text-light bg-dark"} (get-in pokemon-data ["info" "tipo"])]
          ]
         [:tr
          [:td.fw-bold {:class "bg-danger"} "Debilidad"]
          [:td.fw-bold {:class "bg-danger"} "Peso"]
          ]
         [:tr
          [:td {:class "text-light bg-dark"} (get-in pokemon-data ["info" "debilidad"])]
          [:td {:class "text-light bg-dark"} (get-in pokemon-data ["info" "peso"]) " kg"]
          ]
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