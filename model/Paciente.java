package model;

public class Paciente extends Pessoa{
    //WIP (Work in Progress)
    //revisar com o grupo sobre essa classe !!!!
    private String convenio;
    private Prontuario prontuario;

    public Paciente(String nome, String cpf, String dataNascimento, String telefone, String tipoSanguineo, String convenio) {
        super(nome, cpf, dataNascimento, telefone, tipoSanguineo);
        this.convenio = convenio;
        // É necessário fazer um contador em prontuário para o id.
        this.prontuario = new Prontuario(0);
    }

    // Cadastro de Emergência
    public Paciente(String nome, String cpf, String tipoSanguineo) {
        super(nome, cpf, dataNascimento, telefone, tipoSanguineo);
        this.convenio = "Sem convẽnio"; // valor default para caso de cadastro de emergencia
        // É necessário fazer um contador em prontuário para o id.
        this.prontuario = new Prontuario(0);
    }

    @Override
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

    public String getConvenio() {return convenio;}

    public void setConvenio(String convenio) {this.convenio = convenio;}

    public void setProntuario(Prontuario prontuario) {this.prontuario = prontuario;}
}
