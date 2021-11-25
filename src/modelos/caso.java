/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

/**
 *
 * @author Gabox
 */
public class Caso {
    
    private String descripcionProblema;
    private int estado;
    private int fecha;
    private String tipoCaso;

    public Caso() {
    }

    public Caso(String descripcionProblema, int estado, int fecha, String tipoCaso) {
        this.descripcionProblema = descripcionProblema;
        this.estado = estado;
        this.fecha = fecha;
        this.tipoCaso = tipoCaso;
    }

    public String getDescripcionProblema() {
        return descripcionProblema;
    }

    public void setDescripcionProblema(String descripcionProblema) {
        this.descripcionProblema = descripcionProblema;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public int getFecha() {
        return fecha;
    }

    public void setFecha(int fecha) {
        this.fecha = fecha;
    }

    public String getTipoCaso() {
        return tipoCaso;
    }

    public void setTipoCaso(String tipoCaso) {
        this.tipoCaso = tipoCaso;
    }
    
    
    
}
