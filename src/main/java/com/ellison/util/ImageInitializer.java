package com.ellison.util;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import java.io.File;

/**
 * Application startup listener that generates product images on application startup
 */
@WebListener
public class ImageInitializer implements ServletContextListener {
    
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        String realPath = sce.getServletContext().getRealPath("images");
        
        System.out.println("[ImageInitializer] Checking product images...");
        System.out.println("[ImageInitializer] Image directory: " + realPath);
        
        // Check if images already exist
        File imageDir = new File(realPath);
        if (!imageDir.exists()) {
            imageDir.mkdirs();
        }
        
        // Check if any product images exist
        boolean hasImages = false;
        if (imageDir.listFiles() != null) {
            for (File file : imageDir.listFiles()) {
                if (file.getName().matches("(mobiles|tv|laptops|camera)_\\d+\\.jpg")) {
                    hasImages = true;
                    break;
                }
            }
        }
        
        if (!hasImages) {
            System.out.println("[ImageInitializer] No product images found. Generating low-resolution images...");
            ImageGenerator.generateAllProductImages(realPath);
            System.out.println("[ImageInitializer] Product images generated successfully!");
        } else {
            System.out.println("[ImageInitializer] Product images already exist. Skipping generation.");
        }
    }
    
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("[ImageInitializer] Application shutting down.");
    }
}
