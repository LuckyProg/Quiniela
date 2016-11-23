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
 * @author derda
 */
public class Survival {
    
    private int semana;
    private int id_usuario;
    private int id_equipo;
    private boolean gana;
    private String nombre_equipo;

    public Survival() {
    }
    
    public Vector<Survival> verSurvival(String apodo){
        
        Vector<Survival> selected=new Vector<Survival>();
        
        Connection cn=null;
        PreparedStatement pr=null;
        ResultSet rs=null;
        
        
        try{
            cn=Conexion.getConexion();
            
            String sql="SELECT * FROM survival natural join equipo natural join usuario where apodo=?";
            pr=cn.prepareStatement(sql);
            pr.setString(1, apodo);
            rs=pr.executeQuery();
            
            while(rs.next()){
                Survival sur=new Survival();
                sur.setId_equipo(rs.getInt("id_equipo"));
                sur.setSemana(rs.getInt("semana"));
                sur.setGana(rs.getBoolean("gana"));
                sur.setId_usuario(rs.getInt("id_usuario"));
                sur.setNombre_equipo(rs.getString("nombre_equipo"));
                selected.add(sur);
            }
        }catch(SQLException ex){
            ex.printStackTrace();
            selected=null;
        }finally{
            try{
                rs.close();
                pr.close();
                cn.close();
            }catch(SQLException ex){
                ex.printStackTrace();
            }
        }
        
        
        return selected;
    }
    
    public Survival editarSurvival(int id_equipo, int id_usuario, int semana){
        
        Survival sur = null;
        Connection cn=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        
        try{
            
            sur= new Survival();
            cn = Conexion.getConexion();
            String sql = "UPDATE survival SET id_equipo=? WHERE id_usuario=? and semana=?";
            ps = cn.prepareStatement(sql);
            ps.setInt(1, id_equipo);
            ps.setInt(2, id_usuario);
            ps.setInt(3, semana);
             
            ps.executeUpdate();

        }catch(Exception e){
            e.printStackTrace();
            sur = null;
        }finally{
            try{
                //rs.close();
                ps.close();
            }catch(SQLException ex){
                sur = null;
                ex.printStackTrace();
            }
        }
        
        return sur;
    
    }
    
    public boolean participa(String apodo){
        
        Connection cn=null;
        PreparedStatement pr=null;
        ResultSet rs=null;
        boolean part = true;
        
        try{
            cn=Conexion.getConexion();
            
            String sql="SELECT * FROM survival natural join usuario where apodo=?";
            pr=cn.prepareStatement(sql);
            pr.setString(1, apodo);
            rs=pr.executeQuery();
            
            while(rs.next()){
                if(rs.getString("gana")!=null && rs.getBoolean("gana") == false){
                    part = false;
                }
            }
        }catch(SQLException ex){
            ex.printStackTrace();
        }finally{
            try{
                rs.close();
                pr.close();
                cn.close();
            }catch(SQLException ex){
                ex.printStackTrace();
            }
        }
        return part;
    }
    
    public boolean noseleccionado(String apodo, int semana){
        
        Connection cn=null;
        PreparedStatement pr=null;
        ResultSet rs=null;
        boolean nopart = true;
        
        try{
            cn=Conexion.getConexion();
            
            String sql="SELECT * FROM survival natural join usuario where apodo=? and semana =?";
            pr=cn.prepareStatement(sql);
            pr.setString(1, apodo);
            pr.setInt(2, semana);
            rs=pr.executeQuery();
            
            while(rs.next()){
                if(rs.getString("gana") != null){
                    nopart = false;
                    break;
                }
            }
        }catch(SQLException ex){
            ex.printStackTrace();
        }finally{
            try{
                rs.close();
                pr.close();
                cn.close();
            }catch(SQLException ex){
                ex.printStackTrace();
            }
        }
        return nopart;
        
    }
    
    public Survival actualizar(int id_usuario, int semana, boolean vida){
        
        Survival sur = null;
        Connection cn=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        
        
        try{
            
            sur= new Survival();
            cn = Conexion.getConexion();
            String sql = "UPDATE survival SET gana=? WHERE id_usuario=? and semana=?";
            ps = cn.prepareStatement(sql);
            ps.setBoolean(1, vida);
            ps.setInt(2, id_usuario);
            ps.setInt(3, semana);
             
            ps.executeUpdate();

        }catch(Exception e){
            e.printStackTrace();
            sur = null;
        }finally{
            try{
                //rs.close();
                ps.close();
            }catch(SQLException ex){
                sur = null;
                ex.printStackTrace();
            }
        }
        
        return sur;
        
    }
    
    public int getSemana() {
        return semana;
    }

    public void setSemana(int semana) {
        this.semana = semana;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public int getId_equipo() {
        return id_equipo;
    }

    public void setId_equipo(int id_equipo) {
        this.id_equipo = id_equipo;
    }

    public boolean isGana() {
        return gana;
    }

    public void setGana(boolean gana) {
        this.gana = gana;
    }

    public String getNombre_equipo() {
        return nombre_equipo;
    }

    public void setNombre_equipo(String nombre_equipo) {
        this.nombre_equipo = nombre_equipo;
    }
    
}
