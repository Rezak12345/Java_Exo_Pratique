package tests;

import Model.Client;
import utils.ReadData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.*;

public class StepCreationTest {

    private WebDriver driver;
    private ReadData readData;

    private final String URL = "https://sendform.nicepage.io/?version=13efcba7-1a49-45a5-9967-c2da8ebdd189&uid=f7bd60f0-34c8-40e3-8e2c-06cc19fcb730";

    @BeforeClass
    public void setup() {
        driver = new EdgeDriver();
        readData = new ReadData();
    }

    @Test(priority = 1)
    public void testHomme() {
        Client client = readData.readDataFromJson("Client1");
        driver.get(URL);

        driver.findElement(By.id("field-2688")).click();
        new Select(driver.findElement(By.id("select-9648"))).selectByVisibleText("France");

        remplirFormulaire(client);

        new Select(driver.findElement(By.id("select-c283")))
                .selectByVisibleText(client.getProduit());

        driver.findElement(By.id("checkbox-8214")).click();
        submit();
    }

    @Test(priority = 2)
    public void testFemme() {
        Client client = readData.readDataFromJson("Client2");
        driver.get(URL);

        driver.findElement(By.id("field-aa6c")).click();
        new Select(driver.findElement(By.id("select-9648"))).selectByVisibleText("Espagne");

        remplirFormulaire(client);

        new Select(driver.findElement(By.id("select-c283")))
                .selectByVisibleText("Voiture");

        driver.findElement(By.xpath("//*[@id='carousel_1853']/div/div/div/div/form/div[10]/label")).click();
        submit();
    }

    @Test(priority = 3)
    public void testHommeCondition() {
        Client client = readData.readDataFromJson("Client3");
        driver.get(URL);

        WebElement genre = driver.findElement(By.id("field-2688"));
        genre.click();

        Select pays = new Select(driver.findElement(By.id("select-9648")));
        pays.selectByVisibleText("France");

        remplirFormulaire(client);

        if ("man".equals(genre.getAttribute("value"))
                && "France".equals(pays.getFirstSelectedOption().getText())) {

            new Select(driver.findElement(By.id("select-c283"))).selectByVisibleText("Moto");
            driver.findElement(By.id("checkbox-8214")).click();
        } else {
            new Select(driver.findElement(By.id("select-c283"))).selectByVisibleText("Vélo");
            driver.findElement(By.id("checkbox-1848")).click();
        }

        submit();
    }

    @Test(priority = 4)
    public void testFemmeCondition() {
        Client client = readData.readDataFromJson("Client4");
        driver.get(URL);

        WebElement genre = driver.findElement(By.id("field-aa6c"));
        genre.click();

        Select pays = new Select(driver.findElement(By.id("select-9648")));
        pays.selectByVisibleText("Italie");

        remplirFormulaire(client);

        if ("women".equals(genre.getAttribute("value"))
                && "Italie".equals(pays.getFirstSelectedOption().getText())) {

            new Select(driver.findElement(By.id("select-c283"))).selectByVisibleText("Vélo");
            driver.findElement(By.id("checkbox-1848")).click();
        } else {
            new Select(driver.findElement(By.id("select-c283"))).selectByVisibleText("Moto");
            driver.findElement(By.id("checkbox-3250")).click();
        }

        submit();
    }

    private void remplirFormulaire(Client client) {
        driver.findElement(By.id("name-c6a3")).sendKeys(client.getName());
        driver.findElement(By.id("email-c6a3")).sendKeys(client.getEmail());
        driver.findElement(By.id("phone-84d9")).sendKeys(client.getPhone());
        driver.findElement(By.id("address-be2d")).sendKeys(client.getAdresse());
        driver.findElement(By.id("message-c6a3")).sendKeys(client.getMessage());
    }

    private void submit() {
        driver.findElement(By.xpath("//*[@id='carousel_1853']/div/div/div/div/form/div[12]/a")).submit();
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
