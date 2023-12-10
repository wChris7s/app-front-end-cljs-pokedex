# Clojure

> Para compilar los archivos de JavaScript: ``lein cljsbuild once``
> Para iniciar el servidor local: ``lein ring server``

## Language Basics

### Notación Prefija - Prefix Notation

- Clojure usa notación prefija para todas sus expresiones.
- Esto significa que el operador precede a los operandos.
- Nested expressions o expresiones anidadas siguen las mismas reglas.

```clojure
(+ 1 2 3 4 5) ; => 15
(* 1 2 3 4 5) ; => 120
```

### Definición de variables y funciones

- Estructura para definir variables: `(def <nombre> <valor>)`
- Estructura para definir funciones: `(defn <nombre> [<parametros>] <cuerpo>)`
  - Comentario sobre la función [opcional]: `(defn <nombre> "comentario" [<parametros>] <cuerpo>)`

### Tipos de datos primitivos

- Integer: `1`
- Float: `1.0`
- String: `"Hola"`
- Boolean: `true` o `false`
- Character: `\A`, `\E`
- Null: `nil`
- Expresiones regulares: `#"[0-9]+"`

### Métodos String

- Longitud: `(count "Hola")` => 4
- Concatenación: `(str "Hola" " " "Mundo")` => "Hola Mundo"
- Substring: `(subs "Hola Mundo" 0 4)` => "Hola"
- Mayusculas: `(.toUpperCase "Hola Mundo")` => "HOLA MUNDO"
- Minusculas: `(.toLowerCase "Hola Mundo")` => "hola mundo"
- Obtener el caracter en una posición: `(.chatAt "Hola Mundo" 0)` => \H

### Operadores

- Suma: `(+ 1 2 3 4 5)` => 15
- Resta: `(- 1 2 3 4 5)` => -13
- Multiplicación: `(* 1 2 3 4 5)` => 120
- División: `(/ 1 2 3 4 5)` => 1/120
- Módulo: `(mod 5 2)` => 1
- Maximo: `(max 1 2 3 4 5)` => 5
- Minimo: `(min 1 2 3 4 5)` => 1
- Random: `(rand 10)` => 5.123456789
- Random (Integer): `(rand-int 10)` => 5
- Potencia: `(Math/pow 2 3)` => 8.0
- Raiz cuadrada: `(Math/sqrt 9)` => 3.0
- Comparación: `(= 1 2)` => false
- Par: `(even? 2)` => true
- Impar: `(odd? 2)` => false

### Operadores Lógicos

- Igualdad: `(= 1 2)` => false
- Desigualdad: `(!= 1 2)` => true
- Mayor que: `(> 1 2)` => false
- Mayor o igual que: `(>= 1 2)` => false
- AND: `(and true false)` => false
- OR: `(or true false)` => true
- NOT: `(not true)` => false

### Simbolos y Keywords

- Un simbolo es un identificador único que puede ser usado como nombre de variable o función.
- Un keyword es un simbolo precedido por dos puntos `:`, este es usado para representar un valor único.
