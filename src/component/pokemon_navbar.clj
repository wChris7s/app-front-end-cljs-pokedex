(ns component.pokemon-navbar
  (:require [hiccup.page :refer [html5]]
            [component.pokemon-grid :as data]))
(defn navbar-component []
  [:div {:class "container-fluid"}
   [:nav {:class "navbar bg-dark border-bottom border-body navbar-expand-lg m-2 rounded" :data-bs-theme "dark"}
    [:div {:class "container-fluid"}
     [:a {:class "navbar-brand" :href "/index"} "Pokédex "
      [:img {:src "/img/pokeball.svg" :alt "Logo" :width "15" :class "d-inline-block align-text-center"}]
      ]
     [:div {:class "justify-content-end"}
      [:button.btn.btn-outline-danger {:class "mx-4" :type "button" :data-bs-toggle "modal" :data-bs-target "#exampleModal"} "Buscar"]

      [:div {:class "modal fade" :id "exampleModal" :tabindex "-1" :aria-labelledby "exampleModalLabel" :aria-hidden "true"}
       [:div {:class "modal-dialog"}
        [:div {:class "modal-content"}
         [:div {:class "modal-header"}
          [:h1 {:class "modal-title fs-5 text-light" :id "exampleModalLabel"} "Busque su Pokémon favorito"]
          [:button.btn-close {:type "button" :data-bs-dismiss "modal" :aria-label "Close"}]]
         [:div {:class "modal-body text-light"}
          [:div.container.text-center
           [:div.row.row-cols-1.row-cols-lg-1.g-1.g-lg-1
            [:div.col
             [:input#searchInput.form-control.my-2 {:type "text" :placeholder "Ingrese el nombre del Pokémon"}]]
            [:div.row.justify-content-center
             [:div#results]]]]
          ]
         [:div.modal-footer
          [:button.btn.btn-outline-danger {:type "button" :data-bs-dismiss "modal"} "Close"]]]]]

      [:a {:href "#" :target "_blank" :class "ml-auto me-2"}
       [:img {:src "/img/github-mark-white.svg" :alt "GitHub" :width "30" :class "d-inline-block align-text-right"}]]]
     ]
    ]])