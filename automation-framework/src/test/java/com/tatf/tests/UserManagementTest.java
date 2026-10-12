package com.tatf.tests;

import com.tatf.crearusuario.task.CrearUsuarioTask;
import com.tatf.dashboard.task.DashboardTask;
import com.tatf.login.task.LoginTask;
import com.tatf.viewusers.task.ViewUsersTask;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

public class UserManagementTest extends BaseTest {

    private LoginTask loginTask;
    private DashboardTask dashboardTask;
    private CrearUsuarioTask crearUsuarioTask;
    private ViewUsersTask viewUsersTask;

    @BeforeEach
    void beforeEach() {
        loginTask = new LoginTask(browser);
        dashboardTask = new DashboardTask(browser);
        crearUsuarioTask = new CrearUsuarioTask(browser);
        viewUsersTask = new ViewUsersTask(browser);
    }

    @ParameterizedTest(name = "{arguments}")
    @CsvFileSource(resources = "/data/alta_testers.csv", useHeadersInDisplayName = true)
    void altaCuentaTesterDesdeAdministrador(String adminEmail, String adminPassword, String nombre, String apellido,
                                            String emailPrefijo, String emailDominio, String pais, String password,
                                            String perfil) {
        // uso  el time milis para que el mail sea único en cada ejecución
        String emailTester = emailPrefijo + System.currentTimeMillis() + emailDominio;

        loginTask.login(adminEmail, adminPassword);
        verifySuccessAlert("No se pudo iniciar sesión como Administrador.");

        dashboardTask.goToCreateUser();
        crearUsuarioTask.createTester(nombre, apellido, emailTester, pais, password, perfil);
        verifySuccessAlert("No se mostró el mensaje de confirmación de alta del Tester.");

        dashboardTask.goToViewUsers();
        verify.verify(nombre, viewUsersTask.getFirstName(emailTester), "El nombre del Tester no coincide en la tabla.");
        verify.verify(apellido, viewUsersTask.getLastName(emailTester), "El apellido del Tester no coincide en la tabla.");
        verify.verify(pais, viewUsersTask.getCountry(emailTester), "El pais del Tester no coincide en la tabla.");
        verify.verify(perfil, viewUsersTask.getLevel(emailTester), "El perfil del Tester no coincide en la tabla.");
    }

    @ParameterizedTest(name = "{arguments}")
    @CsvFileSource(resources = "/data/eliminar_testers.csv", useHeadersInDisplayName = true)
    void eliminarCuentaTester(String adminEmail, String adminPassword, String emailTester) {
        loginTask.login(adminEmail, adminPassword);
        verifySuccessAlert("No se pudo iniciar sesión como Administrador.");

        dashboardTask.goToViewUsers();
        viewUsersTask.deleteTester(emailTester);
        verifySuccessAlert("No se pudo confirmar el borrado del Tester.");

        verify.verifyTrue(viewUsersTask.isTesterRemoved(emailTester),
                "El tester " + emailTester + " seguía visible en la tabla tras el borrado.");
    }
}
