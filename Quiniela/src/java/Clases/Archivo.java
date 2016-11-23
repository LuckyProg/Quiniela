
package Clases;

import com.csvreader.CsvWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Vector;


public class Archivo {
    
    public File Danielavenami(int tipo, int semana){
        
        
        File cosa = new File("report.csv");
        cosa.delete();
        
        switch(tipo){
        
            case 1:
                try{
                    Vector <Usuario> us = new Usuario().mostrarUsuarios("1");
                    CsvWriter c = new CsvWriter(new FileWriter(cosa, true), ',');
                    c.write("ID");
                    c.write("Nombre");
                    c.write("Apodo");
                    c.write("Contraseña");
                    c.write("Correo");
                    c.write("Correo_secundario");
                    c.endRecord();

                    for(Usuario s:us){

                        c.write(String.valueOf(s.getId()));
                        c.write(s.getNombre());
                        c.write(s.getApodo());
                        c.write(s.getPass());
                        c.write(s.getCorreo());
                        c.write(s.getCorreoS());
                        c.endRecord();

                    }
                    c.close();

                }catch (IOException e) {
                    e.printStackTrace();
                }
                break;
                
            case 2:
                try{
                        Vector<Quinela> jj = new Quinela().cosadelacosa(semana);
                       CsvWriter c = new CsvWriter(new FileWriter(cosa, true), ',');
                       c.write("Nombre");
                       c.write("Equipo Seleccionado");
                       c.write("Mayor");
                       c.write("Menor");
                       c.write("No_marcador");
                       c.write("Acierto");
                       c.endRecord();

                       for(Quinela s:jj){

                           c.write(s.getNombreU());
                           c.write(s.getNombreE());
                           c.write(String.valueOf(s.getMayor()));
                           c.write(String.valueOf(s.getMenor()));
                           c.write(String.valueOf(s.getNo_marcador()));
                           c.write(s.getAcierto());
                           c.endRecord();

                       }
                       c.close();

                   }catch (IOException e) {
                       e.printStackTrace();
                   }
                break;
            case 3:
                try{
             Vector<Survival> jj = new Survival().cosadelacosax2(semana);
            CsvWriter c = new CsvWriter(new FileWriter(cosa, true), ',');
            c.write("Nombre Usuario ");
            c.write("Equipo Seleccionado");
            c.write("Acerto");
            c.write("Semana");
            c.endRecord();
            
            for(Survival s:jj){
                
                c.write(s.getNombreU());
                c.write(s.getNombreE());
                c.write(String.valueOf(s.isGana()));
                c.write(String.valueOf(s.getSemana()));
                c.endRecord();
            
            }
            c.close();
            
        }catch (IOException e) {
            e.printStackTrace();
        }
                break;
            default:break;
        
        
        }
        
        
        
        return cosa;
        
    }
    
}
