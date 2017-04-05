
package Clases;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;



public class Enfrentamiento {

    private int id_enfrentamiento;
    private int vid;
    private String visitante;
    private int vp;
    private int vdl;
    private int vds;
    private int lds;
    private int ldl;
    private int lp;
    private String local;
    private int lid;
    private int ganador;
    private static int porcentaje;
   
    
    public Enfrentamiento(){}
    
    public Vector<Enfrentamiento> mostrarEnfrentamientos(int semana, int liga){
        
       Vector<Enfrentamiento> enfrentamientos=new Vector<Enfrentamiento>();
        
        Connection cn=null;
        PreparedStatement pr=null;
        ResultSet rs=null;
        int dl, ds;
        
        try{
            cn=Conexion.getConexion();
            String sql="SELECT * FROM enfrenL where semana = ?  and liga = ?";
            pr=cn.prepareStatement(sql);
            pr.setInt(1, semana);
            pr.setInt(2, liga);
            rs=pr.executeQuery();
            while(rs.next()){
                Enfrentamiento enfr=new Enfrentamiento();
                enfr.setVid(rs.getInt("vid"));
                enfr.setVisitante(rs.getString("visitante"));
                enfr.setVp(rs.getInt("vp"));
                dl = rs.getString("vdl")!=null ? rs.getInt("vdl") : new Puntaje().peordl(semana);
                ds = rs.getString("vds")!=null ? rs.getInt("vds") : new Puntaje().peords(semana);
                enfr.setVdl(dl);
                enfr.setVds(ds);
                dl = 0;
                ds = 0;
                dl = rs.getString("ldl")!=null ? rs.getInt("ldl") : new Puntaje().peordl(semana);
                ds = rs.getString("lds")!=null ? rs.getInt("lds") : new Puntaje().peords(semana);
                enfr.setLds(ds);
                enfr.setLdl(dl);
                enfr.setLp(rs.getInt("lp"));
                enfr.setLid(rs.getInt("lid"));
                enfr.setLocal(rs.getString("local"));
                enfr.setGanador(rs.getInt("ganador"));
                enfrentamientos.add(enfr);
                dl = 0;
                ds = 0;
            }
        }catch(SQLException ex){
            ex.printStackTrace();
            enfrentamientos=null;
        }finally{
            try{
                rs.close();
                pr.close();
                cn.close();
            }catch(SQLException ex){
                ex.printStackTrace();
            }
        }
        
        
        return enfrentamientos;
        
    }
    
