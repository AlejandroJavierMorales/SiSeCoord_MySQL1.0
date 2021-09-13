
package controlador;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import modelo.Conexion;
import modelo.padre.ConsultasPadre;
import modelo.padre.ModPadre;
import vista.frmAlumno;
import vista.frmPrincipal;
import java.awt.Event;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import modelo.padre.ValidacionesPadre;
import vista.frmPadre;


public class CtrlPadre implements ActionListener, KeyListener, MouseListener{
    
    private ModPadre modPadre;
    private ConsultasPadre modConsPadre;
    private frmPadre frmPadre;
    private Conexion con;
    private ValidacionesPadre validPadre;

    //Constructor del Controlador
    public CtrlPadre(frmPadre frmPadre, ModPadre modPadre, ConsultasPadre modConsPadre, ValidacionesPadre validPadre) {
        this.modPadre = modPadre;
        this.modConsPadre = modConsPadre;
        this.frmPadre = frmPadre;
        this.validPadre = validPadre;

        //poner todos los botones del formulario de alumno en escucha: Listener
        this.frmPadre.btnAlta.addActionListener(this);
        this.frmPadre.btnCancelar.addActionListener(this);
        this.frmPadre.btnEliminar.addActionListener(this);
        this.frmPadre.btnGuardar.addActionListener(this);
        this.frmPadre.btnModificar.addActionListener(this);
        this.frmPadre.jcbxPadre.addActionListener(this);
        //this.frmPadre.cbxNombreAlumno.addMouseListener(this);
        //this.frmPadre.txtDni.addKeyListener(this);
        //JOptionPane.showMessageDialog(null,"Entro al Listener!!!");
    }
    //Sobreescribo el Metodos de las Interfaces Listeners
    //que va a permitir Identificar Todos los Eventos de BOtones y Teclas Ocurridos

