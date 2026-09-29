package com.tatf.viewusers.task;

import com.tatf.alert.task.AlertTask;
import com.tatf.core.browser.IBrowser;
import com.tatf.core.verification.IVerify;
import com.tatf.viewusers.data.ViewUsersData;
import com.tatf.viewusers.pom.ViewUsersPO;
import org.openqa.selenium.TimeoutException;

public class ViewUsersTask {
    private final ViewUsersPO viewUsersPO;
    private final AlertTask alertTask;

    public ViewUsersTask(IBrowser browser) {
        this.viewUsersPO = new ViewUsersPO(browser);
        this.alertTask = new AlertTask(browser);
    }

    public void verifyTester(String email, String firstName, String lastName, String country, String level) {
        viewUsersPO.waitTable();
        IVerify verify = IVerify.create();
        verify.verify(firstName, viewUsersPO.getCellText(email, ViewUsersData.COLUMN_FIRST_NAME), "El nombre del Tester no coincide en la tabla.");
        verify.verify(lastName, viewUsersPO.getCellText(email, ViewUsersData.COLUMN_LAST_NAME), "El apellido del Tester no coincide en la tabla.");
        verify.verify(country, viewUsersPO.getCellText(email, ViewUsersData.COLUMN_COUNTRY), "El pais del Tester no coincide en la tabla.");
        verify.verify(level, viewUsersPO.getCellText(email, ViewUsersData.COLUMN_LEVEL), "El perfil del Tester no coincide en la tabla.");
    }

    public void deleteTester(String email) {
        viewUsersPO.clickDelete(email);
        alertTask.confirm();
    }

    public void verifyTesterNotVisible(String email) {
        try {
            viewUsersPO.waitUserInvisible(email);
        } catch (TimeoutException e) {
            IVerify.create().verifyFail("El tester " + email + " seguía visible en la tabla tras el borrado.");
        }
    }
}
