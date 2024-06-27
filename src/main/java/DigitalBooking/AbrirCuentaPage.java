package DigitalBooking;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AbrirCuentaPage extends BasePage{

    protected AbrirCuentaPage(WebDriver driver, WebDriverWait wait) {
        super(driver, null);
    }

    private By abrirCuentaButton =By.xpath("//*[@id=\"leftPanel\"]/ul/li[1]/a");
    private By desplegarMenuOpcionesButton = By.xpath("//*[@id=\"type\"]");
    private By elegirSavingsButton = By.xpath("//*[@id=\"type\"]/option[2]");
    private By crearCuentaButton = By.xpath("//*[@id=\"openAccountForm\"]/form/div/input");
    private By verificarTextoCreacion = By.xpath("//*[@id=\"openAccountResult\"]/p[1]");

    public void clickAbrirCuenta() throws InterruptedException {
        this.click(abrirCuentaButton);
    }

    public void clickDesplegarMenuOpcionesButton() throws InterruptedException {
        this.click(desplegarMenuOpcionesButton);
    }

    public void clickElegirSavingsButton() throws InterruptedException {
        this.click(elegirSavingsButton);
    }

    public void clickCrearCuentaButton() throws InterruptedException {
        this.click(crearCuentaButton);
    }

    public String obtenerTextoCreacion() throws InterruptedException {
        String res = this.getText(verificarTextoCreacion);
        System.out.println("Resultado:" + res);
        return res;
    }
}
