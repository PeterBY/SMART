package onliner.forms.profile;

import org.openqa.selenium.By;
import webdriver.elements.Button;
import webdriver.elements.ComboBox;

/**
 * Preferences Form
 */
public class PreferencesForm extends ProfileForm {
	private final ComboBox chbBirthDayProperties = new ComboBox(By.xpath("//select[@name='birthdayView']"), "Отображение дня рождения");
	private final Button btnSave = new Button(By.xpath("//div[@id='tab-prefs']//button[@type='submit']"), "Сохранить");

	public PreferencesForm() {
		super(By.id("tab-prefs"), "Настройки");
	}

	public void changeBirthDayProperties(String s) {
		if (s.equals("полностью"))
			chbBirthDayProperties.selectByValue("yymmdd");
		else if (s.equals("день и месяц"))
			chbBirthDayProperties.selectByValue("mmdd");
		else if (s.equals("только год"))
			chbBirthDayProperties.selectByValue("yyyy");
		else if (s.equals("не показывать"))
			chbBirthDayProperties.selectByValue("");
		else logger.error("Не верное значение");
		btnSave.click();
	}
}
