package test.java.funcionais.UC001;

import org.junit.Test;

import test.java.pageobject.Visao.PesquisaGenerica;
import test.java.shared.SeleniumUtils;
import test.java.shared.TestBase;

public class FA3 extends TestBase {

	@Test
	public void test() throws Exception {
		PesquisaGenerica.acessar();
		PesquisaGenerica.procurar("{'busca':'Victor Augusto Bathke'}");
		
		SeleniumUtils.isTextPresent("Aproximadamente");
	}
}