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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;

/**
 *
 * @author derda
 */
public class Survival {
    
    private int semana;
    private int id_usuario;
    private String nombreU;
    private int id_equipo;
    private String nombreE;
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
    
    public Vector<Survival> cosadelacosax2(int semana){
        
        Vector<Survival> selected=new Vector<Survival>();
        
        Connection cn=null;
        PreparedStatement pr=null;
        ResultSet rs=null;
        
        
        try{
            cn=Conexion.getConexion();
            
            String sql="SELECT u.nombre as man, e.nombre_equipo as eq,s.gana,  s.semana  as semana FROM survival as s natural join equipo as e natural join usuario as u where semana=?";
            pr=cn.prepareStatement(sql);
            pr.setInt(1, semana);
            rs=pr.executeQuery();
            
            while(rs.next()){
                Survival sur=new Survival();
                sur.setNombreU(rs.getString("man"));
                sur.setNombreE(rs.getString("eq"));
                sur.setGana(rs.getBoolean("gana"));
                sur.setSemana(rs.getInt("semana"));
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
    
    public Survival actualizar(int semana, int ganadoreal, int local, int visitante){
        
        Survival sur = null;
        Connection cn=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        int perdedoreal = 100;
        
        try{
            cn = Conexion.getConexion();
            String sql = "UPDATE survival SET gana=? WHERE semana=? and id_equipo = ?";
            ps = cn.prepareStatement(sql);
            ps.setBoolean(1, true);
            ps.setInt(2, semana);
            ps.setInt(3, ganadoreal);   
             
            ps.executeUpdate();
            
            perdedoreal = ganadoreal == local ? visitante : local;
            
            sql = "UPDATE survival SET gana=? WHERE semana=? and id_equipo = ?";
            ps = cn.prepareStatement(sql);
            ps.setBoolean(1, false);
            ps.setInt(2, semana);
            ps.setInt(3, perdedoreal);   
             
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
    
    public Survival matar(int semana, int ganadoreal){
        
        Survival sur = null;
        Connection cn=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        
        
        try{
            cn = Conexion.getConexion();
            
            String sql = "UPDATE survival SET gana=? WHERE gana!=? and semana=?";
            ps = cn.prepareStatement(sql);
            ps.setBoolean(1, false);
            ps.setBoolean(2, true);
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
    
    public boolean bloqueo (int semana){
        
        Connection cn=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        boolean bloqueo = true;
        
        try{
            cn = Conexion.getConexion();
            
            String sql = "select fecha from partido where semana = ? order by fecha limit 1";
            ps = cn.prepareStatement(sql);
            ps.setInt(1, semana); 

            rs = ps.executeQuery();
            while(rs.next()){
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd' 'HH:mm:ss");
                Quinela wuera = new Quinela();
                Date servidor = sdf.parse(wuera.obtenerFecha());
                Date partido = sdf.parse(rs.getString("fecha") + ":00.0");
                if(servidor.before(partido)){ 
                    bloqueo = false;
                }
            }

        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try{
                //rs.close();
                ps.close();
            }catch(SQLException ex){
                ex.printStackTrace();
            }
        }
        
        return bloqueo;
        
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

    public String getNombreU() {
        return nombreU;
    }

    public void setNombreU(String nombreU) {
        this.nombreU = nombreU;
    }

    public String getNombreE() {
        return nombreE;
    }

    public void setNombreE(String nombreE) {
        this.nombreE = nombreE;
    }
    
    
}
