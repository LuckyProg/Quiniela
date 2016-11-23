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
 * @author Alumno
 */
public class Usuario {
    
    private int id;
    private String nombre;
    private String correo;
    private String apodo;
    private String pass;
    private double monto;
    private int rol;
    
    
    
    public Usuario (){
    
    }
    
    public Usuario registrarUsuario(String nombre, String pass, double monto, int rol){
        Usuario usu = null;
        Connection cn=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        
        try{
            
            usu= new Usuario();
            cn = Conexion.getConexion();
            String sql = "INSERT INTO usuario (nombre,apodo,pass,correo,monto,rol) VALUES(?,?,?,?,?,?)";
            ps = cn.prepareStatement(sql); 
            ps.setString(1, nombre);
            
            ps.setString(2, nombre);
            
            ps.setString(3, pass);
            
            ps.setString(4, "-");
            ps.setDouble(5, monto);
            ps.setInt(6, rol);
             
            ps.executeUpdate();
       

        }catch(Exception e){
            e.printStackTrace();
            usu = null;
        }finally{
            try{
                //rs.close();
                ps.close();
            }catch(SQLException ex){
                usu = null;
                ex.printStackTrace();
            }
        }
        
        
        
    return usu;
    
    }
    
    public String generarPass(){
    
        String passt = "";
        String num = "0123456789";
        String min = "abcdefghijklmnopqrstuvwxyz";
        String may = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        int temp = 0;
        String tem = "";
        for(int i=0; i<20; i++){
        //(int)(Math.random()*(HASTA-DESDE+1)+DESDE); 
        
        temp =  (int)(Math.random()*(3-1+1)+1);
        switch(temp){
            case 1: 
                tem=String.valueOf(num.charAt((int)(Math.random()*(10))));
            break;
            case 2:
                tem=String.valueOf(min.charAt((int)(Math.random()*(26))));
            break;
            case 3:
                tem=String.valueOf(may.charAt((int)(Math.random()*(26))));
            break;
            default:
                System.out.println("ERROR NUCLIAR");
                break;
        }
         passt = passt+""+tem;;
        }
        return passt;
        
    }
    
    public Usuario borrarUsuario(int id){
        
        if(id!=1){
        
            
        
        
        Usuario usu = null;
        Connection cn=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        
        try{
            
            usu= new Usuario();
            cn = Conexion.getConexion();
            String sql = "DELETE FROM  usuario WHERE id_usuario=?";
            ps = cn.prepareStatement(sql);
            ps.setInt(1, id);
            
             
            ps.executeUpdate();

        }catch(Exception e){
            e.printStackTrace();
            usu = null;
        }finally{
            try{
                //rs.close();
                ps.close();
            }catch(SQLException ex){
                usu = null;
                ex.printStackTrace();
            }
        }
    
        return usu;
        
        }else{return null;}
        
    }
    
    public Usuario editarUsuario(String nombre, String apodo, String pass, String correo, double monto, int rol,int id){
        
        Usuario usu = null;
        Connection cn=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        
        try{
            
            usu= new Usuario();
            cn = Conexion.getConexion();
            String sql = "UPDATE usuario SET nombre=?, apodo=?, pass=?, correo=?, monto=?, rol=? WHERE id_usuario=?";
            ps = cn.prepareStatement(sql);
            ps.setString(1, nombre);
            ps.setString(2, apodo);
            ps.setString(3, pass);
            ps.setString(4, correo);
            ps.setDouble(5, monto);
            ps.setInt(6, rol);
            ps.setInt(7, id);
             
            ps.executeUpdate();

        }catch(Exception e){
            e.printStackTrace();
            usu = null;
        }finally{
            try{
                //rs.close();
                ps.close();
            }catch(SQLException ex){
                usu = null;
                ex.printStackTrace();
            }
        }
        
        return usu;
    
    }
    
