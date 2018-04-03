package va.testes.funcionais.modulos.pedidos;

import static org.junit.Assert.*;

import org.junit.Test;
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
		
		String codigoProduto = "88282446";
		
		//AUTVAModuloCadastroClientesPF.docDriver.findElementByClassName("burger-text").click();
		
		AUTVAUtilidades.executarMetodoElementoHTML(AUTVAModuloCadastroClientesPF.docDriver.getClass().getName(), AUTVAModuloCadastroClientesPF.docDriver, "a", "click", "carrinho", 0);

		AUTVAUtilidades.executarMetodoElementoHTML(AUTVAModuloCadastroClientesPF.docDriver.getClass().getName(), AUTVAModuloCadastroClientesPF.docDriver, "a", "click", "Iniciar novo atendimento", 0);
		

		AUTVAUtilidades.executarMetodoElementoHTML(AUTVAModuloCadastroClientesPF.docDriver.getClass().getName(), AUTVAModuloCadastroClientesPF.docDriver, "a", "click", "O que você está precisando?", 0);

		AUTVAUtilidades.enviarDadosElementWeb(AUTVAModuloCadastroClientesPF.docDriver.findElementById("search"), (long)0.4, codigoProduto);
		
		AUTVAUtilidades.enviarDadosElementWeb(AUTVAModuloCadastroClientesPF.docDriver.findElementById("search"), (long)0.4, "\n");
		
		AUTVAUtilidades.executarMetodoElementoHTML(AUTVAModuloCadastroClientesPF.docDriver.getClass().getName(), AUTVAModuloCadastroClientesPF.docDriver, "a", "click", "Receber em casa", 0);
		
	
		AUTVAUtilidades.enviarDadosElementWeb(AUTVAModuloCadastroClientesPF.docDriver.getClass().getName(), AUTVAModuloCadastroClientesPF.docDriver, (long)0.3,"24", "a", ".{0,}\\W{0,}\\s{0,}input.{0,}\\W{0,}\\s{0,}quantity.{0,}\\W{0,}\\s{0,}");
		
		//AUTVAUtilidades.executarMetodoElementoHTML(AUTVAModuloCadastroClientesPF.docDriver.getClass().getName(), AUTVAModuloCadastroClientesPF.docDriver, "input", "click", "field=\"quantity\"", 0);

		

	}
}
