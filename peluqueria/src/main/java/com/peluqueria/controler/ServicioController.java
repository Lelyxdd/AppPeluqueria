package com.peluqueria.controller;

import com.peluqueria.entity.Servicio;
import com.peluqueria.service.ServicioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/servicios") // URL base para todos los endpoints de Servicio
public class ServicioController {

    @Autowired
    private ServicioService servicioService;

    // 1. GET ALL
    // http://localhost:8080/api/v1/servicios
    @GetMapping
    public ResponseEntity<List<Servicio>> getAllServicios() {
        return ResponseEntity.ok(servicioService.findAll());
    }

    // 2. GET BY ID
    // http://localhost:8080/api/v1/servicios/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Servicio> getServicioById(@PathVariable Long id) {
        Servicio servicio = servicioService.findById(id);
        if (servicio != null) {
            return ResponseEntity.ok(servicio);
        }
        return ResponseEntity.notFound().build();
    }

    // 3. POST (Insertar)
    // http://localhost:8080/api/v1/servicios
    @PostMapping
    public ResponseEntity<Servicio> createServicio(@RequestBody Servicio servicio) {
        Servicio nuevoServicio = servicioService.save(servicio);
        if (nuevoServicio != null) {
            // 201 Created es la respuesta estándar para una inserción exitosa
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevoServicio);
        }
        // Si retorna null (falla la validación del servicio)
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    // 4. PUT (Actualizar)
    // http://localhost:8080/api/v1/servicios/{id}
    @PutMapping("/{id}")
    public ResponseEntity<Servicio> updateServicio(@PathVariable Long id, @RequestBody Servicio servicioDetalles) {
        Servicio servicioExistente = servicioService.findById(id);

        if (servicioExistente != null) {
            // Se garantiza que el ID no se pierda en la actualización
            servicioDetalles.setIdServicio(id);
            Servicio servicioActualizado = servicioService.save(servicioDetalles);

            if (servicioActualizado != null) {
                return ResponseEntity.ok(servicioActualizado);
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build(); // Error de validación (duración)
        }
        return ResponseEntity.notFound().build();
    }

    // 5. DELETE
    // http://localhost:8080/api/v1/servicios/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteServicio(@PathVariable Long id) {
        if (servicioService.findById(id) != null) {
            servicioService.deleteById(id);
            // 204 No Content es la respuesta estándar para una eliminación exitosa
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    // CONSULTAS PERSONALIZADAS

    // 6. Búsqueda por nombre (usa ContainingIgnoreCase para LIKE %nombre%)
    // http://localhost:8080/api/v1/servicios/busquedas/nombre?q=corte
    @GetMapping("/busquedas/nombre")
    public ResponseEntity<List<Servicio>> buscarPorNombre(@RequestParam("q") String nombre) {
        List<Servicio> servicios = servicioService.buscarPorNombre(nombre);
        if (servicios.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(servicios);
    }

    // 7. Búsqueda por duración mínima (Query Nativa: operador >)
    // http://localhost:8080/api/v1/servicios/busquedas/nativa?duracion=4
    @GetMapping("/busquedas/nativa")
    public ResponseEntity<List<Servicio>> buscarPorDuracionMinima(@RequestParam("duracion") int duracion) {
        List<Servicio> servicios = servicioService.buscarPorDuracionMinimaNativa(duracion);
        if (servicios.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(servicios);
    }
}
