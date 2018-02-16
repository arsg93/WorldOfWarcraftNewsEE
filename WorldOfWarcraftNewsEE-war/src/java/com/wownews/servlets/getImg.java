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

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author admin
 */
public class getImg extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String filename = request.getPathInfo().substring(1);
        String aux = filename.substring(0, 6);
        if (filename.substring(0, 6).equals("imgBig")) {
            filename = filename.substring(7, filename.length());
            File file = new File(System.getProperty("uploads.imgBig"), filename);
            if (file.exists()) {
                response.setHeader("Content-Type", getServletContext().getMimeType(filename));
                response.setHeader("Content-Length", String.valueOf(file.length()));
                response.setHeader("Content-Disposition", "inline; filename=\"" + filename + "\"");
                Files.copy(file.toPath(), response.getOutputStream());
            } else {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            }
        } else if (filename.substring(0, 6).equals("imgMid")) {
            filename = filename.substring(7, filename.length());
            File file = new File(System.getProperty("uploads.imgMid"), filename);
            if (file.exists()) {
                response.setHeader("Content-Type", getServletContext().getMimeType(filename));
                response.setHeader("Content-Length", String.valueOf(file.length()));
                response.setHeader("Content-Disposition", "inline; filename=\"" + filename + "\"");
                Files.copy(file.toPath(), response.getOutputStream());
            } else {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            }
        } else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }

    }

}
