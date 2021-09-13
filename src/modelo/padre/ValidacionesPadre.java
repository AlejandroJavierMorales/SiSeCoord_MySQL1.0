/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.padre;

import java.awt.event.KeyEvent;
import vista.frmPadre;

/**
 *
 * @author Samsung
 */
public class ValidacionesPadre {
    
     //Valida DNI del Alumno, que se ingrese en el formato correcto
    public void validarDni(frmPadre frm, KeyEvent evt) {

        frmPadre frmPadre = frm;
        char validar = evt.getKeyChar();
        //Valida que txtField DNI se ingrsen solo numeros y no mas de 6 digitos
        //if(evt.getSource()== frmAl.txtDni){
        if (!Character.isDigit(validar) || frmPadre.jtxtDni.getText().length() > 7) {
            evt.consume();
        }
        //}              
    }

    //******* VALIDACION ESTADOS DE BOTONES Y CAJAS DE TEXTO DEL FORMULARIO DE ALUMNO *******
    public void validaEstadoBotones(String ObjValidar, frmPadre frmPadre) {
        String queValidar;
        queValidar = ObjValidar;

        switch (queValidar) {
            case "alta":
                //txtfields
                frmPadre.jtxtDni.setEnabled(true);
                frmPadre.jtxtNombre.setEnabled(true);
                frmPadre.jtxtTelefono.setEnabled(true);
                frmPadre.jtxtEmail.setEnabled(true);
                //frmPadre.cbxPadre.setEnabled(true);
                frmPadre.jcbxPadre.setEnabled(false);
                //botones
                frmPadre.btnAlta.setEnabled(false);
                frmPadre.btnModificar.setEnabled(false);
                frmPadre.btnEliminar.setEnabled(false);
                frmPadre.btnCancelar.setEnabled(true);
                frmPadre.btnGuardar.setEnabled(true);
                break;
            case "modificar":
                //txtfields
                frmPadre.jtxtDni.setEnabled(true);
                frmPadre.jtxtNombre.setEnabled(true);
                frmPadre.jtxtTelefono.setEnabled(true);
                frmPadre.jtxtEmail.setEnabled(true);
                frmPadre.jcbxPadre.setEnabled(false);
                //frmPadre.cbxNombreAlumno.setEnabled(false);
                //botones
                frmPadre.btnAlta.setEnabled(false);
                frmPadre.btnModificar.setEnabled(false);
                frmPadre.btnEliminar.setEnabled(false);
                frmPadre.btnCancelar.setEnabled(true);
                frmPadre.btnGuardar.setEnabled(true);
                break;

            case "eliminar":
                frmPadre.jtxtDni.setEnabled(false);
                frmPadre.jtxtNombre.setEnabled(false);
                frmPadre.jtxtTelefono.setEnabled(false);
                frmPadre.jtxtEmail.setEnabled(false);
                frmPadre.jcbxPadre.setEnabled(true);
                //botones
                frmPadre.btnAlta.setEnabled(true);
                frmPadre.btnModificar.setEnabled(false);
                frmPadre.btnEliminar.setEnabled(false);
                frmPadre.btnCancelar.setEnabled(false);
                frmPadre.btnGuardar.setEnabled(false);
                break;
            case "cancelar":
                //Habilita o Deshabilita Campos y Botones
                frmPadre.jtxtDni.setEnabled(false);
                frmPadre.jtxtNombre.setEnabled(false);
                frmPadre.jtxtTelefono.setEnabled(false);
                frmPadre.jtxtEmail.setEnabled(false);
                frmPadre.jcbxPadre.setEnabled(true);
                //frmPadre.cbxNombreAlumno.setEnabled(true);
                //botones
                frmPadre.btnAlta.setEnabled(true);
                frmPadre.btnModificar.setEnabled(false);
                frmPadre.btnEliminar.setEnabled(false);
                frmPadre.btnCancelar.setEnabled(false);
                frmPadre.btnGuardar.setEnabled(false);
                break;
            case "guardar":
                //txtfields
                frmPadre.jtxtDni.setEnabled(false);
                frmPadre.jtxtNombre.setEnabled(false);
                frmPadre.jtxtTelefono.setEnabled(false);
                frmPadre.jtxtEmail.setEnabled(false);
                frmPadre.jcbxPadre.setEnabled(true);
                //botones
                frmPadre.btnAlta.setEnabled(true);
                //Si hay datos en los TXTBOX
                if (frmPadre.jtxtDni.getText().equals("")) {
                    frmPadre.btnEliminar.setEnabled(false);
                    frmPadre.btnModificar.setEnabled(false);
                } else {
                    frmPadre.btnEliminar.setEnabled(true);
                    frmPadre.btnModificar.setEnabled(true);
                }
                frmPadre.btnCancelar.setEnabled(false);
                frmPadre.btnGuardar.setEnabled(false);

                break;
            case "comboNombrePadreTrue":
                //si en la busqueda de Alumno encuetra uno en la ase de datos
                frmPadre.btnAlta.setEnabled(true);
                frmPadre.btnModificar.setEnabled(true);
                frmPadre.btnEliminar.setEnabled(true);
                break;
            case "comboNombrePadreFalse":
                //si en la busqueda de Alumno NO encuetra uno en la ase de datos
                frmPadre.btnAlta.setEnabled(true);
                frmPadre.btnModificar.setEnabled(false);
                frmPadre.btnEliminar.setEnabled(false);
                
                break;
        }

    } 
}
