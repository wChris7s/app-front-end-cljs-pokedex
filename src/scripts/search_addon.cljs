(ns search-addon)

(->
  (js/fetch "/data/local-pokemon-db.json")
  (.then (fn [response] (.json response)))
  (.then
    (fn [data]
      (let [pokemons (.values js/Object data)
            searchInput (.getElementById js/document "searchInput")
            resultsDiv (.getElementById js/document "results")]
        (.addEventListener
          searchInput
          "input"
          (fn [event]
            (let [searchText (.toLowerCase (.. js/event -target -value))]
              (when (= searchText "") (set! (.-innerHTML resultsDiv) "") (return))
              (def matchingPokemons
                (.filter
                  pokemons
                  (fn [pokemon]
                    (.includes (.toLowerCase (.-name pokemon)) searchText))))
              (showResults matchingPokemons))))
        (defn showResults
          [pokemonList]
          (set! (.-innerHTML resultsDiv) "")
          (.forEach
            pokemonList
            (fn [pokemon]
              (let [name (.createElement js/document "span")]
                (set!
                  (.-style js/name)
                  "display: block; font-family: \"Monaco\", Courier, monospace;")
                (set! (.-textContent js/name) (.-name pokemon))
                (def image (.createElement js/document "img"))
                (set! (.-src image) (.-img pokemon))
                (set! (.-alt image) (.-name pokemon))
                (set! (.-style image) "display: inline-block; width: 100px;")
                (def button (.createElement js/document "button"))
                (.add (.-classList button) "btn" "btn-outline-warning" "btn-sm")
                (set! (.-textContent button) "Seleccionar")
                (.addEventListener
                  button
                  "click"
                  (fn []
                    (set!
                      (.. js/window -location -href)
                      (str "/poke-inf/pokemon-" (.-id pokemon) ""))))
                (def col1 (.createElement js/document "div"))
                (.add (.-classList col1) "col")
                (.appendChild col1 image)
                (def col2 (.createElement js/document "div"))
                (.add (.-classList col2) "col" "py-3")
                (.appendChild col2 name)
                (.appendChild col2 button)
                (def row (.createElement js/document "div"))
                (.add (.-classList row) "row")
                (.appendChild row col1)
                (.appendChild row col2)
                (def col (.createElement js/document "div"))
                (.add (.-classList col) "col")
                (.appendChild col row)
                (.appendChild resultsDiv col)))))
        )))
  (.catch
    (fn [error] (.error js/console "Error al cargar el archivo JSON:" error))))


(defn log-test []
  (js/console.log "Hello from ClojureScript!"))