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
public class Caso_Laboral extends Caso {
    
    
    private float montoALitigar;
    private String poderGeneralJudicial;
    private int porcentajeGanancia;
    private int estadoBD;

    public Caso_Laboral() {
    }

    public Caso_Laboral(int id_caso, String descripcionProblema, String fecha, String tipoCaso, String estadoCaso, float montoALitigar, String poderGeneralJudicial, int porcentajeGanancia, int estadoBD) {
        super(id_caso, descripcionProblema, fecha, tipoCaso, estadoCaso);
        this.montoALitigar = montoALitigar;
        this.poderGeneralJudicial = poderGeneralJudicial;
        this.porcentajeGanancia = porcentajeGanancia;
        this.estadoBD = estadoBD;
    }


    


    public float getMontoALitigar() {
        return montoALitigar;
    }

    public void setMontoALitigar(float montoALitigar) {
        this.montoALitigar = montoALitigar;
    }

    

    public int getPorcentajeGanancia() {
        return porcentajeGanancia;
    }

    public void setPorcentajeGanancia(int porcentajeGanancia) {
        this.porcentajeGanancia = porcentajeGanancia;
    }

    public int getEstadoBD() {
        return estadoBD;
    }

    public void setEstadoBD(int estadoBD) {
        this.estadoBD = estadoBD;
    }

    public String getPoderGeneralJudicial() {
        return poderGeneralJudicial;
    }

    public void setPoderGeneralJudicial(String poderGeneralJudicial) {
        this.poderGeneralJudicial = poderGeneralJudicial;
    }

    
}
