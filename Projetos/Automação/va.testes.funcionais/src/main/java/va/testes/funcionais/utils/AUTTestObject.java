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
 * Objeto base para defini��o dos testes
 * 
 * @author Softtek - QA
 *
 */
public class AUTTestObject{

	/**
	 * 
	 * Classe respons�vel pelo gerenciamento de log de mensagens do sistema
	 * 
	 * @author Softtek - QA
	 *
	 */


	public static ChromeDriver docDriver = null;

	/**
	 * 
	 * Numero do passo, refer�ncia global
	 * 
	 */
	public static Integer StepNumberGlobal = 0;
	/**
	 * 
	 * N�mero do passo refer�ncia local
	 * 
	 */
	private Integer StepNumber = 1;

	/**
	 * 
	 * Descri��o template do step
	 * 
	 */
	private String  StepDescricao = "STEP N� %s";

	/**
	 * 
	 * Objeto que representa o browser utilizado para automa��o dos testes : Chrome
	 * 
	 */
	private org.openqa.selenium.chrome.ChromeDriver chromeBrowser = null;	
	/**
	 * 
	 * Objeto que representa o browser utilizado para automa��o dos testes : Firefox
	 * 
	 */
	private org.openqa.selenium.firefox.FirefoxDriver firefoxBrowser = null;
	/**
	 * 
	 * Objeto que representa o browser utilizado para automa��o dos testes :  Opera
	 * 		
	 */
	private org.openqa.selenium.opera.OperaDriver operaBrowser = null;	

	/**
	 * 
	 * Op��es de configura��o do browser : Chrome
	 * 
	 */
	private ChromeOptions chrOptions = null;

	/**
	 * 
	 * Op��es de configura��o do browser : Firefox
	 * 
	 */
	private org.openqa.selenium.firefox.FirefoxOptions firefoxOptions = null;

	/**
	 * 
	 * Op��es de configura��o do browser : Opera
	 * 
	 */
	private org.openqa.selenium.opera.OperaOptions operaOptions = null;


	/**
	 * 
	 * 
	 * @return String - Nome do passo atual (em execu��o)
	 * 
	 */
	public String gerarNovoStep() {
		StepNumber++;
		return String.format(StepDescricao, StepNumber);
	}	

	/**
	 * 
	 * Inicializa o browser para execu��o dos testes : Chrome (Modo visual)
	 * 
	 * @return org.openqa.selenium.chrome.ChromeDriver - Objeto para automa��o
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
	 * Inicializa o browser para execu��o dos testes : Chrome (Modo : Segundo plano)
	 * 
	 * @return org.openqa.selenium.chrome.ChromeDriver - Objeto para automa��o
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
	 * @return org.openqa.selenium.chrome.ChromeDriver - Browser aplica��o
	 * 
	 */
	public org.openqa.selenium.chrome.ChromeDriver carregarBrowserChrome(){
		return chromeBrowser;
	}

	/**
	 * 
	 * Carrega op��es de configura��o do chrome
	 * 
	 * @return ChromeOptions - Op��es de configura��o do Chrome
	 * 
	 */
	public ChromeOptions carregarOpcoesConfigChrome() {
		return chrOptions;
	}

	/**
	 * 
	 * Configura��es de inicializa��o do projeto
	 * 
	 */
	public void configInit() {
		configurarBrowserChromeMDVisual();
	}

	/**
	 * 
	 * Construtor padr�o da classe
	 * 
	 */
	public AUTTestObject() {
		configInit();
	}	
}
