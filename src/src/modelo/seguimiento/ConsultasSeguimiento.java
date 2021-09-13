/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.seguimiento;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import javax.swing.table.DefaultTableModel;
import modelo.Conexion;
import modelo.alumno.ConsultasAlumno;
import modelo.alumno.ModAlumno;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import vista.frmSeguimiento;
import java.util.Date;
import vista.frmRegSeguimiento;

/**
 *
 * @author Samsung
 */
public class ConsultasSeguimiento extends Conexion {

    Connection con = getConexion();
    PreparedStatement ps = null;
    ResultSet rs = null;

    private frmSeguimiento frmSeg;
    private ModSeguimiento modSeg;
    private frmRegSeguimiento frmRegSeg;

    public DefaultTableModel verDetalleSeguimiento(frmSeguimiento frmSeg, int tipoBusqueda) {
        int TipoBusqueda;
        TipoBusqueda = tipoBusqueda;
        String fechaD;
        String fechaH;
        this.frmSeg = frmSeg;
        //this.modSeg=ModSeg;

        DefaultTableModel modelo = new DefaultTableModel();
        frmSeg.jtSeguimiento.setModel(modelo);

        PreparedStatement psTabla = null;
        ResultSet rsTabla = null;
        Connection con = getConexion();
        String sql = "";
        try {
            switch (TipoBusqueda) {
                case 0:
                    sql = "SELECT seguimiento.id,DATE_FORMAT(seguimiento.fecha,'%d/%m/%Y'),seguimiento.dnialumno,alumno.nombre,seguimiento.motivo,seguimiento.detalle FROM seguimiento,alumno WHERE seguimiento.dnialumno=alumno.dni ORDER BY seguimiento.fecha";
                    break;
                case 1:
                    fechaD = frmSeg.jftxtFechaDesde.getText();
                    sql = "SELECT seguimiento.id,DATE_FORMAT(seguimiento.fecha,'%d/%m/%Y'),seguimiento.dnialumno,alumno.nombre,seguimiento.motivo,seguimiento.detalle FROM seguimiento,alumno WHERE seguimiento.dnialumno=alumno.dni AND alumno.nombre LIKE '" + frmSeg.jcbNombre.getSelectedItem() + "' AND STR_TO_DATE('" + fechaD + "', '%d/%m/%Y')=seguimiento.fecha ORDER BY seguimiento.fecha";
                    break;

                case 2:
                    fechaD = frmSeg.jftxtFechaDesde.getText();
                    fechaH = frmSeg.jftxtFechaHasta.getText();
                    sql = "SELECT seguimiento.id,DATE_FORMAT(seguimiento.fecha,'%d/%m/%Y'),seguimiento.dnialumno,alumno.nombre,seguimiento.motivo,seguimiento.detalle FROM seguimiento,alumno WHERE seguimiento.dnialumno=alumno.dni AND alumno.nombre LIKE '" + frmSeg.jcbNombre.getSelectedItem() + "' AND seguimiento.fecha BETWEEN STR_TO_DATE('" + fechaD + "', '%d/%m/%Y') AND STR_TO_DATE('" + fechaH + "', '%d/%m/%Y') ORDER BY seguimiento.fecha";
                    break;
                case 3:
                    fechaD = frmSeg.jftxtFechaDesde.getText();
                    sql = "SELECT seguimiento.id,DATE_FORMAT(seguimiento.fecha,'%d/%m/%Y'),seguimiento.dnialumno,alumno.nombre,seguimiento.motivo,seguimiento.detalle FROM seguimiento,alumno WHERE seguimiento.dnialumno=alumno.dni AND alumno.dni LIKE '" + frmSeg.jcbDni.getSelectedItem() + "' AND STR_TO_DATE('" + fechaD + "', '%d/%m/%Y')=seguimiento.fecha ORDER BY seguimiento.fecha";
                    break;

                case 4:
                    fechaD = frmSeg.jftxtFechaDesde.getText();
                    fechaH = frmSeg.jftxtFechaHasta.getText();
                    sql = "SELECT seguimiento.id,DATE_FORMAT(seguimiento.fecha,'%d/%m/%Y'),seguimiento.dnialumno,alumno.nombre,seguimiento.motivo,seguimiento.detalle FROM seguimiento,alumno WHERE seguimiento.dnialumno=alumno.dni AND alumno.dni LIKE '" + frmSeg.jcbDni.getSelectedItem() + "' AND seguimiento.fecha BETWEEN STR_TO_DATE('" + fechaD + "', '%d/%m/%Y') AND STR_TO_DATE('" + fechaH + "', '%d/%m/%Y') ORDER BY seguimiento.fecha";
                    break;

                case 5:
                    sql = "SELECT seguimiento.id,DATE_FORMAT(seguimiento.fecha,'%d/%m/%Y'),seguimiento.dnialumno,alumno.nombre,seguimiento.motivo,seguimiento.detalle FROM seguimiento,alumno WHERE seguimiento.dnialumno=alumno.dni AND alumno.nombre LIKE '" + frmSeg.jcbNombre.getSelectedItem() + "' ORDER BY seguimiento.fecha";
                    break;

                case 6:
                    sql = "SELECT seguimiento.id,DATE_FORMAT(seguimiento.fecha,'%d/%m/%Y'),seguimiento.dnialumno,alumno.nombre,seguimiento.motivo,seguimiento.detalle FROM seguimiento,alumno WHERE seguimiento.dnialumno=alumno.dni AND alumno.dni LIKE '" + frmSeg.jcbDni.getSelectedItem() + "' ORDER BY seguimiento.fecha";
                    break;

                case 7:
                    fechaD = frmSeg.jftxtFechaDesde.getText();
                    sql = "SELECT seguimiento.id,DATE_FORMAT(seguimiento.fecha,'%d/%m/%Y'),seguimiento.dnialumno,alumno.nombre,seguimiento.motivo,seguimiento.detalle FROM seguimiento,alumno WHERE seguimiento.dnialumno=alumno.dni AND STR_TO_DATE('" + fechaD + "', '%d/%m/%Y')=seguimiento.fecha ORDER BY seguimiento.fecha";
                    break;

                case 8:
                    fechaD = frmSeg.jftxtFechaDesde.getText();
                    fechaH = frmSeg.jftxtFechaHasta.getText();
                    sql = "SELECT seguimiento.id,DATE_FORMAT(seguimiento.fecha,'%d/%m/%Y'),seguimiento.dnialumno,alumno.nombre,seguimiento.motivo,seguimiento.detalle FROM seguimiento,alumno WHERE seguimiento.dnialumno=alumno.dni AND seguimiento.fecha BETWEEN STR_TO_DATE('" + fechaD + "', '%d/%m/%Y') AND STR_TO_DATE('" + fechaH + "', '%d/%m/%Y') ORDER BY seguimiento.fecha";
                    break;

                default:
                    sql = "SELECT seguimiento.id,DATE_FORMAT(seguimiento.fecha,'%d/%m/%Y'),seguimiento.dnialumno,alumno.nombre,seguimiento.motivo,seguimiento.detalle FROM seguimiento,alumno WHERE seguimiento.dnialumno=alumno.dni ORDER BY seguimiento.fecha";
                    break;
            }

            psTabla = con.prepareStatement(sql);

            //ps.setInt(1, alumno.dni);
            //ps.setString(2, alumno.nombre);
            //ps.setString(3, alumno.curso);
            //ps.setString(4, alumno.obs);
            //ps.setInt(5, alumno.padre);
            //JOptionPane.showMessageDialog(null,sql);
            rsTabla = psTabla.executeQuery();

            ResultSetMetaData rsmd = rsTabla.getMetaData();
            int cantidadColumnas = rsmd.getColumnCount();

            modelo.addColumn("Id");
            modelo.addColumn("FECHA");
            modelo.addColumn("DNI");
            modelo.addColumn("ALUMNO");
            modelo.addColumn("MOTIVO");
            modelo.addColumn("DETALLE");

            int[] anchos = {15, 45, 40, 160, 70, 480};
            for (int x = 0; x < cantidadColumnas; x++) {
                frmSeg.jtSeguimiento.getColumnModel().getColumn(x).setPreferredWidth(anchos[x]);
            }

            while (rsTabla.next()) {
                //llenar tabla************************
                Object[] filas = new Object[cantidadColumnas];
                for (int i = 0; i < cantidadColumnas; i++) {
                    filas[i] = rsTabla.getObject(i + 1);
                }
                modelo.addRow(filas);
                //*************************************
            }
            return modelo;
        } catch (SQLException ex) {
            Logger.getLogger(ConsultasAlumno.class.getName()).log(Level.SEVERE, null, ex);
            return null;
            //JOptionPane.showMessageDialog(null, "Error! el Registro No se ha generado...");
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.err.println(e);
                //JOptionPane.showMessageDialog(null, "Error! No se ha podido Cerrar la Conexion a la Base de Datos");
            }
        }

    }

    //CARGA LOS TEXTBOX DE RPOFESOR Y PADRE *****
    public void cargaDatosPadreProfesor(int idSeg, int dniAlumno, frmSeguimiento frmSeg) {
        this.frmSeg=frmSeg;
        int id;
        int dni;
        id = idSeg;
        dni = dniAlumno;

        try {
            //Para Profesor*****
            String sql = "SELECT profesor.id, profesor.nombre, profesor.materia FROM profesor, seguimiento WHERE  seguimiento.id="+ id +" AND profesor.id=seguimiento.idprofesor";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                //JOptionPane.showMessageDialog(null, +rs.getString("nombre"));
               //rs.getString("nombre").equals(null) ||
                if ( rs.getString("nombre")==null) {
                    frmSeg.jtxtProfesor.setText("");
                } else {
                    //JOptionPane.showMessageDialog(null, "r"+rs.getString("nombre")+"r");
                    frmSeg.jtxtProfesor.setText(rs.getString("nombre"));
                }
                if (rs.getString("materia")==null){
                    frmSeg.jtxtMateria.setText("");
                } else {
                    frmSeg.jtxtMateria.setText(rs.getString("materia"));
                }
            } else {
                frmSeg.jtxtProfesor.setText("");
                frmSeg.jtxtMateria.setText("");
            }
            //Para Padre*****

            sql = "SELECT padre.nombre, padre.telefono, padre.email FROM padre,alumno WHERE alumno.dni=" + dni + " AND alumno.padre=padre.id";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                if (rs.getString("nombre")==null) {
                    frmSeg.jtxtPadre.setText("");
                } else {
                    frmSeg.jtxtPadre.setText(rs.getString("nombre"));
                }
                if (rs.getString("telefono")==null) {
                    frmSeg.jtxtTelefono.setText("");
                } else {
                    frmSeg.jtxtTelefono.setText(rs.getString("telefono"));
                }
                if (rs.getString("email")==null) {
                    frmSeg.jtxtEmail.setText("");
                } else {
                    frmSeg.jtxtEmail.setText(rs.getString("email"));
                }
            } else {
                frmSeg.jtxtPadre.setText("");
                frmSeg.jtxtTelefono.setText("");
                frmSeg.jtxtEmail.setText("");
            }

        } catch (SQLException evt) {
            System.out.println(evt);
        }
    }

    public void cargaCombosEnFrmSeguiiento(ModSeguimiento ModSeg, frmSeguimiento FrmSeg) {

        ModSeguimiento modSeg = new ModSeguimiento();
        modSeg = ModSeg;
        frmSeguimiento frmSeg = new frmSeguimiento();
        frmSeg = FrmSeg;

        PreparedStatement ps = null;
        ResultSet rsAlumnos = null;
        //Conexion conec=new Conexion();
        Connection con = getConexion();

        try {
            //Para Combo Nombres *******
            String sql = "SELECT nombre FROM alumno ORDER BY nombre ASC";
            ps = con.prepareStatement(sql);
            rsAlumnos = ps.executeQuery();

            frmSeg.jcbNombre.addItem("");

            //System.out.println(rsPadres.);
            while (rsAlumnos.next()) {
                //JOptionPane.showMessageDialog(null,rsPadres.getString("nombre"));
                frmSeg.jcbNombre.addItem(rsAlumnos.getString("nombre"));
            }
            //****para combo DNI
            sql = "SELECT dni FROM alumno ORDER BY dni ASC";
            ps = con.prepareStatement(sql);
            rsAlumnos = ps.executeQuery();

            frmSeg.jcbDni.addItem("");

            //System.out.println(rsPadres.);
            while (rsAlumnos.next()) {
                //JOptionPane.showMessageDialog(null,rsPadres.getString("nombre"));
                frmSeg.jcbDni.addItem(rsAlumnos.getString("dni"));
            }

        } catch (SQLException ex) {
            System.err.println(ex);
            Logger.getLogger(ConsultasAlumno.class.getName()).log(Level.SEVERE, null, ex);
            //JOptionPane.showMessageDialog(null,"Error al Conectar");
        }
    }
