package page;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPage extends AbstractPage{

    private final String BASE_URL = "https://xm.com/";

    @FindBy(xpath = "//li[@class='main_nav_research']")
    private WebElement linkResearchAndEducation;

    @FindBy(xpath = "//a[contains(text(), 'Economic Calendar')]")
    private WebElement linkEconomicCalendar;

    @FindBy(xpath = "//i[@class='fa fa-bars']")
    private WebElement linkMenu;

    private final By mainTitle = By.xpath("//span[@class='title']");

    public MainPage(WebDriver driver, WebDriverWait wait){
        super(driver, wait);
        PageFactory.initElements(this.driver, this);
    }

    public MainPage openPage(){
        driver.navigate().to(BASE_URL);

        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[text()='ACCEPT ALL']"))).click();
            System.out.println("Button 'ACCEPT ALL' was pressed.");
        } catch (Exception e){
            System.out.println("Exception during clicking 'ACCEPT ALL' button.");
        }
        wait.until(ExpectedConditions.presenceOfElementLocated(mainTitle));

        return this;
    }

    public EconomicCalendarPage invokeEconomicCalendarPage(){
        try{
            linkMenu.click();
            System.out.println("Menu button was pressed.");
        } catch (Exception e){
            System.out.println("There is no Menu button.");
        }

        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//i[@class='fa fa-times']"))).click();

        JavascriptExecutor js = ((JavascriptExecutor) driver);
        js.executeScript("arguments[0].scrollIntoView();", linkResearchAndEducation);
        wait.until(ExpectedConditions.elementToBeClickable(linkResearchAndEducation));
        linkResearchAndEducation.click();

        js.executeScript("arguments[0].scrollIntoView();", linkEconomicCalendar);
        linkEconomicCalendar.click();

        System.out.println(driver.getCurrentUrl());
        return new EconomicCalendarPage(driver, wait);
    }

}
