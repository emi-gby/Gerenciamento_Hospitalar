package model;
import java.util.List;

public class Medico extends Funcionario {
    //WIP (Work in Progress)
    private String crm;
    private String especialidade;

    public Medico(String nome, String cpf, String dataNascimento, String telefone, String tipoSanguineo, int totalPessoasCadastradas, String matricula, String login, String senha, String crm, String especialidade) {
        super(nome, cpf, dataNascimento, telefone, tipoSanguineo, totalPessoasCadastradas, matricula, login, senha);
        this.crm = crm;
        this.especialidade = especialidade;
    }
    
    public String exibirDadosBasicos() {
        return super.exibirDadosBasicos() + "\n" +
               "CRM: " + crm + "\n" +
               "Especialidade: " + especialidade;
    }

    @Override
    public List<String> getFuncionalidades() {
        return List.of("lorem", "ipsum"); //para nao quebrar o codigo
    }
}