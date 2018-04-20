package va.testes.funcionais.modulos.pedidos;

import static org.junit.Assert.*;

import org.junit.Test;
import org.openqa.selenium.Keys;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;

import va.testes.funcionais.modulos.clientes.AUTVAModuloCadastroClientesPF;
import va.testes.funcionais.utils.AUTTestObject;
import va.testes.funcionais.utils.AUTVAUtilidades;
import va.testes.funcionais.utils.AUTVAUtilidades.AUT_TIPO_DEPOSITO;
import va.testes.funcionais.utils.AUTVAUtilidades.AUT_TIPO_FLUXO_SAIDA;
import va.testes.funcionais.utils.AUTVAUtilidades.AUT_TIPO_LOJA;


public class AUTVAPedidos extends AUTTestObject{
	@Test
	public void test() {
		System.setProperty("webdriver.chrome.driver", "../va.testes.funcionais/src/main/resources/chromedriver.exe");
		ChromeOptions chrOptions = new ChromeOptions();

		System.out.println("AUT PEDIDOS: INICIANDO CRIACAO DE PEDIDOS:");
		System.out.println(AUTVAModuloCadastroClientesPF.MD_PARAMETROS_SAIDA);	

		String usuarioLogin = AUTVAModuloCadastroClientesPF.MD_PARAMETROS_ENTRADA.get("PARAM_USUARIO_VA").toString(); 
		String senhaLogin = AUTVAModuloCadastroClientesPF.MD_PARAMETROS_ENTRADA.get("PARAM_PWD_VA").toString();

		java.util.HashMap<Object,Object> clientesPedido = (java.util.HashMap<Object,Object>)AUTVAModuloCadastroClientesPF.MD_PARAMETROS_SAIDA.get("CLIENTES");

		String materialCodigoProdPesquisa = "88282446";
		String materialQuantProdPesquisa = "8";
		String materialCodigoProdPedido = "88282446|89455163|88521034|";
		String materialQuantPedido = "3|5|2|";
		String dataAgendaRetirada = "16/05/2018";
		AUT_TIPO_FLUXO_SAIDA materialTipoSaida = va.testes.funcionais.utils.AUTVAUtilidades.selecionarTipoFluxoSaidaRND(new AUT_TIPO_FLUXO_SAIDA[] {
				AUT_TIPO_FLUXO_SAIDA.CAIXA,
				AUT_TIPO_FLUXO_SAIDA.RETIRADA_EXTERNA_IMEDIATA,
				AUT_TIPO_FLUXO_SAIDA.RETIRADA_INTERNA_IMEDIATA
				});		
		String materialTipoSaidaAux = materialTipoSaida.toString();
		AUT_TIPO_LOJA tipoLojaFluxoSaida = AUT_TIPO_LOJA.LJ_OU_CD_0027;
		AUT_TIPO_DEPOSITO tipoDepositoFluxoSaida = AUT_TIPO_DEPOSITO.DEPOSITO_C010;	
		String dtAgendamentoFluxoSd = "18/04/2018";


		AUTVAUtilidades.executarMetodoElementoHTML(docDriver.getClass().getName(), docDriver, "a", "click", "carrinho", 0);

		AUTVAUtilidades.executarMetodoElementoHTML(docDriver.getClass().getName(), docDriver, "a", "click", "Iniciar novo atendimento", 0);


		AUTVAUtilidades.executarMetodoElementoHTML(docDriver.getClass().getName(), docDriver, "a", "click", "O que você está precisando?", 0);

		AUTVAUtilidades.enviarDadosElementWeb(docDriver.findElementById("search"), (long)0.4, materialCodigoProdPesquisa);

		AUTVAUtilidades.enviarDadosElementWeb(docDriver.findElementById("search"), (long)0.4, "\n");

		AUTVAUtilidades.executarMetodoElementoHTML(docDriver.getClass().getName(), docDriver, "a", "click", "Receber em casa", 0);

		AUTVAUtilidades.sincronizarStepPorTexto(30, docDriver, "(?i:\\<.{0,}\\>.{0,}\\W{0,}Meu Carrinho{0,}\\<.{0,}\\>)");

		java.util.List<org.openqa.selenium.WebElement> itensEncontrado = AUTVAUtilidades.procurarElementWebHTML(docDriver.getClass().getName(), docDriver, (long)0.3, "input", "\\W{0,}.{0,}(?i:\\<Input\\W{0,}.{0,}\\W{0,}field\\W{0,}.{0,}quantity\\W{0,}.{0,}\\W{0,}\\W{0,}\\>)");

		for(org.openqa.selenium.WebElement item : itensEncontrado) {
			item.clear();
			item.sendKeys(materialQuantProdPesquisa);
			item.sendKeys("\t");
		}

		boolean quantidadeInsuficiente = AUTVAUtilidades.sincronizarStepPorTexto(5, docDriver, "(?i:\\<(\\w+)\\>.{0,}\\W{0,}Quantidade insuficiente em estoque para o produto.{0,}\\W{0,}\\<\\/\\1>)");

		if(quantidadeInsuficiente) {
			System.out.println("AUT INFO TESTE : QUANTIDADE EM ESTOQUE DO ITEM É INSUFICIENTE PARA TRANSAÇÃO");

			java.util.List<org.openqa.selenium.WebElement> buttonsSubmit = AUTVAUtilidades.procurarElementWebHTML(docDriver.getClass().getName(), docDriver, (long)0.3, "button", "(?i:\\<(button).{0,}button\\-primary.{0,}\\>.{0,}\\W{0,}Ok.{0,}\\W{0,}\\<\\/\\1>)");

			for(org.openqa.selenium.WebElement item : buttonsSubmit) {
				item.click();
			}
		}
		else {
			System.out.println("AUT INFO TESTE : QUANTIDADE EM ESTOQUE DO ITEM É SUFICIENTE PARA ATENDER DEMANDA");
		}


		AUTVAUtilidades.procurarElementWebHTML(docDriver.getClass().getName(), docDriver, (long)0.3, "a", "(?i:<(\\w+).{0,}finalizar_topo.{0,}>\\W{0,}.{0,}Finalizar compra.{0,}\\W{0,}\\<\\/\\1.{0,})").get(0).click();

		AUTVAUtilidades.executarMetodoElementoHTML(docDriver.getClass().getName(), docDriver, "label", "click", "Digite aqui sua senha", 0);

		docDriver.findElementById("email").sendKeys(usuarioLogin);

		AUTVAUtilidades.executarMetodoElementoHTML(docDriver.getClass().getName(), docDriver, "label", "click", "Digite aqui sua senha", 0);

		docDriver.findElementById("password").sendKeys(senhaLogin);

		java.util.List<org.openqa.selenium.WebElement> buttonsEntrar = AUTVAUtilidades.procurarElementWebHTML(docDriver.getClass().getName(), docDriver, (long)0.3, "button", "(?i:\\<(button).{0,}\\>\\W{0,}.{0,}Entrar\\W{0,}.{0,})");

		for(org.openqa.selenium.WebElement bt : buttonsEntrar) {
			bt.click();
		}


		for(Object cliente : clientesPedido.values()) {

			AUTVAUtilidades.sincronizarStepPorTexto(20, docDriver, "\\<.{0,}\\>.{0,}\\W{0,}Principais acessos\\W{0,}.{0,}\\<.{0,}\\>");

			java.util.List<org.openqa.selenium.WebElement> botaoCarrinho = AUTVAUtilidades.procurarElementWebHTML(docDriver.getClass().getName(), docDriver, (long)0.3, "i", "(?i:\\<i.{0,}glyph glyph-cart.{0,}\\>)");

			for(org.openqa.selenium.WebElement bt : botaoCarrinho) {
				bt.click();	
			}

			AUTVAUtilidades.executarMetodoElementoHTML(docDriver.getClass().getName(), docDriver, "a", "click", "Iniciar novo atendimento", 0);

			java.util.HashMap<Integer,String> prods = AUTVAUtilidades.autSplitParameters("\\d+", materialCodigoProdPedido);
			java.util.HashMap<Integer,String> prodQuants = AUTVAUtilidades.autSplitParameters("\\d+", materialQuantPedido);

			int contProds = 0;
			for(String codProd : prods.values()) {
				AUTVAUtilidades.procurarElementWebHTML(docDriver.getClass().getName(), docDriver, (long)0.3, "input", "(?i:\\<(input).{0,}\\W{0,}type\\=.{0,}text.{0,}\\=.{0,}qty.{0,}\\>)").get(0).sendKeys(prodQuants.get(contProds));
				AUTVAUtilidades.procurarElementWebHTML(docDriver.getClass().getName(), docDriver, (long)0.3, "input", "(?i:\\<(input).{0,}\\W{0,}\\=\\\"eanOrCode\\\".{0,}\\>)").get(0).sendKeys(codProd);
				AUTVAUtilidades.procurarElementWebHTML(docDriver.getClass().getName(), docDriver, (long)0.3, "input", "(?i:\\<(input).{0,}\\W{0,}\\=\\\"eanOrCode\\\".{0,}\\>)").get(0).sendKeys(Keys.ENTER);	
				contProds++;
			}


			AUTVAUtilidades.executarMetodoElementoHTML(docDriver.getClass().getName(), docDriver, "button", "click", "Converter para Pedido", 0);

			AUTVAUtilidades.fazerLoginConfirmacao(this,docDriver, usuarioLogin, senhaLogin);



			AUTVAUtilidades.sincronizarStepPorTexto(20, docDriver, "\\<.{0,}\\>.{0,}\\W{0,}Busque um cliente cadastrado\\W{0,}.{0,}\\<.{0,}\\>");




			java.util.List<org.openqa.selenium.WebElement> inputCliente = AUTVAUtilidades.procurarElementWebHTML(docDriver.getClass().getName(), docDriver, (long)0.3, "input", "\\<(input).{0,}.{0,}name=\\\"document\\\".{0,}\\>");

			for(org.openqa.selenium.WebElement input : inputCliente) {
				try {
					input.sendKeys(cliente.toString());
					input.sendKeys("\n");			

					AUTVAUtilidades.sincronizarStepPorTexto(20, docDriver,String.format("\\<.{0,}\\>.{0,}\\W{0,}%s\\W{0,}.{0,}\\<.{0,}\\>",cliente.toString()));


					java.util.List<org.openqa.selenium.WebElement> opcoesCli = AUTVAUtilidades.procurarElementWebHTML(docDriver.getClass().getName(), docDriver, (long)0.3, "span", String.format("(?i:\\<(span).{0,}\\W{0,}\\>%s\\<\\/\\1>)",cliente.toString()));
					for(org.openqa.selenium.WebElement opCli : opcoesCli) {
						try {
							opCli.click();	
						}
						catch(java.lang.Exception e) {

						}
					}

					AUTVAUtilidades.executarMetodoElementoHTML(docDriver.getClass().getName(), docDriver, "button", "click", "Avançar", 0);

					AUTVAUtilidades.sincronizarStepPorTexto(20, docDriver, "\\<.{0,}\\>.{0,}\\W{0,}LM:\\W{0,}.{0,}\\<.{0,}\\>");					

					AUTVAUtilidades.executarMetodoElementoHTML(docDriver.getClass().getName(), docDriver, "button", "click", "Avançar", 0);

					AUTVAUtilidades.sincronizarStepPorTexto(20, docDriver, "\\<.{0,}\\>.{0,}\\W{0,}Fluxo de Saída geral\\W{0,}.{0,}\\<.{0,}\\>");					

					AUTVAUtilidades.sincronizarStepPorTexto(20, docDriver, "\\<.{0,}\\>.{0,}\\W{0,}Fluxo de Saída geral\\W{0,}.{0,}\\<.{0,}\\>");					

					AUTVAUtilidades.sincronizarStepPorTexto(20, docDriver, "(?i:\\<.{0,}\\>.{0,}\\W{0,}Caixa\\W{0,}.{0,}\\<.{0,}\\>)");					

					java.lang.Thread.currentThread().sleep(3000);

					switch(materialTipoSaida) {
					case CAIXA:{

						docDriver.findElementById("caixa").click();

						break;
					}
					case ENTREGA:{

						docDriver.findElementById("entrega").click();

						break;
					}
					case ENTREGA_ECONOMICA:{

						System.out.println("AUT INFO : FUNCAO NAO IMPLEMENTADA :".concat(AUT_TIPO_FLUXO_SAIDA.ENTREGA_ECONOMICA.toString()));

						break;
					}
					case RETIRADA_INTERNA_AGENDADA:{
						java.util.List<org.openqa.selenium.WebElement> listaOpcoesFluxoSaida = AUTVAUtilidades.procurarElementWebHTML(docDriver.getClass().getName(), docDriver, (long)0.3, "select", String.format("(?i:\\<(select).{0,}id=.{0,}withdrawalOptionType\\-{0,}\\d+.{0,}\\>)",cliente.toString()));

						for(org.openqa.selenium.WebElement itemFluxoSd : listaOpcoesFluxoSaida) {
							try {
								org.openqa.selenium.support.ui.Select selectItem = new Select(itemFluxoSd);

								selectItem.selectByValue(materialTipoSaidaAux);

								itemFluxoSd.sendKeys(Keys.TAB);
							}
							catch(java.lang.Exception e) {
								System.err.println("AUT ERROR : SELECT ITEM");
								System.err.println(e.getMessage());
								e.printStackTrace();
							}
						}
						break;
					}
					case RETIRADA_EXTERNA_AGENDADA:{
						java.util.List<org.openqa.selenium.WebElement> listaOpcoesFluxoSaida = AUTVAUtilidades.procurarElementWebHTML(docDriver.getClass().getName(), docDriver, (long)0.3, "select", String.format("(?i:\\<(select).{0,}id=.{0,}withdrawalOptionType\\-{0,}\\d+.{0,}\\>)",cliente.toString()));

						for(org.openqa.selenium.WebElement itemFluxoSd : listaOpcoesFluxoSaida) {
							try {
								org.openqa.selenium.support.ui.Select selectItem = new Select(itemFluxoSd);

								selectItem.selectByValue(materialTipoSaidaAux);

								itemFluxoSd.sendKeys(Keys.TAB);
							}
							catch(java.lang.Exception e) {
								System.err.println("AUT ERROR : SELECT ITEM");
								System.err.println(e.getMessage());
								e.printStackTrace();
							}
						}

						break;
					}
					case RETIRADA_EXTERNA_IMEDIATA:{

						java.util.List<org.openqa.selenium.WebElement> listaOpcoesFluxoSaida = AUTVAUtilidades.procurarElementWebHTML(docDriver.getClass().getName(), docDriver, (long)0.3, "select", String.format("(?i:\\<(select).{0,}id=.{0,}withdrawalOptionType\\-{0,}\\d+.{0,}\\>)",cliente.toString()));

						for(org.openqa.selenium.WebElement itemFluxoSd : listaOpcoesFluxoSaida) {
							try {
								org.openqa.selenium.support.ui.Select selectItem = new Select(itemFluxoSd);

								selectItem.selectByValue(materialTipoSaidaAux);

								itemFluxoSd.sendKeys(Keys.TAB);
							}
							catch(java.lang.Exception e) {
								System.err.println("AUT ERROR : SELECT ITEM");
								System.err.println(e.getMessage());
								e.printStackTrace();
							}
						}	

						break;
					}
					case RETIRADA_INTERNA_IMEDIATA:{

						java.util.List<org.openqa.selenium.WebElement> listaOpcoesFluxoSaida = AUTVAUtilidades.procurarElementWebHTML(docDriver.getClass().getName(), docDriver, (long)0.3, "select", String.format("(?i:\\<(select).{0,}id=.{0,}withdrawalOptionType\\-{0,}\\d+.{0,}\\>)",cliente.toString()));

						for(org.openqa.selenium.WebElement itemFluxoSd : listaOpcoesFluxoSaida) {
							try {
								org.openqa.selenium.support.ui.Select selectItem = new Select(itemFluxoSd);

								selectItem.selectByValue(materialTipoSaidaAux);

								itemFluxoSd.sendKeys(Keys.TAB);
							}
							catch(java.lang.Exception e) {
								System.err.println("AUT ERROR : SELECT ITEM");
								System.err.println(e.getMessage());
								e.printStackTrace();
							}
						}	

						break;
					}
					}

					java.util.List<org.openqa.selenium.WebElement> listaOpcoesFlxSdLojas = AUTVAUtilidades.procurarElementWebHTML(docDriver.getClass().getName(), docDriver, (long)0.3, "select", String.format("(?i:\\<(select).{0,}id=.{0,}withdrawalOptionDeposit\\-{0,}\\d+.{0,}\\>)",cliente.toString()));

					for(org.openqa.selenium.WebElement itemFlxSd : listaOpcoesFlxSdLojas) {
						try {
							org.openqa.selenium.support.ui.Select selectItem = new Select(itemFlxSd);

							selectItem.selectByValue(tipoLojaFluxoSaida.toString());

							itemFlxSd.sendKeys(Keys.TAB);
						}
						catch(java.lang.Exception e) {
							System.err.println("AUT ERROR : SELECT ITEM");
							System.err.println(e.getMessage());
							e.printStackTrace();
						}
					}


					java.util.List<org.openqa.selenium.WebElement> listaOpcoesFlxSdDeposito = AUTVAUtilidades.procurarElementWebHTML(docDriver.getClass().getName(), docDriver, (long)0.3, "select", String.format("(?i:\\<(select).{0,}id=.{0,}withdrawalOptionWarehouse\\-{0,}\\d+.{0,}\\>)",cliente.toString()));

					for(org.openqa.selenium.WebElement itemFlxSd : listaOpcoesFlxSdDeposito) {
						try {

							org.openqa.selenium.support.ui.Select selectItem = new Select(itemFlxSd);


							selectItem.selectByValue(tipoDepositoFluxoSaida.toString().concat("_").concat(tipoLojaFluxoSaida.toString()));
							itemFlxSd.sendKeys(Keys.TAB);


						}
						catch(java.lang.Exception e) {
							System.err.println("AUT ERROR : SELECT ITEM");
							System.err.println(e.getMessage());
							e.printStackTrace();
						}
					}

					/**
					java.util.List<org.openqa.selenium.WebElement> listaOpcoesCmpDtAgendaFlxSd = AUTVAUtilidades.procurarElementWebHTML(docDriver.getClass().getName(), docDriver, (long)0.3, "input", String.format("(?i:\\<input.{0,}id=.{0,}withdrawal-option-date\\-\\d+)",cliente.toString()));					

					for(org.openqa.selenium.WebElement cmpDt : listaOpcoesCmpDtAgendaFlxSd) {
						cmpDt.click();
					}

					java.util.List<org.openqa.selenium.WebElement> listaOpcoesDtAgendaFlxSd = docDriver.findElementsByTagName("span");					
					for(org.openqa.selenium.WebElement itemFlxSd : listaOpcoesDtAgendaFlxSd) {
						try {							
							
							String atributo = itemFlxSd.getAttribute("class");
							if(!atributo.equals(null) && atributo.equals("dayContainer"))
							{
								System.out.println("AUT INFO : CONTAINER DE DIGITOS DATA ENCONTRADO");
								
								System.out.println(atributo);
								System.out.println(itemFlxSd.getAttribute("style"));
								System.out.println(itemFlxSd.getAttribute("outerHTML"));
							}
						}
						catch(java.lang.Exception e) {
							System.err.println("AUT ERROR : SELECT ITEM");
							System.err.println(e.getMessage());
							e.printStackTrace();
						}
					}			
**/
					AUTVAUtilidades.executarMetodoElementoHTML(docDriver.getClass().getName(), docDriver, "button", "click", "Avançar", 0);

					AUTVAUtilidades.sincronizarStepPorTexto(20, docDriver, "\\<.{0,}\\>.{0,}\\W{0,}Adicionar meio de pagamento\\W{0,}.{0,}\\<.{0,}\\>");					

					AUTVAUtilidades.executarMetodoElementoHTML(docDriver.getClass().getName(), docDriver, "button", "click", "Avançar", 0);

					AUTVAUtilidades.sincronizarStepPorTexto(20, docDriver, "\\<.{0,}\\>.{0,}\\W{0,}Resumo\\W{0,}.{0,}\\<.{0,}\\>");					


					AUTVAUtilidades.sincronizarStepPorTexto(20, docDriver, "\\<.{0,}\\>.{0,}\\W{0,}Endereço Cobrança\\W{0,}.{0,}\\<.{0,}\\>");					


					AUTVAUtilidades.executarMetodoElementoHTML(docDriver.getClass().getName(), docDriver, "button", "click", "Finalizar", 0);


					AUTVAUtilidades.sincronizarStepPorTexto(20, docDriver, "\\<.{0,}\\>.{0,}\\W{0,}Identificação do cartão pedido\\W{0,}.{0,}\\<.{0,}\\>");					

					docDriver.getKeyboard().sendKeys(Keys.ESCAPE);

					AUTVAUtilidades.sincronizarStepPorTexto(20, docDriver, "(?i:\\<.{0,}\\>.{0,}\\W{0,}Pedido.{0,}\\W\\d+.{0,})");					
					String docOutCheckpoint = "CLIENTE : ".concat(cliente.toString().concat(" : CHECKPOINT-NUMERO DO PEDIDO"));
					docOutCheckpoint = docOutCheckpoint.replaceAll("\\W", "-");
					AUTVAUtilidades.capturarEvidencia(docDriver, docOutCheckpoint);


					//Reiniciando fluxo para geração de pedido

					java.util.List<org.openqa.selenium.WebElement> botaoCarrinho2 = AUTVAUtilidades.procurarElementWebHTML(docDriver.getClass().getName(), docDriver, (long)0.3, "i", "(?i:\\<i.{0,}glyph glyph-cart.{0,}\\>)");

					for(org.openqa.selenium.WebElement bt : botaoCarrinho2) {
						bt.click();	
					}

					AUTVAUtilidades.executarMetodoElementoHTML(docDriver.getClass().getName(), docDriver, "a", "click", "Iniciar novo atendimento", 0);

				}
				catch(java.lang.Exception e) {

				}

			}			

		}
	}
}
