package modelo.alumno;

import java.awt.event.KeyEvent;
import vista.frmAlumno;


public class ValidacionesAlumnos {

    //Valida DNI del Alumno, que se ingrese en el formato correcto
    public void validarDni(frmAlumno frm, KeyEvent evt) {

        frmAlumno frmAl = frm;
        char validar = evt.getKeyChar();
        //Valida que txtField DNI se ingrsen solo numeros y no mas de 6 digitos
        //if(evt.getSource()== frmAl.txtDni){
        if (!Character.isDigit(validar) || frmAl.txtDni.getText().length() > 7) {
            evt.consume();
        }
        //}              
    }

    //******* VALIDACION ESTADOS DE BOTONES Y CAJAS DE TEXTO DEL FORMULARIO DE ALUMNO *******
    public void validaEstadoBotones(String ObjValidar, frmAlumno frmAl) {
        String queValidar;
        queValidar = ObjValidar;

        switch (queValidar) {
            case "alta":
                //txtfields
                frmAl.txtDni.setEnabled(true);
                frmAl.txtNombre.setEnabled(true);
                frmAl.txtCurso.setEnabled(true);
                frmAl.txtObs.setEnabled(true);
                frmAl.cbxPadre.setEnabled(true);
                frmAl.cbxNombreAlumno.setEnabled(false);
                //botones
                frmAl.btnAlta.setEnabled(false);
                frmAl.btnModificar.setEnabled(false);
                frmAl.btnEliminar.setEnabled(false);
                frmAl.btnCancelar.setEnabled(true);
                frmAl.btnGuardar.setEnabled(true);
                break;
            case "modificar":
                //txtfields
                frmAl.txtDni.setEnabled(true);
                frmAl.txtNombre.setEnabled(true);
                frmAl.txtCurso.setEnabled(true);
                frmAl.txtObs.setEnabled(true);
                frmAl.cbxPadre.setEnabled(true);
                frmAl.cbxNombreAlumno.setEnabled(false);
                //botones
                frmAl.btnAlta.setEnabled(false);
                frmAl.btnModificar.setEnabled(false);
                frmAl.btnEliminar.setEnabled(false);
                frmAl.btnCancelar.setEnabled(true);
                frmAl.btnGuardar.setEnabled(true);
                break;

            case "eliminar":
                frmAl.txtDni.setEnabled(false);
                frmAl.txtNombre.setEnabled(false);
                frmAl.txtCurso.setEnabled(false);
                frmAl.txtObs.setEnabled(false);
                frmAl.cbxPadre.setEnabled(false);
                frmAl.cbxNombreAlumno.setEnabled(true);
                //botones
                frmAl.btnAlta.setEnabled(true);
                frmAl.btnModificar.setEnabled(false);
                frmAl.btnEliminar.setEnabled(false);
                frmAl.btnCancelar.setEnabled(false);
                frmAl.btnGuardar.setEnabled(false);
                break;
            case "cancelar":
                //Habilita o Deshabilita Campos y Botones
                frmAl.txtDni.setEnabled(false);
                frmAl.txtNombre.setEnabled(false);
                frmAl.txtCurso.setEnabled(false);
                frmAl.txtObs.setEnabled(false);
                frmAl.cbxPadre.setEnabled(false);
                frmAl.cbxNombreAlumno.setEnabled(true);
                //botones
                frmAl.btnAlta.setEnabled(true);
                frmAl.btnModificar.setEnabled(false);
                frmAl.btnEliminar.setEnabled(false);
                frmAl.btnCancelar.setEnabled(false);
                frmAl.btnGuardar.setEnabled(false);
                break;
            case "guardar":
                //txtfields
                frmAl.txtDni.setEnabled(false);
                frmAl.txtNombre.setEnabled(false);
                frmAl.txtCurso.setEnabled(false);
                frmAl.txtObs.setEnabled(false);
                frmAl.cbxPadre.setEnabled(false);
                frmAl.cbxNombreAlumno.setEnabled(true);
                //botones
                frmAl.btnAlta.setEnabled(true);
                //Si hay datos en los TXTBOX
                if (frmAl.txtDni.getText().equals("")) {
                    frmAl.btnEliminar.setEnabled(false);
                    frmAl.btnModificar.setEnabled(false);
                } else {
                    frmAl.btnEliminar.setEnabled(true);
                    frmAl.btnModificar.setEnabled(true);
                }
                frmAl.btnCancelar.setEnabled(false);
                frmAl.btnGuardar.setEnabled(false);

                break;
            case "comboNombreAlumnoTrue":
                //si en la busqueda de Alumno encuetra uno en la ase de datos
                frmAl.btnModificar.setEnabled(true);
                frmAl.btnEliminar.setEnabled(true);
                break;
            case "comboNombreAlumnoFalse":
                //si en la busqueda de Alumno NO encuetra uno en la ase de datos
                frmAl.btnModificar.setEnabled(false);
                frmAl.btnEliminar.setEnabled(false);
                break;
        }

    }

}
