package va.testes.funcionais.utils;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.CopyOption;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.DateFormat;

import javax.swing.JScrollPane;
import javax.swing.event.ChangeEvent;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
import va.testes.funcionais.modulos.clientes.AUTVAModuloCadastroClientesPF;
import va.testes.funcionais.runtime.*;

public class AUTVAUtilidades {	


	/**
	 * Classe responsável pelo gerenciamento de logs dos sistema
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


		/**
		 * 
		 * Registra log de mensagem no formato padrão
		 * 
		 * 
		 * Tipo padrão : AUT_TIPO_MSG_LOG.MENSAGEM_INFORMATIVA
		 * 
		 * @param mensagem - Mensagem de envio para log
		 * 
		 */
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


	/**
	 * 
	 * Classe de serviços publicados 
	 * 
	 * @author Softtek - QA
	 *
	 */
	public static abstract class AUTTestServices{
		
	}

	/**
	 * Classe responsável pelo desenvolvimento de expressões regulares para teste e formatação de dados
	 * 
	 * @author Softtek - QA
	 *
	 */
	/**
	 * Define os tipos de saída de mercadoria possíveis
	 * 
	 * @author Softtek - QA
	 *
	 */
	public static enum AUT_TIPO_FLUXO_SAIDA{

		/**
		 * 
		 * SAIDA DE CAIXA
		 * 
		 */
		CAIXA,

		/**
		 * 
		 * RETIRADA NA LOJA
		 * 
		 */
		RETIRADA_INTERNA,

		/**
		 * 
		 * ENTREGA A DOMICILIO
		 * 
		 */
		ENTREGA,

		/**
		 * 
		 * RETIDA NA LOJA EXTERNA AGENDADA
		 * 
		 */
		RETIRADA_EXTERNA_AGENDADA,
		/**
		 *  
		 *  RETIRADA NA LOJA INTERNA NO MOMENTO DA COMPRA		
		 */
		RETIRADA_INTERNA_IMEDIATA,
		/**
		 * 
		 * RETIRADA EXTERNA NO MOMENTO DA COMPRA
		 */
		RETIRADA_EXTERNA_IMEDIATA,

		/**
		 * 
		 * ENTREGA EXPRESSA
		 * 
		 */
		RETIRADA_INTERNA_AGENDADA,
		ENTREGA_ECONOMICA;

		@Override
		public String toString() {
			switch(this) {
			case CAIXA:{			
				return "CAIXA";
			}			
			case ENTREGA:{				
				return "ENTREGA";
			}
			case ENTREGA_ECONOMICA:{			
				return "ENTREGA ECONOMICA";
			}
			case RETIRADA_EXTERNA_IMEDIATA:{				
				return "2_RETIRA_EXTERNA_IMEDIATA";				
			}
			case RETIRADA_EXTERNA_AGENDADA:{				
				return "3_RETIRA_EXTERNA_AGENDADA";
			}
			case RETIRADA_INTERNA_IMEDIATA:{			
				return "4_RETIRA_INTERNA_IMEDIATA";
			}
			case RETIRADA_INTERNA_AGENDADA:{			
				return "5_RETIRA_INTERNA_AGENDADA";
			}

			}

			return "";
		}
	}


	/**
	 * 
	 * Relação de lojas e Centrais de distribuição cadastradas no sistema
	 * 
	 * @author Softtek - QA
	 *
	 */
	public static enum AUT_TIPO_LOJA{
		LJ_OU_CD_0001,
		LJ_OU_CD_0002,
		LJ_OU_CD_0003,
		LJ_OU_CD_0004,
		LJ_OU_CD_0005,
		LJ_OU_CD_0007,
		LJ_OU_CD_0008,
		LJ_OU_CD_0009,
		LJ_OU_CD_0010,
		LJ_OU_CD_0011,
		LJ_OU_CD_0012,
		LJ_OU_CD_0013,
		LJ_OU_CD_0014,
		LJ_OU_CD_0015,
		LJ_OU_CD_0016,
		LJ_OU_CD_0017,
		LJ_OU_CD_0018,
		LJ_OU_CD_0019,
		LJ_OU_CD_0020,
		LJ_OU_CD_0021,
		LJ_OU_CD_0022,
		LJ_OU_CD_0023,
		LJ_OU_CD_0024,
		LJ_OU_CD_0025,
		LJ_OU_CD_0026,
		LJ_OU_CD_0027,
		LJ_OU_CD_0028,
		LJ_OU_CD_0029,
		LJ_OU_CD_0030,
		LJ_OU_CD_0031,
		LJ_OU_CD_0032,
		LJ_OU_CD_0033,
		LJ_OU_CD_0034,
		LJ_OU_CD_0035,
		LJ_OU_CD_0036,
		LJ_OU_CD_0037,
		LJ_OU_CD_0038,
		LJ_OU_CD_0039,
		LJ_OU_CD_0040,
		LJ_OU_CD_0041,
		LJ_OU_CD_0042,
		LJ_OU_CD_0043,
		LJ_OU_CD_0044,
		LJ_OU_CD_0045,
		LJ_OU_CD_0046,
		LJ_OU_CD_0047,
		LJ_OU_CD_0048,
		LJ_OU_CD_0049,
		LJ_OU_CD_0050,
		LJ_OU_CD_0051,
		LJ_OU_CD_0052,
		LJ_OU_CD_0053,
		LJ_OU_CD_0055,
		LJ_OU_CD_0056,
		LJ_OU_CD_0057,
		LJ_OU_CD_0058,
		LJ_OU_CD_0201,
		LJ_OU_CD_0519,
		LJ_OU_CD_0802,
		LJ_OU_CD_0999,
		LJ_OU_CD_CD01,
		LJ_OU_CD_LOJ1,
		LJ_OU_CD_XD01,
		LJ_OU_CD_ZAC1,
		LJ_OU_CD_ZAL1,
		LJ_OU_CD_ZAM1,
		LJ_OU_CD_ZAP1,
		LJ_OU_CD_ZBA1,
		LJ_OU_CD_ZCE1,
		LJ_OU_CD_ZDF1,
		LJ_OU_CD_ZES1,
		LJ_OU_CD_ZGO1,
		LJ_OU_CD_ZMA1,
		LJ_OU_CD_ZMG1,
		LJ_OU_CD_ZMS1,
		LJ_OU_CD_ZMT1,
		LJ_OU_CD_ZPA1,
		LJ_OU_CD_ZPB1,
		LJ_OU_CD_ZPE1,
		LJ_OU_CD_ZPI1,
		LJ_OU_CD_ZPR1,
		LJ_OU_CD_ZRJ1,
		LJ_OU_CD_ZRN1,
		LJ_OU_CD_ZRO1,
		LJ_OU_CD_ZRR1,
		LJ_OU_CD_ZRS1,
		LJ_OU_CD_ZSC1,
		LJ_OU_CD_ZSE1,
		LJ_OU_CD_ZSP1,
		LJ_OU_CD_ZTO1;

