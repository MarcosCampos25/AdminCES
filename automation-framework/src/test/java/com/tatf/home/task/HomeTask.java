package com.tatf.home.task;

import com.tatf.config.TestConfig;
import com.tatf.core.browser.IBrowser;
import com.tatf.home.pom.HomePO;

public class HomeTask {
    private final HomePO homePO;

    public HomeTask(IBrowser browser) {
        this.homePO = new HomePO(browser);
    }

    public void accessAdminces() {
        access(TestConfig.urlAdminces(), TestConfig.passwordAdminces());
    }

    private void access(String url, String password) {
        homePO.navigateTo(url);
        homePO.writePassword(password);
        homePO.clickSubmit();
    }
}
