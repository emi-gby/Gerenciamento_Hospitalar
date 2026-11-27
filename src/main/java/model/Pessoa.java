package model;

public abstract class Pessoa {
    protected String nome;
    protected String cpf;
    protected String dataNascimento;
    protected String telefone;
    protected String tipoSanguineo;
    static int totalPessoasCadastradas = 0;

    public Pessoa(String nome, String cpf, String dataNascimento, String telefone, String tipoSanguineo) {
        this.nome = nome;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
        this.telefone = telefone;
        this.tipoSanguineo = tipoSanguineo;
        totalPessoasCadastradas++;
    }

    public abstract String exibirDadosBasicos();

    public int getTotalPessoasCadastradas() {
        return totalPessoasCadastradas;
    }

    public String getNome() {return nome;}

    public void setNome(String nome) {this.nome = nome;}

    public String getCpf() {return cpf;}

    public void setCpf(String cpf) {this.cpf = cpf;}

    public String getDataNascimento() {return dataNascimento;}

    public void setDataNascimento(String dataNascimento) {this.dataNascimento = dataNascimento;}

    public String getTelefone() {return telefone;}

    public void setTelefone(String telefone) {this.telefone = telefone;}

    public String getTipoSanguineo() {return tipoSanguineo;}

    public void setTipoSanguineo(String tipoSanguineo) {this.tipoSanguineo = tipoSanguineo;}
}
