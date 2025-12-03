# Web‑Shop Project

![GitHub repo size](https://img.shields.io/github/repo-size/julia-shtal/web-shop-project-25)
![GitHub issues](https://img.shields.io/github/issues/julia-shtal/web-shop-project-25)
![GitHub license](https://img.shields.io/badge/license-MIT-green)
![Tech Stack](https://img.shields.io/badge/Tech%20Stack-Java%20%7C%20Svelte%20%7C%20HTML%20%7C%20CSS-blue)

A full-stack web shop application developed as part of the **Distributed Applications** course at Hochschule Fulda. The project demonstrates a distributed architecture using a Java backend and a Svelte-based frontend.

## Features

* User authentication (registration & login)
* Product catalog with inventory data
* Shopping cart functionality
* RESTful backend and dynamic frontend
* JSON-based sample data for products, users, and inventory

## Tech Stack

| Layer    | Technology                                             |
| -------- | ------------------------------------------------------ |
| Frontend | Svelte, HTML, CSS                                      |
| Backend  | Java (Spring Boot or similar), Maven                   |
| Data     | JSON files (products.json, users.json, inventory.json) |

## Project Structure

```
/ (project-root)
├── my-svelte-frontend/       # Svelte frontend code
├── src/                      # Java backend source code
├── products.json             # Sample product data
├── users.json                # Sample user data
├── inventory.json            # Inventory data
├── pom.xml                   # Maven configuration
├── mvnw, mvnw.cmd            # Maven wrapper
└── README.md
```

## ⚙Installation & Setup

### Prerequisites

* Java JDK 11 or higher
* Node.js & npm
* Maven (or use mvnw wrapper)

### Backend Setup

```bash
cd <project-root>
./mvnw clean install
./mvnw spring-boot:run
```

### Frontend Setup

```bash
cd my-svelte-frontend
npm install
npm run dev
```

### Access the Application

* Frontend: `http://localhost:3000`
* Backend: `http://localhost:8080` (default)

## Future Enhancements

* Database integration (PostgreSQL, MySQL, MongoDB)
* JWT-based authentication
* Docker & Docker Compose for containerized deployment
* Improved UI design & responsiveness
* Unit & integration tests

## License

This project is licensed under the **MIT License**. Feel free to use and modify it.

## Author

* **Julia Shtal** — Developer & Maintainer
* GitHub: @julia-shtal

## Contributing

Contributions are welcome! To contribute:

1. Fork this repository
2. Create a new branch (`feature/new-feature`)
3. Commit your changes
4. Submit a pull request

---

Feel free to modify this README to match specific project details.
