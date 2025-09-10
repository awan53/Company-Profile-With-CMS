# 📌 Company Profile Application

Aplikasi Company Profile ini dikembangkan menggunakan **Spring Boot** dengan konsep **Public & Admin Panel**.  
Tujuannya adalah untuk memberikan informasi perusahaan secara publik dan memudahkan admin dalam mengelola konten perusahaan.

---

## 🌐 Public Features (User)
- **Company Profile View**:  
  Menampilkan informasi perusahaan (About Company dan Logo Company).
- **Kategory**:
  Menampilkan daftar kategori yang ditambahkan admin.
- **Product Showcase**: 
   Menampilkan daftar produk yang ditambahkan admin.
- **Blog/Artikel**:  
  Menampilkan artikel atau berita yang dibuat admin.
- **Contact Us**:  
  User dapat mengirimkan email ke admin melalui form kontak.

---

## 🔑 Admin Features
- **Authentication**  
  - Login sebagai Admin.  
  - Ganti password admin.  

- **Company Management**  
  - Menambahkan & mengubah data perusahaan (About Company, Logo Company).  

- **Kategori Management**  
  - CRUD data kategori perusahaan.  

- **Product Management**  
  - CRUD data produk perusahaan.  

- **Blog Management**  
  - CRUD artikel atau posting blog.  

- **Email Management**  
  - Menerima email dari user (Contact Us).  
  - Membalas email langsung dari panel admin.  

---

## 🏗️ Tech Stack
- **Backend**: Java Spring Boot  
- **Database**: SQL Server (opsional PostgreSQL untuk deployment)  
- **Frontend**: Thymeleaf (MVC)  
- **Containerization**: Docker  

---

## 🚀 Future Improvements
- Integrasi dengan API pihak ketiga (misalnya WhatsApp atau Telegram untuk notifikasi).  
- Tambahan fitur galeri foto & video perusahaan.  
- Fitur multi-user role (misalnya Editor Blog, Admin Produk, Super Admin).  
- SEO Optimization untuk halaman publik.  

---

## Running melalui docker
jalankan docker images
```
PS F:\own programming> docker images
REPOSITORY                       TAG           IMAGE ID       CREATED       SIZE
alkes                            1.2           cd630536546f   2 hours ago   400MB
alkse-app                        latest        c9ea875d4766   2 hours ago   400MB
<none>                           <none>        df9f0e09fda1   2 hours ago   400MB
alkes                            1.0           8f1d57423776   2 hours ago   400MB
alkes                            1.1           8f1d57423776   2 hours ago   400MB
mcr.microsoft.com/mssql/server   2022-latest   5236385b17be   3 weeks ago   1.63GB
```
jalankan sql server melalui cmd atau powershell
```
PS F:\own programming> docker start sqlserver
sqlserver
```
jalankan aplikasi 
```
PS F:\own programming> docker run -d -p 8080:8080 -e SPRING_DATASOURCE_URL="jdbc:sqlserver://host.docker.internal:1433;databaseName=alkes;encrypt=false;trustServerCertificate=true" -e SPRING_DATASOURCE_USERNAME=sa -e SPRING_DATASOURCE_PASSWORD="Awan2456@" alkes:1.2
517efa712c4b8dced97be613b4f38f6ec05c84a2342b2df9443895d930c97d3f
```
melihat daftar container yang sedang berjalan
```
PS F:\own programming> docker ps
CONTAINER ID   IMAGE                                        COMMAND                  CREATED          STATUS          PORTS                    NAMES
517efa712c4b   alkes:1.2                                    "java -jar app.jar"      33 seconds ago   Up 32 seconds   0.0.0.0:8080->8080/tcp   trusting_brown
3e32f94cf07c   mcr.microsoft.com/mssql/server:2022-latest   "/opt/mssql/bin/laun…"   2 hours ago      Up 2 hours      0.0.0.0:1433->1433/tcp   sqlserver
```
akses aplikasi 
Buka browser lalu akses:
```
http://localhost:8080/
```
Tampilan Login CMS
username : admin
password : admin123
<img width="1365" height="676" alt="image" src="https://github.com/user-attachments/assets/4b5a40e2-0713-431a-94a6-9283bcb46b3c" />

Tampilan awal dahsboard cms
<img width="1364" height="675" alt="image" src="https://github.com/user-attachments/assets/f0f3fed3-81db-41b3-bc55-8e5553507b23" />

Edit data about company
<img width="1360" height="264" alt="image" src="https://github.com/user-attachments/assets/199b6da1-f7f6-4624-8124-5197c01ee146" />

Tampilan Form Data About Company Profile
<img width="796" height="525" alt="image" src="https://github.com/user-attachments/assets/1c9ad946-0788-4fe0-b28f-7e44725d4012" />

Tampilan Public Company Profile
<img width="1346" height="651" alt="image" src="https://github.com/user-attachments/assets/20b38321-1753-41f3-8459-84958b63a298" />







