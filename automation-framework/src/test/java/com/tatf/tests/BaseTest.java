package com.tatf.tests;

import com.tatf.alert.data.AlertData;
import com.tatf.alert.task.AlertTask;
import com.tatf.config.TestConfig;
import com.tatf.core.browser.BrowserFactory;
import com.tatf.core.browser.IBrowser;
import com.tatf.core.verification.IVerify;
import com.tatf.home.task.HomeTask;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

/**
 * Base de los tests de AdminCes: abre el navegador, accede a la aplicación y lo cierra al terminar.
 */
public abstract class BaseTest {

    protected IBrowser browser;
    protected IVerify verify;

    protected HomeTask homeTask;
    protected AlertTask alertTask;

    @BeforeEach
    void openAdminces() {
        browser = BrowserFactory.getBrowser(TestConfig.debugging());
        verify = IVerify.create();
        homeTask = new HomeTask(browser);
        alertTask = new AlertTask(browser);

        homeTask.accessAdminces();
    }

    @AfterEach
    void quitBrowser() {
        BrowserFactory.quitBrowser();
    }

    protected void verifySuccessAlert(String errorMessage) {
        verify.verify(AlertData.SUCCESS_TITLE, alertTask.getTitle(), errorMessage);
        alertTask.close();
    }
}
