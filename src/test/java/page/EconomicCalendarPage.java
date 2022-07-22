package page;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class EconomicCalendarPage extends AbstractPage{

    private final String EconomicCalendar_URL = "https://www.xm.com/research/economicCalendar/";

    @FindBy(id = "timeFrame_yesterday")
    private WebElement filterYesterday;

    @FindBy(id = "timeFrame_today")
    private WebElement filterToday;

    @FindBy(id = "timeFrame_tomorrow")
    private WebElement filterTomorrow;

    @FindBy(id = "timeFrame_thisWeek")
    private WebElement filterThisWeek;

    private final By widgetFieldDateRange = By.id("widgetFieldDateRange");

    @FindBy(xpath = "//a[@href='/research/risk_warning']")
    private WebElement linkRiskWarning;

    @FindBy(xpath = "//li[@class='saveSpace']")
    private WebElement saveSpace;

    protected EconomicCalendarPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
        PageFactory.initElements(this.driver, this);
    }

    private void expandFilterMenu(){
        try {
            this.saveSpace.click();
            System.out.println("Filter menu was expanded.");
        } catch (Exception e){
            System.out.println("There is no Filter menu.");
        }
    }

    public void clickFilterYesterday() {
        this.expandFilterMenu();
        this.filterYesterday.click();
        wait.until(ExpectedConditions.attributeContains(filterYesterday, "class", "toggled"));
    }

    public void clickFilterToday() {
        this.expandFilterMenu();
        this.filterToday.click();
        wait.until(ExpectedConditions.attributeContains(filterToday, "class", "toggled"));
    }

    public void clickFilterTomorrow() {
        this.expandFilterMenu();
        this.filterTomorrow.click();
        wait.until(ExpectedConditions.attributeContains(filterTomorrow, "class", "toggled"));
    }

    public void clickFilterThisWeek() {
        this.expandFilterMenu();
        this.filterThisWeek.click();
        wait.until(ExpectedConditions.attributeContains(filterThisWeek, "class", "toggled"));
    }

    public EconomicCalendarPage switchToEconomicCalendarFrame(){
        driver.switchTo().frame(driver.findElement(By.xpath(".//iframe[@title='economicCalendar']")));
        wait = new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS));
        return this;
    }

    public EconomicCalendarPage switchToDefault(){
        driver.switchTo().defaultContent();
        wait = new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS));
        return this;
    }

    public void checkDateRange(String dateRange){
        wait.until(ExpectedConditions.textToBe(widgetFieldDateRange, dateRange));
    }

    public NotificationPage invokeNotificationPage() {
        JavascriptExecutor js = ((JavascriptExecutor) driver);
        js.executeScript("arguments[0].scrollIntoView();", linkRiskWarning);

        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//i[@class='fa fa-times']"))).click();
            System.out.println("Top spoiler was closed.");
        } catch (Exception e){
            System.out.println("There is no top spoiler.");
        }

        wait.until(ExpectedConditions.elementToBeClickable(linkRiskWarning)).click();
        System.out.println(driver.getCurrentUrl());
        return new NotificationPage(driver, wait);
    }
}
