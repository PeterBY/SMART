package webdriver.elements;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import webdriver.BaseEntity;
import webdriver.Browser;
import webdriver.Logger;

/**
 * Abstract class, describing base element.
 */
public abstract class BaseElement extends BaseEntity {

	private static final String LINK = "link=";
	private static final String XP = "xpath=";
	private static final String CLASS = "class=";
	private static final String ID = "id=";
	private static final String CSS = "css=";
	private static final int TIMEOUT_WAIT_0 = 0;
	private static final int TIMEOUT_WAIT_DEFAULT = 10;


	/**
	 * @uml.property name="name"
	 */
	protected String name;
	/**
	 * @uml.property name="locator"
	 * @uml.associationEnd multiplicity="(1 1)"
	 */
	protected By locator;
	/**
	 * @uml.property name="element"
	 * @uml.associationEnd
	 */
	protected RemoteWebElement element;

	/**
	 * @uml.property name="element"
	 * @uml.associationEnd
	 */


	/**
	 * @return RemoteWebElement
	 * @uml.property name="element"
	 */
	public RemoteWebElement getElement() {
		waitForIsElementPresent();
		return element;
	}

	/**
	 * @param elementToSet RemoteWebElement
	 * @uml.property name="element"
	 */
	public void setElement(final RemoteWebElement elementToSet) {
		element = elementToSet;
	}

	/**
	 * The simple constructor, name will be extracted
	 *
	 * @param loc By Locator
	 */
	protected BaseElement(final By loc) {
		locator = loc;
	}

	/**
	 * The main constructor
	 *
	 * @param loc    By Locator
	 * @param nameOf Output in logs
	 */
	protected BaseElement(final By loc, final String nameOf) {
		locator = loc;
		name = nameOf;
	}


	/**
	 * using different locators for different stages DON'T FORGET TO INIT {@link BaseEntity#stageController}
	 * @param locatorStageA First locator
	 * @param locatorStageB Second locator
	 * @param nameOfElement Name
	 */


	/**
	 * easy adapting from Selenium RC locators. CSS, ID, LINK, XP, CLASS
	 *
	 * @param stringLocator String locator
	 * @param nameOfElement Name
	 */
	protected BaseElement(String stringLocator, final String nameOfElement) {
		String clearLoc = null;
		if (stringLocator.contains(CSS)) {
			clearLoc = stringLocator.replaceFirst(CSS, "");
			locator = By.cssSelector(clearLoc);
			name = nameOfElement;
		} else if (stringLocator.contains(ID)) {
			clearLoc = stringLocator.replaceFirst(ID, "");
			locator = By.id(clearLoc);
			name = nameOfElement;
		} else if (stringLocator.contains(LINK)) {
			clearLoc = stringLocator.replaceFirst(LINK, "");
			locator = By.linkText(clearLoc);
			name = nameOfElement;
		} else if (stringLocator.contains(XP)) {
			clearLoc = stringLocator.replaceFirst(XP, "");
			locator = By.xpath(clearLoc);
			name = nameOfElement;
		} else if (stringLocator.contains(CLASS)) {
			clearLoc = stringLocator.replaceFirst(CLASS, "");
			locator = By.className(clearLoc);
			name = nameOfElement;
		} else {
			logger.fatal("UNKNOWN LOCATOR's TYPE. Change to 'By'");
		}
	}


	/**
	 * @return Locator
	 * @uml.property name="locator"
	 */
	public By getLocator() {
		return locator;
	}


	/**
	 * The implementation of an abstract method for logging of BaseEntity
	 *
	 * @param message Message to display in the log
	 * @return Formatted message (containing the name and type of item)
	 */
	protected String formatLogMsg(final String message) {
		return String.format("%1$s '%2$s' %3$s %4$s", getElementType(), name, Logger.LOG_DELIMITER, message);
	}

	/**
	 * The method returns the element type (used for logging)
	 *
	 * @return Type of element
	 * @uml.property name="elementType"
	 */
	protected abstract String getElementType();

	/**
	 * Send keys.
	 */
	public void sendKeys(Keys key) {
		waitForIsElementPresent();
		browser.getDriver().findElement(locator).sendKeys(key);
	}

	/**
	 * Wait for element is present.
	 */
	public void waitForIsElementPresent() {

		isPresent(Integer.valueOf(browser.getTimeoutForCondition()));
		// troubleshooting if element is not found
		if (!element.isDisplayed()) {//Browser.getTroubleShooting
			performTroubleShooting();
		}
		Assert.assertTrue(formatLogMsg(getLoc("loc.is.absent")), element.isDisplayed());
	}


	/**
	 * Performing troubleshooting via changing active locator, output log and report.
	 */
	private void performTroubleShooting() {
		int length = decrementLocator(locator).toString().length();
		try {
			info("---------------- Troubleshooting starting --------------------");
			for (int i = 0; i < length; i++) {
				decrementLocator(locator);
				Boolean result = isPresent(TIMEOUT_WAIT_DEFAULT);
				info("Re-try with locator: \t" + locator.toString() + String.format(new String(new char[i]).replace('\0', ' ') + " :%s", result ? "FOUND!" : "NOT FOUND"));
				if (result) {
					break;
				}
			}
		} catch (Exception e) {
			warn(e.getMessage());
		} finally {
			info("---------------- Troubleshooting finished --------------------");
		}
	}

