package com.tatf.alert.task;

import com.tatf.alert.pom.AlertPO;
import com.tatf.core.browser.IBrowser;

public class AlertTask {
    private final AlertPO alertPO;

    public AlertTask(IBrowser browser) {
        this.alertPO = new AlertPO(browser);
    }

    public String getTitle() {
        alertPO.waitTitle();
        return alertPO.getTitle();
    }

    public void confirm() {
        alertPO.waitConfirmButton();
        alertPO.clickConfirm();
    }

    public void close() {
        confirm();
        alertPO.waitClosed();
    }
}
