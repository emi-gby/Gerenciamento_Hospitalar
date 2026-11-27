package exception;

public class EstoqueSangueInsuficienteException extends Exception{
    // construtor padrão da nossa classe, com mensagem fixa
    public EstoqueSangueInsuficienteException(){
        super("Estoque de sangue insuficiente.");
    }
    // construtor com parametro, podemos detalhar a exceção
    public EstoqueSangueInsuficienteException(String message){
        super(message);
    }
}
