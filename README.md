# Pokémon KMP App

Aplicación multiplataforma desarrollada con **Kotlin Multiplatform (KMP)** que permite explorar un catálogo paginado de Pokémon utilizando la [PokeAPI](https://pokeapi.co), añadirlos a un carrito de compras simulado y gestionar la navegación entre pantallas.

---

##  Instrucciones para correr el proyecto

###  Requisitos

- **Android Studio Giraffe o superior**
- **Kotlin Multiplatform configurado**
- Emulador o dispositivo Android
- (Opcional) macOS con Xcode para ejecutar en iOS

### ▶ Pasos para ejecutar

1. Clona el repositorio:

   ```bash
   git clone https://github.com/anfega154/pokemon.git
   cd pokemon-kmp-app
    ```
   
2. Abre el proyecto con Android Studio.
3. Sincroniza Gradle (File > Sync Project with Gradle Files).
4. Ejecuta el proyecto en un emulador o dispositivo.

## Decisiones técnicas

- **Kotlin Multiplatform (KMP):** permite compartir lógica entre Android e iOS.
- **Jetpack Compose + Compose Multiplatform:** UI declarativa y reutilizable.
- **Koin:** se utiliza para la inyección de dependencias de forma ligera y extensible.
- **PokemonViewModel:** ViewModel multiplataforma para manejar estado compartido y lógica de presentación.
- **PokemonRepository<T : Any>:** clase genérica para interactuar con la API de forma desacoplada.
- **HttpAdapter:** capa centralizada de red que facilita la escalabilidad y pruebas.
- **PokemonException:** jerarquía de errores personalizados para manejar diferentes fallos (conexión, API, parsing, etc.).

### Funcionalidad nativa utilizada

Se incorporó funcionalidad nativa para verificar conectividad de red:

- **En Android:** uso de `isConnectedOrConnecting`.
- **En iOS:** uso de `isConnectedOrConnecting`.

Esta funcionalidad es clave para mostrar contenido correctamente y manejar errores cuando no hay conexión antes de intentar consumir la API.

- **En Android:** uso de `vibrate`.
- **En iOS:** uso de `vibrate`.

Esta funcionalidad permite proporcionar retroalimentación háptica al usuario en acciones clave, como añadir un Pokémon al carrito.

## Diseño visual

El diseño de la aplicación sigue un enfoque limpio, moderno y accesible:

- Uso de Jetpack Compose para crear componentes visuales reutilizables.
- Diseño responsivo y adaptable a diferentes tamaños de pantalla.
- Estilo minimalista, con separación clara entre elementos.
- El ícono del carrito incluye un contador de ítems seleccionados.

### Tecnologías y dependencias

- Kotlin Multiplatform para lógica compartida.
- Jetpack Compose y Compose Multiplatform.
- Ktor para peticiones HTTP.
- Koin para inyección de dependencias.
- kotlinx.serialization para parseo de JSON.
- moe.tlaster:precompose para navegación multiplataforma.

### Componentes clave

| Componente             | Descripción                                                                 |
|------------------------|-----------------------------------------------------------------------------|
| PokemonViewModel       | Lógica de negocio y estado compartido entre pantallas.                      |
| PokemonRepository<T>   | Repositorio genérico que maneja peticiones REST según el tipo.              |
| HttpAdapter            | Encapsula lógica HTTP reutilizable (método, endpoint, parsing, etc).        |
| PokemonException       | Excepciones especializadas para fallos comunes (timeout, parsing, 404...).  |
| Page                   | Componente base de pantalla con TopAppBar adaptable.                        |