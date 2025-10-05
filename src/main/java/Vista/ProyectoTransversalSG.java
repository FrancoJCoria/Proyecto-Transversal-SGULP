/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package Vista;

import Modelo.Alumno;
import Modelo.Materia;
import Persistencia.Conexion;
import Persistencia.alumnoData;
import Persistencia.materiaData;
import java.time.LocalDate;

/**
 *
 * @author FRANCO
 */
public class ProyectoTransversalSG {

    public static void main(String[] args) {
        
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MenuPrincipal().setVisible(true);
            }
        });

        Alumno nuevoAlumno = new Alumno(-1, 45801072, "Coria", "Franco", LocalDate.of(2004, 06, 07), true);
        Conexion miConexion = new Conexion("jdbc:mariadb://localhost/gp7universidad", "root", "");
        alumnoData alumnoData = new alumnoData(miConexion);
        //alumnoData.guardarAlumno(nuevoAlumno);
        //alumnoData.actualizarAlumno(1, "dni", 45801072);
        // alumnoData.buscarAlumno(1);
        //alumnoData.DarBajaAlumno(1);
        //alumnoData.eliminarAlumno(1);
        Materia nuevaMateria = new Materia(-1, "Matematicas", 2, true);
        materiaData materiaData = new materiaData(miConexion);
        materiaData.guardarMateria(nuevaMateria);
         //materiaData.actualizarMateria(1, "nombre", "Ingles");
        //materiaData.buscarMateria(1);
        //materiaData.DarBajaMateria(1);
        //materiaData.eliminarMateria(1);
        
    }
}
