/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

/**
 *
 * @author Fernando
 */
public class Equipo {
    
    private int id_equipo;
    private String nombre;
    
    public Equipo(){
    
    
    }
    
    public Vector<Equipo> mostrarEquipos(){
        
        Vector<Equipo> equipos=new Vector<Equipo>();
        
        Connection cn=null;
        PreparedStatement pr=null;
        ResultSet rs=null;
        
        
        try{
            cn=Conexion.getConexion();
           
            String sql="SELECT * FROM equipo";
            pr=cn.prepareStatement(sql);
            rs=pr.executeQuery();
            while(rs.next()){
                Equipo equi=new Equipo();
                equi.setId_equipo(rs.getInt("id_equipo"));
                equi.setNombre(rs.getString("nombre_equipo"));
                
                
                
                equipos.add(equi);
            }
        }catch(SQLException ex){
            ex.printStackTrace();
            equipos=null;
        }finally{
            try{
                rs.close();
                pr.close();
                cn.close();
            }catch(SQLException ex){
                ex.printStackTrace();
            }
        }
        
        
        return equipos;
    }

    public int getId_equipo() {
        return id_equipo;
    }

    public void setId_equipo(int id_equipo) {
        this.id_equipo = id_equipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    
    
}
