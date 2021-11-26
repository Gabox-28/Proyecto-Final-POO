/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

/**
 *
 * @author Usuario
 */
public class Caso_Penal {
    public cliente clienteP;
    public Caso casoP;
    private float honorarios;
    private boolean nombramientoDefensor;

    public Caso_Penal() {
    }

    public Caso_Penal(float honorarios, boolean nombramientoDefensor) {
        this.honorarios = honorarios;
        this.nombramientoDefensor = nombramientoDefensor;
    }

    public float getHonorarios() {
        return honorarios;
    }

    public void setHonorarios(float honorarios) {
        this.honorarios = honorarios;
    }

    public boolean isNombramientoDefensor() {
        return nombramientoDefensor;
    }

    public void setNombramientoDefensor(boolean nombramientoDefensor) {
        this.nombramientoDefensor = nombramientoDefensor;
    }
    
}
