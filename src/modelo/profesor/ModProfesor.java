
package modelo.profesor;






public class ModProfesor {
    int legajo;
    String nombre;
    String materia;
    String email;
    int id;
    int btnPressed=0;//Para identificar si Precione Alta o Modificar antes de Guardar

    public int getBtnPressed() {
        return btnPressed;
    }

    public void setBtnPressed(int btnPressed) {
        this.btnPressed = btnPressed;
    }

    public int getLegajo() {
        return legajo;
    }

    public void setLegajo(int legajo) {
        this.legajo = legajo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getMateria() {
        return materia;
    }

    public void setMateria(String materia) {
        this.materia = materia;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
