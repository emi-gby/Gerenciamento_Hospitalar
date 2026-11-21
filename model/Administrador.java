package model;
import java.util.List;

public class Administrador extends Funcionario {
    //WIP (Work in Progress)

    // Nivel de Acesso = 1 (Cadastrar, Editar, Listas)
    // Nivel de Acesso = 2 (Deletar)
    private int nivelAcesso;

    public Administrador(String nome, String cpf, String dataNascimento, String telefone, String tipoSanguineo, String matricula, String login, String senha, int nivelAcesso) {
        super(nome, cpf, dataNascimento, telefone, tipoSanguineo, matricula, login, senha);
        this.nivelAcesso = nivelAcesso;
    }

    @Override
    public List<String> getFuncionalidades() {
        List<String> funcionalidades = new ArrayList<>();
        funcionalidades.add("Cadastrar Funcionário");
        funcionalidades.add("Editar Funcionário");
        funcionalidades.add("Listar Funcionários");
        if (this.nivelAcesso > 1) {
            funcionalidades.add("Remover Funcionário");
        }
        return funcionalidades;
    }

    @Override
    public String exibirDadosBasicos() {
        return super.exibirDadosBasicos() + "\n" +
               "Nível de Acesso: " + nivelAcesso;
    }

    public int getNivelAcesso() {return nivelAcesso;}

    public void setNivelAcesso(int nivelAcesso) {
        if (nivelAcesso < 1) {
            throw new IllegalArgumentException("Nível de acesso deve ser maior ou igual a 1.");
        }
        this.nivelAcesso = nivelAcesso;
    }
}