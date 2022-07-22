package driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;
import java.time.Duration;

public class Driver {

    protected WebDriver driver;
    protected WebDriverWait wait;
    protected final int WAIT_TIMEOUT_SECONDS = 10;

    @BeforeMethod()
    public void setUp(){
        driver = WebDriverManager.chromedriver().create();
//        switch(System.getProperty("browser")){
//            case "1024,768":{
        Dimension medium = new Dimension(1024,768);
        driver.manage().window().setSize(medium);
//
//            }
//            default:{
//        driver.manage().window().maximize();
//
//            }
//        }
//        Dimension small = new Dimension(800,600);
//        driver.manage().window().setSize(small);
        wait = new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS));
    }
}