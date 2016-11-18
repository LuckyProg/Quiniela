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
public class Quinela {
    
    private int id_pronostico;
    private int id_partido;
    private int id_usuario;
    private int ganador;
    private int mayor;
    private int menor;
    private int id_visitante;
    private int id_local;
    private String visitante;
    private String local;
    private String imagenvis;
    private String imagenloc;
    private String fecha;
    private String hora;
    private String acierto;
    private int no_marcador;

    public Quinela() {
    }
    
    public Vector<Quinela> mostrarPronosticos(int id_usuario, int semana){
        
        Vector<Quinela> quinita=new Vector<Quinela>();
        
        Connection cn=null;
        PreparedStatement pr=null;
        ResultSet rs=null;
        
        
        try{
            cn=Conexion.getConexion();
            int or = id_usuario;
            String sql="";
            
            sql="select  p.id_partido, p.fecha, p.pr_mar, v.id_equipo as id_visitante, v.nombre_equipo as visitante, v.imagen as imagenvis, l.id_equipo as id_local, l.nombre_equipo as local, l.imagen as imagenloc,"
                    + " pr.ganador, pr.mayor, pr.menor, pr.acierto, pr.no_marcador from partido as p, equipo as v, equipo as l, pronostico as pr  where p.local=l.id_equipo"
                    + " and p.visitante=v.id_equipo and pr.id_partido = p.id_partido and p.semana = ? and pr.id_usuario = ?";
            
            pr=cn.prepareStatement(sql);
            pr.setInt(1, semana);
            pr.setInt(2, id_usuario);
            rs=pr.executeQuery();
            
            while(rs.next()){
                Quinela qui=new Quinela();
                qui.setId_partido(rs.getInt("id_partido"));
                qui.setFecha((rs.getString("fecha")).substring(0,10));
                qui.setHora((rs.getString("fecha")).substring(11,16));
                qui.setId_visitante(rs.getInt("id_visitante"));
                qui.setVisitante(rs.getString("visitante"));
                qui.setId_local(rs.getInt("id_local"));
                qui.setLocal(rs.getString("local"));
                qui.setGanador(rs.getInt("ganador"));
                qui.setMayor(rs.getInt("mayor"));
                qui.setMenor(rs.getInt("menor"));
                qui.setAcierto(rs.getString("acierto"));
                qui.setNo_marcador(rs.getInt("no_marcador"));
                if(rs.getBoolean("pr_mar")){
                    qui.setNo_marcador(1);
                }
                qui.setImagenvis(rs.getString("imagenvis"));
                qui.setImagenloc(rs.getString("imagenloc"));
                quinita.add(qui);
            }
        }catch(SQLException ex){
            ex.printStackTrace();
            quinita=null;
        }finally{
            try{
                rs.close();
                pr.close();
                cn.close();
            }catch(SQLException ex){
                ex.printStackTrace();
            }
        }
        
        
        return quinita;
    } 
    
    public Quinela guardarPronostico(int ganador, int mayor, int menor, int id_partido, int id_usuario, int no_marcador){
        
        Quinela qui = null;
        Connection cn=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        
        try{
            
            qui= new Quinela();
            cn = Conexion.getConexion();
            String sql = "update pronostico set ganador=?, mayor=?, menor=?, no_marcador=? where id_partido=? and id_usuario=?;";
            ps = cn.prepareStatement(sql);
            ps.setInt(1, ganador);
            ps.setInt(2, mayor);
            ps.setInt(3, menor);
            ps.setInt(4, no_marcador);
            ps.setInt(5, id_partido);
            ps.setInt(6, id_usuario);
             
            ps.executeUpdate();

        }catch(Exception e){
            e.printStackTrace();
            qui = null;
        }finally{
            try{
                //rs.close();
                ps.close();
            }catch(SQLException ex){
                qui = null;
                ex.printStackTrace();
            }
        }
        
        return qui;
    
    }

    public Quinela crearPronostico(int id_usuario, int id_partido, boolean primer_marcador){
        
        Quinela qui = null;
        Connection cn=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        
        try{
            
            qui= new Quinela();
            cn = Conexion.getConexion();
            String sql = "INSERT INTO pronostico (id_usuario, id_partido, no_marcador) VALUES(?,?,?)";
            ps = cn.prepareStatement(sql); 
            
            ps.setInt(1, id_usuario);
            ps.setInt(2, id_partido);
            if(primer_marcador==true){
                ps.setInt(3, 1);
            }
            else{
                ps.setInt(3, 0); 
            }
            ps.executeUpdate();
       

        }catch(Exception e){
            e.printStackTrace();
            qui = null;
        }finally{
            try{
                //rs.close();
                ps.close();
            }catch(SQLException ex){
                qui = null;
                ex.printStackTrace();
            }
        }
        
        
        
        return qui;
    }
    /*public Quinela crearPronosticoFail(){
        
        Quinela qui = null;
        Connection cn=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        
        try{
            
            qui= new Quinela();
            cn = Conexion.getConexion();
            String sql = "INSERT INTO pronostico (id_usuario, id_partido, no_marcador) VALUES(?,?,?)";
            ps = cn.prepareStatement(sql); 
            
            Vector<Usuario> usuarios=new Usuario().mostrarJugadores();
            Vector<Partido> partidos;
            for(Usuario usu:usuarios){
                for(int i = 1; i<18; i++){
                    partidos=new Partido().mostrarPartidos(String.valueOf(i));
                    for(Partido par:partidos){

                        ps.setInt(1, usu.getId());
                        ps.setInt(2, par.getId_partido());
                        ps.setInt(3, 0); 
                        ps.executeUpdate();
                        System.out.println(usu.getId());

                    }
                }
            }
       

        }catch(Exception e){
            e.printStackTrace();
            qui = null;
        }finally{
            try{
                //rs.close();
                ps.close();
            }catch(SQLException ex){
                qui = null;
                ex.printStackTrace();
            }
        }
        
        
        
        return qui;
    }*/
    
