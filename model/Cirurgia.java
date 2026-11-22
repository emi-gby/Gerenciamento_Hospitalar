package model;

public class Cirurgia {
    //WIP (Work in Progress)

    private int idCirurgia;
    private Paciente paciente;
    private Medico medico;
    private String dataHora;
    private String tipoCirurgia;
    private boolean isEmergencia;
    private int bolsasSangueNecessarias;
    private String status;

    public Cirurgia(int idCirurgia, Paciente paciente, Medico medico, String dataHora, String tipoCirurgia, boolean isEmergencia, int bolsasSangueNecessarias) {
        if (paciente == null || medico == null) {
            throw new IllegalArgumentException("Paciente e médico são obrigatórios para agendar a cirurgia.");
        }
        this.idCirurgia = idCirurgia;
        this.paciente = paciente;
        this.medico = medico;
        this.dataHora = dataHora;
        this.tipoCirurgia = tipoCirurgia;
        this.isEmergencia = isEmergencia;
        this.bolsasSangueNecessarias = bolsasSangueNecessarias;
        this.status = "Agendada";
    }

    public void autorizarCirurgia(BancoDeSangue banco){
        if (banco == null) {
            throw new IllegalArgumentException("Banco de Sangue não informado.");
        }
        if (status.equals("Concluida")) {
            throw new IllegalStateException("Cirurgia já concluida.");
        }

        if(!banco.verificarDisponibilidade(paciente.getTipoSanguineo(), bolsasSangueNecessarias)) {
            status = "Não autorizada";
            throw new IllegalStateException("Bolsas de sangue insuficientes.");
        }
        status = "Autorizada";
    }

    public void concluirCirurgia(BancoDeSangue banco){
        if (status.equals("Não autorizada")) {
            throw new IllegalStateException("Cirurgia não autorizada não pode ser concluida.");
        }
        if (status.equals("Agendada")) {
            throw new IllegalStateException("Cirurgia precisa de autorização.");
        }
        if (status.equals("Concluida")) {
            throw new IllegalStateException("Cirurgia já concluida.");
        }
        if (banco == null) {
            throw new IllegalArgumentException("Banco de Sangue não informado.");
        }
        banco.consumirBolsas(paciente.getTipoSanguineo(), bolsasSangueNecessarias);
        status = "Concluida";
    }

    public int getIdCirurgia() {return idCirurgia;}

    public void setIdCirurgia(int idCirurgia) {this.idCirurgia = idCirurgia;}

    public Paciente getPaciente() {return paciente;}

    public void setPaciente(Paciente paciente) {this.paciente = paciente;}

    public Medico getMedico() {return medico;}

    public void setMedico(Medico medico) {this.medico = medico;}

    public String getDataHora() {return dataHora;}

    public void setDataHora(String dataHora) {this.dataHora = dataHora;}

    public String getTipoCirurgia() {return tipoCirurgia;}

    public void setTipoCirurgia(String tipoCirurgia) {this.tipoCirurgia = tipoCirurgia;}

    public int getBolsasSangueNecessarias() {return bolsasSangueNecessarias;}

    public void setBolsasSangueNecessarias(int bolsasSangueNecessarias) {this.bolsasSangueNecessarias = bolsasSangueNecessarias;}

    public boolean isEmergencia() {return isEmergencia;}

    public void setEmergencia(boolean emergencia) {isEmergencia = emergencia;}

    public String getStatus() {return status;}

}
