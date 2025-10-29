package com.peluqueria.controller;

import com.peluqueria.entity.Servicio;
import com.peluqueria.entity.TiposServicio;
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
        // Retorna 201 si es exitoso, 400 si la validación falla en el Service
        return (nuevo != null) ? ResponseEntity.status(201).body(nuevo) : ResponseEntity.badRequest().build();
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
        // Retorna 200 si es exitoso, 400 si la validación falla en el Service
        return (actualizado != null) ? ResponseEntity.ok(actualizado) : ResponseEntity.badRequest().build();
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


    @GetMapping("/buscar/tipo")
    public ResponseEntity<List<Servicio>> buscarPorTipo(@RequestParam TiposServicio tipo) {
        List<Servicio> resultados = servicioService.buscarPorTipoServicio(tipo);
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