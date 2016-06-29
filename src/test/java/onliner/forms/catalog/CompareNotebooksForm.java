package onliner.forms.catalog;

import org.openqa.selenium.By;
import webdriver.elements.*;

import java.util.List;

/**
 * Страница сравнения Ноутбуков.
 */
public class CompareNotebooksForm extends CompareForm {
	private final String locatorBrandNameItem = "//span[@class='product-summary__caption']";

	public void assertSearchResultByBrandName(String brandName) {
		logger.info("Проверка результата поиска");

		List<BaseElement> searchResult = new Label(By.xpath(locatorBrandNameItem)).getAllElements();
		for (BaseElement element : searchResult) {
			String textElement = ((Label) element).getText();
			doAssert(textElement.startsWith(brandName), textElement + ": Ok!", textElement + ": Error!");
		}
	}

}
