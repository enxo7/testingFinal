package DigitalBooking;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import reportes.ReportFactory;

import java.time.Duration;

import static reportes.ReportFactory.captureScreenshot;

public class testAbrirCuenta {


    private WebDriver driver;
    private WebDriverWait wait;

    static ExtentSparkReporter info = new ExtentSparkReporter("reportes/AbrirCuenta-Test.html");
    static ExtentReports extent;

    @BeforeAll
    public static void createReport() {
        extent = ReportFactory.getInstance();
        extent.attachReporter(info);
        System.out.println("<<< COMIENZAN LOS TEST DE ABRIR NUEVA CUENTA >>>");
    }

    @BeforeEach
    public void setUp() throws InterruptedException {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofMillis(5000));
        LoginPage loginPage = new LoginPage(driver, wait);
        loginPage.setup();
        loginPage.getUrl("https://parabank.parasoft.com/parabank/index.html");
        loginPage.completarCampos();
        loginPage.clickIngresar();
    }

    @Test
    @Tag("APERTURA_CUENTA")
    @Tag("EXITOSA")
    public void aperturaCuentaExitosa() throws InterruptedException {
        ExtentTest test = extent.createTest("Apertura de Cuenta");
        AbrirCuentaPage abrirCuentaPage = new AbrirCuentaPage(driver, wait);

        try {
            Thread.sleep(2000);
            abrirCuentaPage.clickAbrirCuenta();
            abrirCuentaPage.clickDesplegarMenuOpcionesButton();
            abrirCuentaPage.clickElegirSavingsButton();
            abrirCuentaPage.clickCrearCuentaButton();
            Thread.sleep(3000);
            if (abrirCuentaPage.obtenerTextoCreacion().equals("Congratulations, your account is now open.")) {
                test.log(Status.PASS, "La cuenta se abrio con exito!");
            } else {
                test.log(Status.FAIL, "FALLO AL ABRIR LA CUENTA");
            }
        } catch (Exception e) {
            test.log(Status.FAIL, "FALLO EL TEST" + e.getMessage());
            captureScreenshot(test, "FAIL_Abrir_Cuenta", driver);
        }
    }

    @AfterEach
    public void cerrar() {
        LoginPage loginPage = new LoginPage(driver, wait);
        loginPage.close();
    }

    @AfterAll
    public static void saveReport() {
        System.out.println("<<< FINALIZAN LOS TEST DE ABRIR NUEVA CUENTA >>>");
    }
}
