package com.tatf.forgotpassword.pom;

import com.tatf.core.browser.IBrowser;

public class ForgotPasswordPO {
    private static final String FORM = "formResetPassword";
    private static final String INPUT_EMAIL = "#formResetPassword input[name='inputEmail']";
    private static final String INPUT_PASSWORD = "#formResetPassword input[name='inputPassword']";
    private static final String INPUT_REPEAT_PASSWORD = "#formResetPassword input[name='inputRepeatPassword']";
    private static final String BUTTON_RESET = "btnReset";

    private final IBrowser browser;

    public ForgotPasswordPO(IBrowser browser) {
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

    public void writeRepeatPassword(String password) {
        browser.find().css(INPUT_REPEAT_PASSWORD).write(password);
    }

    public void clickReset() {
        browser.find().id(BUTTON_RESET).click();
    }
}
