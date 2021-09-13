
package modelo.padre;

import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import modelo.Conexion;
import vista.frmPadre;




public class ConsultasPadre extends Conexion{
    
    public boolean registrarPadre(ModPadre padre) {

            PreparedStatement ps = null;
            Connection con = getConexion();

            try {
                String sql = "INSERT INTO padre (dni,nombre,telefono,email)VALUES (?,?,?,?)";
                ps = con.prepareStatement(sql);

                ps.setInt(1, padre.dni);
                ps.setString(2, padre.nombre);
                ps.setString(3, padre.telefono);
                ps.setString(4, padre.email);
                //ps.setInt(5, Padre.padre);
                //JOptionPane.showMessageDialog(null,sql);
                ps.execute();
                return true;

            } catch (SQLException ex) {
                Logger.getLogger(ConsultasPadre.class.getName()).log(Level.SEVERE, null, ex);
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
    public boolean modificarPadre(ModPadre padre) {

        PreparedStatement ps = null;
        Connection con = getConexion();

        try {
            String sql = "UPDATE padre SET dni=?,nombre=?,telefono=?,email=? WHERE id=?";
            ps = con.prepareStatement(sql);

            ps.setInt(1, padre.dni);
            ps.setString(2, padre.nombre);
            ps.setString(3, padre.telefono);
            ps.setString(4, padre.email);
            ps.setInt(5, padre.idPadre);

            ps.execute();
            return true;

        } catch (SQLException ex) {
            Logger.getLogger(ConsultasPadre.class.getName()).log(Level.SEVERE, null, ex);
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
    public boolean eliminarPadre(ModPadre padre) {

        PreparedStatement ps = null;
        Connection con = getConexion();
        //JOptionPane.showMessageDialog(null, padre.idPadre);
        try {
            String sql = "DELETE FROM padre WHERE id=" + padre.idPadre;
            ps = con.prepareStatement(sql);

            //ps.setInt(1, alumno.dni);
            ps.execute();
            return true;

        } catch (SQLException ex) {
            Logger.getLogger(ConsultasPadre.class.getName()).log(Level.SEVERE, null, ex);
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

    //Metodo para Buscar un Alumno
    public boolean buscarPadre(ModPadre padre) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = getConexion();
        // if (alumno.nombre!=""){
        try {
            String sql = "SELECT * FROM padre WHERE nombre=?";
            ps = con.prepareStatement(sql);

            ps.setString(1, padre.nombre);

            //JOptionPane.showMessageDialog(null,padre.nombre);
            //JOptionPane.showMessageDialog(null,alumno.nombre);
            rs = ps.executeQuery();

            if (rs.next()) {
                padre.setDni(Integer.parseInt(rs.getString("dni")));
                padre.setNombre(rs.getString("nombre"));
                padre.setTelefono(rs.getString("telefono"));
                padre.setEmail(rs.getString("email"));
                padre.setIdPadre(Integer.parseInt(rs.getString("id")));
                return true;
            } else {
                return false;
            }

        } catch (SQLException ex) {
            Logger.getLogger(ConsultasPadre.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
        }

    }

    //Metodo para Cargar el Combo Padres con todos los Nombre exustentes en la tabla Padre
   /* public void cargaComboPadres(ModPadre pa, frmPadre frm) {

        ModPadre padre = new ModPadre();
        padre = pa;
        frmPadre frmPadre = new frmPadre();
        frmPadre = frm;

        PreparedStatement ps = null;
        ResultSet rsPadres = null;
        //Conexion conec=new Conexion();
        Connection con = getConexion();

        try {
            String sql = "SELECT nombre FROM padre ORDER BY nombre ASC";
            ps = con.prepareStatement(sql);
            rsPadres = ps.executeQuery(sql);

            frmPadre.jcbxPadre.addItem("");

            //System.out.println(rsPadres.);
            while (rsPadres.next()) {
                //JOptionPane.showMessageDialog(null,rsPadres.getString("nombre"));
                frmPadre.jcbxPadre.addItem(rsPadres.getString("nombre"));

            }
        } catch (SQLException ex) {
            System.err.println(ex);
            Logger.getLogger(ConsultasPadre.class.getName()).log(Level.SEVERE, null, ex);
            //JOptionPane.showMessageDialog(null,"Error al Conectar");
        }
    }
*/
    
    //Metodo para Cargar el ComboAlumnos con todos los Nombre exustentes en la tabla Alumnos
    public void cargaComboPadre(ModPadre ModPadre, frmPadre frm) {

        ModPadre padre = new ModPadre();
        padre = ModPadre;
        frmPadre frmPadre = new frmPadre();
        frmPadre = frm;

        PreparedStatement ps = null;
        ResultSet rsPadres = null;

        //Conexion conec=new Conexion();
        Connection con = getConexion();

        try {
            String sql = "SELECT nombre,dni FROM padre ORDER BY nombre ASC";
            ps = con.prepareStatement(sql);

            rsPadres = ps.executeQuery(sql);
            //frmAl.cbxNombreAlumno.removeActionListener((ActionListener) this);
            frmPadre.jcbxPadre.removeAllItems();
            //frmAl.cbxNombreAlumno.
            frmPadre.jcbxPadre.addItem("");
            
            while (rsPadres.next()) {
                //JOptionPane.showMessageDialog(null,rsPadres.getString("nombre"));
                frmPadre.jcbxPadre.addItem(rsPadres.getString("nombre"));
                
            }
                //rsAlumnos.first();
                //frmAl.btnGuardar.setEnabled(true)
                //frmAl.cbxNombreAlumno.addActionListener((ActionListener) this);
        } catch (SQLException ex) {
            System.err.println(ex);
            Logger.getLogger(ConsultasPadre.class.getName()).log(Level.SEVERE, null, ex);
            
        }
        
            
    }

    //Seleccionado el Padres desde el Combo, busca el ID del padre en su table para asociarlo a Alumno  
    public int traeIdPadre(String nombrePadre) {
        int id = 0;
        String nombre = nombrePadre;

        PreparedStatement ps = null;
        ResultSet rsPadres = null;
        //Conexion conec=new Conexion();
        Connection con = getConexion();

        try {
            String sql = "SELECT nombre,id,dni FROM padre WHERE nombre='" + nombre + "'";
            ps = con.prepareStatement(sql);
            rsPadres = ps.executeQuery();
            if (rsPadres.next()) {
                //JOptionPane.showMessageDialog(null,rsPadres.getString("nombre"));
                id = rsPadres.getInt("id");
                return id;

            }
        } catch (SQLException ex) {
            System.err.println(ex);
            Logger.getLogger(ConsultasPadre.class.getName()).log(Level.SEVERE, null, ex);
            //JOptionPane.showMessageDialog(null,"Error al Conectar");
            return id;
        }
        return id;

    }
    
}
    


