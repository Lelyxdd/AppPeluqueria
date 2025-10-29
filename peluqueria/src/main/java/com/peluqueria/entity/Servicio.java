package com.peluqueria.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "servicio")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Servicio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idServicio;

    @Column(nullable = false)
    private String nombre; // corte, tinte, manicura, etc

    @Column(columnDefinition = "TEXT")
    private String descripcion;

    @Column(name = "duracion_bloques", nullable = false)
    private int duracionBloques; // Duración en intervalos de 30 minutos

    @Column(nullable = false)
    private double precio;

    public Long getIdServicio() {
        return idServicio;
    }

    public void setIdServicio(Long idServicio) {
        this.idServicio = idServicio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getDuracionBloques() {
        return duracionBloques;
    }

    public void setDuracionBloques(int duracionBloques) {
        this.duracionBloques = duracionBloques;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
}
