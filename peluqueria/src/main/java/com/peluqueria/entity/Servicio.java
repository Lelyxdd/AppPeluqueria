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

    @Enumerated(EnumType.STRING) // CRUCIAL: Guarda el ENUM como String en la BD
    @Column(name = "tipo_servicio", nullable = false) // Mapea a la columna 'tipo_servicio' de la BD
    private TiposServicio tipoServicio;

    @Column(nullable = false)
    private String nombre; // Nombre específico del servicio: "Corte de media melena"

    @Column(columnDefinition = "TEXT")
    private String descripcion;

    @Column(name = "duracion_bloques", nullable = false)
    private int duracionBloques; // Duración en intervalos de 30 minutos

    @Column(nullable = false)
    private double precio;

    // --- Getters y Setters (Aunque @Data los genera, los mantenemos si quieres ser explícito) ---

    // Getter y Setter para el nuevo campo tipoServicio
    public TiposServicio getTipoServicio() {
        return tipoServicio;
    }

    public void setTipoServicio(TiposServicio tipoServicio) {
        this.tipoServicio = tipoServicio;
    }

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
