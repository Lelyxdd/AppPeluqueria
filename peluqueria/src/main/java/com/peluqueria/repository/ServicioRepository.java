package com.peluqueria.repository;

import com.peluqueria.entity.Servicio;
import com.peluqueria.entity.TiposServicio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ServicioRepository extends JpaRepository<Servicio, Long> {

    // Búsqueda por nombre (parcial y sin distinguir mayúsculas/minúsculas)
    // Esto equivale a LIKE %nombre%
    List<Servicio> findByNombreContainingIgnoreCase(String nombre);

    // NUEVO: Búsqueda por Tipo de Servicio (usando el ENUM)
    List<Servicio> findByTipoServicio(TiposServicio tipoServicio);

    // Búsqueda nativa: servicios más largos que X bloques (usando el operador >)
    @Query(
            value = "SELECT * FROM servicio s WHERE s.duracion_bloques > :minDuracion",
            nativeQuery = true)
    List<Servicio> buscarPorDuracionMinima(@Param("minDuracion") int minDuracion);
}
