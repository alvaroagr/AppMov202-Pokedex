# AppMov202-Pokedex
## Información
Este repositorio contiene mi desarrollo del Reto 2: Pokedex de la clase de Aplicaciones Moviles del segundo semestre de 2020, dirigida por el profesor Domiciano 
Rincón.

La aplicación como tal es un conjunto de actividades donde un usuario, o más bien un entrenador, puede:
* Ingresar con su nombre
* Capturar y listar pokemon usando [PokeAPI](https://pokeapi.co/) y [Firebase](https://firebase.google.com/)
* Mostrar detalle de uno de sus pokemon con sus estadisticas, usando Firebase.

La aplicación cumple con los siguientes requerimientos:
- [x] Un usuario puede ingresar a la aplicación y ver sus propios pokemon atrapados.
- [x] Listar los pokemon atrapados usando la imagen del pokemon y su nombre. Esta lista debe ser implementada con RecyclerView y además debe estar organizada en el orden
en el que se atraparon (primero el más reciente).
- [x] La sección de búsqueda debe permitir buscar a un pokemon por su nombre. Para hacer la búsqueda debe usarse Firebase Firestore. 
- [x] La sección de atrapar del pokemon debe permitir extraer la información del PokeAPI y enviar la información relevante a Firestore. 
NO es necesario que desearialice todo el objeto que provee el PokeAPI. 
- [x] La actividad de detalle del pokemon debe permitirle ver las estadísticas del pokemon atrapado y también le permite sacarlo de su lista de pokemon atrapado. 
La actividad de detalle del pokemon debe permitir ver las estadísticas del pokemon atrapado y también permite sacarlo de la lista de pokemon atrapados del entrenador.

**IMPORTANTE:** En este repositorio no se incluyen archivos ```google-services.json``` generados al conectar la aplicación a Firebase, debido a que obviamente representaria 
una vulnerabilidad de seguridad.

El componente de Firestore de la aplicación **no funcionara** a menos que usted conecte la apliación a su propio proyecto de Firebase y, por ende, genere su propio 
archivo ```google-services.json``` para ubicar en la carpeta ```app``` del proyecto.

**Alvaro A. Gómez Rey. 2020.**
