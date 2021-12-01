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
    
    public cliente clienteC;
    public Caso casoC;
    
    private float honorarios;
    private boolean poderGeneralJudicial;
    private int estadoBD;
    

    public Caso_Civil() {
    }

    public Caso_Civil(int id_caso, String descripcionProblema, int fecha, String tipoCaso, int estadoCaso, float honorarios, boolean poderGeneralJudicial, int estadoBD) {
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

    public boolean isPoderGeneralJudicial() {
        return poderGeneralJudicial;
    }

    public void setPoderGeneralJudicial(boolean poderGeneralJudicial) {
        this.poderGeneralJudicial = poderGeneralJudicial;
    }

    public int getEstadoBD() {
        return estadoBD;
    }

    public void setEstadoBD(int estadoBD) {
        this.estadoBD = estadoBD;
    }

    
}