//******************REPORTES*************************

    public void ReporteSegGral(frmSeguimiento FrmSeg, int tipoDeReporte) {
        frmSeg = new frmSeguimiento();
        frmSeg = FrmSeg;
        int tipoReporte;
        tipoReporte = tipoDeReporte;
        //JOptionPane.showMessageDialog(null, tipoReporte);

        switch (tipoReporte) {
            case 0://Busca TODOS LOS REGISTROS
                try {
                    //Conexion con = new Conexion();
                    Connection conn = getConexion();

                    JasperReport reporte = null;

                    String path = "src\\reportes\\seguimiento\\rptSeg0.jasper";

                    //Map parametro=new HashMap();
                    //parametro.put("parameter1", frmSeg.jcbNombre.getSelectedItem());
                    //parametro.put("parameter2", "Fam");
                    //parametro.put("parameter3", "2021-08-19");
                    //parametro.put("parameter4", "2021-08-20");
                    //parametro.put("det", "Cons");
                    reporte = (JasperReport) JRLoader.loadObjectFromFile(path);

                    JasperPrint jPrint = JasperFillManager.fillReport(reporte, null, conn);

                    JasperViewer view = new JasperViewer(jPrint, false);
                    view.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                    view.setVisible(true);

                    } catch (JRException ex) {
                    Logger.getLogger(frmSeg.getName()).log(Level.SEVERE, null, ex);
            }

                break;
            case 1://Busca por nombre de alumno y Fecha
                try {
                    //Conexion con = new Conexion();
                    Connection conn = getConexion();

                    JasperReport reporte = null;

                    String path = "src\\reportes\\seguimiento\\rptSeg1y2.jasper";

                    Map parametro=new HashMap();
                    parametro.put("parameter1", frmSeg.jcbNombre.getSelectedItem());
                    parametro.put("parameter2", frmSeg.jftxtFechaDesde.getText());
                    parametro.put("parameter3", frmSeg.jftxtFechaDesde.getText());
                    
                    reporte = (JasperReport) JRLoader.loadObjectFromFile(path);

                    JasperPrint jPrint = JasperFillManager.fillReport(reporte, parametro, conn);

                    JasperViewer view = new JasperViewer(jPrint, false);
                    view.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                    view.setVisible(true);

                    } catch (JRException ex) {
                    Logger.getLogger(frmSeg.getName()).log(Level.SEVERE, null, ex);
            }
                break;
            case 2://Busca por Nombre de alumno y Fecha Desde Hasta
                try {
                    //Conexion con = new Conexion();
                    Connection conn = getConexion();

                    JasperReport reporte = null;

                    String path = "src\\reportes\\seguimiento\\rptSeg1y2.jasper";

                    Map parametro=new HashMap();
                    parametro.put("parameter1", frmSeg.jcbNombre.getSelectedItem());
                    parametro.put("parameter2", frmSeg.jftxtFechaDesde.getText());
                    parametro.put("parameter3", frmSeg.jftxtFechaHasta.getText());
                    
                    reporte = (JasperReport) JRLoader.loadObjectFromFile(path);

                    JasperPrint jPrint = JasperFillManager.fillReport(reporte, parametro, conn);

                    JasperViewer view = new JasperViewer(jPrint, false);
                    view.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                    view.setVisible(true);

                    } catch (JRException ex) {
                    Logger.getLogger(frmSeg.getName()).log(Level.SEVERE, null, ex);
            }
                break;
            case 3://Busca por Dni y Fecha
                try {
                    //Conexion con = new Conexion();
                    Connection conn = getConexion();

                    JasperReport reporte = null;

                    String path = "src\\reportes\\seguimiento\\rptSeg3y4.jasper";

                    Map parametro=new HashMap();
                    parametro.put("parameter1", frmSeg.jcbDni.getSelectedItem());
                    parametro.put("parameter2", frmSeg.jftxtFechaDesde.getText());
                    parametro.put("parameter3", frmSeg.jftxtFechaDesde.getText());
                    
                    reporte = (JasperReport) JRLoader.loadObjectFromFile(path);

                    JasperPrint jPrint = JasperFillManager.fillReport(reporte, parametro, conn);

                    JasperViewer view = new JasperViewer(jPrint, false);
                    view.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                    view.setVisible(true);

                    } catch (JRException ex) {
                    Logger.getLogger(frmSeg.getName()).log(Level.SEVERE, null, ex);
            }
                break;
            case 4://Busca por Dni y Fecha Desde Hasta
                try {
                    //Conexion con = new Conexion();
                    Connection conn = getConexion();

                    JasperReport reporte = null;

                    String path = "src\\reportes\\seguimiento\\rptSeg3y4.jasper";

                    Map parametro=new HashMap();
                    parametro.put("parameter1", frmSeg.jcbDni.getSelectedItem());
                    parametro.put("parameter2", frmSeg.jftxtFechaDesde.getText());
                    parametro.put("parameter3", frmSeg.jftxtFechaHasta.getText());
                    
                    reporte = (JasperReport) JRLoader.loadObjectFromFile(path);

                    JasperPrint jPrint = JasperFillManager.fillReport(reporte, parametro, conn);

                    JasperViewer view = new JasperViewer(jPrint, false);
                    view.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                    view.setVisible(true);

                    } catch (JRException ex) {
                    Logger.getLogger(frmSeg.getName()).log(Level.SEVERE, null, ex);
            }

                break;
            case 5://Busca solo por Nombre del Alumno
                try {
                    //Conexion con = new Conexion();
                    Connection conn = getConexion();

                    JasperReport reporte = null;

                    String path = "src\\reportes\\seguimiento\\rptSeg5.jasper";

                    Map parametro=new HashMap();
                    parametro.put("parameter1", frmSeg.jcbNombre.getSelectedItem());
                    //parametro.put("parameter2", "Fam");
                    //parametro.put("parameter3", "2021-08-19");
                    //parametro.put("parameter4", "2021-08-20");
                    //parametro.put("det", "Cons");
                    reporte = (JasperReport) JRLoader.loadObjectFromFile(path);

                    JasperPrint jPrint = JasperFillManager.fillReport(reporte, parametro, conn);

                    JasperViewer view = new JasperViewer(jPrint, false);
                    view.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                    view.setVisible(true);

                    } catch (JRException ex) {
                    Logger.getLogger(frmSeg.getName()).log(Level.SEVERE, null, ex);
            }
            break;

            case 6://Busca solo por DNI
                try {
                    //Conexion con = new Conexion();
                    Connection conn = getConexion();

                    JasperReport reporte = null;

                    String path = "src\\reportes\\seguimiento\\rptSeg6.jasper";

                    Map parametro=new HashMap();
                    parametro.put("parameter1", frmSeg.jcbDni.getSelectedItem());
                    //parametro.put("parameter2", "Fam");
                    //parametro.put("parameter3", "2021-08-19");
                    //parametro.put("parameter4", "2021-08-20");
                    //parametro.put("det", "Cons");
                    reporte = (JasperReport) JRLoader.loadObjectFromFile(path);

                    JasperPrint jPrint = JasperFillManager.fillReport(reporte, parametro, conn);

                    JasperViewer view = new JasperViewer(jPrint, false);
                    view.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                    view.setVisible(true);

                    } catch (JRException ex) {
                    Logger.getLogger(frmSeg.getName()).log(Level.SEVERE, null, ex);
            }
                break;
            case 7://Busca por Fecha
                try {
                    //Conexion con = new Conexion();
                    Connection conn = getConexion();

                    JasperReport reporte = null;

                    String path = "src\\reportes\\seguimiento\\rptSeg7.jasper";

                    Map parametro=new HashMap();
                    parametro.put("parameter1", frmSeg.jftxtFechaDesde.getText());
                    parametro.put("parameter2", frmSeg.jftxtFechaDesde.getText());
                    
                    reporte = (JasperReport) JRLoader.loadObjectFromFile(path);

                    JasperPrint jPrint = JasperFillManager.fillReport(reporte, parametro, conn);

                    JasperViewer view = new JasperViewer(jPrint, false);
                    view.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                    view.setVisible(true);

                    } catch (JRException ex) {
                    Logger.getLogger(frmSeg.getName()).log(Level.SEVERE, null, ex);
            }
                break;
            case 8://Busca por Fecha Desde Hasta
                try {
                    //Conexion con = new Conexion();
                    Connection conn = getConexion();

                    JasperReport reporte = null;

                    String path = "src\\reportes\\seguimiento\\rptSeg7.jasper";

                    Map parametro=new HashMap();
                    parametro.put("parameter1", frmSeg.jftxtFechaDesde.getText());
                    parametro.put("parameter2", frmSeg.jftxtFechaHasta.getText());
                    
                    reporte = (JasperReport) JRLoader.loadObjectFromFile(path);

                    JasperPrint jPrint = JasperFillManager.fillReport(reporte, parametro, conn);

                    JasperViewer view = new JasperViewer(jPrint, false);
                    view.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                    view.setVisible(true);

                    } catch (JRException ex) {
                    Logger.getLogger(frmSeg.getName()).log(Level.SEVERE, null, ex);
            }
                break;

            default:

        }
    }
