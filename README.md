# Employee Management System

## Proje Açıklaması

Bu proje, çalışan ve maaş bilgilerini yönetmek için geliştirilmiş bir Employee Management System'dır. Uygulama, Spring Boot ile geliştirilmiş bir backend ve React ile oluşturulmuş bir frontend içerir. PostgreSQL veritabanı kullanılarak veri yönetimi sağlanır.

## Veritabanı Yapısı

Employee Management System iki tablodan oluşur:

- **Employee** tablosu:
  - `id`: (int, primary key) Çalışanın benzersiz kimliği.
  - `firstName`: (string) Çalışanın adı.
  - `lastName`: (string) Çalışanın soyadı.
  - `email`: (string) Çalışanın e-posta adresi.

- **Salary** tablosu:
  - `id`: (int, primary key) Maaş kaydının benzersiz kimliği.
  - `salary`: (long) Maaş miktarı.
  - `employee_id`: (int, foreign key) Çalışan tablosundaki id.
  - `startDate`: (date) Maaşın başladığı tarih.
  - `finishDate`: (date) Maaşın sona erdiği tarih.


## Kullanılan Programlar

- **IntelliJ IDEA**: Java geliştirme ortamı.
- **DBeaver**: PostgreSQL veritabanı yönetim aracı.
- **Postman**: API test aracı.
- **Visual Studio Code**: Git, Node.js ve React geliştirme ortamı.

# Özellikler

## Çalışan Yönetimi

- Çalışan ekleme
- Çalışan güncelleme
- Çalışan silme
- Çalışan listeleme

## Maaş Yönetimi

- Maaş ekleme
- Maaş güncelleme
- Maaş silme
- Maaş listeleme

## Validasyon

- Maaş başlangıç ve bitiş tarihlerinin kontrolü
- Bitiş tarihinin, başlangıç tarihinden en az 28 gün sonra olması sağlanır.



# Backend

## Genel Bilgi

Backend, Spring Boot kullanılarak geliştirilmiştir ve PostgreSQL veritabanına bağlanır.

## IntelliJ IDEA Ayarları

Backend geliştirmesi için IntelliJ IDEA kullanılırken aşağıdaki ayarların yapılması gerekmektedir:

1. **Maven Ayarları**:
   - IntelliJ IDEA'de `Settings` (Ayarlar) -> `Build, Execution, Deployment` (İnşa, Çalıştırma, Dağıtım) -> `Build Tools` (İnşa Araçları) -> `Maven` (Maven) bölümüne gidin.
   - "Maven home path" (Maven ana yolunu) `Bundled (Maven 3)` olarak seçin.

2. **SDK Seçimi**:
   - Java SDK olarak `corretto-17` seçilmelidir.


# src/main/resources/application.properties'daki ayarlamalar: 
   - spring.datasource.url=jdbc:postgresql://localhost:5432/employee-management   -->Dbeaver'da kurulan PostgreSQL'in ayarlarındaki url.
   - spring.datasource.username=postgres   -->PostgreSQL superuser.
   - spring.datasource.password=joyalty123   -->PostgreSQL kurulurken belirlenen şifre.
   - spring.jpa.hibernate.ddl-auto=update
   - spring.sql.init.mode=always
   - spring.jpa.show-sql=true
   - spring.datasource.driver-class-name=org.postgresql.Driver
   - spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
   - spring.jpa.properties.hibernate.boot.allow_jdbc_metadata_access=false
   - spring.jpa.open-in-view=false

# Employee Management Database'ini manuel olarak oluşturmak için girilecek komutlar (cmd):
   - cd C:\Program Files\PostgreSQL\14\bin
   - psql -U postgres
   - CREATE DATABASE "employee-management";

# Eğer postgres komutuna giderken şifre bilinmiyorsa:
   - ALTER USER postgres WITH PASSWORD 'new_password';

# API Uç Noktaları

## Çalışan API:
- **POST /employee**: Yeni çalışan ekler.
- **GET /employee**: Tüm çalışanları listeler.
- **GET /employee/{id}**: Belirli bir çalışanı getirir.
- **PUT /employee/{id}**: Mevcut bir çalışanı günceller.
- **DELETE /employee/{id}**: Belirli bir çalışanı siler.

## Maaş API:
- **POST /salary**: Yeni maaş ekler.
- **GET /salary**: Tüm maaşları listeler.
- **GET /salary/{id}**: Belirli bir maaşı getirir.
- **PUT /salary/{id}**: Mevcut bir maaşı günceller.
- **DELETE /salary/{id}**: Belirli bir maaşı siler.

# Frontend

## Genel Bakış
Frontend, React ile geliştirilmiş olup, kullanıcı arayüzünü sağlar ve backend API'leri ile etkileşime geçer.

## React Projesini Kurma
   - npx create-react-app employeemanagement-frontend
   - cd employeemanagement-frontend


# Employee Management Ön Yüz React İçin İndirilmesi Gerekenler

## Gerekli Paketlerin İndirilmesi
VS Code Terminalinde aşağıdaki komutları çalıştırın:
   - npm i bootstrap
   - npm i axios
   - npm i react-router-dom
   - npm install --save-dev @babel/plugin-proposal-private-property-in-object
   - npm install date-fns

# Bileşenler

## App.js
Yönlendirme yapılandırmasını ve tüm uygulama için ana bileşeni içerir.

## ListEmployeeComponent
Çalışanları listeleyen bileşendir.

## AddEmployeeComponent
Yeni bir çalışan ekleyen bileşendir.

## UpdateEmployeeComponent
Çalışan bilgilerini güncelleyen bileşendir.

## ListSalaryComponent
Maaşları listeleyen bileşendir.

## AddSalaryComponent
Yeni bir maaş ekleyen bileşendir.

## UpdateSalaryComponent
Maaş bilgilerini güncelleyen bileşendir.

# Kullanım

## Backend
Uygulamayı başlatmak için aşağıdaki komutu kullanın:

mvn spring-boot:run

npm start
