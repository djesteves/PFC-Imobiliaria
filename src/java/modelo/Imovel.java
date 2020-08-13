/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.Date;

/**
 *
 * @author Diieg
 */
public class Imovel {

    private int id_imovel;
    private double area_total;
    private double area_edificada;
    private double valor;
    private Date data_cadastro;
    private int comodos;
    private int banheiros;
    private int vagas_garagem;
    private String tipo_imovel;
    private String descricao;
    private String status;
    private String titulo;
    private String diretorio_imagem;
    private Endereco endereco;
    private Usuario usuario;

    public Imovel() {
        this.endereco = new Endereco();
        this.usuario = new Usuario();
    }

    public int getId_imovel() {
        return id_imovel;
    }

    public void setId_imovel(int id_imovel) {
        this.id_imovel = id_imovel;
    }

    public double getArea_total() {
        return area_total;
    }

    public void setArea_total(double area_total) {
        this.area_total = area_total;
    }

    public double getArea_edificada() {
        return area_edificada;
    }

    public void setArea_edificada(double area_edificada) {
        this.area_edificada = area_edificada;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public Date getData_cadastro() {
        return data_cadastro;
    }

    public void setData_cadastro(Date data_cadastro) {
        this.data_cadastro = data_cadastro;
    }

    public int getComodos() {
        return comodos;
    }

    public void setComodos(int comodos) {
        this.comodos = comodos;
    }

    public int getBanheiros() {
        return banheiros;
    }

    public void setBanheiros(int banheiros) {
        this.banheiros = banheiros;
    }

    public int getVagas_garagem() {
        return vagas_garagem;
    }

    public void setVagas_garagem(int vagas_garagem) {
        this.vagas_garagem = vagas_garagem;
    }

    public String getTipo_imovel() {
        return tipo_imovel;
    }

    public void setTipo_imovel(String tipo_imovel) {
        this.tipo_imovel = tipo_imovel;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDiretorio_imagem() {
        return diretorio_imagem;
    }

    public void setDiretorio_imagem(String diretorio_imagem) {
        this.diretorio_imagem = diretorio_imagem;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
