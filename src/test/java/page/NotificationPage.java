package page;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class NotificationPage extends AbstractPage{

    private final String Notification_URL = "https://www.xm.com/research/risk_warning";

    @FindBy(xpath = "//a[@href='/assets/pdf/new/docs/XMGlobal-Risk-Disclosures-for-Financial-Instruments.pdf?v12']")
    private WebElement linkRiskWarning;

    protected NotificationPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
        PageFactory.initElements(this.driver, this);
    }

    public RiskDisclosuresPage invokeRiskWarningDoc() {
        JavascriptExecutor js = ((JavascriptExecutor) driver);
        js.executeScript("arguments[0].scrollIntoView();", linkRiskWarning);
        wait.until(ExpectedConditions.elementToBeClickable(linkRiskWarning)).click();
        System.out.println(driver.getCurrentUrl());
        return new RiskDisclosuresPage(driver, wait);
    }

}
