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
import modelos.Caso_Laboral;
/**
 *
 * @author Gabox
 */
public class DCasoLaboral {
    
    private ArrayList<Caso_Laboral> listaCasoLaboral = new ArrayList();
    private final Conexion conexion = new Conexion();
    private Connection conn ;
    private PreparedStatement mostrarCasoLaboral;
    private PreparedStatement insertarCasoLaboral;
    private PreparedStatement modificarCasoLaboral;
    private PreparedStatement eliminarCasoLaboral;
    
    public DCasoLaboral() {
        try {
            conn = conexion.obtenerConexion();
            mostrarCasoLaboral = conn.prepareStatement("Select * from caso");
            insertarCasoLaboral = conn.prepareStatement("Insert Into caso(id_caso,"
                    + " description, fecha_caso, id_cliente, honorarios, poder_general_judicial, monto_a_litigar, porcentaje_ganancia, nombramiento_defensor) Values(?, ?, ?, ?, ?, ?, ?, ?, ?)");
            modificarCasoLaboral = conn.prepareStatement("Update caso set description = ?,"
                    + " fecha_caso = ?, id_cliente  = ?, honorarios = ?, poder_general_judicial = ?, monto_a_litigar = ?, porcentaje_ganancia = ?, nombramiento_defensor = ? where id_caso = ?");
            eliminarCasoLaboral = conn.prepareStatement("Delete From caso where id_caso = ?");
            listaCasoLaboral = new ArrayList<>();
            
            listaCasoLaboral = listarRegistro();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            System.exit(1);
        }
    }
    
