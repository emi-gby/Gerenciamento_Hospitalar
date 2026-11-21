package model;
import java.util.List;

public abstract class Funcionario extends Pessoa implements security.Autenticavel {
    //WIP (Work in Progress)
    protected String matricula;
    protected String login;
    protected String senha;

    public Funcionario(String nome, String cpf, String dataNascimento, String telefone, String tipoSanguineo, int totalPessoasCadastradas, String matricula, String login, String senha) {
        super(nome, cpf, dataNascimento, telefone, tipoSanguineo, totalPessoasCadastradas);
        this.matricula = matricula;
        this.login = login;
        this.senha = senha;
    }

    public boolean autenticar(String login, String senha) {
        return this.login.equals(login) && this.senha.equals(senha); //salvar em hash depois? para manter integridade da senha
    }

    public void alterarSenha(String novaSenha) {
        this.senha = novaSenha;
    }

    public abstract List<String> getFuncionalidades();
    
    public String exibirDadosBasicos() {
        return "Nome: " + nome + "\n" +
               "CPF: " + cpf + "\n" +
               "Data de Nascimento: " + dataNascimento + "\n" +
               "Telefone: " + telefone + "\n" +
               "Tipo Sanguíneo: " + tipoSanguineo + "\n" +
               "Matrícula: " + matricula;
    }
}