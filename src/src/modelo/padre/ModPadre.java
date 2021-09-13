
package modelo.padre;


public class ModPadre {
    int dni;
    String nombre;
    String telefono;
    String email;
    int idPadre;
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

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getIdPadre() {
        return idPadre;
    }

    public void setIdPadre(int idPadre) {
        this.idPadre = idPadre;
    }
}