    private ArrayList<Caso_Laboral> listarRegistro() {
        ArrayList<Caso_Laboral> result = null;
        ResultSet rs = null;
        try{
            rs = mostrarCasoLaboral.executeQuery();
            result = new ArrayList<>();
            while(rs.next()){
                result.add(new Caso_Laboral(
                        rs.getInt("id_caso"),
                        rs.getString("descripcionProblema"),
                        rs.getInt("fecha"),
                        rs.getString("tipoCaso"),
                        rs.getInt("estadoCaso"),
                        rs.getFloat("montoALitigar"),
                        rs.getBoolean("poderGeneralJudicial"),
                        rs.getInt("porcentajeGanancia"),
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
    
     public int agregarCasoLaboral(int id_caso, String descripcionProblema, int fecha, String tipoCaso, int estadoCaso, float montoALitigar, boolean poderGeneralJudicial, int porcentajeGanancia, int estadoBD){
        int b = 0;
        try{
            listaCasoLaboral.add(new Caso_Laboral(0,
                    descripcionProblema,
                    fecha,
                    "Laboral",
                    1,
                    montoALitigar,
                    poderGeneralJudicial,
                    porcentajeGanancia,
                    4 // estado Nuevo registro sin guardar en la BD 
            ));
            b = 1;
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return b;
    }
     
     public int editarCasoLaboral(int id_caso, String descripcionProblema, int fecha, String tipoCaso, int estadoCaso, float montoALitigar, boolean poderGeneralJudicial, int porcentajeGanancia, int estadoBD){
        try{
            Caso_Laboral Caso_Laboral  = new Caso_Laboral(
                    id_caso,
                    descripcionProblema,
                    fecha,
                    "Laboral",
                    1,
                    montoALitigar,
                    poderGeneralJudicial,
                    porcentajeGanancia,
                    2 // estado Modificado en la lista, no guardado en la BD
            );
            for(Caso_Laboral a: listaCasoLaboral){
               if(a.getId_caso()== Caso_Laboral.getId_caso()){
                   a.setDescripcionProblema(Caso_Laboral.getDescripcionProblema());
                   a.setFecha(Caso_Laboral.getFecha());
                   a.setTipoCaso(Caso_Laboral.getTipoCaso());
                   a.setMontoALitigar(Caso_Laboral.getMontoALitigar());
                   a.setPoderGeneralJudicial(Caso_Laboral.isPoderGeneralJudicial());
                   a.setPorcentajeGanancia(Caso_Laboral.getPorcentajeGanancia());
                   if(a.getEstadoBD()!=0) a.setEstadoBD(Caso_Laboral.getEstadoBD()); 
                   return 1;
               } 
            }
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return 0;
    }
     
     public int eliminarCasoLaboral(int id_caso){
        try{
            for(Caso_Laboral a : listaCasoLaboral){
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
     
     public int agregarRegistroBD(Caso_Laboral Caso_Laboral) {
        int result = 0;
        try {
            insertarCasoLaboral.setString(1, Caso_Laboral.getDescripcionProblema());
            insertarCasoLaboral.setInt(2, Caso_Laboral.getFecha());
            insertarCasoLaboral.setString(3, Caso_Laboral.getTipoCaso());
            insertarCasoLaboral.setInt(4, Caso_Laboral.getEstadoCaso());
            insertarCasoLaboral.setFloat(5, Caso_Laboral.getMontoALitigar());
            insertarCasoLaboral.setBoolean(6, Caso_Laboral.isPoderGeneralJudicial());
            insertarCasoLaboral.setInt(5, Caso_Laboral.getPorcentajeGanancia());
            result = insertarCasoLaboral.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            conexion.close(conn);
        }
        return result;
    }
     
     public int modificarRegistroBD(Caso_Laboral Caso_Laboral) {
        int result = 0;
        try {
            modificarCasoLaboral.setString(1, Caso_Laboral.getDescripcionProblema());
            modificarCasoLaboral.setInt(2, Caso_Laboral.getFecha());
            modificarCasoLaboral.setString(3, Caso_Laboral.getTipoCaso());
            modificarCasoLaboral.setInt(4, Caso_Laboral.getEstadoCaso());
            modificarCasoLaboral.setFloat(5, Caso_Laboral.getMontoALitigar());
            modificarCasoLaboral.setBoolean(6, Caso_Laboral.isPoderGeneralJudicial());
            modificarCasoLaboral.setFloat(5, Caso_Laboral.getPorcentajeGanancia());
            result = modificarCasoLaboral.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            conexion.close(conn);
        }
        return result;
    }
     
     public int eliminarRegistroBD(Caso_Laboral Caso_Laboral) {
        int result = 0;
        try {
            eliminarCasoLaboral.setInt(1, Caso_Laboral.getId_caso());

            result = eliminarCasoLaboral.executeUpdate();
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
        for (Caso_Laboral Caso_Laboral : listaCasoLaboral) { 
            switch (Caso_Laboral.getEstadoBD()) {
                case 1:
                    //Si es original no hace nada
                    break;
                case 2:
                    //Estado modificado
                    if (this.modificarRegistroBD(Caso_Laboral) != 0) {
                        modificados++;
                    } else {
                        errorModificados++;
                        msnError += "\n - Error al modificar el caso: " + Caso_Laboral.getTipoCaso() 
                                + " " + Caso_Laboral.getDescripcionProblema();
                    }
                    break;
                case 3:
                    //Estado eliminado
                    if (this.eliminarRegistroBD(Caso_Laboral) != 0) {
                        eliminados++;
                    } else {
                        errorEliminados++;
                        msnError += "\n - Error al eliminar el caso: " + Caso_Laboral.getTipoCaso() 
                                + " " + Caso_Laboral.getDescripcionProblema();
                    }
                    break;
                case 4:
                    //Estado nuevo registro
                    if (this.agregarRegistroBD(Caso_Laboral) != 0) {
                        nuevos++;
                    } else {
                        errorNuevos++;
                        msnError += "\n -Error al agregar nuevo caso: "  
                                + Caso_Laboral.getTipoCaso() + " " + Caso_Laboral.getDescripcionProblema();
                    }
                    break;
                default:
                    msnError+="\n Revise el caso: "+ Caso_Laboral.getTipoCaso()
                            + " " + Caso_Laboral.getDescripcionProblema();
                    break;
            }
        }
        msn = "Registros guardados: " + nuevos + "\nRegistros editados: "+ modificados+
                "\nRegistros eliminados: " + eliminados;
        
        if (!msnError.equals("Errores en: ")){
            msn+="\n"+ msnError;
        }        
        listaCasoLaboral = this.listarRegistro();
        return msn;
    }
    
    public ArrayList<Caso_Laboral> getLista() {
        return listaCasoLaboral;
    }
}
