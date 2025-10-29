package com.peluqueria;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PeluqueriaApplication {

	public static void main(String[] args) {
		SpringApplication.run(PeluqueriaApplication.class, args);
	}

}


/*

-- 2. CREACIÓN DE LA TABLA SERVICIO
CREATE TABLE servicio (
    id_servicio BIGINT AUTO_INCREMENT PRIMARY KEY,
    tipo_servicio VARCHAR(50) NOT NULL, -- (CORTE, TINTE, MANICURA, etc.)
    nombre VARCHAR(100) NOT NULL,
    descripcion TEXT,
    duracion_bloques INT NOT NULL, -- En bloques de 30 minutos
    precio DOUBLE NOT NULL
);

-- 3. INSERCIÓN DE DATOS DE PRUEBA
INSERT INTO servicio (id_servicio, tipo_servicio, nombre, descripcion, duracion_bloques, precio) VALUES
(1, 'CORTE', 'Corte Express', 'Corte de puntas rápido', 1, 10.00),
(2, 'MANICURA', 'Manicura Completa', 'Manicura con esmaltado normal', 2, 15.00),
(3, 'TINTE', 'Tinte y Mechas', 'Aplicación de tinte y mechas completas', 4, 45.00);
 */
