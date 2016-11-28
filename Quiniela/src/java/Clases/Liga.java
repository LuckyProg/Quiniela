
package Clases;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class Liga {
    
    private int id_liga;
    private int no_liga;
    private String conferencia;
    private String region;
    private int id_usuario;
    private String nombreU;
    private int jg;
    private int jp;
    private int je;
    private String divi;
    private String afc;
    private String nfc;
    private int pf;
    private int pc;
    
    
    public Liga(){
   
    }
    
    public Vector<Liga> mostrarLigas(int liga){
        
        Vector<Liga> ligas=new Vector<Liga>();
        
        Connection cn=null;
        PreparedStatement pr=null;
        ResultSet rs=null;
        
        
        try{
            cn=Conexion.getConexion();
            String sql="select conferencia, region, usuario.nombre as nombreU, jg,jp,je,divi,afc,nfc,pf,pc  from liga, usuario where liga.id_usuario=usuario.id_usuario and no_liga = ?";
            pr=cn.prepareStatement(sql);
            pr.setInt(1, liga);
            rs=pr.executeQuery();
            while(rs.next()){
                Liga li=new Liga();
                li.setConferencia(rs.getString("conferencia"));
                li.setRegion(rs.getString("region"));
                li.setNombreU(rs.getString("nombreU"));
                li.setJg(rs.getInt("jg"));
                li.setJp(rs.getInt("jp"));
                li.setJe(rs.getInt("je"));
                li.setDivi(rs.getString("divi"));
                li.setAfc(rs.getString("afc"));
                li.setNfc(rs.getString("nfc"));
                li.setPf(rs.getInt("pf"));
                li.setPc(rs.getInt("pc"));
              
                ligas.add(li);
            }
        }catch(SQLException ex){
            ex.printStackTrace();
            ligas=null;
        }finally{
            try{
                rs.close();
                pr.close();
                cn.close();
            }catch(SQLException ex){
                ex.printStackTrace();
            }
        }
        
        
        return ligas;
    }
    
    public Vector<Liga> mostrarTop(int liga,String conferencia, int top){
        
        Vector<Liga> ligas=new Vector<Liga>();
        
        Connection cn=null;
        PreparedStatement pr=null;
        ResultSet rs=null;
        
        
        try{
            cn=Conexion.getConexion();
            String sql="select usuario.nombre as nombreU, usuario.id_usuario, l.region, l.jg, l.jp, l.je, l.divi, l.afc, l.nfc from liga as l, usuario"
                    + " where l.id_usuario=usuario.id_usuario and l.no_liga = ? and l.conferencia = ? order by l.jg desc, l.jp asc LIMIT "+top;
            pr=cn.prepareStatement(sql);
            pr.setInt(1, liga);
            pr.setString(2, conferencia);
            rs=pr.executeQuery();
            while(rs.next()){
                Liga li=new Liga();
                li.setId_usuario(rs.getInt("id_usuario"));
                li.setNombreU(rs.getString("nombreU"));
                li.setRegion(rs.getString("region")); 
                li.setJg(rs.getInt("jg"));
                li.setJp(rs.getInt("jp"));
                li.setJe(rs.getInt("je"));
                li.setDivi(rs.getString("divi"));
                li.setAfc(rs.getString("afc"));
                li.setNfc(rs.getString("nfc"));
                ligas.add(li);
            }
        }catch(SQLException ex){
            ex.printStackTrace();
            ligas=null;
        }finally{
            try{
                rs.close();
                pr.close();
                cn.close();
            }catch(SQLException ex){
                ex.printStackTrace();
            }
        }
        
        
        return ligas;
    }
    
    
    
    public int numL(){
        
       
        Connection cn=null;
        PreparedStatement pr=null;
        ResultSet rs=null;
        int num = 0;
        
        
        try{
            cn=Conexion.getConexion();
            String sql="SELECT count(DISTINCT no_liga) as numL FROM liga";
            pr=cn.prepareStatement(sql);
            rs=pr.executeQuery();
            while(rs.next()){
                num = rs.getInt("numL");
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
        
        
        return num;
    }
    
    public boolean generarLigas(){
    boolean daniela = true;
        Connection cn=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        Vector<Usuario> jugadores = new Usuario().mostrarJugadores();
        int cj = jugadores.size();
        int cl = cj/40;
        int cjv = 40-(cj%40);
        if(cj%40!=0){
        cl++;
        }
        int c = 0;
        
        
        try{
            
            cn = Conexion.getConexion();
            String sql = "INSERT INTO liga (no_liga,conferencia,region,id_usuario) VALUES(?,?,?,?)";
            ps = cn.prepareStatement(sql); 
            for(int kk = 1; kk<=5; kk++){
            for(int i = 1; i<=4;i++){
            
                for(int j =1;j<=2;j++){
                
                    for(int k = 1; k<=cl; k++){
                        
                        if(c<cj){
                            ps.setInt(1, k);
                        if(j==1){
                        ps.setString(2, "AMERICANA"); 
                        }else{
                        ps.setString(2, "NACIONAL");
                        }
                        switch(i){
                        
                            case 1:ps.setString(3, "N");break;
                            case 2:ps.setString(3, "E");break;
                            case 3:ps.setString(3, "O");break;
                            case 4:ps.setString(3, "S");break;
                        
                        }     
                        ps.setInt(4, jugadores.get(c).getId());
                        ps.executeUpdate();
                        c++;
                            
                        }else{
                            
                            ps.setInt(1, k);
                        if(j==1){
                        ps.setString(2, "AMERICANA"); 
                        }else{
                        ps.setString(2, "NACIONAL");
                        }
                        switch(i){
                        
                            case 1:ps.setString(3, "N");break;
                            case 2:ps.setString(3, "E");break;
                            case 3:ps.setString(3, "O");break;
                            case 4:ps.setString(3, "S");break;
                        
                        }    
                            
                        ps.setInt(4, 1000);
                        ps.executeUpdate();
                        c++;
                        
                        }
                        
                    }
                
                }
                
            }
            }
            
            
            
            
       

        }catch(Exception e){
            e.printStackTrace();
            daniela = false;
        }finally{
            try{
                ps.close();
            }catch(SQLException ex){
                daniela = false;
                ex.printStackTrace();
            }
        }
    
    return daniela;
    }
    
    public boolean borrarLigas() {
        
        boolean daniela = true;
        Connection cn=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        
        try{
            
            cn = Conexion.getConexion();
            String sql = "truncate table liga";
            ps = cn.prepareStatement(sql);     
            ps.executeUpdate();
            sql = "truncate table enfrentamiento";
            ps = cn.prepareStatement(sql);     
            ps.executeUpdate();


        }catch(Exception e){
            e.printStackTrace();
            daniela = false;
        }finally{
            try{
                ps.close();
            }catch(SQLException ex){
                daniela = false;
                ex.printStackTrace();
            }
        }
    
    return daniela;
        
    }
    
    
    

    public int getId_liga() {
        return id_liga;
    }

    public void setId_liga(int id_liga) {
        this.id_liga = id_liga;
    }

    public int getNo_liga() {
        return no_liga;
    }

    public void setNo_liga(int no_liga) {
        this.no_liga = no_liga;
    }

    public String getConferencia() {
        return conferencia;
    }

    public void setConferencia(String conferencia) {
        this.conferencia = conferencia;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public int getJg() {
        return jg;
    }

    public void setJg(int jg) {
        this.jg = jg;
    }

    public int getJp() {
        return jp;
    }

    public void setJp(int jp) {
        this.jp = jp;
    }

    public int getJe() {
        return je;
    }

    public void setJe(int je) {
        this.je = je;
    }

    public String getDivi() {
        return divi;
    }

    public void setDivi(String divi) {
        this.divi = divi;
    }

    public String getAfc() {
        return afc;
    }

    public void setAfc(String afc) {
        this.afc = afc;
    }

    public String getNfc() {
        return nfc;
    }

    public void setNfc(String nfc) {
        this.nfc = nfc;
    }

    public int getPf() {
        return pf;
    }

    public void setPf(int pf) {
        this.pf = pf;
    }

    public int getPc() {
        return pc;
    }

    public void setPc(int pc) {
        this.pc = pc;
    }

    public String getNombreU() {
        return nombreU;
    }

    public void setNombreU(String nombreU) {
        this.nombreU = nombreU;
    }

    
    
    
    
}
