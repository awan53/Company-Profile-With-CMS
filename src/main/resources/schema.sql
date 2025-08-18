DROP TABLE IF EXISTS product;
DROP TABLE IF EXISTS category;
DROP TABLE IF EXISTS aboutus;
DROP TABLE IF EXISTS admin;

IF NOT EXISTS (SELECT * FROM sysobjects WHERE name='category' AND xtype='U')
CREATE TABLE category(
    id BIGINT IDENTITY(1,1) PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

IF NOT EXISTS (SELECT * FROM sysobjects WHERE name='about_us' AND xtype='U')
CREATE TABLE aboutus(
    id BIGINT IDENTITY(1,1) PRIMARY KEY,
    companyname VARCHAR(255) NOT NULL,
    mission VARCHAR(MAX) NOT NULL,
    vision VARCHAR(MAX) NOT NULL
);

IF NOT EXISTS (SELECT * FROM sysobjects WHERE name='product' AND xtype='U')
CREATE TABLE product(
    id BIGINT IDENTITY(1,1) PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
     description VARCHAR(MAX) NOT NULL,
     price DECIMAL(19, 2) NOT NULL,
     image_url VARCHAR(255) NOT NULL,
     category_id BIGINT, -- Kolom foreign key yang baru
     CONSTRAINT fk_product_category FOREIGN KEY (category_id) REFERENCES category(id)
);

IF NOT EXISTS (SELECT * FROM sysobjects WHERE name='admin' AND xtype='U')
CREATE TABLE admin (
    id BIGINT IDENTITY(1,1) PRIMARY KEY,
    username VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(50) NOT NULL
);

