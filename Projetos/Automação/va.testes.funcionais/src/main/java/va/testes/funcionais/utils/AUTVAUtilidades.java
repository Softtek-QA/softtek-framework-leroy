package va.testes.funcionais.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

import va.testes.funcionais.runtime.*;
public class AUTVAUtilidades {

	public static void autLogMsg(java.lang.Exception e,org.openqa.selenium.WebDriver docDriver) {
		System.out.println(String.format("AUT MSG LOG: %s",e.getMessage()));
		System.out.println(String.format("AUT Titulo pagina: %s\nAUT Conteudo da Pagina:\n\n",docDriver.getTitle()));			
		System.out.println(docDriver.getPageSource());
		e.printStackTrace();
	}

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
			AUTParametrosConfiguracaoSincronizacao.DOC_DRIVER = docDriver;
			AUTParametrosConfiguracaoSincronizacao.PESQUISAR_POR_TEXTO = true;
			AUTParametrosConfiguracaoSincronizacao.RESULTADO_PESQUISA_POR_TEXTO_EXP_REGULAR = textoExpressaoRegular;
			AUTParametrosConfiguracaoSincronizacao.TEMPO_EM_SEGUNDOS_PARA_PESQUISA_SER_CONCLUIDA = segundosTimeOut;
			java.lang.Runnable threadMonitor = new java.lang.Runnable() {
				public void run() {
					int contCiclos = 0;
					AUTParametrosConfiguracaoSincronizacao.PESQUISA_FINALIZADA = false;

					if(AUTParametrosConfiguracaoSincronizacao.PESQUISAR_POR_ELEMENTO) {
						System.out.println(String.format("AUT INFO : TIPO RESULTADO ESPERADO : POR OBJETO (ELEMENTO GUI) : TIMEOUT DEFINIDO PELO TESTADOR: %s",AUTParametrosConfiguracaoSincronizacao.TEMPO_EM_SEGUNDOS_PARA_PESQUISA_SER_CONCLUIDA));					

					}
					if(AUTParametrosConfiguracaoSincronizacao.PESQUISAR_POR_TEXTO){
						System.out.println(String.format("AUT INFO : TIPO RESULTADO ESPERADO : POR TEXTO (CODIGO FONTE) : TIMEOUT DEFINIDO PELO TESTADOR: %s",AUTParametrosConfiguracaoSincronizacao.TEMPO_EM_SEGUNDOS_PARA_PESQUISA_SER_CONCLUIDA));
						System.out.println(String.format("AUT ANALISE FONTE : TEMPO TOTAL PREVISTO : %s : TEMPO ENTRE CICLOS: %s",AUTParametrosConfiguracaoSincronizacao.TEMPO_EM_SEGUNDOS_PARA_PESQUISA_SER_CONCLUIDA,
								AUTParametrosConfiguracaoSincronizacao.TEMPO_ENTRE_CICLOS_VERIFICACAO));
						java.util.regex.Pattern padrao = java.util.regex.Pattern.compile(AUTParametrosConfiguracaoSincronizacao.RESULTADO_PESQUISA_POR_TEXTO_EXP_REGULAR);
						AUTParametrosConfiguracaoSincronizacao.EVIDENCIAS_TEXTOS_ENCONTRADOS = new java.util.ArrayList<String>();
						while(contCiclos <= AUTParametrosConfiguracaoSincronizacao.TEMPO_EM_SEGUNDOS_PARA_PESQUISA_SER_CONCLUIDA) {
							java.util.regex.Matcher analise = null;

							System.out.println(String.format("AUT INFO : TEMPO DE ANALISE: %s",contCiclos));

							String conteudo = AUTParametrosConfiguracaoSincronizacao.DOC_DRIVER.getPageSource();
							if(conteudo!=null && !conteudo.isEmpty()) {								
								analise = padrao.matcher(conteudo);							
								while(analise.find()) {
									//System.out.println(String.format("AUT RESULTADO: %s : ENCONTRADO",analise.group()));
									AUTParametrosConfiguracaoSincronizacao.RESULTADO_FINAL_PESQUISA_TEXTO = true;
									if(!AUTParametrosConfiguracaoSincronizacao.EVIDENCIAS_TEXTOS_ENCONTRADOS.contains(analise.group())) {
										AUTParametrosConfiguracaoSincronizacao.EVIDENCIAS_TEXTOS_ENCONTRADOS.add(analise.group());
										AUTParametrosConfiguracaoSincronizacao.RESULTADO_FINAL_PESQUISA_TEXTO = true;
										AUTParametrosConfiguracaoSincronizacao.PESQUISA_FINALIZADA = true;

										System.out.println("AUT STATUS FINAL PESQUISA POR TEXTO: SUCESSO : TEXTO ENCONTRADO");
										//System.out.println(String.format("TEXTO PROCURADO: %s TEXTO ENCONTRADO: %s", AUTParametrosConfiguracaoSincronizacao.RESULTADO_PESQUISA_POR_TEXTO_EXP_REGULAR));
										System.out.println(AUTParametrosConfiguracaoSincronizacao.RESULTADO_PESQUISA_POR_TEXTO_EXP_REGULAR);
										System.out.println("\nOcorrências:\n");
										for(String item : AUTParametrosConfiguracaoSincronizacao.EVIDENCIAS_TEXTOS_ENCONTRADOS) {
											System.out.println("@@ - ".concat(item));
										}
										throw new AUTProcessCloseException("@@@@@ FIM DA PESQUISA : ITEM ENCONTRADO");
									}
									System.out.println(AUTParametrosConfiguracaoSincronizacao.EVIDENCIAS_TEXTOS_ENCONTRADOS.size());
								}

							}

							try {
								java.lang.Thread.currentThread().sleep(AUTParametrosConfiguracaoSincronizacao.TEMPO_ENTRE_CICLOS_VERIFICACAO * 1000);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}

							contCiclos+= AUTParametrosConfiguracaoSincronizacao.TEMPO_ENTRE_CICLOS_VERIFICACAO;
						}
						AUTParametrosConfiguracaoSincronizacao.PESQUISA_FINALIZADA = true;
					}				
				}
			};
			threadMonitor.run();
			boolean bEnd = false;
			while(!bEnd) {
				bEnd = AUTParametrosConfiguracaoSincronizacao.PESQUISA_FINALIZADA;
				if(bEnd) {
					if(AUTParametrosConfiguracaoSincronizacao.RESULTADO_FINAL_PESQUISA_TEXTO) {
						System.out.println("AUT STATUS FINAL PESQUISA POR TEXTO: SUCESSO : TEXTO ENCONTRADO");
						//System.out.println(String.format("TEXTO PROCURADO: %s TEXTO ENCONTRADO: %s", AUTParametrosConfiguracaoSincronizacao.RESULTADO_PESQUISA_POR_TEXTO_EXP_REGULAR));
						System.out.println(AUTParametrosConfiguracaoSincronizacao.RESULTADO_PESQUISA_POR_TEXTO_EXP_REGULAR);
						System.out.println("\nOcorrências:\n");
						for(String item : AUTParametrosConfiguracaoSincronizacao.EVIDENCIAS_TEXTOS_ENCONTRADOS) {
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

	public static org.openqa.selenium.WebElement pesquisarObjeto(org.openqa.selenium.WebDriver docItem,String tagElement,String expressaoRegular){
		
		java.util.List<org.openqa.selenium.WebElement> elementos = docItem.findElements(By.tagName(tagElement));
		String conteudoValidacao = "";
		System.out.println("HTML: PESQUISANDO ELEMENTO: ".concat(tagElement));
		java.util.regex.Pattern padrao = java.util.regex.Pattern.compile(expressaoRegular);
		java.util.regex.Matcher analise;
		int totalOcorrencias = 0;
		
		return null;
	}
	
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
