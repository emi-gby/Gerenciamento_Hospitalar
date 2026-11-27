package model;

public class Doacao {
    private int idDoacao;
    private Pessoa doador;
    private String dataDoacao;
    private String tipoSanguineo;
    private int quantidadeBolsas;

    public Doacao(int idDoacao, Pessoa doador, String dataDoacao, String tipoSanguineo, int quantidadeBolsas) {
        this.idDoacao = idDoacao;
        this.doador = doador;
        this.dataDoacao = dataDoacao;
        this.tipoSanguineo = tipoSanguineo;
        this.quantidadeBolsas = quantidadeBolsas;
    }

    public int getIdDoacao(){
        return idDoacao;
    }
    public Pessoa Doador(){
        return doador;
    }
    public String getDataDoacao(){
        return dataDoacao;
    }
    public String getTipoSanguineo(){
        return tipoSanguineo;
    }
    public int getQuantidadeBolsas(){
        return quantidadeBolsas;
    }
}