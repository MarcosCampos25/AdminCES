package com.tatf.tests;

import com.tatf.core.browser.BrowserFactory;
import com.tatf.core.browser.IBrowser;
import com.tatf.core.element.Element;
import com.tatf.core.verification.IVerify;
import org.junit.jupiter.api.*;
import org.openqa.selenium.TimeoutException;

public class AdminCesTest {

    private static IBrowser browser;

    @BeforeEach
    void beforeEach() {
        browser = BrowserFactory.getBrowser(false);
        browser.interaction().navigateTo("http://cestore.ces.com.uy/adminces/");
        browser.find().css("input[type='password']").write("3)ea60e0be3ba12c6ecd%7297868%5c4");
        browser.find().css("button[type='submit']").click();
    }

    @AfterEach
    void afterEach() {
        BrowserFactory.quitBrowser();
    }

    @Test
    void crearCuentaAdministrador() {
        String email = "admin.qa@test.com";
        String contrasenia = "Test1234!";

        browser.find().css("a[href='/adminces/register']").click();
        browser.wait("formAccount").id();
        completarFormularioAltaAdministrador("Juan", "Perez", email, contrasenia, contrasenia, "uruguay");
        browser.find().id("btnRegister").click();

        verificarYCerrarAlertaExito("No se mostró el mensaje de confirmación de alta.");

        iniciarSesionComoAdministrador(email, contrasenia);
        verificarYCerrarAlertaExito("No se mostró el mensaje de confirmación de login.");
    }

    @Test
    void reiniciarContraseniaCuentaExistente() {
        String email = "yaniscorrea@gmail.com";
        String nuevaContrasenia = "Test1234!";

        browser.find().css("a[href='/adminces/forgot-password']").click();
        browser.wait("formResetPassword").id();
        completarFormularioReinicioContrasenia(email, nuevaContrasenia, nuevaContrasenia);
        browser.find().id("btnReset").click();
        verificarYCerrarAlertaExito("No se mostró el mensaje de confirmación de reinicio de contraseña.");

        iniciarSesionComoAdministrador(email, nuevaContrasenia);
        verificarYCerrarAlertaExito("No se pudo iniciar sesión con la nueva contraseña.");
    }

    @Test
    void altaCuentaTesterDesdeAdministrador() {
        String emailAdmin = "leonardoperez@gmail.com";
        String contraseniaAdmin = "12345";

        String nombreTester = "Pedro";
        String apellidoTester = "Gomez";
        String emailTester = "pedro.tester." + System.currentTimeMillis() + "@test.com";
        String paisTester = "Uruguay";
        String contraseniaTester = "Test1234!";
        String nivelTester = "Tester Junior";

        iniciarSesionComoAdministrador(emailAdmin, contraseniaAdmin);
        verificarYCerrarAlertaExito("No se pudo iniciar sesión como Administrador.");

        browser.find().xpath("//a[@id='cardLogin' and @href='/adminces/create-user']").click();
        browser.wait("formCreateUser").id();
        completarFormularioAltaTester(nombreTester, apellidoTester, emailTester, paisTester, contraseniaTester, nivelTester);
        browser.find().id("btnRegister").click();

        verificarYCerrarAlertaExito("No se mostró el mensaje de confirmación de alta del Tester.");


        browser.find().xpath("//a[@id='cardLogin' and @href='/adminces/view-users']").click();
        browser.wait("dataTable").id();

        Element fila = browser.find().xpath("//td[contains(text(), '" + emailTester + "')]/.."); // busco el td con el mail, y me quedo con el padre (el tr que tiene toda la fila)
        IVerify.create().verify(nombreTester, fila.xpath("./td[1]").getText(), "El nombre del Tester no coincide en la tabla.");
        IVerify.create().verify(apellidoTester, fila.xpath("./td[2]").getText(), "El apellido del Tester no coincide en la tabla.");
        IVerify.create().verify(paisTester, fila.xpath("./td[4]").getText(), "El pais del Tester no coincide en la tabla.");
        IVerify.create().verify(nivelTester, fila.xpath("./td[5]").getText(), "El perfil del Tester no coincide en la tabla.");
    }

