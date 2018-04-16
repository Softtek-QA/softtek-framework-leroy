package va.testes.funcionais.main;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import va.testes.funcionais.modulos.clientes.*;
import va.testes.funcionais.modulos.pedidos.AUTVAPedidos;
import va.testes.funcionais.utils.AUTVAUtilidades;

@RunWith(Suite.class)
@SuiteClasses({AUTVAModuloCadastroClientesPF.class,AUTVAPedidos.class})
public class VA_CN0001 {
	
	
}
