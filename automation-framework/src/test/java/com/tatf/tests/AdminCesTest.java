package com.tatf.tests;

import com.tatf.alert.task.AlertTask;
import com.tatf.core.browser.BrowserFactory;
import com.tatf.core.browser.IBrowser;
import com.tatf.crearusuario.data.CrearUsuarioData;
import com.tatf.crearusuario.task.CrearUsuarioTask;
import com.tatf.dashboard.task.DashboardTask;
import com.tatf.forgotpassword.data.ForgotPasswordData;
import com.tatf.forgotpassword.task.ForgotPasswordTask;
import com.tatf.home.task.HomeTask;
import com.tatf.login.data.LoginData;
import com.tatf.login.task.LoginTask;
import com.tatf.register.data.RegisterData;
import com.tatf.register.task.RegisterTask;
import com.tatf.viewusers.data.ViewUsersData;
import com.tatf.viewusers.task.ViewUsersTask;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AdminCesTest {

    private static IBrowser browser;

    private HomeTask homeTask;
    private RegisterTask registerTask;
    private LoginTask loginTask;
    private ForgotPasswordTask forgotPasswordTask;
    private DashboardTask dashboardTask;
    private CrearUsuarioTask crearUsuarioTask;
    private ViewUsersTask viewUsersTask;
    private AlertTask alertTask;

    @BeforeEach
    void beforeEach() {
        browser = BrowserFactory.getBrowser(false);
        homeTask = new HomeTask(browser);
        registerTask = new RegisterTask(browser);
        loginTask = new LoginTask(browser);
        forgotPasswordTask = new ForgotPasswordTask(browser);
        dashboardTask = new DashboardTask(browser);
        crearUsuarioTask = new CrearUsuarioTask(browser);
        viewUsersTask = new ViewUsersTask(browser);
        alertTask = new AlertTask(browser);

        homeTask.accessAdminces();
    }

    @AfterEach
    void afterEach() {
        BrowserFactory.quitBrowser();
    }

    @Test
    void crearCuentaAdministrador() {
        homeTask.goToRegister();
        registerTask.registerAdministrator(RegisterData.FIRST_NAME, RegisterData.LAST_NAME, RegisterData.EMAIL,
                RegisterData.PASSWORD, RegisterData.PASSWORD, RegisterData.COUNTRY);
        alertTask.verifySuccessAndClose("No se mostró el mensaje de confirmación de alta.");

        loginTask.login(RegisterData.EMAIL, RegisterData.PASSWORD);
        alertTask.verifySuccessAndClose("No se mostró el mensaje de confirmación de login.");
    }

    @Test
    void reiniciarContraseniaCuentaExistente() {
        forgotPasswordTask.resetPassword(ForgotPasswordData.EXISTING_EMAIL, ForgotPasswordData.NEW_PASSWORD,
                ForgotPasswordData.NEW_PASSWORD);
        alertTask.verifySuccessAndClose("No se mostró el mensaje de confirmación de reinicio de contraseña.");

        loginTask.login(ForgotPasswordData.EXISTING_EMAIL, ForgotPasswordData.NEW_PASSWORD);
        alertTask.verifySuccessAndClose("No se pudo iniciar sesión con la nueva contraseña.");
    }

    @Test
    void altaCuentaTesterDesdeAdministrador() {
        String emailTester = CrearUsuarioData.EMAIL_PREFIX + System.currentTimeMillis() + CrearUsuarioData.EMAIL_DOMAIN;

        loginTask.login(LoginData.ADMIN_EMAIL, LoginData.ADMIN_PASSWORD);
        alertTask.verifySuccessAndClose("No se pudo iniciar sesión como Administrador.");

        dashboardTask.goToCreateUser();
        crearUsuarioTask.createTester(CrearUsuarioData.FIRST_NAME, CrearUsuarioData.LAST_NAME, emailTester,
                CrearUsuarioData.COUNTRY, CrearUsuarioData.PASSWORD, CrearUsuarioData.LEVEL_JUNIOR);
        alertTask.verifySuccessAndClose("No se mostró el mensaje de confirmación de alta del Tester.");

        dashboardTask.goToViewUsers();
        viewUsersTask.verifyTester(emailTester, CrearUsuarioData.FIRST_NAME, CrearUsuarioData.LAST_NAME,
                CrearUsuarioData.COUNTRY, CrearUsuarioData.LEVEL_JUNIOR);
    }

    @Test
    void eliminarCuentaTester() {
        loginTask.login(ForgotPasswordData.EXISTING_EMAIL, LoginData.ADMIN_PASSWORD);
        alertTask.verifySuccessAndClose("No se pudo iniciar sesión como Administrador.");

        dashboardTask.goToViewUsers();
        viewUsersTask.deleteTester(ViewUsersData.EMAIL_TO_DELETE);
        alertTask.verifySuccessAndClose("No se pudo confirmar el borrado del Tester.");

        viewUsersTask.verifyTesterNotVisible(ViewUsersData.EMAIL_TO_DELETE);
    }
}
