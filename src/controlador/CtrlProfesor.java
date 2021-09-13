
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JOptionPane;
import modelo.Conexion;
import modelo.profesor.ConsultasProfesor;
import modelo.profesor.ModProfesor;
import modelo.profesor.ValidacionesProfesor;
import vista.frmProfesor;





public class CtrlProfesor implements ActionListener, KeyListener, MouseListener {
   
     private ModProfesor modProf;
    private ConsultasProfesor modConsProf;
    private frmProfesor frmProf;
    private Conexion con;
    private ValidacionesProfesor validProf;

    //Constructor del Controlador
    public CtrlProfesor(frmProfesor frmProf,ModProfesor modProf,ConsultasProfesor consProf,ValidacionesProfesor valProf) {
       
        this.modProf = modProf;
        this.modConsProf = consProf;
        this.frmProf = frmProf;
        this.validProf = valProf;

        //poner todos los botones del formulario de alumno en escucha: Listener
        this.frmProf.btnAlta.addActionListener(this);
        this.frmProf.btnCancelar.addActionListener(this);
        this.frmProf.btnEliminar.addActionListener(this);
        this.frmProf.btnGuardar.addActionListener(this);
        this.frmProf.btnModificar.addActionListener(this);
        this.frmProf.jcbxProf.addActionListener(this);
    }
    //Sobreescribo el Metodos de las Interfaces Listeners
    //que va a permitir Identificar Todos los Eventos de BOtones y Teclas Ocurridos

    //De KeyListener
    @Override
    public void keyTyped(KeyEvent kevt) {
        validProf.validarDni(frmProf, kevt);
    }

    @Override
    public void keyPressed(KeyEvent kpe) {

    }

    @Override
    public void keyReleased(KeyEvent kre) {

    }

