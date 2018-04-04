package va.testes.funcionais.modulos.pedidos;

import static org.junit.Assert.*;

import org.junit.Test;
import org.openqa.selenium.Keys;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import va.testes.funcionais.modulos.clientes.AUTVAModuloCadastroClientesPF;
import va.testes.funcionais.utils.AUTVAUtilidades;

public class AUTVAPedidos {
	@Test
	public void test() {
		System.setProperty("webdriver.chrome.driver", "../va.testes.funcionais/src/main/resources/chromedriver.exe");
		ChromeOptions chrOptions = new ChromeOptions();

		System.out.println("AUT PEDIDOS: INICIANDO CRIACAO DE PEDIDOS:");
		System.out.println(AUTVAModuloCadastroClientesPF.MD_PARAMETROS_SAIDA);	

		String usuarioLogin = AUTVAModuloCadastroClientesPF.MD_PARAMETROS_ENTRADA.get("PARAM_USUARIO_VA").toString(); 
		String senhaLogin = AUTVAModuloCadastroClientesPF.MD_PARAMETROS_ENTRADA.get("PARAM_PWD_VA").toString();

		String materialCodigoProdPesquisa = "88282446";
		String materialQuantProdPesquisa = "8";
		String materialCodigoProdPedido = "88282446|89419400|89455163|88521034|";
		String materialQuantPedido = "5|10|6|3|";		

		AUTVAUtilidades.executarMetodoElementoHTML(AUTVAModuloCadastroClientesPF.docDriver.getClass().getName(), AUTVAModuloCadastroClientesPF.docDriver, "a", "click", "carrinho", 0);

		AUTVAUtilidades.executarMetodoElementoHTML(AUTVAModuloCadastroClientesPF.docDriver.getClass().getName(), AUTVAModuloCadastroClientesPF.docDriver, "a", "click", "Iniciar novo atendimento", 0);


		AUTVAUtilidades.executarMetodoElementoHTML(AUTVAModuloCadastroClientesPF.docDriver.getClass().getName(), AUTVAModuloCadastroClientesPF.docDriver, "a", "click", "O que você está precisando?", 0);

		AUTVAUtilidades.enviarDadosElementWeb(AUTVAModuloCadastroClientesPF.docDriver.findElementById("search"), (long)0.4, materialCodigoProdPesquisa);

		AUTVAUtilidades.enviarDadosElementWeb(AUTVAModuloCadastroClientesPF.docDriver.findElementById("search"), (long)0.4, "\n");

		AUTVAUtilidades.executarMetodoElementoHTML(AUTVAModuloCadastroClientesPF.docDriver.getClass().getName(), AUTVAModuloCadastroClientesPF.docDriver, "a", "click", "Receber em casa", 0);

		AUTVAUtilidades.sincronizarStepPorTexto(30, AUTVAModuloCadastroClientesPF.docDriver, "(?i:\\<.{0,}\\>.{0,}\\W{0,}Meu Carrinho{0,}\\<.{0,}\\>)");

		java.util.List<org.openqa.selenium.WebElement> itensEncontrado = AUTVAUtilidades.procurarElementWebHTML(AUTVAModuloCadastroClientesPF.docDriver.getClass().getName(), AUTVAModuloCadastroClientesPF.docDriver, (long)0.3, "input", "\\W{0,}.{0,}(?i:\\<Input\\W{0,}.{0,}\\W{0,}field\\W{0,}.{0,}quantity\\W{0,}.{0,}\\W{0,}\\W{0,}\\>)");

		for(org.openqa.selenium.WebElement item : itensEncontrado) {
			item.clear();
			item.sendKeys(materialQuantProdPesquisa);
			item.sendKeys("\t");
		}

		boolean quantidadeInsuficiente = AUTVAUtilidades.sincronizarStepPorTexto(5, AUTVAModuloCadastroClientesPF.docDriver, "(?i:\\<(\\w+)\\>.{0,}\\W{0,}Quantidade insuficiente em estoque para o produto.{0,}\\W{0,}\\<\\/\\1>)");

		if(quantidadeInsuficiente) {
			System.out.println("AUT INFO TESTE : QUANTIDADE EM ESTOQUE DO ITEM É INSUFICIENTE PARA TRANSAÇÃO");

			java.util.List<org.openqa.selenium.WebElement> buttonsSubmit = AUTVAUtilidades.procurarElementWebHTML(AUTVAModuloCadastroClientesPF.docDriver.getClass().getName(), AUTVAModuloCadastroClientesPF.docDriver, (long)0.3, "button", "(?i:\\<(button).{0,}button\\-primary.{0,}\\>.{0,}\\W{0,}Ok.{0,}\\W{0,}\\<\\/\\1>)");

			for(org.openqa.selenium.WebElement item : buttonsSubmit) {
				item.click();
			}
		}
		else {
			System.out.println("AUT INFO TESTE : QUANTIDADE EM ESTOQUE DO ITEM É SUFICIENTE PARA ATENDER DEMANDA");
		}


		AUTVAUtilidades.procurarElementWebHTML(AUTVAModuloCadastroClientesPF.docDriver.getClass().getName(), AUTVAModuloCadastroClientesPF.docDriver, (long)0.3, "a", "(?i:<(\\w+).{0,}finalizar_topo.{0,}>\\W{0,}.{0,}Finalizar compra.{0,}\\W{0,}\\<\\/\\1.{0,})").get(0).click();

		AUTVAUtilidades.executarMetodoElementoHTML(AUTVAModuloCadastroClientesPF.docDriver.getClass().getName(), AUTVAModuloCadastroClientesPF.docDriver, "label", "click", "Digite aqui sua senha", 0);

		AUTVAModuloCadastroClientesPF.docDriver.findElementById("email").sendKeys(usuarioLogin);

		AUTVAUtilidades.executarMetodoElementoHTML(AUTVAModuloCadastroClientesPF.docDriver.getClass().getName(), AUTVAModuloCadastroClientesPF.docDriver, "label", "click", "Digite aqui sua senha", 0);

		AUTVAModuloCadastroClientesPF.docDriver.findElementById("password").sendKeys(senhaLogin);

		java.util.List<org.openqa.selenium.WebElement> buttonsEntrar = AUTVAUtilidades.procurarElementWebHTML(AUTVAModuloCadastroClientesPF.docDriver.getClass().getName(), AUTVAModuloCadastroClientesPF.docDriver, (long)0.3, "button", "(?i:\\<(button).{0,}\\>\\W{0,}.{0,}Entrar\\W{0,}.{0,})");

		for(org.openqa.selenium.WebElement bt : buttonsEntrar) {
			bt.click();
		}

		AUTVAUtilidades.sincronizarStepPorTexto(20, AUTVAModuloCadastroClientesPF.docDriver, "\\<.{0,}\\>.{0,}\\W{0,}Principais acessos\\W{0,}.{0,}\\<.{0,}\\>");

		java.util.List<org.openqa.selenium.WebElement> botaoCarrinho = AUTVAUtilidades.procurarElementWebHTML(AUTVAModuloCadastroClientesPF.docDriver.getClass().getName(), AUTVAModuloCadastroClientesPF.docDriver, (long)0.3, "i", "(?i:\\<i.{0,}glyph glyph-cart.{0,}\\>)");

		for(org.openqa.selenium.WebElement bt : botaoCarrinho) {
			bt.click();	
		}

		AUTVAUtilidades.executarMetodoElementoHTML(AUTVAModuloCadastroClientesPF.docDriver.getClass().getName(), AUTVAModuloCadastroClientesPF.docDriver, "a", "click", "Iniciar novo atendimento", 0);

		java.util.HashMap<Integer,String> prods = AUTVAUtilidades.autSplitParameters("\\d+", materialCodigoProdPedido);
		java.util.HashMap<Integer,String> prodQuants = AUTVAUtilidades.autSplitParameters("\\d+", materialQuantPedido);

		int contProds = 0;
		for(String codProd : prods.values()) {
			AUTVAUtilidades.procurarElementWebHTML(AUTVAModuloCadastroClientesPF.docDriver.getClass().getName(), AUTVAModuloCadastroClientesPF.docDriver, (long)0.3, "input", "(?i:\\<(input).{0,}\\W{0,}type\\=.{0,}text.{0,}\\=.{0,}qty.{0,}\\>)").get(0).sendKeys(prodQuants.get(contProds));
			AUTVAUtilidades.procurarElementWebHTML(AUTVAModuloCadastroClientesPF.docDriver.getClass().getName(), AUTVAModuloCadastroClientesPF.docDriver, (long)0.3, "input", "(?i:\\<(input).{0,}\\W{0,}\\=\\\"eanOrCode\\\".{0,}\\>)").get(0).sendKeys(codProd);
			AUTVAUtilidades.procurarElementWebHTML(AUTVAModuloCadastroClientesPF.docDriver.getClass().getName(), AUTVAModuloCadastroClientesPF.docDriver, (long)0.3, "input", "(?i:\\<(input).{0,}\\W{0,}\\=\\\"eanOrCode\\\".{0,}\\>)").get(0).sendKeys(Keys.ENTER);	
			contProds++;
		}
		
		
		AUTVAUtilidades.executarMetodoElementoHTML(AUTVAModuloCadastroClientesPF.docDriver.getClass().getName(), AUTVAModuloCadastroClientesPF.docDriver, "button", "click", "Converter para Pedido", 0);
		
		AUTVAUtilidades.fazerLoginConfirmacao(AUTVAModuloCadastroClientesPF.docDriver, usuarioLogin, senhaLogin);
		
	}
}
