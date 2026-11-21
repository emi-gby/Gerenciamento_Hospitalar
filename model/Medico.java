package model;
import java.util.List;

public class Medico extends Funcionario {
    //WIP (Work in Progress)
    private String crm;
    private String especialidade;

    public Medico(String nome, String cpf, String dataNascimento, String telefone, String tipoSanguineo, String matricula, String login, String senha, String crm, String especialidade) {
        super(nome, cpf, dataNascimento, telefone, tipoSanguineo, matricula, login, senha);
        this.crm = crm;
        this.especialidade = especialidade;
    }

    @Override
    public String exibirDadosBasicos() {
        return super.exibirDadosBasicos() + "\n" +
               "CRM: " + crm + "\n" +
               "Especialidade: " + especialidade;
    }

    @Override
    public List<String> getFuncionalidades() {
        return List.of("Visualizar Agenda",
                        "Editar Prontuário");
    }

    // Fazer validação dos dados??
    public String getEspecialidade() {return especialidade;}

    public void setEspecialidade(String especialidade) {this.especialidade = especialidade;}

    public String getCrm() {return crm;}

    public void setCrm(String crm) {this.crm = crm;}
}