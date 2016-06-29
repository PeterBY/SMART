package onliner.tests;

import onliner.forms.*;
import onliner.forms.profile.ProfileForm;
import onliner.forms.profile.PreferencesForm;
import onliner.forms.profile.PrivateDataForm;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import webdriver.BaseTest;

/**
 * Изменение настроек профиля.
 */
public class ProfilePreferencesTest extends BaseTest {

	private String login;
	private String password;
	private int day, month, year;

	@BeforeTest
	@Parameters({"login", "password", "day", "month", "year"})
	public void readParams(String login, String password, int day, int month, int year) {
		this.login = login;
		this.password = password;
		this.day = day;
		this.month = month;
		this.year = year;
	}

	public void runTest() {
		logger.step(0);
		MainForm mainForm = new MainForm();

		logger.step(1);
		mainForm.login(login, password);

		logger.step(2);
		mainForm.goToProfile();
		ProfileForm profileForm = new ProfileForm();

		logger.step(3);
		profileForm.navigateProfileMenu("Редактировать личные данные");

		logger.step(4);
		PrivateDataForm privateDataForm = new PrivateDataForm();
		privateDataForm.changeBirthDay(day, month, year);

		logger.step(5);
		privateDataForm.navigateProfilePrivateDataTabs("Настройки");
		PreferencesForm preferencesForm = new PreferencesForm();
		preferencesForm.changeBirthDayProperties("полностью");

		logger.step(6);
		preferencesForm.goToProfile();
		profileForm.chekBirthDayProperties(day, month, year);

		logger.step(7);
		profileForm.logout();
	}

}
