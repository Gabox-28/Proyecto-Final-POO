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

    private float honorarios;
    private String nombramientoDefensor;
    private int estadoBD;

    public Caso_Penal() {
    }

    public Caso_Penal(int id_caso, String descripcionProblema, String fecha, String tipoCaso, String estadoCaso, float honorarios, String nombramientoDefensor, int estadoBD) {
        super(id_caso, descripcionProblema, fecha, tipoCaso, estadoCaso);
        this.honorarios = honorarios;
        this.nombramientoDefensor = nombramientoDefensor;
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

    public String getNombramientoDefensor() {
        return nombramientoDefensor;
    }

    public void setNombramientoDefensor(String nombramientoDefensor) {
        this.nombramientoDefensor = nombramientoDefensor;
    }
    
}
