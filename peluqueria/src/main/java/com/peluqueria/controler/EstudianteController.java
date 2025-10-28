package com.peluqueria.controler;

import com.peluqueria.entity.Estudiante;
import com.peluqueria.repository.EstudianteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/estudiantes")
public class EstudianteController {

    @Autowired
    private EstudianteRepository estudianteRepository;

    @GetMapping
    public List<Estudiante> getAll() {
        return estudianteRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Estudiante> getById(@PathVariable float id) {
        Optional<Estudiante> estudiante = estudianteRepository.findById(id);
        return estudiante.map(e -> new ResponseEntity<>(e, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Estudiante create(@RequestBody Estudiante nuevoEstudiante) {
        return estudianteRepository.save(nuevoEstudiante);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Estudiante> update(@PathVariable float id, @RequestBody Estudiante datosEstudiante) {
        return estudianteRepository.findById(id).map(e -> {
            e.setNombre(datosEstudiante.getNombre());
            e.setEmail(datosEstudiante.getEmail());
            e.setEdad(datosEstudiante.getEdad());
            e.setCursos(datosEstudiante.getCursos());
            return new ResponseEntity<>(estudianteRepository.save(e), HttpStatus.OK);
        }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable float id) {
        estudianteRepository.deleteById(id);
    }

    @GetMapping("/busquedas/or")
    public List<Estudiante> findByNombreOrEmail(@RequestParam String nombre, @RequestParam String email) {
        return estudianteRepository.findByNombreOrEmail(nombre, email);
    }

    @GetMapping("/busquedas/and")
    public List<Estudiante> findByNombreAndEmail(@RequestParam String nombre, @RequestParam String email) {
        return estudianteRepository.findByNombreAndEmail(nombre, email);
    }

    @GetMapping("/busquedas/nativa")
    public List<Estudiante> findByQueryNativa(@RequestParam String nombre, @RequestParam int edad) {
        return estudianteRepository.buscarPorNombreYEdad(nombre, edad);
    }
}