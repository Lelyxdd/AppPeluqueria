package com.peluqueria.service;

import com.peluqueria.entity.Servicio;
import com.peluqueria.entity.TiposServicio;
import com.peluqueria.repository.ServicioRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import java.util.List;

@Service
public class ServicioService {

    @Autowired
    private ServicioRepository servicioRepository;

    @PersistenceContext
    private EntityManager entityManager;

    public List<Servicio> findAll() {
        return servicioRepository.findAll();
    }

    public Servicio findById(Long id) {
        return servicioRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Servicio no encontrado"));
    }

    public Servicio save(Servicio servicio) {
        if (servicio.getDuracionBloques() <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La duración del servicio debe ser mayor a cero");
        }
        return servicioRepository.save(servicio);
    }

    public void deleteById(Long id) {
        if (!servicioRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Servicio no encontrado");
        }
        servicioRepository.deleteById(id);
    }

    public List<Servicio> buscarPorNombre(String nombre) {
        return servicioRepository.findByNombreContainingIgnoreCase(nombre);
    }

    public List<Servicio> buscarPorTipoServicio(TiposServicio tipoServicio) {
        return servicioRepository.findByTipoServicio(tipoServicio);
    }

    public List<Servicio> buscarPorDuracionMinimaNativa(int minDuracion) {
        return servicioRepository.buscarPorDuracionMinima(minDuracion);
    }

    // Nuevo método con EntityManager para cumplir el criterio RAAD3 punto 7
    public List<Servicio> buscarPorDuracionConEntityManager(int minDuracion) {
        String jpql = "SELECT s FROM Servicio s WHERE s.duracionBloques > :minDuracion";
        TypedQuery<Servicio> query = entityManager.createQuery(jpql, Servicio.class);
        query.setParameter("minDuracion", minDuracion);
        return query.getResultList();
    }
}
