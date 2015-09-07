package test.java.pageobject.Visao;

import org.openqa.selenium.By;

import test.java.shared.FabricaWebDriver;
import test.java.shared.JsonParseSingleQuote;
import test.java.shared.TestBase;

public class PesquisaGenerica extends TestBase {

	public static void acessar() throws Exception {
		FabricaWebDriver.getDriver().get(FabricaWebDriver.properties.getProperty("urlbase") + "/");
	}

	public static void procurar(String data) throws Exception {
		JsonParseSingleQuote jsonParser = new JsonParseSingleQuote(data);
		
		FabricaWebDriver.getDriver().findElement(By.id("lst-ib")).clear();
		FabricaWebDriver.getDriver().findElement(By.id("lst-ib")).sendKeys(jsonParser.get("busca"));
		FabricaWebDriver.getDriver().findElement(By.name("btnG")).click();
	}
}
