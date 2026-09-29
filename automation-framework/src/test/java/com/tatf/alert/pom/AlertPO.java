package com.tatf.alert.pom;

import com.tatf.core.browser.IBrowser;

public class AlertPO {
    private static final String TITLE = "h2.swal2-title";
    private static final String BUTTON_CONFIRM = "button.swal2-confirm";
    private static final String CONTAINER = ".swal2-container";

    private final IBrowser browser;

    public AlertPO(IBrowser browser) {
        this.browser = browser;
    }

    public void waitTitle() {
        browser.wait(TITLE).css();
    }

    public String getTitle() {
        return browser.find().css(TITLE).getText();
    }

    public void waitConfirmButton() {
        browser.wait(BUTTON_CONFIRM).css();
    }

    public void clickConfirm() {
        browser.find().css(BUTTON_CONFIRM).click();
    }

    public void waitClosed() {
        browser.wait(CONTAINER).invisibilityCss();
    }
}
