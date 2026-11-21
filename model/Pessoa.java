package model;

public abstract class Pessoa {
    protected String nome;
    protected String cpf;
    protected String dataNascimento;
    protected String telefone;
    protected String tipoSanguineo;
    protected int totalPessoasCadastradas;

    public Pessoa(String nome, String cpf, String dataNascimento, String telefone, String tipoSanguineo, int totalPessoasCadastradas) {
        this.nome = nome;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
        this.telefone = telefone;
        this.tipoSanguineo = tipoSanguineo;
        this.totalPessoasCadastradas = totalPessoasCadastradas;
    }

    public abstract String exibirDadosBasicos();

    public int getTotalPessoasCadastradas() {
        return totalPessoasCadastradas;
    }
}
