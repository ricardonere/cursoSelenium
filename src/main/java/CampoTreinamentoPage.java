import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CampoTreinamentoPage {
    private DSL dsl;

    public CampoTreinamentoPage(WebDriver driver) {
        dsl = new DSL(driver);
    }

    public void setNome(String nome) {
        dsl.esscreve("elementosForm:nome", nome);

    }

    public void setSobrenome(String sobrenome) {
        dsl.esscreve("elementosForm:sobrenome", sobrenome);
    }

    public void setSexoMascuino() {
        dsl.clicarRadio("elementosForm:sexo:0");
    }

    public void setComidaPizza() {
        dsl.clicarRadio("elementosForm:comidaFavorita:2");
    }

    public void setEscolaridade(String valor) {
        dsl.selecionarCombo("elementosForm:escolaridade", valor);
    }

    public void setEsportes(String valor) {
        dsl.selecionarCombo("elementosForm:esportes", valor);
    }

    public void cadastrar() {
        dsl.clicarBotao("elementosForm:cadastrar");
    }

    public String obterResultadoCadastro() {
        return dsl.obterTexto("resultado");
    }

    public String obterNomeCadastro() {
        return dsl.obterTexto("descNome");
    }

    public String obterSobrenomeCadastro() {
        return dsl.obterTexto("descSobrenome");
    }

    public String obterSexoCadastro() {
        return dsl.obterTexto("descSexo");
    }

    public String obterComidaCadastro() {
        return dsl.obterTexto(By.id("descComida"));
    }

    public String obterEscolaridadeCadastro() {
        return dsl.obterTexto("descEscolaridade");
    }

    public String obterEsportesCadastro() {
        return dsl.obterTexto("descEsportes");
    }

}