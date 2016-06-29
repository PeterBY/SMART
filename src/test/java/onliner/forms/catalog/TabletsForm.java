package onliner.forms.catalog;

import org.openqa.selenium.By;
import webdriver.elements.ComboBox;

/**
 * Страница каталога, раздел Планшеты.
 */
public class TabletsForm extends ItemsForm {
	private ComboBox cmbSizeMin = new ComboBox(By.xpath("(//div[span[contains(text(),'Диагональ')]]/following-sibling::div//select)[1]"), "Size Min");
	private ComboBox cmbSizeMax = new ComboBox(By.xpath("(//div[span[contains(text(),'Диагональ')]]/following-sibling::div//select)[2]"), "Size Max");

	public TabletsForm() {
		super(By.xpath("//div[@class='schema-header']/h1[text()='Планшеты']"), "Каталог Планшетов");
	}

	public void searchItemsByScreenSize(double sizeMin, double sizeMax) {
		logger.info("Поиск по диагонали экрана");
		if (!(sizeMin == 0))
			cmbSizeMin.selectByValue(String.format("%.0f", sizeMin * 10));
		if (!(sizeMax == 0))
			cmbSizeMax.selectByValue(String.format("%.0f", sizeMax * 10));
	}

}
