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

-- Esto es crucial para no tener errores de tablas duplicadas
DROP TABLE IF EXISTS servicio;

-- 2. CREACIÓN DE LA TABLA SERVICIO (AÑADIENDO tipo_servicio)
CREATE TABLE servicio (
    id_servicio BIGINT AUTO_INCREMENT PRIMARY KEY,
    -- NUEVA COLUMNA: Aquí se guarda el valor del ENUM como texto
    tipo_servicio VARCHAR(50) NOT NULL,
    nombre VARCHAR(100) NOT NULL,
    descripcion TEXT,
    duracion_bloques INT NOT NULL, -- En bloques de 30 minutos
    precio DOUBLE NOT NULL
);

-- 3. INSERCIÓN DE DATOS DE PRUEBA (USANDO LOS VALORES DEL ENUM)
INSERT INTO servicio (id_servicio, tipo_servicio, nombre, descripcion, duracion_bloques, precio) VALUES
-- Tipo: CORTE
(1, 'CORTE', 'Corte Express', 'Corte de puntas rápido', 1, 10.00),
-- Tipo: MANICURA
(2, 'MANICURA', 'Manicura Completa', 'Manicura con esmaltado normal', 2, 15.00),
-- Tipo: TINTE
(3, 'TINTE', 'Tinte y Mechas', 'Aplicación de tinte y mechas completas', 4, 45.00),
-- Tipo: PEINADO
(4, 'PEINADO', 'Secado y Plancha', 'Secado con planchado de puntas', 1, 8.00);

 */
