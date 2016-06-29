package webdriver.elements;

import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.List;

/**
 * Class, Describing element check link
 */
public class Link extends BaseElement {
	public Link(By loc) {
		super(loc);
	}

	public Link(By loc, String nameOf) {
		super(loc, nameOf);
	}

	public Link(String stringLocator, String nameOfElement) {
		super(stringLocator, nameOfElement);
	}

	@Override
	protected String getElementType() {
		return getLoc("loc.link");
	}

}