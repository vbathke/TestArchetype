package test.java.shared;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;

public class SeleniumUtils {

	public static void sendKeys(String keys, By by) {
		FabricaWebDriver.getDriver().findElement(by).clear();
		FabricaWebDriver.getDriver().findElement(by).sendKeys(keys);
	}

	public static boolean isElementPresent(By by) {
		try {
			FabricaWebDriver.getDriver().findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	public static boolean isTextPresent(String textToBeVerified){
		try{
			if(FabricaWebDriver.getDriver().findElement(By.xpath("//*[contains(.,'" + textToBeVerified + "')]")) != null){
				return true;
			}else{
				return false;
			}
		} catch (Exception e){
			return false;
		}
	}	
}