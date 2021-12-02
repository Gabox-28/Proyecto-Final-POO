/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import modelos.Caso_Penal;
/**
 *
 * @author Gabox
 */
public class DCasoPenal {
    private ArrayList<Caso_Penal> listaCasoPenal = new ArrayList();
    private final Conexion conexion = new Conexion();
    private Connection conn ;
    private PreparedStatement mostrarCasoPenal;
    private PreparedStatement insertarCasoPenal;
    private PreparedStatement modificarCasoPenal;
    private PreparedStatement eliminarCasoPenal;
    
    public DCasoPenal() {
        try {
            conn = conexion.obtenerConexion();
            mostrarCasoPenal = conn.prepareStatement("Select * from Caso_Penal");
            insertarCasoPenal = conn.prepareStatement("Insert Into Caso_Penal(tipoCaso,"
                    + " descripcion, estadoCaso, fecha, honorarios, nombramientoDefensor) Values(?, ?, ?, ?, ?, ?)");
            modificarCasoPenal = conn.prepareStatement("Update Caso_Penal set tipoCaso = ?,"
                    + " descripcion = ?, estadoCaso = ?, fecha = ?, honorarios = ?, nombramientoDefensor = ? where CasoP_id = ?");
            eliminarCasoPenal = conn.prepareStatement("Delete From CasoPenal where CasoP_id = ?");
            listaCasoPenal = new ArrayList<>();
            
            listaCasoPenal = listarRegistro();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            System.exit(1);
        }
    }
    
    private ArrayList<Caso_Penal> listarRegistro() {
        ArrayList<Caso_Penal> result = null;
        ResultSet rs = null;
        try{
            rs = mostrarCasoPenal.executeQuery();
            result = new ArrayList<>();
            while(rs.next()){
                result.add(new Caso_Penal(
                        rs.getInt("CasoP_id"),
                        rs.getString("descripcion"),
                        rs.getString("fecha"),
                        rs.getString("tipoCaso"),
                        rs.getString("estadoCaso"),
                        rs.getFloat("honorarios"),
                        rs.getString("nombramientoDefensor"),
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
    
    public int agregarCasoPenal(String descripcionProblema, String fecha, String estadoCaso, float honorarios, String nombramientoDefensor){
        int b = 0;
        try{
            listaCasoPenal.add(new Caso_Penal(0,
                    descripcionProblema,
                    fecha,
                    "Penal",
                    estadoCaso,
                    honorarios,
                    nombramientoDefensor,
                    4 // estado Nuevo registro sin guardar en la BD 
            ));
            b = 1;
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return b;
    }
    
    public int editarCasoPenal(int id_caso, String descripcionProblema, String fecha, String estadoCaso, float honorarios, String nombramientoDefensor){
        try{
            Caso_Penal Caso_Penal  = new Caso_Penal(
                    id_caso,
                    descripcionProblema,
                    fecha,
                    "Penal",
                    estadoCaso,
                    honorarios,
                    nombramientoDefensor,
                    2 // estado Modificado en la lista, no guardado en la BD
            );
            for(Caso_Penal a: listaCasoPenal){
               if(a.getId_caso()== Caso_Penal.getId_caso()){
                   a.setDescripcionProblema(Caso_Penal.getDescripcionProblema());
                   a.setFecha(Caso_Penal.getFecha());
                   a.setTipoCaso(Caso_Penal.getTipoCaso());
                   a.setHonorarios(Caso_Penal.getHonorarios());
                   a.setNombramientoDefensor(Caso_Penal.getNombramientoDefensor());
                   if(a.getEstadoBD()!=0) a.setEstadoBD(Caso_Penal.getEstadoBD()); 
                   return 1;
               } 
            }
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return 0;
    }
    
    public int eliminarCasoCivil(int id_caso){
        try{
            for(Caso_Penal a : listaCasoPenal){
                if(a.getId_caso() == id_caso){
                    a.setEstadoBD(3); //estado Eliminado en la lista,
                    //aun no eliminado en la base de datos.
                    return 1;
                }         
            }
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return 0;
    }
    
    public int agregarRegistroBD(Caso_Penal Caso_Penal) {
        int result = 0;
        try {
            insertarCasoPenal.setString(1, Caso_Penal.getTipoCaso());
            insertarCasoPenal.setString(2, Caso_Penal.getDescripcionProblema());
            insertarCasoPenal.setString(3, Caso_Penal.getEstadoCaso());
            insertarCasoPenal.setString(4, Caso_Penal.getFecha());
            insertarCasoPenal.setFloat(5, Caso_Penal.getHonorarios());
            insertarCasoPenal.setString(6, Caso_Penal.getNombramientoDefensor());
            result = insertarCasoPenal.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            conexion.close(conn);
        }
        return result;
    }
    
    public int modificarRegistroBD(Caso_Penal Caso_Penal) {
        int result = 0;
        try {
            modificarCasoPenal.setString(1, Caso_Penal.getTipoCaso());
            modificarCasoPenal.setString(2, Caso_Penal.getDescripcionProblema());
            modificarCasoPenal.setString(3, Caso_Penal.getEstadoCaso());
            modificarCasoPenal.setString(4, Caso_Penal.getFecha());
            modificarCasoPenal.setFloat(5, Caso_Penal.getHonorarios());
            modificarCasoPenal.setString(6, Caso_Penal.getNombramientoDefensor());
            result = modificarCasoPenal.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            conexion.close(conn);
        }
        return result;
    }
    
    public int eliminarRegistroBD(Caso_Penal Caso_Penal) {
        int result = 0;
        try {
            eliminarCasoPenal.setInt(1, Caso_Penal.getId_caso());

            result = eliminarCasoPenal.executeUpdate();
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
        for (Caso_Penal Caso_Penal : listaCasoPenal) { 
            switch (Caso_Penal.getEstadoBD()) {
                case 1:
                    //Si es original no hace nada
                    break;
                case 2:
                    //Estado modificado
                    if (this.modificarRegistroBD(Caso_Penal) != 0) {
                        modificados++;
                    } else {
                        errorModificados++;
                        msnError += "\n - Error al modificar el caso: " + Caso_Penal.getTipoCaso() 
                                + " " + Caso_Penal.getDescripcionProblema();
                    }
                    break;
                case 3:
                    //Estado eliminado
                    if (this.eliminarRegistroBD(Caso_Penal) != 0) {
                        eliminados++;
                    } else {
                        errorEliminados++;
                        msnError += "\n - Error al eliminar el caso: " + Caso_Penal.getTipoCaso() 
                                + " " + Caso_Penal.getDescripcionProblema();
                    }
                    break;
                case 4:
                    //Estado nuevo registro
                    if (this.agregarRegistroBD(Caso_Penal) != 0) {
                        nuevos++;
                    } else {
                        errorNuevos++;
                        msnError += "\n -Error al agregar nuevo caso: "  
                                + Caso_Penal.getTipoCaso() + " " + Caso_Penal.getDescripcionProblema();
                    }
                    break;
                default:
                    msnError+="\n Revise el caso: "+ Caso_Penal.getTipoCaso()
                            + " " + Caso_Penal.getDescripcionProblema();
                    break;
            }
        }
        msn = "Registros guardados: " + nuevos + "\nRegistros editados: "+ modificados+
                "\nRegistros eliminados: " + eliminados;
        
        if (!msnError.equals("Errores en: ")){
            msn+="\n"+ msnError;
        }        
        listaCasoPenal = this.listarRegistro();
        return msn;
    }
    
    public ArrayList<Caso_Penal> getLista() {
        return listaCasoPenal;
    }
}
