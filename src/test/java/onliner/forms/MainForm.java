package onliner.forms;

import org.openqa.selenium.By;

/**
 * Main Form
 */
public class MainForm extends OnlinerForm {
	public MainForm() {
		super(By.xpath("//div[@class='project-navigation project-navigation_overflow']"), "Главная страница");
	}
}
