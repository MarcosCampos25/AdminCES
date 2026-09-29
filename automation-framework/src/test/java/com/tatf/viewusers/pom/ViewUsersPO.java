package com.tatf.viewusers.pom;

import com.tatf.core.browser.IBrowser;

public class ViewUsersPO {
    private static final String TABLE = "dataTable";
    // td con el mail, y me quedo con el padre (el tr que tiene toda la fila)
    private static final String ROW_BY_EMAIL = "//td[contains(text(), '%s')]/.."; // usando %s luego puedo usar string format para cambiarlo por un string https://www.w3schools.com/java/ref_string_format.asp
    private static final String CELL_BY_POSITION = "./td[%d]"; // %d para cambiar por un digito con string format https://www.w3schools.com/java/ref_string_format.asp
    private static final String DELETE_BUTTON_BY_EMAIL = "//td[contains(text(), '%s')]/../td/button";
    private static final String CELL_BY_EMAIL = "//td[contains(text(), '%s')]";

    private final IBrowser browser;

    public ViewUsersPO(IBrowser browser) {
        this.browser = browser;
    }

    public void waitTable() {
        browser.wait(TABLE).id();
    }

    public String getCellText(String email, int column) {
        return browser.find().xpath(String.format(ROW_BY_EMAIL, email))
                .xpath(String.format(CELL_BY_POSITION, column)).getText();
    }

    public void clickDelete(String email) {
        browser.find().xpath(String.format(DELETE_BUTTON_BY_EMAIL, email)).click();
    }

    public void waitUserInvisible(String email) {
        browser.wait(String.format(CELL_BY_EMAIL, email)).invisibilityXpath();
    }
}
