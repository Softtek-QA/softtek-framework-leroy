package va.testes.funcionais.modulos.pedidos;

import static org.junit.Assert.*;

import org.junit.Test;
import org.openqa.selenium.Keys;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import va.testes.funcionais.modulos.clientes.AUTVAModuloCadastroClientesPF_HOMOLOG_1;
import va.testes.funcionais.utils.AUTVAUtilidades_LD;

public class AUTVAPedidos_HOMOLOG_1_LD {
	@Test
	public void test() {
		System.setProperty("webdriver.chrome.driver", "../va.testes.funcionais/src/main/resources/chromedriver.exe");
		ChromeOptions chrOptions = new ChromeOptions();

		System.out.println("AUT PEDIDOS: INICIANDO CRIACAO DE PEDIDOS:");
		System.out.println(AUTVAModuloCadastroClientesPF_HOMOLOG_1.MD_PARAMETROS_SAIDA);	

		String usuarioLogin = AUTVAModuloCadastroClientesPF_HOMOLOG_1.MD_PARAMETROS_ENTRADA.get("PARAM_USUARIO_VA").toString(); 
		String senhaLogin = AUTVAModuloCadastroClientesPF_HOMOLOG_1.MD_PARAMETROS_ENTRADA.get("PARAM_PWD_VA").toString();

		java.util.HashMap<Object,Object> clientesPedido = (java.util.HashMap<Object,Object>)AUTVAModuloCadastroClientesPF_HOMOLOG_1.MD_PARAMETROS_SAIDA.get("CLIENTES");
		
		String materialCodigoProdPesquisa = "88282446";
		String materialQuantProdPesquisa = "8";
		String materialCodigoProdPedido = "88282446|89455163|88521034|";
		String materialQuantPedido = "3|5|2|";		

		
		AUTVAUtilidades_LD.executarMetodoElementoHTML(AUTVAModuloCadastroClientesPF_HOMOLOG_1.docDriver.getClass().getName(), AUTVAModuloCadastroClientesPF_HOMOLOG_1.docDriver, "a", "click", "carrinho", 0);

		AUTVAUtilidades_LD.executarMetodoElementoHTML(AUTVAModuloCadastroClientesPF_HOMOLOG_1.docDriver.getClass().getName(), AUTVAModuloCadastroClientesPF_HOMOLOG_1.docDriver, "a", "click", "Iniciar novo atendimento", 0);


		AUTVAUtilidades_LD.executarMetodoElementoHTML(AUTVAModuloCadastroClientesPF_HOMOLOG_1.docDriver.getClass().getName(), AUTVAModuloCadastroClientesPF_HOMOLOG_1.docDriver, "a", "click", "O que você está precisando?", 0);

		AUTVAUtilidades_LD.enviarDadosElementWeb(AUTVAModuloCadastroClientesPF_HOMOLOG_1.docDriver.findElementById("search"), (long)0.4, materialCodigoProdPesquisa);

		AUTVAUtilidades_LD.enviarDadosElementWeb(AUTVAModuloCadastroClientesPF_HOMOLOG_1.docDriver.findElementById("search"), (long)0.4, "\n");

		AUTVAUtilidades_LD.executarMetodoElementoHTML(AUTVAModuloCadastroClientesPF_HOMOLOG_1.docDriver.getClass().getName(), AUTVAModuloCadastroClientesPF_HOMOLOG_1.docDriver, "a", "click", "Receber em casa", 0);

		AUTVAUtilidades_LD.sincronizarStepPorTexto(30, AUTVAModuloCadastroClientesPF_HOMOLOG_1.docDriver, "(?i:\\<.{0,}\\>.{0,}\\W{0,}Meu Carrinho{0,}\\<.{0,}\\>)");

		java.util.List<org.openqa.selenium.WebElement> itensEncontrado = AUTVAUtilidades_LD.procurarElementWebHTML(AUTVAModuloCadastroClientesPF_HOMOLOG_1.docDriver.getClass().getName(), AUTVAModuloCadastroClientesPF_HOMOLOG_1.docDriver, (long)0.3, "input", "\\W{0,}.{0,}(?i:\\<Input\\W{0,}.{0,}\\W{0,}field\\W{0,}.{0,}quantity\\W{0,}.{0,}\\W{0,}\\W{0,}\\>)");

		for(org.openqa.selenium.WebElement item : itensEncontrado) {
			item.clear();
			item.sendKeys(materialQuantProdPesquisa);
			item.sendKeys("\t");
		}

		boolean quantidadeInsuficiente = AUTVAUtilidades_LD.sincronizarStepPorTexto(5, AUTVAModuloCadastroClientesPF_HOMOLOG_1.docDriver, "(?i:\\<(\\w+)\\>.{0,}\\W{0,}Quantidade insuficiente em estoque para o produto.{0,}\\W{0,}\\<\\/\\1>)");

		if(quantidadeInsuficiente) {
			System.out.println("AUT INFO TESTE : QUANTIDADE EM ESTOQUE DO ITEM É INSUFICIENTE PARA TRANSAÇÃO");

			java.util.List<org.openqa.selenium.WebElement> buttonsSubmit = AUTVAUtilidades_LD.procurarElementWebHTML(AUTVAModuloCadastroClientesPF_HOMOLOG_1.docDriver.getClass().getName(), AUTVAModuloCadastroClientesPF_HOMOLOG_1.docDriver, (long)0.3, "button", "(?i:\\<(button).{0,}button\\-primary.{0,}\\>.{0,}\\W{0,}Ok.{0,}\\W{0,}\\<\\/\\1>)");

			for(org.openqa.selenium.WebElement item : buttonsSubmit) {
				item.click();
			}
		}
		else {
			System.out.println("AUT INFO TESTE : QUANTIDADE EM ESTOQUE DO ITEM É SUFICIENTE PARA ATENDER DEMANDA");
		}


		AUTVAUtilidades_LD.procurarElementWebHTML(AUTVAModuloCadastroClientesPF_HOMOLOG_1.docDriver.getClass().getName(), AUTVAModuloCadastroClientesPF_HOMOLOG_1.docDriver, (long)0.3, "a", "(?i:<(\\w+).{0,}finalizar_topo.{0,}>\\W{0,}.{0,}Finalizar compra.{0,}\\W{0,}\\<\\/\\1.{0,})").get(0).click();

		AUTVAUtilidades_LD.executarMetodoElementoHTML(AUTVAModuloCadastroClientesPF_HOMOLOG_1.docDriver.getClass().getName(), AUTVAModuloCadastroClientesPF_HOMOLOG_1.docDriver, "label", "click", "Digite aqui sua senha", 0);

		AUTVAModuloCadastroClientesPF_HOMOLOG_1.docDriver.findElementById("email").sendKeys(usuarioLogin);

		AUTVAUtilidades_LD.executarMetodoElementoHTML(AUTVAModuloCadastroClientesPF_HOMOLOG_1.docDriver.getClass().getName(), AUTVAModuloCadastroClientesPF_HOMOLOG_1.docDriver, "label", "click", "Digite aqui sua senha", 0);

		AUTVAModuloCadastroClientesPF_HOMOLOG_1.docDriver.findElementById("password").sendKeys(senhaLogin);

		java.util.List<org.openqa.selenium.WebElement> buttonsEntrar = AUTVAUtilidades_LD.procurarElementWebHTML(AUTVAModuloCadastroClientesPF_HOMOLOG_1.docDriver.getClass().getName(), AUTVAModuloCadastroClientesPF_HOMOLOG_1.docDriver, (long)0.3, "button", "(?i:\\<(button).{0,}\\>\\W{0,}.{0,}Entrar\\W{0,}.{0,})");

		for(org.openqa.selenium.WebElement bt : buttonsEntrar) {
			bt.click();
		}

		AUTVAUtilidades_LD.sincronizarStepPorTexto(20, AUTVAModuloCadastroClientesPF_HOMOLOG_1.docDriver, "\\<.{0,}\\>.{0,}\\W{0,}Principais acessos\\W{0,}.{0,}\\<.{0,}\\>");

		java.util.List<org.openqa.selenium.WebElement> botaoCarrinho = AUTVAUtilidades_LD.procurarElementWebHTML(AUTVAModuloCadastroClientesPF_HOMOLOG_1.docDriver.getClass().getName(), AUTVAModuloCadastroClientesPF_HOMOLOG_1.docDriver, (long)0.3, "i", "(?i:\\<i.{0,}glyph glyph-cart.{0,}\\>)");

		for(org.openqa.selenium.WebElement bt : botaoCarrinho) {
			bt.click();	
		}

		AUTVAUtilidades_LD.executarMetodoElementoHTML(AUTVAModuloCadastroClientesPF_HOMOLOG_1.docDriver.getClass().getName(), AUTVAModuloCadastroClientesPF_HOMOLOG_1.docDriver, "a", "click", "Iniciar novo atendimento", 0);

		java.util.HashMap<Integer,String> prods = AUTVAUtilidades_LD.autSplitParameters("\\d+", materialCodigoProdPedido);
		java.util.HashMap<Integer,String> prodQuants = AUTVAUtilidades_LD.autSplitParameters("\\d+", materialQuantPedido);

		int contProds = 0;
		for(String codProd : prods.values()) {
			AUTVAUtilidades_LD.procurarElementWebHTML(AUTVAModuloCadastroClientesPF_HOMOLOG_1.docDriver.getClass().getName(), AUTVAModuloCadastroClientesPF_HOMOLOG_1.docDriver, (long)0.3, "input", "(?i:\\<(input).{0,}\\W{0,}type\\=.{0,}text.{0,}\\=.{0,}qty.{0,}\\>)").get(0).sendKeys(prodQuants.get(contProds));
			AUTVAUtilidades_LD.procurarElementWebHTML(AUTVAModuloCadastroClientesPF_HOMOLOG_1.docDriver.getClass().getName(), AUTVAModuloCadastroClientesPF_HOMOLOG_1.docDriver, (long)0.3, "input", "(?i:\\<(input).{0,}\\W{0,}\\=\\\"eanOrCode\\\".{0,}\\>)").get(0).sendKeys(codProd);
			AUTVAUtilidades_LD.procurarElementWebHTML(AUTVAModuloCadastroClientesPF_HOMOLOG_1.docDriver.getClass().getName(), AUTVAModuloCadastroClientesPF_HOMOLOG_1.docDriver, (long)0.3, "input", "(?i:\\<(input).{0,}\\W{0,}\\=\\\"eanOrCode\\\".{0,}\\>)").get(0).sendKeys(Keys.ENTER);	
			contProds++;
		}
		
		
		AUTVAUtilidades_LD.executarMetodoElementoHTML(AUTVAModuloCadastroClientesPF_HOMOLOG_1.docDriver.getClass().getName(), AUTVAModuloCadastroClientesPF_HOMOLOG_1.docDriver, "button", "click", "Converter para Pedido", 0);
		
		AUTVAUtilidades_LD.fazerLoginConfirmacao(AUTVAModuloCadastroClientesPF_HOMOLOG_1.docDriver, usuarioLogin, senhaLogin);
		
		

		AUTVAUtilidades_LD.sincronizarStepPorTexto(20, AUTVAModuloCadastroClientesPF_HOMOLOG_1.docDriver, "\\<.{0,}\\>.{0,}\\W{0,}Busque um cliente cadastrado\\W{0,}.{0,}\\<.{0,}\\>");

        
		for(Object cliente : clientesPedido.values()) {
			
			java.util.List<org.openqa.selenium.WebElement> inputCliente = AUTVAUtilidades_LD.procurarElementWebHTML(AUTVAModuloCadastroClientesPF_HOMOLOG_1.docDriver.getClass().getName(), AUTVAModuloCadastroClientesPF_HOMOLOG_1.docDriver, (long)0.3, "input", "\\<(input).{0,}.{0,}name=\\\"document\\\".{0,}\\>");

			for(org.openqa.selenium.WebElement input : inputCliente) {
				try {
					input.sendKeys(cliente.toString());
					input.sendKeys("\n");			

					AUTVAUtilidades_LD.sincronizarStepPorTexto(20, AUTVAModuloCadastroClientesPF_HOMOLOG_1.docDriver,String.format("\\<.{0,}\\>.{0,}\\W{0,}%s\\W{0,}.{0,}\\<.{0,}\\>",cliente.toString()));
					
					
					java.util.List<org.openqa.selenium.WebElement> opcoesCli = AUTVAUtilidades_LD.procurarElementWebHTML(AUTVAModuloCadastroClientesPF_HOMOLOG_1.docDriver.getClass().getName(), AUTVAModuloCadastroClientesPF_HOMOLOG_1.docDriver, (long)0.3, "span", String.format("(?i:\\<(span).{0,}\\W{0,}\\>%s\\<\\/\\1>)",cliente.toString()));
					for(org.openqa.selenium.WebElement opCli : opcoesCli) {
						try {
							opCli.click();	
						}
						catch(java.lang.Exception e) {
							
						}
					}

					AUTVAUtilidades_LD.executarMetodoElementoHTML(AUTVAModuloCadastroClientesPF_HOMOLOG_1.docDriver.getClass().getName(), AUTVAModuloCadastroClientesPF_HOMOLOG_1.docDriver, "button", "click", "Avançar", 0);

					AUTVAUtilidades_LD.sincronizarStepPorTexto(20, AUTVAModuloCadastroClientesPF_HOMOLOG_1.docDriver, "\\<.{0,}\\>.{0,}\\W{0,}LM:\\W{0,}.{0,}\\<.{0,}\\>");					
					
					AUTVAUtilidades_LD.executarMetodoElementoHTML(AUTVAModuloCadastroClientesPF_HOMOLOG_1.docDriver.getClass().getName(), AUTVAModuloCadastroClientesPF_HOMOLOG_1.docDriver, "button", "click", "Avançar", 0);

					AUTVAUtilidades_LD.sincronizarStepPorTexto(20, AUTVAModuloCadastroClientesPF_HOMOLOG_1.docDriver, "\\<.{0,}\\>.{0,}\\W{0,}Fluxo de Saída geral\\W{0,}.{0,}\\<.{0,}\\>");					
					
					
					AUTVAModuloCadastroClientesPF_HOMOLOG_1.docDriver.findElementById("caixa").click();
					
					AUTVAUtilidades_LD.executarMetodoElementoHTML(AUTVAModuloCadastroClientesPF_HOMOLOG_1.docDriver.getClass().getName(), AUTVAModuloCadastroClientesPF_HOMOLOG_1.docDriver, "button", "click", "Avançar", 0);

					AUTVAUtilidades_LD.sincronizarStepPorTexto(20, AUTVAModuloCadastroClientesPF_HOMOLOG_1.docDriver, "\\<.{0,}\\>.{0,}\\W{0,}Adicionar meio de pagamento\\W{0,}.{0,}\\<.{0,}\\>");					
					
					AUTVAUtilidades_LD.executarMetodoElementoHTML(AUTVAModuloCadastroClientesPF_HOMOLOG_1.docDriver.getClass().getName(), AUTVAModuloCadastroClientesPF_HOMOLOG_1.docDriver, "button", "click", "Avançar", 0);
					
					AUTVAUtilidades_LD.sincronizarStepPorTexto(20, AUTVAModuloCadastroClientesPF_HOMOLOG_1.docDriver, "\\<.{0,}\\>.{0,}\\W{0,}Resumo\\W{0,}.{0,}\\<.{0,}\\>");					

					
					AUTVAUtilidades_LD.sincronizarStepPorTexto(20, AUTVAModuloCadastroClientesPF_HOMOLOG_1.docDriver, "\\<.{0,}\\>.{0,}\\W{0,}Endereço Cobrança\\W{0,}.{0,}\\<.{0,}\\>");					

					
					AUTVAUtilidades_LD.executarMetodoElementoHTML(AUTVAModuloCadastroClientesPF_HOMOLOG_1.docDriver.getClass().getName(), AUTVAModuloCadastroClientesPF_HOMOLOG_1.docDriver, "button", "click", "Finalizar", 0);

					
					AUTVAUtilidades_LD.sincronizarStepPorTexto(20, AUTVAModuloCadastroClientesPF_HOMOLOG_1.docDriver, "\\<.{0,}\\>.{0,}\\W{0,}Identificação do cartão pedido\\W{0,}.{0,}\\<.{0,}\\>");					

					AUTVAModuloCadastroClientesPF_HOMOLOG_1.docDriver.getKeyboard().sendKeys(Keys.ESCAPE);
					
					AUTVAUtilidades_LD.sincronizarStepPorTexto(20, AUTVAModuloCadastroClientesPF_HOMOLOG_1.docDriver, "(?i:\\<.{0,}\\>.{0,}\\W{0,}Pedido.{0,}\\W\\d+.{0,})");					
					String docOutCheckpoint = "CLIENTE : ".concat(cliente.toString().concat(" : CHECKPOINT-NUMERO DO PEDIDO"));
					docOutCheckpoint = docOutCheckpoint.replaceAll("\\W", "-");
					AUTVAUtilidades_LD.capturarEvidencia(AUTVAModuloCadastroClientesPF_HOMOLOG_1.docDriver, docOutCheckpoint);
										
				}
				catch(java.lang.Exception e) {
					
				}
			
			}			
			
		}
	}
}
