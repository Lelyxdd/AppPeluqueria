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

    // Un Servicio no necesita conocer sus Citas para el CRUD básico,
    // pero podría ser útil si implementamos informes complejos.
}