    //De ActionListener
    @Override
    public void actionPerformed(ActionEvent e) {
         //Boton Alta
        if (e.getSource() == frmProf.btnAlta) {
            int btnPressed = 1;
            modProf.setBtnPressed(btnPressed);
            Limpiar();
            validProf.validaEstadoBotones("alta", frmProf);
            
        }
        //Boton Mofificar
        if (e.getSource() == frmProf.btnModificar) {
            int btnPressed = 2;
            modProf.setBtnPressed(btnPressed);
            validProf.validaEstadoBotones("modificar", frmProf);
            //JOptionPane.showMessageDialog(null,modPadre.getNombre());
            modProf.setId(modConsProf.traeIdProfesor(modProf.getNombre()));
        }
        //Boton Eliminar
        if (e.getSource() == frmProf.btnEliminar) {

            modProf.setLegajo(Integer.parseInt(frmProf.jtxtLegajo.getText()));
            if (JOptionPane.showConfirmDialog(null, "Desea Eliminar Este Registro", "Eliminar Alumno", JOptionPane.WARNING_MESSAGE, JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                modProf.setId(Integer.parseInt(frmProf.jlblId.getText()));
                if (modConsProf.eliminarProfesor(modProf)) {
                    JOptionPane.showMessageDialog(null, "El Registro se ha Eliminado");
                } else {
                    JOptionPane.showMessageDialog(null, "Error...El Registro NO se ha Eliminado");
                }
            }

            Limpiar();
            validProf.validaEstadoBotones("eliminar", frmProf);
            //this.frmAl.cbxNombreAlumno.removeActionListener(this);
            modConsProf.cargaComboProfesor(modProf, frmProf);
            //this.frmAl.cbxNombreAlumno.addActionListener(this);
        }

        //Boton Guardar
        if (e.getSource() == frmProf.btnGuardar) {
            if (frmProf.jtxtLegajo.getText().length() > 0) {

                modProf.setLegajo(Integer.parseInt(frmProf.jtxtLegajo.getText()));
                modProf.setNombre(frmProf.jtxtNombre.getText());
                modProf.setMateria(frmProf.jtxtMateria.getText());
                modProf.setEmail(frmProf.jtxtEmail.getText());
                modProf.setId(Integer.parseInt(frmProf.jlblId.getText()));
                int btnPressed;
                btnPressed = modProf.getBtnPressed();

                if (btnPressed == 1) {
                    //Lama al metodo RegistrarAlumno del Objeto ModCosAl: ConsultasAlumono del modelo
                    //si el metodo que genera el registro devuelve true es porque pudo guardar correctamente
                    //en la base de datos.
                    if (modConsProf.registrarProfesor(modProf)) {
                        JOptionPane.showMessageDialog(null, "El Registro se ha Guardado Correctamente");

                    } else {
                        JOptionPane.showMessageDialog(null, "Error! El Registro no se ha Guardado...!");
                        //Limpiar();
                    }
                } else if (btnPressed == 2) {
                    if (modConsProf.modificarProfesor(modProf)) {
                        JOptionPane.showMessageDialog(null, "El Registro se ha Modificado Correctamente");
                    } else {
                        JOptionPane.showMessageDialog(null, "Error! El Registro no se ha Modificado...!");
                        //Limpiar();
                    }
                }

            }
            validProf.validaEstadoBotones("guardar", frmProf);
            try {
                frmProf.jcbxProf.removeAll();
                modConsProf.cargaComboProfesor(modProf, frmProf);
            } catch (Exception e1) {
                System.err.println(e1);
            }
        }
        //Boton Cancelar
        if (e.getSource() == frmProf.btnCancelar) {
            //JOptionPane.showMessageDialog(null,"Cancelar");
            validProf.validaEstadoBotones("cancelar", frmProf);
            Limpiar();

        }
        //ComboPadre, Busca Nombre seleccionado en la Tabla Padre y trae el Id del Padre Correspondiente
        //para colocarlo en el campo correpondiente de la tabla de alumnos
        if (e.getSource() == frmProf.jcbxProf) {
            //JOptionPane.showMessageDialog(null, "Entro al evento del combo");
            try {
                modProf.setNombre(frmProf.jcbxProf.getSelectedItem().toString());
                if (frmProf.jcbxProf.getSelectedItem().equals("")) {
                   
                      validProf.validaEstadoBotones("comboNombreProfesorFalse", frmProf);
                      Limpiar();
                    
                } else {
                    //JOptionPane.showMessageDialog(null, "Entro al if");
                    if(modConsProf.buscarProfesor(modProf)){
                      frmProf.jtxtLegajo.setText(String.valueOf(modProf.getLegajo()));
                      frmProf.jtxtEmail.setText(modProf.getEmail());
                      frmProf.jtxtMateria.setText(modProf.getMateria());
                      frmProf.jlblId.setText(String.valueOf(modProf.getId()));
                      frmProf.jtxtNombre.setText(modProf.getNombre());
                      validProf.validaEstadoBotones("comboNombreProfesorTrue", frmProf);
                    }
                    
                    //int Id = (modConsPadre.traeIdPadre());
                    //frmPadre.jlblId.setText(Integer.toString(Id));
                }
            } catch (Exception exp) {
                System.out.println(exp);
            }

        }

    }

    private void Limpiar() {
        frmProf.jtxtLegajo.setText(null);
        frmProf.jtxtNombre.setText(null);
        frmProf.jtxtEmail.setText(null);
        frmProf.jtxtMateria.setText(null);
        frmProf.jlblId.setText("0");
        frmProf.jcbxProf.setSelectedIndex(-1);

    }

    @Override
    public void mouseClicked(MouseEvent e) {

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
        //ComboPadre, Busca Padre por Nombre
        if (e.getSource() == frmProf.jcbxProf) {

            modProf.setNombre(frmProf.jcbxProf.getSelectedItem().toString());

            if (modConsProf.buscarProfesor(modProf)) {
                frmProf.jtxtLegajo.setText(String.valueOf(modProf.getLegajo()));
                frmProf.jtxtNombre.setText(modProf.getNombre());
                frmProf.jtxtMateria.setText(modProf.getMateria());
                frmProf.jtxtEmail.setText(modProf.getEmail());
                frmProf.jlblId.setText(String.valueOf(modProf.getId()));
                //Cargar combo Padre con el nombre del ID correspondiente
                //modConsPadre.cargaComboPadresAlumnos(modPadre, frmPadre);
                validProf.validaEstadoBotones("comboNombreAlumnoTrue", frmProf);
            } else {
                validProf.validaEstadoBotones("comboNombreAlumnoFalse", frmProf);
            }

        }
    }

    
        
}
    
    
    

