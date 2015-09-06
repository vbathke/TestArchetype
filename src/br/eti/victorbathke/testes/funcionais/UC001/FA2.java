package br.eti.victorbathke.testes.funcionais.UC001;

import org.junit.Test;

import br.eti.victorbathke.testes.pageobject.Visao.PesquisaGenerica;
import br.eti.victorbathke.testes.shared.SeleniumUtils;
import br.eti.victorbathke.testes.shared.TestBase;

public class FA2 extends TestBase {

	@Test
	public void test() throws Exception {
		PesquisaGenerica.acessar();
		PesquisaGenerica.procurar("{'busca':'Victor Augusto Bathke'}");
		
		SeleniumUtils.isTextPresent("Aproximadamente");
		System.out.println("Texto \"Aproximadamente\" verificado");
		
	}
}