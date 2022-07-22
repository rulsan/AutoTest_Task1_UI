package driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.time.Duration;

public class Driver {

    protected WebDriver driver;
    protected WebDriverWait wait;
    protected final int WAIT_TIMEOUT_SECONDS = 10;
    protected String DIMENSION = "";

    @Parameters({"dimension"})
    @BeforeMethod()
    public void setUp(@Optional("") String dimension){
        this.DIMENSION = dimension;
        System.out.println(dimension);
        driver = WebDriverManager.chromedriver().create();
        switch (DIMENSION) {
            case "medium":{
                Dimension medium = new Dimension(1024,768);
                driver.manage().window().setSize(medium);
                break;
            }
            case "small":{
                Dimension small = new Dimension(800,600);
                driver.manage().window().setSize(small);
                break;
            }
            default:{
                driver.manage().window().maximize();
            }
        }

        wait = new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS));
    }

    @AfterMethod()
    public void tearDown(){
        driver.quit();
        driver = null;
    }

}