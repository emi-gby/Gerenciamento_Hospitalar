package model;
import java.util.List;

public class Recepcionista extends Funcionario {
    //WIP (Work in Progress)
    private String turno;

    public Recepcionista(String nome, String cpf, String dataNascimento, String telefone, String tipoSanguineo, String matricula, String login, String senha, String turno) {
        super(nome, cpf, dataNascimento, telefone, tipoSanguineo, matricula, login, senha);
        this.turno = turno;
    }
    
    @Override 
    public List<String> getFuncionalidades() {
        return List.of("Cadastrar Paciente",
                        "Agendar Consulta",
                        "Cancelar Consulta",
                        "Solicitar Cirurgia",
                        "Registrar Doação de Sangue",
                        "Consultar Banco de Sangue");
    }

    @Override
    public String exibirDadosBasicos() {
        return super.exibirDadosBasicos() + "\n" +
               "Turno: " + turno;
    }

    public String getTurno() {return turno;}

    public void setTurno(String turno) {this.turno = turno;}
}