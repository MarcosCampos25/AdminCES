package com.tatf.suites;

import com.tatf.tests.ForgotPasswordTest;
import com.tatf.tests.LoginTest;
import com.tatf.tests.RegisterTest;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

// pruebas sobre la cuenta del Administrador: registro, inicio de sesión y reinicio de contraseña.
@Suite
@SelectClasses({RegisterTest.class, LoginTest.class, ForgotPasswordTest.class})
public class CuentasAdministradorSuite {
}
