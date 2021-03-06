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
public class Puntaje {
    
    private int p;
    private int dl;
    private int ds;
    private String nombre;
    
    public Puntaje(){
        
    }
    
    public Vector<Puntaje> mostrarPuntajes(int semana){
        
        Vector<Puntaje> puntajes=new Vector<Puntaje>();
        
        Connection cn=null;
        PreparedStatement pr=null;
        ResultSet rs=null;
        
        
        try{
            cn=Conexion.getConexion();
            String sql="";
            
            sql="select u.nombre, p.p, p.dl, p.ds from usuario as u, puntaje as p where u.id_usuario = p.id_usuario and p.semana = ? order by p.p DESC, p.dl ASC, p.ds ASC";
            
            pr=cn.prepareStatement(sql);
            pr.setInt(1, semana);
            rs=pr.executeQuery();
            int dl, ds;
            while(rs.next()){
                Puntaje pun=new Puntaje();
                pun.setNombre(rs.getString("nombre"));
                pun.setP(rs.getInt("p"));
                dl = rs.getString("dl")!=null ? rs.getInt("dl") : new Puntaje().peordl(semana);
                pun.setDl(dl);
                ds = rs.getString("ds")!=null ? rs.getInt("ds") : new Puntaje().peords(semana);
                pun.setDs(ds);
                puntajes.add(pun);
            }
        }catch(SQLException ex){
            ex.printStackTrace();
            puntajes=null;
        }finally{
            try{
                rs.close();
                pr.close();
                cn.close();
            }catch(SQLException ex){
                ex.printStackTrace();
            }
        }
        
        
        return puntajes;
    } 
    
    public Vector<Puntaje> mostrarPuntajesG(){
        
        Vector<Puntaje> puntajes=new Vector<Puntaje>();
        
        Connection cn=null;
        PreparedStatement pr=null;
        ResultSet rs=null;
        
        
        try{
            cn=Conexion.getConexion();
            String sql="";
            
            Vector<Usuario> usuarios=new Usuario().mostrarJugadores();
            
            for(Usuario u:usuarios){
            
                sql="select u.nombre, sum(p.p) as p, sum(p.dl) as dl, sum(p.ds) as ds from usuario as u, puntaje as p where u.id_usuario = p.id_usuario and p.id_usuario = ?";

                pr=cn.prepareStatement(sql);
                pr.setInt(1, u.getId());
                rs=pr.executeQuery();

                while(rs.next()){
                    Puntaje pun=new Puntaje();
                    pun.setNombre(rs.getString("nombre"));
                    pun.setP(rs.getInt("p"));
                    pun.setDl(rs.getInt("dl"));
                    pun.setDs(rs.getInt("ds"));
                    puntajes.add(pun);
                }
                
            }
        }catch(SQLException ex){
            ex.printStackTrace();
            puntajes=null;
        }finally{
            try{
                rs.close();
                pr.close();
                cn.close();
            }catch(SQLException ex){
                ex.printStackTrace();
            }
        }
        
        
        return puntajes;
    } 
    
    public void actualizar_dl (int id_usuario, int dl, int semana){
        
        Connection cn=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        
        try{
            
            cn = Conexion.getConexion();
            String sql = "update puntaje set dl = ? where id_usuario= ? and semana = ? ;";
            ps = cn.prepareStatement(sql);
            ps.setInt(1, dl);
            ps.setInt(2, id_usuario);
            ps.setInt(3, semana);
             
            ps.executeUpdate();

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
        
    
    }
    
    public void actualizar_ds (int id_usuario, int ds, int semana){
        
        Connection cn=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        
        try{
            
            cn = Conexion.getConexion();
            String sql = "update puntaje set ds=? where id_usuario=? and semana = ?;";
            ps = cn.prepareStatement(sql);
            ps.setInt(1, ds);
            ps.setInt(2, id_usuario);
            ps.setInt(3, semana);
             
            ps.executeUpdate();

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
        
    
    }
    
    public int promedio(int semana){
        
        Connection cn=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        int p = 0;
        int c = -1;
        
        
        try{
            
            cn = Conexion.getConexion();
            String sql = "select p from puntaje where semana = ?";
            ps = cn.prepareStatement(sql); 
            ps.setInt(1, semana);
            rs = ps.executeQuery();
            while(rs.next()){
                p += rs.getInt("p");
                c++;
            }
            p = p/c;

        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try{
                ps.close();
            }catch(SQLException ex){
                ex.printStackTrace();
            }
        }
    
        return p;
        
    }
    
    public int peordl(int semana){
        
        Connection cn=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        int dl = 0;
        
        
        try{
            
            cn = Conexion.getConexion();
            String sql = "select dl from puntaje where semana = ?";
            ps = cn.prepareStatement(sql); 
            ps.setInt(1, semana);
            rs = ps.executeQuery();
            while(rs.next()){
                dl = rs.getInt("dl") > dl ? rs.getInt("dl"):dl;
            }
            dl = dl +10;

        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try{
                ps.close();
            }catch(SQLException ex){
                ex.printStackTrace();
            }
        }
    
        return dl;
        
    }
    
    public int peords(int semana){
        
        Connection cn=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        int ds = 0;
        
        
        try{
            
            cn = Conexion.getConexion();
            String sql = "select ds from puntaje where semana = ?";
            ps = cn.prepareStatement(sql); 
            ps.setInt(1, semana);
            rs = ps.executeQuery();
            while(rs.next()){
                ds = rs.getInt("ds") > ds ? rs.getInt("ds"):ds;
            }
            ds = ds +10;

        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try{
                ps.close();
            }catch(SQLException ex){
                ex.printStackTrace();
            }
        }
    
        return ds;
        
    }
    

    public int getP() {
        return p;
    }

    public void setP(int p) {
        this.p = p;
    }

    public int getDl() {
        return dl;
    }

    public void setDl(int dl) {
        this.dl = dl;
    }

    public int getDs() {
        return ds;
    }

    public void setDs(int ds) {
        this.ds = ds;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    
    
}
