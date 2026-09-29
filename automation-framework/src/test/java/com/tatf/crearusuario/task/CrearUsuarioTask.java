package com.tatf.crearusuario.task;

import com.tatf.core.browser.IBrowser;
import com.tatf.crearusuario.data.CrearUsuarioData;
import com.tatf.crearusuario.pom.CrearUsuarioPO;

public class CrearUsuarioTask {
    private final CrearUsuarioPO crearUsuarioPO;

    public CrearUsuarioTask(IBrowser browser) {
        this.crearUsuarioPO = new CrearUsuarioPO(browser);
    }

    /**
     * Completa el formulario de alta de tester y lo envía.
     */
    public void createTester(String firstName, String lastName, String email, String country, String password, String level) {
        crearUsuarioPO.waitForm();
        crearUsuarioPO.writeFirstName(firstName);
        crearUsuarioPO.writeLastName(lastName);
        crearUsuarioPO.writeEmail(email);
        crearUsuarioPO.selectCountry(country);
        crearUsuarioPO.writePassword(password);
        selectLevel(level);
        crearUsuarioPO.clickRegister();
    }

    private void selectLevel(String level) {
        switch (level) {
            case CrearUsuarioData.LEVEL_JUNIOR:
                crearUsuarioPO.clickJunior();
                break;
            case CrearUsuarioData.LEVEL_SENIOR:
                crearUsuarioPO.clickSenior();
                break;
            case CrearUsuarioData.LEVEL_LEAD:
                crearUsuarioPO.clickLead();
                break;
            default:
                throw new IllegalArgumentException("Nivel de tester no soportado: " + level);
        }
    }
}
