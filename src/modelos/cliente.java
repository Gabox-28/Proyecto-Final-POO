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
public class Cliente {
    
    private String apellios;
    private String cedula;
    private String direccionDomicilo;
    private int edad;
    private String estadoCivil;
    private String nombres;
    private String profesion;
    private boolean sexo;

    public Cliente() {
    }

    public Cliente(String apellios, String cedula, String direccionDomicilo, int edad, String estadoCivil, String nombres, String profesion, boolean sexo) {
        this.apellios = apellios;
        this.cedula = cedula;
        this.direccionDomicilo = direccionDomicilo;
        this.edad = edad;
        this.estadoCivil = estadoCivil;
        this.nombres = nombres;
        this.profesion = profesion;
        this.sexo = sexo;
    }

    public String getApellios() {
        return apellios;
    }

    public void setApellios(String apellios) {
        this.apellios = apellios;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getDireccionDomicilo() {
        return direccionDomicilo;
    }

    public void setDireccionDomicilo(String direccionDomicilo) {
        this.direccionDomicilo = direccionDomicilo;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(String estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getProfesion() {
        return profesion;
    }

    public void setProfesion(String profesion) {
        this.profesion = profesion;
    }

    public boolean isSexo() {
        return sexo;
    }

    public void setSexo(boolean sexo) {
        this.sexo = sexo;
    }
    
    
    
    
}
