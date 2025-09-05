# 📧 MailSender Service

The **MailSender Service** is a Spring Boot microservice that sends emails such as account verification, password reset, and notifications.  
It is designed to be used by other services (e.g., Registration Service, URL Shortener Service, etc.).

---

## 🚀 Setup Instructions

### 1️⃣ Clone the Repository
```bash
git clone https://github.com/your-username/mailsender-service.git
cd mailsender-service
2️⃣ Configure SMTP
Open src/main/resources/application.properties and add your SMTP credentials:

properties
Copy code
spring.mail.host=smtp.gmail.com
spring.mail.port=587
spring.mail.username=your_email@gmail.com
spring.mail.password=your_app_password
spring.mail.properties.mail.smtp.auth=true
spring.mail.properties.mail.smtp.starttls.enable=true
⚠️ For Gmail, use an App Password, not your normal Gmail password.

3️⃣ Build and Run
Using Maven:

bash
Copy code
mvn spring-boot:run
Or build a JAR:

bash
Copy code
mvn clean package
java -jar target/mailsender-service-0.0.1-SNAPSHOT.jar
