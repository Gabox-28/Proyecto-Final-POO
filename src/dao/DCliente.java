/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import modelos.cliente;
/**
 *
 * @author Gabox
 */

public class DCliente {
    
    private ArrayList<cliente> lista;
    private final Conexion conexion = new Conexion();
    private Connection conn; // Gestiona la conexion.
    private PreparedStatement mostrarRegistros;
    private PreparedStatement insertarRegistro;
    private PreparedStatement modificarRegistro;
    private PreparedStatement eliminarRegistro;
    
    public DCliente(){
        try{
            conn = conexion.obtenerConexion();
            mostrarRegistros = conn.prepareStatement("Select * from Cliente");
            insertarRegistro = conn.prepareStatement("Insert Into Cliente(cedula, first_name,"
                    + " last_name, direccion, sex, civil_status, occupation, age) Values(?, ?, ?, ?, ?, ? ,?, ?)");
            modificarRegistro = conn.prepareStatement("Update Cliente set cedula = ?, first_name = ?,"
                    + " last_name = ?, direccion = ?, sex = ?, civil_status = ?, occupation = ?, age = ? where Cliente_id = ?");
            eliminarRegistro = conn.prepareStatement("Delete From Cliente where Cliente_id = ?");
            lista = new ArrayList<>();
            
            lista = listarRegistro();
        }catch (SQLException ex) {
            System.out.println(ex.getMessage());
            System.exit(1);
        }
    }
    
