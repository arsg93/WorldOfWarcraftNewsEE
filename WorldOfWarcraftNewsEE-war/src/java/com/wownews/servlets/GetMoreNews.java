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
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jsoup.Jsoup;

/**
 *
 * @author admin
 */
public class GetMoreNews extends HttpServlet {

    @EJB
    private NewsFacade newsFacade;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            List<News> more = (List<News>) changeTitle(changeDescription((List<News>) newsFacade.getMoreNews(Integer.parseInt(request.getParameter("last"))*4)));
            if (more.size() == 0) {
                Map<String, String> mess = new HashMap<>();
                mess.put("noMore", "No hay más noticias");

                Gson gson = new GsonBuilder().create();

                response.setContentType("application/json");
                PrintWriter pw = response.getWriter();
                pw.println(gson.toJson(mess));
            } else {
                Gson gson = new GsonBuilder().setPrettyPrinting().setDateFormat("EEE MMM dd HH:mm:ss zzz yyyy").create();
                response.setContentType("application/json");
                PrintWriter pw = response.getWriter();
                pw.println(gson.toJson(more));
            }
        } catch (Exception e) {
            Map<String, String> emess = new HashMap<>();
            emess.put("error", "Server error");

            Gson gson = new GsonBuilder().create();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.setContentType("application/json");
            PrintWriter pw = response.getWriter();
            pw.println(gson.toJson(emess));

        }
    }

    private List<News> changeDescription(List<News> news) {
        for (News aux : news) {
            String desc = html2text(aux.getDescription());
            if (desc.length() > 300) {
                desc = desc.substring(0, 300);
            }
            if (desc.lastIndexOf(' ') != -1) {
                aux.setDescription(desc.substring(0, desc.lastIndexOf(' ')) + "...");
            }
        }
        return news;
    }

    private List<News> changeTitle(List<News> news) {
        for (News aux : news) {
            if (aux.getTitle().length() > 50) {
                String desc = aux.getTitle().substring(0, 50);
                if (desc.lastIndexOf(' ') != -1) {
                    aux.setTitle(desc.substring(0, desc.lastIndexOf(' ')) + "...");
                }
            }
        }
        return news;
    }

    private String html2text(String html) {
        return Jsoup.parse(html).text();
    }

}
