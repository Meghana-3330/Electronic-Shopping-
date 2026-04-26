package com.ellison.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import javax.imageio.ImageIO;

/**
 * Utility class to generate low-resolution placeholder images for products
 */
public class ImageGenerator {
    
    // Category-specific colors
    private static final Map<String, Color> CATEGORY_COLORS = new HashMap<>();
    
    static {
        CATEGORY_COLORS.put("Mobiles", new Color(52, 152, 219));     // Blue
        CATEGORY_COLORS.put("TV", new Color(46, 204, 113));          // Green
        CATEGORY_COLORS.put("Laptops", new Color(155, 89, 182));     // Purple
        CATEGORY_COLORS.put("Camera", new Color(230, 126, 34));      // Orange
        CATEGORY_COLORS.put("Electronics", new Color(231, 76, 60));  // Red
    }
    
    /**
     * Generate a low-resolution image for a product
     * @param category Product category
     * @param productName Product name
     * @param width Image width (low resolution default: 300px)
     * @param height Image height (low resolution default: 250px)
     * @return BufferedImage
     */
    public static BufferedImage generateProductImage(String category, String productName, int width, int height) {
        // Create low-resolution image (intentionally small for low quality)
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = image.createGraphics();
        
        // Get category color or use default
        Color bgColor = CATEGORY_COLORS.getOrDefault(category, new Color(149, 165, 166));
        g2d.setColor(bgColor);
        g2d.fillRect(0, 0, width, height);
        
        // Add gradient effect
        Color lightColor = new Color(
            Math.min(bgColor.getRed() + 40, 255),
            Math.min(bgColor.getGreen() + 40, 255),
            Math.min(bgColor.getBlue() + 40, 255)
        );
        for (int i = 0; i < height / 2; i++) {
            float ratio = (float) i / (height / 2);
            int r = (int) (bgColor.getRed() + (lightColor.getRed() - bgColor.getRed()) * ratio);
            int g = (int) (bgColor.getGreen() + (lightColor.getGreen() - bgColor.getGreen()) * ratio);
            int b = (int) (bgColor.getBlue() + (lightColor.getBlue() - bgColor.getBlue()) * ratio);
            g2d.setColor(new Color(r, g, b));
            g2d.drawLine(0, i, width, i);
        }
        
        // Add category label
        g2d.setColor(Color.WHITE);
        g2d.setFont(new Font("Arial", Font.BOLD, 16));
        String categoryText = category;
        int categoryX = (width - g2d.getFontMetrics().stringWidth(categoryText)) / 2;
        int categoryY = height / 3;
        g2d.drawString(categoryText, categoryX, categoryY);
        
        // Add product name (shortened if needed)
        g2d.setColor(new Color(255, 255, 255, 200));
        g2d.setFont(new Font("Arial", Font.PLAIN, 12));
        String displayName = productName.length() > 20 ? productName.substring(0, 17) + "..." : productName;
        int nameX = (width - g2d.getFontMetrics().stringWidth(displayName)) / 2;
        int nameY = (height * 2) / 3;
        g2d.drawString(displayName, nameX, nameY);
        
        // Add decorative icon in center
        g2d.setColor(lightColor);
        int iconSize = 40;
        g2d.fillRoundRect((width - iconSize) / 2, (height - iconSize) / 2 - 20, iconSize, iconSize, 5, 5);
        
        g2d.dispose();
        return image;
    }
    
    /**
     * Save image to file
     * @param image BufferedImage to save
     * @param filename Output filename
     * @param directory Output directory
     * @return true if successful
     */
    public static boolean saveImage(BufferedImage image, String filename, String directory) {
        try {
            File dir = new File(directory);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            File outputFile = new File(dir, filename);
            ImageIO.write(image, "jpg", outputFile);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    /**
     * Generate and save images for all product types
     * @param outputDirectory Directory to save images
     */
    public static void generateAllProductImages(String outputDirectory) {
        String[] categories = {"Mobiles", "TV", "Laptops", "Camera"};
        
        for (String category : categories) {
            // Generate 2 variations per category for diversity
            for (int i = 1; i <= 2; i++) {
                String filename = category.toLowerCase() + "_" + i + ".jpg";
                String displayName = category + " Product " + i;
                BufferedImage img = generateProductImage(category, displayName, 300, 250);
                saveImage(img, filename, outputDirectory);
                System.out.println("Generated: " + filename);
            }
        }
        
        System.out.println("All product images generated successfully!");
    }
}
