package onliner.forms.catalog;

import org.openqa.selenium.By;
import webdriver.elements.Button;

/**
 * Страница предложений товара.
 */
public class OffersForm extends CatalogForm {
	private final Button btnAddToCart = new Button(By.xpath("//a[@class='button button_orange button_middle offers-list__button offers-list__button_basket']"), "Add to Cart");

	public OffersForm() {
		super(By.className("b-offers"), "Страница предложений для товара");
	}

	/**
	 * Adding to cart first offer of item
	 */
	public void addToCartFirstOffer() {
		logger.info("Добавление в корзину первого в списке предложения");
		btnAddToCart.clickAndWait();
	}
}
