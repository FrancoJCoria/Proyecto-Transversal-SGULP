/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistencia;

import Modelo.Alumno;
import Modelo.Materia;
import Modelo.Inscripcion;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author feerl
 */
public class inscripcionData {
    
    
    private Connection con = null;
    private alumnoData ad = new alumnoData();
    private materiaData md = new materiaData();
    
    public inscripcionData(){
        con = Conexion.buscarConexion();
    }
    
}
