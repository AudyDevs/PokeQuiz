<h1 align="center">PokeQuiz</h1>

<p align="center">  
  Aplicación para jugar a ver cuantos nombres de Pokémon puedes adivinar. Nos permite jugar a adivinar a través de todas las generaciones y también poder practicar cada una de las generaciones Pokémon. Al final nos dará una puntuación que se guardará en el ranking para poder competir entre los mejores.
</p>
<p align="center">   
  Aplicicación Android en Jetpack Compose basada en la arquitectura MVVM desarrollada con DaggerHilt, StateFlows, ViewModels, Corrutinas, Room, Retrofit, FireBase y Testing
</p>

## 🛠 Herramientas y librerias
- Basado en lenguaje [Kotlin](https://kotlinlang.org/) con una interfaz en Jetpack Compose
- Arquitectura MVVM (Model-View-ViewModel)
- ViewModel y StateFlow: Nos permite almacenar el estado y realizar cambios de forma reactiva en la interfaz de usuario.
- Lifecycle: Observador de los ciclos de vida de Androrid. Los usamos para recolectar los cambios de estado en el StateFlow para modificar la interfaz del usuario.
- Room: Base de datos local sobre SQLite para permitirnos un acceso fluido, eficiente y seguro.
- [FireBase](https://github.com/firebase/firebase-android-sdk): Suite de muchas herramientas tales como, notificaciones push, base de datos cloud, informe de errores, analíticas y controles de login.
- [Retrofit2](https://github.com/square/retrofit): Cliente de HTTP para conexiones de red. Nos permite hacer consultas API-REST.
- [Gson](https://github.com/google/gson): Nos permite convertir un formato JSON a un objeto Kotlin.
- [Coroutines](https://github.com/Kotlin/kotlinx.coroutines): Manejo de tareas asíncronas, usado para no bloquear el hilo principal de la aplicación mientras se espera la respuesta de la consulta.
- [Dagger Hilt](https://dagger.dev/hilt/) para inyección de dependencias.
- [Coil](https://github.com/coil-kt/coil): Nos permite representar imagenes a traves de una URL en Jetpack Compose.
- Navigation component: Es una parte de la suite de Jetpack que simplifica la implementación de la navegación en las Activities y los Fragments.
- Datastore preferences: Nos permite almacenar datos en local de forma asíncrona. Ideal para guardar preferencias de usuario y configuraciones de la aplicación.
- Testing
- Código con Clean Code y Clean Architecture

## 📱 Capturas
| Home Screen | Game Screen | Score Screen |
|--|--|--|
| <img src="/previews/HomeScreen.webp" width="245" height="500"> | <img src="/previews/GameScreen.webp" width="245" height="500"> | <img src="/previews/ScoreScreen.webp" width="245" height="500">

| Dialog Name | Dialog Generation | Dialog Score |
|--|--|--|
| <img src="/previews/DialogNameTrainer.webp" width="245" height="500"> | <img src="/previews/DialogGeneration.webp" width="245" height="500"> | <img src="/previews/DialogScore.webp" width="245" height="500">

## 👇 Descargar 👇
Ir a [Releases](https://github.com/AudyDevs/PokeQuiz/releases) para descargar el último APK.
