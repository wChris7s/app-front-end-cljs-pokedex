(ns search-addon)

(->
  ;; Realiza una solicitud para obtener datos en formato JSON desde "/data/local-pokemon-db.json"
  (js/fetch "/data/local-pokemon-db.json")
  ;; Procesa la respuesta como JSON
  (.then (fn [response] (.json response)))
  ;; Procesa los datos JSON obtenidos
  (.then
    (fn [data]
      (let [pokemons (.values js/Object data) ;; Extrae la lista de pokémons de los datos JSON
            searchInput (.getElementById js/document "searchInput") ;; Obtiene el elemento de entrada de búsqueda
            resultsDiv (.getElementById js/document "results")] ;; Obtiene el contenedor de resultados

        ;; Agrega un escuchador de eventos al campo de búsqueda para buscar en tiempo real
        (.addEventListener
          searchInput
          "input"
          (fn [event]
            (let [searchText (.toLowerCase (.. js/event -target -value))] ;; Obtiene el texto de búsqueda en minúsculas
              (when (= searchText "") (set! (.-innerHTML resultsDiv) "") (return)) ;; Borra los resultados si el texto de búsqueda está vacío
              ;; Filtra los pokémons basándose en el texto de búsqueda
              (def matchingPokemons
                (.filter
                  pokemons
                  (fn [pokemon]
                    (.includes (.toLowerCase (.-name pokemon)) searchText))))
              (showResults matchingPokemons)))) ;; Llama a la función showResults con los pokémons coincidentes

        ;; Define la función showResults para mostrar los resultados de la búsqueda
        (defn showResults
          [pokemonList]
          (set! (.-innerHTML resultsDiv) "") ;; Borra los resultados anteriores
          (.forEach
            pokemonList
            (fn [pokemon]
              (let [name (.createElement js/document "span")] ;; Crea un elemento span para el nombre del pokémon
                (set!
                  (.-style js/name)
                  "display: block; font-family: \"Monaco\", Courier, monospace;")
                (set! (.-textContent js/name) (.-name pokemon))
                (def image (.createElement js/document "img")) ;; Crea un elemento de imagen para el pokémon
                (set! (.-src image) (.-img pokemon))
                (set! (.-alt image) (.-name pokemon))
                (set! (.-style image) "display: inline-block; width: 100px;")
                (def button (.createElement js/document "button")) ;; Crea un elemento de botón para seleccionar el pokémon
                (.add (.-classList button) "btn" "btn-outline-warning" "btn-sm")
                (set! (.-textContent button) "Seleccionar")
                ;; Agrega un escuchador de eventos de clic al botón para redirigir a la página de detalles del pokémon
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
  ;; Maneja errores durante la operación de obtención de datos
  (.catch
    (fn [error] (.error js/console "Error al cargar el archivo JSON:" error))))

;; Una función simple para registrar un mensaje de prueba en la consola
(defn log-test []
  (js/console.log "¡Hola desde ClojureScript!"))
