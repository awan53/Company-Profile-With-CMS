
-- Memasukkan data awal ke tabel about_us
-- Hanya satu entri yang dibutuhkan untuk data perusahaan
INSERT INTO aboutus (companyname, mission, vision) VALUES
('Nama Perusahaan Anda', 'Misi perusahaan Anda di sini.', 'Visi perusahaan Anda di sini.');


INSERT INTO admin (username, password, role)
VALUES ('ADMIN', '$2a$10$9.3seaSMW4paJc2ewC4msOMEu35AASoZhhVxXFmua1LVKdv8f2.OW', 'ADMIN');

-- Tambahkan data kategori awal
INSERT INTO category (name) VALUES ('Termometer');
INSERT INTO category (name) VALUES ('Tensimeter');

-- Memasukkan data awal ke tabel products
INSERT INTO product (name, description, price, image_url, category_id) VALUES
('Produk 1', 'Deskripsi detail untuk Produk 1.', 15000000.00, 'http://example.com/images/product1.jpg', 1),
('Produk 2', 'Deskripsi detail untuk Produk 2.', 25500000.50, 'http://example.com/images/product2.jpg', 2);



