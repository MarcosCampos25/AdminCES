package com.tatf.login.task;

import com.tatf.core.browser.IBrowser;
import com.tatf.home.pom.HomePO;
import com.tatf.login.pom.LoginPO;

public class LoginTask {
    private final HomePO homePO;
    private final LoginPO loginPO;

    public LoginTask(IBrowser browser) {
        this.homePO = new HomePO(browser);
        this.loginPO = new LoginPO(browser);
    }

    public void login(String email, String password) {
        homePO.clickLoginLink();
        loginPO.waitForm();
        loginPO.writeEmail(email);
        loginPO.writePassword(password);
        loginPO.clickLogin();
    }
}
