/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.profesor;

import java.awt.event.KeyEvent;
import vista.frmProfesor;

/**
 *
 * @author Samsung
 */
public class ValidacionesProfesor {
    
     //Valida DNI del Alumno, que se ingrese en el formato correcto
    public void validarDni(frmProfesor frm, KeyEvent evt) {

        frmProfesor frmProf = frm;
        char validar = evt.getKeyChar();
        //Valida que txtField DNI se ingrsen solo numeros y no mas de 6 digitos
        //if(evt.getSource()== frmAl.txtDni){
        if (!Character.isDigit(validar) || frmProf.jtxtLegajo.getText().length() > 8) {
            evt.consume();
        }
        //}              
    }

    //******* VALIDACION ESTADOS DE BOTONES Y CAJAS DE TEXTO DEL FORMULARIO DE ALUMNO *******
    public void validaEstadoBotones(String ObjValidar, frmProfesor frmProf) {
        String queValidar;
        queValidar = ObjValidar;

        switch (queValidar) {
            case "alta":
                //txtfields
                frmProf.jtxtLegajo.setEnabled(true);
                frmProf.jtxtNombre.setEnabled(true);
                frmProf.jtxtMateria.setEnabled(true);
                frmProf.jtxtEmail.setEnabled(true);
                frmProf.jcbxProf.setEnabled(false);
                //botones
                frmProf.btnAlta.setEnabled(false);
                frmProf.btnModificar.setEnabled(false);
                frmProf.btnEliminar.setEnabled(false);
                frmProf.btnCancelar.setEnabled(true);
                frmProf.btnGuardar.setEnabled(true);
                break;
            case "modificar":
                //txtfields
                frmProf.jtxtLegajo.setEnabled(true);
                frmProf.jtxtNombre.setEnabled(true);
                frmProf.jtxtMateria.setEnabled(true);
                frmProf.jtxtEmail.setEnabled(true);
                frmProf.jcbxProf.setEnabled(false);
                //frmPadre.cbxNombreAlumno.setEnabled(false);
                //botones
                frmProf.btnAlta.setEnabled(false);
                frmProf.btnModificar.setEnabled(false);
                frmProf.btnEliminar.setEnabled(false);
                frmProf.btnCancelar.setEnabled(true);
                frmProf.btnGuardar.setEnabled(true);
                break;

            case "eliminar":
                frmProf.jtxtLegajo.setEnabled(false);
                frmProf.jtxtNombre.setEnabled(false);
                frmProf.jtxtMateria.setEnabled(false);
                frmProf.jtxtEmail.setEnabled(false);
                frmProf.jcbxProf.setEnabled(true);
                //botones
                frmProf.btnAlta.setEnabled(true);
                frmProf.btnModificar.setEnabled(false);
                frmProf.btnEliminar.setEnabled(false);
                frmProf.btnCancelar.setEnabled(false);
                frmProf.btnGuardar.setEnabled(false);
                break;
            case "cancelar":
                //Habilita o Deshabilita Campos y Botones
                frmProf.jtxtLegajo.setEnabled(false);
                frmProf.jtxtNombre.setEnabled(false);
                frmProf.jtxtMateria.setEnabled(false);
                frmProf.jtxtEmail.setEnabled(false);
                frmProf.jcbxProf.setEnabled(true);
                //frmPadre.cbxNombreAlumno.setEnabled(true);
                //botones
                frmProf.btnAlta.setEnabled(true);
                frmProf.btnModificar.setEnabled(false);
                frmProf.btnEliminar.setEnabled(false);
                frmProf.btnCancelar.setEnabled(false);
                frmProf.btnGuardar.setEnabled(false);
                break;
            case "guardar":
                //txtfields
                frmProf.jtxtLegajo.setEnabled(false);
                frmProf.jtxtNombre.setEnabled(false);
                frmProf.jtxtMateria.setEnabled(false);
                frmProf.jtxtEmail.setEnabled(false);
                frmProf.jcbxProf.setEnabled(true);
                //botones
                frmProf.btnAlta.setEnabled(true);
                //Si hay datos en los TXTBOX
                if (frmProf.jtxtLegajo.getText().equals("")) {
                    frmProf.btnEliminar.setEnabled(false);
                    frmProf.btnModificar.setEnabled(false);
                } else {
                    frmProf.btnEliminar.setEnabled(true);
                    frmProf.btnModificar.setEnabled(true);
                }
                frmProf.btnCancelar.setEnabled(false);
                frmProf.btnGuardar.setEnabled(false);

                break;
            case "comboNombreProfesorTrue":
                //si en la busqueda de Alumno encuetra uno en la ase de datos
                frmProf.btnAlta.setEnabled(true);
                frmProf.btnModificar.setEnabled(true);
                frmProf.btnEliminar.setEnabled(true);
                break;
            case "comboNombreProfesorFalse":
                //si en la busqueda de Alumno NO encuetra uno en la ase de datos
                frmProf.btnAlta.setEnabled(true);
                frmProf.btnModificar.setEnabled(false);
                frmProf.btnEliminar.setEnabled(false);
                
                break;
        }

    } 
    
}
