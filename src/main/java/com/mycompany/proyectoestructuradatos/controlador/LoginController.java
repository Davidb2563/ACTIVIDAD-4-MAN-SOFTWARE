package com.mycompany.proyectoestructuradatos.controlador;

import com.mycompany.proyectoestructuradatos.controlador.gestorDeVistas.GestorDeVistas;
import com.mycompany.proyectoestructuradatos.vista.JFrmLogin;
import com.mycompany.proyectoestructuradatos.modelo.dao.UsuarioDao;
import com.mycompany.proyectoestructuradatos.modelo.entidad.Usuario;
import com.mycompany.proyectoestructuradatos.sesion.SesionUsuario;
import com.mycompany.proyectoestructuradatos.vista.JFmCrearUsuario;
import com.mycompany.proyectoestructuradatos.vista.JFmMenu;
import javax.swing.JOptionPane;

/**
 * REINGENIERÍA PERFECTIVA:
 * Se optimizó el flujo de validaciones previas antes de consultar la base de datos.
 * Se estandarizó el uso del patrón MVC encapsulando las alertas visuales a través de la vista.
 * Se corrigieron inconsistencias de nombres de variables para mejorar la mantenibilidad del código.
 */
public class LoginController {

    private final UsuarioDao usuarioDAO;
    private final JFrmLogin vista;

    public LoginController(UsuarioDao usuarioDAO, JFrmLogin vista) {
        this.usuarioDAO = usuarioDAO;
        this.vista = vista;

        this.vista.getBtnAceptar().addActionListener(e -> autenticarUsuario());
        this.vista.getBtnRegistrar().addActionListener(e -> crearUsuario());
    }

    public LoginController(JFrmLogin vista) {
        this.usuarioDAO = new UsuarioDao();
        this.vista = vista;

        this.vista.getBtnAceptar().addActionListener(e -> autenticarUsuario());
        // Ajuste sutil: Aseguramos que el botón registrar también funcione en este constructor secundario si se requiere
        this.vista.getBtnRegistrar().addActionListener(e -> crearUsuario());
    }

    private void autenticarUsuario() {
        try {
            // AJUSTE 1: Uso de .trim() para limpiar espacios accidentales al inicio/final
            String username = vista.getUsuario().trim();
            String password = vista.getPassword();

            // AJUSTE 2: Validación previa (Defensive Programming).
            // Si están vacíos, frena la ejecución inmediatamente y evita un viaje innecesario a la base de datos.
            if (username.isEmpty() || password.isEmpty()) {
                vista.mostrarMensaje("Por favor, ingrese el usuario y la contraseña.");
                return;
            }

            Usuario usuario = usuarioDAO.obtenerUsuario(username, password);

            if (usuario != null) {
                vista.limpiarcampos();

                switch (usuario.getIdRol()) {
                    case 1:
                        // AJUSTE 3: Desacoplamiento de JOptionPane usando el método reutilizable de la propia vista
                        vista.mostrarMensaje("Ingreso exitoso");

                        SesionUsuario.getInstancia().setUsuario(usuario);
                        JFmMenu mantenimientoVista = new JFmMenu();

                        // AJUSTE 4: Corrección de nomenclatura (Reingeniería de nombres de variables)
                        MenuController menuController = new MenuController(mantenimientoVista, vista);

                        GestorDeVistas.mostrarVista(vista, mantenimientoVista);
                        break;

                    default:
                        vista.mostrarMensaje("¡Rol no reconocido!");
                        break;
                }
            } else {
                vista.mostrarMensaje("Usuario o contraseña incorrectos");
                vista.limpiarcampos();
            }

        } catch (Exception e) {
            // AJUSTE 5: Gestión profesional de excepciones.
            // Reemplazo de e.printStackTrace() por un mensaje emergente controlado respetando la UI.
            JOptionPane.showMessageDialog(vista,
                    "Ocurrió un error al intentar ingresar a la opción seleccionada.\nDetalle: " + e.getMessage(),
                    "Error de Login",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public void crearUsuario() {
        JFmCrearUsuario crearUsuario = new JFmCrearUsuario();
        CrearUsuarioController administradorController = new CrearUsuarioController(crearUsuario, vista);
        vista.limpiarcampos();
        GestorDeVistas.mostrarVista(vista, crearUsuario);
    }
}