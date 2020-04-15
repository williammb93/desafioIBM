package TestesAutomaticos;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.After;
import org.junit.Before;
import java.util.List;

/**
 * Classe de teste com JUnit criada para garantir o funcionamento das simulação de investimento do associado
 * 
 * @author William Malacarne Boff
 * @date 15/04/2020
 */

public class SimulaInvestimentoTests {
	
    static WebDriver webDriver;
	private static String valor1 = "2000,00";
	private static String valor2 = "500,00";
	private static String valor3 = "20,00"; //valor limite
	private static String valor4 = "19,99"; //menor que valor limite
	private	static String periodoInvestido = "24";
	
	@Before
	//Setup de teste, que abre o navegador escolhido (Chrome) e acessa a URL desejada
	public void SetUp() {
		// Dizendo para o sistema onde encontrar o Driver do Chrome (browser utilizado) para o Selenium
		System.setProperty("webdriver.chrome.driver","C:/Selenium/chromedriver.exe");
    	
		// Configurando o usuário do browser para evitar alguns erros que podem ocorrem na execução
		ChromeOptions m_Options = new ChromeOptions();
		m_Options.addArguments("--user-data-dir=C:/Usuarios/William/AppData/Local/Google/Chrome/User Data/Profile 1");
		m_Options.addArguments("--disable-extensions");
		m_Options.addArguments("--remote-debugging-port=9222");
		m_Options.addArguments("--no-sandbox");      
        
		// Abrindo o Chrome com as configurações de usuário realizadas acima
		webDriver = new ChromeDriver(m_Options);
		// Maximizando a janela
		webDriver.manage().window().maximize();
		// Abre a URL desejada
		webDriver.navigate().to("https://www.sicredi.com.br/html/ferramenta/simulador-investimento-poupanca/");
	}
	
    @After
    // fecha o Navegador e o WebDriver após terminar teste
	public void tearDown() {
    	
    	/* ========== FINALIZAÇÃO ========== */
    	webDriver.close();
    	webDriver.quit();
    }
	
	
	@Test
	//Testa se o sistema simula o investimento e mostra o resultado e outras opções caso 
	//todos valores estão de acordo (>20,00)
    public void testSimulaInvestimento_01() {
        
    	/* ========== MONTAGEM DO CENÁRIO ========== */
    	preencheValores(valor1,valor2,periodoInvestido);
            	
        /* ========== EXECUÇÃO ========== */
    	WebElement resultado = webDriver.findElement(By.xpath("//*[text()='Veja estas outras opções para você']"));
        
        /* ========== VERIFICAÇÃO ========== */
        assertTrue(resultado.isDisplayed());
        if (resultado.isDisplayed()) {
            System.out.println("Simulação 1 realizada com Sucesso: Passed");
        } else {
            System.out.println("Simulação 1 falhou: Failed");
        }
        
    }
	
	@Test
	//Testa se o sistema simula o investimento e mostra o resultado e outras opções 
	//se ambos valores limites (=20,00)
    public void testSimulaInvestimento_02() {
        
		/* ========== MONTAGEM DO CENÁRIO ========== */
    	preencheValores(valor3,valor3,periodoInvestido);
            	
        /* ========== EXECUÇÃO ========== */
    	WebElement resultado = webDriver.findElement(By.xpath("//*[text()='Veja estas outras opções para você']"));
        
        /* ========== VERIFICAÇÃO ========== */
        assertTrue(resultado.isDisplayed());
        if (resultado.isDisplayed()) {
            System.out.println("Simulação 2 realizada com Sucesso: Passed");
        } else {
            System.out.println("Simulação 2 falhou: Failed");
        }
        
    }
	
