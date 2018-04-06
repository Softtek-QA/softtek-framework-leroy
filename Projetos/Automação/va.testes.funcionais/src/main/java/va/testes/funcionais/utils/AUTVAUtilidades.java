package va.testes.funcionais.utils;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
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
		RETIRADA,
		
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
		ENTREGA_EXPRESSA,
		ENTREGA_ECONOMICA
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
