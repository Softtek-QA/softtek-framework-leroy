package va.testes.funcionais.modulos.clientes;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.interactions.Keyboard;
import org.openqa.selenium.interactions.SendKeysAction;
import org.openqa.selenium.remote.ExecuteMethod;
import org.openqa.selenium.remote.RemoteExecuteMethod;
import org.openqa.selenium.remote.RemoteKeyboard;

import va.testes.funcionais.md.AUTDataLoader;
import va.testes.funcionais.utils.AUTTestObject;
import va.testes.funcionais.utils.AUTVAUtilidades;
public class AUTVAModuloCadastroClientesPF extends AUTTestObject{
	/**
	 * 
	 * Massa de dados do teste
	 * 
	 */
	public static java.util.HashMap<Object,Object> MD_PARAMETROS_ENTRADA;
	public static java.util.HashMap<Object,Object> MD_PARAMETROS_SAIDA;

	
	@Before
	public void testInit(){
		System.out.println("AUT INFO  : INICIALIZACAO MOD CADASTRO");
	}
	
	@Test
	public void test() throws IOException, InterruptedException {				
		

		System.out.println("INFO TEST: CARREGANDO MASSA DE DADOS DO TESTE");
		MD_PARAMETROS_ENTRADA = AUTDataLoader.carregarParametros("../va.testes.funcionais/Arquivos de Dados/AUTCN001.txt");
		java.util.HashMap<Object, Object> parametrosTeste = MD_PARAMETROS_ENTRADA;

		
		String urlInit = parametrosTeste.get("LINK_APLICACAO_WEB").toString();
		String usuarioLogin = parametrosTeste.get("PARAM_USUARIO_VA").toString();
		String senhaLogin = parametrosTeste.get("PARAM_PWD_VA").toString();
		String clienteCPF = parametrosTeste.get("CLIENTE_CPF").toString();
		String clienteNome = parametrosTeste.get("CLIENTE_NOME").toString();
		String clienteEmail = parametrosTeste.get("CLIENTE_EMAIL").toString();
		boolean clienteAceitaContrato = (parametrosTeste.get("CLIENTE_ACEITA_CONTRATO").toString().toUpperCase()=="S"?true:false);
		String clienteTipoTelefone = parametrosTeste.get("CLIENTE_TIPO_TELEFONE").toString();
		String clienteNumeroTelefone = parametrosTeste.get("CLIENTE_NUMERO_TELEFONE").toString();
		String clienteTipoEndereco = parametrosTeste.get("CLIENTE_TIPO_ENDERECO").toString();
		String clienteCEPEndereco = parametrosTeste.get("CLIENTE_CEP_ENDERECO").toString();
		String clienteRuaEndereco = parametrosTeste.get("CLIENTE_RUA_ENDERECO").toString();
		String clienteRuaNumEndereco = parametrosTeste.get("CLIENTE_RUA_NUM_ENDERECO").toString();
		String clienteBairroEndereco = parametrosTeste.get("CLIENTE_BAIRRO_ENDERECO").toString();
		String clienteComplementoEndereco = parametrosTeste.get("CLIENTE_COMPLEMENTO_ENDERECO").toString();
		String clienteCidadeEndereco = parametrosTeste.get("CLIENTE_CIDADE_ENDERECO").toString();
		String clienteEstadoEndereco = parametrosTeste.get("CLIENTE_ESTADO_ENDERECO").toString();
		String clienteReferenciaEndereco = parametrosTeste.get("CLIENTE_REFERENCIA_ENDERECO").toString();
		String clienteTipoImovelEndereco = parametrosTeste.get("CLIENTE_TIPO_IMOVEL").toString();
		
		docDriver.get(urlInit);

		AUTVAUtilidades.fazerLogin(docDriver, usuarioLogin, senhaLogin);

		boolean existemItemsPendAprov = AUTVAUtilidades.sincronizarStepPorTexto(20,docDriver, "\\<.{0,}\\>.{0,}Você possui pedidos/orçamentos, pendentes de aprovação comercial.{0,}\\<.{0,}\\>");

		if(existemItemsPendAprov) {
			System.out.println("AUT VALIDACAO HTML: EXISTEM PEDIDOS PENDENTES DE APROVACAO");
			docDriver.findElement(By.xpath("/html/body/div[3]/div/i")).click();			
		}
		else {
			System.out.println("AUT VALIDACAO HTML: NAO EXISTEM PEDIDOS PENDENTES DE APROVACAO");
		}	

		boolean telaCarrinho = AUTVAUtilidades.sincronizarStepPorTexto(20,docDriver, "\\<.{0,}\\>.{0,}\\W{0,}Criar carrinho\\.\\.\\..{0,}\\<.{0,}\\>");
		AUTVAUtilidades.sincronizarStepPorTexto(20,docDriver, "\\<.{0,}\\>.{0,}\\W{0,}Recuperar pedido.{0,}\\<.{0,}\\>");
		AUTVAUtilidades.sincronizarStepPorTexto(20,docDriver, "\\<.{0,}\\>.{0,}\\W{0,}Recuperar orçamento.{0,}\\<.{0,}\\>");
		AUTVAUtilidades.sincronizarStepPorTexto(20,docDriver, "\\<.{0,}\\>.{0,}\\W{0,}Recuperar carrinho.{0,}\\<.{0,}\\>");	
		//docDriver.findElementByXPath("/html/body/div[3]/div/i").click();

		AUTVAUtilidades.executarMetodoElementoHTML(docDriver.getClass().getName(), docDriver, "a", "click", "Adicionar Novo", 1);


		docDriver.executeScript("document.getElementsByClassName(\"burger-menu\")[0].click()");


		AUTVAUtilidades.sincronizarStepPorTexto(20,docDriver, "\\<.{0,}\\>.{0,}Selecionar Loja.{0,}\\<.{0,}\\>");
		boolean existeMenuClientes = AUTVAUtilidades.sincronizarStepPorTexto(20,docDriver, "\\<.{0,}\\>.{0,}Clientes.{0,}\\<.{0,}\\>");
		AUTVAUtilidades.sincronizarStepPorTexto(20,docDriver, "\\<.{0,}\\>.{0,}\\W{0,}Parâmetros de Frete\\W{0,}.{0,}\\<.{0,}\\>");
		AUTVAUtilidades.sincronizarStepPorTexto(20,docDriver, "\\<.{0,}\\>.{0,}\\W{0,}Performance de Vendas\\W{0,}.{0,}\\<.{0,}\\>");
		AUTVAUtilidades.sincronizarStepPorTexto(20,docDriver, "\\<.{0,}\\>.{0,}\\W{0,}RSS Pendentes\\W{0,}.{0,}\\<.{0,}\\>");
		AUTVAUtilidades.sincronizarStepPorTexto(20,docDriver, "\\<.{0,}\\>.{0,}\\W{0,}Liberações Pendentes\\W{0,}.{0,}\\<.{0,}\\>");
		AUTVAUtilidades.sincronizarStepPorTexto(20,docDriver, "\\<.{0,}\\>.{0,}\\W{0,}Liberar Cartão Pedido\\W{0,}.{0,}\\<.{0,}\\>");

		docDriver.executeScript("cont=0;tot=document.getElementsByTagName(\"strong\").length;itens=document.getElementsByTagName(\"strong\");while(cont<tot){console.log(itens[cont]);cont++;}");

		AUTVAUtilidades.executarMetodoElementoHTML(docDriver.getClass().getName(), docDriver, "strong", "click", "Clientes", 0);


		Integer contClientes = 1;
		
		
		MD_PARAMETROS_SAIDA = new java.util.HashMap<Object,Object>();
		
		MD_PARAMETROS_SAIDA.put("CLIENTES", AUTVAUtilidades.autSplitParameters("\\d+",clienteCPF));
		
		java.util.HashMap<Object,Object> hashClientes = (java.util.HashMap<Object,Object>)MD_PARAMETROS_SAIDA.get("CLIENTES");
		
		for(Object clienteCorrente : hashClientes.values()) {
			AUTVAUtilidades.sincronizarStepPorTexto(20,docDriver, "\\<.{0,}\\>.{0,}\\W{0,}Adicionar Novo\\W{0,}.{0,}\\<.{0,}\\>");

			AUTVAUtilidades.executarMetodoElementoHTML(docDriver.getClass().getName(), docDriver, "a", "click", "Adicionar Novo", 0);

			AUTVAUtilidades.sincronizarStepPorTexto(20,docDriver, "\\<.{0,}\\>.{0,}\\W{0,}Adicionar Cliente\\W{0,}.{0,}\\<.{0,}\\>");

			AUTVAUtilidades.sincronizarStepPorTexto(20,docDriver, "\\<.{0,}\\>.{0,}\\W{0,}Dados Básicos\\W{0,}.{0,}\\<.{0,}\\>");

			AUTVAUtilidades.sincronizarStepPorTexto(20,docDriver, "\\<.{0,}\\>.{0,}\\W{0,}CPF/CNPJ\\W{0,}.{0,}\\<.{0,}\\>");


			AUTVAUtilidades.executarMetodoElementoHTML(docDriver.getClass().getName(), docDriver, "label", "click", "CPF/CNPJ", 0);

			AUTVAUtilidades.enviarDadosElementWeb(docDriver.getClass().getName(), docDriver, 1, clienteCorrente.toString());

			AUTVAUtilidades.sincronizarStepPorTexto(20,docDriver, "\\<.{0,}\\>.{0,}\\W{0,}Nome completo\\W{0,}.{0,}\\<.{0,}\\>");

			AUTVAUtilidades.executarMetodoElementoHTML(docDriver.getClass().getName(), docDriver, "label", "click", "Nome completo", 0);

			//docDriver.getKeyboard().pressKey("\b");

			AUTVAUtilidades.executarMetodoElementoHTML(docDriver.getClass().getName(), docDriver, "label", "click", "Nome completo", 0);


			docDriver.findElementById("nome-pf").sendKeys(clienteNome.concat(" : ").concat(clienteCorrente.toString()));

			AUTVAUtilidades.sincronizarStepPorTexto(20,docDriver, "\\<.{0,}\\>.{0,}\\W{0,}E-mail\\W{0,}.{0,}\\<.{0,}\\>");

			AUTVAUtilidades.executarMetodoElementoHTML(docDriver.getClass().getName(), docDriver, "label", "click", "E-mail", 0);

			AUTVAUtilidades.enviarDadosElementWeb(docDriver.findElementById("e-mail-pf"), (long)0.5, clienteEmail);

			docDriver.findElementById("e-mail-pf").sendKeys("\t");

			AUTVAUtilidades.sincronizarStepPorTexto(20,docDriver, "\\<.{0,}\\>.{0,}\\W{0,}Aceito receber novidades, ofertas e notícias da Leroy Merlin\\W{0,}.{0,}\\<.{0,}\\>");

			AUTVAUtilidades.capturarEvidencia(docDriver,"STEP 1");
			
			AUTVAUtilidades.executarMetodoElementoHTML(docDriver.getClass().getName(), docDriver, "label", "click", "Sim", 0);

			AUTVAUtilidades.capturarEvidencia(docDriver,contClientes.toString().concat(" - ").concat("STEP 2"));
			
			AUTVAUtilidades.enviarDadosElementWeb(docDriver.getClass().getName(), docDriver, 2, "\t");

			AUTVAUtilidades.sincronizarStepPorTexto(20,docDriver, "\\<.{0,}\\>.{0,}\\W{0,}Num. Telefone\\W{0,}.{0,}\\<.{0,}\\>");

			AUTVAUtilidades.sincronizarStepPorTexto(20,docDriver, "\\<.{0,}\\>.{0,}\\W{0,}Tipo telefone\\W{0,}.{0,}\\<.{0,}\\>");

			AUTVAUtilidades.executarMetodoElementoHTML(docDriver.getClass().getName(), docDriver, "label", "click", "Tipo telefone", 0);

			docDriver.findElementById("tipo-0-pf").sendKeys(clienteTipoTelefone);
			
			AUTVAUtilidades.capturarEvidencia(docDriver,contClientes.toString().concat(" - ").concat("STEP 3"));

			AUTVAUtilidades.executarMetodoElementoHTML(docDriver.getClass().getName(), docDriver, "label", "click", "Num. Telefone", 0);

			AUTVAUtilidades.enviarDadosElementWeb(docDriver.findElementById("telefone-0-pf"), (long)0.5, clienteNumeroTelefone);

			AUTVAUtilidades.capturarEvidencia(docDriver,contClientes.toString().concat(" - ").concat("STEP 4"));
			
			docDriver.getKeyboard().sendKeys("\t");
			
			docDriver.findElementById("oferta-telefone-sim-pf").click();

			AUTVAUtilidades.enviarDadosElementWeb(docDriver.findElementById("tipo-imovel-0-pf"), (long)0.3, clienteNumeroTelefone);

			//docDriver.findElementById("oferta-telefone-sim").click();

			AUTVAUtilidades.capturarEvidencia(docDriver,contClientes.toString().concat(" - ").concat("STEP 5"));
			
			AUTVAUtilidades.executarMetodoElementoHTML(docDriver.getClass().getName(), docDriver, "label", "click", "CEP", 0);
			AUTVAUtilidades.enviarDadosElementWeb(docDriver.findElementById("cep-0-pf"), (long)0.3, clienteCEPEndereco);

			AUTVAUtilidades.capturarEvidencia(docDriver,contClientes.toString().concat(" - ").concat("STEP 6"));

			boolean erroPesquisaCEP = AUTVAUtilidades.sincronizarStepPorTexto(20,docDriver, "\\<.{0,}\\>.{0,}\\W{0,}Não foi possível realizar sua pesquisa, tente mais tarde.\\W{0,}.{0,}\\<.{0,}\\>");
			if(erroPesquisaCEP) {
				System.out.println("AUT INFO : NAO FOI POSSIVEL PESQUISAR CEP ONLINE");

				AUTVAUtilidades.executarMetodoElementoHTML(docDriver.getClass().getName(), docDriver, "button", "click", "Confirmar", 0);
			}

			AUTVAUtilidades.executarMetodoElementoHTML(docDriver.getClass().getName(), docDriver, "label", "click", "Rua", 0);
			AUTVAUtilidades.enviarDadosElementWeb(docDriver.findElementById("rua-0-pf"), (long)0.3, clienteRuaEndereco);

			AUTVAUtilidades.capturarEvidencia(docDriver,contClientes.toString().concat(" - ").concat("STEP 7"));

			AUTVAUtilidades.executarMetodoElementoHTML(docDriver.getClass().getName(), docDriver, "label", "click", "Número", 0);
			AUTVAUtilidades.enviarDadosElementWeb(docDriver.findElementById("numero-0-pf"), (long)0.3, clienteRuaNumEndereco);

			AUTVAUtilidades.capturarEvidencia(docDriver,contClientes.toString().concat(" - ").concat("STEP 8"));
			
			AUTVAUtilidades.executarMetodoElementoHTML(docDriver.getClass().getName(), docDriver, "label", "click", "Bairro", 0);
			AUTVAUtilidades.enviarDadosElementWeb(docDriver.findElementById("bairro-0-pf"), (long)0.3, clienteBairroEndereco);

			AUTVAUtilidades.capturarEvidencia(docDriver,contClientes.toString().concat(" - ").concat("STEP 9"));
			
			AUTVAUtilidades.executarMetodoElementoHTML(docDriver.getClass().getName(), docDriver, "label", "click", "Complemento", 0);
			AUTVAUtilidades.enviarDadosElementWeb(docDriver.findElementById("complemento-0-pf"), (long)0.3, clienteComplementoEndereco);

			AUTVAUtilidades.capturarEvidencia(docDriver,contClientes.toString().concat(" - ").concat("STEP 10"));
			
			AUTVAUtilidades.executarMetodoElementoHTML(docDriver.getClass().getName(), docDriver, "label", "click", "Cidade", 0);
			AUTVAUtilidades.enviarDadosElementWeb(docDriver.findElementById("cidade-0-pf"), (long)0.3, clienteCidadeEndereco);

			AUTVAUtilidades.capturarEvidencia(docDriver,contClientes.toString().concat(" - ").concat("STEP 11"));
			
			AUTVAUtilidades.executarMetodoElementoHTML(docDriver.getClass().getName(), docDriver, "label", "click", "Estado", 0);
			AUTVAUtilidades.enviarDadosElementWeb(docDriver.findElementById("estado-0-pf"), (long)0.3, clienteEstadoEndereco);

			AUTVAUtilidades.capturarEvidencia(docDriver,contClientes.toString().concat(" - ").concat("STEP 12"));
			
			AUTVAUtilidades.executarMetodoElementoHTML(docDriver.getClass().getName(), docDriver, "label", "click", "Referência", 0);
			AUTVAUtilidades.enviarDadosElementWeb(docDriver.findElementById("referencia-0-pf"), (long)0.3, clienteReferenciaEndereco);

			AUTVAUtilidades.capturarEvidencia(docDriver,contClientes.toString().concat(" - ").concat("STEP 13"));
			
			AUTVAUtilidades.executarMetodoElementoHTML(docDriver.getClass().getName(), docDriver, "label", "click", "Tipo de Imóvel", 0);
			AUTVAUtilidades.enviarDadosElementWeb(docDriver.findElementById("tipo-imovel-0-pf"), (long)0.3, clienteTipoImovelEndereco);

			AUTVAUtilidades.capturarEvidencia(docDriver,contClientes.toString().concat(" - ").concat("STEP 14"));

			AUTVAUtilidades.sincronizarStepPorTexto(20,docDriver, "\\<.{0,}\\>.{0,}\\W{0,}Aceito receber novidades, ofertas e notícias da Leroy Merlin por mala direta\\W{0,}.{0,}\\<.{0,}\\>");

			java.lang.Thread.currentThread().sleep(4000);
			docDriver.findElementById("mala-direta-sim-pf").click();

			AUTVAUtilidades.procurarElementWebHTML(docDriver.getClass().getName(), docDriver, (long)0.3, "input", "(?i:<(input).{0,}mala\\-{0,}direta\\-{0,}sim\\-pf.{0,}>)").get(0).click();

			docDriver.getKeyboard().sendKeys("\t");

			AUTVAUtilidades.capturarEvidencia(docDriver,contClientes.toString().concat(" - ").concat("STEP 15"));
			
			AUTVAUtilidades.executarMetodoElementoHTML(docDriver.getClass().getName(), docDriver, "button", "click", "Avançar", 0);

			AUTVAUtilidades.sincronizarStepPorTexto(20,docDriver, String.format(".{0,30}%s.{0,30}",clienteCorrente));
			
			AUTVAUtilidades.capturarEvidencia(docDriver,contClientes.toString().concat(" - ").concat("STEP 16"));

			System.out.println("AUT STATUS FINAL: CLIENTE CADASTRADO COM SUCESSO!!!");

			docDriver.executeScript("cont=0;tot=document.getElementsByTagName(\"strong\").length;itens=document.getElementsByTagName(\"strong\");while(cont<tot){console.log(itens[cont]);cont++;}");

			AUTVAUtilidades.executarMetodoElementoHTML(docDriver.getClass().getName(), docDriver, "strong", "click", "Clientes", 0);
			 		
			AUTVAUtilidades.capturarEvidencia(docDriver,  clienteCorrente.toString().concat(" - ").concat("CHECKPOINT - CADASTRO CLIENTE"));
			contClientes++;
		}
	}

}