	@Test
	//Testa se o sistema mostra mensagem de orientação se valor aplicado < 20,00 e não deixa simular
    public void testSimulaInvestimento_03() {
        
    	/* ========== MONTAGEM DO CENÁRIO ========== */
    	preencheValores(valor4,valor2,periodoInvestido);
            	
        /* ========== EXECUÇÃO ========== */
    	WebElement resultado = webDriver.findElement(By.xpath("//*[text()='Veja estas outras opções para você']"));
    	WebElement msg_aviso = webDriver.findElement(By.xpath("//*[text()='Valor mínimo de 20.00']"));
        
        /* ========== VERIFICAÇÃO ========== */
        assertFalse(resultado.isDisplayed());
        assertTrue(msg_aviso.isDisplayed());
        
        if (!resultado.isDisplayed() && msg_aviso.isDisplayed()) {
            System.out.println("Simulação 3 realizada com Sucesso: Passed");
        } else {
            System.out.println("Simulação 3 falhou: Failed");
        }
        
    }
	
	@Test
	//Testa se o sistema mostra mensagem de orientação se valor investido por mês < 20,00 e não deixa simular
    public void testSimulaInvestimento_04() {
        
    	/* ========== MONTAGEM DO CENÁRIO ========== */
		preencheValores(valor1,valor4,periodoInvestido);
            	
		/* ========== EXECUÇÃO ========== */
		WebElement resultado = webDriver.findElement(By.xpath("//*[text()='Veja estas outras opções para você']"));
		WebElement msg_aviso = webDriver.findElement(By.xpath("//*[text()='Valor mínimo de 20.00']"));
       
        /* ========== VERIFICAÇÃO ========== */
		assertFalse(resultado.isDisplayed());
		assertTrue(msg_aviso.isDisplayed());
       
		if (!resultado.isDisplayed() && msg_aviso.isDisplayed()) {
			System.out.println("Simulação 4 realizada com Sucesso: Passed");
		} else {
           System.out.println("Simulação 4 falhou: Failed");
		}
        
    }
	
	@Test
	//Testa se o sistema mostra mensagem de orientação se ambos valores investido por mês < 20,00 e não deixa simular
    public void testSimulaInvestimento_05() {
        
    	/* ========== MONTAGEM DO CENÁRIO ========== */
		preencheValores(valor4,valor4,periodoInvestido);
            	
        /* ========== EXECUÇÃO ========== */
		WebElement resultado = webDriver.findElement(By.xpath("//*[text()='Veja estas outras opções para você']"));             
		WebElement msg_aviso = webDriver.findElement(By.xpath("//*[text()='Valor mínimo de 20.00']"));
		List<WebElement> num_avisos = webDriver.findElements(By.xpath("//*[text()='Valor mínimo de 20.00']"));
		/* ========== VERIFICAÇÃO ========== */
		assertFalse(resultado.isDisplayed());
		assertTrue(msg_aviso.isDisplayed());
		assertTrue(num_avisos.size()==2);
      
      
		if (!resultado.isDisplayed() && msg_aviso.isDisplayed()) {
			System.out.println("Simulação 5 realizada com Sucesso: Passed");
		} else {
			System.out.println("Simulação 5 falhou: Failed");
		}
        
    }
	    
    public static void preencheValores(String Aplicado, String Investido, String Tempo) {

    	// Preencher campo "Qual valor você quer aplicar?"
        WebElement valorAplicar = webDriver.findElement(By.id("valorAplicar"));
        valorAplicar.sendKeys(Aplicado);

        // Preencher campo "Quanto você quer poupar todo mês?"
        WebElement valorInvestir = webDriver.findElement(By.id("valorInvestir"));
        valorInvestir.sendKeys(Investido);
            
        // Preencher campo "Por quanto tempo você quer poupar?"
        WebElement tempo =webDriver.findElement(By.id("tempo"));
        tempo.sendKeys(Tempo);   
                       
        // Clica no botão Simular
        WebElement simular = webDriver.findElement(By.className("btnAmarelo"));
        simular.click();
        
        espera();
                   
    }
        

    private static void espera() {
    	// Espera um pouco para realizar outra ação (1000 milliseconds)
    	try {
    		Thread.sleep(1000);
    	} catch (InterruptedException e) {
    		e.printStackTrace();
    	}
    }

}
 