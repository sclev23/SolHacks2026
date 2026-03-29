# \# Stress Calendar

# \*\*Created by Chipot-lovers\*\*

# 

# A schedule builder with built-in stress tracking. Users can create and manage events across daily, weekly, and monthly calendar views, with each event assigned a stress level (1–5). The app visualizes average stress across days, weeks, and months using a color-coded system giving users a quick at-a-glance picture of their workload over time.

# 

# Data is stored persistently per browser using cookie-based user identification, so events are saved between sessions without requiring an account.

# 

# \---

# 

# \## Tech Stack

# 

# \- \*\*Backend:\*\* Java 21, Spring Boot 4, Spring Data JPA

# \- \*\*Database:\*\* PostgreSQL via Supabase

# \- \*\*Frontend:\*\* Vanilla HTML, CSS, and JavaScript

# 

# \## Architecture

# 

# The backend follows a standard layered architecture:

# 

# \- \*\*Model\*\* — defines the `Event` entity and its fields (title, date, time, location, description, stress level)

# \- \*\*Repository\*\* — handles all database queries via Spring Data JPA

# \- \*\*Service\*\* — contains business logic and validation

# \- \*\*Controller\*\* — exposes a REST API consumed by the frontend

# 

# The frontend communicates with the backend through `fetch()` calls to the REST API, rendering events dynamically as visual blocks on the schedule grid.

# 

# \---

# 

# \## Local Development

# 

# 1\. Clone the repository

# 2\. Create a project at \[supabase.com](https://supabase.com) and obtain your JDBC connection string

# 3\. Create `src/main/resources/application.properties` with the following:

# 

# ```properties

# server.port=8080

# spring.datasource.url=YOUR\_SUPABASE\_JDBC\_URL?prepareThreshold=0

# spring.datasource.username=YOUR\_USERNAME

# spring.datasource.password=YOUR\_PASSWORD

# spring.jpa.hibernate.ddl-auto=update

# ```

# 

# 4\. Run `ProjectApplication.java` from your preferred IDE

# 5\. Visit `http://localhost:8080` in your browser

# 

# > `application.properties` is excluded from version control. Never commit your credentials.