		@Override
		public String toString() {

			switch(this) {
			case LJ_OU_CD_0001: { return "0001"; }
			case LJ_OU_CD_0002: { return "0002"; }
			case LJ_OU_CD_0003: { return "0003"; }
			case LJ_OU_CD_0004: { return "0004"; }
			case LJ_OU_CD_0005: { return "0005"; }
			case LJ_OU_CD_0007: { return "0007"; }
			case LJ_OU_CD_0008: { return "0008"; }
			case LJ_OU_CD_0009: { return "0009"; }
			case LJ_OU_CD_0010: { return "0010"; }
			case LJ_OU_CD_0011: { return "0011"; }
			case LJ_OU_CD_0012: { return "0012"; }
			case LJ_OU_CD_0013: { return "0013"; }
			case LJ_OU_CD_0014: { return "0014"; }
			case LJ_OU_CD_0015: { return "0015"; }
			case LJ_OU_CD_0016: { return "0016"; }
			case LJ_OU_CD_0017: { return "0017"; }
			case LJ_OU_CD_0018: { return "0018"; }
			case LJ_OU_CD_0019: { return "0019"; }
			case LJ_OU_CD_0020: { return "0020"; }
			case LJ_OU_CD_0021: { return "0021"; }
			case LJ_OU_CD_0022: { return "0022"; }
			case LJ_OU_CD_0023: { return "0023"; }
			case LJ_OU_CD_0024: { return "0024"; }
			case LJ_OU_CD_0025: { return "0025"; }
			case LJ_OU_CD_0026: { return "0026"; }
			case LJ_OU_CD_0027: { return "0027"; }
			case LJ_OU_CD_0028: { return "0028"; }
			case LJ_OU_CD_0029: { return "0029"; }
			case LJ_OU_CD_0030: { return "0030"; }
			case LJ_OU_CD_0031: { return "0031"; }
			case LJ_OU_CD_0032: { return "0032"; }
			case LJ_OU_CD_0033: { return "0033"; }
			case LJ_OU_CD_0034: { return "0034"; }
			case LJ_OU_CD_0035: { return "0035"; }
			case LJ_OU_CD_0036: { return "0036"; }
			case LJ_OU_CD_0037: { return "0037"; }
			case LJ_OU_CD_0038: { return "0038"; }
			case LJ_OU_CD_0039: { return "0039"; }
			case LJ_OU_CD_0040: { return "0040"; }
			case LJ_OU_CD_0041: { return "0041"; }
			case LJ_OU_CD_0042: { return "0042"; }
			case LJ_OU_CD_0043: { return "0043"; }
			case LJ_OU_CD_0044: { return "0044"; }
			case LJ_OU_CD_0045: { return "0045"; }
			case LJ_OU_CD_0046: { return "0046"; }
			case LJ_OU_CD_0047: { return "0047"; }
			case LJ_OU_CD_0048: { return "0048"; }
			case LJ_OU_CD_0049: { return "0049"; }
			case LJ_OU_CD_0050: { return "0050"; }
			case LJ_OU_CD_0051: { return "0051"; }
			case LJ_OU_CD_0052: { return "0052"; }
			case LJ_OU_CD_0053: { return "0053"; }
			case LJ_OU_CD_0055: { return "0055"; }
			case LJ_OU_CD_0056: { return "0056"; }
			case LJ_OU_CD_0057: { return "0057"; }
			case LJ_OU_CD_0058: { return "0058"; }
			case LJ_OU_CD_0201: { return "0201"; }
			case LJ_OU_CD_0519: { return "0519"; }
			case LJ_OU_CD_0802: { return "0802"; }
			case LJ_OU_CD_0999: { return "0999"; }
			case LJ_OU_CD_CD01: { return "CD01"; }
			case LJ_OU_CD_LOJ1: { return "LOJ1"; }
			case LJ_OU_CD_XD01: { return "XD01"; }
			case LJ_OU_CD_ZAC1: { return "ZAC1"; }
			case LJ_OU_CD_ZAL1: { return "ZAL1"; }
			case LJ_OU_CD_ZAM1: { return "ZAM1"; }
			case LJ_OU_CD_ZAP1: { return "ZAP1"; }
			case LJ_OU_CD_ZBA1: { return "ZBA1"; }
			case LJ_OU_CD_ZCE1: { return "ZCE1"; }
			case LJ_OU_CD_ZDF1: { return "ZDF1"; }
			case LJ_OU_CD_ZES1: { return "ZES1"; }
			case LJ_OU_CD_ZGO1: { return "ZGO1"; }
			case LJ_OU_CD_ZMA1: { return "ZMA1"; }
			case LJ_OU_CD_ZMG1: { return "ZMG1"; }
			case LJ_OU_CD_ZMS1: { return "ZMS1"; }
			case LJ_OU_CD_ZMT1: { return "ZMT1"; }
			case LJ_OU_CD_ZPA1: { return "ZPA1"; }
			case LJ_OU_CD_ZPB1: { return "ZPB1"; }
			case LJ_OU_CD_ZPE1: { return "ZPE1"; }
			case LJ_OU_CD_ZPI1: { return "ZPI1"; }
			case LJ_OU_CD_ZPR1: { return "ZPR1"; }
			case LJ_OU_CD_ZRJ1: { return "ZRJ1"; }
			case LJ_OU_CD_ZRN1: { return "ZRN1"; }
			case LJ_OU_CD_ZRO1: { return "ZRO1"; }
			case LJ_OU_CD_ZRR1: { return "ZRR1"; }
			case LJ_OU_CD_ZRS1: { return "ZRS1"; }
			case LJ_OU_CD_ZSC1: { return "ZSC1"; }
			case LJ_OU_CD_ZSE1: { return "ZSE1"; }
			case LJ_OU_CD_ZSP1: { return "ZSP1"; }
			case LJ_OU_CD_ZTO1: { return "ZTO1"; }			
			}

			return "";
		}

	}

