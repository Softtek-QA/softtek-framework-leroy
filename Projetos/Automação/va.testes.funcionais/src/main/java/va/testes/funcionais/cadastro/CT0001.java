package va.testes.funcionais.cadastro;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import va.testes.funcionais.md.AUTDataLoader;

public class CT0001 {
	
	
	
	@Test
	public void test() throws IOException {
		
		System.setProperty("webdriver.chrome.driver", "../va.testes.funcionais/src/main/resources/chromedriver.exe");
		ChromeOptions chrOptions = new ChromeOptions();
		
		
		//chrOptions.addArguments("headless");
		//chrOptions.addArguments("window-size=1200x1024");
		
		//WebDriver driverDoc = new ChromeDriver(chrOptions);
		WebDriver driverDoc = new ChromeDriver();		
		
		System.out.println(AUTDataLoader.carregarParametros(""));
		
	}

}
