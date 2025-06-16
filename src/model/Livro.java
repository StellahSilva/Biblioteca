package model;

public class Livro {
    private String titulo;
    private String autor;
    private String codigo;
    private boolean disponivel;

    public Livro(String titulo, String autor, String codigo, boolean disponivel) {
        this.titulo = titulo;
        this.autor = autor;
        this.codigo = codigo;
        this.disponivel = disponivel;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public void marcarComoEmprestado() {
        this.disponivel = false;
    }

    public void marcarComoDisponivel() {
        this.disponivel = true;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public String getCodigo() {
        return codigo;
    }
}
