package model;
import java.util.List;

public class Prontuario {
    //WIP (Work in Progress)
    private int idProntuario;
    private List<String> historicoRegistros;
    private List<String> alergias;

    public Prontuario(int idProntuario) {
        this.idProntuario = idProntuario;
    }

    public void adicionarRegistro(Medico medico, String descricao) {
       // adiciona o registro com a descricao fornecida pela recepcionista, incluindo o nome do médico que realizou o atendimento
    }

    public void adicionarRegistro(Medico medico, String descricao, String prescricao) {
        // adiciona o registro com a descricao e prescricao fornecidas pela recepcionista, incluindo o nome do médico que realizou o atendimento
    }

    public List<String> consultarHistorico() {
        //oq isso deveria retornar? ToT
        return null; //pra nao quebrar o codigo
    }
}
