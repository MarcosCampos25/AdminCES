package com.tatf.register.pom;

import com.tatf.core.browser.IBrowser;

public class RegisterPO {
    private static final String FORM = "formAccount";
    private static final String INPUT_FIRST_NAME = "input[name='inputFirstName']";
    private static final String INPUT_LAST_NAME = "input[name='inputLastName']";
    private static final String INPUT_EMAIL = "input[name='inputEmail']";
    private static final String INPUT_PASSWORD = "input[name='inputPassword']";
    private static final String INPUT_REPEAT_PASSWORD = "input[name='inputRepeatPassword']";
    private static final String INPUT_COUNTRY = "input[name='inputCountry']";
    private static final String BUTTON_REGISTER = "btnRegister";

    private final IBrowser browser;

    public RegisterPO(IBrowser browser) {
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

    public void writePassword(String password) {
        browser.find().css(INPUT_PASSWORD).write(password);
    }

    public void writeRepeatPassword(String password) {
        browser.find().css(INPUT_REPEAT_PASSWORD).write(password);
    }

    public void writeCountry(String country) {
        browser.find().css(INPUT_COUNTRY).write(country);
    }

    public void clickRegister() {
        browser.find().id(BUTTON_REGISTER).click();
    }
}
