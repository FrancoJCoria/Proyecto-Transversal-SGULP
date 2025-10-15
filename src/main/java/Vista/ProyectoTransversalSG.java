/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package Vista;

import Persistencia.alumnoData;
import Persistencia.inscripcionData;
import Persistencia.materiaData;

/**
 *
 * @author FRANCO
 */
public class ProyectoTransversalSG {

    public static void main(String[] args) {

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                materiaData materiaData = new materiaData();
                alumnoData alumnoData = new alumnoData();
                inscripcionData inscripcionData = new inscripcionData(materiaData);
                new MenuPrincipal(alumnoData, materiaData, inscripcionData).setVisible(true);
            }
        });

        //Alumno nuevoAlumno = new Alumno(-1, 45801072, "Coria", "Franco", LocalDate.of(2004, 06, 07), true);
        //alumnoData alumnoData =new alumnoData();
        //alumnoData.guardarAlumno(nuevoAlumno);
    }
}
