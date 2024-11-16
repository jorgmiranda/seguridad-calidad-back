
 GRANT ALL PRIVILEGES ON * . * TO 'root'@'localhost';

-- CREATE USER 'myuser'@'%' IDENTIFIED BY 'password';

 GRANT ALL PRIVILEGES ON *.* TO 'myuser'@'%' WITH GRANT OPTION;


-- Crear tabla usuarios
CREATE TABLE usuarios(
    id         BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre     VARCHAR(80) NOT NULL,
    correo     VARCHAR(80) NOT NULL,
    contrasena VARCHAR(16) NOT NULL
);

-- Crear la tabla recetas
CREATE TABLE recetas (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    tipo_de_cocina VARCHAR(100) NOT NULL,
    pais_de_origen VARCHAR(100) NOT NULL,
    dificultad_elaboracion VARCHAR(50) NOT NULL,
    instrucciones_preparacion TEXT NOT NULL,
    tiempo_coccion INT NOT NULL,
    url_imagen VARCHAR(255) NOT NULL,
    fecha_creacion DATE NOT NULL,
    popularidad INT CHECK (popularidad >= 1 AND popularidad <= 5)
);

-- Crear la tabla ingredientes
CREATE TABLE ingredientes (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL UNIQUE
);

-- Crear tabla de relación entre recetas e ingredientes
CREATE TABLE receta_ingredientes (
    receta_id BIGINT NOT NULL,
    ingrediente_id BIGINT NOT NULL,
    FOREIGN KEY (receta_id) REFERENCES recetas(id),
    FOREIGN KEY (ingrediente_id) REFERENCES ingredientes(id),
    PRIMARY KEY (receta_id, ingrediente_id)
);

-- inserta datos en la tabla usuarios
INSERT INTO usuarios (nombre, correo, contrasena)
VALUES
    ("Malenia Blade of Miquella", "malenia@gmail.com", "123456"),
    ("Starscourge Radahn", "radahn@gmail.com", "123456");
-- Insertar datos en la tabla recetas
INSERT INTO recetas (nombre, tipo_de_cocina, pais_de_origen, dificultad_elaboracion, instrucciones_preparacion, tiempo_coccion, url_imagen, fecha_creacion, popularidad)
VALUES
    ("Tacos al Pastor", "Mexicana", "México", "Media", "Marinar la carne, cocinarla en un trompo, servir en tortillas con piña, cebolla y cilantro.", 30, "/images/recetas/tacos-al-pastor-receta.jpg", "2023-01-10", 5),
    ("Paella", "Española", "España", "Alta", "Cocinar el arroz con el caldo y añadir los ingredientes. Cocinar a fuego lento hasta que el arroz esté tierno.", 60, "/images/recetas/paella.jpg", "2023-02-15", 4),
    ("Sushi", "Japonesa", "Japón", "Alta", "Cocinar el arroz y mezclar con vinagre. Enrollar con alga nori y añadir los ingredientes. Cortar en piezas.", 45, "/images/recetas/sushi.jpg", "2023-03-05", 5),
    ("Curry de Pollo", "India", "India", "Media", "Saltear la cebolla, añadir el pollo y el curry. Incorporar el tomate y la leche de coco, cocinar a fuego lento.", 40, "/images/recetas/curry-de-pollo.jpg", "2023-04-20", 4),
    ("Pasta Carbonara", "Italiana", "Italia", "Baja", "Cocinar la pasta, mezclar con panceta dorada y añadir la mezcla de huevos y queso. Revolver y servir.", 20, "/images/recetas/Carbonara-editada.jpg", "2023-05-10", 5);

-- Insertar ingredientes
INSERT INTO ingredientes (nombre) VALUES
    ("Tortillas de maíz"),
    ("Carne de cerdo marinado"),
    ("Piña"),
    ("Cebolla"),
    ("Cilantro"),
    ("Salsa"),
    ("Arroz"),
    ("Mariscos"),
    ("Pollo"),
    ("Pimiento rojo"),
    ("Azafrán"),
    ("Caldo de pollo"),
    ("Alga nori"),
    ("Pescado crudo"),
    ("Aguacate"),
    ("Vinagre"),
    ("Salsa de soja"),
    ("Pechuga de pollo"),
    ("Tomate"),
    ("Curry en polvo"),
    ("Leche de coco"),
    ("Espagueti"),
    ("Huevos"),
    ("Panceta"),
    ("Queso parmesano"),
    ("Pimienta negra"),
    ("Sal");

-- Insertar la relación entre recetas e ingredientes
INSERT INTO receta_ingredientes (receta_id, ingrediente_id) VALUES
    (1, 1), -- Tacos al Pastor
    (1, 2),
    (1, 3),
    (1, 4),
    (1, 5),
    (1, 6),
    (2, 7), -- Paella
    (2, 8),
    (2, 9),
    (2, 10),
    (2, 11),
    (3, 12), -- Sushi
    (3, 13),
    (3, 14),
    (3, 15),
    (3, 16),
    (4, 17), -- Curry de Pollo
    (4, 18),
    (4, 19),
    (4, 20),
    (4, 21),
    (5, 22), -- Pasta Carbonara
    (5, 23),
    (5, 24),
    (5, 25),
    (5, 26);