    private ArrayList<cliente> listarRegistro() {
        ArrayList<cliente> result = null;
        ResultSet rs = null;
        try{
            rs = mostrarRegistros.executeQuery();
            result = new ArrayList<>();
            while(rs.next()){
                result.add(new cliente(
                        rs.getInt("cliente_id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("direccion"),
                        rs.getString("sex"),
                        rs.getString("civil_status"),
                        rs.getString("occupation"),
                        rs.getInt("age"),
                        rs.getString("cedula"),
                        1 // estado Original que viene desde la BD
                ));
            }
        }catch(SQLException ex){
            ex.printStackTrace();
        }finally{
            try{
                rs.close();
            }catch(SQLException ex){
                conexion.close(conn);
            }
        }
        return result;
    }
    
    public int agregarCliente(String nombres, String apellidos, String direccionDomicilio, String sexo, String estadoCivil, String profesion, int edad, String cedula){
        try{
            lista.add(new cliente(0, 
                    nombres, 
                    apellidos,
                    direccionDomicilio,
                    sexo,
                    estadoCivil,
                    profesion,
                    edad,
                    cedula,
                    4 // estado Nuevo registro sin guardar en la BD 
                    ));
            return 1;
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }  
        return 0;
    }
    
    public int editarCliente(int cliente_id, String nombres, String apellidos, String direccionDomicilio, String sexo, String estadoCivil, String profesion, int edad, String cedula){
        try{
            cliente cliente = new cliente(
                    cliente_id, 
                    nombres, 
                    apellidos,
                    direccionDomicilio,
                    sexo,
                    estadoCivil,
                    profesion,
                    edad,
                    cedula, 
                    2 // estado Modificado en la lista, no guardado en la BD
            );
            for(cliente a: lista){
               if(a.getCliente_id()== cliente.getCliente_id()){
                   a.setNombres(cliente.getNombres());
                   a.setApellidos(cliente.getApellidos());
                   a.setDireccionDomicilio(cliente.getDireccionDomicilio());
                   a.setSexo(cliente.getSexo());
                   a.setEstadoCivil(cliente.getEstadoCivil());
                   a.setProfesion(cliente.getProfesion());
                   a.setEdad(cliente.getEdad());
                   a.setCedula(cliente.getCedula());
                   if(a.getEstado()!=0) a.setEstado(cliente.getEstado()); 
                   return 1;
               } 
            }
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return 0;
    }
    
    public int eliminarCliente(int cliente_id){
        try{
            for(cliente a : lista){
                if(a.getCliente_id() == cliente_id){
                    a.setEstado(3); //estado Eliminado en la lista,
                    //aun no eliminado en la base de datos.
                    return 1;
                }         
            }
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return 0;
    }
    
    public int agregarRegistroBD(cliente cliente) {
        int result = 0;
        try {
            insertarRegistro.setString(1, cliente.getCedula());
            insertarRegistro.setString(2, cliente.getNombres());
            insertarRegistro.setString(3, cliente.getApellidos());
            insertarRegistro.setString(4, cliente.getDireccionDomicilio());
            insertarRegistro.setString(5, cliente.getSexo());
            insertarRegistro.setString(6, cliente.getEstadoCivil());
            insertarRegistro.setString(7, cliente.getProfesion());
            insertarRegistro.setInt(8, cliente.getEdad());
            
            result = insertarRegistro.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            conexion.close(conn);
        }
        return result;
    }
    
    public int modificarRegistroBD(cliente cliente) {
        int result = 0;
        try {
            modificarRegistro.setString(1, cliente.getCedula());
            modificarRegistro.setString(2, cliente.getNombres());
            modificarRegistro.setString(3, cliente.getApellidos());
            modificarRegistro.setString(4, cliente.getDireccionDomicilio());
            modificarRegistro.setString(5, cliente.getSexo());
            modificarRegistro.setString(6, cliente.getEstadoCivil());
            modificarRegistro.setString(7, cliente.getProfesion());
            modificarRegistro.setInt(8, cliente.getEdad());
            modificarRegistro.setInt(9, cliente.getCliente_id());
            result = modificarRegistro.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            conexion.close(conn);
        }
        return result;
    }
    
    public int eliminarRegistroBD(cliente cliente) {
        int result = 0;
        try {
            eliminarRegistro.setInt(1, cliente.getCliente_id());

            result = eliminarRegistro.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            conexion.close(conn);
        }
        return result;
    }
    
    public String actualizarBD() {
        String msn = "";
        String msnError = "Errores en: ";
        int nuevos = 0, modificados = 0, eliminados = 0;
        int errorNuevos = 0, errorModificados = 0, errorEliminados = 0;
        for (cliente cliente : lista) { 
            switch (cliente.getEstado()) {
                case 1:
                    //Si es original no hace nada
                    break;
                case 2:
                    //Estado modificado
                    if (this.modificarRegistroBD(cliente) != 0) {
                        modificados++;
                    } else {
                        errorModificados++;
                        msnError += "\n - Error al modificar: " + cliente.getNombres() 
                                + " " + cliente.getApellidos();
                    }
                    break;
                case 3:
                    //Estado eliminado
                    if (this.eliminarRegistroBD(cliente) != 0) {
                        eliminados++;
                    } else {
                        errorEliminados++;
                        msnError += "\n - Error al eliminar: " + cliente.getNombres() 
                                + " " + cliente.getApellidos();
                    }
                    break;
                case 4:
                    //Estado nuevo registro
                    if (this.agregarRegistroBD(cliente) != 0) {
                        nuevos++;
                    } else {
                        errorNuevos++;
                        msnError += "\n -Error al agregar nuevo registro: "  
                                + cliente.getNombres() + " " + cliente.getApellidos();
                    }
                    break;
                default:
                    msnError+="\n Revise el registro: "+ cliente.getNombres() 
                            + " " + cliente.getApellidos();
                    break;
            }
        }
        msn = "Registros guardados: " + nuevos + "\nRegistros editados: "+ modificados+
                "\nRegistros eliminados: " + eliminados;
        
        if (!msnError.equals("Errores en: ")){
            msn+="\n"+ msnError;
        }        
        lista = this.listarRegistro();
        return msn;
    }
    
    public ArrayList<cliente> getLista() {
        return lista;
    }
}
