package onliner.forms;

import org.openqa.selenium.By;
import webdriver.BaseForm;
import webdriver.elements.BaseElement;
import webdriver.elements.Button;
import webdriver.elements.Label;

import java.util.List;

/**
 * Cart Form
 */
public class CartForm extends BaseForm {
	private final Label lblNameItem = new Label(By.className("cart-product-title"), "Название продукта");
	private final Button btnDeleteItem = new Button(By.xpath("//a[@class='cart-product__remove']"), "Удалить продукт из корзины");

	public CartForm() {
		super(By.className("cart-content"), "Корзина");
	}

	/**
	 * Assert item in Cart by name
	 * @param nameItem
	 */
	public void assertNameItem(String nameItem) {
		logger.info("Проверка содержимого корзины");
		doAssert(lblNameItem.getText().contains(nameItem), "Ok: название товара соответвует поисковому", "Error: название товара не соответвует поисковому");
	}

	/**
	 * Clear Cart
	 */
	public void clear() {
		logger.info("Очистка корзины");
		List<BaseElement> list = btnDeleteItem.getAllElements();
		for (BaseElement el: list) {
			((Button) el).clickAndWait();
		}
	}
}
