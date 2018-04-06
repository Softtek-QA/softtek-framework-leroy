package va.testes.funcionais.utils;

import org.openqa.selenium.chrome.ChromeOptions;

/**
 * 
 * Objeto base para defini��o dos testes
 * 
 * @author Softtek - QA
 *
 */
public class AUTTestObject {
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
	 * Inicializa o browser para execu��o dos testes : Chrome (Modo visual)
	 * 
	 * @return org.openqa.selenium.chrome.ChromeDriver - Objeto para automa��o
	 */
	public org.openqa.selenium.chrome.ChromeDriver configurarBrowserChromeMDVisual(){
		
		chrOptions = new ChromeOptions();
		
		chromeBrowser = new org.openqa.selenium.chrome.ChromeDriver(chrOptions);
				
		return chromeBrowser;
	}
	
	/**
	 * 
	 * Inicializa o browser para execu��o dos testes : Chrome (Modo : Segundo plano)
	 * 
	 * @return org.openqa.selenium.chrome.ChromeDriver - Objeto para automa��o
	 */
	public org.openqa.selenium.chrome.ChromeDriver configurarBrowserChromeMDSegundoPlano(){
		chrOptions = new ChromeOptions();
		chrOptions.addArguments("headless");
		chrOptions.addArguments("window-size=1200x1024");
		chromeBrowser = new org.openqa.selenium.chrome.ChromeDriver();
				
		return chromeBrowser;
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