	/**
	 * 
	 * Tipos de depósitos cadastrados no sistema
	 * 
	 * @author Softtek - QA
	 *
	 */
	public static enum AUT_TIPO_DEPOSITO{
		DEPOSITO_C010,
		DEPOSITO_C050,
		DEPOSITO_C060,
		DEPOSITO_C070,
		DEPOSITO_C080,
		DEPOSITO_C081,
		DEPOSITO_C082,
		DEPOSITO_C090,
		DEPOSITO_C100,
		DEPOSITO_C101,
		DEPOSITO_C102,
		DEPOSITO_C103,
		DEPOSITO_C200,
		DEPOSITO_C500,
		DEPOSITO_C020,
		DEPOSITO_C030,
		DEPOSITO_C040;

		@Override
		public String toString() {
			switch(this) {
			case DEPOSITO_C010: { return "C010"; }
			case DEPOSITO_C050: { return "C050"; }
			case DEPOSITO_C060: { return "C060"; }
			case DEPOSITO_C070: { return "C070"; }
			case DEPOSITO_C080: { return "C080"; }
			case DEPOSITO_C081: { return "C081"; }
			case DEPOSITO_C082: { return "C082"; }
			case DEPOSITO_C090: { return "C090"; }
			case DEPOSITO_C100: { return "C100"; }
			case DEPOSITO_C101: { return "C101"; }
			case DEPOSITO_C102: { return "C102"; }
			case DEPOSITO_C103: { return "C103"; }
			case DEPOSITO_C200: { return "C200"; }
			case DEPOSITO_C500: { return "C500"; }
			case DEPOSITO_C020: { return "C020"; }
			case DEPOSITO_C030: { return "C030"; }
			case DEPOSITO_C040: { return "C040"; }			
			}

			return "";
		}

	}


	/**
	 * 
	 * Extrai e imprime de forma padronizada mensagens de erro geradas pelo sistema
	 * 
	 * @param e - Exceção que foi lançada pelo sistema
	 * @param docDriver - WebDriver
	 * 
	 */
	public static void autLogMsg(java.lang.Exception e,org.openqa.selenium.WebDriver docDriver) {
		System.out.println(String.format("AUT MSG LOG: %s",e.getMessage()));
		System.out.println(String.format("AUT Titulo pagina: %s\nAUT Conteudo da Pagina:\n\n",docDriver.getTitle()));			
		System.out.println(docDriver.getPageSource());
		e.printStackTrace();
	}

	/**
	 * 
	 * Retorna a relação de elementos que atendem ao critério definido pela expressão regular
	 * 
	 * @param tagElemento - Tag do elemento procurado
	 * @param expressaoRegular - Expressão regular para definição do critério de pesquisa
	 * 
	 * @return java.util.List<org.openqa.selenium.WebElement> - Elementos HTML que atendem ao critério
	 */
	public java.util.List<org.openqa.selenium.WebElement> carregarListaElementos(org.openqa.selenium.WebDriver docItem,String tagElemento,String expressaoRegular){
		
		java.util.List<org.openqa.selenium.WebElement> itensOut = new java.util.ArrayList<org.openqa.selenium.WebElement>();
		
		String docHTML = docItem.getPageSource();
		
		java.util.regex.Pattern padrao = java.util.regex.Pattern.compile(expressaoRegular);
		
		java.util.regex.Matcher analise = padrao.matcher(docHTML);
		
		System.out.println("AUT INFO : INICIANDO PESQUISA DE ELEMENTOS POR TAG + EXPRESSÃO REGULAR");
		
		while(analise.find()) {
			System.out.println("CORRESPONDENCIA TAG ENCONTRADA: ");
			
			System.out.println(analise.group());
		}
		
		return itensOut;
	}

