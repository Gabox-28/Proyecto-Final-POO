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
import modelos.Caso_Civil;

/**
 *
 * @author Gabox
 */
public class DCasoCivil {
    
    private ArrayList<Caso_Civil> listaCasoCivil = new ArrayList();
    private final Conexion conexion = new Conexion();
    private Connection conn ;
    private PreparedStatement mostrarCasoCivil;
    private PreparedStatement insertarCasoCivil;
    private PreparedStatement modificarCasoCivil;
    private PreparedStatement eliminarCasoCivil;    
    
    public DCasoCivil() {
        try {
            conn = conexion.obtenerConexion();
            mostrarCasoCivil = conn.prepareStatement("Select * from caso");
            insertarCasoCivil = conn.prepareStatement("Insert Into caso(id_caso,"
                    + " description, fecha_caso, id_cliente, honorarios, poder_general_judicial, monto_a_litigar, porcentaje_ganancia, nombramiento_defensor) Values(?, ?, ?, ?, ?, ?, ?, ?, ?)");
            modificarCasoCivil = conn.prepareStatement("Update caso set description = ?,"
                    + " fecha_caso = ?, id_cliente  = ?, honorarios = ?, poder_general_judicial = ?, monto_a_litigar = ?, porcentaje_ganancia = ?, nombramiento_defensor = ? where id_caso = ?");
            eliminarCasoCivil = conn.prepareStatement("Delete From caso where id_caso = ?");
            listaCasoCivil = new ArrayList<>();
            
            listaCasoCivil = listarRegistro();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            System.exit(1);
        }
    }
    
    private ArrayList<Caso_Civil> listarRegistro() {
        ArrayList<Caso_Civil> result = null;
        ResultSet rs = null;
        try{
            rs = mostrarCasoCivil.executeQuery();
            result = new ArrayList<>();
            while(rs.next()){
                result.add(new Caso_Civil(
                        rs.getInt("id_caso"),
                        rs.getString("descripcionProblema"),
                        rs.getInt("fecha"),
                        rs.getString("tipoCaso"),
                        rs.getInt("estadoCaso"),
                        rs.getFloat("honorarios"),
                        rs.getBoolean("poderGeneralJudicial"),
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
    
    public int agregarCasoCivil(int id_caso, String descripcionProblema, int fecha, String tipoCaso, int estadoCaso, float honorarios, boolean poderGeneralJudicial, int estadoBD){
        int b = 0;
        try{
            listaCasoCivil.add(new Caso_Civil(0,
                    descripcionProblema,
                    fecha,
                    "Civil",
                    1,
                    honorarios,
                    poderGeneralJudicial,
                    4 // estado Nuevo registro sin guardar en la BD 
            ));
            b = 1;
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return b;
    }
    
    public int editarCasoCivil(int id_caso, String descripcionProblema, int fecha, String tipoCaso, int estadoCaso, float honorarios, boolean poderGeneralJudicial, int estadoBD){
        try{
            Caso_Civil Caso_Civil  = new Caso_Civil(
                    id_caso,
                    descripcionProblema,
                    fecha,
                    "Civil",
                    1,
                    honorarios,
                    poderGeneralJudicial,
                    2 // estado Modificado en la lista, no guardado en la BD
            );
            for(Caso_Civil a: listaCasoCivil){
               if(a.getId_caso()== Caso_Civil.getId_caso()){
                   a.setDescripcionProblema(Caso_Civil.getDescripcionProblema());
                   a.setFecha(Caso_Civil.getFecha());
                   a.setTipoCaso(Caso_Civil.getTipoCaso());
                   a.setHonorarios(Caso_Civil.getHonorarios());
                   a.setPoderGeneralJudicial(Caso_Civil.isPoderGeneralJudicial());
                   if(a.getEstadoBD()!=0) a.setEstadoBD(Caso_Civil.getEstadoBD()); 
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
            for(Caso_Civil a : listaCasoCivil){
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
    
    public int agregarRegistroBD(Caso_Civil Caso_Civil) {
        int result = 0;
        try {
            insertarCasoCivil.setString(1, Caso_Civil.getDescripcionProblema());
            insertarCasoCivil.setInt(2, Caso_Civil.getFecha());
            insertarCasoCivil.setString(3, Caso_Civil.getTipoCaso());
            insertarCasoCivil.setInt(4, Caso_Civil.getEstadoCaso());
            insertarCasoCivil.setFloat(5, Caso_Civil.getHonorarios());
            insertarCasoCivil.setBoolean(6, Caso_Civil.isPoderGeneralJudicial());
            result = insertarCasoCivil.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            conexion.close(conn);
        }
        return result;
    }
    
    public int modificarRegistroBD(Caso_Civil Caso_Civil) {
        int result = 0;
        try {
            modificarCasoCivil.setString(1, Caso_Civil.getDescripcionProblema());
            modificarCasoCivil.setInt(2, Caso_Civil.getFecha());
            modificarCasoCivil.setString(3, Caso_Civil.getTipoCaso());
            modificarCasoCivil.setInt(4, Caso_Civil.getEstadoCaso());
            modificarCasoCivil.setFloat(5, Caso_Civil.getHonorarios());
            modificarCasoCivil.setBoolean(6, Caso_Civil.isPoderGeneralJudicial());
            result = modificarCasoCivil.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            conexion.close(conn);
        }
        return result;
    }
    
    public int eliminarRegistroBD(Caso_Civil Caso_Civil) {
        int result = 0;
        try {
            eliminarCasoCivil.setInt(1, Caso_Civil.getId_caso());

            result = eliminarCasoCivil.executeUpdate();
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
        for (Caso_Civil Caso_Civil : listaCasoCivil) { 
            switch (Caso_Civil.getEstadoBD()) {
                case 1:
                    //Si es original no hace nada
                    break;
                case 2:
                    //Estado modificado
                    if (this.modificarRegistroBD(Caso_Civil) != 0) {
                        modificados++;
                    } else {
                        errorModificados++;
                        msnError += "\n - Error al modificar el caso: " + Caso_Civil.getTipoCaso() 
                                + " " + Caso_Civil.getDescripcionProblema();
                    }
                    break;
                case 3:
                    //Estado eliminado
                    if (this.eliminarRegistroBD(Caso_Civil) != 0) {
                        eliminados++;
                    } else {
                        errorEliminados++;
                        msnError += "\n - Error al eliminar el caso: " + Caso_Civil.getTipoCaso() 
                                + " " + Caso_Civil.getDescripcionProblema();
                    }
                    break;
                case 4:
                    //Estado nuevo registro
                    if (this.agregarRegistroBD(Caso_Civil) != 0) {
                        nuevos++;
                    } else {
                        errorNuevos++;
                        msnError += "\n -Error al agregar nuevo caso: "  
                                + Caso_Civil.getTipoCaso() + " " + Caso_Civil.getDescripcionProblema();
                    }
                    break;
                default:
                    msnError+="\n Revise el caso: "+ Caso_Civil.getTipoCaso()
                            + " " + Caso_Civil.getDescripcionProblema();
                    break;
            }
        }
        msn = "Registros guardados: " + nuevos + "\nRegistros editados: "+ modificados+
                "\nRegistros eliminados: " + eliminados;
        
        if (!msnError.equals("Errores en: ")){
            msn+="\n"+ msnError;
        }        
        listaCasoCivil = this.listarRegistro();
        return msn;
    }
    
    public ArrayList<Caso_Civil> getLista() {
        return listaCasoCivil;
    }
}
