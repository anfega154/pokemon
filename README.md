# Pokémon KMP App

Aplicación multiplataforma desarrollada con Kotlin Multiplatform (KMP) que permite explorar un catálogo paginado de Pokémon utilizando la PokeAPI, añadirlos a un carrito de compras simulado y gestionar la navegación entre pantallas.

---

## Instrucciones para correr el proyecto

### Requisitos
- Android Studio Giraffe o superior
- Kotlin Multiplatform configurado
- Emulador o dispositivo Android
- (Opcional) macOS con Xcode para ejecutar en iOS

### Pasos para ejecutar
1. Clona el repositorio:
   git clone https://github.com/anfega154/pokemon.git
   cd pokemon
2. Abre el proyecto con Android Studio.
3. Sincroniza Gradle (File > Sync Project with Gradle Files).
4. Ejecuta el proyecto en un emulador o dispositivo.

---

## Arquitectura y decisiones técnicas

Estructura de carpetas:
├── domain/
│   ├── common/        → Modelos y clases base de red
│   └── model/         → Entidades del dominio (Pokemon, etc.)
├── repository/        → Repositorios e interfaces para acceso a datos
├── presentation/
│   ├── ui/            → Componentes visuales y navegación
│   └── viewmodel/     → ViewModels multiplataforma
├── utils/             → Utilidades compartidas

Tecnologías destacadas:
- Kotlin Multiplatform: lógica compartida entre Android e iOS
- Jetpack Compose + Compose Multiplatform: UI declarativa y reutilizable
- Koin: inyección de dependencias liviana y extensible
- PokemonViewModel: ViewModel multiplataforma
- PokemonRepository<T>: clase genérica desacoplada para interactuar con la API
- HttpAdapter: capa HTTP centralizada
- PokemonException: jerarquía personalizada de errores

---

## Funcionalidad nativa utilizada

- Conectividad de red:
   - Android: ConnectivityManager
   - iOS: SCNetworkReachability (SystemConfiguration)

- Feedback háptico:
   - Android: Vibrator
   - iOS: UIImpactFeedbackGenerator

---

## Diseño visual

- Jetpack Compose para componentes reutilizables
- Diseño responsivo y minimalista
- Ícono del carrito con contador de ítems seleccionados

---

## Tecnologías y dependencias

- Kotlin Multiplatform
- Jetpack Compose y Compose Multiplatform
- Ktor
- Koin
- kotlinx.serialization
- moe.tlaster:precompose

---

## Componentes clave

| Componente           | Descripción                                                                 |
|----------------------|-----------------------------------------------------------------------------|
| PokemonViewModel     | Lógica de negocio y estado compartido entre pantallas.                      |
| PokemonRepository<T> | Repositorio genérico para peticiones REST según el tipo.                   |
| HttpAdapter          | Encapsula lógica HTTP reutilizable.                                         |
| PokemonException     | Excepciones especializadas (timeout, parsing, 404...).                       |
| Page                 | Componente base de pantalla con TopAppBar adaptable.                        |
