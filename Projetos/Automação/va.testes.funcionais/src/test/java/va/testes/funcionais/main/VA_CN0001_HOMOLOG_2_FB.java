package va.testes.funcionais.main;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import va.testes.funcionais.modulos.clientes.*;
import va.testes.funcionais.modulos.pedidos.AUTVAPedidos;

@RunWith(Suite.class)
@SuiteClasses({AUTVAModuloCadastroClientesPF_HOMOLOG_1_LD.class,AUTVAPedidos.class})
public class VA_CN0001_HOMOLOG_2_FB {
	
}
