package modelo.alumno;

import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import modelo.Conexion;
import vista.frmAlumno;

//Metodo para el Alta de un Alumno
public class ConsultasAlumno extends Conexion {

        public boolean registrarAlumno(ModAlumno alumno) {

            PreparedStatement ps = null;
            Connection con = getConexion();

            try {
                String sql = "INSERT INTO alumno (dni,nombre,curso,obs,padre)VALUES (?,?,?,?,?)";
                ps = con.prepareStatement(sql);

                ps.setInt(1, alumno.dni);
                ps.setString(2, alumno.nombre);
                ps.setString(3, alumno.curso);
                ps.setString(4, alumno.obs);
                ps.setInt(5, alumno.padre);
                //JOptionPane.showMessageDialog(null,sql);
                ps.execute();
                return true;

            } catch (SQLException ex) {
                Logger.getLogger(ConsultasAlumno.class.getName()).log(Level.SEVERE, null, ex);
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
    public boolean modificarAlumno(ModAlumno alumno) {

        PreparedStatement ps = null;
        Connection con = getConexion();

        try {
            String sql = "UPDATE alumno SET nombre=?,curso=?,obs=?,padre=? WHERE dni=?";
            ps = con.prepareStatement(sql);

            ps.setString(1, alumno.nombre);
            ps.setString(2, alumno.curso);
            ps.setString(3, alumno.obs);
            ps.setInt(4, alumno.padre);
            ps.setInt(5, alumno.dni);

            ps.execute();
            return true;

        } catch (SQLException ex) {
            Logger.getLogger(ConsultasAlumno.class.getName()).log(Level.SEVERE, null, ex);
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
    public boolean eliminarAlumno(ModAlumno alumno) {

        PreparedStatement ps = null;
        Connection con = getConexion();

        try {
            String sql = "DELETE FROM alumno WHERE dni=" + alumno.dni;
            ps = con.prepareStatement(sql);

            //ps.setInt(1, alumno.dni);
            ps.execute();
            return true;

        } catch (SQLException ex) {
            Logger.getLogger(ConsultasAlumno.class.getName()).log(Level.SEVERE, null, ex);
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
    public boolean buscarAlumno(ModAlumno alumno) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = getConexion();
        // if (alumno.nombre!=""){
        try {
            String sql = "SELECT * FROM alumno WHERE nombre=?";
            ps = con.prepareStatement(sql);

            ps.setString(1, alumno.nombre);

            //JOptionPane.showMessageDialog(null,sql);
            //JOptionPane.showMessageDialog(null,alumno.nombre);
            rs = ps.executeQuery();

            if (rs.next()) {
                alumno.setDni(Integer.parseInt(rs.getString("dni")));
                alumno.setCurso(rs.getString("curso"));
                alumno.setObs(rs.getString("obs"));
                alumno.setPadre(Integer.parseInt(rs.getString("padre")));

                return true;
            } else {
                return false;
            }

        } catch (SQLException ex) {
            Logger.getLogger(ConsultasAlumno.class.getName()).log(Level.SEVERE, null, ex);
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
    public void cargaComboPadres(ModAlumno al, frmAlumno frm) {

        ModAlumno alumno = new ModAlumno();
        alumno = al;
        frmAlumno frmAl = new frmAlumno();
        frmAl = frm;

        PreparedStatement ps = null;
        ResultSet rsPadres = null;
        //Conexion conec=new Conexion();
        Connection con = getConexion();

        try {
            String sql = "SELECT nombre FROM padre ORDER BY nombre ASC";
            ps = con.prepareStatement(sql);
            rsPadres = ps.executeQuery(sql);

            frmAl.cbxPadre.addItem("");

            //System.out.println(rsPadres.);
            while (rsPadres.next()) {
                //JOptionPane.showMessageDialog(null,rsPadres.getString("nombre"));
                frmAl.cbxPadre.addItem(rsPadres.getString("nombre"));

            }
        } catch (SQLException ex) {
            System.err.println(ex);
            Logger.getLogger(ConsultasAlumno.class.getName()).log(Level.SEVERE, null, ex);
            //JOptionPane.showMessageDialog(null,"Error al Conectar");
        }
    }

    //Cargar el ComboPadres el nombre según el Alumno Seleccionado (ID)
    public void cargaComboPadresAlumnos(ModAlumno al, frmAlumno frm) {

        ModAlumno alumno = new ModAlumno();
        alumno = al;
        frmAlumno frmAl = new frmAlumno();
        frmAl = frm;

        PreparedStatement ps = null;
        ResultSet rsPadres = null;
        //Conexion conec=new Conexion();
        Connection con = getConexion();

        try {

            String sql = "SELECT nombre, id FROM padre WHERE id=" + Integer.parseInt(frmAl.txtIdPadre.getText());
            ps = con.prepareStatement(sql);
            rsPadres = ps.executeQuery();

            if (rsPadres.next()) {
                frmAl.cbxPadre.setSelectedItem(rsPadres.getString("nombre"));
            }
        } catch (SQLException ex) {
            System.err.println(ex);
            Logger.getLogger(ConsultasAlumno.class.getName()).log(Level.SEVERE, null, ex);

        }
    }

    //Metodo para Cargar el ComboAlumnos con todos los Nombre exustentes en la tabla Alumnos
    public void cargaComboAlumnos(ModAlumno ModAl, frmAlumno frm) {

        ModAlumno alumno = new ModAlumno();
        alumno = ModAl;
        frmAlumno frmAl = new frmAlumno();
        frmAl = frm;

        PreparedStatement ps = null;
        ResultSet rsAlumnos = null;

        //Conexion conec=new Conexion();
        Connection con = getConexion();

        try {
            String sql = "SELECT nombre,dni FROM alumno ORDER BY nombre ASC";
            ps = con.prepareStatement(sql);

            rsAlumnos = ps.executeQuery(sql);
            //frmAl.cbxNombreAlumno.removeActionListener((ActionListener) this);
            frmAl.cbxNombreAlumno.removeAllItems();
            //frmAl.cbxNombreAlumno.
            frmAl.cbxNombreAlumno.addItem("");
            
            while (rsAlumnos.next()) {
                //JOptionPane.showMessageDialog(null,rsPadres.getString("nombre"));
                frmAl.cbxNombreAlumno.addItem(rsAlumnos.getString("nombre"));
                
            }
                //rsAlumnos.first();
                //frmAl.btnGuardar.setEnabled(true)
                //frmAl.cbxNombreAlumno.addActionListener((ActionListener) this);
        } catch (SQLException ex) {
            System.err.println(ex);
            Logger.getLogger(ConsultasAlumno.class.getName()).log(Level.SEVERE, null, ex);
            
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
            rsPadres = ps.executeQuery(sql);
            if (rsPadres.next()) {
                //JOptionPane.showMessageDialog(null,rsPadres.getString("nombre"));
                id = rsPadres.getInt("id");
                return id;

            }
        } catch (SQLException ex) {
            System.err.println(ex);
            Logger.getLogger(ConsultasAlumno.class.getName()).log(Level.SEVERE, null, ex);
            //JOptionPane.showMessageDialog(null,"Error al Conectar");
            return id;
        }
        return id;

    }
}
