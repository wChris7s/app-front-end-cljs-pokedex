(ns component.pokemon-navbar
  (:require [hiccup.page :refer [html5]]))

(defn navbar-component []
  [:div {:class "container-fluid"}
   [:nav {:class "navbar bg-dark border-bottom border-body navbar-expand-lg m-2 rounded" :data-bs-theme "dark"}
    [:div {:class "container-fluid"}
     [:a {:class "navbar-brand" :href "/index"} "Pokédex "
      [:img {:src "/img/pokeball.svg" :alt "Logo" :width "15" :class "d-inline-block align-text-center"}]
      ]
     [:button.navbar-toggler {:type "button" :data-bs-toggle "collapse" :data-bs-target "#navbarText" :aria-controls "navbarText" :aria-expanded "false" :aria-label "Toggle navigation"}
      [:span.navbar-toggler-icon]]
     [:div {:class "collapse navbar-collapse" :id "navbarText"}
      [:ul {:class "navbar-nav me-auto mb-2 mb-lg-0"}
       [:li.nav-item
        [:a.nav-link {:href "#"} "Fuego"]]
       [:li.nav-item
        [:a.nav-link {:href "#"} "Agua"]]
       [:li.nav-item
        [:a.nav-link {:href "#"} "Planta"]]]
      [:a {:href "#" :target "_blank" :class "ml-auto me-2"}
       [:img {:src "/img/github-mark-white.svg" :alt "GitHub" :width "30" :class "d-inline-block align-text-right"}]]]
     ]
    ]]
  )