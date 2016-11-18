
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
                enfr.setVdl(rs.getInt("vdl"));
                enfr.setVds(rs.getInt("vds"));
                enfr.setLds(rs.getInt("lds"));
                enfr.setLdl(rs.getInt("ldl"));
                enfr.setLp(rs.getInt("lp"));
                enfr.setLid(rs.getInt("lid"));
                enfr.setLocal(rs.getString("local"));
                enfr.setGanador(rs.getInt("ganador"));
                enfrentamientos.add(enfr);
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
                System.out.println("--------------Semana "+s+" Liga "+j+1);
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
                System.out.println("--------------Semana "+s+" Liga "+j+1);    
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
                System.out.println("--------------Semana "+s+" Liga "+j+1);  
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
                System.out.println("--------------Semana "+s+" Liga "+j+1);    
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
                 System.out.println("--------------Semana "+s+" Liga "+j+1);   
                    //++++++++++++++SEMANA 6++++++++++
                
                s++;
                for(int i = 0; i<=30; i+=10){
                    
                    for(int k = i; k<=i+4;k++){
                    
                        sql = "INSERT INTO enfrentamiento (visitante,local,semana,liga) VALUES("+ids_Usuarios.get(k+(40*j))+","+ids_Usuarios.get(k+5+(40*j))+","+s+","+(j+1)+")";
                        ps = cn.prepareStatement(sql); 
                        ps.executeUpdate();
                    
                    }
                
                }
                System.out.println("--------------Semana "+s+" Liga "+j+1);
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
                
                
                System.out.println("--------------Semana "+s+" Liga "+j+1);
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
                System.out.println("--------------Semana "+s+" Liga "+j+1);
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
                    
                    sql = "INSERT INTO enfrentamiento (visitante,local,semana,liga) VALUES("+ids_Usuarios.get(30+(40*j))+","+ids_Usuarios.get(40*j)+","+s+","+(j+1)+")";
                    ps = cn.prepareStatement(sql); 
                    ps.executeUpdate();
                    sql = "INSERT INTO enfrentamiento (visitante,local,semana,liga) VALUES("+ids_Usuarios.get(35+(40*j))+","+ids_Usuarios.get(5+(40*j))+","+s+","+(j+1)+")";
                    ps = cn.prepareStatement(sql); 
                    ps.executeUpdate();
                    sql = "INSERT INTO enfrentamiento (visitante,local,semana,liga) VALUES("+ids_Usuarios.get(20+(40*j))+","+ids_Usuarios.get(10+(40*j))+","+s+","+(j+1)+")";
                    ps = cn.prepareStatement(sql); 
                    ps.executeUpdate();
                    sql = "INSERT INTO enfrentamiento (visitante,local,semana,liga) VALUES("+ids_Usuarios.get(25+(40*j))+","+ids_Usuarios.get(15+(40*j))+","+s+","+(j+1)+")";
                    ps = cn.prepareStatement(sql); 
                    ps.executeUpdate();
                 System.out.println("--------------Semana "+s+" Liga "+j+1);
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
                    
                    sql = "INSERT INTO enfrentamiento (visitante,local,semana,liga) VALUES("+ids_Usuarios.get(31+(40*j))+","+ids_Usuarios.get(1+(40*j))+","+s+","+(j+1)+")";
                    ps = cn.prepareStatement(sql); 
                    ps.executeUpdate();
                    sql = "INSERT INTO enfrentamiento (visitante,local,semana,liga) VALUES("+ids_Usuarios.get(36+(40*j))+","+ids_Usuarios.get(6+(40*j))+","+s+","+(j+1)+")";
                    ps = cn.prepareStatement(sql); 
                    ps.executeUpdate();
                    sql = "INSERT INTO enfrentamiento (visitante,local,semana,liga) VALUES("+ids_Usuarios.get(21+(40*j))+","+ids_Usuarios.get(11+(40*j))+","+s+","+(j+1)+")";
                    ps = cn.prepareStatement(sql); 
                    ps.executeUpdate();
                    sql = "INSERT INTO enfrentamiento (visitante,local,semana,liga) VALUES("+ids_Usuarios.get(26+(40*j))+","+ids_Usuarios.get(16+(40*j))+","+s+","+(j+1)+")";
                    ps = cn.prepareStatement(sql); 
                    ps.executeUpdate();
                System.out.println("--------------Semana "+s+" Liga "+j+1);
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
                    
                    sql = "INSERT INTO enfrentamiento (visitante,local,semana,liga) VALUES("+ids_Usuarios.get(32+(40*j))+","+ids_Usuarios.get(2+(40*j))+","+s+","+(j+1)+")";
                    ps = cn.prepareStatement(sql); 
                    ps.executeUpdate();
                    sql = "INSERT INTO enfrentamiento (visitante,local,semana,liga) VALUES("+ids_Usuarios.get(37+(40*j))+","+ids_Usuarios.get(7+(40*j))+","+s+","+(j+1)+")";
                    ps = cn.prepareStatement(sql); 
                    ps.executeUpdate();
                    sql = "INSERT INTO enfrentamiento (visitante,local,semana,liga) VALUES("+ids_Usuarios.get(22+(40*j))+","+ids_Usuarios.get(12+(40*j))+","+s+","+(j+1)+")";
                    ps = cn.prepareStatement(sql); 
                    ps.executeUpdate();
                    sql = "INSERT INTO enfrentamiento (visitante,local,semana,liga) VALUES("+ids_Usuarios.get(27+(40*j))+","+ids_Usuarios.get(17+(40*j))+","+s+","+(j+1)+")";
                    ps = cn.prepareStatement(sql); 
                    ps.executeUpdate();
                System.out.println("--------------Semana "+s+" Liga "+j+1);
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
                    
                    sql = "INSERT INTO enfrentamiento (visitante,local,semana,liga) VALUES("+ids_Usuarios.get(33+(40*j))+","+ids_Usuarios.get(3+(40*j))+","+s+","+(j+1)+")";
                    ps = cn.prepareStatement(sql); 
                    ps.executeUpdate();
                    sql = "INSERT INTO enfrentamiento (visitante,local,semana,liga) VALUES("+ids_Usuarios.get(38+(40*j))+","+ids_Usuarios.get(8+(40*j))+","+s+","+(j+1)+")";
                    ps = cn.prepareStatement(sql); 
                    ps.executeUpdate();
                    sql = "INSERT INTO enfrentamiento (visitante,local,semana,liga) VALUES("+ids_Usuarios.get(23+(40*j))+","+ids_Usuarios.get(13+(40*j))+","+s+","+(j+1)+")";
                    ps = cn.prepareStatement(sql); 
                    ps.executeUpdate();
                    sql = "INSERT INTO enfrentamiento (visitante,local,semana,liga) VALUES("+ids_Usuarios.get(28+(40*j))+","+ids_Usuarios.get(18+(40*j))+","+s+","+(j+1)+")";
                    ps = cn.prepareStatement(sql); 
                    ps.executeUpdate();
                System.out.println("--------------Semana "+s+" Liga "+j+1);
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
                    
                    sql = "INSERT INTO enfrentamiento (visitante,local,semana,liga) VALUES("+ids_Usuarios.get(34+(40*j))+","+ids_Usuarios.get(4+(40*j))+","+s+","+(j+1)+")";
                    ps = cn.prepareStatement(sql); 
                    ps.executeUpdate();
                    sql = "INSERT INTO enfrentamiento (visitante,local,semana,liga) VALUES("+ids_Usuarios.get(39+(40*j))+","+ids_Usuarios.get(9+(40*j))+","+s+","+(j+1)+")";
                    ps = cn.prepareStatement(sql); 
                    ps.executeUpdate();
                    sql = "INSERT INTO enfrentamiento (visitante,local,semana,liga) VALUES("+ids_Usuarios.get(24+(40*j))+","+ids_Usuarios.get(14+(40*j))+","+s+","+(j+1)+")";
                    ps = cn.prepareStatement(sql); 
                    ps.executeUpdate();
                    sql = "INSERT INTO enfrentamiento (visitante,local,semana,liga) VALUES("+ids_Usuarios.get(29+(40*j))+","+ids_Usuarios.get(19+(40*j))+","+s+","+(j+1)+")";
                    ps = cn.prepareStatement(sql); 
                    ps.executeUpdate();
                 System.out.println("--------------Semana "+s+" Liga "+j+1);   
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
    
    public int checarTiempo(){return porcentaje;}
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
