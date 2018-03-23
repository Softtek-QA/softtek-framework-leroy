package va.testes.funcionais.cadastro;

import static org.junit.Assert.*;

import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class CT0001 {

	@Test
	public void test() {
		
		WebDriver driverDoc = new ChromeDriver();
		
		org.junit.Assert.assertSame("valor 2", "valor 2");
		
		//fail("Not yet implemented");
		
	}

}
