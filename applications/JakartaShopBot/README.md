# JakartaShopBot

## Project Overview

This project demonstrates the integration of LangChain4j with Jakarta EE to create an AI-powered eCommerce chatbot. The application showcases how natural language processing can be leveraged in a Java enterprise environment to handle customer interactions and order management.


![jakartashopbot](https://github.com/user-attachments/assets/75a6e7b0-48a5-4c66-865b-761a07326940)

### Key Features:

- Natural language order processing using LangChain4j and OpenAI APIs
- Real-time chat interface with Jakarta WebSocket
- Order management through conversational AI
- Jakarta EE backend with JSF frontend
- Demonstrates Jakarta EE capabilities including CDI, JPA, and Security

### Unique Selling Point:

Unlike traditional eCommerce platforms where users navigate through UI elements to create orders, this chatbot allows customers to place, modify, and track orders using natural language commands. This approach simplifies the user experience and showcases the power of integrating AI with enterprise Java technologies.

## Technology Stack:

- Jakarta EE 10
- LangChain4j
- OpenAI APIs
- Jakarta Faces (JSF)
- Jakarta Persistence (JPA)
- Jakarta WebSocket
- H2 Database (for development)

This project serves as a proof of concept for building intelligent, conversational interfaces in enterprise Java applications, highlighting the synergy between modern AI tools and established Java EE frameworks.

## Build and Run

### Prerequisites:
- Java 17
- Maven
- GlassFish Server
- OpenAI API Key

### Build Steps:

1. Build the project using Maven:
    ```
    mvn clean package
    ```

2. Set up the OpenAI API Key:
- In your GlassFish server configuration, set the environment variable:
  ```
  OPENAI_API_KEY=your_api_key_here
  ```

3. Deploy the WAR file:
- Copy the generated `JakartaShopBot-1.0-SNAPSHOT.war` from the `target` directory to your GlassFish `deployments` folder.
- Or use the GlassFish Admin Console to deploy the WAR file.

4. Start the GlassFish server if it's not already running.

5. Access the application:
   Open your web browser and navigate to:

http://localhost:8080/JakartaShopBot-1.0-SNAPSHOT/chat

### Troubleshooting:

If you encounter the error described in [LangChain4j Issue #1369](https://github.com/langchain4j/langchain4j/issues/1369), follow the steps mentioned in the solution.

Please note that the application is still under development. Currently, You will find a Chatbot application which streams responses to your queries(similar to ChatGPT).
The above stated features will be added in upcoming releases.