	/**
	 * 
	 * Retorna um conjunto de possíveis valores
	 * 
	 * @param caracteres - Caractere separador de colunas
	 * 
	 * @return java.util.HashMap<Object,Object> - Parametros de saída
	 * 
	 */
	public static  java.util.HashMap<Integer,String> autSplitParameters(String expressaoRegularParaDivisaoColunas,String conteudoAnalise){
		java.util.HashMap<Integer,String> prmOut = new java.util.HashMap<Integer,String>();
		Integer indexRow = 0;
		java.util.regex.Pattern padrao = java.util.regex.Pattern.compile(expressaoRegularParaDivisaoColunas);
		java.util.regex.Matcher analise = padrao.matcher(conteudoAnalise);

		System.out.println("AUT LOADER PARAMS: INIT");

		while(analise.find()) {

			prmOut.put(indexRow, analise.group());

			System.out.println(analise.group());
			indexRow++;
		}


		System.out.println("AUT LOADER PARAMS: END");


		return prmOut;
	}


	/**
	 * Função responsável pela captura de tela para evidência dos testes
	 * 
	 * @param elemento - Elemento que implementa a interface de captura screen(TakesScreenshot)
	 * @param nomeStep - Nome do arquivo de imagem que será gerado
	 * 
	 * @return String - Caminho do arquivo de captura(screenshot)
	 */
	public static <TypeHTMLItem extends TakesScreenshot> String capturarEvidencia(TypeHTMLItem elemento,String nomeStep) {
		String outDir = "";
		String outDirDestino = "../va.testes.funcionais/Evidencias/".concat(nomeStep).concat(".png");

		CopyOption cpy;


		java.io.File outFile = elemento.getScreenshotAs(OutputType.FILE);
		System.out.println(String.format("COPIANDO EVIDENCIA: %s PARA: %s", outFile.getAbsolutePath(),outDirDestino));

		try {
			java.nio.file.Files.copy(Paths.get(outFile.getAbsolutePath()), Paths.get(outDirDestino),StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		return outDir;
	}
	/**
	 * 
	 * Envia dados para o elemento com um delay no input de dados
	 * 
	 * @param elemento - Elemento alvo para entrada de dados
	 * @param delayEntrada - Tempo de delay em (segundos)
	 * @param conteudo - Conteúdo de entrada
	 * 
	 */
	public static void enviarDadosElementWeb(org.openqa.selenium.WebElement elemento,long delayEntrada,String conteudo) {
		for(Character chr : conteudo.toCharArray()) {
			elemento.sendKeys(chr.toString());
			try {
				java.lang.Thread.currentThread().sleep(delayEntrada * 1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public static void enviarDadosElementWeb(String browserNome,org.openqa.selenium.WebDriver docItem,long delayEntrada,String conteudo) {
		if(browserNome.contains("chrome")) {
			ChromeDriver doc = (ChromeDriver) docItem;

			for(Character chr : conteudo.toCharArray()) {
				doc.getKeyboard().sendKeys(chr.toString());
				try {
					java.lang.Thread.currentThread().sleep(1000 * delayEntrada);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}			
		}
		else if(browserNome.contains("gecko")) {
			FirefoxDriver doc = (FirefoxDriver) docItem;

			for(Character chr : conteudo.toCharArray()) {
				doc.getKeyboard().sendKeys(chr.toString());
				try {
					java.lang.Thread.currentThread().sleep(1000 * delayEntrada);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		else if(browserNome.contains("ie")) {
			InternetExplorerDriver doc = (InternetExplorerDriver) docItem;

			for(Character chr : conteudo.toCharArray()) {
				doc.getKeyboard().sendKeys(chr.toString());
				try {
					java.lang.Thread.currentThread().sleep(1000 * delayEntrada);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}
		else if(browserNome.contains("opera")) {
			OperaDriver doc = (OperaDriver) docItem;

			for(Character chr : conteudo.toCharArray()) {
				doc.getKeyboard().sendKeys(chr.toString());
				try {
					java.lang.Thread.currentThread().sleep(1000 * delayEntrada);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}	
		}
	}


	/**
	 * 
	 * Envia dados para a tela com um delay especificado sem elemento associado
	 * 
	 * @param browserNome - Nome do browser alvo dos testes
	 * @param docItem - Webdriver
	 * @param delayEntrada - Delay no input de dados
	 * @param conteudo - Conteúdo de entrada do campo
	 * 
	 */
	public static java.util.List<org.openqa.selenium.WebElement> procurarElementWebHTML(String browserNome,org.openqa.selenium.WebDriver docItem,long delayEntrada,String tagElemento,String expressaoRegularPesquisa) {
		java.util.List<org.openqa.selenium.WebElement> itensOut = new java.util.ArrayList<org.openqa.selenium.WebElement>();

		if(browserNome.contains("chrome")) {
			ChromeDriver doc = (ChromeDriver) docItem;

			java.util.List<org.openqa.selenium.WebElement> ltItens = doc.findElementsByTagName(tagElemento);
			java.util.regex.Pattern padrao = java.util.regex.Pattern.compile(expressaoRegularPesquisa);
			java.util.regex.Matcher analise = null;
			for(org.openqa.selenium.WebElement item : ltItens) {

				String htmlItem = item.getAttribute("outerHTML");
				analise = padrao.matcher(htmlItem);

				while(analise.find()) {		
					System.out.println("AUT INFO : INPUT DATA : CORRESPONDENCIA ENCONTRADA NO ELEMENTO  : \n");
					System.out.println(analise.group());
					System.out.println("\n".concat(htmlItem));

					itensOut.add(item);

				}								
			}			
		}
		else if(browserNome.contains("gecko")) {
			FirefoxDriver doc = (FirefoxDriver) docItem;

			java.util.List<org.openqa.selenium.WebElement> ltItens = doc.findElementsByTagName(tagElemento);
			java.util.regex.Pattern padrao = java.util.regex.Pattern.compile(expressaoRegularPesquisa);
			java.util.regex.Matcher analise = null;
			for(org.openqa.selenium.WebElement item : ltItens) {

				String htmlItem = item.getAttribute("outerHTML");
				analise = padrao.matcher(htmlItem);

				while(analise.find()) {		
					System.out.println("AUT INFO : INPUT DATA : CORRESPONDENCIA ENCONTRADA NO ELEMENTO  : \n");
					System.out.println(analise.group());
					System.out.println("\n".concat(htmlItem));

					itensOut.add(item);

				}								
			}
		}
		else if(browserNome.contains("ie")) {
			InternetExplorerDriver doc = (InternetExplorerDriver) docItem;

			java.util.List<org.openqa.selenium.WebElement> ltItens = doc.findElementsByTagName(tagElemento);
			java.util.regex.Pattern padrao = java.util.regex.Pattern.compile(expressaoRegularPesquisa);
			java.util.regex.Matcher analise = null;
			for(org.openqa.selenium.WebElement item : ltItens) {

				String htmlItem = item.getAttribute("outerHTML");
				analise = padrao.matcher(htmlItem);

				while(analise.find()) {		
					System.out.println("AUT INFO : INPUT DATA : CORRESPONDENCIA ENCONTRADA NO ELEMENTO  : \n");
					System.out.println(analise.group());
					System.out.println("\n".concat(htmlItem));

					itensOut.add(item);

				}								
			}
		}
		else if(browserNome.contains("opera")) {
			OperaDriver doc = (OperaDriver) docItem;

			java.util.List<org.openqa.selenium.WebElement> ltItens = doc.findElementsByTagName(tagElemento);
			java.util.regex.Pattern padrao = java.util.regex.Pattern.compile(expressaoRegularPesquisa);
			java.util.regex.Matcher analise = null;
			for(org.openqa.selenium.WebElement item : ltItens) {

				String htmlItem = item.getAttribute("outerHTML");
				analise = padrao.matcher(htmlItem);

				while(analise.find()) {		
					System.out.println("AUT INFO : INPUT DATA : CORRESPONDENCIA ENCONTRADA NO ELEMENTO  : \n");
					System.out.println(analise.group());
					System.out.println("\n".concat(htmlItem));

					itensOut.add(item);

				}								
			}

		}

		return itensOut;		
	}


	/**
	 * 
	 * Envia dados para o elemento com (Tag) específica e que tenha o texto específico
	 * 
	 * @param browserNome - Nome do browser (webdriver), pode ser obtido com o método getName() da classe webdriver
	 * @param docItem - Webdriver
	 * @param tagElementoComTextoProcurado - Tag do elemento na qual o (textoElemento) será procurado
	 * @param numeroOcorrencia - Número de ocorrências a desconsiderar
	 * @param textoElemento - Texto do elemento procurado
	 * @param delayEntrada - Delay no input de dados do sistema
	 * @param conteudo - Conteúdo a ser enviado para tela
	 * 
	 */
	public static void enviarDadosElementWeb(String browserNome,org.openqa.selenium.WebDriver docItem,String tagElementoComTextoProcurado,Integer numeroOcorrencia,String textoElemento,long delayEntrada,String conteudo) {
		AUTVAUtilidades.executarMetodoElementoHTML(browserNome, docItem, tagElementoComTextoProcurado, "click", textoElemento, numeroOcorrencia);

		if(browserNome.contains("chrome")) {
			ChromeDriver doc = (ChromeDriver) docItem;

			for(Character chr : conteudo.toCharArray()) {
				doc.getKeyboard().sendKeys(chr.toString());
				try {
					java.lang.Thread.currentThread().sleep(1000 * delayEntrada);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}			
		}
		else if(browserNome.contains("gecko")) {
			FirefoxDriver doc = (FirefoxDriver) docItem;

			for(Character chr : conteudo.toCharArray()) {
				doc.getKeyboard().sendKeys(chr.toString());
				try {
					java.lang.Thread.currentThread().sleep(1000 * delayEntrada);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		else if(browserNome.contains("ie")) {
			InternetExplorerDriver doc = (InternetExplorerDriver) docItem;

			for(Character chr : conteudo.toCharArray()) {
				doc.getKeyboard().sendKeys(chr.toString());
				try {
					java.lang.Thread.currentThread().sleep(1000 * delayEntrada);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}
		else if(browserNome.contains("opera")) {
			OperaDriver doc = (OperaDriver) docItem;

			for(Character chr : conteudo.toCharArray()) {
				doc.getKeyboard().sendKeys(chr.toString());
				try {
					java.lang.Thread.currentThread().sleep(1000 * delayEntrada);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}	
		}
	}


	/**
	 * 
	 * Executa um método de um elemento HTML específico
	 * 
	 * @param browserNome - Nome do browser (use o método getName() da classe do objeto para retornar esse valor, para isso deve ser específicado o nome da versão webdriver diretamente Ex: ChromeDriver chr = new ChromeDriver())
	 * @param docItem - Webdriver
	 * @param tagElementoProcurado - Tag do elemento procurado
	 * @param metodoParaAcionar - Método que será chamado no elemento HTML (Exemplo: click)
	 * @param conteudoElementoProcurado - Conteúdo texto que o elemento deve possuir
	 * @param numeroOcorrenciasDoItem - Número de ocorrencias do item que devem ser descartadas
	 * 
	 */
	public static void executarMetodoElementoHTML(String browserNome,org.openqa.selenium.WebDriver docItem,String tagElementoProcurado,String metodoParaAcionar,String conteudoElementoProcurado,int numeroOcorrenciasDoItem) {
		try {

			String script = "try{cont=0;numOcorrencia=%s;contOcorrencias=0;tot=document.getElementsByTagName(\"%s\").length;itens=document.getElementsByTagName(\"%s\");output=\"\";while(cont<tot){console.log(itens[cont]);output+=itens[cont];cont++;strItem=itens[cont].innerHTML.toString();if(strItem.indexOf(\"%s\") >= 0){console.log(\"***** encontrado *****\");if(contOcorrencias==numOcorrencia){console.log(\"@@@**** ATIVANDO ITEM:\");itens[cont].%s();}contOcorrencias++}};console.log(output);}catch(exp){}";
			String scriptFormat = String.format(script, numeroOcorrenciasDoItem,tagElementoProcurado,tagElementoProcurado,conteudoElementoProcurado,metodoParaAcionar);

			if(browserNome.contains("chrome")) {
				ChromeDriver doc = (ChromeDriver) docItem;

				System.out.println("AUT INFO : EXECUTANDO SCRIPT JS : NAVEGADOR CHROME");

				doc.executeScript(scriptFormat);

			}
			else if(browserNome.contains("gecko")) {
				FirefoxDriver doc = (FirefoxDriver) docItem;

				System.out.println("AUT INFO : EXECUTANDO SCRIPT JS : NAVEGADOR FIREFOX (GECKO)");				
				doc.executeScript(scriptFormat);
			}
			else if(browserNome.contains("ie")) {
				InternetExplorerDriver doc = (InternetExplorerDriver) docItem;

				System.out.println("AUT INFO : EXECUTANDO SCRIPT JS : NAVEGADOR INTERNET EXPLORER");
				doc.executeScript(scriptFormat);	

			}
			else if(browserNome.contains("opera")) {
				OperaDriver doc = (OperaDriver) docItem;

				System.out.println("AUT INFO : EXECUTANDO SCRIPT JS : NAVEGADOR OPERA");				
				doc.executeScript(scriptFormat);	

			}

			System.out.println(scriptFormat);



		}
		catch(java.lang.Exception e) {
			System.out.println("AUT INFO : ERRO NA DEFINICAO DO METODO DE PESQUISA : JSCRIPT");
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * Função para sincronização de etapas no processo de automação
	 * 
	 * @param segundosTimeOut - Timeout em segundos para aguarda que o elemento esteja disponível
	 * @param docDriver - Webdriver
	 * @param textoExpressaoRegular - Expressão regular que define o resultado esperado para o passo em questão (padrão java)
	 * @return - True para sucesso false caso contrário
	 */
	public static boolean sincronizarStepPorTexto(Integer segundosTimeOut,org.openqa.selenium.WebDriver docDriver,String textoExpressaoRegular) {
		class AUTProcessCloseException extends java.lang.RuntimeException{
			public AUTProcessCloseException() {
				super();
			}
			public AUTProcessCloseException(String mensagem) {
				super(mensagem);
			}
		}
		try {
			AUTParametrosConfiguracao.DOC_DRIVER = docDriver;
			AUTParametrosConfiguracao.PESQUISAR_POR_TEXTO = true;
			AUTParametrosConfiguracao.RESULTADO_PESQUISA_POR_TEXTO_EXP_REGULAR = textoExpressaoRegular;
			AUTParametrosConfiguracao.TEMPO_EM_SEGUNDOS_PARA_PESQUISA_SER_CONCLUIDA = segundosTimeOut;
			java.lang.Runnable threadMonitor = new java.lang.Runnable() {
				public void run() {
					int contCiclos = 0;
					AUTParametrosConfiguracao.PESQUISA_FINALIZADA = false;

					if(AUTParametrosConfiguracao.PESQUISAR_POR_ELEMENTO) {
						System.out.println(String.format("AUT INFO : TIPO RESULTADO ESPERADO : POR OBJETO (ELEMENTO GUI) : TIMEOUT DEFINIDO PELO TESTADOR: %s",AUTParametrosConfiguracao.TEMPO_EM_SEGUNDOS_PARA_PESQUISA_SER_CONCLUIDA));					

					}
					if(AUTParametrosConfiguracao.PESQUISAR_POR_TEXTO){
						System.out.println(String.format("AUT INFO : TIPO RESULTADO ESPERADO : POR TEXTO (CODIGO FONTE) : TIMEOUT DEFINIDO PELO TESTADOR: %s",AUTParametrosConfiguracao.TEMPO_EM_SEGUNDOS_PARA_PESQUISA_SER_CONCLUIDA));
						System.out.println(String.format("AUT ANALISE FONTE : TEMPO TOTAL PREVISTO : %s : TEMPO ENTRE CICLOS: %s",AUTParametrosConfiguracao.TEMPO_EM_SEGUNDOS_PARA_PESQUISA_SER_CONCLUIDA,
								AUTParametrosConfiguracao.TEMPO_ENTRE_CICLOS_VERIFICACAO));
						java.util.regex.Pattern padrao = java.util.regex.Pattern.compile(AUTParametrosConfiguracao.RESULTADO_PESQUISA_POR_TEXTO_EXP_REGULAR);
						AUTParametrosConfiguracao.EVIDENCIAS_TEXTOS_ENCONTRADOS = new java.util.ArrayList<String>();
						while(contCiclos <= AUTParametrosConfiguracao.TEMPO_EM_SEGUNDOS_PARA_PESQUISA_SER_CONCLUIDA) {
							java.util.regex.Matcher analise = null;

							System.out.println(String.format("AUT INFO : TEMPO DE ANALISE: %s",contCiclos));

							String conteudo = AUTParametrosConfiguracao.DOC_DRIVER.getPageSource();
							if(conteudo!=null && !conteudo.isEmpty()) {								
								analise = padrao.matcher(conteudo);							
								while(analise.find()) {
									//System.out.println(String.format("AUT RESULTADO: %s : ENCONTRADO",analise.group()));
									AUTParametrosConfiguracao.RESULTADO_FINAL_PESQUISA_TEXTO = true;
									if(!AUTParametrosConfiguracao.EVIDENCIAS_TEXTOS_ENCONTRADOS.contains(analise.group())) {
										AUTParametrosConfiguracao.EVIDENCIAS_TEXTOS_ENCONTRADOS.add(analise.group());
										AUTParametrosConfiguracao.RESULTADO_FINAL_PESQUISA_TEXTO = true;
										AUTParametrosConfiguracao.PESQUISA_FINALIZADA = true;

										System.out.println("AUT STATUS FINAL PESQUISA POR TEXTO: SUCESSO : TEXTO ENCONTRADO");
										//System.out.println(String.format("TEXTO PROCURADO: %s TEXTO ENCONTRADO: %s", AUTParametrosConfiguracaoSincronizacao.RESULTADO_PESQUISA_POR_TEXTO_EXP_REGULAR));
										System.out.println(AUTParametrosConfiguracao.RESULTADO_PESQUISA_POR_TEXTO_EXP_REGULAR);
										System.out.println("\nOcorrências:\n");
										for(String item : AUTParametrosConfiguracao.EVIDENCIAS_TEXTOS_ENCONTRADOS) {
											System.out.println("@@ - ".concat(item));
										}
										throw new AUTProcessCloseException("@@@@@ FIM DA PESQUISA : ITEM ENCONTRADO");
									}
									System.out.println(AUTParametrosConfiguracao.EVIDENCIAS_TEXTOS_ENCONTRADOS.size());
								}

							}

							try {
								java.lang.Thread.currentThread().sleep(AUTParametrosConfiguracao.TEMPO_ENTRE_CICLOS_VERIFICACAO * 1000);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}

							contCiclos+= AUTParametrosConfiguracao.TEMPO_ENTRE_CICLOS_VERIFICACAO;
						}
						AUTParametrosConfiguracao.PESQUISA_FINALIZADA = true;
					}				
				}
			};
			threadMonitor.run();
			boolean bEnd = false;
			while(!bEnd) {
				bEnd = AUTParametrosConfiguracao.PESQUISA_FINALIZADA;
				if(bEnd) {
					if(AUTParametrosConfiguracao.RESULTADO_FINAL_PESQUISA_TEXTO) {
						System.out.println("AUT STATUS FINAL PESQUISA POR TEXTO: SUCESSO : TEXTO ENCONTRADO");
						//System.out.println(String.format("TEXTO PROCURADO: %s TEXTO ENCONTRADO: %s", AUTParametrosConfiguracaoSincronizacao.RESULTADO_PESQUISA_POR_TEXTO_EXP_REGULAR));
						System.out.println(AUTParametrosConfiguracao.RESULTADO_PESQUISA_POR_TEXTO_EXP_REGULAR);
						System.out.println("\nOcorrências:\n");
						for(String item : AUTParametrosConfiguracao.EVIDENCIAS_TEXTOS_ENCONTRADOS) {
							System.out.println("@@ - ".concat(item));
						}
						return true;
					}
					else {
						System.out.println("AUT STATUS FINAL PESQUISA POR TEXTO: FALHOU : TEXTO ENCONTRADO");

						return false;
					}
				}
				try {
					java.lang.Thread.currentThread().sleep(1500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			System.out.println("AUT STATUS FINAL PESQUISA POR TEXTO: FALHOU : TEXTO ENCONTRADO");		
			return false;	
		}
		catch(AUTProcessCloseException e) {
			System.out.println(e.getMessage());

			return true;
		}

	}


	/**
	 * 
	 * Retorna um elemento para manipulação com base no valor do atributo especificado
	 * 
	 * @param docItem - Webdriver
	 * @param tagElement - Tag do elemento 
	 * @param numOcorrencia - Número do total de ocorrências do elemento que deve ser descartada
	 * @param nomeAtributo - Nome do atributo HTML que será verificado
	 * @param conteudoPesquisa - Conteúdo que o atributo especificado deve conter
	 * @param output - Mensagem de retorno do processamento da função
	 * @return - WebElement que poderá ser manipulado
	 * 
	 */
	public static org.openqa.selenium.WebElement pesquisarObjeto(org.openqa.selenium.WebDriver docItem,String tagElement,int numOcorrencia,String nomeAtributo,String conteudoPesquisa,Object... output){
		java.util.List<org.openqa.selenium.WebElement> elementos = docItem.findElements(By.tagName(tagElement));
		String conteudoValidacao = "";
		System.out.println("HTML: PESQUISANDO ELEMENTO: ".concat(tagElement));
		java.util.regex.Pattern padrao = java.util.regex.Pattern.compile(conteudoPesquisa);
		java.util.regex.Matcher analise;
		int totalOcorrencias = 0;

		java.util.List<String> ltOutput = new java.util.ArrayList<String>();

		for(int cont = 0;cont < elementos.size();cont++) {			
			org.openqa.selenium.WebElement wbItem = elementos.get(cont);


			String conteudo = wbItem.getAttribute(nomeAtributo);
			try {
				analise = padrao.matcher(conteudo);
				String dtOut = "";
				while(analise.find()) {
					System.out.println("INFO HTML: ELEMENTO ENCONTRADO:");

					System.out.println(conteudo);
					ltOutput.add(conteudo);

					if(totalOcorrencias == numOcorrencia) {
						conteudoValidacao = conteudo;
						System.out.println(String.format("CONTEUDO PROCURADO: %s CONTEUDO: %s : NUM OCORRENCIA: %s", conteudoPesquisa,conteudo,totalOcorrencias));
						try {
							if(output.length > 0) {
								System.out.println(wbItem.getAttribute(output[0].toString()));
							}	
						}
						catch(java.lang.Exception e) {

						}
						return wbItem;
					}	

					if(numOcorrencia==-1) {	

						try {
							if(output.length > 0) {
								System.out.println(wbItem.getAttribute(output[0].toString()));
							}	
						}
						catch(java.lang.Exception e) {

						}

						return wbItem;
					}

					totalOcorrencias++;
				}	
			}
			catch(java.lang.Exception e) {

			}
		}

		System.out.println("HTML: PESQUISANDO ELEMENTO: FIM DA PESQUISA");
		System.out.println(String.format("HTML : ERROR PESQUISA: ELEMENTO COM O TEXTO: %s",conteudoPesquisa));
		System.out.println("\n\nHTML INFO : CONTEUDO DO DOCUMENTO GERADOR ERRO\n\n\n");
		System.out.println(docItem.getPageSource());

		return null;
	}



	/**
	 * Responsável por fazer o login no sistema VA
	 * 
	 * @param docDriver - WebDriver
	 * @param usuario - Usuário com acesso ao sistema
	 * @param senha - Senha do usuário
	 * @return - True (Sucesso no login) false caso contrário
	 * 
	 */
	public static boolean fazerLoginConfirmacao(AUTTestObject testeObject,org.openqa.selenium.WebDriver docDriver,String usuario,String senha) {
		try {

			boolean loginConfirmacao = AUTVAUtilidades.sincronizarStepPorTexto(10, docDriver, "(?i:<(div).{0,}\\W{0,}.{0,}class=.{0,}modal-enter(.{0,}\\W{0,}.{0,})reAuthentication.{0,}\\W{0,}.{0,}\\W{0,}.{0,}\\W{0,}Confirmação de Login.{0,})");


			if(loginConfirmacao) {

				AUTVAUtilidades.executarMetodoElementoHTML(testeObject.docDriver.getClass().getName(), testeObject.docDriver, "input", "click", usuario, 0);

				java.util.List<org.openqa.selenium.WebElement> inputUsr = AUTVAUtilidades.procurarElementWebHTML(docDriver.getClass().getName(), docDriver, (long)0.3, "input", "(?i:\\<(input).{0,}\\W{0,}name\\=.{0,}username.{0,}\\.{0,}\\>)");

				for(org.openqa.selenium.WebElement input : inputUsr) {
					try {
						//input.sendKeys(usuario);	
					}
					catch(java.lang.Exception e) 
					{

					}
				}

				java.util.List<org.openqa.selenium.WebElement> inputPWD = AUTVAUtilidades.procurarElementWebHTML(docDriver.getClass().getName(), docDriver, (long)0.3, "input", "(?i:\\<(input).{0,}\\W{0,}name\\=.{0,}password.{0,}\\.{0,}\\>)");

				for(org.openqa.selenium.WebElement pwd : inputPWD) {
					try {
						pwd.sendKeys(senha);	
					}
					catch(java.lang.Exception e) {

					}
				}

				java.util.List<org.openqa.selenium.WebElement> btsAvancar = AUTVAUtilidades.procurarElementWebHTML(docDriver.getClass().getName(), docDriver, (long)0.3, "button", "(?i:\\<(button).{0,}\\W{0,}.{0,}advancedReAutentication.{0,}\\W{0,}.{0,}\\>(.{0,}\\W{0,}.{0,})Avançar\\2{0,}\\<\\/\\1>)");

				for(org.openqa.selenium.WebElement bt : btsAvancar) {
					try {
						bt.click();	
					}
					catch(java.lang.Exception e) {

					}
				}


			}
			else {

			}
			return true;

		}
		catch(java.lang.Exception e) {

			System.out.println("AUT INFO ERROR : NAO FOI POSSIVEL FAZER LOGIN DE CONFIRMACAO");
			System.out.println(e.getMessage());
			e.printStackTrace();

			return false;
		}
	} 

	/**
	 * Responsável por fazer o login no sistema VA
	 * 
	 * @param docDriver - WebDriver
	 * @param usuario - Usuário com acesso ao sistema
	 * @param senha - Senha do usuário
	 * @return - True (Sucesso no login) false caso contrário
	 * 
	 */
	public static boolean fazerLogin(org.openqa.selenium.WebDriver docDriver,String usuario,String senha) {
		try {

			System.out.println("AUT INFO : FAZENDO LOGIN NO SISTEMA");
			docDriver.findElement(By.id("j_username")).sendKeys(usuario);
			docDriver.findElement(By.id("j_password")).sendKeys(senha);	

			docDriver.findElement(By.xpath("//*[@id=\"loginForm\"]/div[2]/button")).click();

			java.lang.Thread.sleep(5000);

			System.out.println(docDriver.getPageSource());

			System.out.println("AUT INFO : LOGIN FINALIZADO");
			return true;
		}
		catch(org.openqa.selenium.NoSuchElementException e) {

			System.out.println("AUT ERROR : ELEMENTO NAO ENCONTRADO NA PÁGINA");
			autLogMsg(e, docDriver);

			return false;
		}
		catch(java.lang.Exception e2) {

			System.out.println("AUT ERROR : DURANTE AUTOMACAO");
			autLogMsg(e2, docDriver);

			return false;
		}
	} 
}
