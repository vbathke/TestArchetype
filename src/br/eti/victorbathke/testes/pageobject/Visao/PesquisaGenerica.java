package br.eti.victorbathke.testes.pageobject.Visao;

import org.openqa.selenium.By;

import br.eti.victorbathke.testes.shared.FabricaWebDriver;
import br.eti.victorbathke.testes.shared.JsonParseSingleQuote;
import br.eti.victorbathke.testes.shared.TestBase;

public class PesquisaGenerica extends TestBase {

	public static void acessar() throws Exception {
		System.out.println("Acessando a url "+FabricaWebDriver.properties.getProperty("urlbase") + "/");
		FabricaWebDriver.getDriver().get(FabricaWebDriver.properties.getProperty("urlbase") + "/");
	}

	public static void procurar(String data) throws Exception {
		JsonParseSingleQuote jsonParser = new JsonParseSingleQuote(data);
		
		FabricaWebDriver.getDriver().findElement(By.id("lst-ib")).clear();
		System.out.println("Limpando o campo de pesquisa");
		
		FabricaWebDriver.getDriver().findElement(By.id("lst-ib")).sendKeys(jsonParser.get("busca"));
		System.out.println("Digitado o termpo de busca "+jsonParser.get("busca"));
		
		FabricaWebDriver.getDriver().findElement(By.name("btnG")).click();
		System.out.println("Clicado o botão de pesquisa");
	}
}
