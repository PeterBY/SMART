package onliner.forms;

import org.openqa.selenium.By;
import webdriver.*;
import webdriver.elements.Button;

/**
 * Search Form
 */
public class SearchForm extends BaseForm {
	private Button btnFirstItemOffers = new Button(By.xpath("//a[@class='button button_orange product__button']"), "Fist Item Offers");
	private Button btnCloseSearchForm = new Button(By.xpath("//span[@class='search__close']"));

	public SearchForm() {
		super(By.id("search-page"), "Форма поиска");
	}

	public void goToFirstItemOffers() {
		logger.info("Переход к предложениям для первого товара в списке");
		btnFirstItemOffers.clickAndWait();
	}

	public void close() {
		logger.info("Закрытие поисковой формы");
		btnCloseSearchForm.clickAndWait();
		btnCloseSearchForm.switchToDefault();
	}
}
