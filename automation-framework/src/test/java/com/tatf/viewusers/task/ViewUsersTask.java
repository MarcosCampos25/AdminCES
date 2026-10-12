package com.tatf.viewusers.task;

import com.tatf.alert.task.AlertTask;
import com.tatf.core.browser.IBrowser;
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

    public String getFirstName(String email) {
        return getCellText(email, ViewUsersData.COLUMN_FIRST_NAME);
    }

    public String getLastName(String email) {
        return getCellText(email, ViewUsersData.COLUMN_LAST_NAME);
    }

    public String getCountry(String email) {
        return getCellText(email, ViewUsersData.COLUMN_COUNTRY);
    }

    public String getLevel(String email) {
        return getCellText(email, ViewUsersData.COLUMN_LEVEL);
    }

    public void deleteTester(String email) {
        viewUsersPO.clickDelete(email);
        alertTask.confirm();
    }

    public boolean isTesterRemoved(String email) {
        try {
            viewUsersPO.waitUserInvisible(email);
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    private String getCellText(String email, int column) {
        viewUsersPO.waitTable();
        return viewUsersPO.getCellText(email, column);
    }
}
