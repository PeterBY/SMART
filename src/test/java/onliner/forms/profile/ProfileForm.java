package onliner.forms.profile;

import onliner.forms.OnlinerForm;
import org.openqa.selenium.By;
import webdriver.elements.*;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * Profile Form
 */
public class ProfileForm extends OnlinerForm {
	private final String locatorProfileMenu = "//div[@class='uprofile-col-i']//a[text()='%s']";
	private final String locatorProfilePrivateDataTabs = "//ul[@class='b-utabs']//a[text()='%s']";
	private final Label lblBirthDay = new Label(By.xpath("//dt[contains(text(),'День рождения')]/following-sibling::dd"), "День рождения");

	public ProfileForm() {
		super(By.xpath("//div[@class='b-uprofile']"), "Страница Профиля");
	}

	public ProfileForm(By locator, String formTitle) {
		super(locator, formTitle);
	}

	public void navigateProfileMenu(String menuItem) {
		logger.info("Навигация по меню профиля: " + menuItem);
		String menuItemLocator = String.format(locatorProfileMenu, menuItem);
		Link menuLink = new Link(By.xpath(menuItemLocator), "menu link " + menuItem);
		menuLink.clickAndWait();
	}

	public void navigateProfilePrivateDataTabs(String menuItem) {
		logger.info("Навигация по меню личных данных: " + menuItem);
		String menuItemLocator = String.format(locatorProfilePrivateDataTabs, menuItem);
		Link menuLink = new Link(By.xpath(menuItemLocator), "menu link " + menuItem);
		menuLink.clickAndWait();
	}

	public void chekBirthDayProperties(int day, int month, int year) {
		logger.info("Проверка даты рождения");
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd MMMM yyyy");
		Calendar calendar = Calendar.getInstance(new Locale("ru", "RU"));
		calendar.set(year, month - 1, day);
		logger.info(lblBirthDay.getText());
		logger.debug(simpleDateFormat.format(calendar.getTime()));
		doAssert(lblBirthDay.getText().contains(simpleDateFormat.format(calendar.getTime())), "Дата совпадает!", "Дата не совпадает!");
	}
}
