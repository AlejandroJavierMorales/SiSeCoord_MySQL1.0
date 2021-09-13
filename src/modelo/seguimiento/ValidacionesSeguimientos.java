
package modelo.seguimiento;

import java.awt.event.KeyEvent;
import vista.frmRegSeguimiento;


public class ValidacionesSeguimientos {
    
    //Valida DNI del Alumno, que se ingrese en el formato correcto
    public void validarFecha(frmRegSeguimiento FrmRegSeg, KeyEvent evt) {

        frmRegSeguimiento frmRegSeg = FrmRegSeg;
        char validar = evt.getKeyChar();
        //Valida que txtField DNI se ingrsen solo numeros y no mas de 6 digitos
        //if(evt.getSource()== frmAl.txtDni){
        if (!Character.isDigit(validar) || frmRegSeg.jftxtFecha.getText().length() > 7) {
            evt.consume();
        }
        //}              
    }

    //******* VALIDACION ESTADOS DE BOTONES Y CAJAS DE TEXTO DEL FORMULARIO DE ALUMNO *******
    public void validaEstadoBotones(String ObjValidar, frmRegSeguimiento frmRegSeg) {
        String queValidar;
        queValidar = ObjValidar;

        switch (queValidar) {
            case "alta":
                //txtfields
                frmRegSeg.jftxtFecha.setEnabled(true);
                frmRegSeg.jcbxMotivo.setEnabled(true);
                frmRegSeg.jtfDetalle.setEnabled(true);
                frmRegSeg.jcbxAlumno.setEnabled(true);
                frmRegSeg.jcbxProfesor.setEnabled(true);
                //botones
                frmRegSeg.btnAlta.setEnabled(false);
                frmRegSeg.btnModificar.setEnabled(false);
                frmRegSeg.btnEliminar.setEnabled(false);
                frmRegSeg.btnCancelar.setEnabled(true);
                frmRegSeg.btnGuardar.setEnabled(true);
                break;
            case "modificar":
                //txtfields
                frmRegSeg.jftxtFecha.setEnabled(true);
                frmRegSeg.jcbxMotivo.setEnabled(true);
                frmRegSeg.jtfDetalle.setEnabled(true);
                frmRegSeg.jcbxAlumno.setEnabled(true);
                frmRegSeg.jcbxProfesor.setEnabled(true);
                //botones
                frmRegSeg.btnAlta.setEnabled(false);
                frmRegSeg.btnModificar.setEnabled(false);
                frmRegSeg.btnEliminar.setEnabled(false);
                frmRegSeg.btnCancelar.setEnabled(true);
                frmRegSeg.btnGuardar.setEnabled(true);
                break;

            case "eliminar":
                frmRegSeg.jftxtFecha.setEnabled(false);
                frmRegSeg.jcbxMotivo.setEnabled(false);
                frmRegSeg.jtfDetalle.setEnabled(false);
                frmRegSeg.jcbxAlumno.setEnabled(false);
                frmRegSeg.jcbxProfesor.setEnabled(false);
                //botones
                frmRegSeg.btnAlta.setEnabled(true);
                frmRegSeg.btnModificar.setEnabled(false);
                frmRegSeg.btnEliminar.setEnabled(false);
                frmRegSeg.btnCancelar.setEnabled(false);
                frmRegSeg.btnGuardar.setEnabled(false);
                break;
            case "cancelar":
                //Habilita o Deshabilita Campos y Botones
                frmRegSeg.jftxtFecha.setEnabled(false);
                frmRegSeg.jcbxMotivo.setEnabled(false);
                frmRegSeg.jtfDetalle.setEnabled(false);
                frmRegSeg.jcbxAlumno.setEnabled(false);
                frmRegSeg.jcbxProfesor.setEnabled(false);
                //botones
                frmRegSeg.btnAlta.setEnabled(true);
                frmRegSeg.btnModificar.setEnabled(false);
                frmRegSeg.btnEliminar.setEnabled(false);
                frmRegSeg.btnCancelar.setEnabled(false);
                frmRegSeg.btnGuardar.setEnabled(false);
                break;
            case "guardar":
                //txtfields
                frmRegSeg.jftxtFecha.setEnabled(false);
                frmRegSeg.jcbxMotivo.setEnabled(false);
                frmRegSeg.jtfDetalle.setEnabled(false);
                frmRegSeg.jcbxAlumno.setEnabled(false);
                frmRegSeg.jcbxProfesor.setEnabled(false);
                //botones
                frmRegSeg.btnAlta.setEnabled(true);
                //Si hay datos en los TXTBOX
                /*if (frmRegSeg.jftxtFecha.getText().equals("  /  /   ")) {
                    frmRegSeg.btnEliminar.setEnabled(false);
                    frmRegSeg.btnModificar.setEnabled(false);
                } else {*/
                frmRegSeg.btnEliminar.setEnabled(false);
                frmRegSeg.btnModificar.setEnabled(false);
                //}
                frmRegSeg.btnCancelar.setEnabled(false);
                frmRegSeg.btnGuardar.setEnabled(false);

                break;
            case "comboNombreAlumnoTrue":
                //si en la busqueda de Alumno encuetra uno en la ase de datos
                //frmAl.btnModificar.setEnabled(true);
                //frmAl.btnEliminar.setEnabled(true);
                break;
            case "comboNombreAlumnoFalse":
                //si en la busqueda de Alumno NO encuetra uno en la ase de datos
                //frmAl.btnModificar.setEnabled(false);
                //frmAl.btnEliminar.setEnabled(false);
                break;
        }

    }
    
    
}
