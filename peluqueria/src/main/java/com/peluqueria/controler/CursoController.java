package com.peluqueria.controler;

import com.peluqueria.entity.Curso;
import com.peluqueria.repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cursos")
public class CursoController {

    @Autowired
    private CursoRepository cursoRepo;

    @GetMapping
    public List<Curso> findAll() {
        return cursoRepo.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Curso> getById(@PathVariable float id) {
        Optional<Curso> curso = cursoRepo.findById(id);
        return curso.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Curso create(@RequestBody Curso nuevoCurso) {
        return cursoRepo.save(nuevoCurso);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Curso> update(@PathVariable float id, @RequestBody Curso detallesCurso) {
        return cursoRepo.findById(id).map(c -> {
            c.setTitulo(detallesCurso.getTitulo());
            c.setDescripcion(detallesCurso.getDescripcion());
            c.setDuracionHoras(detallesCurso.getDuracionHoras());
            c.setEstudiantes(detallesCurso.getEstudiantes());
            return new ResponseEntity<>(cursoRepo.save(c), HttpStatus.OK);
        }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable float id) {
        cursoRepo.deleteById(id);
    }

    @GetMapping("/busquedas/or")
    public List<Curso> findByTituloOrDescripcion(@RequestParam String titulo, @RequestParam String descripcion) {
        return cursoRepo.findByTituloOrDescripcion(titulo, descripcion);
    }

    @GetMapping("/busquedas/and")
    public List<Curso> findByTituloAndDescripcion(@RequestParam String titulo, @RequestParam String descripcion) {
        return cursoRepo.findByTituloAndDescripcion(titulo, descripcion);
    }

    @GetMapping("/busquedas/nativa")
    public List<Curso> findByQueryNativa(@RequestParam String desc, @RequestParam int horas) {
        return cursoRepo.buscarPorDescripcionYDuracion(desc, horas);
    }
}