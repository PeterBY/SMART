package onliner.forms.profile;

import org.openqa.selenium.By;
import webdriver.elements.Button;
import webdriver.elements.ComboBox;

/**
 * Private Data Form
 */
public class PrivateDataForm extends ProfileForm {
	private final ComboBox chbUserBirthDay = new ComboBox(By.xpath("//select[@name='user_bday']"), "День рождения");
	private final ComboBox chbUserBirthMonth = new ComboBox(By.xpath("//select[@name='user_bmonth']"), "Месяц рождения");
	private final ComboBox chbUserBirthYear = new ComboBox(By.xpath("//select[@name='user_byear']"), "Год рождения");
	private final Button btnSave = new Button(By.xpath("//div[@id='tab-personal']//button[@type='submit']"), "Сохранить");

	public PrivateDataForm() {
		super(By.id("tab-personal"), "Личные данные");
	}

	public void changeBirthDay(int day, int month, int year) {
		chbUserBirthDay.selectByValue(String.valueOf(day));
		chbUserBirthMonth.selectByValue(String.valueOf(month));
		chbUserBirthYear.selectByValue(String.valueOf(year));
		btnSave.click();
	}
}
