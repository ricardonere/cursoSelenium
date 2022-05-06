import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TesteRegrasCadastro {
    private WebDriver driver;
    private DSL dsl;
    private CampoTreinamentoPage page;
    @Before
    public void inicializa() {
        driver = new FirefoxDriver();
        driver.manage().window().setSize(new Dimension(1200, 765));
        driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
        dsl = new DSL(driver);
        page = new CampoTreinamentoPage(driver);
    }

    @After
    public void finaliza() {
        driver.quit();
    }
    public void deveValidarEsportistaIndeciso(){
page.setNome("Nome qualquer");
page.setSobrenome("Sobrenome qualquer");
page.setSexoMascuino();
page.setComidaPizza();
page.setEsportes("Natacao", "O que eh esporte?");
page.cadastrar();
Assert.assertEquals("Voce faz esporte ou nao?", dsl.obterTexto());

    }
}
