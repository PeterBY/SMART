package onliner.forms.catalog;

import onliner.forms.OnlinerForm;
import org.openqa.selenium.By;
import webdriver.elements.Link;

/**
 * Главная страница каталога.
 */
public class CatalogForm extends OnlinerForm {
	final String locatorCatalogMenuItem = "//div[@class='catalog-bar']//a[text()='%s']";

	public CatalogForm() {
		super(By.className("catalog-bar-main"), "Главная каталога");
	}

	protected CatalogForm(By locator, String formTitle) {
		super(locator, formTitle);
	}

	public void navigateCatalogMenu(String menuItem) {
		logger.info("Переход в раздел: " + menuItem);
		Link linkCatalogMenuItem = new Link(By.xpath(String.format(locatorCatalogMenuItem, menuItem)), "Меню каталога " + menuItem);
		linkCatalogMenuItem.clickAndWait();
	}
}
