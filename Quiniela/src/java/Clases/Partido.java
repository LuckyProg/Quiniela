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
import java.util.HashSet;
import java.util.Set;
import java.util.Vector;

/**
 *
 * @author Fernando
 */
public class Partido {
    
    private int id_partido;
    private String visitante;
    private String local;
    private int id_visitante;
    private int id_local;
    private int semana;
    private String fecha;
    private int mayoreal;
    private int menoreal;
    private int ganadoreal;
    private boolean pr_mar;
    
    
    
    public Partido(){
    }
    
    
    
    public Partido registrarPartido(int semana,  int local, int visitante, String f, boolean pr_mar){
        Partido par = null;
        Connection cn=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        
        
        String fecha = f.substring(0,10)+"-"+f.substring(11,16);
     
        
        try{
            
            par= new Partido();
            cn = Conexion.getConexion();
            String sql = "insert into partido (semana,local, visitante, fecha, pr_mar) values (?,?,?,?,?)";
            ps = cn.prepareStatement(sql); 
            ps.setInt(1, semana);
            ps.setInt(2, local);
            ps.setInt(3, visitante);
            ps.setString(4, fecha);
            ps.setBoolean(5, pr_mar);
            ps.executeUpdate();
       

        }catch(Exception e){
            e.printStackTrace();
            par = null;
        }finally{
            try{
                //rs.close();
                ps.close();
            }catch(SQLException ex){
                par = null;
                ex.printStackTrace();
            }
        }
        
        
        
    return par;
    
    }
    
    public Partido borrarPartido(int id){
        
        Partido par = null;
        Connection cn=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        
        try{
            
            par= new Partido();
            cn = Conexion.getConexion();
            String sql = "DELETE FROM  partido WHERE id_partido=?";
            ps = cn.prepareStatement(sql);
            ps.setInt(1, id);
            
             
            ps.executeUpdate();

        }catch(Exception e){
            e.printStackTrace();
            par = null;
        }finally{
            try{
                //rs.close();
                ps.close();
            }catch(SQLException ex){
                par = null;
                ex.printStackTrace();
            }
        }
    
        return par;
        
    }
    
    public Partido editarPartido(int semana, int local, int visitante, String fecha, int id_partido, boolean pr_mar){
        
        Partido par = null;
        Connection cn=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        
        try{
            
            par= new Partido();
            cn = Conexion.getConexion();
            String sql = "update partido set semana=?, local=?, visitante=?, fecha=?, pr_mar=? where id_partido=?;";
            ps = cn.prepareStatement(sql);
            ps.setInt(1, semana);
            ps.setInt(2, local);
            ps.setInt(3, visitante);
            ps.setString(4, fecha);
            ps.setBoolean(5, pr_mar);
            ps.setInt(6, id_partido);
             
            ps.executeUpdate();

        }catch(Exception e){
            e.printStackTrace();
            par = null;
        }finally{
            try{
                //rs.close();
                ps.close();
            }catch(SQLException ex){
                par = null;
                ex.printStackTrace();
            }
        }
        
        return par;
    
    }
    
    public Partido subirResultado(int semana, int local, int visitante, String fecha, String mayoreal, String menoreal, String ganadoreal, int id_partido){
        
        Partido par = null;
        Connection cn=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        
        try{
            
            par= new Partido();
            cn = Conexion.getConexion();
            String sql = "update partido set semana=?, local=?, visitante=?, fecha=?, mayoreal=?, menoreal=?, ganadoreal=? where id_partido=?;";
            ps = cn.prepareStatement(sql);
            ps.setInt(1, semana);
            ps.setInt(2, local);
            ps.setInt(3, visitante);
            ps.setString(4, fecha);
            ps.setInt(5, Integer.parseInt(mayoreal));
            ps.setInt(6, Integer.parseInt(menoreal));
            ps.setInt(7, Integer.parseInt(ganadoreal));
            ps.setInt(8, id_partido);
             
            ps.executeUpdate();

        }catch(Exception e){
            e.printStackTrace();
            par = null;
        }finally{
            try{
                //rs.close();
                ps.close();
            }catch(SQLException ex){
                par = null;
                ex.printStackTrace();
            }
        }
        
        return par;
    
    }

