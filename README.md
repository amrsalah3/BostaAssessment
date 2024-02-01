## Bosta Technical Assessment Solution - Amr Salah Abdelhady

### Architecture
- MVVM is applied with Clean Architecture principles below:
  
<img src="https://github.com/amrsalah3/BostaAssessment/assets/52531091/e009fefa-804f-49fb-a53a-db4c4d321fc2" width=300>

**Packages** 

- **domain**: Contains business rules and does not depend on any other layers. Thus, can be used with different data layer implementations or different presentation layer implementation (e.g. Kotlin and Compose Multiplatform.
- **data**: Contains data sources and repositories' implementations (Repository business rules/contracts (interfaces) are defined in the domain layer)
- **ui**: Contains the presentation layer including Composables and the ViewModels
- **di**: Contains dependency injection among the app
