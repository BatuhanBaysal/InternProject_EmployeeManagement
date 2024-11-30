# Internship Project - Employee Management System 

### Full Stack Development with Java, Spring Boot, PostgreSQL, React.js 

---

- Bu proje, stajım sırasında geliştirilen bir `Tam Yığın (Full Stack)` Çalışan Yönetim Sistemidir. Proje kapsamında `Spring Boot` ve `React.js` teknolojileri kullanılarak backend ve frontend geliştirilmiştir. Veritabanı olarak `PostgreSQL`, API testi için `Postman` kullanılmıştır. 

- Stajımın ardından `Full Stack Development` alanında kendimi geliştirmeye karar verdim. Bu doğrultuda çeşitli eğitim programlarına katıldım ve bu eğitimlerden elde ettiğim bilgilerle staj sürecinde yazdığım program üzerinde ek geliştirmeler yaptım.

---

## 1. Proje Hakkında (Overview)

- **Kullanılan Teknolojiler**:
   - Backend: Java, Spring Boot
   - Frontend: React.js, JavaScript, CSS, HTML  
   - Database: PostgreSQL
   - API Testing: Postman

- **Kullanılan Programlar**:
   - Backend Development: IntelliJ IDEA
   - Frontend Development: Visual Studio Code
   - Database Management: DBeaver, pgAdmin 

- **Veritabanı Yapısı**:
   - Employee Table: Çalışan ad, soyad ve e-posta bilgilerini içerir.
   - Salary Table: Çalışan maaş bilgilerini başlangıç ve bitiş tarihleri ile birlikte tutar. Bu tablo, bir yabancı anahtar (foreign key) aracılığıyla `Employee` tablosuna bağlanır.

- **Temel Özellikler**:
   - CRUD işlemleri: Kullanıcılar hem `Employee` hem de `Salary` tablolarında CRUD işlemleri gerçekleştirebilir.
   - Kaskadlı Silme: Bir çalışan silindiğinde ilişkili maaş kayıtları da silinir.
   - Exception Handling: Uygulama boyunca hata bildirimleri sağlanır.
   - DTO ve Mapper: Karmaşıklığı azaltmak ve güvenliği artırmak için Veri Aktarım Nesneleri (DTO) ve Mapper kullanımı.

---

## 2. Teknik Bilgiler (Technical Details)

- **Backend**

   - **Mimari**: Model-View-Controller (MVC)
   - IntelliJ IDEA ile geliştirilmiş ve PostgreSQL veritabanına bağlanmıştır.
   - **Ayarlar**:
      - Maven yapılandırması: IntelliJ IDEA'de Maven ana yolu "Bundled (Maven 3)" olarak ayarlanır.
      - **Java SDK**: `corretto-17` kullanılarak geliştirilmiştir.
      - **application.properties** dosyası:

      ```application.properties
      spring.datasource.url=jdbc:postgresql://localhost:5432/employee-management
      spring.datasource.username=postgres
      spring.datasource.password=12345678
      spring.jpa.hibernate.ddl-auto=update
      spring.sql.init.mode=always
      spring.jpa.show-sql=true
      spring.datasource.driver-class-name=org.postgresql.Driver
      spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
      spring.jpa.open-in-view=false
      ```

      - **Veritabanı Oluşturma**: `pgAdmin` ile veritabanı oluşturulur.
      - **Veritabanı Komutları (Manuel oluşturmak için)**:

      ```cmd
      cd C:\Program Files\PostgreSQL\17\bin
      psql -U postgres
      CREATE DATABASE "employee-management";
      ```

      - **Not**: Eğer `psql -U postgres` komutu girildikten sonra çıkan şifre bilinmiyorsa (PostgreSQL Kurulumu sırasında superuser için belirlenen şifre):

      ```cmd
      ALTER USER postgres WITH PASSWORD 'new_password';
      ```

      - **Önemli Not**: PostgreSQL kurulumu sırasında `The Database cluster initialization failed.` hatası alınırsa indirilen tüm dosyaları bilgisayardan silin ve kurulumu yönetici olarak açıp konumu `English United State America` seçin.

---

- **Frontend**
   - React.js ile geliştirilmiştir.
   - Kullanıcı arayüzü tasarımı ve backend API'leriyle etkileşim sağlanmıştır.
   - **React.js kurulumu**:
      ```VS Code terminal
      npx create-react-app employeemanagement-frontend
      cd employeemanagement-frontend
      ```

- **Gerekli Bağımlılıklar**:
   ```VS Code terminal
   npm i bootstrap
   npm i axios
   npm i react-router-dom
   npm install --save-dev @babel/plugin-proposal-private-property-in-object
   npm install date-fns
   ```

---

## 3. Kullanım (Usage)

- **Backend**
   - Uygulamayı başlatmak için bu komut kullanılır: `mvn spring-boot:run`

- **Frontend**
   - Uygulamayı başlatmak için bu komut kullanılır: `npm start`

---

## 4. Öne Çıkan Özellikler (Key Features)

- Tam Yığın (Full Stack) geliştirme yaklaşımı.
- PostgreSQL ile güvenilir veritabanı yönetimi.
- RESTful API ve CRUD işlemleri.
- DTO ve Mapper ile temiz ve modüler kod yapısı.
- Exception Handling ile kullanıcı dostu hata yönetimi.

---

## 5. Demo ve Ekran Görüntüleri (Demo & Screenshots)

Backend kısmı Intellij IDEA'dan, Frontend kısmını Visual Studio Code programından çalıştırılabilir.

---

## 6. Katkıda Bulunanlar ve Kaynaklar (Contributors & Resources)

- **Proje Sahibi**: [Batuhan Baysal](https://www.linkedin.com/in/batuhan-baysal-502656170/)

- **Kaynaklar**:
   - Spring Boot Resmi Dokümantasyonu
   - React.js Resmi Kılavuzu
   - PostgreSQL Veritabanı Yönetimi

---

## 7. İletişim ve Destek (Contact & Support)

- **LinkedIn**: [Batuhan Baysal LinkedIn Profilim](https://www.linkedin.com/in/batuhan-baysal-502656170/)

- **Github**: [Batuhan Baysal GitHub Profilim](https://github.com/BatuhanBaysal)
