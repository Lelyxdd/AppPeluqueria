package com.peluqueria.controller;

import com.peluqueria.entity.Servicio;
import com.peluqueria.service.ServicioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/servicios") // URL base para todos los endpoints de Servicio
public class ServicioController {

    @Autowired
    private ServicioService servicioService;

    // http://localhost:8080/api/servicios
    @GetMapping
    public ResponseEntity<List<Servicio>> getAllServicios() {
        return ResponseEntity.ok(servicioService.findAll());
    }


    // http://localhost:8080/api/servicios/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Servicio> getServicioById(@PathVariable Long id) {
        Servicio servicio = servicioService.findById(id);
        if (servicio != null) {
            return ResponseEntity.ok(servicio);
        }
        return ResponseEntity.notFound().build();
    }


    // http://localhost:8080/servicios
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


    // http://localhost:8080/servicios/{id}
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

    // http://localhost:8080/servicios/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteServicio(@PathVariable Long id) {
        if (servicioService.findById(id) != null) {
            servicioService.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }


    // http://localhost:8080/servicios/busquedas/nombre?q=corte
    @GetMapping("/busquedas/nombre")
    public ResponseEntity<List<Servicio>> buscarPorNombre(@RequestParam("q") String nombre) {
        List<Servicio> servicios = servicioService.buscarPorNombre(nombre);
        if (servicios.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(servicios);
    }


    // http://localhost:8080/servicios/busquedas/nativa?duracion=4
    @GetMapping("/busquedas/nativa")
    public ResponseEntity<List<Servicio>> buscarPorDuracionMinima(@RequestParam("duracion") int duracion) {
        List<Servicio> servicios = servicioService.buscarPorDuracionMinimaNativa(duracion);
        if (servicios.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(servicios);
    }
}
