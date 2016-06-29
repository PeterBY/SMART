package onliner.forms;

import org.openqa.selenium.By;
import webdriver.BaseForm;
import webdriver.elements.Button;
import webdriver.elements.TextBox;

/**
 * Login Form
 */
public class LoginForm extends BaseForm {
	protected final TextBox txbLogin = new TextBox(By.xpath("//input[@placeholder='Ник или e-mail']"), "Поле ввода логина");
	protected final TextBox txbPass = new TextBox(By.xpath("//input[@placeholder='Пароль']"), "Поле ввода пароля");
	protected final Button btnEnter = new Button(By.xpath("//button[@class='auth-box__auth-submit auth__btn auth__btn--green']"), "Кнопка входа");

	protected LoginForm() {
		super(By.id("auth-container__forms"), "Страница входа");
	}
}
