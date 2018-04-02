package va.testes.funcionais.modulos.pedidos;

import static org.junit.Assert.*;

import org.junit.Test;

public class AUTVAPedidos {

	@Test
	public void test() {
		for(Object prop : System.getProperties().keySet()) {
			System.out.println("PARAMETRO: ".concat(prop.toString()));
		}
	}

}
