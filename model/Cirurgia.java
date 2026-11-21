package model;

public class Cirurgia {
    //WIP (Work in Progress)
    //crítico! o que fazer com o paciente? criar atributo paciente? ou só passar como parâmetro no construtor?
    private int idCirurgia;
    private String dataHora;
    private String tipoCirurgia;
    private boolean isEmergencia;
    private int bolsasSangueNecessarias;
    private String status;

    public Cirurgia(int idCirurgia, String dataHora, String tipoCirurgia, boolean isEmergencia, int bolsasSangueNecessarias, Paciente paciente, String status) {
        this.idCirurgia = idCirurgia;
        this.dataHora = dataHora;
        this.tipoCirurgia = tipoCirurgia;
        this.isEmergencia = isEmergencia;
        this.bolsasSangueNecessarias = bolsasSangueNecessarias;
        //oq fazer com paciente? cria atributo paciente? 
        this.status = status;
    }

    public void autorizarCirurgia(BancoDeSangue banco){
        //lorem ipsum
    }

    public void concluirCirurgia(){
        //lorem ipsum
    }
}
