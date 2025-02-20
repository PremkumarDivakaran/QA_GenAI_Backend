# QA-GenAI-Backend

This project is a **Spring Boot backend service** designed to support the **QA-GenAI-UI** application. It provides APIs for QA professionals to utilize **LLM (Large Language Models) to solve problems**. This backend service is **dependent on the UI project**, which is hosted separately on GitHub:
👉 [QA-GenAI-UI](https://github.com/PremkumarDivakaran/QA_GenAI_UI)

---
## **Getting Started**

### **1. Run Locally (Without Docker)**

#### **Prerequisites**
- Install **Java 17** ([Download here](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html))
- Ensure **Maven** is installed and working ([Download here](https://maven.apache.org/download.cgi))
- Install an **IDE** (e.g., IntelliJ IDEA, Eclipse, or VS Code)
- Ensure the **QA-GenAI-UI** project is set up and running ([QA-GenAI-UI](https://github.com/PremkumarDivakaran/QA_GenAI_UI))

#### **Steps**
1. **Open the project** in your IDE or file explorer.
2. **Navigate to the project folder** that contains `pom.xml`.
3. **Open a terminal** in that folder and run the following command:

   ```sh
   mvn spring-boot:run  # Start the Spring Boot application
   ```

4. The backend should now be running at **http://localhost:8080**.
5. Ensure the **QA-GenAI-UI** is running and can connect to this backend.

---

### **2. Run with Docker**

#### **Prerequisites**
- Install [Docker](https://www.docker.com/)
- Ensure the **QA-GenAI-UI** project is set up and running ([QA-GenAI-UI](https://github.com/PremkumarDivakaran/QA_GenAI_UI))

#### **Steps**
1. **Open the project** in any editor or terminal.
2. **Run the following commands:**

   ```sh
   docker build -t qa-genai-backend .  # Build the Docker image
   docker run -d --name genai-bk -p 8080:8080 qa-genai-backend  # Run the container in detached mode
   ```

3. The backend should now be accessible at **http://localhost:8080**.
4. Ensure the **QA-GenAI-UI** is running and can communicate with this backend.

---

### **3. Useful Docker Commands**

#### **Check Running Containers**
```sh
docker ps
```

#### **Stop the Running Container**
```sh
docker stop genai-bk
```

#### **Restart the Container**
```sh
docker start genai-bk
```

#### **Remove the Container**
```sh
docker rm genai-bk
```

#### **Remove the Docker Image**
```sh
docker rmi qa-genai-backend
```

---

### **Frontend Repository**
The UI application for this backend service is hosted at:
👉 [QA-GenAI-UI](https://github.com/PremkumarDivakaran/QA_GenAI_UI)

Make sure the UI is running for proper functionality.

---
