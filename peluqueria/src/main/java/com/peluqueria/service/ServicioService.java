package com.peluqueria.service;

import com.peluqueria.entity.Servicio;
import com.peluqueria.entity.TiposServicio;
import com.peluqueria.repository.ServicioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicioService {

    @Autowired
    private ServicioRepository servicioRepository;

    public List<Servicio> findAll() {
        return servicioRepository.findAll();
    }

    public Servicio findById(Long id) {
        return servicioRepository.findById(id).orElse(null);
    }

    public Servicio save(Servicio servicio) {
        // Validación básica de duración
        if (servicio.getDuracionBloques() <= 0) {
            System.err.println("ERROR: La duración del servicio debe ser mayor a cero.");
            return null; // Devolver null para indicar que la validación falló
        }
        return servicioRepository.save(servicio);
    }

    public void deleteById(Long id) {
        servicioRepository.deleteById(id);
    }

    // Consultas personalizadas

    public List<Servicio> buscarPorNombre(String nombre) {
        // Usa ContainingIgnoreCase, que permite buscar sin % en el código y es insensible a mayúsculas
        return servicioRepository.findByNombreContainingIgnoreCase(nombre);
    }


    public List<Servicio> buscarPorTipoServicio(TiposServicio tipoServicio) {
        return servicioRepository.findByTipoServicio(tipoServicio);}

    public List<Servicio> buscarPorDuracionMinimaNativa(int minDuracion) {
        // Usa la query nativa con el operador >
        return servicioRepository.buscarPorDuracionMinima(minDuracion);
    }
}