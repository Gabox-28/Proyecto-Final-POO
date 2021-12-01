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
    
    private cliente clienteL;
    private Caso casoL;
    
    private float montoALitigar;
    private boolean poderGeneralJudicial;
    private int porcentajeGanancia;
    private int estadoBD;

    public Caso_Laboral() {
    }

    public Caso_Laboral(int id_caso, String descripcionProblema, int fecha, String tipoCaso, int estadoCaso, float montoALitigar, boolean poderGeneralJudicial, int porcentajeGanancia, int estadoBD) {
        super(id_caso, descripcionProblema, fecha, tipoCaso, estadoCaso);
        this.montoALitigar = montoALitigar;
        this.poderGeneralJudicial = poderGeneralJudicial;
        this.porcentajeGanancia = porcentajeGanancia;
        this.estadoBD = estadoBD;
    }

    public cliente getClienteL() {
        return clienteL;
    }

    public void setClienteL(cliente clienteL) {
        this.clienteL = clienteL;
    }

    public Caso getCasoL() {
        return casoL;
    }

    public void setCasoL(Caso casoL) {
        this.casoL = casoL;
    }

    public float getMontoALitigar() {
        return montoALitigar;
    }

    public void setMontoALitigar(float montoALitigar) {
        this.montoALitigar = montoALitigar;
    }

    public boolean isPoderGeneralJudicial() {
        return poderGeneralJudicial;
    }

    public void setPoderGeneralJudicial(boolean poderGeneralJudicial) {
        this.poderGeneralJudicial = poderGeneralJudicial;
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

    
}
