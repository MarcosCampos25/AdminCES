package com.tatf.tests;

import com.tatf.forgotpassword.task.ForgotPasswordTask;
import com.tatf.login.task.LoginTask;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

@Feature("Reinicio de contraseña")
@Tag("password")
public class ForgotPasswordTest extends BaseTest {

    private ForgotPasswordTask forgotPasswordTask;
    private LoginTask loginTask;

    @BeforeEach
    void beforeEach() {
        forgotPasswordTask = new ForgotPasswordTask(browser);
        loginTask = new LoginTask(browser);
    }

    @ParameterizedTest(name = "{arguments}")
    @CsvFileSource(resources = "/data/forgot_password_cuentas.csv", useHeadersInDisplayName = true)
    void reiniciarContraseniaCuentaExistente(String email, String nuevaPassword) {
        forgotPasswordTask.resetPassword(email, nuevaPassword, nuevaPassword);
        verifySuccessAlert("No se mostró el mensaje de confirmación de reinicio de contraseña.");

        loginTask.login(email, nuevaPassword);
        verifySuccessAlert("No se pudo iniciar sesión con la nueva contraseña.");
    }
}
