package onliner.forms.catalog;

import onliner.forms.OnlinerForm;
import org.openqa.selenium.By;
import webdriver.elements.*;

import java.util.List;

/**
 * Абстрактный класс, описывает раздел каталога.
 */
public abstract class ItemsForm extends OnlinerForm {
	private final String locatorBrandName = "//div[span[text()='Производитель']]/following-sibling::div//span[text()='%1s']";
	protected final String locatorItemPage = "//div[@class='schema-product__title']/a";
	private final String locatorSelectItemToCompare = "//div[@class='schema-product__compare']";
	private final String locatorSortingType = "//div[@class='schema-order__list']//span[contains(text(), '%s')]";

	private Button btnCompare = new Button(By.xpath("//div[@class='compare-button compare-button_visible']"), "Compare Button");
	private Button btnTrashCompare = new Button(By.xpath("//a[@class='compare-button__sub compare-button__sub_control']"), "Очистить список сравнения");
	private Label lblBrandNameMore = new Label(By.xpath("//div[span[text()='Производитель']]/following-sibling::div/div"), "Все производители");
	private Link lnkSorting = new Link(By.xpath("//a[@class='schema-order__link']"), "Сортировка");
	private TextBox txbPriceMin = new TextBox(By.xpath("(//div[span[contains(text(),'цена')]]/following-sibling::div//input[@type='text'])[1]"), "Price Min");
	private TextBox txbPriceMax = new TextBox(By.xpath("(//div[span[contains(text(),'цена')]]/following-sibling::div//input[@type='text'])[2]"), "Price Max");

	protected ItemsForm(By locator, String formTitle) {
		super(locator, formTitle);
	}

	/**
	 * Search item by BrandName
	 * @param brandName
	 */
	public void searchItemsByBrandName(String brandName) {
		logger.info("Выбор производителя");
		lblBrandNameMore.click();
		CheckBox checkBoxBrandName = new CheckBox(By.xpath(String.format(locatorBrandName, brandName)), brandName);
		checkBoxBrandName.check();
	}

	/**
	 * Search item by Price
	 * @param priceMin
	 * @param priceMax
	 */
	public void searchItemsByPrice(double priceMin, double priceMax) {
		logger.info("Поиск по цене");
		if (!(priceMin == 0))
			txbPriceMin.setText(String.valueOf(priceMin));
		if (!(priceMax == 0))
			txbPriceMax.setText(String.valueOf(priceMax));
	}

	/**
	 * Sort item by sortType
	 * @param sortType
	 */
	public void sortBy(String sortType) {
		logger.info("Сортировка по " + sortType);
		lnkSorting.click();
		new Label(By.xpath(String.format(locatorSortingType, sortType)), "Сортировать по " + sortType).clickAndWait();
	}

	/**
	 * Selecting all items on page
	 */
	public void selectAllItemsOnPage() {
		logger.info("Выбор всех позиций для сравнения");
		cleanSelection();

		List<BaseElement> searchResult = new CheckBox(By.xpath(locatorSelectItemToCompare)).getAllElements();
		for (BaseElement element : searchResult) {
			((CheckBox) element).check();
		}
	}

	/**
	 * Selecting first n-items on page
	 * @param number
	 */
	public void selectFirstItemsOnPage(int number) {
		logger.info(String.format("Выбор первых %d позиций для сравнения", number));
		cleanSelection();

		List<BaseElement> searchResult = new CheckBox(By.xpath(locatorSelectItemToCompare)).getAllElements();
		for (int i = 0; i < searchResult.size() && i < number; i++) {
			((CheckBox) searchResult.get(i)).check();
		}
	}

	/**
	 * Compare selected items
	 */
	public void compareSelectedItems() {
		logger.info("Сравнение товаров");
		if (!btnCompare.isPresent(3))
			logger.fatal("Не выбраны элементы сравнения");

		btnCompare.clickAndWait();
	}

	/**
	 * Clean list of selection
	 */
	public void cleanSelection() {
		logger.info("Очистка списка для сравнения");
		if (!btnCompare.isPresent(3))
			logger.info("Список для сравнения пуст");
		else {
			btnTrashCompare.click();
			btnCompare.clickAndWait();
		}
	}
}
