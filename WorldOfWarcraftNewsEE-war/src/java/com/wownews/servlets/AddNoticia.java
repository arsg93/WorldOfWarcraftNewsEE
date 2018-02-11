/*
 * The MIT License
 *
 * Copyright 2018 admin.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.wownews.servlets;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import javax.activation.MimetypesFileTypeMap;
import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 *
 * @author admin
 */
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10, // 10MB
        maxRequestSize = 1024 * 1024 * 50)   // 50MB
public class AddNoticia extends HttpServlet {

    private static final String SAVE_DIR = "uploads";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        StringBuilder sb = new StringBuilder();
        try {
            // gets absolute path of the web application
            String appPath = request.getServletContext().getRealPath("");
            // constructs path of the directory to save uploaded file
            String savePath = appPath + File.separator + SAVE_DIR;

            // creates the save directory if it does not exists
            File fileSaveDir = new File(savePath);
            if (!fileSaveDir.exists()) {
                fileSaveDir.mkdir();
            }

            String description = request.getParameter("description"); // Retrieves <input type="text" name="description">
            Part filePart = request.getPart("file"); // Retrieves <input type="file" name="file">
            InputStream fileContent = filePart.getInputStream();

            File outputFile = new File(savePath + File.separator + "test.jpg");
            BufferedImage image = ImageIO.read(fileContent);
            if (image != null) {

                OutputStream os = new FileOutputStream(outputFile);
                ImageIO.write(image, "jpg", os);

                Map<String, String> mess = new HashMap<>();
                mess.put("mess", "Noticia añadida");

                Gson gson = new GsonBuilder().create();

                response.setContentType("application/json");
                PrintWriter pw = response.getWriter();
                pw.println(gson.toJson(mess));
            } else {
                Map<String, String> emess = new HashMap<>();
                emess.put("error", "El archivo no es una imagen");

                Gson gson = new GsonBuilder().create();
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                response.setContentType("application/json");
                PrintWriter pw = response.getWriter();
                pw.println(gson.toJson(emess));
            }
        } catch (Exception e) {

            Map<String, String> emess = new HashMap<>();
            //"No ha sido posible añadir la noticia"
            emess.put("error", e.toString());

            Gson gson = new GsonBuilder().create();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.setContentType("application/json");
            PrintWriter pw = response.getWriter();
            pw.println(gson.toJson(emess));

        }
    }

}
