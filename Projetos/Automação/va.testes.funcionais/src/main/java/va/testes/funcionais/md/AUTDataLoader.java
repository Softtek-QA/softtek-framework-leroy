package va.testes.funcionais.md;

import java.io.IOException;
import java.nio.file.Paths;

public class AUTDataLoader {

	public static java.util.HashMap<String, String> carregarParametros(String fileNameFull) throws IOException{
		
		java.util.HashMap<String,String> params = new java.util.HashMap<String,String>();
		
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
		
		return params;			
	}


}
