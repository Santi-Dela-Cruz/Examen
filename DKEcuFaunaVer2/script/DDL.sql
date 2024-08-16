-- database: ../DataBase/EcuFauna.sqlite
DROP TABLE IF EXISTS Hormiga;

DROP TABLE IF EXISTS CatalogoGeografia;

DROP TABLE IF EXISTS CatalogoAlimento;

DROP TABLE IF EXISTS CatalogoTipoGeografia;

DROP TABLE IF EXISTS CatalogoTipoAlimento;

CREATE TABLE
    CatalogoTipoAlimento (
        idCatalogoTipoAl INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
        nombre VARCHAR(50) NOT NULL UNIQUE,
        descripcion VARCHAR(100) NOT NULL,
        estado VARCHAR(1) NOT NULL DEFAULT 'A' CHECK (Estado IN ('A', 'X')),
        FechaCreacion DATETIME DEFAULT (CURRENT_TIMESTAMP)
    );

CREATE TABLE
    CatalogoAlimento (
        idCatalogoAl INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
        idCatalogoTipoAl INTEGER NOT NULL REFERENCES CatalogoTipoAlimento (idCatalogoTipoAl),
        nombre VARCHAR(50) NOT NULL UNIQUE,
        descripcion VARCHAR(100) NOT NULL,
        estado VARCHAR(1) NOT NULL DEFAULT 'A' CHECK (Estado IN ('A', 'X')),
        FechaCreacion DATETIME DEFAULT (CURRENT_TIMESTAMP)
    );

CREATE TABLE
    CatalogoTipoGeografia (
        idCatalogoTipoGeo INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
        nombre VARCHAR(50) NOT NULL UNIQUE,
        descripcion VARCHAR(100) NOT NULL,
        estado VARCHAR(1) NOT NULL DEFAULT 'A' CHECK (Estado IN ('A', 'X')),
        FechaCreacion DATETIME DEFAULT (CURRENT_TIMESTAMP)
    );

CREATE TABLE
    CatalogoGeografia (
        idCatalogoGeo INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
        idCatalogoTipoGeo INTEGER NOT NULL REFERENCES CatalogoTipoGeografia (idCatalogoTipoGeo),
        idRegion INTEGER REFERENCES CatalogoGeografia (idCatalogoGeo),
        nombre VARCHAR(50) NOT NULL UNIQUE,
        descripcion VARCHAR(100) NOT NULL,
        estado VARCHAR(1) NOT NULL DEFAULT 'A' CHECK (Estado IN ('A', 'X')),
        FechaCreacion DATETIME DEFAULT (CURRENT_TIMESTAMP)
    );

CREATE TABLE
    Hormiga (
        idHormiga INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
        idSexo INTEGER NOT NULL REFERENCES CatalogoAlimento (idCatalogoAl),
        idGenoAlimento INTEGER NOT NULL REFERENCES CatalogoAlimento (idCatalogoAl),
        idIngestaNativa INTEGER NOT NULL REFERENCES CatalogoAlimento (idCatalogoAl),
        idProvincia INTEGER NOT NULL REFERENCES CatalogoGeografia (idCatalogoGeo),
        tipoHormiga VARCHAR(50) NOT NULL,
        nombre VARCHAR(50) NOT NULL UNIQUE,
        porcentajeEvolucion INTEGER DEFAULT 0,
        estado VARCHAR(1) NOT NULL DEFAULT 'A' CHECK (estado IN ('A', 'X')),
        FechaCreacion DATETIME DEFAULT (CURRENT_TIMESTAMP)
    );

CREATE TABLE
    CatalogoEvoluciones (
        idEvolucion INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
        nombreEvolucion VARCHAR(50) NOT NULL UNIQUE,
        descripcion VARCHAR(100) NOT NULL
    );
