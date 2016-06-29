package onliner.tests;

import onliner.forms.CartForm;
import onliner.forms.MainForm;
import onliner.forms.SearchForm;
import onliner.forms.catalog.OffersForm;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import webdriver.BaseTest;

/**
 * Поиск и добавление товаров в Корзину.
 */
public class SearchAndAddToCartItem extends BaseTest {
	private String nameItem;

	@BeforeTest
	@Parameters({"nameItem"})
	public void readParams(String nameItem) {
		this.nameItem = nameItem;
	}

	public void runTest() {
		logger.step(0);
		MainForm mainForm = new MainForm();

		logger.step(1);
		mainForm.searchItem(nameItem);
		SearchForm searchForm = new SearchForm();

		logger.step(2);
		searchForm.goToFirstItemOffers();
		OffersForm offersForm = new OffersForm();

		logger.step(3);
		offersForm.addToCartFirstOffer();

		logger.step(4);
		offersForm.goToCart();
		CartForm cartForm = new CartForm();

		logger.step(5);
		cartForm.assertNameItem(nameItem);

		logger.step(6);
		cartForm.clear();


	}
}
