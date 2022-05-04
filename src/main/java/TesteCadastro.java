import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class TesteCadastro {
    private DSL dsl;
    @Test
    public void deveRealizarCadastroComSucesso() {
        WebDriver driver = new FirefoxDriver();
        driver.manage().window().setSize(new Dimension(1200, 765));
        driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
        dsl = new DSL (driver);
        dsl.esscreve("elementosForm:nome", "Ricardo");
        dsl.esscreve("elementosForm:sobrenome", "Nere");
        dsl.clicarRadio("elementosForm:sexo:0");
        dsl.clicarRadio("elementosForm:comidaFavorita:2");

        dsl.selecionarCombo("elementosForm:escolaridade", "Mestrado");
        dsl.selecionarCombo("elementosForm:esportes", "Natacao");
        dsl.clicarBotao("elementosForm:cadastrar");

        Assert.assertTrue(dsl.obterTexto("resultado").startsWith("Cadastrado!"));
        Assert.assertTrue(dsl.obterTexto("descNome").endsWith("Ricardo"));
        Assert.assertEquals("Sobrenome: Nere", dsl.obterTexto("descSobrenome"));
        Assert.assertEquals("Sexo: Masculino", dsl.obterTexto("descSexo"));
        Assert.assertEquals("Comida: Pizza", dsl.obterTexto(By.id("descComida")));
        Assert.assertEquals("Escolaridade: mestrado", dsl.obterTexto("descEscolaridade"));
        Assert.assertEquals("Esportes: Natacao", dsl.obterTexto("descEsportes"));
        driver.quit();
    }
}
