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
public class cliente {
    
    private int cliente_id;
    private String nombres;
    private String apellidos;
    private String direccionDomicilio;
    private boolean sexo;
    private String estadoCivil;
    private String profesion;
    private int edad;
    private String cedula;
    private int estado;

    public cliente() {
    }

    public cliente(int cliente_id, String nombres, String apellidos, String direccionDomicilio, boolean sexo, String estadoCivil, String profesion, int edad, String cedula, int estado) {
        this.cliente_id = cliente_id;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.direccionDomicilio = direccionDomicilio;
        this.sexo = sexo;
        this.estadoCivil = estadoCivil;
        this.profesion = profesion;
        this.edad = edad;
        this.cedula = cedula;
        this.estado = estado;
    }

    public int getCliente_id() {
        return cliente_id;
    }

    public void setCliente_id(int cliente_id) {
        this.cliente_id = cliente_id;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getDireccionDomicilio() {
        return direccionDomicilio;
    }

    public void setDireccionDomicilio(String direccionDomicilio) {
        this.direccionDomicilio = direccionDomicilio;
    }

    public boolean isSexo() {
        return sexo;
    }

    public void setSexo(boolean sexo) {
        this.sexo = sexo;
    }

    public String getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(String estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    public String getProfesion() {
        return profesion;
    }

    public void setProfesion(String profesion) {
        this.profesion = profesion;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    
}
