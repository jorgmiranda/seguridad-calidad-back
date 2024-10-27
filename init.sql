-- Crear la tabla recetas
CREATE TABLE recetas (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    tipoDeCocina VARCHAR(100) NOT NULL,
    paisDeOrigen VARCHAR(100) NOT NULL,
    dificultadElaboracion VARCHAR(50) NOT NULL,
    instruccionesPreparacion TEXT NOT NULL,
    tiempoCoccion INT NOT NULL,
    urlImagen VARCHAR(255) NOT NULL,
    fechaCreacion DATE NOT NULL,
    popularidad INT CHECK (popularidad >= 1 AND popularidad <= 5)
);

-- Crear la tabla ingredientes
CREATE TABLE ingredientes (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL UNIQUE
);

-- Crear tabla de relación entre recetas e ingredientes
CREATE TABLE receta_ingredientes (
    receta_id INT NOT NULL,
    ingrediente_id INT NOT NULL,
    FOREIGN KEY (receta_id) REFERENCES recetas(id),
    FOREIGN KEY (ingrediente_id) REFERENCES ingredientes(id),
    PRIMARY KEY (receta_id, ingrediente_id)
);

-- Insertar datos en la tabla recetas
INSERT INTO recetas (nombre, tipoDeCocina, paisDeOrigen, dificultadElaboracion, instruccionesPreparacion, tiempoCoccion, urlImagen, fechaCreacion, popularidad)
VALUES
    ("Tacos al Pastor", "Mexicana", "México", "Media", "Marinar la carne, cocinarla en un trompo, servir en tortillas con piña, cebolla y cilantro.", "30 minutos", "https://comedera.com/wp-content/uploads/sites/9/2017/08/tacos-al-pastor-receta.jpg", "2023-01-10", 5),
    ("Paella", "Española", "España", "Alta", "Cocinar el arroz con el caldo y añadir los ingredientes. Cocinar a fuego lento hasta que el arroz esté tierno.", "1 hora", "https://www.nestleprofessional-latam.com/sites/default/files/styles/np_recipe_detail/public/2022-07/paella.png?itok=CBvKkcsa", "2023-02-15", 4),
    ("Sushi", "Japonesa", "Japón", "Alta", "Cocinar el arroz y mezclar con vinagre. Enrollar con alga nori y añadir los ingredientes. Cortar en piezas.", "45 minutos", "https://assets.tmecosys.com/image/upload/t_web767x639/img/recipe/ras/Assets/0749D9BC-260D-40F4-A07F-54814C4A82B4/Derivates/A73A7793-F3EE-4B90-ABA4-1CC1A0C3E18F.jpg", "2023-03-05", 5),
    ("Curry de Pollo", "India", "India", "Media", "Saltear la cebolla, añadir el pollo y el curry. Incorporar el tomate y la leche de coco, cocinar a fuego lento.", "40 minutos", "https://i.blogs.es/8c3360/pollo_curry/450_1000.jpg", "2023-04-20", 4),
    ("Pasta Carbonara", "Italiana", "Italia", "Baja", "Cocinar la pasta, mezclar con panceta dorada y añadir la mezcla de huevos y queso. Revolver y servir.", "20 minutos", "https://www.gourmet.cl/wp-content/uploads/2016/12/Carbonara-editada.jpg", "2023-05-10", 5);

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