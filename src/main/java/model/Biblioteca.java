package model;

import java.util.*;

public class Biblioteca {
    private List<Libro> libros = new ArrayList<>();
    private List<Usuario> usuarios = new ArrayList<>();

    private Map<Usuario, List<Libro>> prestamos = new HashMap<>();

    public Libro buscarLibroPorId(String id) {
        for (Libro libro : libros) {
            if (libro.getId().equals(id)) {
                return libro;
            }
        }
        return null;
    }

    public Usuario buscarUsuarioPorId(String id) {
        for (Usuario usuario : usuarios) {
            if (usuario.getId().equals(id)) {
                return usuario;
            }
        }
        return null;
    }

    public void prestarLibro(String idLibro, String idUsuario) {
        Libro libro = buscarLibroPorId(idLibro);
        Usuario usuario = buscarUsuarioPorId(idUsuario);

        if (libro != null && usuario != null) {
            List<Libro> librosPrestados = prestamos.get(usuario);
            if (librosPrestados == null) {
                librosPrestados = new ArrayList<>();
                prestamos.put(usuario, librosPrestados);
            }
            librosPrestados.add(libro);
        } else {
            System.out.println("Libro o usuario no encontrado.");
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Biblioteca biblioteca = (Biblioteca) obj;
        return Objects.equals(libros, biblioteca.libros) &&
                Objects.equals(usuarios, biblioteca.usuarios);
    }

    @Override
    public int hashCode() {
        return Objects.hash(libros, usuarios);
    }

    public List<Libro> getLibroPrestado(Usuario usuario) {
        return prestamos.get(usuario);
    }
}
