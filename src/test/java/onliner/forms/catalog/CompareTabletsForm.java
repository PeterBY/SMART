package onliner.forms.catalog;

import org.openqa.selenium.By;
import webdriver.elements.Label;

/**
 * Страница сравнения Планшетов.
 */
public class CompareTabletsForm extends CompareForm {
//	private final String locatorItems= "//th[@class='product-table__cell']";

	/**
	 * Assert search by number items
	 * @param numberFirstItems
	 */
	public void assertResultByNumberItems(int numberFirstItems) {
		logger.info("Проверка результата поиска");
		int numberComparedItems = new Label(By.xpath(locatorItems)).getListWebElements(By.xpath(locatorItems)).size();
		doAssert(numberComparedItems == numberFirstItems,
				String.format("Количество товаров совпадает: %d", numberComparedItems),
				String.format("Ошибка! Ожидалось %1d товаров, но на странице представлено %2d", numberFirstItems, numberComparedItems));
	}
}