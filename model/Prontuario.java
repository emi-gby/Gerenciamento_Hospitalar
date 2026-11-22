package model;
import java.util.ArrayList;
import java.util.List;

public class Prontuario{
    //WIP (Work in Progress)
    private int idProntuario;
    private List<String> historicoRegistros;
    private List<String> alergias;

    public Prontuario(int idProntuario) {
        this.idProntuario = idProntuario;
        this.historicoRegistros=new ArrayList <>();
        this.alergias=new ArrayList<> ();
    }

    public void adicionarRegistro(Medico medico, String descricao) {
       // adiciona o registro com a descricao fornecida pela recepcionista, incluindo o nome do médico que realizou o atendimento
       if(medico==null){
        throw new IllegalArgumentException("Médico não informado.");
       }
       if(descricao.isEmpty()){
        throw new IllegalArgumentException("Descrição não informada.");
       }
       String texto="Médico: " + medico.nome + "\n" + "Descrição: " + descricao;
       historicoRegistros.add(texto);
    }

    public void adicionarRegistro(Medico medico, String descricao, String prescricao) {
        // adiciona o registro com a descricao e prescricao fornecidas pela recepcionista, incluindo o nome do médico que realizou o atendimento
        if(medico==null){
            throw new IllegalArgumentException("Médico não informado.");
        }
        if(descricao.isEmpty()){
            throw new IllegalArgumentException("Descrição não informada.");
        }
        if(prescricao.isEmpty()){
           throw new IllegalArgumentException("Prescrição não informada.");
        }
        String texto="Médico: " + medico.nome + "\n" + "Descrição: " + descricao + "\n" + "Prescrição: " + prescricao;
        historicoRegistros.add(texto);
    }

    public void adicionarAlergia(String alergia) {
        if(alergia==null || alergia.isBlank()) {
            throw new IllegalArgumentException("Alergia nâo informada.");
        }
        if (alergias.contains(alergia.trim())) {
            throw new IllegalArgumentException("Alergia já existente.");
        }
        alergias.add(alergia.trim());
    }

    public List<String> consultarRegistros() {
        return historicoRegistros;
    }

    public List<String> getAlergias() {
        return alergias;
    }
}
