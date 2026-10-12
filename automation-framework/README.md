# Estructura del proyecto

## Interfaces

| Entidad  | Descripción                                                                                                       |
|----------|-------------------------------------------------------------------------------------------------------------------|
| IBrowser | Interfaz que define operaciones comunes para la gestión del navegador y la espera de elementos en una página web. |
| IVerify  | Interfaz que define métodos para verificar resultados esperados con obtenidos y agregar mensajes asociados.       |

## Patrones aplicados

| Patrón     | Entidad                 | Descripción                                                               |
|------------|-------------------------|---------------------------------------------------------------------------|
| Factory    | DriverManagerFactory    | Clase encargada de indicar que driver se debe retornar.                   |
| Singlenton | DriverManagerSinglenton | Clase encargada de mantener una sola instancia configurada con un driver. |

## Suites

| Suite                     | Selección                              | Pruebas                                              |
|---------------------------|----------------------------------------|------------------------------------------------------|
| RegresionSuite            | Paquete `com.tatf.tests`               | Todas.                                               |
| SmokeSuite                | Tag `smoke`                            | Inicio de sesión.                                    |
| CuentasAdministradorSuite | Clases                                 | Registro, inicio de sesión y reinicio de contraseña. |
| GestionUsuariosSuite      | Tag `usuarios`                         | Alta y borrado de Testers.                           |

## Ejecución

El navegador se define en `src/test/resources/config.properties` (`browser=CHROME` o `EDGE`) y se puede
cambiar por línea de comandos con `-Dbrowser`.

```
mvn clean test -Dtest=SmokeSuite
mvn clean test -Dtest=RegresionSuite -Dbrowser=EDGE
```

## Reporte

Las ejecuciones dejan los resultados en `target/allure-results`. Para generar y abrir el reporte:

```
mvn allure:serve
```
