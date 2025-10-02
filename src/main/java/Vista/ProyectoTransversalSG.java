/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package Vista;

import Modelo.Alumno;
import Persistencia.Conexion;
import Persistencia.alumnoData;
import java.time.LocalDate;


/**
 *
 * @author FRANCO
 */
public class ProyectoTransversalSG {

    public static void main(String[] args) {
   
         Alumno nuevoAlumno=new Alumno(-1,45801072,"Coria","Franco",LocalDate.of(2004,06,07),true);
         Conexion miConexion =new Conexion("jdbc:mariadb://localhost/gp7universidad","root","");
         alumnoData alumnoData = new alumnoData(miConexion);
         //alumnoData.guardarAlumno(nuevoAlumno);
         //alumnoData.actualizarAlumno(1, "dni", 45801072);
          //alumnoData.buscarAlumno(1);
          //alumnoData.DarBajaAlumno(1);
          //alumnoData.eliminarAlumno(1);
    }
}
