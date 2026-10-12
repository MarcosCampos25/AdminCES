package com.tatf.core.driver.manager;

import org.openqa.selenium.edge.EdgeOptions;

public class EdgeDriver extends DriverManager {
    /**
     * Crea el driver de Edge con las opciones por defecto.
     */
    public EdgeDriver() {
        EdgeOptions edgeOptions = new EdgeOptions();
        edgeOptions.addArguments("start-maximized");
        edgeOptions.addArguments("--ignore-certificate-errors");
        // Edge es Chromium, así que uso --guest igual que en Chrome para evitar los pop ups de contraseñas
        edgeOptions.addArguments("--guest");

        this.driver = new org.openqa.selenium.edge.EdgeDriver(edgeOptions);
        setDefaultConfig();
    }
}
