package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RiskDisclosuresPage extends AbstractPage{

    private final String RiskDisclosures_URL = "https://www.xm.com/assets/pdf/new/docs/XMGlobal-Risk-Disclosures-for-Financial-Instruments.pdf?v12";

    protected RiskDisclosuresPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
        PageFactory.initElements(this.driver, this);
    }

    public void setActive() {
        driver.switchTo().window(driver.getWindowHandles().toArray()[1].toString());
        System.out.println(driver.getCurrentUrl());
    }
}