    //De KeyListener
    @Override
    public void keyTyped(KeyEvent kevt) {
        validPadre.validarDni(frmPadre, kevt);
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
        if (e.getSource() == frmPadre.btnAlta) {
            int btnPressed = 1;
            modPadre.setBtnPressed(btnPressed);
            Limpiar();
            validPadre.validaEstadoBotones("alta", frmPadre);
            //JOptionPane.showMessageDialog(null,"Alta");

        }
        //Boton Mofificar
        if (e.getSource() == frmPadre.btnModificar) {
            int btnPressed = 2;
            modPadre.setBtnPressed(btnPressed);
            validPadre.validaEstadoBotones("modificar", frmPadre);
            //JOptionPane.showMessageDialog(null,modPadre.getNombre());
            modPadre.setIdPadre(modConsPadre.traeIdPadre(modPadre.getNombre()));
        }
        //Boton Eliminar
        if (e.getSource() == frmPadre.btnEliminar) {

            modPadre.setDni(Integer.parseInt(frmPadre.jtxtDni.getText()));
            if (JOptionPane.showConfirmDialog(null, "Desea Eliminar Este Registro", "Eliminar Alumno", JOptionPane.WARNING_MESSAGE, JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                modPadre.setIdPadre(Integer.parseInt(frmPadre.jlblId.getText()));
                if (modConsPadre.eliminarPadre(modPadre)) {
                    JOptionPane.showMessageDialog(null, "El Registro se ha Eliminado");
                } else {
                    JOptionPane.showMessageDialog(null, "Error...El Registro NO se ha Eliminado");
                }
            }

            Limpiar();
            validPadre.validaEstadoBotones("eliminar", frmPadre);
            //this.frmAl.cbxNombreAlumno.removeActionListener(this);
            modConsPadre.cargaComboPadre(modPadre, frmPadre);
            //this.frmAl.cbxNombreAlumno.addActionListener(this);
        }

        //Boton Guardar
        if (e.getSource() == frmPadre.btnGuardar) {
            if (frmPadre.jtxtDni.getText().length() > 0) {

                modPadre.setDni(Integer.parseInt(frmPadre.jtxtDni.getText()));
                modPadre.setNombre(frmPadre.jtxtNombre.getText());
                modPadre.setTelefono(frmPadre.jtxtTelefono.getText());
                modPadre.setEmail(frmPadre.jtxtEmail.getText());
                modPadre.setIdPadre(Integer.parseInt(frmPadre.jlblId.getText()));
                int btnPressed;
                btnPressed = modPadre.getBtnPressed();

                if (btnPressed == 1) {
                    //Lama al metodo RegistrarAlumno del Objeto ModCosAl: ConsultasAlumono del modelo
                    //si el metodo que genera el registro devuelve true es porque pudo guardar correctamente
                    //en la base de datos.
                    if (modConsPadre.registrarPadre(modPadre)) {
                        JOptionPane.showMessageDialog(null, "El Registro se ha Guardado Correctamente");

                    } else {
                        JOptionPane.showMessageDialog(null, "Error! El Registro no se ha Guardado...!");
                        //Limpiar();
                    }
                } else if (btnPressed == 2) {
                    if (modConsPadre.modificarPadre(modPadre)) {
                        JOptionPane.showMessageDialog(null, "El Registro se ha Modificado Correctamente");
                    } else {
                        JOptionPane.showMessageDialog(null, "Error! El Registro no se ha Modificado...!");
                        //Limpiar();
                    }
                }

            }
            validPadre.validaEstadoBotones("guardar", frmPadre);
            try {
                frmPadre.jcbxPadre.removeAll();
                modConsPadre.cargaComboPadre(modPadre, frmPadre);
            } catch (Exception e1) {
                System.err.println(e1);
            }
        }
        //Boton Cancelar
        if (e.getSource() == frmPadre.btnCancelar) {
            //JOptionPane.showMessageDialog(null,"Cancelar");
            validPadre.validaEstadoBotones("cancelar", frmPadre);
            Limpiar();

        }
        //ComboPadre, Busca Nombre seleccionado en la Tabla Padre y trae el Id del Padre Correspondiente
        //para colocarlo en el campo correpondiente de la tabla de alumnos
        if (e.getSource() == frmPadre.jcbxPadre) {

            try {
                modPadre.setNombre(frmPadre.jcbxPadre.getSelectedItem().toString());
                if (frmPadre.jcbxPadre.getSelectedItem().equals("")) {
                   
                      validPadre.validaEstadoBotones("comboNombreProfesorFalse", frmPadre);
                      Limpiar();
                    
                } else {
                    if(modConsPadre.buscarPadre(modPadre)){
                      frmPadre.jtxtDni.setText(String.valueOf(modPadre.getDni()));
                      frmPadre.jtxtEmail.setText(modPadre.getEmail());
                      frmPadre.jtxtTelefono.setText(modPadre.getTelefono());
                      frmPadre.jlblId.setText(String.valueOf(modPadre.getIdPadre()));
                      frmPadre.jtxtNombre.setText(modPadre.getNombre());
                      validPadre.validaEstadoBotones("comboNombreProfesorTrue", frmPadre);
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
        frmPadre.jtxtDni.setText(null);
        frmPadre.jtxtNombre.setText(null);
        frmPadre.jtxtEmail.setText(null);
        frmPadre.jtxtTelefono.setText(null);
        frmPadre.jlblId.setText("0");
        frmPadre.jcbxPadre.setSelectedIndex(-1);

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
        if (e.getSource() == frmPadre.jcbxPadre) {

            modPadre.setNombre(frmPadre.jcbxPadre.getSelectedItem().toString());

            if (modConsPadre.buscarPadre(modPadre)) {
                frmPadre.jtxtDni.setText(String.valueOf(modPadre.getDni()));
                frmPadre.jtxtNombre.setText(modPadre.getNombre());
                frmPadre.jtxtTelefono.setText(modPadre.getTelefono());
                frmPadre.jtxtEmail.setText(modPadre.getEmail());
                frmPadre.jlblId.setText(String.valueOf(modPadre.getIdPadre()));
                //Cargar combo Padre con el nombre del ID correspondiente
                //modConsPadre.cargaComboPadresAlumnos(modPadre, frmPadre);
                validPadre.validaEstadoBotones("comboNombreAlumnoTrue", frmPadre);
            } else {
                validPadre.validaEstadoBotones("comboNombreAlumnoFalse", frmPadre);
            }

        }
    }

    
}
