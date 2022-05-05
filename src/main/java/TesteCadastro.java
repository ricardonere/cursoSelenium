import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class TesteCadastro {
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
    @Test
    public void deveRealizarCadastroComSucesso() {
        page.setNome("Ricardo");
        page.setSobrenome("Nere");
        page.setSexoMascuino();
        page.setComidaPizza();
        page.setEscolaridade("Mestrado");
        page.setEsportes("Natacao");

        dsl.clicarBotao("elementosForm:cadastrar");

        Assert.assertTrue(dsl.obterTexto("resultado").startsWith("Cadastrado!"));
        Assert.assertTrue(dsl.obterTexto("descNome").endsWith("Ricardo"));
        Assert.assertEquals("Sobrenome: Nere", dsl.obterTexto("descSobrenome"));
        Assert.assertEquals("Sexo: Masculino", dsl.obterTexto("descSexo"));
        Assert.assertEquals("Comida: Pizza", dsl.obterTexto(By.id("descComida")));
        Assert.assertEquals("Escolaridade: mestrado", dsl.obterTexto("descEscolaridade"));
        Assert.assertEquals("Esportes: Natacao", dsl.obterTexto("descEsportes"));

    }
}
