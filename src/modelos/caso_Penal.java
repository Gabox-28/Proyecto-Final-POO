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
public class Caso_Penal extends Caso{
    public cliente clienteP;
    public Caso casoP;
    private float honorarios;
    private boolean nombramientoDefensor;
    private int estadoBD;

    public Caso_Penal() {
    }

    public Caso_Penal(int id_caso, String descripcionProblema, int fecha, String tipoCaso, int estadoCaso, float honorarios, boolean nombramientoDefensor, int estadoBD) {
        super(id_caso, descripcionProblema, fecha, tipoCaso, estadoCaso);
        this.honorarios = honorarios;
        this.nombramientoDefensor = nombramientoDefensor;
        this.estadoBD = estadoBD;
    }

    public cliente getClienteP() {
        return clienteP;
    }

    public void setClienteP(cliente clienteP) {
        this.clienteP = clienteP;
    }

    public Caso getCasoP() {
        return casoP;
    }

    public void setCasoP(Caso casoP) {
        this.casoP = casoP;
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

    public int getEstadoBD() {
        return estadoBD;
    }

    public void setEstadoBD(int estadoBD) {
        this.estadoBD = estadoBD;
    }
    
}
