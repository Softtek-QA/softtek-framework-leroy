package va.testes.funcionais.utils;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.openqa.selenium.chrome.ChromeDriver;
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
	 * Classe responsável pelo gerenciamento de log de mensagens do sistema
	 * 
	 * @author Softtek - QA
	 *
	 */
	public static class AUTLogMensagem{
		/**
		 * 
		 * Objeto arquivo (IO) - Log de Mensagens
		 * 
		 */
		private java.io.File fileLogDinamic;
		java.io.FileOutputStream fileLogPadrao;
		java.io.BufferedOutputStream buffOutputLogPadrao;
		Integer numeroMaxLinhasLog = 5;
		
		/**
		 * 
		 * Caminho do arquivo de dados associado ao gerenciador de logs
		 * 
		 */
		private String caminhoArquivoDinamico = "/va.testes.funcionais/logs/%s.txt";
		private String caminhoArquivoPadrao = "/va.testes.funcionais/logs/AUTLOG001.txt";
		/**
		 * 
		 * Variável de configuração do modo de operação do log(visual, arquivos)
		 * 
		 */
		private boolean exibirMensagemConsole = true;
				
		
		public static enum AUT_TIPO_MSG_LOG{
			/**
			 * 
			 * MENSAGEM INFORMATIVA PARA O USUARIO
			 * 
			 */
			MENSAGEM_INFORMATIVA,
			/**
			 * 
			 * MENSAGEM DE ALERTA PARA O USUARIO
			 * 
			 */
			MENSAGEM_ALERTA_USUARIO,
			/**
			 * 
			 * MENSAGEM PARA INFORMAR UM ERRO TESTE
			 * 
			 */
			MENSAGEM_ERRO_TESTE,
			
			/**
			 * 
			 * 
			 * MENSAGEM PARA INFORMAR UM ERRO NO SISTEMA
			 * 
			 */
			MENSAGEM_ERRO_SISTEMA;
			
			@Override
			public String toString() {
				switch(this) {
					case MENSAGEM_ALERTA_USUARIO:{
						
						return "ATENCAO USUARIO";
					}
					case MENSAGEM_ERRO_SISTEMA:{
						
						return "ERRO NO SISTEMA";
					}			
					case MENSAGEM_ERRO_TESTE:{
						
						return "ERRO NO CASO DE TESTES: FALHOU";
					}
					case MENSAGEM_INFORMATIVA:{
						
						return "INFORMACAO";
					}
				}
				
				return "";
			}
		}


		
		/**
		 * 
		 * Habilita a exibição de mensagens no console de saída padrão do sistema
		 * 
		 */
		public void habilitarExibicaoLogMsg() {
			exibirMensagemConsole = true;
		}
		
		/**
		 * 
		 * Desabilita a exibição de mensagens no log de saída padrão do sistema
		 * 
		 * 
		 */
		public void desabilitarExibicaoLogMsg() {
			exibirMensagemConsole = false;
		}
		
		/**
		 * 
		 * Verifica o status de configuração do log de mensagens padrão do sistema
		 * 
		 * @return boolean - Retorna true (exibição de mensagens no console habilitada), caso contrário false
		 * 
		 */
		public  boolean exibicaoLogHabilitada() {
			return exibirMensagemConsole;
		}
		
				
		/**
		 * 
		 * Registra mensagem no arquivo de log e direciona para o console de saída padrão
		 * 		
		 * @param tipoMensagem - Tipo da mensagem
		 * @param mensagem - Mensagem que será registrada no log do sistema
		 * 
		 */
		public void logMensagem(AUT_TIPO_MSG_LOG tipoMensagem,Object mensagem) {
			String formatMsg = "";
						
			switch(tipoMensagem) {
				case MENSAGEM_ALERTA_USUARIO:{
					
					formatMsg = String.format("TIPO MSG: %s : MENSAGEM : %s", tipoMensagem.toString(),mensagem.toString());
					
					if(exibicaoLogHabilitada()) {
						System.out.println(formatMsg);
					}
					else {						
						//registrarLog(formatMsg);
					}
					
					break;
				}
				case MENSAGEM_ERRO_SISTEMA:{
					
					formatMsg = String.format("TIPO MSG: %s : MENSAGEM : %s", tipoMensagem.toString(),mensagem.toString());
					
					if(exibicaoLogHabilitada()) {
						System.out.println(formatMsg);
					}
					else {						
						//registrarLog(formatMsg);
					}
					
					break;
				}
				case MENSAGEM_ERRO_TESTE:{
					
					formatMsg = String.format("TIPO MSG: %s : MENSAGEM : %s", tipoMensagem.toString(),mensagem.toString());
					
					if(exibicaoLogHabilitada()) {
						System.out.println(formatMsg);
					}
					else {						
						//registrarLog(formatMsg);
					}

					break;
				}
				case MENSAGEM_INFORMATIVA:{
					
					formatMsg = String.format("TIPO MSG: %s : MENSAGEM : %s", tipoMensagem.toString(),mensagem.toString());
					
					if(exibicaoLogHabilitada()) {
						System.out.println(formatMsg);
					}
					else {						
						//registrarLog(formatMsg);
					}

					break;
				}			
			}
		}
		
		public void logMensagem(Object mensagem) {
			logMensagem(AUT_TIPO_MSG_LOG.MENSAGEM_INFORMATIVA, mensagem);
		}
		
		
		public void registrarLog(String mensagem) {
			try {
				buffOutputLogPadrao.write(mensagem.getBytes());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		/**
		 * 
		 * Configurações de inicialização da classe
		 * 
		 */
		public void configInit() {
			try {
				
				System.out.println("AUT INFO : CRIANDO ARQUIVO DE LOGS DO SISTEMA");
				
				fileLogPadrao = new java.io.FileOutputStream(caminhoArquivoPadrao);
				
				buffOutputLogPadrao = new java.io.BufferedOutputStream(fileLogPadrao);
				
				
			} catch (FileNotFoundException e) {
				
				System.out.println("AUT INIT DATA ERROR: INICIALIZACAO DO ARQUIVO DE LOG");
				
				System.out.println(e.getMessage());
				
				e.printStackTrace();
			}
		
			System.out.println("AUT INFO : CONFIGURANDO VARIAVEIS AMBIENTE");

			/******************** CONFIGURACAO DE VARIÁVEIS AMBIENTE **********************/
			
			
		}
		/**
		 * Construtor padrão da classe
		 */
		public AUTLogMensagem() {
			configInit();
		}
	}


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
