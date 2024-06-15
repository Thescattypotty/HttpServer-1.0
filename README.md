# HttpServer/1.0

This repository contains a simple HTTP server implementation in Java.

## Features

- Supports HTTP methods: GET, POST, PUT, DELETE
- Serves static files with correct MIME types
- Handles various content types: HTML, text, images, PDF, etc.
- Provides basic error handling and status code responses

## Components

### HTTPRequest

Represents an incoming HTTP request, parsing method, path, and HTTP version.

### HTTPResponse

Constructs and sends HTTP responses with appropriate headers and content.

### HTTPHandler Interface

Defines the contract for handling HTTP requests based on different methods.

### FileServer

Serves static files from a specified directory, determining content types based on file extensions.

### RequestHandler

Handles incoming client connections, delegates requests to appropriate handlers (`HTTPHandler`), and manages response generation.

## Usage

To use the HTTP server:

1. Clone the repository.
2. Change The rootDirectory on HttpServer.java
3. Compile the Java classes (`javac *.java`).
4. Run the (`java HTTPServer`) .
6. The server serve on port 1202 .
