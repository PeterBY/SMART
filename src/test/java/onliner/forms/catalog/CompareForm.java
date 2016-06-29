package onliner.forms.catalog;

import onliner.forms.OnlinerForm;
import org.openqa.selenium.By;
import webdriver.elements.Label;

import java.util.ArrayList;
import java.util.List;

/**
 * Абстрактный класс, описывает страницу сравнения.
 */
public abstract class CompareForm extends OnlinerForm {
	protected final String locatorItems = "//th[@class='product-table__cell']";
	protected final String locatorFieldItems = "(//td[span[contains(text(),'%s')]]/following-sibling::td/span)[%d]";
	private Label lblFieldItem;

	protected CompareForm() {
		super(By.xpath("//div[@class='catalog-top']//h1[text()='Сравнение товаров']"), "Сравнение товаров");
	}

	/**
	 * Return list of value field by name field
	 * @param nameField
	 * @return
	 */
	public List<String> getListFieldItems(String nameField) {
		logger.info("Получение значений поля: " + nameField);
		List<String> result = new ArrayList<String>();

		int numberComparedItems = new Label(By.xpath(locatorItems)).getListWebElements(By.xpath(locatorItems)).size();

		for (int i = 0; i < numberComparedItems; i++) {
			lblFieldItem = new Label(By.xpath(String.format(locatorFieldItems, nameField, i + 2)));
			result.add(lblFieldItem.getText());
		}

		return result;
	}
}
