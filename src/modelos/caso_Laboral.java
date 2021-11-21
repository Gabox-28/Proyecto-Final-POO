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
public class caso_Laboral {
    
    private float montoALitigar;
    private boolean poderGeneralJudicial;
    private int porcentajeGanancia;

    public caso_Laboral() {
    }

    public caso_Laboral(float montoALitigar, boolean poderGeneralJudicial, int porcentajeGanancia) {
        this.montoALitigar = montoALitigar;
        this.poderGeneralJudicial = poderGeneralJudicial;
        this.porcentajeGanancia = porcentajeGanancia;
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
    
    public float calcularHonorarios(int porcentajeGanancia, float montoALitigar){
        float honorarios = 0;
        try{
            honorarios = montoALitigar*(porcentajeGanancia / 100);
            return honorarios;
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return honorarios;
    }
}
