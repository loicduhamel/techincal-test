# Technical test

## Installation guide

### Requirement
- [Node.js](https://nodejs.org/fr/download/package-manager)
- OR [NVM](https://github.com/nvm-sh/nvm?tab=readme-ov-file#installing-and-updating)
- Java 8
- Maven

After installation of NVM:
- `nvm install v20.0.0`
- `nvm use v20.0.0`

### Installation
- Clone the project `git clone https://github.com/loicduhamel/techincal-test.git`
- Open a terminal and go to the root of the project `cd tests-main`
- Assert you have Java 8 `java -version`
- Assert you have node `node -v`

## How to use
- Open a terminal and go to the root of the project `cd tests-main`
- To start the project, use the command: `./start.sh`
- The frontend is on the address http://localhost:4200/
- The frontend is on the address http://localhost:8080/



## Description:

This exercise involves building a music collection management system with a Spring Boot backend and a VueJS frontend. The system will read JSON files containing information about music albums and users.


### Backend (Java)
#### Required Tasks

- Use the TODO statements in the Java code to complete missing features.
- Ensure that all unit tests pass. You can add additional tests if necessary.

#### Code Requirements

- Write clear, well-formatted, and properly indented code following industry standards.
- Make use of Spring Boot and Maven libraries as required.
- Follow best practices when structuring the code.

```java
public class AlbumController {
    // ...
}
```

 
### Frontend (VueJS)
#### Required Tasks

- Create a dynamic web interface with VueJS to present the collected data.
- Build a single-page application that makes asynchronous requests to the backend endpoints.

#### Code Requirements

- Organize the code neatly and clearly, ensuring proper separation between components and views.
- Utilize VueJS frameworks and plugins appropriately.
- Adhere to Web development conventions, such as valid HTML/CSS and semantic markup.

```js
<template>
    <div>
        <!-- Vue components go here -->
    </div>
</template>

<script setup lang="ts">
import { ref } from 'vue';
const albums = ref([]);
// ...
</script>
```


### Important Note

    Pay close attention to formatting rules for both the Java and JavaScript code blocks to ensure legibility and ease of maintenance.
