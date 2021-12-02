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
public class Caso_Civil extends Caso{
    
    
    private float honorarios;
    private String poderGeneralJudicial;
    private int estadoBD;
    

    public Caso_Civil() {
    }

    public Caso_Civil(int id_caso, String descripcionProblema, String fecha, String tipoCaso, String estadoCaso, float honorarios, String poderGeneralJudicial, int estadoBD) {
        super(id_caso, descripcionProblema, fecha, tipoCaso, estadoCaso);
        this.honorarios = honorarios;
        this.poderGeneralJudicial = poderGeneralJudicial;
        this.estadoBD = estadoBD;
    }

    

    public float getHonorarios() {
        return honorarios;
    }

    public void setHonorarios(float honorarios) {
        this.honorarios = honorarios;
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
