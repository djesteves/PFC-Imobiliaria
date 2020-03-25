
package modelo;

public class Login {

    private String email;
    private String senha;
    private Perfil nivel;

    public Perfil getNivel() {
        return nivel;
    }

    public void setNivel(Perfil nivel) {
        this.nivel = nivel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
