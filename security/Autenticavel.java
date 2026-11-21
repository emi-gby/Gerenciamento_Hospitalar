package security;

public interface Autenticavel {
    public boolean autenticar(String login, String senha);
    public void alterarSenha(String novaSenha);
}
