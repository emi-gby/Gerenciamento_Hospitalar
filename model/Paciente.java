package model;

public class Paciente extends Pessoa{
    //WIP (Work in Progress)
    //revisar com o grupo sobre essa classe
    private String convenio;
    private Prontuario prontuario;

    public Paciente(String nome, String cpf, String dataNascimento, String telefone, String tipoSanguineo, int totalPessoasCadastradas, String convenio) {
        super(nome, cpf, dataNascimento, telefone, tipoSanguineo, totalPessoasCadastradas);
        this.convenio = convenio;
        this.prontuario = new Prontuario(0);
    }

    public Paciente(String nome, String cpf, String dataNascimento, String telefone, String tipoSanguineo, int totalPessoasCadastradas) {
        super(nome, cpf, dataNascimento, telefone, tipoSanguineo, totalPessoasCadastradas);
        this.prontuario = new Prontuario(0);
    }

    public String exibirDadosBasicos() {
        return "Nome: " + nome + "\n" +
               "CPF: " + cpf + "\n" +
               "Data de Nascimento: " + dataNascimento + "\n" +
               "Telefone: " + telefone + "\n" +
               "Tipo Sanguíneo: " + tipoSanguineo + "\n" +
               "Convênio: " + convenio;
    }

    public Prontuario getProntuario() {
        return prontuario;
    }
}
