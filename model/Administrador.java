package model;
import java.util.List;

public class Administrador extends Funcionario {
    //WIP (Work in Progress)
    private int nivelAcesso;

    public Administrador(String nome, String cpf, String dataNascimento, String telefone, String tipoSanguineo, int totalPessoasCadastradas, String matricula, String login, String senha) {
        super(nome, cpf, dataNascimento, telefone, tipoSanguineo, totalPessoasCadastradas, matricula, login, senha);
    }

    @Override
    public List<String> getFuncionalidades() {
        return List.of("lorem", "ipsum"); //para nao quebrar o codigo
    }

    public String exibirDadosBasicos() {
        return super.exibirDadosBasicos() + "\n" +
               "Nível de Acesso: " + nivelAcesso;
    }
}