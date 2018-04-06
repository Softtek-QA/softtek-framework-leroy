package va.testes.funcionais.utils;

import org.openqa.selenium.chrome.ChromeOptions;

/**
 * 
 * Objeto base para definição dos testes
 * 
 * @author Softtek - QA
 *
 */
public class AUTTestObject {
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
	 * Inicializa o browser para execução dos testes : Chrome (Modo visual)
	 * 
	 * @return org.openqa.selenium.chrome.ChromeDriver - Objeto para automação
	 */
	public org.openqa.selenium.chrome.ChromeDriver configurarBrowserChromeMDVisual(){
		
		chrOptions = new ChromeOptions();
		
		chromeBrowser = new org.openqa.selenium.chrome.ChromeDriver(chrOptions);
				
		return chromeBrowser;
	}
	
	/**
	 * 
	 * Inicializa o browser para execução dos testes : Chrome (Modo : Segundo plano)
	 * 
	 * @return org.openqa.selenium.chrome.ChromeDriver - Objeto para automação
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
