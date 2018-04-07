package va.testes.funcionais.utils;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import va.testes.funcionais.utils.AUTVAUtilidades;
import va.testes.funcionais.utils.AUTVAUtilidades.*;


/**
 * 
 * Objeto base para definição dos testes
 * 
 * @author Softtek - QA
 *
 */
public class AUTTestObject{

	/**
	 * 
	 * Classe responsável pelo gerenciamento de log de mensagens do sistema
	 * 
	 * @author Softtek - QA
	 *
	 */


	public static ChromeDriver docDriver = null;

	/**
	 * 
	 * Numero do passo, referência global
	 * 
	 */
	public static Integer StepNumberGlobal = 0;
	/**
	 * 
	 * Número do passo referência local
	 * 
	 */
	private Integer StepNumber = 1;

	/**
	 * 
	 * Descrição template do step
	 * 
	 */
	private String  StepDescricao = "STEP Nº %s";

	/**
	 * 
	 * Objeto que representa o browser utilizado para automação dos testes : Chrome
	 * 
	 */
	private org.openqa.selenium.chrome.ChromeDriver chromeBrowser = null;	
	/**
	 * 
	 * Objeto que representa o browser utilizado para automação dos testes : Firefox
	 * 
	 */
	private org.openqa.selenium.firefox.FirefoxDriver firefoxBrowser = null;
	/**
	 * 
	 * Objeto que representa o browser utilizado para automação dos testes :  Opera
	 * 		
	 */
	private org.openqa.selenium.opera.OperaDriver operaBrowser = null;	

	/**
	 * 
	 * Opções de configuração do browser : Chrome
	 * 
	 */
	private ChromeOptions chrOptions = null;

	/**
	 * 
	 * Opções de configuração do browser : Firefox
	 * 
	 */
	private org.openqa.selenium.firefox.FirefoxOptions firefoxOptions = null;

	/**
	 * 
	 * Opções de configuração do browser : Opera
	 * 
	 */
	private org.openqa.selenium.opera.OperaOptions operaOptions = null;


	/**
	 * 
	 * 
	 * @return String - Nome do passo atual (em execução)
	 * 
	 */
	public String gerarNovoStep() {
		StepNumber++;
		return String.format(StepDescricao, StepNumber);
	}	

	/**
	 * 
	 * Inicializa o browser para execução dos testes : Chrome (Modo visual)
	 * 
	 * @return org.openqa.selenium.chrome.ChromeDriver - Objeto para automação
	 */
	public org.openqa.selenium.chrome.ChromeDriver configurarBrowserChromeMDVisual(){

		if(docDriver==null) {
			System.setProperty("webdriver.chrome.driver", "../va.testes.funcionais/src/main/resources/chromedriver.exe");

			chrOptions = new ChromeOptions();
			
			docDriver = new org.openqa.selenium.chrome.ChromeDriver();
		}


		return chromeBrowser;
	}

	/**
	 * 
	 * Inicializa o browser para execução dos testes : Chrome (Modo : Segundo plano)
	 * 
	 * @return org.openqa.selenium.chrome.ChromeDriver - Objeto para automação
	 */
	public org.openqa.selenium.chrome.ChromeDriver configurarBrowserChromeMDSegundoPlano(){

		if(docDriver==null) {

			chrOptions = new ChromeOptions();
			chrOptions.addArguments("headless");
			chrOptions.addArguments("window-size=1200x1024");
			chromeBrowser = new org.openqa.selenium.chrome.ChromeDriver(chrOptions);

		}

		return docDriver;
	}


	/**
	 *  
	 * @return org.openqa.selenium.chrome.ChromeDriver - Browser aplicação
	 * 
	 */
	public org.openqa.selenium.chrome.ChromeDriver carregarBrowserChrome(){
		return chromeBrowser;
	}

	/**
	 * 
	 * Carrega opções de configuração do chrome
	 * 
	 * @return ChromeOptions - Opções de configuração do Chrome
	 * 
	 */
	public ChromeOptions carregarOpcoesConfigChrome() {
		return chrOptions;
	}

	/**
	 * 
	 * Configurações de inicialização do projeto
	 * 
	 */
	public void configInit() {
		configurarBrowserChromeMDVisual();
	}

	/**
	 * 
	 * Construtor padrão da classe
	 * 
	 */
	public AUTTestObject() {
		configInit();
	}	
}
