
-- Memasukkan data awal ke tabel about_us
-- Hanya satu entri yang dibutuhkan untuk data perusahaan
INSERT INTO aboutus (companyname, mission, vision, description, logo_url, contact_email, address, phone, slogan) VALUES
('PT. Alkes Medika Sejahtera',
     'Memberikan pelayanan terbaik dalam penyediaan alat kesehatan berkualitas.',
     'Menjadi perusahaan distribusi alat kesehatan terkemuka di Indonesia.',
     'PT. Alkes Medika Sejahtera berdiri sejak 2020 dan berkomitmen menghadirkan produk alat kesehatan terpercaya, aman, dan terjangkau untuk masyarakat Indonesia.',
     '/images/logo.png',
     'info@alkesmedika.co.id',
     'Jl. Raya Citeureup No.123, Bogor, Jawa Barat, Indonesia',
     '+62-812-3456-7890',
     'Sehat Bersama Kami');


INSERT INTO admin (username, password, role)
VALUES ('ADMIN', '$2a$10$9.3seaSMW4paJc2ewC4msOMEu35AASoZhhVxXFmua1LVKdv8f2.OW', 'ROLE_ADMIN');

-- Tambahkan data kategori awal
INSERT INTO category (name) VALUES ('Termometer');
INSERT INTO category (name) VALUES ('Tensimeter');

-- Memasukkan data awal ke tabel products
INSERT INTO product (name, description, price, image_url, category_id) VALUES
('Produk 1', 'Deskripsi detail untuk Produk 1.', 15000000.00, 'http://example.com/images/product1.jpg', 1),
('Produk 2', 'Deskripsi detail untuk Produk 2.', 25500000.50, 'http://example.com/images/product2.jpg', 2);



