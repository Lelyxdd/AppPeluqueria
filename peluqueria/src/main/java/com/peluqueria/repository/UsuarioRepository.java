package com.peluqueria.repository;

import com.peluqueria.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    // 1. Método para buscar por dos atributos con OR
    List<Usuario> findByNombreContainingOrApellidosContaining(String nombre, String apellidos);

    // 2. Método para buscar por dos atributos con AND
    List<Usuario> findByRolAndApellidosContaining(Rol rol, String apellidos);

    // 3. Método nativo para buscar usuarios (LIKE % % y >)
    // Buscamos usuarios cuyo nombre contenga el texto dado (%) O cuyo ID sea mayor a un valor dado (>)
    @Query(value = "SELECT * FROM usuario u WHERE u.nombre LIKE %:nombreParcial% OR u.id_usuario > :minId", nativeQuery = true)
    List<Usuario> buscarPorNombreParcialOIdMayor(@Param("nombreParcial") String nombreParcial, @Param("minId") Long minId);
}
