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

    @FindBy(xpath = "//a[@href='#researchMenu']")
    private WebElement linkResearchAndEducationSmall;

    @FindBy(xpath = "//a[contains(text(), 'Economic Calendar')]")
    private WebElement linkEconomicCalendar;

    @FindBy(xpath = "//*[@id=\"researchMenu\"]/ul/li[7]/a")
    private WebElement linkEconomicCalendarSmall;

    @FindBy(xpath = "//i[@class='fa fa-bars']")
    private WebElement linkMenu;

    private final By mainTitle = By.xpath("//span[@class='title']");

    public MainPage(WebDriver driver, WebDriverWait wait, String dimension){
        super(driver, wait, dimension);
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

    public EconomicCalendarPage invokeEconomicCalendarPage() {

        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//i[@class='fa fa-times']"))).click();

        switch(DIMENSION){
            case "small":{
                linkMenu.click();
                System.out.println("Menu button was pressed.");

                JavascriptExecutor js = ((JavascriptExecutor) driver);
                js.executeScript("arguments[0].scrollIntoView();", linkResearchAndEducationSmall);
                wait.until(ExpectedConditions.elementToBeClickable(linkResearchAndEducationSmall));
                linkResearchAndEducationSmall.click();

                js.executeScript("arguments[0].scrollIntoView();", linkEconomicCalendarSmall);
                wait.until(ExpectedConditions.elementToBeClickable(linkEconomicCalendarSmall));
                linkEconomicCalendarSmall.click();
                break;
            }
            default:{
                System.out.println("There is no Menu button.");

                JavascriptExecutor js = ((JavascriptExecutor) driver);
                js.executeScript("arguments[0].scrollIntoView();", linkResearchAndEducation);
                wait.until(ExpectedConditions.elementToBeClickable(linkResearchAndEducation));
                linkResearchAndEducation.click();

                js.executeScript("arguments[0].scrollIntoView();", linkEconomicCalendar);
                linkEconomicCalendar.click();
            }
        }

        System.out.println(driver.getCurrentUrl());
        return new EconomicCalendarPage(driver, wait);
    }

}
