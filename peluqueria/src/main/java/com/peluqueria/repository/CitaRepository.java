package com.peluqueria.repository;

import com.peluqueria.entity.Cita;
import com.peluqueria.entity.EstadoCita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface CitaRepository extends JpaRepository<Cita, Long> {

    // Método para encontrar citas de un alumno en una fecha específica
    List<Cita> findByAlumnoIdUsuarioAndFecha(Long alumnoId, LocalDate fecha);

    // Método para encontrar citas por estado (PENDIENTE, REALIZADA, CANCELADA)
    List<Cita> findByEstado(EstadoCita estado);

    // Query Nativa: Buscar citas REALIZADAS que tuvieron una duración superior a un valor y fueron realizadas por un alumno específico.
    @Query(value = "SELECT c.* FROM cita c JOIN servicio s ON c.id_servicio = s.id_servicio " +
            "WHERE c.estado = 'REALIZADA' AND s.duracion_minutos > :duracionMin AND c.id_alumno = :alumnoId", nativeQuery = true)
    List<Cita> buscarCitasRealizadasYDuracionMayor(@Param("duracionMin") int duracionMin, @Param("alumnoId") Long alumnoId);
}
