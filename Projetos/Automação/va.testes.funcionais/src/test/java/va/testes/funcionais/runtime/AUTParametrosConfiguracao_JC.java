package va.testes.funcionais.runtime;

public class AUTParametrosConfiguracao_JC {
	
	public static Integer MD_ID_PARAMS_CONFIG = 0;
	/**
	 * 
	 * Massa de dados dos testes
	 * 
	 */
	public static java.util.HashMap<Object,java.util.HashMap<Object,Object>> MD_PARAMETROS_CONFIGURACAO_DOS_TESTES = new java.util.HashMap<Object,java.util.HashMap<Object,Object>>(); 
	
	/**
	 * 
	 * INDICA SE A PESQUISA ESTÁ EM ANDAMENTO
	 * 
	 */
	public static boolean PESQUISA_FINALIZADA = true;
	/**
	 * 
	 * STATUS FINAL DA PESQUISA PELO TEXTO
	 * 
	 */
	public static boolean RESULTADO_FINAL_PESQUISA_TEXTO = false;
	
	/**
	 * 
	 * STATUS FINAL DA PESQUISA PELO ELEMENTO
	 * 
	 */
	public static boolean RESULTADO_FINAL_PESQUISA_ELEMENTO = false;
	/**
	 * TEXTOS CAPTURADOS DURANTE A PESQUISA
	 */
	public static java.util.List<String> EVIDENCIAS_TEXTOS_ENCONTRADOS = null; 
	/**
	 * 
	 * CASO O ELEMENTO PROCURADO
	 * 
	 */
	public static org.openqa.selenium.WebElement RESULTADO_PESQUISA_POR_ELEMENTO = null;
	/**
	 * TEXTO PROCURADO DEFINIDO EM EXPRESSAO REGURA
	 */
	public static String RESULTADO_PESQUISA_POR_TEXTO_EXP_REGULAR = "";
	/**
	 * 
	 * PESQUISA BASEADA EM TEXTO : CÓDIGO FONTE DA APLICAÇÃO
	 * 
	 */
	public static boolean PESQUISAR_POR_TEXTO = true;
	/**
	 * 
	 * PESQUISA POR ELEMENTO DEFINIDO EM INTERFACE GRÁFICA DA APLICAÇÃO
	 * 
	 */
	public static boolean PESQUISAR_POR_ELEMENTO = false;
	/**
	 * 
	 * WEBDRIVER ATUALMENTE ATIVO PARA MANIPULAÇÃO DO BROWSER
	 * 
	 */
	public static org.openqa.selenium.WebDriver DOC_DRIVER = null;
	/**
	 * 
	 * TEXTO PROCURADO NO CÓDIGO FONTE
	 * 
	 */
	public static String TEXTO_PROCURADO = "";
	/**
	 * 
	 * ELEMENTO PROCURADO NA APLICAÇÃO
	 * 
	 */
	public static org.openqa.selenium.WebElement ELEMENTO_PROCURADO = null;
	/**
	 * 
	 * TEMPO LIMITE PARA ENCONTRAR O ELEMENTO OU TEXTO ESPECIFICADO COMO CRITÉRIO DE PESQUISA
	 * 
	 * 
	 */
	public static Integer TEMPO_EM_SEGUNDOS_PARA_PESQUISA_SER_CONCLUIDA = 20;
	/**
	 * 
	 * TEMPO ENTRE AS VERIFICAÇÃO NA PAGINA ALVO DA PESQUISA, SEJA EM PESQUISAS POR TEXTO OU OBJETO GUI 
	 * 
	 */
	public static Integer TEMPO_ENTRE_CICLOS_VERIFICACAO = 2;
	
}
