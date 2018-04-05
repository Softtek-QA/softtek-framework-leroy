package va.testes.funcionais.md;

import java.io.IOException;
import java.nio.file.Paths;

import va.testes.funcionais.runtime.AUTParametrosConfiguracao_LD;

public class AUTDataLoader_JC {

	public static java.util.HashMap<Object,Object> carregarParametros(String fileNameFull) throws IOException{
		
		java.util.HashMap<Object,Object> params = new java.util.HashMap<Object,Object>();
		
		if(!java.nio.file.Files.exists(Paths.get(fileNameFull))) {
			java.nio.file.Files.createFile(Paths.get(fileNameFull));
		}
		
		java.util.List<String> linhas = java.nio.file.Files.readAllLines(Paths.get(fileNameFull));
					
		System.out.println("INFO: CARREGANDO ARQUIVO DE DADOS DO SISTEMA");
		
		for(String ln: linhas) {
			
			String campo = ln.split("=")[0];
			String valor = ln.split("=")[1];
			
			params.put(campo, valor);
			
			System.out.println(ln);
		}
		
		System.out.println(params);

		AUTParametrosConfiguracao_LD.MD_ID_PARAMS_CONFIG++;
		AUTParametrosConfiguracao_LD.MD_PARAMETROS_CONFIGURACAO_DOS_TESTES.put((Object)AUTParametrosConfiguracao_LD.MD_ID_PARAMS_CONFIG, params);		
		
		return params;			
	}


}
