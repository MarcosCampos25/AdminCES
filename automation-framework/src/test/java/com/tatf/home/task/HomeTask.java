package com.tatf.home.task;

import com.tatf.core.browser.IBrowser;
import com.tatf.home.data.HomeData;
import com.tatf.home.pom.HomePO;

public class HomeTask {
    private final HomePO homePO;

    public HomeTask(IBrowser browser) {
        this.homePO = new HomePO(browser);
    }

    public void accessAdminces() {
        access(HomeData.URL_ADMINCES, HomeData.PASSWORD_ADMINCES);
    }

    public void goToRegister() {
        homePO.clickRegisterLink();
    }

    private void access(String url, String password) {
        homePO.navigateTo(url);
        homePO.writePassword(password);
        homePO.clickSubmit();
    }
}
