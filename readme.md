# Deskripsi
Project ini dibuat ke dalam beberapa bagian sesuai fungsinya masing-masing. Beberapa project yang akan dibuat yaitu `oauth2-server`, `oauth2-resource-server`, dan `oauth2-client`.

## Membuat oauth2-server
Project pertama yang akan dibuat adalah `oauth2-server`, aplikasi ini bertugas melakukan autentikasi dan otorisasi terhadap penggunaan REST API. Sehingga aplikasi lain yang menggunakan oauth2, tidak perlu membuatkan fungsi autentikasi untuk masing-masing aplikasinya. Dan menyerahkan fungsi autentikasi ke oauth2-server.

Berikut ini langkah yang dilakukan untuk membuat oauth2-server:
- Buka http://start.spring.io/ untuk mempermudah inisialisasi aplikasi kita
![logo](img/img/1 - Inisialisasi Project)oauth-server.PNG
- Tambahkan library berikut di `build.gradle`

    compile('org.springframework.security.oauth:spring-security-oauth2') <br/>
    compile('org.springframework.security:spring-security-jwt')
- Tambahkan anotasi `@EnableAuthorizationServer` di kelas utama, supaya auth server dapat berfungsi.
- Flyway https://flywaydb.org/documentation/gradle/
- Bersambung ...