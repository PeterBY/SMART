package onliner.forms.catalog;

import org.openqa.selenium.By;
import webdriver.elements.*;

/**
 * Страница каталога, раздел Ноутбуки.
 */
public class NotebooksForm extends ItemsForm {
	private ComboBox cmbSizeMin = new ComboBox(By.xpath("(//div[span[contains(text(),'Диагональ')]]/following-sibling::div//select)[1]"), "Size Min");
	private ComboBox cmbSizeMax = new ComboBox(By.xpath("(//div[span[contains(text(),'Диагональ')]]/following-sibling::div//select)[2]"), "Size Max");

	public NotebooksForm() {
		super(By.xpath("//div[@class='schema-header']/h1[text()='Ноутбуки']"), "Каталог Ноутбуки");
	}

	/**
	 * Search items by screen size
	 * @param sizeMin
	 * @param sizeMax
	 */
	public void searchItemsByScreenSize(double sizeMin, double sizeMax) {
		logger.info("Поиск по диагонали экрана");
		if (!(sizeMin == 0))
			cmbSizeMin.selectByValue(String.format("%.0f", sizeMin * 10));
		if (!(sizeMax == 0))
			cmbSizeMax.selectByValue(String.format("%.0f", sizeMax * 10));
	}
}
