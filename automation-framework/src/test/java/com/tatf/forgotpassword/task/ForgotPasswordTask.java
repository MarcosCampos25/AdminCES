package com.tatf.forgotpassword.task;

import com.tatf.core.browser.IBrowser;
import com.tatf.forgotpassword.pom.ForgotPasswordPO;
import com.tatf.home.pom.HomePO;

public class ForgotPasswordTask {
    private final HomePO homePO;
    private final ForgotPasswordPO forgotPasswordPO;

    public ForgotPasswordTask(IBrowser browser) {
        this.homePO = new HomePO(browser);
        this.forgotPasswordPO = new ForgotPasswordPO(browser);
    }


    public void resetPassword(String email, String newPassword, String repeatPassword) {
        homePO.clickForgotPasswordLink();
        forgotPasswordPO.waitForm();
        forgotPasswordPO.writeEmail(email);
        forgotPasswordPO.writePassword(newPassword);
        forgotPasswordPO.writeRepeatPassword(repeatPassword);
        forgotPasswordPO.clickReset();
    }
}
