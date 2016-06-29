package onliner.forms;

import org.openqa.selenium.By;
import webdriver.BaseForm;
import webdriver.elements.Label;
import webdriver.elements.Link;
import webdriver.elements.TextBox;

/**
 * Абстрактный класс, описывает базовую страницу Onliner.by
 */
public abstract class OnlinerForm extends BaseForm {
	private final String locatorTopMenu = "//a[@class='b-main-navigation__link']/span[text()='%s']";
	private final Link lnkEnter = new Link(By.xpath("//div[@class='auth-bar__item auth-bar__item--text']"), "Ссылка для входа в аккаунт");
	private final Label lblTopProfile = new Label(By.className("b-top-profile__user-account"), "Блок отображения профиля");
	private final Link lnkProfile = new Link(By.xpath("//p[@class='user-name']/a"), "Ссылка на профиль");
	private final Link lnkExit = new Link(By.xpath("//a[@class='exit']"), "Ссылка выхода из аккаунта");
	private final TextBox txbSearchItem = new TextBox(By.xpath("//input[@class='fast-search__input']"), "Строка поиска");
	private final Label lblSearchFrame = new Label(By.className("modal-iframe"), "Форма поиска");
	private final Link lnkCart = new Link(By.className("b-top-navigation-cart__link"), "Корзина");

	protected OnlinerForm(By locator, String formTitle) {
		super(locator, formTitle);
	}

	/**
	 * User authorization
	 * @param login
	 * @param password
	 */
	public void login(String login, String password) {
		logger.info("Логин пользователя");
		if (lblTopProfile.isPresent())
			logger.error("Пользователь уже вошёл");
		else {
			lnkEnter.clickAndWait();
			LoginForm loginForm = new LoginForm();
			loginForm.txbLogin.setText(login);
			loginForm.txbPass.setText(password);
			loginForm.btnEnter.clickAndWait();
		}
	}

	/**
	 * Navigate to Profile
	 */
	public void goToProfile(){
		logger.info("Переход в профиль");
		lblTopProfile.waitForIsElementPresent();
		lnkProfile.clickAndWait();
	}

	/**
	 * Navigate top menu
	 * @param menuItem
	 */
	public void navigateTopMenu(String menuItem) {
		logger.info("Переход из главного меню в : " + menuItem);
		String menuItemLocator = String.format(locatorTopMenu, menuItem);
		Link menuLink = new Link(By.xpath(menuItemLocator), "Ссылка на " + menuItem);
		menuLink.clickAndWait();
	}

	/**
	 * Logout user
	 */
	public void logout() {
		logger.info("Выход из аккаунта");
		lnkExit.clickAndWait();
	}

	/**
	 * Search item on catalog by name
	 * @param nameItem
	 */
	public void searchItem(String nameItem) {
		logger.info("Поиск товара");
		txbSearchItem.setText(nameItem);
		lblSearchFrame.switchToFrame();
	}

	/**
	 * Navigate to Cart
	 */
	public void goToCart() {
		logger.info("Переход в корзину");
		lnkCart.clickAndWait();
	}
}