    public int idEnfrentamientos(int semana, int id){
        
        
        Connection cn=null;
        PreparedStatement pr=null;
        ResultSet rs=null;
        
            int ide = 0;
        
        try{
            cn=Conexion.getConexion();
            String sql="select * from enfrentamiento where semana = ? and (visitante = ? or local = ?);";
            pr=cn.prepareStatement(sql);
            pr.setInt(1, semana);
            pr.setInt(2, id);
            pr.setInt(3, id);
            rs=pr.executeQuery();
            while(rs.next()){
                Enfrentamiento enfr=new Enfrentamiento();
                ide = rs.getInt("id_enfrentamiento");
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
        
        
        return ide;
        
    }

    public void CrearEnfrentamientos(){
       
        Connection cn=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        
        try{
            
            cn = Conexion.getConexion();
            String sql = "select id_usuario from liga order by no_liga, conferencia, region";
            ps = cn.prepareStatement(sql); 
            rs=ps.executeQuery();
            ArrayList<Integer> ids_Usuarios = new ArrayList<Integer>();
            
            while(rs.next()){
            
                ids_Usuarios.add(rs.getInt("id_usuario"));
            
            }
            for(int j = 0; j<new Liga().numL();j++){
               
                int s = 1;
                //++++++++++++SEMANA 1+++++++++++++++
                
                for(int i = 0; i<=35; i+=5){
                
                    sql = "INSERT INTO enfrentamiento (visitante,local,semana,liga) VALUES("+ids_Usuarios.get(i+(40*j))+","+ids_Usuarios.get(i+(40*j)+1)+","+s+","+(j+1)+")";
                    ps = cn.prepareStatement(sql); 
                    ps.executeUpdate();
                    sql = "INSERT INTO enfrentamiento (visitante,local,semana,liga) VALUES("+ids_Usuarios.get(2+i+(40*j))+","+ids_Usuarios.get(3+i+(40*j))+","+s+","+(j+1)+")";
                    ps = cn.prepareStatement(sql); 
                    ps.executeUpdate();
                }
                    
                    sql = "INSERT INTO enfrentamiento (visitante,local,semana,liga) VALUES("+ids_Usuarios.get(4+(40*j))+","+ids_Usuarios.get(34+(40*j))+","+s+","+(j+1)+")";
                    ps = cn.prepareStatement(sql); 
                    ps.executeUpdate();
                    sql = "INSERT INTO enfrentamiento (visitante,local,semana,liga) VALUES("+ids_Usuarios.get(9+(40*j))+","+ids_Usuarios.get(39+(40*j))+","+s+","+(j+1)+")";
                    ps = cn.prepareStatement(sql); 
                    ps.executeUpdate();
                    sql = "INSERT INTO enfrentamiento (visitante,local,semana,liga) VALUES("+ids_Usuarios.get(14+(40*j))+","+ids_Usuarios.get(24+(40*j))+","+s+","+(j+1)+")";
                    ps = cn.prepareStatement(sql); 
                    ps.executeUpdate();
                    sql = "INSERT INTO enfrentamiento (visitante,local,semana,liga) VALUES("+ids_Usuarios.get(19+(40*j))+","+ids_Usuarios.get(29+(40*j))+","+s+","+(j+1)+")";
                    ps = cn.prepareStatement(sql); 
                    ps.executeUpdate();
                //++++++++++++++SEMANA 2++++++++++
                
                s++;
                for(int i = 0; i<=35; i+=5){
                
                    sql = "INSERT INTO enfrentamiento (visitante,local,semana,liga) VALUES("+ids_Usuarios.get(i+(40*j))+","+ids_Usuarios.get(i+(40*j)+2)+","+s+","+(j+1)+")";
                    ps = cn.prepareStatement(sql); 
                    ps.executeUpdate();
                    sql = "INSERT INTO enfrentamiento (visitante,local,semana,liga) VALUES("+ids_Usuarios.get(1+i+(40*j))+","+ids_Usuarios.get(4+i+(40*j))+","+s+","+(j+1)+")";
                    ps = cn.prepareStatement(sql); 
                    ps.executeUpdate();
                }
                    
                    sql = "INSERT INTO enfrentamiento (visitante,local,semana,liga) VALUES("+ids_Usuarios.get(3+(40*j))+","+ids_Usuarios.get(33+(40*j))+","+s+","+(j+1)+")";
                    ps = cn.prepareStatement(sql); 
                    ps.executeUpdate();
                    sql = "INSERT INTO enfrentamiento (visitante,local,semana,liga) VALUES("+ids_Usuarios.get(8+(40*j))+","+ids_Usuarios.get(38+(40*j))+","+s+","+(j+1)+")";
                    ps = cn.prepareStatement(sql); 
                    ps.executeUpdate();
                    sql = "INSERT INTO enfrentamiento (visitante,local,semana,liga) VALUES("+ids_Usuarios.get(13+(40*j))+","+ids_Usuarios.get(23+(40*j))+","+s+","+(j+1)+")";
                    ps = cn.prepareStatement(sql); 
                    ps.executeUpdate();
                    sql = "INSERT INTO enfrentamiento (visitante,local,semana,liga) VALUES("+ids_Usuarios.get(18+(40*j))+","+ids_Usuarios.get(28+(40*j))+","+s+","+(j+1)+")";
                    ps = cn.prepareStatement(sql); 
                    ps.executeUpdate();  
                    //++++++++++++++SEMANA 3++++++++++
                
                s++;
                for(int i = 0; i<=35; i+=5){
                
                    sql = "INSERT INTO enfrentamiento (visitante,local,semana,liga) VALUES("+ids_Usuarios.get(i+(40*j))+","+ids_Usuarios.get(i+(40*j)+4)+","+s+","+(j+1)+")";
                    ps = cn.prepareStatement(sql); 
                    ps.executeUpdate();
                    sql = "INSERT INTO enfrentamiento (visitante,local,semana,liga) VALUES("+ids_Usuarios.get(1+i+(40*j))+","+ids_Usuarios.get(3+i+(40*j))+","+s+","+(j+1)+")";
                    ps = cn.prepareStatement(sql); 
                    ps.executeUpdate();
                }
                    
                    sql = "INSERT INTO enfrentamiento (visitante,local,semana,liga) VALUES("+ids_Usuarios.get(2+(40*j))+","+ids_Usuarios.get(32+(40*j))+","+s+","+(j+1)+")";
                    ps = cn.prepareStatement(sql); 
                    ps.executeUpdate();
                    sql = "INSERT INTO enfrentamiento (visitante,local,semana,liga) VALUES("+ids_Usuarios.get(7+(40*j))+","+ids_Usuarios.get(37+(40*j))+","+s+","+(j+1)+")";
                    ps = cn.prepareStatement(sql); 
                    ps.executeUpdate();
                    sql = "INSERT INTO enfrentamiento (visitante,local,semana,liga) VALUES("+ids_Usuarios.get(12+(40*j))+","+ids_Usuarios.get(22+(40*j))+","+s+","+(j+1)+")";
                    ps = cn.prepareStatement(sql); 
                    ps.executeUpdate();
                    sql = "INSERT INTO enfrentamiento (visitante,local,semana,liga) VALUES("+ids_Usuarios.get(17+(40*j))+","+ids_Usuarios.get(27+(40*j))+","+s+","+(j+1)+")";
                    ps = cn.prepareStatement(sql); 
                    ps.executeUpdate();
                    //++++++++++++++SEMANA 4++++++++++
                
                s++;
                for(int i = 0; i<=35; i+=5){
                
                    sql = "INSERT INTO enfrentamiento (visitante,local,semana,liga) VALUES("+ids_Usuarios.get(i+(40*j))+","+ids_Usuarios.get(3+i+(40*j))+","+s+","+(j+1)+")";
                    ps = cn.prepareStatement(sql); 
                    ps.executeUpdate();
                    sql = "INSERT INTO enfrentamiento (visitante,local,semana,liga) VALUES("+ids_Usuarios.get(2+i+(40*j))+","+ids_Usuarios.get(4+i+(40*j))+","+s+","+(j+1)+")";
                    ps = cn.prepareStatement(sql); 
                    ps.executeUpdate();
                }
                    
                    sql = "INSERT INTO enfrentamiento (visitante,local,semana,liga) VALUES("+ids_Usuarios.get(1+(40*j))+","+ids_Usuarios.get(31+(40*j))+","+s+","+(j+1)+")";
                    ps = cn.prepareStatement(sql); 
                    ps.executeUpdate();
                    sql = "INSERT INTO enfrentamiento (visitante,local,semana,liga) VALUES("+ids_Usuarios.get(6+(40*j))+","+ids_Usuarios.get(36+(40*j))+","+s+","+(j+1)+")";
                    ps = cn.prepareStatement(sql); 
                    ps.executeUpdate();
                    sql = "INSERT INTO enfrentamiento (visitante,local,semana,liga) VALUES("+ids_Usuarios.get(11+(40*j))+","+ids_Usuarios.get(21+(40*j))+","+s+","+(j+1)+")";
                    ps = cn.prepareStatement(sql); 
                    ps.executeUpdate();
                    sql = "INSERT INTO enfrentamiento (visitante,local,semana,liga) VALUES("+ids_Usuarios.get(16+(40*j))+","+ids_Usuarios.get(26+(40*j))+","+s+","+(j+1)+")";
                    ps = cn.prepareStatement(sql); 
                    ps.executeUpdate();   
                    //++++++++++++++SEMANA 5++++++++++
                
                s++;
                for(int i = 0; i<=35; i+=5){
                
                    sql = "INSERT INTO enfrentamiento (visitante,local,semana,liga) VALUES("+ids_Usuarios.get(i+1+(40*j))+","+ids_Usuarios.get(i+(40*j)+2)+","+s+","+(j+1)+")";
                    ps = cn.prepareStatement(sql); 
                    ps.executeUpdate();
                    sql = "INSERT INTO enfrentamiento (visitante,local,semana,liga) VALUES("+ids_Usuarios.get(3+i+(40*j))+","+ids_Usuarios.get(4+i+(40*j))+","+s+","+(j+1)+")";
                    ps = cn.prepareStatement(sql); 
                    ps.executeUpdate();
                }
                    
                    sql = "INSERT INTO enfrentamiento (visitante,local,semana,liga) VALUES("+ids_Usuarios.get(40*j)+","+ids_Usuarios.get(30+(40*j))+","+s+","+(j+1)+")";
                    ps = cn.prepareStatement(sql); 
                    ps.executeUpdate();
                    sql = "INSERT INTO enfrentamiento (visitante,local,semana,liga) VALUES("+ids_Usuarios.get(5+(40*j))+","+ids_Usuarios.get(35+(40*j))+","+s+","+(j+1)+")";
                    ps = cn.prepareStatement(sql); 
                    ps.executeUpdate();
                    sql = "INSERT INTO enfrentamiento (visitante,local,semana,liga) VALUES("+ids_Usuarios.get(10+(40*j))+","+ids_Usuarios.get(20+(40*j))+","+s+","+(j+1)+")";
                    ps = cn.prepareStatement(sql); 
                    ps.executeUpdate();
                    sql = "INSERT INTO enfrentamiento (visitante,local,semana,liga) VALUES("+ids_Usuarios.get(15+(40*j))+","+ids_Usuarios.get(25+(40*j))+","+s+","+(j+1)+")";
                    ps = cn.prepareStatement(sql); 
                    ps.executeUpdate();   
                    //++++++++++++++SEMANA 6++++++++++
                
                s++;
                for(int i = 0; i<=30; i+=10){
                    
                    for(int k = i; k<=i+4;k++){
                    
                        sql = "INSERT INTO enfrentamiento (visitante,local,semana,liga) VALUES("+ids_Usuarios.get(k+(40*j))+","+ids_Usuarios.get(k+5+(40*j))+","+s+","+(j+1)+")";
                        ps = cn.prepareStatement(sql); 
                        ps.executeUpdate();
                    
                    }
                
                }
                //++++++++++++++SEMANA 7++++++++++
                    
                s++;
                
                    
                for(int k = 0; k<=4;k++){

                    sql = "INSERT INTO enfrentamiento (visitante,local,semana,liga) VALUES("+ids_Usuarios.get(k+(40*j))+","+ids_Usuarios.get(k+15+(40*j))+","+s+","+(j+1)+")";
                    ps = cn.prepareStatement(sql); 
                    ps.executeUpdate();
                    sql = "INSERT INTO enfrentamiento (visitante,local,semana,liga) VALUES("+ids_Usuarios.get(k+20+(40*j))+","+ids_Usuarios.get(k+35+(40*j))+","+s+","+(j+1)+")";
                    ps = cn.prepareStatement(sql); 
                    ps.executeUpdate();
                    sql = "INSERT INTO enfrentamiento (visitante,local,semana,liga) VALUES("+ids_Usuarios.get(k+5+(40*j))+","+ids_Usuarios.get(k+10+(40*j))+","+s+","+(j+1)+")";
                    ps = cn.prepareStatement(sql); 
                    ps.executeUpdate();
                    sql = "INSERT INTO enfrentamiento (visitante,local,semana,liga) VALUES("+ids_Usuarios.get(k+25+(40*j))+","+ids_Usuarios.get(k+30+(40*j))+","+s+","+(j+1)+")";
                    ps = cn.prepareStatement(sql); 
                    ps.executeUpdate();

                }
                
                //++++++++++++++SEMANA 8++++++++++
                    
                s++;
                for(int i = 0; i<=5; i+=5){
                    
                    for(int k = i; k<=i+4;k++){
                    
                        sql = "INSERT INTO enfrentamiento (visitante,local,semana,liga) VALUES("+ids_Usuarios.get(k+(40*j))+","+ids_Usuarios.get(k+10+(40*j))+","+s+","+(j+1)+")";
                        ps = cn.prepareStatement(sql); 
                        ps.executeUpdate();
                        sql = "INSERT INTO enfrentamiento (visitante,local,semana,liga) VALUES("+ids_Usuarios.get(k+20+(40*j))+","+ids_Usuarios.get(k+30+(40*j))+","+s+","+(j+1)+")";
                        ps = cn.prepareStatement(sql); 
                        ps.executeUpdate();
                    
                    }
                
                }
                //++++++++++++++SEMANA 9++++++++++
                
                s++;
                for(int i = 0; i<=35; i+=5){
                
                    sql = "INSERT INTO enfrentamiento (visitante,local,semana,liga) VALUES("+ids_Usuarios.get(i+2+(40*j))+","+ids_Usuarios.get(i+(40*j)+1)+","+s+","+(j+1)+")";
                    ps = cn.prepareStatement(sql); 
                    ps.executeUpdate();
                    sql = "INSERT INTO enfrentamiento (visitante,local,semana,liga) VALUES("+ids_Usuarios.get(4+i+(40*j))+","+ids_Usuarios.get(3+i+(40*j))+","+s+","+(j+1)+")";
                    ps = cn.prepareStatement(sql); 
                    ps.executeUpdate();
                }
                    
                    sql = "INSERT INTO enfrentamiento (visitante,local,semana,liga) VALUES("+ids_Usuarios.get(35+(40*j))+","+ids_Usuarios.get(40*j)+","+s+","+(j+1)+")";
                    ps = cn.prepareStatement(sql); 
                    ps.executeUpdate();
                    sql = "INSERT INTO enfrentamiento (visitante,local,semana,liga) VALUES("+ids_Usuarios.get(30+(40*j))+","+ids_Usuarios.get(5+(40*j))+","+s+","+(j+1)+")";
                    ps = cn.prepareStatement(sql); 
                    ps.executeUpdate();
                    sql = "INSERT INTO enfrentamiento (visitante,local,semana,liga) VALUES("+ids_Usuarios.get(25+(40*j))+","+ids_Usuarios.get(10+(40*j))+","+s+","+(j+1)+")";
                    ps = cn.prepareStatement(sql); 
                    ps.executeUpdate();
                    sql = "INSERT INTO enfrentamiento (visitante,local,semana,liga) VALUES("+ids_Usuarios.get(20+(40*j))+","+ids_Usuarios.get(15+(40*j))+","+s+","+(j+1)+")";
                    ps = cn.prepareStatement(sql); 
                    ps.executeUpdate();
                //++++++++++++++SEMANA 10++++++++++
                
                s++;
                for(int i = 0; i<=35; i+=5){
                
                    sql = "INSERT INTO enfrentamiento (visitante,local,semana,liga) VALUES("+ids_Usuarios.get(i+(40*j)+3)+","+ids_Usuarios.get(i+(40*j))+","+s+","+(j+1)+")";
                    ps = cn.prepareStatement(sql); 
                    ps.executeUpdate();
                    sql = "INSERT INTO enfrentamiento (visitante,local,semana,liga) VALUES("+ids_Usuarios.get(4+i+(40*j))+","+ids_Usuarios.get(2+i+(40*j))+","+s+","+(j+1)+")";
                    ps = cn.prepareStatement(sql); 
                    ps.executeUpdate();
                }
                    
                    sql = "INSERT INTO enfrentamiento (visitante,local,semana,liga) VALUES("+ids_Usuarios.get(36+(40*j))+","+ids_Usuarios.get(1+(40*j))+","+s+","+(j+1)+")";
                    ps = cn.prepareStatement(sql); 
                    ps.executeUpdate();
                    sql = "INSERT INTO enfrentamiento (visitante,local,semana,liga) VALUES("+ids_Usuarios.get(31+(40*j))+","+ids_Usuarios.get(6+(40*j))+","+s+","+(j+1)+")";
                    ps = cn.prepareStatement(sql); 
                    ps.executeUpdate();
                    sql = "INSERT INTO enfrentamiento (visitante,local,semana,liga) VALUES("+ids_Usuarios.get(26+(40*j))+","+ids_Usuarios.get(11+(40*j))+","+s+","+(j+1)+")";
                    ps = cn.prepareStatement(sql); 
                    ps.executeUpdate();
                    sql = "INSERT INTO enfrentamiento (visitante,local,semana,liga) VALUES("+ids_Usuarios.get(21+(40*j))+","+ids_Usuarios.get(16+(40*j))+","+s+","+(j+1)+")";
                    ps = cn.prepareStatement(sql); 
                    ps.executeUpdate();
                //++++++++++++++SEMANA 11++++++++++
                
                s++;
                for(int i = 0; i<=35; i+=5){
                
                    sql = "INSERT INTO enfrentamiento (visitante,local,semana,liga) VALUES("+ids_Usuarios.get(4+i+(40*j))+","+ids_Usuarios.get(i+(40*j))+","+s+","+(j+1)+")";
                    ps = cn.prepareStatement(sql); 
                    ps.executeUpdate();
                    sql = "INSERT INTO enfrentamiento (visitante,local,semana,liga) VALUES("+ids_Usuarios.get(3+i+(40*j))+","+ids_Usuarios.get(1+i+(40*j))+","+s+","+(j+1)+")";
                    ps = cn.prepareStatement(sql); 
                    ps.executeUpdate();
                }
                    
                    sql = "INSERT INTO enfrentamiento (visitante,local,semana,liga) VALUES("+ids_Usuarios.get(37+(40*j))+","+ids_Usuarios.get(2+(40*j))+","+s+","+(j+1)+")";
                    ps = cn.prepareStatement(sql); 
                    ps.executeUpdate();
                    sql = "INSERT INTO enfrentamiento (visitante,local,semana,liga) VALUES("+ids_Usuarios.get(32+(40*j))+","+ids_Usuarios.get(7+(40*j))+","+s+","+(j+1)+")";
                    ps = cn.prepareStatement(sql); 
                    ps.executeUpdate();
                    sql = "INSERT INTO enfrentamiento (visitante,local,semana,liga) VALUES("+ids_Usuarios.get(27+(40*j))+","+ids_Usuarios.get(12+(40*j))+","+s+","+(j+1)+")";
                    ps = cn.prepareStatement(sql); 
                    ps.executeUpdate();
                    sql = "INSERT INTO enfrentamiento (visitante,local,semana,liga) VALUES("+ids_Usuarios.get(22+(40*j))+","+ids_Usuarios.get(17+(40*j))+","+s+","+(j+1)+")";
                    ps = cn.prepareStatement(sql); 
                    ps.executeUpdate();
                //++++++++++++++SEMANA 12++++++++++
                
                s++;
                for(int i = 0; i<=35; i+=5){
                
                    sql = "INSERT INTO enfrentamiento (visitante,local,semana,liga) VALUES("+ids_Usuarios.get(i+(40*j)+2)+","+ids_Usuarios.get(i+(40*j))+","+s+","+(j+1)+")";
                    ps = cn.prepareStatement(sql); 
                    ps.executeUpdate();
                    sql = "INSERT INTO enfrentamiento (visitante,local,semana,liga) VALUES("+ids_Usuarios.get(4+i+(40*j))+","+ids_Usuarios.get(1+i+(40*j))+","+s+","+(j+1)+")";
                    ps = cn.prepareStatement(sql); 
                    ps.executeUpdate();
                }
                    
                    sql = "INSERT INTO enfrentamiento (visitante,local,semana,liga) VALUES("+ids_Usuarios.get(38+(40*j))+","+ids_Usuarios.get(3+(40*j))+","+s+","+(j+1)+")";
                    ps = cn.prepareStatement(sql); 
                    ps.executeUpdate();
                    sql = "INSERT INTO enfrentamiento (visitante,local,semana,liga) VALUES("+ids_Usuarios.get(33+(40*j))+","+ids_Usuarios.get(8+(40*j))+","+s+","+(j+1)+")";
                    ps = cn.prepareStatement(sql); 
                    ps.executeUpdate();
                    sql = "INSERT INTO enfrentamiento (visitante,local,semana,liga) VALUES("+ids_Usuarios.get(28+(40*j))+","+ids_Usuarios.get(13+(40*j))+","+s+","+(j+1)+")";
                    ps = cn.prepareStatement(sql); 
                    ps.executeUpdate();
                    sql = "INSERT INTO enfrentamiento (visitante,local,semana,liga) VALUES("+ids_Usuarios.get(23+(40*j))+","+ids_Usuarios.get(18+(40*j))+","+s+","+(j+1)+")";
                    ps = cn.prepareStatement(sql); 
                    ps.executeUpdate();
                //++++++++++++SEMANA 13+++++++++++++++
                
                s++;
                for(int i = 0; i<=35; i+=5){
                
                    sql = "INSERT INTO enfrentamiento (visitante,local,semana,liga) VALUES("+ids_Usuarios.get(i+(40*j)+1)+","+ids_Usuarios.get(i+(40*j))+","+s+","+(j+1)+")";
                    ps = cn.prepareStatement(sql); 
                    ps.executeUpdate();
                    sql = "INSERT INTO enfrentamiento (visitante,local,semana,liga) VALUES("+ids_Usuarios.get(3+i+(40*j))+","+ids_Usuarios.get(2+i+(40*j))+","+s+","+(j+1)+")";
                    ps = cn.prepareStatement(sql); 
                    ps.executeUpdate();
                }
                    
                    sql = "INSERT INTO enfrentamiento (visitante,local,semana,liga) VALUES("+ids_Usuarios.get(39+(40*j))+","+ids_Usuarios.get(4+(40*j))+","+s+","+(j+1)+")";
                    ps = cn.prepareStatement(sql); 
                    ps.executeUpdate();
                    sql = "INSERT INTO enfrentamiento (visitante,local,semana,liga) VALUES("+ids_Usuarios.get(34+(40*j))+","+ids_Usuarios.get(9+(40*j))+","+s+","+(j+1)+")";
                    ps = cn.prepareStatement(sql); 
                    ps.executeUpdate();
                    sql = "INSERT INTO enfrentamiento (visitante,local,semana,liga) VALUES("+ids_Usuarios.get(29+(40*j))+","+ids_Usuarios.get(14+(40*j))+","+s+","+(j+1)+")";
                    ps = cn.prepareStatement(sql); 
                    ps.executeUpdate();
                    sql = "INSERT INTO enfrentamiento (visitante,local,semana,liga) VALUES("+ids_Usuarios.get(24+(40*j))+","+ids_Usuarios.get(19+(40*j))+","+s+","+(j+1)+")";
                    ps = cn.prepareStatement(sql); 
                    ps.executeUpdate();
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
         
    
    }
    
    public void crearS14(int id1, int id2, int id3, int id4, int id5, int id6, int liga){
        
        Connection cn=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        
        try{
            
            cn = Conexion.getConexion();
            String sql = "INSERT INTO enfrentamiento (visitante,local,semana,liga, ganador) VALUES( "+id1+", "+id1+", 14, "+liga+", "+id1+")";
            ps = cn.prepareStatement(sql); 
            ps.executeUpdate();
            sql = "INSERT INTO enfrentamiento (visitante,local,semana,liga) VALUES( "+id3+", "+id5+", 14, "+liga+")";
            ps = cn.prepareStatement(sql); 
            ps.executeUpdate();
            sql = "INSERT INTO enfrentamiento (visitante,local,semana,liga, ganador) VALUES( "+id2+", "+id2+", 14, "+liga+", "+id2+")";
            ps = cn.prepareStatement(sql); 
            ps.executeUpdate();
            sql = "INSERT INTO enfrentamiento (visitante,local,semana,liga) VALUES( "+id4+", "+id6+", 14, "+liga+")";
            ps = cn.prepareStatement(sql); 
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
    
    public void crearS15(){
        
        Connection cn=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        int id1 = 0, id2 = 0, id3 = 0, id4 = 0, idd1 = 0, idd2 = 0, idd3 = 0, idd4 = 0;
        
        try{
            
            cn = Conexion.getConexion();
            
            String sql;
            
            for(int liga = 1; liga<=(new Liga().numL()); liga++){
                
            sql="SELECT ganador FROM enfrenL where semana = ?  and liga = ?";
            ps=cn.prepareStatement(sql);
            ps.setInt(1, 14);
            ps.setInt(2, liga);
            rs=ps.executeQuery();
            int cont = 1;
            while(rs.next()){
                switch(cont){
                    case 1:
                        id1 = rs.getInt("ganador");
                    break;
                    case 2:
                        id2 = rs.getInt("ganador");
                    break;
                    case 3:
                        id3 = rs.getInt("ganador");
                    break;
                    case 4:
                        id4 = rs.getInt("ganador");
                    break;
                    case 5:
                        idd1 = rs.getInt("ganador");
                    break;
                    case 6:
                        idd2 = rs.getInt("ganador");
                    break;
                    case 7:
                        idd3 = rs.getInt("ganador");
                    break;
                    case 8:
                        idd4 = rs.getInt("ganador");
                    break;
                }
                cont++;
            }
            
            sql = "INSERT INTO enfrentamiento (visitante,local,semana,liga) VALUES( "+id1+", "+id3+", 15, "+liga+")";
            ps = cn.prepareStatement(sql); 
            ps.executeUpdate();
            sql = "INSERT INTO enfrentamiento (visitante,local,semana,liga) VALUES( "+id2+", "+id4+", 15, "+liga+")";
            ps = cn.prepareStatement(sql); 
            ps.executeUpdate();
            sql = "INSERT INTO enfrentamiento (visitante,local,semana,liga) VALUES( "+idd1+", "+idd3+", 15, "+liga+")";
            ps = cn.prepareStatement(sql); 
            ps.executeUpdate();
            sql = "INSERT INTO enfrentamiento (visitante,local,semana,liga) VALUES( "+idd2+", "+idd4+", 15, "+liga+")";
            ps = cn.prepareStatement(sql); 
            ps.executeUpdate();
                
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
        
    }
    
    public void crearS16(){
        
        Connection cn=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        int id1 = 0, id2 = 0, idd1 = 0, idd2 = 0;
        
        try{
            
            cn = Conexion.getConexion();
            
            String sql;
            
            for(int liga = 1; liga<=(new Liga().numL()); liga++){
                
            sql="SELECT ganador FROM enfrenL where semana = ?  and liga = ?";
            ps=cn.prepareStatement(sql);
            ps.setInt(1, 15);
            ps.setInt(2, liga);
            rs=ps.executeQuery();
            int cont = 1;
            while(rs.next()){
                switch (cont){
                    case 1:
                        id1 = rs.getInt("ganador");
                    break;
                    case 2:
                        id2 = rs.getInt("ganador");
                    break;
                    case 3:
                        idd1 = rs.getInt("ganador");
                    break;
                    case 4:
                        idd2 = rs.getInt("ganador");
                    break;
                }
                cont++;
            }
            
            sql = "INSERT INTO enfrentamiento (visitante,local,semana,liga) VALUES( "+id1+", "+id2+", 16, "+liga+")";
            ps = cn.prepareStatement(sql); 
            ps.executeUpdate();
            sql = "INSERT INTO enfrentamiento (visitante,local,semana,liga) VALUES( "+idd1+", "+idd2+", 16, "+liga+")";
            ps = cn.prepareStatement(sql); 
            ps.executeUpdate();
                
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
        
    }
    
    public void crearS17(){
        
        Connection cn=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        int id1 = 0, idd1 = 0;
        
        try{
            
            cn = Conexion.getConexion();
            
            String sql;
            
            for(int liga = 1; liga<=(new Liga().numL()); liga++){
                
            sql="SELECT ganador FROM enfrenL where semana = ?  and liga = ?";
            ps=cn.prepareStatement(sql);
            ps.setInt(1, 16);
            ps.setInt(2, liga);
            rs=ps.executeQuery();
            int cont = 1;
            while(rs.next()){
                switch (cont){
                    case 1:
                        id1 = rs.getInt("ganador");
                    break;
                    case 2:
                        idd1 = rs.getInt("ganador");
                    break;
                }
                cont++;
            }
            
            sql = "INSERT INTO enfrentamiento (visitante,local,semana,liga) VALUES( "+id1+", "+idd1+", 17, "+liga+")";
            ps = cn.prepareStatement(sql); 
            ps.executeUpdate();
                
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
        
    }
    
    public int checarTiempo(){
        return porcentaje;
    }
    
    public int getId_enfrentamiento() {
        return id_enfrentamiento;
    }

    public void setId_enfrentamiento(int id_enfrentamiento) {
        this.id_enfrentamiento = id_enfrentamiento;
    }

    public int getVid() {
        return vid;
    }

    public void setVid(int vid) {
        this.vid = vid;
    }

    public String getVisitante() {
        return visitante;
    }

    public void setVisitante(String visitante) {
        this.visitante = visitante;
    }

    public int getVp() {
        return vp;
    }

    public void setVp(int vp) {
        this.vp = vp;
    }

    public int getVdl() {
        return vdl;
    }

    public void setVdl(int vdl) {
        this.vdl = vdl;
    }

    public int getVds() {
        return vds;
    }

    public void setVds(int vds) {
        this.vds = vds;
    }

    public int getLds() {
        return lds;
    }

    public void setLds(int lds) {
        this.lds = lds;
    }

    public int getLdl() {
        return ldl;
    }

    public void setLdl(int ldl) {
        this.ldl = ldl;
    }

    public int getLp() {
        return lp;
    }

    public void setLp(int lp) {
        this.lp = lp;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public int getLid() {
        return lid;
    }

    public void setLid(int lid) {
        this.lid = lid;
    }

    public int getGanador() {
        return ganador;
    }

    public void setGanador(int ganador) {
        this.ganador = ganador;
    }

    
            
}
