package pe.edu.tecsup.lab01.sistemaacademico.model;

public class DetalleMatricula {
    private int idDetalle;
    private int idMatricula;
    private int idCurso;
    private String estado;

    // Getters y Setters
    public int getIdDetalle() { return idDetalle; }
    public void setIdDetalle(int idDetalle) { this.idDetalle = idDetalle; }

    public int getIdMatricula() { return idMatricula; }
    public void setIdMatricula(int idMatricula) { this.idMatricula = idMatricula; }

    public int getIdCurso() { return idCurso; }
    public void setIdCurso(int idCurso) { this.idCurso = idCurso; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
}
