
package Modelo;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Login {

    private String email;
    private String senha;
    private Perfil nivel;
    private String situacao;

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

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
    
    public static String criptografia(String senha) {
        MessageDigest algoritmo;
        byte messageDigest[];
        StringBuilder hexString;
        try {
            //algoritmo = MessageDigest.getInstance("SHA-256");// 64 letras
            algoritmo = MessageDigest.getInstance("MD5");  // 32 letras
            messageDigest = algoritmo.digest(senha.getBytes("UTF-8"));
            hexString = new StringBuilder();
            for (byte b : messageDigest) {
                hexString.append(String.format("%02X", 0xFF & b));
            }
            senha = hexString.toString();
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            System.out.println(e.getMessage());
        }
        return senha;
    }
}
