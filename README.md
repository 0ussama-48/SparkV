SparkV App - Limpieza de Vehículos
Descripción del Proyecto
SparkV es una aplicación móvil desarrollada en Android que permite a los usuarios acceder a servicios de limpieza profesional para vehículos. La app está dividida en dos roles principales: Cliente y Experto en Limpieza , cada uno con funcionalidades específicas.

Características Principales
Clientes :
Visualizar categorías de servicios.
Ver servicios populares.
Acceder al perfil personal.
Consultar soporte técnico.
Añadir servicios a un carrito de compras.
Expertos en Limpieza :
Panel de control con estadísticas generales.
Gestión de tareas y pedidos.
Navegación lateral para acceder a diferentes secciones.
La app utiliza Firebase Firestore para almacenamiento de datos, incluyendo categorías, servicios populares y reservas. Además, implementa componentes Material Design como BottomAppBar, FloatingActionButtons y RecyclerViews.

Estructura del Proyecto
SparkV/
├── app/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/
│   │   │   │   └── com.example.sparkv_app/
│   │   │   │       ├── Cliente/
│   │   │   │       │   ├── Activity/
│   │   │   │       │   │   ├── ClienteMainActivity.java
│   │   │   │       │   │   ├── PerfilActivity.java
│   │   │   │       │   │   ├── SoporteActivity.java
│   │   │   │       │   │   └── ...
│   │   │   │       │   ├── Domain/
│   │   │   │       │   │   ├── CategoriaDomain.java
│   │   │   │       │   │   └── PopularDomain.java
│   │   │   │       │   └── Adaptador/
│   │   │   │       │       ├── CategoriasAdaptador.java
│   │   │   │       │       └── PopularAdaptador.java
│   │   │   │       └── Admin/
│   │   │   │           ├── BaseActivity.java
│   │   │   │           └── ...
│   │   │   ├── res/
│   │   │   │   ├── layout/
│   │   │   │   │   ├── activity_main.xml
│   │   │   │   │   ├── activity_perfil.xml
│   │   │   │   │   └── ...
│   │   │   │   ├── drawable/
│   │   │   │   │   ├── person_24.png
│   │   │   │   │   ├── shopping_cart.png
│   │   │   │   │   └── ...
│   │   │   │   ├── values/
│   │   │   │   │   ├── colors.xml
│   │   │   │   │   └── strings.xml
│   │   │   │   └── menu/
│   │   │   │       └── menu_lateral.xml
│   │   │   └── AndroidManifest.xml
│   │   └── build.gradle
│   └── build/
│       └── ... (archivos compilados)
├── .idea/
│   └── ... (configuración de IntelliJ IDEA)
├── .gradle/
│   └── ... (dependencias Gradle)
└── README.md

Requisitos
Antes de comenzar a trabajar con el proyecto, asegúrate de tener instalado lo siguiente:

Android Studio
SDK de Android actualizado
Google Play Services (para usar Firebase)
Cuenta de desarrollador en Firebase Console
