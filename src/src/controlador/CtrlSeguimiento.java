
package controlador;

import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
/*import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;*/
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import static jdk.nashorn.internal.objects.NativeString.trim;
import modelo.Conexion;
import modelo.seguimiento.ConsultasSeguimiento;
import modelo.seguimiento.ModSeguimiento;
import modelo.seguimiento.ValidacionesSeguimientos;
import vista.frmRegSeguimiento;
import vista.frmSeguimiento;




//IMPLEMENTAR LAS INTERFASES ActionListener, MouseListener y KeyListener
public class CtrlSeguimiento implements ActionListener, MouseListener, ListSelectionListener, KeyListener, PropertyChangeListener{
    private ModSeguimiento modSeg;
    private ConsultasSeguimiento modConsSeg;
    private frmSeguimiento frmSeg;
    private Conexion con;
    private ValidacionesSeguimientos validSeg;
    private frmRegSeguimiento frmRegSeg;
    
    
    //Constructor
    public CtrlSeguimiento(frmRegSeguimiento frmRegSeg,ModSeguimiento modSeg,ConsultasSeguimiento modConsSeg,frmSeguimiento frmSeg, ValidacionesSeguimientos valSeg){
            
        this.frmSeg=frmSeg;
        this.modConsSeg=modConsSeg;
        this.modSeg=modSeg;
        this.validSeg=valSeg;
        this.frmRegSeg=frmRegSeg;
        
        frmSeg.jtSeguimiento.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        //****Listener frmSeguimiento**********************************
        this.frmSeg.jtSeguimiento.addKeyListener(this);
        this.frmSeg.jtSeguimiento.addMouseListener(this);
        
        this.frmSeg.jchbNombre.addMouseListener(this);//Combobox
        this.frmSeg.jchbFecha.addMouseListener(this);
        this.frmSeg.jrbNombre.addMouseListener(this);//DataChooser
        this.frmSeg.jrbDni.addMouseListener(this);
        
        this.frmSeg.jbBuscar.addActionListener(this);//Boton Buscar
        this.frmSeg.jchbNombre.addActionListener(this);//CheckBox Nombre
        this.frmSeg.jchbFecha.addActionListener(this);//CheckBox Fecha
        this.frmSeg.jrbNombre.addActionListener(this);//RadioButton Nombre
        this.frmSeg.jrbDni.addActionListener(this);//RadioButton DNI
        this.frmSeg.jbtnImprimir.addActionListener(this);//RadioButton DNI
        frmSeg.btnModificarInforme.addActionListener(this);//Boton Modificar Informe
        frmSeg.btnEliminarInforme.addActionListener(this);//Boton Eliminar Informe
        frmSeg.btnGenerarInforme.addActionListener(this);//Boton Generar Informe
//****Listener frmRegSEguimiento*****************************
        frmRegSeg.btnAlta.addActionListener(this);
        frmRegSeg.btnModificar.addActionListener(this);
        frmRegSeg.btnGuardar.addActionListener(this);
        frmRegSeg.btnCancelar.addActionListener(this);
        frmRegSeg.btnEliminar.addActionListener(this);
        frmRegSeg.jcbxAlumno.addActionListener(this);
        frmRegSeg.jcbxProfesor.addActionListener(this);
        frmRegSeg.jcbxMotivo.addActionListener(this);
        
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //JOptionPane.showMessageDialog(null, e.getSource());
            
        int tipoBusqueda=0;
        int tipoDeReporte;
        //Boton Imprimir en Seguimiento
        if(e.getSource()==frmSeg.jbtnImprimir){
            tipoDeReporte=modSeg.getTipoReporte();
            modConsSeg.ReporteSegGral(frmSeg,tipoDeReporte);
        }
        //Boton de Generar informe    
        if(e.getSource()==frmSeg.btnGenerarInforme){
            //System.out.println(e);
            //JOptionPane.showMessageDialog(null, "El Registro se ha Eliminado");
            frmRegSeg.setLocationRelativeTo(null);
            frmRegSeg.setVisible(true);
            modConsSeg.cargaComboAlumnos(modSeg, frmRegSeg);
            modConsSeg.cargaComboProfesores(modSeg, frmRegSeg);
            this.Limpiar();
        }
        //Boton ModificarInforme
        if(e.getSource()==frmSeg.btnModificarInforme){
            DefaultTableModel tabla=(DefaultTableModel) frmSeg.jtSeguimiento.getModel();
            String valorId;
            valorId=String.valueOf(tabla.getValueAt(frmSeg.jtSeguimiento.getSelectedRow(), 0));
            //Mostrar el frmREgSeguimiento y LLenerlocon los datos segun valorId***
            frmRegSeg.setVisible(true);
            frmRegSeg.setLocationRelativeTo(null);
            this.Limpiar();
            frmRegSeg.jcbxAlumno.setEnabled(true);
            frmRegSeg.jcbxMotivo.setEnabled(true);
            frmRegSeg.jcbxProfesor.setEnabled(true);
            frmRegSeg.jtfDetalle.setEnabled(true);
            frmRegSeg.btnAlta.setEnabled(false);
            frmRegSeg.btnGuardar.setEnabled(true);
            modSeg.setBtnPressed(2);
            modConsSeg.cargaComboAlumnos(modSeg, frmRegSeg);
            modConsSeg.cargaComboProfesores(modSeg, frmRegSeg);
            
            frmRegSeg.jtxtIdSeg.setText(valorId);
            modSeg.setId(Integer.parseInt(valorId));
            frmRegSeg.jftxtFecha.setText(String.valueOf(tabla.getValueAt(frmSeg.jtSeguimiento.getSelectedRow(), 1)));
            frmRegSeg.jtxtDni.setText(String.valueOf(tabla.getValueAt(frmSeg.jtSeguimiento.getSelectedRow(), 2)));
            frmRegSeg.jcbxMotivo.setSelectedItem((String.valueOf(tabla.getValueAt(frmSeg.jtSeguimiento.getSelectedRow(), 4))));
            frmRegSeg.jcbxAlumno.setSelectedItem((String.valueOf(tabla.getValueAt(frmSeg.jtSeguimiento.getSelectedRow(), 3))));
            frmRegSeg.jcbxProfesor.setSelectedItem(frmSeg.jtxtProfesor.getText());
            frmRegSeg.jtfDetalle.setText(String.valueOf(tabla.getValueAt(frmSeg.jtSeguimiento.getSelectedRow(), 5)));
            //frmRegSeg.jftxtFecha.setText(String.valueOf(tabla.getValueAt(frmSeg.jtSeguimiento.getSelectedRow(), 0)));
            
            modConsSeg.cargaIdProfesor(frmRegSeg);
        }
        //Boton EliminarInforme
        if (e.getSource() == frmSeg.btnEliminarInforme) {
            DefaultTableModel grilla=(DefaultTableModel) frmSeg.jtSeguimiento.getModel();
            
            modSeg.setId((int) grilla.getValueAt(frmSeg.jtSeguimiento.getSelectedRow(), 0));
            if (JOptionPane.showConfirmDialog(null, "Desea Eliminar Este Registro", "Eliminar Registro de Seguimiento", JOptionPane.WARNING_MESSAGE, JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                if (modConsSeg.eliminarSeguimiento(modSeg)) {
                    JOptionPane.showMessageDialog(null, "El Registro se ha Eliminado");
                    //Limpiar la fila borrada en la Grilla*****
                    modConsSeg.borraFilaDeGrilla(frmSeg);
                } else {
                    JOptionPane.showMessageDialog(null, "Error...El Registro NO se ha Eliminado");
                }
            }
        
            
        }
        //Boton Buscar del Formulario de Seguimiento
        if(e.getSource()==frmSeg.jbBuscar){
            Date Fecha1=null;
            Date Fecha2=null;
            try{
            if(!frmSeg.jftxtFechaDesde.getText().equals("")){
                SimpleDateFormat formato=new SimpleDateFormat("dd/mm/yyyy");
                Fecha1=formato.parse(frmSeg.jftxtFechaDesde.getText());
                modSeg.setFecha1(Fecha1);
                }
            if(!frmSeg.jftxtFechaHasta.getText().equals("")){
                SimpleDateFormat formato=new SimpleDateFormat("dd/mm/yyyy");
                Fecha2=formato.parse(frmSeg.jftxtFechaHasta.getText());
                modSeg.setFecha2(Fecha2);
            }
            }catch(Exception eF){
                System.out.println(eF);
            }
            //JOptionPane.showMessageDialog(null, "Boton Buscar");
            //BUSCAR REGISTROS DE LA TABLE DE SEGUIMIENTOS POR CRITERIOS:
            
              if(frmSeg.jchbNombre.isSelected()){
                if(frmSeg.jchbFecha.isSelected()){
                    if(frmSeg.jrbNombre.isSelected()){
                        if(Fecha1.equals(Fecha2)){
                        //Buscar por Nombre de Alumno y Fecha de Registro
                        tipoBusqueda=1;
                        modSeg.setTipoReporte(1);
                        //JOptionPane.showMessageDialog(null,"La Fecha1 "+ modSeg.getFecha1());
                        }else if(Fecha1.before(Fecha2)){ //Buscar por Nombre y Fecha Desde Hasta (periodo)
                                    tipoBusqueda=2;
                                    modSeg.setTipoReporte(2);
                                    //JOptionPane.showMessageDialog(null,"La Fecha1 Menor a Fecha2...!");
                              }else{ //Si FechaDesde es < que FechaHasta Error!!!!
                            //JOptionPane.showMessageDialog(null,"La Fecha Seleccionada es Incorrecta...!");
                        }
                        
                    }else{
                        //Buscar por DNI de Alumno y por Fecha de Registro
                        if(Fecha1.equals(Fecha2)){
                        //Buscar por DNI de Alumno y Fecha de Registro
                        tipoBusqueda=3;
                        modSeg.setTipoReporte(3);
                        }else if(Fecha1.before(Fecha2)){ //Buscar por DNI y Fecha Desde Hasta (periodo)
                                    tipoBusqueda=4;
                                    modSeg.setTipoReporte(4);
                              }else{ //Si FechaDesde es < que FechaHasta Error!!!!
                            JOptionPane.showMessageDialog(null,"La Fecha Seleccionada es Incorrecta...!");
                        }
                    }
                }else if(frmSeg.jrbNombre.isSelected()){
                //Busca por Nombre de Alumno
                    tipoBusqueda=5;
                    modSeg.setTipoReporte(5);
                    //JOptionPane.showMessageDialog(null, "Boton Buscar xNombre");
                }else{
                    //Busca por DNI Alumno
                    tipoBusqueda=6;
                    modSeg.setTipoReporte(6);
                    //JOptionPane.showMessageDialog(null, "Boton BuscarDNI");
                }
            }else if (frmSeg.jchbFecha.isSelected()){
                    if(Fecha1.equals(Fecha2)){
                    //Busca Registro por Fecha unicamente
                    tipoBusqueda=7;
                    modSeg.setTipoReporte(7);
                    }else if(Fecha1.before(Fecha2)){
                        //Busca Registro por perios Fecha Desde Hasta.
                        tipoBusqueda=8;
                        modSeg.setTipoReporte(8);
                    }
            }else{
                //Busca totod los registros (Ni por Alumno ni por Fecha)
                tipoBusqueda=0;
                modSeg.setTipoReporte(0);
            }
                modConsSeg.verDetalleSeguimiento(frmSeg, tipoBusqueda);
            
        }
        //*************CRUD Seguimiento**************
        //Boton Alta
        if (e.getSource() == frmRegSeg.btnAlta) {
            //JOptionPane.showMessageDialog(null, "Alta!");
            int btnPressed = 1;
            modSeg.setBtnPressed(btnPressed);
            Limpiar();
            validSeg.validaEstadoBotones("alta", frmRegSeg);
            //JOptionPane.showMessageDialog(null,"Alta");

        }
        //Boton Mofificar
        if (e.getSource() == frmRegSeg.btnModificar) {
            int btnPressed = 2;
            modSeg.setBtnPressed(btnPressed);
            validSeg.validaEstadoBotones("modificar", frmRegSeg);
        }
        //Boton Eliminar
        if (e.getSource() == frmRegSeg.btnEliminar) {

            modSeg.setId(Integer.parseInt(frmRegSeg.jtxtIdSeg.getText()));
            if (JOptionPane.showConfirmDialog(null, "Desea Eliminar Este Registro", "Eliminar Alumno", JOptionPane.WARNING_MESSAGE, JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                if (modConsSeg.eliminarSeguimiento(modSeg)) {
                    JOptionPane.showMessageDialog(null, "El Registro se ha Eliminado");
                } else {
                    JOptionPane.showMessageDialog(null, "Error...El Registro NO se ha Eliminado");
                }
            }

            Limpiar();
            validSeg.validaEstadoBotones("eliminar", frmRegSeg);
        }

        //Boton Guardar
        if (e.getSource() == frmRegSeg.btnGuardar) {
            if (modConsSeg.validarFecha(frmRegSeg.jftxtFecha.getText())) {
                              
                if(!(frmRegSeg.jtfDetalle.getText().equals("")) || frmRegSeg.jtxtDni.getText().length()==6){
                    modSeg.setFecha(trim(frmRegSeg.jftxtFecha.getText()));
                    modSeg.setMotivo((String) frmRegSeg.jcbxMotivo.getSelectedItem());
                    modSeg.setDetalle(frmRegSeg.jtfDetalle.getText());
                    modSeg.setDniAlumno(Integer.parseInt(frmRegSeg.jtxtDni.getText()));
                    modSeg.setIdprofesor(Integer.parseInt(frmRegSeg.jtxtIdProf.getText()));
                    //modSeg.setId(Integer.parseInt(frmRegSeg.jtxtIdSeg.getText()));
                    int btnPressed;
                    btnPressed = modSeg.getBtnPressed();

                    if (btnPressed == 1) {
                         //Lama al metodo RegistrarAlumno del Objeto ModCosAl: ConsultasAlumono del modelo
                        //si el metodo que genera el registro devuelve true es porque pudo guardar correctamente
                        //en la base de datos.
                        if (modConsSeg.registrarSeguimiento(modSeg)) {
                            //Trae idSeguimiento al jtxtId
                            //modConsSeg.traeIdSeguimiento(frmRegSeg);
                            JOptionPane.showMessageDialog(null, "El Registro se ha Guardado Correctamente");
                            frmRegSeg.setVisible(false);
                        } else {
                            JOptionPane.showMessageDialog(null, "Error! El Registro no se ha Guardado...!");
                            //Limpiar();
                        }
                    } else if (btnPressed == 2) {
                        if (modConsSeg.modificarSeguimiento(modSeg)) {
                            JOptionPane.showMessageDialog(null, "El Registro se ha Modificado Correctamente");
                            //******************* cargar en la grilla las modificaciones Desde frmRegSeg********
                            modConsSeg.actualizarGrilla(frmRegSeg,frmSeg);
                            frmRegSeg.setVisible(false);
                            //******************************************************************
                        } else {
                            JOptionPane.showMessageDialog(null, "Error! El Registro no se ha Modificado...!");
                            //Limpiar();
                        }
                    }
                    validSeg.validaEstadoBotones("guardar", frmRegSeg);
                }else{
                    JOptionPane.showMessageDialog(null, "Error!   Alumno o Detalle incorrecto...");
                }
            }else{
                JOptionPane.showMessageDialog(null, "Error!   Fecha Incorrecta...");
                        
            }
        }
        //Boton Cancelar
        if (e.getSource() == frmRegSeg.btnCancelar) {
            //JOptionPane.showMessageDialog(null,"Cancelar");
            validSeg.validaEstadoBotones("cancelar", frmRegSeg);
            Limpiar();
        }
        //Combobox Alumno
        if(e.getSource()==frmRegSeg.jcbxAlumno){
              modConsSeg.cargaDniCursoAlumnos(frmRegSeg);
        }
        //Combobox Profesor
        if(e.getSource()==frmRegSeg.jcbxProfesor){
              modConsSeg.cargaIdProfesor(frmRegSeg);
        }
        //ComboMotivo de Informe
        if(e.getSource()==frmRegSeg.jcbxMotivo){
            //JOptionPane.showMessageDialog(null, "Motivo");
            if(frmRegSeg.jcbxMotivo.getSelectedItem()!="Profesor"){
                frmRegSeg.jcbxProfesor.setSelectedItem("");
                modConsSeg.cargaIdProfesor(frmRegSeg);   
            }
        }
    }
    private void Limpiar() {
        frmRegSeg.jftxtFecha.setText(null);
        frmRegSeg.jcbxMotivo.setSelectedIndex(-1);
        frmRegSeg.jcbxAlumno.setSelectedIndex(-1);
        frmRegSeg.jtxtDni.setText(null);
        frmRegSeg.jtxtCurso.setText(null);
        frmRegSeg.jtxtDni.setText("0");
        frmRegSeg.jcbxProfesor.setSelectedIndex(-1);
        frmRegSeg.jtxtIdProf.setText("0");
        frmRegSeg.jtfDetalle.setText("");

    }
    @Override
    public void mouseClicked(MouseEvent e) {
        
        
        //***Si hizo Click en Grilla de la Tabla*****
        if(e.getSource()==frmSeg.jtSeguimiento){
            int id;
            int dniAlumno;
            
            int fila=frmSeg.jtSeguimiento.getSelectedRow();
            id=(int) frmSeg.jtSeguimiento.getValueAt(fila, 0);
            dniAlumno=(int) frmSeg.jtSeguimiento.getValueAt(fila, 2);
            
            String detalle=(String) frmSeg.jtSeguimiento.getValueAt(fila, 5);
            frmSeg.jtxtDetalle.setText(detalle);
            modConsSeg.cargaDatosPadreProfesor(id, dniAlumno,frmSeg);
            //System.out.println("Evento MouseClicked");
            
        }
        //Click en CheckBox Alumno
        if(e.getSource()==frmSeg.jchbNombre){
            if(frmSeg.jchbNombre.isSelected()){
                frmSeg.jrbNombre.setEnabled(true);
                frmSeg.jrbDni.setEnabled(true);
                modConsSeg.cargaCombosEnFrmSeguiiento(modSeg, frmSeg);
                if(frmSeg.jrbNombre.isSelected()){
                    frmSeg.jcbNombre.setEnabled(true);
                }
                if(frmSeg.jrbDni.isSelected()){
                    frmSeg.jcbDni.setEnabled(true);
                }
            }else{
                frmSeg.jrbNombre.setEnabled(false);
                frmSeg.jrbDni.setEnabled(false);
                frmSeg.jcbNombre.setEnabled(false);
                frmSeg.jcbDni.setEnabled(false);
            }
        }
        //Click en RadioButtons
        if(e.getSource()==frmSeg.jrbNombre){
            frmSeg.jcbNombre.setEnabled(true);
            frmSeg.jcbDni.setEnabled(false);
            
            frmSeg.jcbNombre.removeAllItems();
            frmSeg.jcbDni.removeAllItems();
            modConsSeg.cargaCombosEnFrmSeguiiento(modSeg, frmSeg);
        }
        if(e.getSource()==frmSeg.jrbDni){
            frmSeg.jcbNombre.setEnabled(false);
            frmSeg.jcbDni.setEnabled(true);
            
            frmSeg.jcbNombre.removeAllItems();
            frmSeg.jcbDni.removeAllItems();
            modConsSeg.cargaCombosEnFrmSeguiiento(modSeg, frmSeg);
        }
        //CheckboxFecha
        if(e.getSource()==frmSeg.jchbFecha){
            
            if(frmSeg.jchbFecha.isSelected()){
                frmSeg.jftxtFechaDesde.setEnabled(true);
                frmSeg.jftxtFechaHasta.setEnabled(true);
            }else{
                frmSeg.jftxtFechaDesde.setEnabled(false);
                frmSeg.jftxtFechaHasta.setEnabled(false);
            }
        }
        //ComboMotivo de Informe
        if(e.getSource()==frmRegSeg.jcbxMotivo){
            //JOptionPane.showMessageDialog(null, "Motivo");
            if(!frmRegSeg.jcbxMotivo.getSelectedItem().equals("Profesor")){
                frmRegSeg.jcbxProfesor.setSelectedItem("");
                modConsSeg.cargaIdProfesor(frmRegSeg);   
            }
        }
                
    }   

    @Override
    public void mousePressed(MouseEvent e) {
     
   }

    @Override
    public void mouseReleased(MouseEvent e) {
    
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    
    }

    @Override
    public void mouseExited(MouseEvent e) {
     
  
    }
    //ListSelectionListener
    @Override
    public void valueChanged(ListSelectionEvent e) {
        
         }

    @Override
    public void keyTyped(KeyEvent e) {
   
    }

    @Override
    public void keyPressed(KeyEvent e) {
         
    }

    @Override
    public void keyReleased(KeyEvent e) {
         //***Si presionas una tecla sobre la Tabla(desplazamiento)*****
        if(e.getSource()==frmSeg.jtSeguimiento){
            int id;
            int dniAlumno;
            
            int fila=frmSeg.jtSeguimiento.getSelectedRow();
            id=(int) frmSeg.jtSeguimiento.getValueAt(fila, 0);
            dniAlumno=(int) frmSeg.jtSeguimiento.getValueAt(fila, 2);
            
            String detalle=(String) frmSeg.jtSeguimiento.getValueAt(fila, 5);
            frmSeg.jtxtDetalle.setText(detalle);
            modConsSeg.cargaDatosPadreProfesor(id, dniAlumno,frmSeg);
           
            
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
    
    
    
    
}
