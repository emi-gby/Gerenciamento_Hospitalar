package model;
import java.util.List;

public class Recepcionista extends Funcionario {
    //WIP (Work in Progress)
    private String turno;

    public Recepcionista(String nome, String cpf, String dataNascimento, String telefone, String tipoSanguineo, int totalPessoasCadastradas, String matricula, String login, String senha, String turno) {
        super(nome, cpf, dataNascimento, telefone, tipoSanguineo, totalPessoasCadastradas, matricula, login, senha);
        this.turno = turno;
    }
    
    @Override 
    public List<String> getFuncionalidades() {
        return List.of("Lorem", "Ipsum");
    }
    
    public String exibirDadosBasicos() {
        return super.exibirDadosBasicos() + "\n" +
               "Turno: " + turno;
    }
}