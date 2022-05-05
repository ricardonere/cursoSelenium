import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class TesteCampoTreinamento {
    private WebDriver driver;
    private DSL dsl;

    @Before
    public void inicializa() {
        driver = new FirefoxDriver();
        driver.manage().window().setSize(new Dimension(1200, 765));
        driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
        dsl = new DSL(driver);
    }

    @After
    public void finaliza() {
        driver.quit();
    }

    @Test
    public void testeTextField() {
        dsl.esscreve("elementosForm:nome", "Teste de escrita");
        Assert.assertEquals("Teste de escrita", dsl.obterValorCampo("elementosForm:nome"));
    }

    @Test
    public void deveInteragirComOTextArea() {
        dsl.esscreve("elementosForm:sugestoes", "teste\n\naasldjdlks\nUltima linha");
        Assert.assertEquals("teste\n\naasldjdlks\nUltima linha", dsl.obterValorCampo("elementosForm:sugestoes"));
    }

    @Test
    public void deveInteragirComRadioButton() {
        driver.findElement(By.id("elementosForm:sexo:0")).click();
        Assert.assertTrue(driver.findElement(By.id("elementosForm:sexo:0")).isSelected());
    }

    @Test
    public void deveInteragirComCheckbox() {
        dsl.clicarRadio("elementosForm:comidaFavorita:2");
        Assert.assertTrue(dsl.isRadioMarcado("elementosForm:comidaFavorita:2"));
    }

    @Test
    public void deveInteragirComCombo() {

        dsl.selecionarCombo("elementosForm:escolaridade", "2o grau completo");
        Assert.assertEquals("2o grau completo", dsl.obterValorCombo("elementosForm:escolaridade"));
        //Assert.assertTrue(driver.findElement(By.id("elementosForm:comidaFavorita:2")).isSelected());
    }

    @Test
    public void deveVerificarValoresCombo() {
        WebElement element = driver.findElement(By.id("elementosForm:escolaridade"));
        Select combo = new Select(element);
        List<WebElement> options = combo.getOptions();
        Assert.assertEquals(8, options.size());

        boolean encontrou = false;
        for (WebElement option : options) {
            if (option.getText().equals("Mestrado")) {
                encontrou = true;
                break;
            }
        }
    }

    @Test
    public void deveVerificarValoresComboMultiplo() {
        dsl.selecionarCombo("elementosForm:esportes", "Natacao");
        dsl.selecionarCombo("elementosForm:esportes", "Corrida");
        dsl.selecionarCombo("elementosForm:esportes", "O que eh esporte?");

        WebElement element = driver.findElement(By.id("elementosForm:esportes"));
        Select combo = new Select(element);
        List<WebElement> allSelectedOptions = combo.getAllSelectedOptions();
        Assert.assertEquals(3, allSelectedOptions.size());

        combo.deselectByVisibleText("Corrida");
        allSelectedOptions = combo.getAllSelectedOptions();
        Assert.assertEquals(2, allSelectedOptions.size());
    }

    @Test
    public void deveInteragirComBotoes() {
        dsl.clicarBotao("buttonSimple");

        WebElement botao = driver.findElement(By.id("buttonSimple"));
        Assert.assertEquals("Obrigado!", botao.getAttribute("value"));
        //driver.quit();
    }

    @Test
    public void deveInteragirComLinks() {
        dsl.clicarLink("Voltar");
        Assert.assertEquals("Voltou!", dsl.obterTexto("resultado"));
    }

    @Test
    public void deveBuscarTextosNaPagina() {//chamada do metodo-assinatura
//        WebDriver driver = new FirefoxDriver();//tipo de driver
//        driver.manage().window().setSize(new Dimension(1200, 765));//dimensão da tela
//        driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");//Acesso ao link-site
        //System.out.println(driver.findElement(By.tagName("body")).getText());//traz o texto visivel da tela
        //Assert.assertTrue(driver.findElement(By.tagName("body")).getText().contains("Campo de Treinamento"));//checar se o titulo esta presente
        Assert.assertEquals("Campo de Treinamento", dsl.obterTexto(By.tagName("h3")));
        Assert.assertEquals("Cuidado onde clica, muitas armadilhas...",
                dsl.obterTexto(By.className("facilAchar")));
    }
}