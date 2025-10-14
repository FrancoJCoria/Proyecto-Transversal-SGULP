/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistencia;

import Modelo.Alumno;
import java.awt.List;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author FRANCO
 */
public class alumnoData {

    private Connection con = null;

    public alumnoData() {
        this.con = Conexion.buscarConexion();
    }

    public void guardarAlumno(Alumno a) {
        String sql = "INSERT INTO alumno(dni,apellido,nombre,fechaNacimiento,estado) VALUES(?,?,?,?,?)";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, a.getDni());
            ps.setString(2, a.getApellido());
            ps.setString(3, a.getNombre());
            ps.setDate(4, Date.valueOf(a.getFechaNacimiento()));
            ps.setBoolean(5, a.isEstado());
            ps.executeUpdate();
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al cargar la sentencia SQL " + ex.getMessage());
            return;
        }
        JOptionPane.showMessageDialog(null, "Se agrego al alumno exitosamente!", "Éxito", JOptionPane.INFORMATION_MESSAGE);

    }

    public Alumno buscarAlumno(int id) {
        String sql = "SELECT * FROM alumno WHERE idAlumno = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            Alumno alumno = null;
            if (rs.next()) {
                alumno=new Alumno();
                alumno.setIdAlumno(rs.getInt("idAlumno"));
                alumno.setDni(rs.getInt("dni"));
                alumno.setApellido(rs.getString("Apellido"));
                alumno.setNombre(rs.getString("Nombre"));
                alumno.setFechaNacimiento(rs.getDate("fechaNacimiento").toLocalDate());
                alumno.setEstado(rs.getBoolean("estado"));
                return alumno;
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al cargar la sentencia SQL " + ex.getMessage());
        }
        return null;
    }

    public void actualizarAlumno(int id, String columna, Object dato) {
        if (!columna.equals("dni") && !columna.equals("apellido") && !columna.equals("nombre") && !columna.equals("fechaNacimiento") && !columna.equals("estado")) {
            throw new IllegalArgumentException("Columna no permitida");
        }

        String sql = "UPDATE alumno SET " + columna + "=? WHERE idAlumno=?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            if (dato instanceof String) {
                ps.setString(1, (String) dato);

            } else if (dato instanceof Integer) {
                ps.setInt(1, (int) dato);

            } else if (dato instanceof Boolean) {
                ps.setBoolean(1, (boolean) dato);

            } else if (dato instanceof java.sql.Date) {
               ps.setDate(1, (java.sql.Date) dato);
            }else{
                 throw new IllegalArgumentException(" Tipo de dato no soportado");
            }
            ps.setInt(2, id);
            ps.executeUpdate();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al cargar la sentencia SQL " + ex.getMessage());
        }

    }

    public void DarBajaAlumno(int id) {
        String sql = "UPDATE alumno SET estado=? WHERE idAlumno=?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setBoolean(1, false);
            ps.setInt(2,id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al cargar la sentencia SQL " + ex.getMessage());
            return;
        }
         JOptionPane.showMessageDialog(null, "El alumno se dio de baja exitosamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
    }
    
    
    public void DarAltaAlumno(int id) {
        String sql = "UPDATE alumno SET estado=? WHERE idAlumno=?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setBoolean(1, true);
            ps.setInt(2,id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al cargar la sentencia SQL " + ex.getMessage());
            return;
        }
         JOptionPane.showMessageDialog(null, "El alumno se dio de alta exitosamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
    }

    public void eliminarAlumno(int id) {
        String sql = "DELETE FROM alumno WHERE idAlumno=?";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al cargar la sentencia SQL " + ex.getMessage());
            return;
        }
         JOptionPane.showMessageDialog(null, "El alumno se elimino exitosamente!", "Éxito", JOptionPane.INFORMATION_MESSAGE);
    }
    
    public ArrayList<Alumno> listarAlumnos(){
          ArrayList<Alumno> alumnos = new ArrayList<>();
          String sql = "SELECT * FROM alumno";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
             Alumno alumno = new Alumno();
             alumno.setIdAlumno(rs.getInt("IdAlumno"));
             alumno.setDni(rs.getInt("dni"));
             alumno.setApellido(rs.getString("apellido"));
             alumno.setNombre(rs.getString("nombre"));
             alumno.setFechaNacimiento( rs.getDate("fechaNacimiento").toLocalDate());
             alumno.setEstado(rs.getBoolean("estado"));
             alumnos.add(alumno);
            }
            return alumnos;
        } catch (SQLException ex) {
           JOptionPane.showMessageDialog(null, "Error al cargar la sentencia SQL " + ex.getMessage());
            return null;
        }
          
    }
}
