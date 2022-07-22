package test;

import driver.Driver;
import org.testng.annotations.Test;
import page.EconomicCalendarPage;
import page.MainPage;
import page.NotificationPage;
import page.RiskDisclosuresPage;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class RiskDisclosureTest extends Driver {

    @Test
    public void openRiskDisclousureDocument() {

        DateTimeFormatter formater = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        ZoneId zid = ZoneId.of("Etc/GMT-1");

        String TODAY = LocalDate.now(zid).format(formater);
        String YESTERDAY = LocalDate.now(zid).minusDays(1).format(formater);
        String TOMORROW = LocalDate.now(zid).plusDays(1).format(formater);

        LocalDate sunday = LocalDate.now(zid);
        while (sunday.getDayOfWeek() != DayOfWeek.SUNDAY)
            {
                sunday = sunday.minusDays(1);
            }
        LocalDate monday = sunday.plusDays(6);

        String SUNDAY = sunday.format(formater);
        String MONDAY = monday.format(formater);

        EconomicCalendarPage economicCalendarPage = new MainPage(driver, wait, DIMENSION)
                .openPage()
                .invokeEconomicCalendarPage()
                .switchToEconomicCalendarFrame();

        economicCalendarPage.clickFilterYesterday();
        economicCalendarPage.checkDateRange(YESTERDAY + " - " + YESTERDAY);

        economicCalendarPage.clickFilterToday();
        economicCalendarPage.checkDateRange(TODAY + " - " + TODAY);

        economicCalendarPage.clickFilterTomorrow();
        economicCalendarPage.checkDateRange(TOMORROW + " - " + TOMORROW);

        economicCalendarPage.clickFilterThisWeek();
        economicCalendarPage.checkDateRange(SUNDAY + " - " + MONDAY);
        System.out.println(SUNDAY + " - " + MONDAY);

        economicCalendarPage.switchToDefault();
        NotificationPage notificationPage = economicCalendarPage.invokeNotificationPage();
        RiskDisclosuresPage riskDisclosuresPage = notificationPage.invokeRiskWarningDoc();
        riskDisclosuresPage.setActive();
        assertThat(driver.getCurrentUrl(), equalTo("https://www.xm.com/assets/pdf/new/docs/XMGlobal-Risk-Disclosures-for-Financial-Instruments.pdf?v12"));
    }
}
