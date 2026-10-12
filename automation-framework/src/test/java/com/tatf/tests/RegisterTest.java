package com.tatf.tests;

import com.tatf.login.task.LoginTask;
import com.tatf.register.task.RegisterTask;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

@Feature("Registro de Administrador")
@Tag("registro")
public class RegisterTest extends BaseTest {

    private RegisterTask registerTask;
    private LoginTask loginTask;

    @BeforeEach
    void beforeEach() {
        registerTask = new RegisterTask(browser);
        loginTask = new LoginTask(browser);
    }

    @ParameterizedTest(name = "{arguments}")
    @CsvFileSource(resources = "/data/register_administradores.csv", useHeadersInDisplayName = true)
    void crearCuentaAdministrador(String nombre, String apellido, String email, String password, String pais) {
        registerTask.registerAdministrator(nombre, apellido, email, password, password, pais);
        verifySuccessAlert("No se mostró el mensaje de confirmación de alta.");

        loginTask.login(email, password);
        verifySuccessAlert("No se mostró el mensaje de confirmación de login.");
    }
}
