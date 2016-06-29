package onliner.forms.catalog;

import java.util.List;

/**
 * Страница сравнения Мобильных телефонов.
 */
public class CompareMobilesForm extends CompareForm {

	public void assertResultCompare(double sizeMin, double sizeMax, double ramMin, double ramMax, int batteryMin, int batteryMax) {
		logger.info("Проверка результата поиска");
		assertSize(sizeMin, sizeMax);
		assertRam(ramMin, ramMax);
		assertBattery(batteryMin, batteryMax);
	}

	public void assertSize(double sizeMin, double sizeMax) {
		logger.info("Проверка размера экрана");
		List<String> listSizeItems = getListFieldItems("Размер экрана");
		for (String s : listSizeItems) {
			double size = Double.parseDouble(s.replace("\"", ""));
			boolean flag = true;
			if (!(sizeMin == 0) && size < sizeMin)
				flag = false;
			if (!(sizeMax == 0) && size > sizeMax)
				flag = false;
			doAssert(flag, "Размер экрана " + size + ": Ok!", "Размер экрана " + size + ": Error!");
		}
	}

	public void assertRam(double ramMin, double ramMax) {
		logger.info("Проверка объёма памяти");
		List<String> listRamItems = getListFieldItems("Оперативная память");
		for (String s : listRamItems) {
			int ram = Integer.parseInt(s.replace(" ГБ", ""));
			boolean flag = true;
			if (!(ramMin == 0) && ram < ramMin)
				flag = false;
			if (!(ramMax == 0) && ram > ramMax)
				flag = false;
			doAssert(flag, "Объём памяти " + ram + "Гб: Ok!", "Объём памяти " + ram + "Гб: Error!");
		}
	}

	public void assertBattery(int batteryMin, int batteryMax) {
		logger.info("Проверка ёмкости аккумулятора");
		List<String> listBatteryItems = getListFieldItems("Ёмкость аккумулятора");
		for (String s : listBatteryItems) {
			int battery = Integer.parseInt(s.replace(" мА·ч", "").replace(" ", ""));
			boolean flag = true;
			if (!(batteryMin == 0) && battery < batteryMin)
				flag = false;
			if (!(batteryMax == 0) && battery > batteryMax)
				flag = false;
			doAssert(flag, "Ёмкость аккумулятора " + battery + " мА·ч: Ok!", "Ёмкость аккумулятора " + battery + " мА·ч: Error!");
		}
	}
}
