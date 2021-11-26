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
public class Caso_Civil {
    
    public cliente clienteC;
    public Caso casoC;
    private float honorarios;
    private boolean poderGeneralJudicial;
    

    public Caso_Civil() {
    }

    public Caso_Civil(float honorarios, boolean poderGeneralJudicial) {
        this.honorarios = honorarios;
        this.poderGeneralJudicial = poderGeneralJudicial;
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
    
    
}