    @Test
    void eliminarCuentaTester() {
        String email = "yaniscorrea@gmail.com";
        String password = "12345";

        iniciarSesionComoAdministrador(email, password);
        verificarYCerrarAlertaExito("No se pudo iniciar sesión como Administrador.");

        browser.find().xpath("//a[@id='cardLogin' and @href='/adminces/view-users']").click();
        browser.find().xpath("//td[contains(text(), 'jeniffer@gmail.com')]/../td/button").click(); // busco el tester jeniffer y luego tomo el boton asociado a esa fila
        browser.find().css("button.swal2-confirm").click();

        verificarYCerrarAlertaExito("No se pudo confirmar el borrado del Tester.");

        try {
            browser.wait("//td[contains(text(), 'jeniffer@gmail.com')]").invisibilityXpath();
        } catch (TimeoutException e) {
            IVerify.create().verifyFail("El tester jeniffer@gmail.com seguía visible en la tabla tras el borrado.");
        }
    }


    private void completarFormularioAltaAdministrador(String nombre, String apellido, String email, String contrasenia, String repetirContrasenia, String paisNacimiento) {
        browser.find().css("input[name='inputFirstName']").write(nombre);
        browser.find().css("input[name='inputLastName']").write(apellido);
        browser.find().css("input[name='inputEmail']").write(email);
        browser.find().css("input[name='inputPassword']").write(contrasenia);
        browser.find().css("input[name='inputRepeatPassword']").write(repetirContrasenia);
        browser.find().css("input[name='inputCountry']").write(paisNacimiento);
    }

    private void completarFormularioReinicioContrasenia(String email, String nuevaContrasenia, String repetirContrasenia) {
        browser.find().css("#formResetPassword input[name='inputEmail']").write(email);
        browser.find().css("#formResetPassword input[name='inputPassword']").write(nuevaContrasenia);
        browser.find().css("#formResetPassword input[name='inputRepeatPassword']").write(repetirContrasenia);
    }

    private void completarFormularioAltaTester(String nombre, String apellido, String email, String paisNacimiento, String contraseniaTester, String nivel) {
        browser.find().css("input[name='inputFirstName']").write(nombre);
        browser.find().css("input[name='inputLastName']").write(apellido);
        browser.find().css("input[name='inputEmail']").write(email);
        browser.find().css("select[name='inputCountry']").selectValue(paisNacimiento);
        browser.find().css("input[name='inputPassword']").write(contraseniaTester);
        seleccionarNivelTester(nivel);
    }

    private void seleccionarNivelTester(String nivel) {
        switch (nivel) {
            case "Tester Junior":
                browser.find().id("testerJunior").click();
                break;
            case "Tester Senior":
                browser.find().id("testerSenior").click();
                break;
            case "Tester Líder":
                browser.find().id("testerLead").click();
                break;
            default:
                throw new IllegalArgumentException("Nivel de tester no soportado: " + nivel);
        }
    }

    private void verificarYCerrarAlertaExito(String mensajeError) {
        browser.wait("h2.swal2-title").css();
        Element alerta = browser.find().css("h2.swal2-title");
        IVerify.create().verify("Correcto!", alerta.getText(), mensajeError);
        browser.wait("button.swal2-confirm").css();
        browser.find().css("button.swal2-confirm").click();
        browser.wait(".swal2-container").invisibilityCss();
    }

    private void iniciarSesionComoAdministrador(String email, String contrasenia) {
        browser.find().css("a[href='/adminces/login']").click();
        browser.wait("formLogin").id();
        browser.find().css("#formLogin input[name='inputEmail']").write(email);
        browser.find().css("#formLogin input[name='inputPassword']").write(contrasenia);
        browser.find().xpath("//button[contains(text(), 'Iniciar Sesión')]").click();
    }
}
