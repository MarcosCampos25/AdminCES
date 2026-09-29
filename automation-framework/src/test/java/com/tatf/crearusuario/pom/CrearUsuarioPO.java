package com.tatf.crearusuario.pom;

import com.tatf.core.browser.IBrowser;

public class CrearUsuarioPO {
    private static final String FORM = "formCreateUser";
    private static final String INPUT_FIRST_NAME = "input[name='inputFirstName']";
    private static final String INPUT_LAST_NAME = "input[name='inputLastName']";
    private static final String INPUT_EMAIL = "input[name='inputEmail']";
    private static final String SELECT_COUNTRY = "select[name='inputCountry']";
    private static final String INPUT_PASSWORD = "input[name='inputPassword']";
    private static final String RADIO_JUNIOR = "testerJunior";
    private static final String RADIO_SENIOR = "testerSenior";
    private static final String RADIO_LEAD = "testerLead";
    private static final String BUTTON_REGISTER = "btnRegister";

    private final IBrowser browser;

    public CrearUsuarioPO(IBrowser browser) {
        this.browser = browser;
    }

    public void waitForm() {
        browser.wait(FORM).id();
    }

    public void writeFirstName(String firstName) {
        browser.find().css(INPUT_FIRST_NAME).write(firstName);
    }

    public void writeLastName(String lastName) {
        browser.find().css(INPUT_LAST_NAME).write(lastName);
    }

    public void writeEmail(String email) {
        browser.find().css(INPUT_EMAIL).write(email);
    }

    public void selectCountry(String country) {
        browser.find().css(SELECT_COUNTRY).selectValue(country);
    }

    public void writePassword(String password) {
        browser.find().css(INPUT_PASSWORD).write(password);
    }

    public void clickJunior() {
        browser.find().id(RADIO_JUNIOR).click();
    }

    public void clickSenior() {
        browser.find().id(RADIO_SENIOR).click();
    }

    public void clickLead() {
        browser.find().id(RADIO_LEAD).click();
    }

    public void clickRegister() {
        browser.find().id(BUTTON_REGISTER).click();
    }
}
