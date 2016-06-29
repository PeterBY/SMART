package onliner.tests;

import onliner.forms.MainForm;
import onliner.forms.catalog.*;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import webdriver.BaseTest;

/**
 * Фильтр и сравнение в разделе Мобильные телефоны.
 */
public class FilterAndCompareMobiles extends BaseTest {
	private double sizeMin, sizeMax;
	private double ramMin, ramMax;
	private int batteryMin, batteryMax;
	private String sortType;
	private int numberFirstItems;

	@BeforeTest
	@Parameters({"sizeMin", "sizeMax", "ramMin", "ramMax", "batteryMin", "batteryMax", "sortType", "numberFirstItems"})
	public void readParams(double sizeMin, double sizeMax, double ramMin, double ramMax, int batteryMin, int batteryMax, String sortType, int numberFirstItems) {
		this.sizeMin = sizeMin;
		this.sizeMax = sizeMax;
		this.ramMin = ramMin;
		this.ramMax = ramMax;
		this.batteryMin = batteryMin;
		this.batteryMax = batteryMax;
		this.sortType = sortType;
		this.numberFirstItems = numberFirstItems;
	}

	public void runTest() {
		logger.step(0);
		MainForm mainForm = new MainForm();
		mainForm.navigateTopMenu("Каталог");
		CatalogForm catalogForm = new CatalogForm();

		logger.step(1);
		catalogForm.navigateCatalogMenu("Мобильные телефоны");
		MobilesForm mobilesForm = new MobilesForm();

		logger.step(2);
		mobilesForm.searchItems(sizeMin, sizeMax, ramMin, ramMax, batteryMin, batteryMax);

		logger.step(3);
		mobilesForm.sortBy(sortType);

		logger.step(4);
		mobilesForm.selectFirstItemsOnPage(numberFirstItems);
		mobilesForm.compareSelectedItems();
		CompareMobilesForm compareMobilesForm = new CompareMobilesForm();

		logger.step(5);
		compareMobilesForm.assertResultCompare(sizeMin, sizeMax, ramMin, ramMax, batteryMin, batteryMax);
	}
}
