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
    private String nombreU;
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
    private String nombreE;

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
                    + " and p.visitante=v.id_equipo and pr.id_partido = p.id_partido and p.semana = ? and pr.id_usuario = ? order by p.id_partido";
            
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
    
    public Vector<Quinela> cosadelacosa(int semana){
        
        Vector<Quinela> quinita=new Vector<Quinela>();
        
        Connection cn=null;
        PreparedStatement pr=null;
        ResultSet rs=null;
        
        
        try{
            cn=Conexion.getConexion();
            String sql="";
            
            sql="select u.nombre as nombreU, e.nombre_equipo as nombreE, pr.mayor as mayor, pr.menor as menor, pr.acierto as acierto, pr.no_marcador as marca from  partido as p, usuario as u, pronostico as pr, equipo as e where"
                    + " pr.id_partido = p.id_partido and pr.id_usuario=u.id_usuario and pr.ganador = e.id_equipo and p.semana = ? order by u.id_usuario;";
            
            pr=cn.prepareStatement(sql);
            pr.setInt(1, semana);
            rs=pr.executeQuery();
            
            while(rs.next()){
                Quinela qui=new Quinela();
                qui.setNombreU(rs.getString("nombreU"));
                qui.setNombreE(rs.getString("nombreE"));
                qui.setMayor(rs.getInt("mayor"));
                qui.setMenor(rs.getInt("menor"));
                qui.setAcierto(rs.getString("acierto"));
                qui.setNo_marcador(rs.getInt("marca"));
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
            System.out.println(no_marcador);
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

    public Quinela crearPronostico(int id_usuario){
        
        Quinela qui = null;
        Connection cn=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        
        try{
            
            qui= new Quinela();
            cn = Conexion.getConexion();
            Vector<Partido> partidos;
            for(int i=1; i<=17; i++){
                partidos = new Partido().mostrarPartidos(String.valueOf(i));
                for(Partido par:partidos){
                    String sql = "INSERT INTO pronostico (id_usuario, id_partido, no_marcador) VALUES(?,?,?)";
                    ps = cn.prepareStatement(sql); 
                    ps.setInt(1, id_usuario);
                    ps.setInt(2, par.getId_partido());
                    if(par.isPr_mar()==true){
                        ps.setInt(3, 1);
                    }
                    else{
                        ps.setInt(3, 0); 
                    }
                    ps.executeUpdate();
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
    }
    
     public Quinela crearPronostico2(int id_partido, boolean primer_marcador){
        
        Quinela qui = null;
        Connection cn=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        
        try{
            
            qui= new Quinela();
            cn = Conexion.getConexion();
            Vector<Usuario> usuarios;
            
                usuarios = new Usuario().mostrarJugadores();
                for(Usuario par:usuarios){
                    String sql = "INSERT INTO pronostico (id_usuario, id_partido, no_marcador) VALUES(?,?,?)";
                    ps = cn.prepareStatement(sql); 
                    ps.setInt(1, par.getId());
                    ps.setInt(2, id_partido);
                    if(primer_marcador==true){
                        ps.setInt(3, 1);
                    }
                    else{
                        ps.setInt(3, 0); 
                    }
                    ps.executeUpdate();
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
    }
    
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
    
    public Quinela guardarAcierto(boolean pr_mar, String mayoreal, String menoreal, int id_partido, int semana, int ganadoreal){
        
        Quinela qui = null;
        Connection cn=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        
        try{
            
            qui= new Quinela();
            cn = Conexion.getConexion();
            String sql;
            
            Puntaje puntazo = new Puntaje();
            int dl = 0; 
            int ds = 0; 
                
            Vector<Usuario> usuarios=new Usuario().mostrarJugadores();
            Vector <Quinela> q ;
            Vector <Integer> usug = new Vector<>();
            
                for(Usuario usu:usuarios){
                    q =new Quinela().mostrarPronosticos(usu.getId(),semana);
                    for(Quinela quis:q){
                        if(id_partido == quis.getId_partido()){
                            if(quis.getGanador() == ganadoreal){
                                sql = "update pronostico set acierto = ? where id_usuario =? and id_partido =?;";
                                ps = cn.prepareStatement(sql);
                                ps.setBoolean(1, true);
                                ps.setInt(2, usu.getId());
                                ps.setInt(3, id_partido);
                                ps.executeUpdate();
                                
                                sql = "update puntaje set p = p + 1 where id_usuario = ? and semana = ?;";
                                ps = cn.prepareStatement(sql);
                                ps.setInt(1,usu.getId());
                                ps.setInt(2,semana);
                                
                                ps.executeUpdate();
                                
                                usug.add(usu.getId());
                            }
                            else{
                                sql = "update pronostico set acierto = ? where id_usuario =? and id_partido =?;";
                                ps = cn.prepareStatement(sql);
                                ps.setBoolean(1, false);
                                ps.setInt(2, usu.getId());
                                ps.setInt(3, id_partido);
                                ps.executeUpdate();
                            }
                            if(pr_mar){
                                int Mr = Integer.parseInt(mayoreal);
                                int mr = Integer.parseInt(menoreal);
                                int dM = 0;
                                int dm = 0;
                                if(Mr>quis.getMayor()){
                                    dM = Mr - quis.getMayor();
                                }else{
                                    dM = quis.getMayor() - Mr;
                                }
                                if(mr>quis.getMenor()){
                                    dm = mr - quis.getMenor();
                                }else{
                                    dm = quis.getMenor() - mr;
                                }
                                dl = dM + dm;
                                puntazo.actualizar_dl(usu.getId(), dl, semana);
                            }
                            if(qui.getNo_marcador() == 2){
                                int Mr = Integer.parseInt(mayoreal);
                                int mr = Integer.parseInt(menoreal);
                                int dM = 0;
                                int dm = 0;
                                if(Mr>quis.getMayor()){
                                    dM = Mr - qui.getMayor();
                                }else{
                                    dM = quis.getMayor() - Mr;
                                }
                                if(mr>qui.getMenor()){
                                    dm = mr - quis.getMenor();
                                }else{
                                    dm = qui.getMenor() - mr;
                                }
                                ds = dM + dm;
                                puntazo.actualizar_ds(usu.getId(), ds, semana);
                            }
                        }
                    }
                }
                int numL = new Liga().numL();
                Vector<Enfrentamiento> enfrents;
                String confl = " ";
                String confv = " ";
                String regl = " ";
                String regv = " ";
                int jgl = 0;
                int jgv = 0;
                int jpl = 0;
                int jpv = 0;
                int jel = 0;
                int jev = 0;
                String divl = " ";
                String divv = " ";
                String afcl = " ";
                String afcv = " ";
                String nfcl = " ";
                String nfcv = " ";
                int pl = 0;
                int pv = 0;
                int dll = 0;
                int dlv = 0;
                int dsl = 0;
                int dsv = 0;
                int no_liga = 0;
                int ganador;
                for(int l = 1; l<=numL; l++){
                    
                    enfrents = new Enfrentamiento().mostrarEnfrentamientos(semana, l);
                    for(Enfrentamiento E:enfrents){
                        
                        ganador = 0;
                        
                        sql = "select l.no_liga, l.conferencia, l.region, l.jg, l.jp, l.je, l.divi, l.afc, l.nfc, p.p, p.dl, p.ds from liga as l, usuario as u, puntaje as p "
                                + "where l.id_usuario = u.id_usuario and p.id_usuario = u.id_usuario and p.semana = ? and u.id_usuario=?";
                        ps=cn.prepareStatement(sql);
                        ps.setInt(1, semana);
                        ps.setInt(2, E.getLid());
                        rs=ps.executeQuery();
                        while(rs.next()){
                            no_liga = rs.getInt("no_liga");
                            confl = rs.getString("conferencia");
                            regl = rs.getString("region");
                            jgl = rs.getInt("jg");
                            jpl = rs.getInt("jp");
                            jel = rs.getInt("je");
                            divl = rs.getString("divi");
                            afcl = rs.getString("afc");
                            nfcl = rs.getString("nfc");
                            pl = rs.getInt("p");
                            dll = rs.getInt("dl");
                            dsl = rs.getInt("ds");
                        }
                        if(E.getLid()==1000){
                            pl = new Puntaje().promedio(semana);
                            dll = new Puntaje().peordl(semana);
                            dsl = new Puntaje().peords(semana);
                        }
                        sql = "select l.conferencia, l.region, l.jg, l.jp, l.je, l.divi, l.afc, l.nfc, p.p, p.dl, p.ds from liga as l, usuario as u, puntaje as p "
                                + "where l.id_usuario = u.id_usuario and p.id_usuario = u.id_usuario and p.semana = ? and u.id_usuario=?";
                        ps=cn.prepareStatement(sql);
                        ps.setInt(1, semana);
                        ps.setInt(2, E.getVid());
                        rs=ps.executeQuery();
                        int aux1 = 0;
                        int aux2 = 0;
                        while(rs.next()){
                            confv = rs.getString("conferencia");
                            regv = rs.getString("region");
                            jgv = rs.getInt("jg");
                            jpv = rs.getInt("jp");
                            jev = rs.getInt("je");
                            divv = rs.getString("divi");
                            afcv = rs.getString("afc");
                            nfcv = rs.getString("nfc");
                            pv = rs.getInt("p");
                            dlv = rs.getInt("dl");
                            dsv = rs.getInt("ds");
                        }
                        if(E.getVid()==1000){
                            pv = new Puntaje().promedio(semana);
                            dlv = new Puntaje().peordl(semana);
                            dsv = new Puntaje().peords(semana);
                        }
                        
                        if(pl>pv){
                            ganador = E.getLid();
                            jgl++;
                            jpv++;
                        }
                        else if(pv>pl){
                            ganador = E.getVid();
                            jpl++;
                            jgv++;
                        }
                        else if(pv==pl){
                            if(dll<dlv){
                                ganador = E.getLid();
                                jgl++;
                                jpv++;
                            }
                            else if(dlv<dll){
                                ganador = E.getVid();
                                jpl++;
                                jgv++;
                            }
                            else if(dlv==dll){
                                if(dsl<dsv){
                                    ganador = E.getLid();
                                    jgl++;
                                    jpv++;
                                }
                                else if(dsv<dsl){
                                    ganador = E.getVid();
                                    jpl++;
                                    jgv++;
                                }
                                else if(dsv==dsl){
                                    ganador = 0;
                                    jel++;
                                    jev++;
                                }
                            }
                        }
                        
                        if(confl.equalsIgnoreCase("AMERICANA")){
                            
                            if(confl.equalsIgnoreCase(confv)){
                                
                                aux1 = (String.valueOf((afcl.substring(0, 2)).charAt(1))).equals("-") ? Integer.parseInt(afcl.substring(0, 1)):Integer.parseInt(afcl.substring(0, 2));
                                aux2 = (String.valueOf((afcl.substring(afcl.length()-2, afcl.length())).charAt(0))).equals("-") ? Integer.parseInt(afcl.substring(afcl.length()-1, afcl.length())):Integer.parseInt(afcl.substring(afcl.length()-2, afcl.length())); 
                                if(E.getGanador()!=E.getLid()){
                                    aux1 = ganador == E.getLid() ? aux1+1:aux1;
                                }
                                else{
                                    aux1 = ganador == E.getLid() ? aux1:aux1-1;
                                }
                                if(E.getGanador()!=E.getVid()){
                                    aux2 = ganador == E.getVid() ? aux2+1:aux2;
                                }
                                else{
                                    aux2 = ganador == E.getVid() ? aux2:aux2-1;
                                }
                                afcl = aux1 + "-" + aux2;
                                
                                aux1 = (String.valueOf((afcv.substring(0, 2)).charAt(1))).equals("-") ? Integer.parseInt(afcv.substring(0, 1)):Integer.parseInt(afcv.substring(0, 2)); 
                                aux2 = (String.valueOf((afcv.substring(afcv.length()-2, afcv.length())).charAt(0))).equals("-") ? Integer.parseInt(afcv.substring(afcv.length()-1, afcv.length())):Integer.parseInt(afcv.substring(afcv.length()-2, afcv.length())); 
                                if(E.getGanador()!=E.getLid()){
                                    aux2 = ganador == E.getLid() ? aux2+1:aux2;
                                }
                                else{
                                    aux2 = ganador == E.getLid() ? aux2:aux2-1;
                                }
                                if(E.getGanador()!=E.getVid()){
                                    aux1 = ganador == E.getVid() ? aux1+1:aux1;
                                }
                                else{
                                    aux1 = ganador == E.getVid() ? aux1:aux1-1;
                                }
                                afcv = aux1 + "-" + aux2;
                                
                            }

                            else{
                                
                                aux1 = (String.valueOf((nfcl.substring(0, 2)).charAt(1))).equals("-") ? Integer.parseInt(nfcl.substring(0, 1)):Integer.parseInt(nfcl.substring(0, 2)); 
                                aux2 = (String.valueOf((nfcl.substring(nfcl.length()-2, nfcl.length())).charAt(0))).equals("-") ? Integer.parseInt(nfcl.substring(nfcl.length()-1, nfcl.length())):Integer.parseInt(nfcl.substring(nfcl.length()-2, nfcl.length())); 
                                if(E.getGanador()!=E.getLid()){
                                    aux1 = ganador == E.getLid() ? aux1+1:aux1;
                                }
                                else{
                                    aux1 = ganador == E.getLid() ? aux1:aux1-1;
                                }
                                if(E.getGanador()!=E.getVid()){
                                    aux2 = ganador == E.getVid() ? aux2+1:aux2;
                                }
                                else{
                                    aux2 = ganador == E.getVid() ? aux2:aux2-1;
                                }
                                nfcl = aux1 + "-" + aux2;
                                
                                aux1 = (String.valueOf((afcv.substring(0, 2)).charAt(1))).equals("-") ? Integer.parseInt(afcv.substring(0, 1)):Integer.parseInt(afcv.substring(0, 2)); 
                                aux2 = (String.valueOf((afcv.substring(afcv.length()-2, afcv.length())).charAt(0))).equals("-") ? Integer.parseInt(afcv.substring(afcv.length()-1, afcv.length())):Integer.parseInt(afcv.substring(afcv.length()-2, afcv.length())); 
                                if(E.getGanador()!=E.getLid()){
                                    aux2 = ganador == E.getLid() ? aux2+1:aux2;
                                }
                                else{
                                    aux2 = ganador == E.getLid() ? aux2:aux2-1;
                                }
                                if(E.getGanador()!=E.getVid()){
                                    aux1 = ganador == E.getVid() ? aux1+1:aux1;
                                }
                                else{
                                    aux1 = ganador == E.getVid() ? aux1:aux1-1;
                                }
                                afcv = aux1 + "-" + aux2;
                                
                            }

                        }
                        if(confl.equalsIgnoreCase("NACIONAL")){
                            
                            if(confl.equalsIgnoreCase(confv)){
                                
                                aux1 = (String.valueOf((nfcl.substring(0, 2)).charAt(1))).equals("-") ? Integer.parseInt(nfcl.substring(0, 1)):Integer.parseInt(nfcl.substring(0, 2)); 
                                aux2 = (String.valueOf((nfcl.substring(nfcl.length()-2, nfcl.length())).charAt(0))).equals("-") ? Integer.parseInt(nfcl.substring(nfcl.length()-1, nfcl.length())):Integer.parseInt(nfcl.substring(nfcl.length()-2, nfcl.length())); 
                                if(E.getGanador()!=E.getLid()){
                                    aux1 = ganador == E.getLid() ? aux1+1:aux1;
                                }
                                else{
                                    aux1 = ganador == E.getLid() ? aux1:aux1-1;
                                }
                                if(E.getGanador()!=E.getVid()){
                                    aux2 = ganador == E.getVid() ? aux2+1:aux2;
                                }
                                else{
                                    aux2 = ganador == E.getVid() ? aux2:aux2-1;
                                }
                                nfcl = aux1 + "-" + aux2;
                                
                                aux1 = (String.valueOf((nfcv.substring(0, 2)).charAt(1))).equals("-") ? Integer.parseInt(nfcv.substring(0, 1)):Integer.parseInt(nfcv.substring(0, 2)); 
                                aux2 = (String.valueOf((nfcv.substring(nfcv.length()-2, nfcv.length())).charAt(0))).equals("-") ? Integer.parseInt(nfcv.substring(nfcv.length()-1, nfcv.length())):Integer.parseInt(nfcv.substring(nfcv.length()-2, nfcv.length())); 
                                if(E.getGanador()!=E.getLid()){
                                    aux2 = ganador == E.getLid() ? aux2+1:aux2;
                                }
                                else{
                                    aux2 = ganador == E.getLid() ? aux2:aux2-1;
                                }
                                if(E.getGanador()!=E.getVid()){
                                    aux1 = ganador == E.getVid() ? aux1+1:aux1;
                                }
                                else{
                                    aux1 = ganador == E.getVid() ? aux1:aux1-1;
                                }
                                nfcv = aux1 + "-" + aux2;
                                
                            }

                            else{
                                
                                aux1 = (String.valueOf((afcl.substring(0, 2)).charAt(1))).equals("-") ? Integer.parseInt(afcl.substring(0, 1)):Integer.parseInt(afcl.substring(0, 2)); 
                                aux2 = (String.valueOf((afcl.substring(afcl.length()-2, afcl.length())).charAt(0))).equals("-") ? Integer.parseInt(afcl.substring(afcl.length()-1, afcl.length())):Integer.parseInt(afcl.substring(afcl.length()-2, afcl.length())); 
                                if(E.getGanador()!=E.getLid()){
                                    aux1 = ganador == E.getLid() ? aux1+1:aux1;
                                }
                                else{
                                    aux1 = ganador == E.getLid() ? aux1:aux1-1;
                                }
                                if(E.getGanador()!=E.getVid()){
                                    aux2 = ganador == E.getVid() ? aux2+1:aux2;
                                }
                                else{
                                    aux2 = ganador == E.getVid() ? aux2:aux2-1;
                                }
                                afcl = aux1 + "-" + aux2;
                                
                                aux1 = (String.valueOf((nfcv.substring(0, 2)).charAt(1))).equals("-") ? Integer.parseInt(nfcv.substring(0, 1)):Integer.parseInt(nfcv.substring(0, 2)); 
                                aux2 = (String.valueOf((nfcv.substring(nfcv.length()-2, nfcv.length())).charAt(0))).equals("-") ? Integer.parseInt(nfcv.substring(nfcv.length()-1, nfcv.length())):Integer.parseInt(nfcv.substring(nfcv.length()-2, nfcv.length())); 
                                if(E.getGanador()!=E.getLid()){
                                    aux2 = ganador == E.getLid() ? aux2+1:aux2;
                                }
                                else{
                                    aux2 = ganador == E.getLid() ? aux2:aux2-1;
                                }
                                if(E.getGanador()!=E.getVid()){
                                    aux1 = ganador == E.getVid() ? aux1+1:aux1;
                                }
                                else{
                                    aux1 = ganador == E.getVid() ? aux1:aux1-1;
                                }
                                nfcv = aux1 + "-" + aux2;
                                
                            }

                        }
                        if(regl.equalsIgnoreCase(regv)){
                            aux1 = (String.valueOf((divl.substring(0, 2)).charAt(1))).equals("-") ? Integer.parseInt(divl.substring(0, 1)):Integer.parseInt(divl.substring(0, 2)); 
                            aux2 = (String.valueOf((divl.substring(divl.length()-2, divl.length())).charAt(0))).equals("-") ? Integer.parseInt(divl.substring(divl.length()-1, divl.length())):Integer.parseInt(divl.substring(divl.length()-2, divl.length())); 
                            if(E.getGanador()!=E.getLid()){
                                aux1 = ganador == E.getLid() ? aux1+1:aux1;
                            }
                            else{
                                aux1 = ganador == E.getLid() ? aux1:aux1-1;
                            }
                            if(E.getGanador()!=E.getVid()){
                                aux2 = ganador == E.getVid() ? aux2+1:aux2;
                            }
                            else{
                                aux2 = ganador == E.getVid() ? aux2:aux2-1;
                            }
                            divl = aux1 + "-" + aux2;
                            aux1 = (String.valueOf((divv.substring(0, 2)).charAt(1))).equals("-") ? Integer.parseInt(divv.substring(0, 1)):Integer.parseInt(divv.substring(0, 2)); 
                            aux2 = (String.valueOf((divv.substring(divv.length()-2, divv.length())).charAt(0))).equals("-") ? Integer.parseInt(divv.substring(divv.length()-1, divv.length())):Integer.parseInt(divv.substring(divv.length()-2, divv.length())); 
                            if(E.getGanador()!=E.getLid()){
                                aux2 = ganador == E.getLid() ? aux2+1:aux2;
                            }
                            else{
                                aux2 = ganador == E.getLid() ? aux2:aux2-1;
                            }
                            if(E.getGanador()!=E.getVid()){
                                aux1 = ganador == E.getVid() ? aux1+1:aux1;
                            }
                            else{
                                aux1 = ganador == E.getVid() ? aux1:aux1-1;
                            }
                            divv = aux1 + "-" + aux2;
                        }
                        
                        sql = "update enfrentamiento set ganador = ? where semana = ? and visitante = ? and local = ?;";
                        ps = cn.prepareStatement(sql);
                        ps.setInt(1,ganador);
                        ps.setInt(2,semana);
                        ps.setInt(3,E.getVid());
                        ps.setInt(4,E.getLid());

                        ps.executeUpdate();
                        sql = "update liga set jg = ?, jp = ?, je = ?, divi = ?, afc = ?, nfc = ?, pf = pf+?, pc = pc+? where id_usuario = ? and no_liga = ? and conferencia = ? and region = ?;";
                        ps = cn.prepareStatement(sql);
                        ps.setInt(1,jgl);
                        ps.setInt(2,jpl);
                        ps.setInt(3,jel);
                        ps.setString(4,divl);
                        ps.setString(5,afcl);
                        ps.setString(6,nfcl);
                        for(Integer i:usug){
                            if(i == E.getLid()){
                                ps.setInt(7,1);
                            }
                            else{
                                ps.setInt(7,0);
                            }
                            if(i == E.getVid()){
                                ps.setInt(8,1);
                            }
                            else{
                                ps.setInt(8,0);
                            }
                        }
                        ps.setInt(9,E.getLid());
                        ps.setInt(10,no_liga);
                        ps.setString(11,confl);
                        ps.setString(12,regl);
                        
                        ps.executeUpdate();
                        
                      ps.executeUpdate();
                        sql = "update liga set jg = ?, jp = ?, je = ?, divi = ?, afc = ?, nfc = ?, pf = pf + ?, pc = pc + ? where id_usuario = ? and no_liga = ? and conferencia = ? and region = ?;";
                        ps = cn.prepareStatement(sql);
                        ps.setInt(1,jgv);
                        ps.setInt(2,jpv);
                        ps.setInt(3,jev);
                        ps.setString(4,divv);
                        ps.setString(5,afcv);
                        ps.setString(6,nfcv);
                        for(Integer i:usug){
                            if(i == E.getVid()){
                                ps.setInt(7,1);
                            }
                            else{
                                ps.setInt(7,0);
                            }
                            if(i == E.getLid()){
                                ps.setInt(8,1);
                            }
                            else{
                                ps.setInt(8,0);
                            }
                        }
                        ps.setInt(9,E.getVid());
                        ps.setInt(10,no_liga);
                        ps.setString(11,confv);
                        ps.setString(12,regv);
                        
                        ps.executeUpdate();
                    }
                    
                }
                

        }catch(Exception e){
            e.printStackTrace();
            System.out.println("-----------------------------------------------------------------------");
            System.out.println(id_usuario);
            System.out.println(semana);
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
