package com.ellison.servlet;

import com.ellison.util.ImageGenerator;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Servlet for manual image generation - useful for admin purposes
 * Access via: /GenerateImagesServlet
 */
@WebServlet("/GenerateImagesServlet")
public class GenerateImagesServlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        doPost(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        try {
            String realPath = getServletContext().getRealPath("images");
            String action = request.getParameter("action");
            
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Image Generation</title>");
            out.println("<link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css\" rel=\"stylesheet\">");
            out.println("<style>");
            out.println("body { padding: 20px; background-color: #f5f5f5; }");
            out.println(".container { max-width: 600px; background: white; padding: 30px; border-radius: 10px; box-shadow: 0 0 10px rgba(0,0,0,0.1); }");
            out.println("</style>");
            out.println("</head>");
            out.println("<body>");
            out.println("<div class='container'>");
            
            if ("generate".equals(action)) {
                out.println("<h2 class='text-success'>Generating Images...</h2>");
                ImageGenerator.generateAllProductImages(realPath);
                out.println("<div class='alert alert-success mt-3'>");
                out.println("<h4>✓ Image Generation Complete!</h4>");
                out.println("<p>Generated 8 low-resolution product images (300x250px each):</p>");
                out.println("<ul>");
                out.println("<li>mobiles_1.jpg</li>");
                out.println("<li>mobiles_2.jpg</li>");
                out.println("<li>tv_1.jpg</li>");
                out.println("<li>tv_2.jpg</li>");
                out.println("<li>laptops_1.jpg</li>");
                out.println("<li>laptops_2.jpg</li>");
                out.println("<li>camera_1.jpg</li>");
                out.println("<li>camera_2.jpg</li>");
                out.println("</ul>");
                out.println("<p><strong>Location:</strong> " + realPath + "</p>");
                out.println("<p><strong>Resolution:</strong> 300x250 pixels (low resolution for faster loading)</p>");
                out.println("</div>");
            } else {
                out.println("<h2>Product Image Generator</h2>");
                out.println("<p class='text-muted'>This tool generates low-resolution placeholder images for all products.</p>");
                out.println("<form method='POST'>");
                out.println("<input type='hidden' name='action' value='generate'>");
                out.println("<button type='submit' class='btn btn-primary btn-lg'>Generate Product Images</button>");
                out.println("</form>");
                out.println("<div class='alert alert-info mt-4'>");
                out.println("<h5>What This Does:</h5>");
                out.println("<ul>");
                out.println("<li>Creates 8 low-resolution images (300x250px)</li>");
                out.println("<li>One pair of images per product category</li>");
                out.println("<li>Category-specific colors for visual distinction</li>");
                out.println("<li>Lightweight for faster page loading</li>");
                out.println("</ul>");
                out.println("</div>");
            }
            
            out.println("<a href='index.jsp' class='btn btn-secondary mt-3'>Back to Home</a>");
            out.println("</div>");
            out.println("</body>");
            out.println("</html>");
            
        } catch (Exception e) {
            out.println("<h2 class='text-danger'>Error!</h2>");
            out.println("<p>" + e.getMessage() + "</p>");
            e.printStackTrace(out);
        } finally {
            out.close();
        }
    }
}
