# Ellison Electronics - E-Commerce Web Application

This is a full-stack DevOps-enabled E-Commerce application built using Java Servlets, JSP, JDBC, and MySQL. It features user authentication, a shopping cart, checkout, order tracking, and an admin dashboard.

## Prerequisites
- Java JDK 11+
- Apache Maven 3.x
- MySQL 8.x
- Docker & Docker Compose (optional, for containerized run)
- Kubernetes & Jenkins (optional, for CI/CD)

## Local Setup Instructions

1. **Database Setup**
   - Open your MySQL client or command line.
   - Run the provided `database.sql` script to create the `shoppingcart` database and tables, and insert dummy data.
   - Default credentials in `DBUtil.java` are set to `root` / `password`. Update these if your local MySQL setup differs.

2. **Email Configuration (Optional)**
   - To enable email notifications, update `EmailUtil.java` with your actual Gmail address and App Password.

3. **Build the Application**
   - Open a terminal in the project root directory.
   - Run `mvn clean install`.
   - This will generate a `shopping.war` file in the `target/` directory.

4. **Deploy to Tomcat**
   - Copy the `target/shopping.war` file to your Tomcat `webapps` directory.
   - Rename it to `ROOT.war` if you want it accessible at `http://localhost:8080/`. Otherwise, it will be at `http://localhost:8080/shopping/`.
   - Start your Tomcat server.

## Docker Setup (Easiest Way)

You can run both the database and the application using Docker Compose.

1. Make sure Docker is running.
2. Run the following command in the project root:
   ```bash
   docker-compose up -d
   ```
3. The database will initialize using `database.sql`.
4. The Tomcat server will start and deploy the application.
5. Access the app at `http://localhost:8080/`.

## Access Details

- **Admin Login:**
  - Email: `admin@ellison.com`
  - Password: `admin123`
- **Customer:** Register a new account from the web UI.

## DevOps Pipeline

- **Dockerfile**: Packages the built `.war` into a Tomcat 9 container.
- **docker-compose.yml**: Spins up MySQL and the Tomcat App container together.
- **Jenkinsfile**: Defines a CI/CD pipeline (Checkout -> Build -> SonarQube -> Docker Build -> Docker Push -> K8s Deploy).
- **k8s/**: Contains Kubernetes manifests for Deployment and Service.
