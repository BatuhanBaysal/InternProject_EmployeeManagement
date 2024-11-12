## Internship Project - Employee Management System (Full Stack Development with Spring Boot)
- Bu proje, stajım sırasında geliştirilen bir Tam Yığın(Full Stack) Çalışan Yönetim Sistemidir. Arka uç, `Spring Boot` ile `Java`, veritabanı için `PostgreSQL` (pgAdmin kullanılarak oluşturulmuştur) ve veritabanı yönetimi için `DBeaver` kullanılarak oluşturulmuştur. Ön uç `React.js`, `HTML`, `CSS` ve `JavaScript` ile geliştirilmiştir. API testi için `Postman` kullanılmıştır.
- Stajımın ardından Full Stack Development alanında kendimi geliştirmeye karar verdim. Bu doğrultuda çeşitli eğitim programlarına katıldım ve bu eğitmlerden elde ettiğim bilgilerle staj sürecinde yazdığım program üzerinde ek geliştirmeler yaptım.

### Genel Bakış

#### Kullanılan Teknolojiler:
- **Backend**: Java, Spring Boot
- **Frontend**: React.js, HTML, CSS, JavaScript
- **Database**: PostgreSQL
- **API Testing**: Postman

#### Kullanılan Programlar:
- **Backend Development**: IntelliJ IDEA
- **Frontend Development**: Visual Studio Code
- **Database Management**: pgAdmin, DBeaver

#### Veritabanı Yapısı:
- **Employee Table**: Ad, soyad ve e-posta gibi çalışan ayrıntılarını içerir.
- **Salary Table**: Başlangıç ve bitiş tarihleriyle birlikte maaş bilgilerini içerir. Bu tablo, bir yabancı anahtar aracılığıyla **Employee** tablosuna bağlanır.

#### Temel Özellikler:
- Kullanıcılar hem **Employee** hem de **Salary** tablolarında CRUD işlemleri gerçekleştirebilir.
- Bir çalışan silindiğinde, ilişkili maaş kayıtları da silinir.
- `Exception handling`, uygulama boyunca hata bildirimleri sağlamak için uygulanmaktadır.
- Karmaşıklığı azaltmak ve güvenliği sağlamak için **DTO** (Veri Aktarım Nesneleri) ve **Mapper** kullanılır.

### Backend (Arka uç)
Backend, `Spring Boot` kullanılarak `IntelliJ IDEA` ile geliştirilmiş olup, veritabanı `pgAdmin` ile oluşturulmuş ve `PostgreSQL` ile erişim sağlanmıştır.

#### IntelliJ IDEA Ayarları
Backend geliştirmesi için `IntelliJ IDEA` kullanılırken aşağıdaki ayarların yapılması gerekmektedir:

1. **Maven Ayarları**:
   - IntelliJ IDEA'de `Settings` (Ayarlar) -> `Build, Execution, Deployment` (İnşa, Çalıştırma, Dağıtım) -> `Build Tools` (İnşa Araçları) -> `Maven` (Maven) bölümüne gidin.
   - "Maven home path" (Maven ana yolunu) `Bundled (Maven 3)` olarak seçin.

2. **SDK Seçimi**:
   - Java SDK olarak `corretto-17` seçilmelidir.
   - Projede `Java 17` kullanılmıştır.

#### `application.properties` ayarlamaları: 
   - spring.datasource.url=jdbc:postgresql://localhost:5432/employee-management --> Dbeaver programında oluşturulan PostgreSQL veritabanındaki `Edit Connection` kısmındaki url.
   - spring.datasource.username=postgres --> PostgreSQL superuser.
   - spring.datasource.password=joyalty123 --> PostgreSQL `Edit Connection` kısmındaki belirlenen şifre.
   - spring.jpa.hibernate.ddl-auto=update
   - spring.sql.init.mode=always
   - spring.jpa.show-sql=true
   - spring.datasource.driver-class-name=org.postgresql.Driver
   - spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
   - spring.jpa.properties.hibernate.boot.allow_jdbc_metadata_access=false
   - spring.jpa.open-in-view=false

#### Bu kısım `pgAdmin` programı kullanılarak Database oluşturulur. Eğer komut ile yapacaksksanız Employee Management Database'ini manuel olarak oluşturmak için girilecek komutlar (`cmd`):
   - cd C:\Program Files\PostgreSQL\17\bin
   - psql -U postgres
   - CREATE DATABASE "employee-management";

#### Eğer postgres komutuna giderken şifre bilinmiyorsa(Dbeaver Kurulumu sırasında superuser için belirlenen şifre) (`cmd`):
   - ALTER USER postgres WITH PASSWORD 'new_password';

### Frontend
Frontend, `React.js` ile geliştirilmiş olup, kullanıcı arayüzünü sağlar ve backend API'leri ile etkileşime geçer.

#### React Projesini Kurma (`cmd`)
   - npx create-react-app employeemanagement-frontend
   - cd employeemanagement-frontend

#### Employee Management Ön Yüz React İçin İndirilmesi Gerekenler
Visual Studio Code Terminalinde aşağıdaki komutları çalıştırın:
   - npm i bootstrap
   - npm i axios
   - npm i react-router-dom
   - npm install --save-dev @babel/plugin-proposal-private-property-in-object
   - npm install date-fns

### Kullanım

#### Backend
   - Uygulamayı başlatmak için aşağıdaki komutu kullanın: mvn spring-boot:run

#### Frontend
   - Uygulamayı başlatmak için aşağıdaki komutu kullanın: npm start
