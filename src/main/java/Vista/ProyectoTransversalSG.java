/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package Vista;

import Modelo.Alumno;
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
                alumnoData alumnoData = new alumnoData();
                new MenuPrincipal(alumnoData).setVisible(true);
            }
        });

        Alumno nuevoAlumno = new Alumno(-1, 45801072, "Coria", "Franco", LocalDate.of(2004, 06, 07), true);
        alumnoData alumnoData =new alumnoData();
        alumnoData.guardarAlumno(nuevoAlumno);
      
        
    }
}
