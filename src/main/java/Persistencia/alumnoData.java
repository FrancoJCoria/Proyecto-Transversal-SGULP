/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistencia;

import Modelo.Alumno;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author FRANCO
 */
public class alumnoData {

    private Connection con = null;

    public alumnoData(Conexion conexion) {
        this.con = conexion.buscarConexion();
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
        }
    }

    public void buscarAlumno(int id) {
        String sql = "SELECT * FROM alumno WHERE dni = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                System.out.println(" ID " + rs.getInt("idAlumno"));
                System.out.print(" Dni " + rs.getInt("dni"));
                System.out.print(" Apellido " + rs.getString("Apellido"));
                System.out.print(" Nombre " + rs.getString("Nombre"));
                System.out.println(" Fecha Nac " + rs.getDate("fechaNacimiento"));
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al cargar la sentencia SQL " + ex.getMessage());
        }

    }

    public void actualizarAlumno(int id, String columna, Object dato) {
        if (!columna.equals("dni") && !columna.equals("apellido") && !columna.equals("nombre") && !columna.equals("fechaNacimiento") && !columna.equals("estado")) {
            throw new IllegalArgumentException("Columna no permitida");
        }

        String sql = "UPDATE FROM alumno SET " + columna + "=? WHERE idAlumno=?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            if (dato instanceof String) {
                ps.setString(1, (String) dato);
                ps.setInt(2, id);
            } else if (dato instanceof Integer) {
                ps.setInt(1, (int) dato);
                ps.setInt(2, id);
            } else if (dato instanceof LocalDate) {
                ps.setDate(1, (Date) dato);
                ps.setInt(2, id);
            } else if (dato instanceof Boolean) {
                ps.setBoolean(1, (boolean) dato);
                ps.setInt(2, id);
            }

        } catch (SQLException ex) {
            Logger.getLogger(alumnoData.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    public void DarBajaAlumno(){
    
    }
}
