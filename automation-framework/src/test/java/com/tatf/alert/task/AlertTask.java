package com.tatf.alert.task;

import com.tatf.alert.data.AlertData;
import com.tatf.alert.pom.AlertPO;
import com.tatf.core.browser.IBrowser;
import com.tatf.core.verification.IVerify;

public class AlertTask {
    private final AlertPO alertPO;

    public AlertTask(IBrowser browser) {
        this.alertPO = new AlertPO(browser);
    }

    public void verifySuccessAndClose(String errorMessage) {
        alertPO.waitTitle();
        IVerify.create().verify(AlertData.SUCCESS_TITLE, alertPO.getTitle(), errorMessage);
        alertPO.waitConfirmButton();
        alertPO.clickConfirm();
        alertPO.waitClosed();
    }

    public void confirm() {
        alertPO.waitConfirmButton();
        alertPO.clickConfirm();
    }
}
