package com.example.courses;

import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

@ApplicationPath("/api")
public class ApplicationConfig extends Application {
    // JAX-RS will auto-discover all @Path annotated classes
}
