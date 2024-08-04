# Employee Management System
Bu proje, çalışan ve maaş bilgilerini yönetmek için geliştirilmiş bir Employee Management System'dır. Uygulama, Spring Boot ile geliştirilmiş bir backend ve React ile oluşturulmuş bir frontend içerir. PostgreSQL veritabanı kullanılarak veri yönetimi sağlanır. Employee Management System iki tablodan oluşur. Ana tablo employee'nin id(primary key), firstName, lastName, email sütunları bulunur. İkinci tablo salary'nin id(primary key), salary, employee_id(foreign key -> employee tablosundaki id), startDate, finishDate sütunları bulunur. 

# Kullanılan Programlar: Intellij(Java), Dbeaver(PostgreSQL), Postman, Visual Studio Code(Git, Node.js ve react).

# Özellikler
Çalışan Yönetimi: Çalışan ekleme, güncelleme, silme ve listeleme işlevleri.
Maaş Yönetimi: Maaş ekleme, güncelleme, silme ve listeleme işlevleri.
Validasyon: Maaş başlangıç ve bitiş tarihlerinin kontrolü, bitiş tarihinin başlangıç tarihinden en az 28 gün sonra olması sağlanır.


# Backend
Backend, Spring Boot kullanılarak geliştirilmiştir ve PostgreSQL veritabanına bağlanır.

# İntellij'de Setting -> Build, Execution, Deployment -> Build Tools -> Maven. Bu kısımdan "Maven home path: Bundled (Maven 3)" seçilmelidir. Ayrıca SDK corretto-17 seçilmiştir.

# src/main/resources/application.properties'daki ayarlamalar: 
spring.datasource.url=jdbc:postgresql://localhost:5432/employee-management   -->Dbeaver'da kurulan PostgreSQL'in ayarlarındaki url.
spring.datasource.username=postgres   -->PostgreSQL superuser.
spring.datasource.password=joyalty123   -->PostgreSQL kurulurken belirlenen şifre.
spring.jpa.hibernate.ddl-auto=update
spring.sql.init.mode=always
spring.jpa.show-sql=true
spring.datasource.driver-class-name=org.postgresql.Driver
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
spring.jpa.properties.hibernate.boot.allow_jdbc_metadata_access=false
spring.jpa.open-in-view=false

# Employee Management Database'ini manuel olarak oluşturmak için girilecek komutlar (cmd):
cd C:\Program Files\PostgreSQL\14\bin
psql -U postgres
CREATE DATABASE "employee-management";

# Eğer postgres komutuna giderken şifre bilinmiyorsa:
ALTER USER postgres WITH PASSWORD 'new_password';

# API Uç Noktaları
# Çalışan API:
POST /employee: Yeni çalışan ekler.
GET /employee: Tüm çalışanları listeler.
GET /employee/{id}: Belirli bir çalışanı getirir.
PUT /employee/{id}: Mevcut bir çalışanı günceller.
DELETE /employee/{id}: Belirli bir çalışanı siler.

# Maaş API:
POST /salary: Yeni maaş ekler.
GET /salary: Tüm maaşları listeler.
GET /salary/{id}: Belirli bir maaşı getirir.
PUT /salary/{id}: Mevcut bir maaşı günceller.
DELETE /salary/{id}: Belirli bir maaşı siler.


# Frontend
Frontend, React ile geliştirilmiş olup, kullanıcı arayüzünü sağlar ve backend API'leri ile etkileşime geçer.

# React Projesini Kurma:
npx create-react-app employeemanagement-frontend
cd employeemanagement-frontend

# Employee Management Ön yüz React için indirilmesi gerekenler (VS Code Terminal):
npm i bootstrap
npm i axios
npm i react-router-dom
npm install --save-dev @babel/plugin-proposal-private-property-in-object
npm install date-fns

# VS Code Terminalde "npm start" yazarak proje ayağa kaldırılır.

# Bileşenler
App.js: Yönlendirme yapılandırması, tüm uygulama için ana bileşen.
ListEmployeeComponent: Çalışanları listeleyen bileşen.
AddEmployeeComponent: Yeni çalışan ekleyen bileşen.
UpdateEmployeeComponent: Çalışan bilgilerini güncelleyen bileşen.
ListSalaryComponent: Maaşları listeleyen bileşen.
AddSalaryComponent: Yeni maaş ekleyen bileşen.
UpdateSalaryComponent: Maaş bilgilerini güncelleyen bileşen.

# Kullanım
Backend: Uygulamayı başlatmak için mvn spring-boot:run komutunu kullanın (veya IDE'nizden çalıştırın). PostgreSQL veritabanını localhost:5432 adresinde yapılandırın.
Frontend: React uygulamasını başlatmak için npm start komutunu kullanın.
