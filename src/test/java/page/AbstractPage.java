package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class AbstractPage {

    protected WebDriver driver;
    protected WebDriverWait wait;
    protected final int WAIT_TIMEOUT_SECONDS = 10;
    protected String DIMENSION;

    protected AbstractPage(WebDriver driver, WebDriverWait wait){
        this.driver = driver;
        this.wait = wait;
    }

    protected AbstractPage(WebDriver driver, WebDriverWait wait, String dimension){
        this.driver = driver;
        this.wait = wait;
        this.DIMENSION = dimension;
    }

}
