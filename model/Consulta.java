package model;

public class Consulta {
    //WIP (Work in Progress)
    
    private int idConsulta;
    private String dataHora;
    private Paciente paciente;
    private Medico medico;
    private String status;

    public Consulta(int idConsulta, String dataHora, Paciente paciente, Medico medico) {
        this.idConsulta = idConsulta;
        this.dataHora = dataHora;
        this.paciente = paciente;
        this.medico = medico;
    }

    public void cancelar(){
        //lorem ipsum
    }

    public void concluir(){
        //lorem ipsum
    }
}
