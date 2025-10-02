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
        String sql = "SELECT * FROM alumno WHERE idAlumno = ?";
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

        String sql = "UPDATE alumno SET " + columna + "=? WHERE idAlumno=?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            if (dato instanceof String) {
                ps.setString(1, (String) dato);

            } else if (dato instanceof Integer) {
                ps.setInt(1, (int) dato);

            } else if (dato instanceof LocalDate) {
                ps.setDate(1, (Date) dato);

            } else if (dato instanceof Boolean) {
                ps.setBoolean(1, (boolean) dato);

            } else {
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
        }
    }

    public void eliminarAlumno(int id) {
        String sql = "DELETE FROM alumno WHERE idAlumno=?";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al cargar la sentencia SQL " + ex.getMessage());
        }

    }
}
