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
}