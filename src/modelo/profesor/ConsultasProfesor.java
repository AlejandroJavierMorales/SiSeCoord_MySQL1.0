
package modelo.profesor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import modelo.Conexion;
import modelo.profesor.ConsultasProfesor;
import modelo.profesor.ModProfesor;
import vista.frmProfesor;


public class ConsultasProfesor extends Conexion{
  
    public boolean registrarProfesor(ModProfesor profesor) {

            PreparedStatement ps = null;
            Connection con = getConexion();

            try {
                String sql = "INSERT INTO profesor (legajo,nombre,materia,email)VALUES (?,?,?,?)";
                ps = con.prepareStatement(sql);

                ps.setInt(1, profesor.legajo);
                ps.setString(2, profesor.nombre);
                ps.setString(3, profesor.materia);
                ps.setString(4, profesor.email);
                //ps.setInt(5, Padre.padre);
                //JOptionPane.showMessageDialog(null,sql);
                ps.execute();
                return true;

            } catch (SQLException ex) {
                Logger.getLogger(ConsultasProfesor.class.getName()).log(Level.SEVERE, null, ex);
                //JOptionPane.showMessageDialog(null, "Error! el Registro No se ha generado...");
                return false;
            } finally {
                try {
                    con.close();
                } catch (SQLException e) {
                    System.err.println(e);
                    //JOptionPane.showMessageDialog(null, "Error! No se ha podido Cerrar la Conexion a la Base de Datos");
                }
            }

        }

    //Metodo para Modificar un Alumno
    public boolean modificarProfesor(ModProfesor profesor) {

        PreparedStatement ps = null;
        Connection con = getConexion();

        try {
            String sql = "UPDATE profesor SET legajo=?,nombre=?,materia=?,email=? WHERE id=?";
            ps = con.prepareStatement(sql);

            ps.setInt(1, profesor.legajo);
            ps.setString(2, profesor.nombre);
            ps.setString(3, profesor.materia);
            ps.setString(4, profesor.email);
            ps.setInt(5, profesor.id);

            ps.execute();
            return true;

        } catch (SQLException ex) {
            Logger.getLogger(ConsultasProfesor.class.getName()).log(Level.SEVERE, null, ex);
            //JOptionPane.showMessageDialog(null, "Error! el Registro No se ha Modificado...");
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.err.println(e);
                //JOptionPane.showMessageDialog(null, "Error! No se ha podido Cerrar la Conexion a la Base de Datos");
            }
        }

    }

    //Metodo para Eliminar un Alumno
    public boolean eliminarProfesor(ModProfesor profesor) {

        PreparedStatement ps = null;
        Connection con = getConexion();
        //JOptionPane.showMessageDialog(null, padre.idPadre);
        try {
            String sql = "DELETE FROM profesor WHERE id=" + profesor.id;
            ps = con.prepareStatement(sql);

            //ps.setInt(1, alumno.dni);
            ps.execute();
            return true;

        } catch (SQLException ex) {
            Logger.getLogger(ConsultasProfesor.class.getName()).log(Level.SEVERE, null, ex);
            //JOptionPane.showMessageDialog(null, "Error! el Registro No se ha Eliminado...");
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.err.println(e);
                //JOptionPane.showMessageDialog(null, "Error! No se ha podido Cerrar la Conexion a la Base de Datos");
            }
        }

    }

    //Metodo para Buscar un Profesor
    public boolean buscarProfesor(ModProfesor profesor) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = getConexion();
        try {
            String sql = "SELECT * FROM profesor WHERE nombre=?";
            ps = con.prepareStatement(sql);

            ps.setString(1, profesor.nombre);

            rs = ps.executeQuery();

            if (rs.next()) {
                profesor.setLegajo(Integer.parseInt(rs.getString("legajo")));
                profesor.setNombre(rs.getString("nombre"));
                profesor.setMateria(rs.getString("materia"));
                profesor.setEmail(rs.getString("email"));
                profesor.setId(Integer.parseInt(rs.getString("id")));
                return true;
            } else {
                return false;
            }

        } catch (SQLException ex) {
            Logger.getLogger(ConsultasProfesor.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
        }

    }

    //Metodo para Cargar el ComboAlumnos con todos los Nombre exustentes en la tabla Alumnos
    public void cargaComboProfesor(ModProfesor ModProf, frmProfesor frm) {

        ModProfesor profesor = new ModProfesor();
        profesor = ModProf;
        frmProfesor frmProf = new frmProfesor();
        frmProf = frm;

        PreparedStatement ps = null;
        ResultSet rsProfesor = null;

        //Conexion conec=new Conexion();
        Connection con = getConexion();

        try {
            String sql = "SELECT nombre,id FROM profesor ORDER BY nombre ASC";
            ps = con.prepareStatement(sql);

            rsProfesor = ps.executeQuery(sql);
        
            frmProf.jcbxProf.removeAllItems();
          
            //frmProf.jcbxProf.addItem("");
            
            while (rsProfesor.next()) {
                //JOptionPane.showMessageDialog(null,rsPadres.getString("nombre"));
                frmProf.jcbxProf.addItem(rsProfesor.getString("nombre"));
                
            }
                
        } catch (SQLException ex) {
            System.err.println(ex);
            Logger.getLogger(ConsultasProfesor.class.getName()).log(Level.SEVERE, null, ex);
            
        }
        
            
    }

    //Seleccionado el Padres desde el Combo, busca el ID del padre en su table para asociarlo a Alumno  
    public int traeIdProfesor(String nombreProf) {
        int id = 0;
        String nombre = nombreProf;

        PreparedStatement ps = null;
        ResultSet rsProfesor = null;
        //Conexion conec=new Conexion();
        Connection con = getConexion();

        try {
            String sql = "SELECT nombre,id FROM profesor WHERE nombre='" + nombre + "'";
            ps = con.prepareStatement(sql);
            rsProfesor = ps.executeQuery();
            if (rsProfesor.next()) {
                //JOptionPane.showMessageDialog(null,rsPadres.getString("nombre"));
                id = rsProfesor.getInt("id");
                return id;

            }
        } catch (SQLException ex) {
            System.err.println(ex);
            Logger.getLogger(ConsultasProfesor.class.getName()).log(Level.SEVERE, null, ex);
            //JOptionPane.showMessageDialog(null,"Error al Conectar");
            return id;
        }
        return id;

    }
    
}
