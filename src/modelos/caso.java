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
public class Caso{
    
    private int id_caso;
    private String descripcionProblema;
    private String fecha;
    private String tipoCaso; //1 = Civil , 2 = Laboral, 3 = Penal
    private String estadoCaso; 

    public Caso() {
    }

    public Caso(int id_caso, String descripcionProblema, String fecha, String tipoCaso, String estadoCaso) {
        this.id_caso = id_caso;
        this.descripcionProblema = descripcionProblema;
        this.fecha = fecha;
        this.tipoCaso = tipoCaso;
        this.estadoCaso = estadoCaso;
    }

    public int getId_caso() {
        return id_caso;
    }

    public void setId_caso(int id_caso) {
        this.id_caso = id_caso;
    }

    public String getDescripcionProblema() {
        return descripcionProblema;
    }

    public void setDescripcionProblema(String descripcionProblema) {
        this.descripcionProblema = descripcionProblema;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getTipoCaso() {
        return tipoCaso;
    }

    public void setTipoCaso(String tipoCaso) {
        this.tipoCaso = tipoCaso;
    }

    public String getEstadoCaso() {
        return estadoCaso;
    }

    public void setEstadoCaso(String estadoCaso) {
        this.estadoCaso = estadoCaso;
    }

    

    
}