    public Vector<Usuario> mostrarUsuarios(String orden){
        
        Vector<Usuario> usuarios=new Vector<Usuario>();
        
        Connection cn=null;
        PreparedStatement pr=null;
        ResultSet rs=null;
        
        
        try{
            cn=Conexion.getConexion();
            int or = Integer.parseInt(orden);
            String sql="";
            switch (or){
            
                case 0:
                    sql="SELECT * FROM usuario";
                break;
                case 1:
                    sql="SELECT * FROM usuario ORDER BY nombre";
                break;
                case 2:
                    sql="SELECT * FROM usuario ORDER BY apodo";
                break;
                case 3:
                    sql="SELECT * FROM usuario ORDER BY monto";
                break;
                case 4:
                    sql="SELECT * FROM usuario ORDER BY rol";
                break;
                case 5:
                    sql="SELECT * FROM usuario ORDER BY survival DESC";
                break;
            
            }
            
            pr=cn.prepareStatement(sql);
            rs=pr.executeQuery();
            while(rs.next()){
                Usuario usu=new Usuario();
                usu.setId(rs.getInt("id_usuario"));
                usu.setNombre(rs.getString("nombre"));
                usu.setApodo(rs.getString("apodo"));
                usu.setPass(rs.getString("pass"));
                usu.setCorreo(rs.getString("correo"));
                usu.setMonto(rs.getDouble("monto"));
                usu.setRol(rs.getInt("rol"));
                
                
                
                usuarios.add(usu);
            }
        }catch(SQLException ex){
            ex.printStackTrace();
            usuarios=null;
        }finally{
            try{
                rs.close();
                pr.close();
                cn.close();
            }catch(SQLException ex){
                ex.printStackTrace();
            }
        }
        
        
        return usuarios;
    }
    
    public Vector<Usuario> mostrarJugadores(){
        
        Vector<Usuario> usuarios=new Vector<Usuario>();
        
        Connection cn=null;
        PreparedStatement pr=null;
        ResultSet rs=null;
        
        
        try{
            cn=Conexion.getConexion();
            String sql="select id_usuario from usuario where rol =2";
            pr=cn.prepareStatement(sql);
            rs=pr.executeQuery();
            while(rs.next()){
                Usuario usu=new Usuario();
                usu.setId(rs.getInt("id_usuario"));
                usuarios.add(usu);
            }
        }catch(SQLException ex){
            ex.printStackTrace();
            usuarios=null;
        }finally{
            try{
                rs.close();
                pr.close();
                cn.close();
            }catch(SQLException ex){
                ex.printStackTrace();
            }
        }
        
        
        return usuarios;
    }
    
    public Vector<Usuario> validarUsuario(String apodo){
        Vector<Usuario> kk =  new Vector<Usuario>();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs =  null;
        
        
        try{
            
            c=Conexion.getConexion();
            
            String sql = "select id_usuario from usuario where apodo=?";
            
           
            ps=c.prepareStatement(sql);
             ps.setString(1, apodo);
            rs=ps.executeQuery();
            while(rs.next()){
                Usuario usu=new Usuario();
                usu.setId(rs.getInt("id_usuario"));
                kk.add(usu);
            }
        }catch(SQLException ex){
            ex.printStackTrace();
            kk=null;
        }finally{
            try{
                rs.close();
                ps.close();
                c.close();
            }catch(SQLException ex){
                ex.printStackTrace();
            }
        }
        return kk;
    
    
    }

    public Usuario survi(int id_usuario, boolean vida){
        
        Usuario usu = null;
        Connection cn=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        
        try{
            
            usu= new Usuario();
            cn = Conexion.getConexion();
            String sql = "UPDATE usuario SET survival=? WHERE id_usuario=?";
            ps = cn.prepareStatement(sql);
            ps.setBoolean(1, vida);
            ps.setInt(2, id_usuario);
             
            ps.executeUpdate();

        }catch(Exception e){
            e.printStackTrace();
            usu = null;
        }finally{
            try{
                //rs.close();
                ps.close();
            }catch(SQLException ex){
                usu = null;
                ex.printStackTrace();
            }
        }
        
        return usu;
        
    }
    
    public String mostrarNombre(int id_usuario){
        
        String nombre = " ";
        
        Connection cn=null;
        PreparedStatement pr=null;
        ResultSet rs=null;
        
        
        try{
            cn=Conexion.getConexion();
            String sql="select nombre from usuario where id_usuario = ?";
            pr=cn.prepareStatement(sql);
            pr.setInt(1, id_usuario);
            rs=pr.executeQuery();
            while(rs.next()){
                nombre = rs.getString("nombre");
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
        
        
        return nombre;
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getApodo() {
        return apodo;
    }

    public void setApodo(String apodo) {
        this.apodo = apodo;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public int getRol() {
        return rol;
    }

    public void setRol(int rol) {
        this.rol = rol;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    
    
    
    
    
}
