package model;
import exception.EstoqueSangueInsuficienteException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BancoDeSangue{
    //WIP (Work in Progress)
    private Map<String, Integer> estoqueSangue;
    private List<Doacao> historicoDoacoes;

    public BancoDeSangue(){
        this.estoqueSangue=new HashMap<> ();
        this.historicoDoacoes=new ArrayList<> ();
    }

    public void registrarDoacao(Pessoa doador, int quantidadeBolsas) {
        if(doador==null || quantidadeBolsas<=0){
            throw new IllegalArgumentException("Doação não realizada.");
        }
        String tipo=padronizar(doador.getTipoSanguineo());
        if(tipo.isEmpty()){
            throw new IllegalArgumentException("Tipo sanguíneo não informado.");
        }
        estoqueSangue.put(tipo, estoqueSangue.getOrDefault(tipo,0) + quantidadeBolsas);
        int id=historicoDoacoes.size() + 1;
        String data=LocalDate.now().toString();
        historicoDoacoes.add(new Doacao(id,doador,data,tipo,quantidadeBolsas));
    }

    public boolean verificarDisponibilidade(String tipoSanguineo, int qtd) {
        String tipo=padronizar(tipoSanguineo);
        return estoqueSangue.getOrDefault(tipo,0)>=qtd;
    }

    public void consumirBolsas(String tipoSanguineo, int qtd) throws EstoqueSangueInsuficienteException {
        if(qtd<=0){
            throw new IllegalArgumentException("Quantidade inválida.");
        }
        String tipo=padronizar(tipoSanguineo);
        int disponivel=estoqueSangue.getOrDefault(tipo,0);
        if(disponivel<qtd){
            throw new EstoqueSangueInsuficienteException("Estoque insuficiente de " + tipo + ": solicitado " + qtd + ", disponível "+ disponivel + ".");
        }
        estoqueSangue.put(tipo,disponivel - qtd);
    }
    //pra diferenciar letras minúsculas das maiúsculas no map
    private String padronizar(String tipoSanguineo){
        if(tipoSanguineo==null){
            return "";
        }
        return tipoSanguineo.trim().toUpperCase();
    }
    // método que retorna a quantidade de bolsas no estoque
    public int consultarEstoque(String tipoSanguineo){
        return estoqueSangue.getOrDefault(padronizar(tipoSanguineo),0);
    }
    // exibe tbm os tipos
    public Map<String, Integer> consultarEstoque(){
        return new HashMap<>(estoqueSangue);
    }
    //retorna lista de doações
    //esse método só vai funcionar se tiver os getters em Doacao
    public List<Doacao> getHistoricoDoacoes(){
        return Collections.unmodifiableList(historicoDoacoes);
    }
}
