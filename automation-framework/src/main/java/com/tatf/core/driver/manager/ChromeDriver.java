package com.tatf.core.driver.manager;

import org.openqa.selenium.chrome.ChromeOptions;

public class ChromeDriver extends DriverManager {
    /**
     * Crea el driver de Chrome con las opciones por defecto.
     */
    public ChromeDriver() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("start-maximized");
        chromeOptions.addArguments("--ignore-certificate-errors");
        chromeOptions.addArguments("--guest");
        // un pop up de chrome de "Cambia tu contraseña" me hacia fallar el test ya que parece que bloqueaba los clicks con el sitio
        // buscando en google, encotre que con la option --guest podia evitar mensajes sobre las contraseñas
        // https://stackoverflow.com/questions/77230830/how-to-disable-password-manager-in-chrome-webdriver


        this.driver = new org.openqa.selenium.chrome.ChromeDriver(chromeOptions);
        setDefaultConfig();
    }
}
