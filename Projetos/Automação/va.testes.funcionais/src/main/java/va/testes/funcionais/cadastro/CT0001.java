package va.testes.funcionais.cadastro;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import va.testes.funcionais.md.AUTDataLoader;
import va.testes.funcionais.utils.AUTVAUtilidades;
public class CT0001 {
	
	
	
	@Test
	public void test() throws IOException, InterruptedException {
		
		System.setProperty("webdriver.chrome.driver", "../va.testes.funcionais/src/main/resources/chromedriver.exe");
		ChromeOptions chrOptions = new ChromeOptions();
		
		
		chrOptions.addArguments("headless");
		chrOptions.addArguments("window-size=1200x1024");
		
		//ChromeDriver docDriver = new ChromeDriver(chrOptions);
		ChromeDriver docDriver = new ChromeDriver();		
		
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
	}

}
