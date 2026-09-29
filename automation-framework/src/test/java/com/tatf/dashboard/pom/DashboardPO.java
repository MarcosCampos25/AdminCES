package com.tatf.dashboard.pom;

import com.tatf.core.browser.IBrowser;

public class DashboardPO {
    private static final String CARD_BY_HREF = "//a[@id='cardLogin' and @href='%s']";

    private final IBrowser browser;

    public DashboardPO(IBrowser browser) {
        this.browser = browser;
    }

    public void clickCard(String href) {
        browser.find().xpath(String.format(CARD_BY_HREF, href)).click();
    }
}
