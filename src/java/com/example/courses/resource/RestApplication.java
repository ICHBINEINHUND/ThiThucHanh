package com.example.courses.resource;

import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

/**
 * JAX-RS Application configuration
 * Sets the base path for all REST endpoints to /api
 */
@ApplicationPath("/api")
public class RestApplication extends Application {
    // No additional configuration needed
    // Container will automatically discover REST resources
}
