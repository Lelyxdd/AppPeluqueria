package com.peluqueria.controller;

import com.peluqueria.entity.Servicio;
import com.peluqueria.service.ServicioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/servicios")
public class ServicioController {

    @Autowired
    private ServicioService servicioService;

    // Obtener todos los servicios
    @GetMapping
    public ResponseEntity<List<Servicio>> listarServicios() {
        List<Servicio> servicios = servicioService.findAll();
        return servicios.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(servicios);
    }

    // Obtener un servicio por su ID
    @GetMapping("/{id}")
    public ResponseEntity<Servicio> obtenerServicio(@PathVariable Long id) {
        Servicio servicio = servicioService.findById(id);
        return (servicio != null) ? ResponseEntity.ok(servicio) : ResponseEntity.notFound().build();
    }

    // Crear un nuevo servicio
    @PostMapping
    public ResponseEntity<Servicio> crearServicio(@RequestBody Servicio servicio) {
        Servicio nuevo = servicioService.save(servicio);
        return ResponseEntity.status(201).body(nuevo);
    }

    // Actualizar un servicio existente
    @PutMapping("/{id}")
    public ResponseEntity<Servicio> actualizarServicio(@PathVariable Long id, @RequestBody Servicio servicio) {
        Servicio existente = servicioService.findById(id);
        if (existente == null) {
            return ResponseEntity.notFound().build();
        }
        servicio.setIdServicio(id);
        Servicio actualizado = servicioService.save(servicio);
        return ResponseEntity.ok(actualizado);
    }

    // Eliminar un servicio
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarServicio(@PathVariable Long id) {
        Servicio servicio = servicioService.findById(id);
        if (servicio == null) {
            return ResponseEntity.notFound().build();
        }
        servicioService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    // Buscar servicios por nombre
    @GetMapping("/buscar")
    public ResponseEntity<List<Servicio>> buscarPorNombre(@RequestParam String nombre) {
        List<Servicio> resultados = servicioService.buscarPorNombre(nombre);
        return resultados.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(resultados);
    }

    // Buscar servicios por duración mínima (consulta nativa)
    @GetMapping("/buscar/duracion")
    public ResponseEntity<List<Servicio>> buscarPorDuracion(@RequestParam int duracion) {
        List<Servicio> resultados = servicioService.buscarPorDuracionMinimaNativa(duracion);
        return resultados.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(resultados);
    }

    //--------------------------extra------------------------------



}
