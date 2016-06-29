package onliner.tests;

import onliner.forms.MainForm;
import onliner.forms.catalog.CatalogForm;
import onliner.forms.catalog.CompareNotebooksForm;
import onliner.forms.catalog.NotebooksForm;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import webdriver.BaseTest;

/**
 * Фильтр и сортировка в разделе Ноутбуки.
 */
public class FilterAndSortingNotebooks extends BaseTest {
	private String brandName, sortType;
	private double priceMin, priceMax;


	@BeforeTest
	@Parameters({"brandName", "sortType", "priceMin", "priceMax"})
	public void readParams(String brandName, String sortType, double priceMin, double priceMax) {
		this.brandName = brandName;
		this.sortType = sortType;
		this.priceMin = priceMin;
		this.priceMax = priceMax;
	}

	public void runTest() {
		logger.step(0);
		MainForm mainForm = new MainForm();
		mainForm.navigateTopMenu("Каталог");
		CatalogForm catalogForm = new CatalogForm();

		logger.step(1);
		catalogForm.navigateCatalogMenu("Ноутбуки");
		NotebooksForm notebooksForm = new NotebooksForm();

		logger.step(2);
		notebooksForm.searchItemsByPrice(priceMin, priceMax);
		notebooksForm.searchItemsByBrandName(brandName);

		logger.step(3);
		notebooksForm.sortBy(sortType);

		logger.step(4);
		notebooksForm.selectAllItemsOnPage();
		notebooksForm.compareSelectedItems();

		logger.step(5);
		CompareNotebooksForm compareNotebooksForm = new CompareNotebooksForm();
		compareNotebooksForm.assertSearchResultByBrandName(brandName);
	}
}
