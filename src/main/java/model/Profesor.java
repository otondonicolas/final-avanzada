package model;

import javax.persistence.Entity;

@Entity
public class Profesor extends Usuario {

    public Profesor(String nombre) {
        super(null, nombre);
    }

    public Profesor() {

    }

    public Profesor(Integer id, String nombre) {
        super(id, nombre);
    }

    @Override
    public String toString() {
        return getNombre();
    }
}