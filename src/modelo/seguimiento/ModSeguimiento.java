/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.seguimiento;

import java.util.Date;

/**
 *
 * @author Samsung
 */
public class ModSeguimiento {
    int id;
    String fecha;
    String motivo;
    String detalle;
    int dniAlumno;
    int idprofesor;
    int btnPressed=0;

    public int getBtnPressed() {
        return btnPressed;
    }

    public void setBtnPressed(int btnPressed) {
        this.btnPressed = btnPressed;
    }

    public int getIdprofesor() {
        return idprofesor;
    }

    public void setIdprofesor(int idprofesor) {
        this.idprofesor = idprofesor;
    }
    
    Date fecha1;
    Date fecha2;
    int tipoReporte;

    public int getTipoReporte() {
        return tipoReporte;
    }

    public void setTipoReporte(int tipoReporte) {
        this.tipoReporte = tipoReporte;
    }

    public Date getFecha1() {
        return fecha1;
    }

    public void setFecha1(Date fecha1) {
        this.fecha1 = fecha1;
    }

    public Date getFecha2() {
        return fecha2;
    }

    public void setFecha2(Date fecha2) {
        this.fecha2 = fecha2;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public int getDniAlumno() {
        return dniAlumno;
    }

    public void setDniAlumno(int dniAlumno) {
        this.dniAlumno = dniAlumno;
    }
    
    
    
}