    public String obtenerFecha(){
        String fecha = "";
        Connection cn=null;
        PreparedStatement pr=null;
        ResultSet rs=null;
        
        
        try{
            cn=Conexion.getConexion();
            String sql="";
            
            sql="select now() as time;";
            
            pr=cn.prepareStatement(sql);
            rs=pr.executeQuery();
            
            while(rs.next()){
                fecha = rs.getString("time");
            }
        }catch(SQLException ex){
            ex.printStackTrace();
            fecha=null;
        }finally{
            try{
                rs.close();
                pr.close();
                cn.close();
            }catch(SQLException ex){
                ex.printStackTrace();
            }
        }
        return fecha;
    }
    
    public Quinela guardarAcierto(boolean acierto, int id_usuario, int id_partido){
        
        Quinela qui = null;
        Connection cn=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        
        try{
            
            qui= new Quinela();
            cn = Conexion.getConexion();
            String sql = "update pronostico set acierto = ? where id_usuario =? and id_partido =?;";
            ps = cn.prepareStatement(sql);
            ps.setBoolean(1, acierto);
            ps.setInt(2, id_usuario);
            ps.setInt(3, id_partido);
            
             
            ps.executeUpdate();

        }catch(Exception e){
            e.printStackTrace();
            qui = null;
        }finally{
            try{
                //rs.close();
                ps.close();
            }catch(SQLException ex){
                qui = null;
                ex.printStackTrace();
            }
        }
        
        return qui;
    
    }
    
    public int obtenerPorcentajelocal(int id_partido, int semana){
        int local = 0;
        int numusus = 0;
        Vector <Quinela> q;
        Vector<Usuario> usuarios=new Usuario().mostrarUsuarios("0");
        for(Usuario usu:usuarios){
            q =new Quinela().mostrarPronosticos(usu.getId(),semana);
                for(Quinela qui:q){
                    if(id_partido == qui.getId_partido()){
                        if(qui.getId_local() == qui.getGanador()){
                            local++;
                        }
                    }
                }
            if(usu.getRol()!=1){
                numusus++;
            }
        }
        local = (local*100)/numusus;
        return local;
    }
    
    public int obtenerPorcentajevisitante(int id_partido, int semana){
        int vis = 0;
        int numusus = 0;
        Vector <Quinela> q;
        Vector<Usuario> usuarios=new Usuario().mostrarUsuarios("0");
        for(Usuario usu:usuarios){
            q =new Quinela().mostrarPronosticos(usu.getId(),semana);
                for(Quinela qui:q){
                    if(id_partido == qui.getId_partido()){
                        if(qui.getId_visitante() == qui.getGanador()){
                            vis++;
                        }
                    }
                }
            if(usu.getRol()!=1){
                numusus++;
            }
        }

        vis = (vis*100)/numusus;
        return vis;
    }
    
    
    public int getId_pronostico() {
        return id_pronostico;
    }

    public void setId_pronostico(int id_pronostico) {
        this.id_pronostico = id_pronostico;
    }

    public int getId_partido() {
        return id_partido;
    }

    public void setId_partido(int id_partido) {
        this.id_partido = id_partido;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public int getGanador() {
        return ganador;
    }

    public void setGanador(int ganador) {
        this.ganador = ganador;
    }

    public int getMayor() {
        return mayor;
    }

    public void setMayor(int mayor) {
        this.mayor = mayor;
    }

    public int getMenor() {
        return menor;
    }

    public void setMenor(int menor) {
        this.menor = menor;
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

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getImagenvis() {
        return imagenvis;
    }

    public void setImagenvis(String imagenvis) {
        this.imagenvis = imagenvis;
    }

    public String getImagenloc() {
        return imagenloc;
    }

    public void setImagenloc(String imagenloc) {
        this.imagenloc = imagenloc;
    }

    public String getAcierto() {
        return acierto;
    }

    public void setAcierto(String acierto) {
        this.acierto = acierto;
    }

    public int getNo_marcador() {
        return no_marcador;
    }

    public void setNo_marcador(int no_marcador) {
        this.no_marcador = no_marcador;
    }

    
    
}
