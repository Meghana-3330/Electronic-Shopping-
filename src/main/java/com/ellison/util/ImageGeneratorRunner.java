package com.ellison.util;

/**
 * Utility to generate product images on startup or on-demand
 * Run this to populate the images directory with low-resolution product images
 */
public class ImageGeneratorRunner {
    
    public static void main(String[] args) {
        String imageDirectory = "src/main/webapp/images";
        System.out.println("Starting to generate product images...");
        System.out.println("Output directory: " + imageDirectory);
        
        ImageGenerator.generateAllProductImages(imageDirectory);
        
        System.out.println("\nImage generation complete!");
        System.out.println("Generated images:");
        System.out.println("  - mobiles_1.jpg");
        System.out.println("  - mobiles_2.jpg");
        System.out.println("  - tv_1.jpg");
        System.out.println("  - tv_2.jpg");
        System.out.println("  - laptops_1.jpg");
        System.out.println("  - laptops_2.jpg");
        System.out.println("  - camera_1.jpg");
        System.out.println("  - camera_2.jpg");
        System.out.println("\nAll images are low-resolution (300x250px) placeholder images.");
    }
}
