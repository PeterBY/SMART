package webdriver.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

/**
 * Class, Describing element check box
 */
public class CheckBox extends BaseElement {


	public CheckBox(By loc) {
		super(loc);
	}

	public CheckBox(By loc, String nameOf) {
		super(loc, nameOf);
	}

	public CheckBox(String stringLocator, String nameOfElement) {
		super(stringLocator, nameOfElement);
	}

	@Override
	protected String getElementType() {
		return getLoc("loc.checkbox");
	}

	public boolean isEnabled() {
		return this.getElement().isEnabled();
	}

	public void check() {
		waitForIsElementPresent();
		if (!element.isSelected()) {
			click();
		}
	}

	public void unCheck() {
		waitForIsElementPresent();
		if (element.isSelected()) {
			click();
		}
	}

	public boolean isChecked() {
		waitForIsElementPresent();
		if (element.isSelected()) {
			return true;
		} else return false;
	}
}
