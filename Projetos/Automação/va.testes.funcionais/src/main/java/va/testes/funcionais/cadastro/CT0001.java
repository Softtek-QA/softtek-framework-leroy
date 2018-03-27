package va.testes.funcionais.cadastro;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.interactions.Keyboard;
import org.openqa.selenium.interactions.SendKeysAction;
import org.openqa.selenium.remote.ExecuteMethod;
import org.openqa.selenium.remote.RemoteExecuteMethod;
import org.openqa.selenium.remote.RemoteKeyboard;

import va.testes.funcionais.md.AUTDataLoader;
import va.testes.funcionais.utils.AUTVAUtilidades;
public class CT0001 {
	
	
	
	@Test
	public void test() throws IOException, InterruptedException {
		
		System.setProperty("webdriver.chrome.driver", "../va.testes.funcionais/src/main/resources/chromedriver.exe");
		ChromeOptions chrOptions = new ChromeOptions();
		
		
		chrOptions.addArguments("headless");
		chrOptions.addArguments("window-size=1200x1024");
		
		ChromeDriver docDriver = new ChromeDriver(chrOptions);
		//ChromeDriver docDriver = new ChromeDriver();		
		
		System.out.println("INFO TEST: CARREGANDO MASSA DE DADOS DO TESTE");
		java.util.HashMap<String, String> parametrosTeste = AUTDataLoader.carregarParametros("../va.testes.funcionais/Arquivos de Dados/AUTCN001.txt");
		
		String urlInit = parametrosTeste.get("LINK_APLICACAO_WEB");
		String usuarioLogin = parametrosTeste.get("PARAM_USUARIO_VA");
		String senhaLogin = parametrosTeste.get("PARAM_PWD_VA");
				
		docDriver.get(urlInit);
		
		AUTVAUtilidades.fazerLogin(docDriver, usuarioLogin, senhaLogin);
		
		boolean existemItemsPendAprov = AUTVAUtilidades.sincronizarStepPorTexto(20,docDriver, "\\<.{0,}\\>.{0,}Você possui pedidos/orçamentos, pendentes de aprovação comercial.{0,}\\<.{0,}\\>");
		
		
		if(existemItemsPendAprov) {
			System.out.println("AUT VALIDACAO HTML: EXISTEM PEDIDOS PENDENTES DE APROVACAO");
			docDriver.findElement(By.xpath("/html/body/div[3]/div/i")).click();			
		}
		else {
			System.out.println("AUT VALIDACAO HTML: NAO EXISTEM PEDIDOS PENDENTES DE APROVACAO");
		}	
		
		AUTVAUtilidades.sincronizarStepPorTexto(20,docDriver, "\\<.{0,}\\>.{0,}\\W{0,}Criar carrinho\\.\\.\\..{0,}\\<.{0,}\\>");
		AUTVAUtilidades.sincronizarStepPorTexto(20,docDriver, "\\<.{0,}\\>.{0,}\\W{0,}Recuperar pedido.{0,}\\<.{0,}\\>");
		AUTVAUtilidades.sincronizarStepPorTexto(20,docDriver, "\\<.{0,}\\>.{0,}\\W{0,}Recuperar orçamento.{0,}\\<.{0,}\\>");
		AUTVAUtilidades.sincronizarStepPorTexto(20,docDriver, "\\<.{0,}\\>.{0,}\\W{0,}Recuperar carrinho.{0,}\\<.{0,}\\>");	
		docDriver.findElementByXPath("/html/body/div[3]/div/i").click();
		
		docDriver.executeScript("document.getElementsByClassName(\"burger-menu\")[0].click()");
		
			
		AUTVAUtilidades.sincronizarStepPorTexto(20,docDriver, "\\<.{0,}\\>.{0,}Selecionar Loja.{0,}\\<.{0,}\\>");
		boolean existeMenuClientes = AUTVAUtilidades.sincronizarStepPorTexto(20,docDriver, "\\<.{0,}\\>.{0,}Clientes.{0,}\\<.{0,}\\>");
		AUTVAUtilidades.sincronizarStepPorTexto(20,docDriver, "\\<.{0,}\\>.{0,}\\W{0,}Parâmetros de Frete\\W{0,}.{0,}\\<.{0,}\\>");
		AUTVAUtilidades.sincronizarStepPorTexto(20,docDriver, "\\<.{0,}\\>.{0,}\\W{0,}Performance de Vendas\\W{0,}.{0,}\\<.{0,}\\>");
		AUTVAUtilidades.sincronizarStepPorTexto(20,docDriver, "\\<.{0,}\\>.{0,}\\W{0,}RSS Pendentes\\W{0,}.{0,}\\<.{0,}\\>");
		AUTVAUtilidades.sincronizarStepPorTexto(20,docDriver, "\\<.{0,}\\>.{0,}\\W{0,}Liberações Pendentes\\W{0,}.{0,}\\<.{0,}\\>");
		AUTVAUtilidades.sincronizarStepPorTexto(20,docDriver, "\\<.{0,}\\>.{0,}\\W{0,}Liberar Cartão Pedido\\W{0,}.{0,}\\<.{0,}\\>");

		docDriver.executeScript("cont=0;tot=document.getElementsByTagName(\"strong\").length;itens=document.getElementsByTagName(\"strong\");while(cont<tot){console.log(itens[cont]);cont++;}");
		
		AUTVAUtilidades.executarMetodoElementoHTML(docDriver.getClass().getName(), docDriver, "strong", "click", "Clientes", 0);
		
		AUTVAUtilidades.sincronizarStepPorTexto(20,docDriver, "\\<.{0,}\\>.{0,}\\W{0,}Adicionar Novo\\W{0,}.{0,}\\<.{0,}\\>");

		AUTVAUtilidades.executarMetodoElementoHTML(docDriver.getClass().getName(), docDriver, "a", "click", "Adicionar Novo", 0);

		AUTVAUtilidades.sincronizarStepPorTexto(20,docDriver, "\\<.{0,}\\>.{0,}\\W{0,}Adicionar Cliente\\W{0,}.{0,}\\<.{0,}\\>");

		AUTVAUtilidades.sincronizarStepPorTexto(20,docDriver, "\\<.{0,}\\>.{0,}\\W{0,}Dados Básicos\\W{0,}.{0,}\\<.{0,}\\>");

		
		
	}

}
