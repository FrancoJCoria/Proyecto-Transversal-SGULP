/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistencia;


import Modelo.Materia;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author FRANCO
 */
public class materiaData {
    
    private Connection con = null;

    public materiaData(Conexion conexion) {
        this.con=conexion.buscarConexion();
    }
    
     public void guardarMateria(Materia m) {
        String sql = "INSERT INTO materia(nombre,año,estado) VALUES(?,?,?)";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            
            ps.setString(1, m.getNombre());
            ps.setInt(2,m.getAño());
            ps.setBoolean(3, m.isEstado());
            ps.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al cargar la sentencia SQL " + ex.getMessage());
        }
    }

    public Materia buscarMateria(int id) {
        String sql = "SELECT * FROM materia WHERE idMateria = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            Materia materia = null;
            if (rs.next()) {
                materia.setIdMateria(rs.getInt("idMateria"));
                materia.setNombre(rs.getString("nombre"));
                materia.setAño(rs.getInt("año"));
                materia.setEstado(rs.getBoolean("estado"));
               return materia;
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al cargar la sentencia SQL " + ex.getMessage());
          
        }
        return null;
    }

    public void actualizarMateria(int id, String columna, Object dato) {
        if (!columna.equals("nombre") && !columna.equals("año") && !columna.equals("estado")) {
            throw new IllegalArgumentException("Columna no permitida");
        }

        String sql = "UPDATE materia SET " + columna + "=? WHERE idMateria=?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            if (dato instanceof String) {
                ps.setString(1, (String) dato);

            } else if (dato instanceof Integer) {
                ps.setInt(1, (int) dato);

            
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

    public void DarBajaMateria(int id) {
        String sql = "UPDATE materia SET estado=? WHERE idMateria=?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setBoolean(1, false);
            ps.setInt(2,id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al cargar la sentencia SQL " + ex.getMessage());
        }
    }

    public void eliminarMateria(int id) {
        String sql = "DELETE FROM materia WHERE idMateria=?";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al cargar la sentencia SQL " + ex.getMessage());
        }

    }
}

    
    
    

