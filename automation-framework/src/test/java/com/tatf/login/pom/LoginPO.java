package com.tatf.login.pom;

import com.tatf.core.browser.IBrowser;

public class LoginPO {
    private static final String FORM = "formLogin";
    private static final String INPUT_EMAIL = "#formLogin input[name='inputEmail']";
    private static final String INPUT_PASSWORD = "#formLogin input[name='inputPassword']";
    private static final String BUTTON_LOGIN = "//button[contains(text(), 'Iniciar Sesión')]";

    private final IBrowser browser;

    public LoginPO(IBrowser browser) {
        this.browser = browser;
    }

    public void waitForm() {
        browser.wait(FORM).id();
    }

    public void writeEmail(String email) {
        browser.find().css(INPUT_EMAIL).write(email);
    }

    public void writePassword(String password) {
        browser.find().css(INPUT_PASSWORD).write(password);
    }

    public void clickLogin() {
        browser.find().xpath(BUTTON_LOGIN).click();
    }
}
