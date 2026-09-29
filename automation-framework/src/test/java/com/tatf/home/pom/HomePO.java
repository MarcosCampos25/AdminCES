package com.tatf.home.pom;

import com.tatf.core.browser.IBrowser;

/**
 * Pantalla de acceso (password) y menú inicial de las aplicaciones.
 */
public class HomePO {
    private static final String INPUT_PASSWORD = "input[type='password']";
    private static final String BUTTON_SUBMIT = "button[type='submit']";
    private static final String LINK_FORMULARIO = "Formulario";
    private static final String LINK_REGISTER = "a[href='/adminces/register']";
    private static final String LINK_FORGOT_PASSWORD = "a[href='/adminces/forgot-password']";
    private static final String LINK_LOGIN = "a[href='/adminces/login']";

    private final IBrowser browser;

    public HomePO(IBrowser browser) {
        this.browser = browser;
    }

    public void navigateTo(String url) {
        browser.interaction().navigateTo(url);
    }

    public void writePassword(String password) {
        browser.find().css(INPUT_PASSWORD).write(password);
    }

    public void clickSubmit() {
        browser.find().css(BUTTON_SUBMIT).click();
    }

    public void clickRegisterLink() {
        browser.find().css(LINK_REGISTER).click();
    }

    public void clickForgotPasswordLink() {
        browser.find().css(LINK_FORGOT_PASSWORD).click();
    }

    public void clickLoginLink() {
        browser.find().css(LINK_LOGIN).click();
    }
}