//*************************CRUD REGISTRO DE SEGUIMIENTO *****************
        public boolean registrarSeguimiento(ModSeguimiento modSeg) {

            PreparedStatement ps = null;
            Connection con = getConexion();
            ResultSet rs=null;

            try {
                String sql = "INSERT INTO seguimiento (fecha,motivo,detalle,dnialumno,idprofesor)VALUES (str_to_date(REPLACE(?,'/','.'),get_format(date,'EUR')),?,?,?,?)";
                ps = con.prepareStatement(sql);
                
                ps.setString(1, modSeg.fecha);
                ps.setString(2, modSeg.motivo);
                ps.setString(3, modSeg.detalle);
                ps.setInt(4, modSeg.dniAlumno);
                ps.setInt(5, modSeg.idprofesor);
                ps.execute();
                
                return true;

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
        
        //Metodo para Modificar un Alumno
    public boolean modificarSeguimiento(ModSeguimiento modSeg) {

        PreparedStatement ps = null;
        Connection con = getConexion();

        try {
            //JOptionPane.showMessageDialog(null, modSeg.id);
            ResultSet rs;
            String sql = "UPDATE seguimiento SET fecha=str_to_date(replace('"+modSeg.fecha+"','/','.'),get_format(date,'EUR')),motivo='"+modSeg.motivo+"',detalle='"+modSeg.detalle+"',dnialumno="+modSeg.dniAlumno+",idprofesor="+modSeg.idprofesor+" WHERE id="+ modSeg.id;
            ps = con.prepareStatement(sql);
            /*
            ps.setString(1, modSeg.fecha);
            ps.setString(2, modSeg.motivo);
            ps.setString(3, modSeg.detalle);
            ps.setInt(4, modSeg.dniAlumno);
            ps.setInt(5, modSeg.idprofesor);
             */  
            ps.execute();
            //ps=con.prepareStatement("select * from seguimiento where id="+modSeg.id );
            //rs=ps.executeQuery();
            //JOptionPane.showMessageDialog(null, rs.getRef("fecha"));
            
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
        public boolean eliminarSeguimiento(ModSeguimiento modSeg) {

        PreparedStatement ps = null;
        Connection con = getConexion();

        try {
            String sql = "DELETE FROM seguimiento WHERE id=" + modSeg.id;
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
    
    public boolean validarFecha(String fecha) {
        try {
            SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
            formatoFecha.setLenient(false);
            formatoFecha.parse(fecha);
        } catch (ParseException e) {
            return false;
        }
        return true;
    }
    
    //Metodo para Cargar el Combo Alumnos con todos los Nombres exustentes en la tabla Alumno
    public void cargaComboAlumnos(ModSeguimiento ModSeg, frmRegSeguimiento FrmRegSeg) {

        this.modSeg = ModSeg;
        this.frmRegSeg = FrmRegSeg;

        PreparedStatement ps = null;
        ResultSet rsAlumno = null;
        //Conexion conec=new Conexion();
        Connection con = getConexion();

        try {
            String sql = "SELECT nombre,dni FROM alumno ORDER BY nombre ASC";
            ps = con.prepareStatement(sql);
            rsAlumno = ps.executeQuery(sql);

            frmRegSeg.jcbxAlumno.addItem("");

            //System.out.println(rsPadres.);
            while (rsAlumno.next()) {
                //JOptionPane.showMessageDialog(null,rsPadres.getString("nombre"));
                frmRegSeg.jcbxAlumno.addItem(rsAlumno.getString("nombre"));
            }
        } catch (SQLException ex) {
            System.err.println(ex);
            Logger.getLogger(ConsultasAlumno.class.getName()).log(Level.SEVERE, null, ex);
            //JOptionPane.showMessageDialog(null,"Error al Conectar");
        }
    }
    //Metodo para Cargar el Combo Alumnos con todos los Nombres exustentes en la tabla Alumno
    public void cargaComboProfesores(ModSeguimiento ModSeg, frmRegSeguimiento FrmRegSeg) {

        this.modSeg = ModSeg;
        this.frmRegSeg = FrmRegSeg;

        PreparedStatement ps = null;
        ResultSet rsProf = null;
        //Conexion conec=new Conexion();
        Connection con = getConexion();

        try {
            String sql = "SELECT nombre,id FROM profesor ORDER BY nombre ASC";
            ps = con.prepareStatement(sql);
            rsProf = ps.executeQuery(sql);

            frmRegSeg.jcbxAlumno.addItem("");

            //System.out.println(rsPadres.);
            while (rsProf.next()) {
                //JOptionPane.showMessageDialog(null,rsPadres.getString("nombre"));
                frmRegSeg.jcbxProfesor.addItem(rsProf.getString("nombre"));
            }
        } catch (SQLException ex) {
            System.err.println(ex);
            Logger.getLogger(ConsultasSeguimiento.class.getName()).log(Level.SEVERE, null, ex);
            //JOptionPane.showMessageDialog(null,"Error al Conectar");
        }
    }
    
    public void cargaDniCursoAlumnos(frmRegSeguimiento frmRegSeg){
        this.frmRegSeg=frmRegSeg;
              
        try {
            //Para Profesor*****
            String sql = "SELECT dni,nombre,curso FROM alumno WHERE nombre ='" + frmRegSeg.jcbxAlumno.getSelectedItem()+"'";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                frmRegSeg.jtxtCurso.setText(rs.getString("curso"));
                frmRegSeg.jtxtDni.setText((String.valueOf(rs.getInt("dni"))));
            }
        }catch(SQLException e){
                System.out.println("Error al buscar datos del alumno "+ e);            
                    }
        
    }
    public void cargaIdProfesor(frmRegSeguimiento frmRegSeg){
        this.frmRegSeg=frmRegSeg;
              
        try {
            //Para Profesor*****
            String sql = "SELECT id,nombre,materia FROM profesor WHERE nombre ='" + frmRegSeg.jcbxProfesor.getSelectedItem()+"'";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                frmRegSeg.jtxtIdProf.setText(rs.getString("id"));
                }
        }catch(SQLException e){
                System.out.println("Error al buscar datos del profesor "+ e);            
                    }
        
    }
   
    public void actualizarGrilla(frmRegSeguimiento frm1, frmSeguimiento frm2){
        frmRegSeg=frm1;
        frmSeg=frm2;
        ConsultasSeguimiento modConsSeg=new ConsultasSeguimiento();
        //DefaultTableModel grilla=(DefaultTableModel) frmSeg.jtSeguimiento.getModel();
        frmSeg.jtSeguimiento.setValueAt(modSeg.getId(), frmSeg.jtSeguimiento.getSelectedRow(), 0);
        frmSeg.jtSeguimiento.setValueAt(modSeg.getFecha(), frmSeg.jtSeguimiento.getSelectedRow(), 1);
        frmSeg.jtSeguimiento.setValueAt(modSeg.getDniAlumno(), frmSeg.jtSeguimiento.getSelectedRow(), 2);
        frmSeg.jtSeguimiento.setValueAt(frmRegSeg.jcbxAlumno.getSelectedItem(), frmSeg.jtSeguimiento.getSelectedRow(), 3);
        frmSeg.jtSeguimiento.setValueAt(modSeg.getMotivo(), frmSeg.jtSeguimiento.getSelectedRow(), 4);
        frmSeg.jtSeguimiento.setValueAt(modSeg.getDetalle(), frmSeg.jtSeguimiento.getSelectedRow(), 5);
        
        modConsSeg.cargaDatosPadreProfesor(modSeg.getId(), modSeg.getDniAlumno(),frmSeg);
       
        
    }
    
    public void borraFilaDeGrilla(frmSeguimiento frmSeg){
        
        this.frmSeg=frmSeg;
        for(int i=0;i<6;i++){
            frmSeg.jtSeguimiento.setValueAt("", frmSeg.jtSeguimiento.getSelectedRow(), i);
        }
        
    }
}

