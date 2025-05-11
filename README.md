# Feature
- Autentikasi menggunakan JWT
- Operasi CRUD untuk entitas `Cat` (CatDTO)
- Upload dan download file

## Teknologi

- Java 17
- Spring Boot 3.x
- Spring Security
- Lombok
- JWT (JSON Web Token)
- Maven

## Prasyarat

Sebelum menjalankan aplikasi, pastikan Anda memiliki:

- JDK 17 atau lebih tinggi
- Maven 3.6+ 
- Akses ke database (misal: H2, PostgreSQL, MySQL)

## Instalasi & Konfigurasi

1. **Clone repository**
   ```bash
   git clone https://github.com/username/test.git
   ```

2. **Konfigurasi `application.properties`**
   Sesuaikan pengaturan koneksi database dan JWT di `src/main/resources/application.properties`:
   ```properties
   # Database
   spring.datasource.url=jdbc:YOUR_DB_URL
   spring.datasource.username=YOUR_DB_USER
   spring.datasource.password=YOUR_DB_PASS
   spring.jpa.hibernate.ddl-auto=update

   # JWT
   jwt.secret=YourSuperSecretKey
   jwt.expirationMs=3600000
   ```

3. **Bangun aplikasi**
   ```bash
   mvn clean install
   ```

## Menjalankan Aplikasi

```bash
mvn spring-boot:run
```

Aplikasi akan berjalan di `http://localhost:8080/` secara default.

## Dokumentasi API

Semua endpoint menggunakan prefix `/api/v1`.

### 1. Autentikasi

#### `POST /api/v1/auth/login`

- **Deskripsi**: Melakukan autentikasi dan menghasilkan JWT.
- **Request**:
  ```json
  {
    "username": "admin",
    "password": "admin"
  }
  ```
- **Response**:
  ```json
  {
    "token": "<JWT_TOKEN_HERE>"
  }
  ```

### 2. CatController (Manajemen Data Kucing)

#### `POST /api/v1/cat/insert`
- **Deskripsi**: Menambahkan data kucing baru.
- **Request**:
  ```json
  {
    "url": "https://example.com/cat.jpg",
    "width": 800,
    "height": 600
  }
  ```
- **Response**: `201 Created` dengan objek `CatDTO` yang tersimpan.

#### `GET /api/v1/cat/all`
- **Deskripsi**: Mendapatkan semua data kucing.
- **Response**: `200 OK` array `CatDTO`.

#### `GET /api/v1/cat/{id}`
- **Deskripsi**: Mendapatkan data kucing berdasarkan `id`.
- **Response**: `200 OK` objek `CatDTO` atau `404 Not Found` jika tidak ada.

#### `PUT /api/v1/cat/update`
- **Deskripsi**: Memperbarui data kucing.
- **Request**:
  ```json
  {
    "id": "<cat-id>",
    "url": "https://example.com/new-cat.jpg",
    "width": 1024,
    "height": 768
  }
  ```
- **Response**: `200 OK` objek `CatDTO` yang diperbarui.

#### `DELETE /api/v1/cat/delete/{id}`
- **Deskripsi**: Menghapus data kucing berdasarkan `id`.
- **Response**: `204 No Content`.

### 3. FileController (Manajemen File)

#### `POST /api/v1/files/upload`
- **Deskripsi**: Upload file ke server.
- **Request**: `multipart/form-data` dengan field `file`.
- **Response**: `201 Created` objek `FileDTO` berisi metadata file.

#### `GET /api/v1/files/all`
- **Deskripsi**: Mendapatkan daftar metadata semua file.
- **Response**: `200 OK` array `FileDTO`.

#### `GET /api/v1/files/{fileId}`
- **Deskripsi**: Mendownload file berdasarkan `fileId`.
- **Response**: `200 OK` stream file dengan header `Content-Disposition: attachment`.

## Struktur Proyek

```
src
├─ main
│  ├─ java/com/
│  │  ├─ controller  # REST controller
│  │  ├─ dto         # Data Transfer Object
│  │  ├─ entity      # Entitas
│  │  ├─ security    # JWT util dan konfigurasi keamanan
│  │  └─ service     # Logika bisnis
│  └─ resources
│     └─ application.properties
```

