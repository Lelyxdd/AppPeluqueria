package com.peluqueria.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "cita")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cita {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCita;

    private LocalDate fecha;
    private LocalTime horaInicio;
    private LocalTime horaFin;

    @Enumerated(EnumType.STRING)
    private EstadoCita estado; // PENDIENTE, REALIZADA, CANCELADA

    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private Usuario cliente; // Extenderá de Usuario

    @ManyToOne
    @JoinColumn(name = "id_alumno")
    private Usuario alumno; // Extenderá de Usuario

    @ManyToOne
    @JoinColumn(name = "id_servicio")
    private Servicio servicio;
}
