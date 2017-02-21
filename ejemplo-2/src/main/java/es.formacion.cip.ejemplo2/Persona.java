package es.formacion.cip.ejemplo2;

/**
 * Created by jpexposito on 21/2/17.
 */
public class Persona {

    String nombre;
    String apellido1;
    String apellido2;
    String dni;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido1() {
        return apellido1;
    }

    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    public String getApellido2() {
        return apellido2;
    }

    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public Persona(String nombre, String apellido1, String apellido2, String dni) {
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.dni = dni;
    }

    public Persona() {
    }



    public String toString() {
        return "Mi nombre es: " + nombre + " " + apellido1 + " " +apellido2 + " " + ", y tengo dni:" +dni;
    }
}



