# Documentación de la aplicación: **app-front-end-cljs-pokedex**

## Introducción

Esta aplicación tiene como objetivo simular una Pokédex. En términos simples, una Pokédex es una base de datos
que almacena información sobre Pokémones. Cada Pokémon contiene detalles como nombre, descripción, habilidades
tipo, debilidades y estadísticas de poder, entre otros datos.

Para llevar a cabo esta simulación, se empleó el lenguaje de programación funcional Clojure y su variante
ClojureScript. ¿Por qué elegir este lenguaje? Clojure se utiliza comúnmente en el desarrollo web debido a sus
diversos frameworks, los cuales son eficaces para alcanzar los objetivos de desarrollo tanto en el front-end como
en el back-end, mientras que ClojureScript permite utilizar la sintaxis de Clojure y poder compilarlo a JavaScript.

## Requerimientos

Para poder hacer uso de la siguiente aplicación se debe tener las siguientes caracteristicas instaladas en el sistema operativo.
1. [Java 11 o Superior.](https://www.oracle.com/java/technologies/downloads/)
   1. Descargar la JDK correspondiente.
   2. Abrir el archivo e instalarlo.
   3. Abrir las Variables de entorno y actualizar el **path** con la ruta correspondiente, por ejemplo: **C:\Program Files\Java\jdk-11\bin**
   4. Crear una nueva **variable** con nombre **JAVA_HOME** y su valor correspondiente, por ejemplo **C:\Program Files\Java\jdk-11**
2. [Leiningen.](https://leiningen.org/)
   * En la página se describe una guia de instalación.
   * Para usuarios de Windows se puede instalar de manera sencilla con el administrador de paquetes [**Chocolatey**](https://chocolatey.org/)-[Lein](https://community.chocolatey.org/packages/Lein)
     * Para evitar problemas con **Lein**, se debe instalar como administrador desde la powershell.
3. [IntelliJ IDEA.](https://www.jetbrains.com/es-es/idea/)
   * Es importante usar ese IDE, ya que posee características que hacen sencilla la carga de dependencias con Maven.
   * En caso de usar otro IDE se debe instalar [Maven](https://maven.apache.org/).
4. Clonar el presente repositorio y abrirlo con el IDE, esperar a que Maven cargue las dependencias.

### Estructura de la aplicación

````
.
├── resources/
│   ├── public/
│   │   ├── css
│   │   ├── data/
│   │   │   └── local-pokemon-db.json
│   │   ├── img
│   │   └── js/
│   │       └── main.js
│   └── src/
│       ├── component/
│       │   ├── pokemon_grid.clj
│       │   ├── pokemon_info.clj
│       │   └── pokemon_navbar.clj
│       ├── pokedex/
│       │   ├── handler.clj
│       │   └── pages.clj
│       └── scripts/
│           └── search_addon.cljs
└── project.clj
````

## Compilación

Este proceso de compilación es solo para los archivos de **ClojureScript**, lo que hace este proceso es tomar
todos los archivos que se encuentren en la carpeta **scripts** y los compila a JavaScript, este es un paso
importante.

- Abrir una terminal en la raíz del proyecto.
- En la terminal ingresar el siguiente comando: ``lein cljsbuild once``.
- Una vez culmina este proceso se genera un archivo JavaScript en la carpeta ``js`` que está contenida en `resources`.

## Ejecución del servidor

Este paso consiste en generar un archivo HTML que contiene todo el contenido de los archivos de Clojure. Una vez
creado, se inicia un servidor en el puerto 3000 para cargar este contenido.

- Abrir una terminal en la raíz del proyecto.
- En la terminal ingresar el siguiente comando: ``lein ring server``.

## Funcionamiento de la aplicación

### project.clj

Este archivo contiene las dependencias y configuraciones del proyecto.

- ``:dependencies``: Contiene todas las dependencias que debe cargar Maven para poder compilar el proyecto.
- ``:plugins``: Contiene los plugins que se deben cargar para poder compilar el proyecto.
- ``:ring``: Ring es un framework web para Clojure, este contiene la configuración del servidor. En este caso carga `handler.clj`. Este archivo contiene las rutas que se deben cargar en el
servidor. Así mismo está configurado para que los cambios que se realicen carguen al servidor de manera automática. 
- ``:cljsbuild``: Contiene la configuración para compilar los archivos de ClojureScript. En esta configuración se apunta a la carpeta ``src/scripts`` que debe contener los archivos ``cljs``, también se apunta en donde se debe generar el archivo de JavaScript compilado junto con su nombre, en este caso ``resources/public/js/main.js``.

### local-pokemon-db.json

El proyecto no trabaja directamente con algúna base de datos, sea SQL como PostgresSQL o NoSQL como MongoDB. Lo que se
realiza es tener un archivo local que almacena toda la información de los Pokémones de manera estática, esto para
ahorrar tiempo en desarrollo y producción. El archivo JSON contiene información necesaria para poder simular lo ya
mencionado.

### handler.clj

Contiene las rutas de la pagína simulada, este llama al componente ``pages`` para poder obtener la información de los componentes y así cargarlos en diferentes rutas. En caso de que se acceda a una ruta no definida simplemente se muestra el mensaje ``Not Found``.
Las rutas son las siguientes:
* "/": Esta ruta redirecciona a la ruta real que es "/index".
* "/index": Carga los componentes de la página principal.
* "/poke-inf/:id": Carga los componentes basándose en el ``id``, este corresponde al ``id`` del Pokémon.

### pages.clj

> #### ¿Qué es hiccup?
> Hiccup es una biblioteca que permite generar HTML de una manera más simple y legible utilizando la sintaxis de Clojure. Hiccup se basa en el uso de mapas key-value.


El archivo actual incluye tanto la invocación de distintos componentes como la definición de "base-page".

* Primero se realizan las invocaciones de los diferentes componentes, asignándoles sus respectivos alias. Estos alias permiten llamar a las funciones contenidas en cada componente una vez que han sido referenciados.
* La función "base-page" corresponde al archivo "index.html" y toma dos parámetros [title, &body]. Su objetivo es mantener un mapa con todas las definiciones que puede contener un archivo HTML, especialmente en lo que respecta al "head". Además, engloba el "body", donde se cargan los elementos enviados a esta función.
* La función "index" es responsable de cargar la página principal. En ella, se utiliza la función "base-page" para cargar el contenido. Se otorga el título "Pokedex" a la página y se cargan dos componentes: la barra de navegación y el grid que contiene a los Pokémon.
* La función "pokemon-info" utiliza el ID que se pasa como parámetro desde la URL. Esta función se encarga de cargar la información del Pokémon correspondiente. Hace uso de "base.page" y envía el título "Información" junto con los componentes de la barra de navegación y pokemon-info, utilizando el ID como parámetro.

### pokemon_grid.clj

Define un componente para generar una cuadrícula de tarjetas de Pokémon basadas en datos provenientes de un archivo
JSON local.
* Lectura del Archivo JSON:
  * data-pk se inicializa leyendo un archivo JSON local llamado "local-pokemon-db.json" utilizando las funciones de clojure.data.json y clojure.java.io.

* card-component:
  * Esta función crea una tarjeta HTML representando a un Pokémon. 
  * Toma cuatro argumentos: id, name, description, y img.
  * Devuelve una estructura de datos que representa la tarjeta de un Pokémon.

* grid-component:
  * Genera una cuadrícula de tarjetas de Pokémon utilizando los datos del archivo JSON. 
  * Utiliza un diseño de cuadrícula de filas y columnas. 
  * Para cada Pokémon en data-pk, genera una tarjeta utilizando la función card-component.

* Iteración de la información de los Pokémones:
  * Utiliza un bucle for para recorrer todos los Pokémon en data-pk. 
  * Usa let para definir variables locales id y pokemon para cada iteración. 
  * Dentro de cada iteración, llama a card-component con los datos específicos del Pokémon actual obtenidos del archivo JSON utilizando get-in.


### pokemon_info.clj

Define un componente para generar una vista mas detallada de las estadisticasa de cada pokemon,
estas estadisticas provienen de datos almacenados en el archivo JSON
* Lectura del archivo JSON:
  * data-pk se inicializa leyendo un archivo JSON local llamado "local-pokemon-db.json" utilizando las funciones de clojure.data.json y clojure.java.io, para luego ser renombrado como pokemon-data.
* Container:
  * el container es divido en 2 con div.row seguido de varios div.col.
  * El el primer espacio reciclamos codigo del card-component para poder visualizar: description, name y la imagen del pokemon. Luego pasamos a crear un boton con el enlace a la pagina principal po si se requiere regresar.
  * En el segundo espacio en la parte superior creamos un cuadro en el que se cargan las caracteristicas: altura, categoria, habilidad, tipo, debilidad. Estos se dividen en diferentes .div-cool y .div-row para una mejor visualizacion.
  * En el segundo espacio en la parte inferior creamos barras de carga cada una correspondiente a un dato almacenado en el archivo JSON correspondiente a estadisticas como son : ps(puntos de salud), ataque, defensa, ae(ataque especial), de(defensa especial), velocidad,
  estas barras se cargan dependiendo el numero almacenado en las caracteristicas para que se adapte segun cada pokemon.
### search_addon.cljs

Este script define un buscador de Pokémon en ClojureScript. El código utiliza una estructura funcional con manejo de eventos para actualizar dinámicamente la interfaz de usuario en respuesta a la entrada del usuario.

* search-addon:
  * Encapsula toda la lógica para buscar y mostrar los Pokémon en la página web.
  * Realiza una solicitud fetch al archivo local /data/local-pokemon-db.json. 
  * Procesa los datos cuando la solicitud se resuelve correctamente.
  * Configura un evento de escucha en el input de búsqueda para mostrar los resultados correspondientes.

* showResults:
  * Toma una lista de Pokémon y muestra sus detalles en la página. 
  * Crea elementos HTML dinámicamente para cada Pokémon que coincida con la búsqueda.

### pokemon_navbar.clj

Define un componete que genera una barra de navegación, esta barra de navegación incluye a **search_addon.cljs**.

* logo-component, github-component:
  * Se encargan de cargar y visualizar tanto el logo de la Pokedex y el logo de Github.

* search-modal-component:
  * Este componente contiene un botón que activa un modal.
  * Ese modal así mismo contiene el apartado de búsqueda para el Pokémon.

* navbar-component:
  * Se encarga de crear la barra de navegación llamando a los diferentes componentes.