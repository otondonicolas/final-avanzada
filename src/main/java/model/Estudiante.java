package model;

import javax.persistence.Entity;

@Entity
public class Estudiante extends Usuario {

    public Estudiante(String nombre) {
        super(null, nombre);
    }

    public Estudiante() {

    }

    public Estudiante(Integer id, String nombre) {
        super(id, nombre);
    }

    @Override
    public String toString(){
        return getNombre();
    }
}