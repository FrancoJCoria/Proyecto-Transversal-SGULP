/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistencia;


import Modelo.Inscripcion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author FRANCO
 */
public class inscripcionData {
    private Connection con =  null;
    
    public inscripcionData(){
        this.con=Conexion.buscarConexion();
    }
    
    public void guardarInscripcion(Inscripcion i){
        String sql = "INSERT INTO inscripcion(nota,idAlumno,idMateria) VALUES(?,?,?)";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, i.getNota());
            ps.setInt(2,i.getIdAlumno());
            ps.setInt(3, i.getIdMateria());
            ps.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al cargar la sentencia SQL " + ex.getMessage());
            return;
        }
        JOptionPane.showMessageDialog(null, "La inscripcion se agrego exitosamente!", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        
    }
    
    public Inscripcion buscarInscripcion(int id){
     String sql = "SELECT * FROM inscripcion WHERE idInscripto= ?";
     try{
         PreparedStatement ps = con.prepareStatement(sql);
         ps.setInt(1, id);
         ResultSet rs = ps.executeQuery();
         Inscripcion inscripcion;
         if(rs.next()){
          inscripcion=new Inscripcion();
          inscripcion.setIdInscripto(rs.getInt("idInscripto"));
          inscripcion.setNota(rs.getInt("nota"));
          inscripcion.setIdAlumno(rs.getInt("idAlumno"));
          inscripcion.setIdMateria(rs.getInt("idMateria"));
          ps.executeQuery();
          return inscripcion;
         }
     
     }catch(SQLException ex){
         JOptionPane.showMessageDialog(null, "Error al cargar la sentencia SQL " + ex.getMessage());
            
     }
    
     return null;
    }
    
    
}
