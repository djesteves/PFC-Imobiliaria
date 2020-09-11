/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author Diego
 */
public class Sessao {

    private String nome;
    private int id_usuario;
    private Perfil nivel;
    private String email;

    public Sessao(String nome, int id_usuario, Perfil nivel, String email) {
        this.nome = nome;
        this.id_usuario = id_usuario;
        this.nivel = nivel;
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
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

   

}
