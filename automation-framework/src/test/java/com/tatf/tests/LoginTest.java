package com.tatf.tests;

import com.tatf.login.task.LoginTask;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

public class LoginTest extends BaseTest {

    private LoginTask loginTask;

    @BeforeEach
    void beforeEach() {
        loginTask = new LoginTask(browser);
    }

    @ParameterizedTest(name = "{arguments}")
    @CsvFileSource(resources = "/data/login_administradores.csv", useHeadersInDisplayName = true)
    void iniciarSesionComoAdministrador(String email, String password) {
        loginTask.login(email, password);
        verifySuccessAlert("No se pudo iniciar sesión como Administrador.");
    }
}
