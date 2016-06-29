package onliner.forms.catalog;

import org.openqa.selenium.By;
import webdriver.elements.ComboBox;
import webdriver.elements.TextBox;

/**
 * Страница каталога, раздел Мобильные телефоны.
 */
public class MobilesForm extends ItemsForm {
	private ComboBox cmbSizeMin = new ComboBox(By.xpath("(//div[span[contains(text(),'Размер экрана')]]/following-sibling::div//select)[1]"), "Size Min");
	private ComboBox cmbSizeMax = new ComboBox(By.xpath("(//div[span[contains(text(),'Размер экрана')]]/following-sibling::div//select)[2]"), "Size Max");
	private ComboBox cmbResolutionMin = new ComboBox(By.xpath("(//div[span[contains(text(),'Разрешение экрана')]]/following-sibling::div//select)[1]"), "Resolution Min");
	private ComboBox cmbResolutionMax = new ComboBox(By.xpath("(//div[span[contains(text(),'Разрешение экрана')]]/following-sibling::div//select)[2]"), "Resolution Max");
	private TextBox txbRamMin = new TextBox(By.xpath("(//div[span[contains(text(),'Оперативная память')]]/following-sibling::div//input[@type='text'])[1]"), "Ram Min");
	private TextBox txbRamMax = new TextBox(By.xpath("(//div[span[contains(text(),'Оперативная память')]]/following-sibling::div//input[@type='text'])[2]"), "Ram Max");
	private TextBox txbBatteryMin = new TextBox(By.xpath("(//div[span[contains(text(),'Емкость аккумулятора')]]/following-sibling::div//input[@type='text'])[1]"), "Battery Min");
	private TextBox txbBatteryMax = new TextBox(By.xpath("(//div[span[contains(text(),'Емкость аккумулятора')]]/following-sibling::div//input[@type='text'])[2]"), "Battery Max");

	public MobilesForm() {
		super(By.xpath("//div[@class='schema-header']/h1[text()='Мобильные телефоны']"), "Каталог Мобильные телефоны");
	}

	public void searchItems(double sizeMin, double sizeMax, double ramMin, double ramMax, int batteryMin, int batteryMax) {
		searchItemsByScreenSize(sizeMin, sizeMax);
		searchItemsByRamSize(ramMin, ramMax);
		searchItemsByBatteryCapacity(batteryMin, batteryMax);
	}

	public void searchItemsByScreenSize(double sizeMin, double sizeMax) {
		logger.info("Поиск по размеру экрана");
		if (!(sizeMin == 0))
			cmbSizeMin.selectByValue(String.format("%.0f", sizeMin * 100));
		if (!(sizeMax == 0))
			cmbSizeMax.selectByValue(String.format("%.0f", sizeMax * 100));
	}

	public void searchItemsByResolution(String resolutionMin, String resolutionMax) {
		logger.info("Поиск по разрешению экрана");
		if (!(resolutionMin.isEmpty()))
			cmbResolutionMin.selectByValue(resolutionMin);
		if (!(resolutionMax.isEmpty()))
			cmbResolutionMax.selectByValue(resolutionMin);
	}

	public void searchItemsByRamSize(double ramMin, double ramMax) {
		logger.info("Поиск по объёму оперативной памяти");
		if (!(ramMin == 0))
			txbRamMin.setText(String.valueOf(ramMin));
		if (!(ramMax == 0))
			txbRamMax.setText(String.valueOf(ramMax));
	}

	public void searchItemsByBatteryCapacity(int batteryMin, int batteryMax) {
		logger.info("Поиск по ёмкости аккумулятора");
		if (!(batteryMin == 0))
			txbBatteryMin.setText(String.valueOf(batteryMin));
		if (!(batteryMax == 0))
			txbBatteryMax.setText(String.valueOf(batteryMax));
	}
}
