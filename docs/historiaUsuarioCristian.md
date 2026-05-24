# HISTORIA DE USUARIO LOGIN – 03

| Campo | Descripción |
| :--- | :--- |
| **Título** | Autenticación y Login de Usuarios |
| **Como** | Usuario del sistema |
| **Quiero** | Ingresar mis credenciales (usuario/correo y contraseña) en la interfaz de inicio de sesión |
| **Para** | Acceder de forma segura a las funcionalidades correspondientes del aplicativo según mi rol asignado |
| **Prioridad** | Alta |
| **Estado** | En Desarrollo |
| **Responsable** | [Tu Nombre Completo] |

## Criterios de aceptación

| ID | Criterio |
| :--- | :--- |
| **CA-01** | El sistema debe mostrar un formulario limpio con los campos de entrada para "Usuario o Correo Electrónico", "Contraseña" y un botón visible de "Iniciar Sesión". |
| **CA-02** | El sistema debe validar que los campos obligatorios no estén vacíos al intentar hacer clic en el botón de ingreso, mostrando una advertencia en pantalla. |
| **CA-03** | El sistema debe verificar las credenciales ingresadas de forma segura contra los registros de la base de datos. |
| **CA-04** | El sistema debe redirigir automáticamente al usuario a la pantalla principal (Dashboard) asignada a su rol específico si la autenticación es exitosa. |
| **CA-05** | El sistema debe mostrar un mensaje de error claro ("Usuario o contraseña incorrectos") si la autenticación falla, protegiendo los datos sin especificar cuál campo falló. |
| **CA-06** | El sistema debe proveer una opción o enlace visible que diga "¿Olvidaste tu contraseña?" para iniciar el flujo de recuperación de accesos. |