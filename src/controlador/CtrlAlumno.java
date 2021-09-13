package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import modelo.Conexion;
import modelo.alumno.ConsultasAlumno;
import modelo.alumno.ModAlumno;
import vista.frmAlumno;
import vista.frmPrincipal;
import java.awt.Event;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import modelo.alumno.ValidacionesAlumnos;

public class CtrlAlumno implements ActionListener, KeyListener, MouseListener {

    private ModAlumno modAl;
    private ConsultasAlumno modConsAl;
    private frmAlumno frmAl;
    private Conexion con;
    private ValidacionesAlumnos validAl;

    //Constructor del Controlador
    public CtrlAlumno(frmAlumno frmAl1, ModAlumno modAl1, ConsultasAlumno modConsAl1, ValidacionesAlumnos validAl1) {
        this.modAl = modAl1;
        this.modConsAl = modConsAl1;
        this.frmAl = frmAl1;
        this.validAl = validAl1;

        //poner todos los botones del formulario de alumno en escucha: Listener
        this.frmAl.btnAlta.addActionListener(this);
        this.frmAl.btnCancelar.addActionListener(this);
        this.frmAl.btnEliminar.addActionListener(this);
        this.frmAl.btnGuardar.addActionListener(this);
        this.frmAl.btnModificar.addActionListener(this);
        this.frmAl.cbxPadre.addActionListener(this);
        this.frmAl.cbxNombreAlumno.addMouseListener(this);
        this.frmAl.txtDni.addKeyListener(this);
        //JOptionPane.showMessageDialog(null,"Entro al Listener!!!");
    }
    //Sobreescribo el Metodos de las Interfaces Listeners
    //que va a permitir Identificar Todos los Eventos de BOtones y Teclas Ocurridos

    //De KeyListener
    @Override
    public void keyTyped(KeyEvent kevt) {
        validAl.validarDni(frmAl, kevt);
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
        if (e.getSource() == frmAl.btnAlta) {
            int btnPressed = 1;
            modAl.setBtnPressed(btnPressed);
            Limpiar();
            validAl.validaEstadoBotones("alta", frmAl);
            //JOptionPane.showMessageDialog(null,"Alta");

        }
        //Boton Mofificar
        if (e.getSource() == frmAl.btnModificar) {
            int btnPressed = 2;
            modAl.setBtnPressed(btnPressed);
            validAl.validaEstadoBotones("modificar", frmAl);
        }
        //Boton Eliminar
        if (e.getSource() == frmAl.btnEliminar) {

            modAl.setDni(Integer.parseInt(frmAl.txtDni.getText()));
            if (JOptionPane.showConfirmDialog(null, "Desea Eliminar Este Registro", "Eliminar Alumno", JOptionPane.WARNING_MESSAGE, JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                if (modConsAl.eliminarAlumno(modAl)) {
                    JOptionPane.showMessageDialog(null, "El Registro se ha Eliminado");
                } else {
                    JOptionPane.showMessageDialog(null, "Error...El Registro NO se ha Eliminado");
                }
            }

            Limpiar();
            validAl.validaEstadoBotones("eliminar", frmAl);
            //this.frmAl.cbxNombreAlumno.removeActionListener(this);
            modConsAl.cargaComboAlumnos(modAl, frmAl);
            //this.frmAl.cbxNombreAlumno.addActionListener(this);
        }

        //Boton Guardar
        if (e.getSource() == frmAl.btnGuardar) {
            if (frmAl.txtDni.getText().length() > 0) {

                modAl.setDni(Integer.parseInt(frmAl.txtDni.getText()));
                modAl.setNombre(frmAl.txtNombre.getText());
                modAl.setCurso(frmAl.txtCurso.getText());
                modAl.setObs(frmAl.txtObs.getText());
                modAl.setPadre(Integer.parseInt(frmAl.txtIdPadre.getText()));

                int btnPressed;
                btnPressed = modAl.getBtnPressed();

                if (btnPressed == 1) {
                    //Lama al metodo RegistrarAlumno del Objeto ModCosAl: ConsultasAlumono del modelo
                    //si el metodo que genera el registro devuelve true es porque pudo guardar correctamente
                    //en la base de datos.
                    if (modConsAl.registrarAlumno(modAl)) {
                        JOptionPane.showMessageDialog(null, "El Registro se ha Guardado Correctamente");

                    } else {
                        JOptionPane.showMessageDialog(null, "Error! El Registro no se ha Guardado...!");
                        Limpiar();
                    }
                } else if (btnPressed == 2) {
                    if (modConsAl.modificarAlumno(modAl)) {
                        JOptionPane.showMessageDialog(null, "El Registro se ha Modificado Correctamente");
                    } else {
                        JOptionPane.showMessageDialog(null, "Error! El Registro no se ha Modificado...!");
                        Limpiar();
                    }
                }

            }
            validAl.validaEstadoBotones("guardar", frmAl);
            try {
                frmAl.cbxNombreAlumno.removeAll();
                modConsAl.cargaComboAlumnos(modAl, frmAl);
            } catch (Exception e1) {
                System.err.println(e1);
            }
        }
        //Boton Cancelar
        if (e.getSource() == frmAl.btnCancelar) {
            //JOptionPane.showMessageDialog(null,"Cancelar");
            validAl.validaEstadoBotones("cancelar", frmAl);
            Limpiar();

        }
        //ComboPadre, Busca Nombre seleccionado en la Tabla Padre y trae el Id del Padre Correspondiente
        //para colocarlo en el campo correpondiente de la tabla de alumnos
        if (e.getSource() == frmAl.cbxPadre) {

            try {
                if (frmAl.cbxPadre.getSelectedItem().equals("")) {
                    //int Id=0;
                    //frmAl.txtIdPadre.setText(Integer.toString(Id));
                } else {
                    int Id = (modConsAl.traeIdPadre(frmAl.cbxPadre.getSelectedItem().toString()));
                    frmAl.txtIdPadre.setText(Integer.toString(Id));
                }
            } catch (Exception exp) {
                System.out.println(exp);
            }

        }

    }

    public void Iniciar() {
        //frmAl.setTitle("CeateReadUpdateDelete con ModeloVistaCcontrolador");
        //frmAl.setLocationRelativeTo(null);
        //frmAl.txtId.setVisible(false);

    }

    private void Limpiar() {
        frmAl.txtDni.setText(null);
        frmAl.txtNombre.setText(null);
        frmAl.txtCurso.setText(null);
        frmAl.txtObs.setText(null);
        frmAl.txtIdPadre.setText("0");
        frmAl.cbxPadre.setSelectedIndex(-1);

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
        //ComboAlumnos, Busca Alumnos por Nombre
        if (e.getSource() == frmAl.cbxNombreAlumno) {

            modAl.setNombre(frmAl.cbxNombreAlumno.getSelectedItem().toString());

            if (modConsAl.buscarAlumno(modAl)) {
                frmAl.txtDni.setText(String.valueOf(modAl.getDni()));
                frmAl.txtNombre.setText(modAl.getNombre());
                frmAl.txtCurso.setText(modAl.getCurso());
                frmAl.txtIdPadre.setText(String.valueOf(modAl.getPadre()));
                //frmAl.cbxPadre.setSelectedItem("pepe");
                frmAl.txtObs.setText(modAl.getObs());
                //Cargar combo Padre con el nombre del ID correspondiente
                modConsAl.cargaComboPadresAlumnos(modAl, frmAl);
                validAl.validaEstadoBotones("comboNombreAlumnoTrue", frmAl);
            } else {
                validAl.validaEstadoBotones("comboNombreAlumnoFalse", frmAl);
            }

        }
    }

}
