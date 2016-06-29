package onliner.tests;

import onliner.forms.catalog.CatalogForm;
import onliner.forms.catalog.CompareTabletsForm;
import onliner.forms.catalog.TabletsForm;
import onliner.forms.MainForm;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import webdriver.BaseTest;

/**
 * Фильтр и сравнение в разделе Планшеты.
 */
public class FilterAndCompareTablets extends BaseTest {
	private String brandName, sortType;
	private double sizeMin, sizeMax;
	private int numberFirstItems;

	@BeforeTest
	@Parameters({"brandName", "sortType", "sizeMin", "sizeMax", "numberFirstItems"})
	public void readParams(String brandName, String sortType, double sizeMin, double sizeMax, int numberFirstItems) {
		this.brandName = brandName;
		this.sortType = sortType;
		this.sizeMin = sizeMin;
		this.sizeMax = sizeMax;
		this.numberFirstItems = numberFirstItems;
	}

	public void runTest() {
		logger.step(0);
		MainForm mainForm = new MainForm();
		mainForm.navigateTopMenu("Каталог");
		CatalogForm catalogForm = new CatalogForm();

		logger.step(1);
		catalogForm.navigateCatalogMenu("Планшеты");
		TabletsForm tabletsForm = new TabletsForm();

		logger.step(2);
		tabletsForm.searchItemsByScreenSize(sizeMin, sizeMax);
		tabletsForm.searchItemsByBrandName(brandName);

		logger.step(3);
		tabletsForm.sortBy(sortType);

		logger.step(4);
		tabletsForm.selectFirstItemsOnPage(numberFirstItems);

		logger.step(5);
		tabletsForm.compareSelectedItems();
		CompareTabletsForm compareTabletsForm = new CompareTabletsForm();

		logger.step(6);
		compareTabletsForm.assertResultByNumberItems(numberFirstItems);
	}
}
