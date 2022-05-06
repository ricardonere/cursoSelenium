import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

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
        page.setEsportes("Natacao", "O que eh esporte?");
        page.cadastrar();
        Assert.assertTrue(dsl.obterTexto("resultado").startsWith("Cadastrado!"));
        Assert.assertTrue(dsl.obterTexto("descNome").endsWith("Ricardo"));
        Assert.assertEquals("Sobrenome: Nere", dsl.obterTexto("descSobrenome"));
        Assert.assertEquals("Sexo: Masculino", dsl.obterTexto("descSexo"));
        Assert.assertEquals("Comida: Pizza", dsl.obterTexto(By.id("descComida")));
        Assert.assertEquals("Escolaridade: mestrado", dsl.obterTexto("descEscolaridade"));
        Assert.assertEquals("Esportes: Natacao", dsl.obterTexto("descEsportes"));

    }

    @Test
    public void deveValidarNomeObrigatorio() {
        page.cadastrar();
        Assert.assertEquals("Nome eh obrigatorio", dsl.alertaObterTextoEAceita);
    }

    @Test
    public void deveValidarSobrenomeObrigatorio() {
        page.setNome("Nome qualquer");
        page.cadastrar();
        Assert.assertEquals("Sobrenome eh obrigatorio", dsl.alertaObterTextoEAceita);
    }

    @Test
    public void deveValidarSexoObrigatorio() {
        page.setNome("Nome qualquer");
        page.setSobrenome("Sobrenome qualquer");
        page.cadastrar();
        Assert.assertEquals("Sexo eh obrigatorio", dsl.alertaObterTextoEAceita);

    }

    @Test
    public void deveValidarComidaVegetariana() {
        page.setNome("Nome qualquer");
        page.setSobrenome("Sobrenome qualquer");
        page.setSexoMascuino();
        page.setComidaPizza();
        page.setComidaVegetariano();
        page.cadastrar();
        Assert.assertEquals("Tem certeza que voce eh vegetariano?", dsl.alertaObterTextoEAceita);
    }


    @Test
    public void deveValidarEsportistaIndeciso() {
        page.setNome("Nome qualquer");
        page.setSobrenome("Sobrenome qualquer");
        page.setSexoMascuino();
        page.setComidaPizza();
        page.setComidaVegetariano();
        page.setEsportes("Karate", "O que eh esporte?");
        page.cadastrar();
        Assert.assertEquals("Voce faz esporte ou nao?", dsl.alertaObterTextoEAceita);
    }
}