package model;

public class Consulta {
    //WIP (Work in Progress)
    private int idConsulta;
    private String dataHora;
    private Paciente paciente;
    private Medico medico;
    private String status;

    public Consulta(int idConsulta, String dataHora, Paciente paciente, Medico medico) {
        if (paciente == null || medico == null) {
            throw new IllegalArgumentException("Paciente e médico são obrigatórios para agendar a consulta.");
        }
        this.idConsulta = idConsulta;
        this.dataHora = dataHora;
        this.paciente = paciente;
        this.medico = medico;
        status = "Agendada";
    }

    public void cancelar(){
        if (status.equals("Concluida")) {
            throw new IllegalStateException("Consulta concluida não pode ser cancelada.");
        }
        status = "Cancelada";
    }

    public void concluir(){
        //lorem ipsum
    }

    public int getIdConsulta() {return idConsulta;}

    public String getDataHora() {return dataHora;}

    public Paciente getPaciente() {return paciente;}

    public Medico getMedico() {return medico;}

    public String getStatus() {return status;}
}
