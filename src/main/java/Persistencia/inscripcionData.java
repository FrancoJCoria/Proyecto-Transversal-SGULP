/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistencia;

import Modelo.Inscripcion;
import Modelo.Materia;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author FRANCO
 */
public class inscripcionData {

    private Connection con = null;
    private final materiaData materiaData;

    public inscripcionData(materiaData materiaData) {
        this.con = Conexion.buscarConexion();
        this.materiaData = materiaData;
    }

    public void guardarInscripcion(Inscripcion i) {
        String sql = "INSERT INTO inscripcion(nota,idAlumno,idMateria) VALUES(?,?,?)";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, i.getNota());
            ps.setInt(2, i.getIdAlumno());
            ps.setInt(3, i.getIdMateria());
            ps.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al cargar la sentencia SQL " + ex.getMessage());
            return;
        }
        JOptionPane.showMessageDialog(null, "La inscripcion se agrego exitosamente!", "Éxito", JOptionPane.INFORMATION_MESSAGE);

    }

    public Inscripcion buscarInscripcion(int id) {
        String sql = "SELECT * FROM inscripcion WHERE idInscripto= ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            Inscripcion inscripcion;
            if (rs.next()) {
                inscripcion = new Inscripcion();
                inscripcion.setIdInscripto(rs.getInt("idInscripto"));
                inscripcion.setNota(rs.getInt("nota"));
                inscripcion.setIdAlumno(rs.getInt("idAlumno"));
                inscripcion.setIdMateria(rs.getInt("idMateria"));
                ps.executeQuery();
                return inscripcion;
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al cargar la sentencia SQL " + ex.getMessage());

        }

        return null;
    }

    public void borrarInscripcion(int idAlumno, int idMateria) {
        String sql = "DELETE FROM inscripcion WHERE idAlumno = ? AND idMateria = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idAlumno);
            ps.setInt(2, idMateria);
            int exito = ps.executeUpdate();
            if (exito > 0) {
                JOptionPane.showMessageDialog(null, "Inscripción eliminada correctamente.");
            } else {
                JOptionPane.showMessageDialog(null, "No se encontró la inscripción a eliminar.");
            }
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al eliminar inscripción: " + ex.getMessage());
        }
    }

    public ArrayList<Materia> listarMateriasCursadas(int idAlumno) {

        ArrayList<Materia> materias = new ArrayList<>();
        String sql = "SELECT m.idMateria, m.nombre FROM inscripcion i, materia m "
                + "WHERE i.idMateria = m.idMateria"
                + " AND i.idAlumno = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idAlumno);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Materia materia = new Materia();
                materia.setIdMateria(rs.getInt("IdMateria"));
                materia.setNombre(rs.getString("nombre"));
                materias.add(materia);
            }
            return materias;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al cargar la sentencia SQL " + ex.getMessage());
            return null;
        }

    }

    public ArrayList<Materia> listarMateriasNoCursadas(int idAlumno) {

       return null;
    }

}