    public Vector<Partido> mostrarPartidos(String sem){
        
        Vector<Partido> partidos=new Vector<Partido>();
        
        Connection cn=null;
        PreparedStatement pr=null;
        ResultSet rs=null;
        
        
        try{
            cn=Conexion.getConexion();
            
            String sql="select p.id_partido, p.semana, p.mayoreal, p.menoreal, p.ganadoreal, e1.nombre_equipo as local,p.local as id_local, e2.nombre_equipo as visitante,"
                    + " p.visitante as id_visitante, p.fecha, p.pr_mar from partido as p, equipo as e1, equipo as e2 where p.local=e1.id_equipo and p.visitante=e2.id_equipo and semana = "+sem;
            
            
            pr=cn.prepareStatement(sql);
            rs=pr.executeQuery();
            while(rs.next()){
                Partido par=new Partido();
                par.setId_partido(rs.getInt("id_partido"));
                par.setSemana(rs.getInt("semana"));
                par.setMayoreal(rs.getInt("mayoreal"));
                par.setMenoreal(rs.getInt("menoreal"));
                par.setGanadoreal(rs.getInt("ganadoreal"));
                par.setLocal(rs.getString("local"));
                par.setId_local(rs.getInt("id_local"));
                par.setVisitante(rs.getString("visitante"));
                par.setId_visitante(rs.getInt("id_visitante"));
                String c = rs.getString("fecha").substring(0,10)+"T"+rs.getString("fecha").substring(11,16);
                par.setFecha(c);
               par.setPr_mar(rs.getBoolean("pr_mar"));
                
                
                /*
                par.setVisitante(rs.getString("visitante.nombre_visitante"));
                par.setLocal(rs.getString("local.nombre_local"));
                par.setSemana(rs.getInt("partido.semana"));
                par.setFecha(rs.getString("partido.fecha"));
                par.setHora(rs.getString("partido.hora"));
                par.setResultado(rs.getString("partido.resultado"));
                par.setId_partido(rs.getInt("partido.id_partido"));
                */
                
                
                
                partidos.add(par);
            }
        }catch(SQLException ex){
            ex.printStackTrace();
            partidos=null;
        }finally{
            try{
                rs.close();
                pr.close();
                cn.close();
            }catch(SQLException ex){
                ex.printStackTrace();
            }
        }
        
        
        return partidos;
    }
    
    public void resetearPartido(){
        
    }

    public int getId_partido() {
        return id_partido;
    }

    public void setId_partido(int id_partido) {
        this.id_partido = id_partido;
    }

    public String getVisitante() {
        return visitante;
    }

    public void setVisitante(String visitante) {
        this.visitante = visitante;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public int getId_visitante() {
        return id_visitante;
    }

    public void setId_visitante(int id_visitante) {
        this.id_visitante = id_visitante;
    }

    public int getId_local() {
        return id_local;
    }

    public void setId_local(int id_local) {
        this.id_local = id_local;
    }

    public int getSemana() {
        return semana;
    }

    public void setSemana(int semana) {
        this.semana = semana;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getMayoreal() {
        return mayoreal;
    }

    public void setMayoreal(int mayoreal) {
        this.mayoreal = mayoreal;
    }

    public int getMenoreal() {
        return menoreal;
    }

    public void setMenoreal(int menoreal) {
        this.menoreal = menoreal;
    }

    public int getGanadoreal() {
        return ganadoreal;
    }

    public void setGanadoreal(int ganadoreal) {
        this.ganadoreal = ganadoreal;
    }

    public boolean isPr_mar() {
        return pr_mar;
    }

    public void setPr_mar(boolean pr_mar) {
        this.pr_mar = pr_mar;
    }
    
}
