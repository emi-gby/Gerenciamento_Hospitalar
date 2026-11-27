package model;
import java.util.List;

public abstract class Funcionario extends Pessoa implements security.Autenticavel {
    //WIP (Work in Progress)
    protected String matricula;
    protected String login;
    protected String senha;

    public Funcionario(String nome, String cpf, String dataNascimento, String telefone, String tipoSanguineo, String matricula, String login, String senha) {
        super(nome, cpf, dataNascimento, telefone, tipoSanguineo);
        this.matricula = matricula;
        this.login = login;
        this.senha = senha;
    }

    @Override
    public boolean autenticar(String login, String senha) {
        return this.login != null && this.login.equals(login)
                && this.senha != null && this.senha.equals(senha); //salvar em hash depois? para manter integridade da senha
    }

    @Override
    public void alterarSenha(String senhaAntiga, String novaSenha) {
        if (this.senha == null || !this.senha.equals(senhaAntiga)) {
            throw new IllegalArgumentException("Senha antiga incorreta.")
        } else if (senhaNova == null || senhaNova.isBlank()){
            throw new IllegalArgumentException("Nova senha inválida.");
        } else {
            this.senha = novaSenha;
        }
    }

    public abstract List<String> getFuncionalidades();

    @Override
    public String exibirDadosBasicos() {
        return "Nome: " + nome + "\n" +
               "CPF: " + cpf + "\n" +
               "Data de Nascimento: " + dataNascimento + "\n" +
               "Telefone: " + telefone + "\n" +
               "Tipo Sanguíneo: " + tipoSanguineo + "\n" +
               "Matrícula: " + matricula;
    }


    public String getMatricula() { return matricula;}

    public void setMatricula(String matricula) {this.matricula = matricula;}

    public String getLogin() {return login;}

    public void setLogin(String login) {this.login = login;}

    public void getSenhaParaPersistencia() {return senha;}

}