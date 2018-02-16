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
import com.wownews.entities.News;
import com.wownews.sessionBeans.NewsFacade;
import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.RenderingHints;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.Normalizer;
import java.text.Normalizer.Form;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Pattern;
import javax.ejb.EJB;
import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import sun.awt.image.BufferedImageGraphicsConfig;

/**
 *
 * @author admin
 */
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10, // 10MB
        maxRequestSize = 1024 * 1024 * 50)   // 50MB
public class AddNoticia extends HttpServlet {

    @EJB
    private NewsFacade newsFacade;
    private static final Pattern NONLATIN = Pattern.compile("[^\\w-]");
    private static final Pattern WHITESPACE = Pattern.compile("[\\s]");

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        try {

            File fBig = new File(System.getProperty("uploads.imgBig"));
            if (!fBig.exists()) {
                fBig.mkdirs();
            }

            File fMid = new File(System.getProperty("uploads.imgMid"));
            if (!fMid.exists()) {
                fMid.mkdirs();
            }

            Part filePart = request.getPart("file");
            InputStream fileContent = filePart.getInputStream();

            BufferedImage image = ImageIO.read(fileContent);
            if (image != null) {
                String title = request.getParameter("title").trim();
                String description = request.getParameter("description");

                News news = new News();
                news.setTitle(title);
                String slug = makeSlug(title);
                String slugF = slug;
                int aux = 2;
                while (newsFacade.slugExists(slugF)) {
                    slugF = slug + aux;
                    aux++;
                }
                news.setSlug(slugF);
                news.setDescription(description);

                request.authenticate(response);
                news.setUsername(request.getRemoteUser());
                newsFacade.create(news);

                String idNew = news.getId().toString();

                File ofB = new File(fBig, idNew + ".png");
                File ofM = new File(fMid, idNew + ".png");
                

                ImageIO.write(resizeTrick(image, 960, 480), "png", ofB);
                ImageIO.write(resizeTrick(image, 600, 300), "png", ofM);

                Map<String, String> mess = new HashMap<>();

                mess.put("mess", "Noticia añadida");

                Gson gson = new GsonBuilder().create();

                response.setContentType(
                        "application/json");
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

    private BufferedImage resize(BufferedImage image, int width,
            int height) {
        int type = image.getType() == 0 ? BufferedImage.TYPE_INT_ARGB : image
                .getType();
        BufferedImage resizedImage = new BufferedImage(width, height, type);
        Graphics2D g = resizedImage.createGraphics();
        g.setComposite(AlphaComposite.Src);
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g.setRenderingHint(RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY);
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g.drawImage(image, 0, 0, width, height, null);
        g.dispose();
        return resizedImage;
    }

    private BufferedImage resizeTrick(BufferedImage image, int width,
            int height) {
        image = createCompatibleImage(image);
        image = resize(image, width, height);

        return image;
    }

    private BufferedImage createCompatibleImage(BufferedImage image) {
        GraphicsConfiguration gc = BufferedImageGraphicsConfig.getConfig(image);
        int w = image.getWidth();
        int h = image.getHeight();
        BufferedImage result = gc.createCompatibleImage(w, h,
                Transparency.TRANSLUCENT);
        Graphics2D g2 = result.createGraphics();
        g2.drawRenderedImage(image, null);
        g2.dispose();
        return result;
    }

    public String makeSlug(String input) {
        String nowhitespace = WHITESPACE.matcher(input).replaceAll("-");
        String normalized = Normalizer.normalize(nowhitespace, Form.NFD);
        String slug = NONLATIN.matcher(normalized).replaceAll("");
        return slug.toLowerCase(Locale.ENGLISH);
    }

}
