package com.tatf.register.task;

import com.tatf.core.browser.IBrowser;
import com.tatf.home.pom.HomePO;
import com.tatf.register.pom.RegisterPO;

public class RegisterTask {
    private final HomePO homePO;
    private final RegisterPO registerPO;

    public RegisterTask(IBrowser browser) {
        this.homePO = new HomePO(browser);
        this.registerPO = new RegisterPO(browser);
    }

    public void registerAdministrator(String firstName, String lastName, String email, String password,
                                      String repeatPassword, String country) {
        homePO.clickRegisterLink();
        registerPO.waitForm();
        registerPO.writeFirstName(firstName);
        registerPO.writeLastName(lastName);
        registerPO.writeEmail(email);
        registerPO.writePassword(password);
        registerPO.writeRepeatPassword(repeatPassword);
        registerPO.writeCountry(country);
        registerPO.clickRegister();
    }
}
