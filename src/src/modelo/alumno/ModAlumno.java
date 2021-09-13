/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.alumno;

/**
 *
 * @author Samsung
 */
public class ModAlumno {
    
    //Propiedades del Alumno
    int dni;
    String nombre;
    String curso;
    String obs;
    int padre;
    int btnPressed=0;//Para identificar si Precione Alta o Modificar antes de Guardar

    public int getBtnPressed() {
        return btnPressed;
    }

    public void setBtnPressed(int btnPressed) {
        this.btnPressed = btnPressed;
    }

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public int getPadre() {
        return padre;
    }

    public void setPadre(int padre) {
        this.padre = padre;
    }
    
    
    
    
}
