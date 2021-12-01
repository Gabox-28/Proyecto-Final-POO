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
            mostrarCasoPenal = conn.prepareStatement("Select * from caso");
            insertarCasoPenal = conn.prepareStatement("Insert Into caso(id_caso,"
                    + " description, fecha_caso, id_cliente, honorarios, poder_general_judicial, monto_a_litigar, porcentaje_ganancia, nombramiento_defensor) Values(?, ?, ?, ?, ?, ?, ?, ?, ?)");
            modificarCasoPenal = conn.prepareStatement("Update caso set description = ?,"
                    + " fecha_caso = ?, id_cliente  = ?, honorarios = ?, poder_general_judicial = ?, monto_a_litigar = ?, porcentaje_ganancia = ?, nombramiento_defensor = ? where id_caso = ?");
            eliminarCasoPenal = conn.prepareStatement("Delete From caso where id_caso = ?");
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
                        rs.getInt("id_caso"),
                        rs.getString("descripcionProblema"),
                        rs.getInt("fecha"),
                        rs.getString("tipoCaso"),
                        rs.getInt("estadoCaso"),
                        rs.getFloat("honorarios"),
                        rs.getBoolean("nombremientoDefensor"),
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
    
    public int agregarCasoPenal(int id_caso, String descripcionProblema, int fecha, String tipoCaso, int estadoCaso, float honorarios, boolean nombramientoDefensor, int estadoBD){
        int b = 0;
        try{
            listaCasoPenal.add(new Caso_Penal(0,
                    descripcionProblema,
                    fecha,
                    "Civil",
                    1,
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
    
    public int editarCasoPenal(int id_caso, String descripcionProblema, int fecha, String tipoCaso, int estadoCaso, float honorarios, boolean nombramientoDefensor, int estadoBD){
        try{
            Caso_Penal Caso_Penal  = new Caso_Penal(
                    id_caso,
                    descripcionProblema,
                    fecha,
                    "Civil",
                    1,
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
                   a.setNombramientoDefensor(Caso_Penal.isNombramientoDefensor());
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
            insertarCasoPenal.setString(1, Caso_Penal.getDescripcionProblema());
            insertarCasoPenal.setInt(2, Caso_Penal.getFecha());
            insertarCasoPenal.setString(3, Caso_Penal.getTipoCaso());
            insertarCasoPenal.setInt(4, Caso_Penal.getEstadoCaso());
            insertarCasoPenal.setFloat(5, Caso_Penal.getHonorarios());
            insertarCasoPenal.setBoolean(6, Caso_Penal.isNombramientoDefensor());
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
            modificarCasoPenal.setString(1, Caso_Penal.getDescripcionProblema());
            modificarCasoPenal.setInt(2, Caso_Penal.getFecha());
            modificarCasoPenal.setString(3, Caso_Penal.getTipoCaso());
            modificarCasoPenal.setInt(4, Caso_Penal.getEstadoCaso());
            modificarCasoPenal.setFloat(5, Caso_Penal.getHonorarios());
            modificarCasoPenal.setBoolean(6, Caso_Penal.isNombramientoDefensor());
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
