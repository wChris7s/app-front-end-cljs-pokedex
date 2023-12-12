(ns component.pokemon-navbar)

(defn logo-component []
 [:a {:class "navbar-brand" :href "/index"} "Pokédex"
  [:img {:src "/img/pokeball.svg" :alt "Logo" :width "20" :class "d-inline-block align-text-center ms-2 pb-1"}]
  ])

(defn github-component []
 [:a {:href "https://github.com/wChris7s/app-front-end-cljs-pokedex" :target "_blank" :class "ml-auto me-2"}
  [:img {:src "/img/github-mark-white.svg" :alt "GitHub" :width "30" :class "d-inline-block align-text-right"}]]
 )

(defn search-modal-component []
 [:div {:class "modal fade" :id "pokemonModal" :tabindex "-1" :aria-labelledby "pokemonModalLabel" :aria-hidden "true"}
  [:div {:class "modal-dialog"}
   [:div {:class "modal-content"}
    [:div {:class "modal-header"}
     [:h1 {:class "modal-title fs-5 text-light" :id "pokemonModalLabel"} "Busque su Pokémon favorito"]
     [:button {:class "btn-close" :type "button" :data-bs-dismiss "modal" :aria-label "Close"}]]

    [:div {:class "modal-body text-light"}
     [:div {:class "container text-center"}
      [:div {:class "row row-cols-1 row-cols-lg-1 g-1 g-lg-1"}
       [:div {:class "col"}
        [:input {:id "searchInput" :class "form-control my-2" :type "text" :placeholder "Ingrese el nombre del Pokémon"}]]
       [:div {:class "row justify-content-center"}
        [:div {:id "results"}]
        ]]]
     ]

    [:div {:class "modal-footer"}
     [:button {:class "btn btn-outline-danger" :type "button" :data-bs-dismiss "modal"} "Close"]
     ]]]]
 )
(defn navbar-component []
 [:div {:class "container-fluid"}
  [:nav {:class "navbar bg-dark border-bottom border-body navbar-expand-lg m-2 rounded" :data-bs-theme "dark"}
   [:div {:class "container-fluid"}
    (logo-component)
    [:div {:class "justify-content-end"}
     [:button {:class "btn btn-outline-danger mx-4" :type "button" :data-bs-toggle "modal" :data-bs-target "#pokemonModal"} "Buscar"]
     (search-modal-component)
     (github-component)
     ]
    ]
   ]]
 )