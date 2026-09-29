package com.tatf.dashboard.task;

import com.tatf.core.browser.IBrowser;
import com.tatf.dashboard.data.DashboardData;
import com.tatf.dashboard.pom.DashboardPO;

public class DashboardTask {
    private final DashboardPO dashboardPO;

    public DashboardTask(IBrowser browser) {
        this.dashboardPO = new DashboardPO(browser);
    }

    public void goToCreateUser() {
        dashboardPO.clickCard(DashboardData.HREF_CREATE_USER);
    }

    public void goToViewUsers() {
        dashboardPO.clickCard(DashboardData.HREF_VIEW_USERS);
    }
}
