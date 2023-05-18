/*
 * Permission to use, copy, modify, and/or distribute this software for any
 * purpose with or without fee is hereby granted.
 *
 * THE SOFTWARE IS PROVIDED "AS IS" AND THE AUTHOR(S) DISCLAIMS ALL WARRANTIES
 * WITH REGARD TO THIS SOFTWARE INCLUDING ALL IMPLIED WARRANTIES OF
 * MERCHANTABILITY AND FITNESS. IN NO EVENT SHALL THE AUTHOR(S) BE LIABLE FOR ANY
 * SPECIAL, DIRECT, INDIRECT, OR CONSEQUENTIAL DAMAGES OR ANY DAMAGES WHATSOEVER
 * RESULTING FROM LOSS OF USE, DATA OR PROFITS, WHETHER IN AN ACTION OF CONTRACT,
 * NEGLIGENCE OR OTHER TORTIOUS ACTION, ARISING OUT OF OR IN CONNECTION WITH THE
 * USE OR PERFORMANCE OF THIS SOFTWARE.
 */
package com.forest.web.util;

import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.Closeable;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.forest.ejb.ProductBean;
import com.forest.entity.Product;

import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Image servlet based on BalusC (@link below) implementation
 * @author balusc
 * @author markito
 * @link http://balusc.blogspot.com/2007/04/imageservlet.html
 */
@WebServlet(urlPatterns = "/image/*")
public class ImageServlet extends HttpServlet {
    
    private static final long serialVersionUID = 6439315738094263474L;

    @EJB
    ProductBean productBean;
    private static final Logger logger = Logger.getLogger(ImageServlet.class.getCanonicalName());
    // Constants 
    private static final int DEFAULT_BUFFER_SIZE = 10240; // 10KB.
    // Properties 
    private final String UPLOAD_DIR = "/upload/img/";
    //private final String UPLOAD_DIR = ResourceBundle.getBundle("bundles.Bundle").getString("UPLOAD_LOCATION");

    // Actions 
    @Override
    public void init() throws ServletException {
        // In a Windows environment with the Applicationserver running on the
        // c: volume, the above path is exactly the same as "c:\images".
        // In UNIX, it is just straightforward "/images".
        // If you have stored files in the WebContent of a WAR, for example in the
        // "/WEB-INF/images" folder, then you can retrieve the absolute path by:
        // this.imagePath = getServletContext().getRealPath("/WEB-INF/images");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Get requested image by path info.
        String requestedImage = request.getParameter("id"); //request.getPathInfo();
        

        // Check if file name is actually supplied to the request URI.
        if (requestedImage == null) {
            // Do your thing if the image is not supplied to the request URI.
            // Throw an exception, or send 404, or show default/warning image, or just ignore it.

            response.sendError(HttpServletResponse.SC_NOT_FOUND); // 404.
            return;
        }
        Product p = productBean.find(Integer.parseInt(requestedImage));

        if ((p == null) || (p.getImgSrc() == null)) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND); // 404.
        } else {
            /*
            // Decode the file name (might contain spaces and on) and prepare file object.
            File image = new File(getServletContext().getRealPath(UPLOAD_DIR), URLDecoder.decode(requestedImage, "UTF-8"));
            
            // Check if file actually exists in filesystem.
            if (!image.exists()) {
            // Throw an exception, or send 404, or show default/warning image, or just ignore it.
            logger.info("Uploaded image already exists");
            response.sendError(HttpServletResponse.SC_NOT_FOUND); // 404.
            return;
            }
            
            // Get content type by filename.
            String contentType = getServletContext().getMimeType(image.getName());
            
            // Check if file is actually an image (avoid download of other files by hackers!).
            // For all content types, see: http://www.w3schools.com/media/media_mimeref.asp
            if (contentType == null || !contentType.startsWith("image")) {
            // Throw an exception, or send 404, or show default/warning image, or just ignore it.
            logger.info("Uploaded image doesn't look like a real image");
            response.sendError(HttpServletResponse.SC_NOT_FOUND); // 404.
            return;
            }
             */
            // Init servlet response.
            response.reset();
            response.setBufferSize(DEFAULT_BUFFER_SIZE);
            //response.setContentType("image/jpg");
            response.setHeader("Content-Length", String.valueOf(p.getImgSrc().length));
            response.setHeader("Content-Disposition", "inline; filename=\"" + p.getName() + "\"");

            // Prepare streams.
            ByteArrayInputStream byteInputStream = null;
            BufferedOutputStream output = null;

            try {
                // Open streams.
                byteInputStream = new ByteArrayInputStream(p.getImgSrc());
                output = new BufferedOutputStream(response.getOutputStream(), DEFAULT_BUFFER_SIZE);

              // Write file contents to response.
                byte[] buffer = new byte[DEFAULT_BUFFER_SIZE];
                int length;
                while ((length = byteInputStream.read(buffer)) > 0) {
                    output.write(buffer, 0, length);
                }
            } finally {
                // close streams.
                close(output);
                close(byteInputStream);
            }
        }
    }

    // Helpers (can be refactored to public utility class) 
    private static void close(Closeable resource) {
        if (resource != null) {
            try {
                resource.close();
            } catch (IOException e) {
                logger.log(Level.SEVERE, 
                        "Problems during image resource manipulation. {0}", 
                        e.getMessage());
            }
        }
    }
}
