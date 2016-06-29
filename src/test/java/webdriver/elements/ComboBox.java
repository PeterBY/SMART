package webdriver.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.Select;

/**
 * Class, Describing element combo box
 */
public class ComboBox extends BaseElement {

	public ComboBox(By loc) {
		super(loc);
	}

	public ComboBox(By loc, String nameOf) {
		super(loc, nameOf);
	}

	public ComboBox(String stringLocator, String nameOfElement) {
		super(stringLocator, nameOfElement);
	}

	@Override
	protected String getElementType() {
		return getLoc("loc.combobox");
	}

	private Select getSelect() {
		return new Select(getElement());
	}

	public boolean isMultiple() {
		return getSelect().isMultiple();
	}

	public void selectByVisibleText(String text) {
		waitForIsElementPresent();
		info(getLoc("loc.selecting.value"));
		browser.getDriver().getMouse().mouseMove(element.getCoordinates());
		if (browser.getDriver() instanceof JavascriptExecutor) {
			((JavascriptExecutor)browser.getDriver()).executeScript("arguments[0].style.border='3px solid red'", element);
		}
		getSelect().selectByVisibleText(text);
	}

	public void selectByIndex(int index) {
		waitForIsElementPresent();
		info(getLoc("loc.selecting.value"));
		browser.getDriver().getMouse().mouseMove(element.getCoordinates());
		if (browser.getDriver() instanceof JavascriptExecutor) {
			((JavascriptExecutor)browser.getDriver()).executeScript("arguments[0].style.border='3px solid red'", element);
		}
		getSelect().selectByIndex(index);
	}

	public void selectByValue(String value) {
		waitForIsElementPresent();
		info(getLoc("loc.selecting.value"));
		browser.getDriver().getMouse().mouseMove(element.getCoordinates());
		if (browser.getDriver() instanceof JavascriptExecutor) {
			((JavascriptExecutor)browser.getDriver()).executeScript("arguments[0].style.border='3px solid red'", element);
		}
		getSelect().selectByValue(value);

	}
}