	/**
	 * Decrement type of locator (troubleshooting).
	 *
	 * @param locator
	 * @return By
	 */
	private By decrementLocator(By locator) {
		for (Field field : locator.getClass().getDeclaredFields()) {
			field.setAccessible(true);
			try {
				String strLocator = (String) field.get(locator);
				field.set(locator, strLocator.substring(0, strLocator.length() - 1));
			} catch (IllegalArgumentException e) {
				warn(e.getMessage());
			} catch (IllegalAccessException e) {
				warn(e.getMessage());
			}
		}
		return locator;
	}


	/**
	 * Click on the item.
	 */
	public void click() {
		waitForIsElementPresent();
		info(getLoc("loc.clicking"));
		browser.getDriver().getMouse().mouseMove(element.getCoordinates());
		if (browser.getDriver() instanceof JavascriptExecutor) {
			((JavascriptExecutor) browser.getDriver()).executeScript("arguments[0].style.border='3px solid red'", element);
		}
		element.click();
	}

	/**
	 * Click on the item and wait loading.
	 */
	public void clickAndWait() {
		click();
		browser.waitForPageToLoad();
	}

	/**
	 * Get the item text (inner text).
	 *
	 * @return Text of element
	 */
	public String getText() {
		waitForIsElementPresent();
		return element.getText();
	}


	protected String id;

	/**
	 * Check for is element present on the page.
	 *
	 * @return Is element present
	 */
	public boolean isPresent() {
		boolean isPresent = isPresent(TIMEOUT_WAIT_0);
		info("is present : " + isPresent);
		return isPresent;
	}

	/**
	 * Check for is element present on the page.
	 *
	 * @return Is element present
	 */
	public boolean isPresent(int timeout) {
		WebDriverWait wait = new WebDriverWait(Browser.getInstance().getDriver(), timeout);
		browser.getDriver().manage().timeouts().implicitlyWait(TIMEOUT_WAIT_0, TimeUnit.SECONDS);
		try {
			wait.until(new ExpectedCondition<Boolean>() {
				public Boolean apply(final WebDriver driver) {
					try {
						List<WebElement> list = driver.findElements(locator);
						for (WebElement el : list) {
							if (el instanceof RemoteWebElement && el.isDisplayed()) {
								element = (RemoteWebElement) el;
								return element.isDisplayed();
							}
						}
						element = (RemoteWebElement) driver.findElement(locator);
					} catch (Exception e) {
						return false;
					}
					return element.isDisplayed();
				}
			});
		} catch (Exception e) {
			return false;
		}
		try {
			browser.getDriver().manage().timeouts().implicitlyWait(Integer.valueOf(browser.getTimeoutForCondition()), TimeUnit.SECONDS);
			return element.isDisplayed();
		} catch (Exception e) {
			warn(e.getMessage());
		}
		return false;
	}

	/**
	 * Returns list of WebElements by Locator.
	 *
	 * @param locator
	 * @return
	 */
	public List<WebElement> getListWebElements(final By locator) {
		waitForIsElementPresent();

		List<WebElement> listWebElements = (new WebDriverWait(browser.getDriver(), Integer.valueOf(browser.getTimeoutForCondition())))
				.until(new ExpectedCondition<List<WebElement>>() {
					public List<WebElement> apply(WebDriver d) {
						return d.findElements(locator);
					}
				});

		logger.info(String.format("Найдено %d элементов", listWebElements.size()));

		return listWebElements;
	}

	/**
	 * Returns list of all BaseElements by same locator like this instance.
	 * Accepts only XPath locators, otherwise asserts Fatal Error!
	 * Method uses the Reflection API.
	 *
	 * @return
	 */
	public List<BaseElement> getAllElements() {
		waitForIsElementPresent();

		List<WebElement> listWebElements = browser.getDriver().findElements(locator);

		logger.info(String.format("Найдено %d элементов", listWebElements.size()));

		List<BaseElement> listElements = new ArrayList<BaseElement>();

		String locatorElement = locator.toString();

		if (!locatorElement.startsWith("By.xpath: "))
			logger.fatal("Change locator of Element on XPatch!");

		for (int i = 1; i <= listWebElements.size(); i++) {
			String newLocator = String.format("xpath=(%s)[%d]", locatorElement.substring(10), i);

			try {
				Class<?> theClass = Class.forName(this.getClass().getName());
				Object object = theClass.getConstructor(String.class, String.class)
						.newInstance(new Object[]{newLocator, theClass.getSimpleName() + " " + i});
				listElements.add((BaseElement) object);
			} catch (ReflectiveOperationException e) {
				logger.fatal("Ошибка создания элементов");
			}
		}
		return listElements;
	}

	public void switchToFrame() {
		waitForIsElementPresent();
		browser.getDriver().switchTo().frame(browser.getDriver().findElement(locator));
		browser.waitForPageToLoad();
	}

	public void switchToDefault() {
		browser.getDriver().switchTo().defaultContent();
	}
}

