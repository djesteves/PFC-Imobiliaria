/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.util.Date;

/**
 *
 * @author Diego
 */
public class Contrato {

    private int id_contrato;
    private double valor_fechado;
    private Usuario usuarioCorretor;
    private Imovel imovel;
    private Usuario usuarioComprador;
    private Usuario usuarioVendedor;
    private Date dataContrato;

    public Contrato() {
        this.usuarioCorretor = new Usuario();
        this.usuarioComprador = new Usuario();
        this.usuarioVendedor = new Usuario();
        this.imovel = new Imovel();
    }

    public int getId_contrato() {
        return id_contrato;
    }

    public void setId_contrato(int id_contrato) {
        this.id_contrato = id_contrato;
    }

    public double getValor_fechado() {
        return valor_fechado;
    }

    public void setValor_fechado(double valor_fechado) {
        this.valor_fechado = valor_fechado;
    }

    public Usuario getCorretor() {
        return usuarioCorretor;
    }

    public void setCorretor(Usuario usuarioCorretor) {
        this.usuarioCorretor = usuarioCorretor;
    }

    public Imovel getImovel() {
        return imovel;
    }

    public void setImovel(Imovel imovel) {
        this.imovel = imovel;
    }

    public Usuario getUsuarioComprador() {
        return usuarioComprador;
    }

    public void setUsuarioComprador(Usuario usuarioComprador) {
        this.usuarioComprador = usuarioComprador;
    }

    public Usuario getUsuarioVendedor() {
        return usuarioVendedor;
    }

    public void setUsuarioVendedor(Usuario usuarioVendedor) {
        this.usuarioVendedor = usuarioVendedor;
    }

    public Date getDataContrato() {
        return dataContrato;
    }

    public void setDataContrato(Date dataContrato) {
        this.dataContrato = dataContrato;
    }

}
