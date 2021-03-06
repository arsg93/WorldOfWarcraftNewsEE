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

import com.wownews.entities.News;
import com.wownews.sessionBeans.NewsFacade;
import java.io.IOException;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jsoup.Jsoup;

/**
 *
 * @author admin
 */
public class GetFirstNews extends HttpServlet {

    @EJB
    private NewsFacade newsFacade;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<News> news = changeTitle(changeDescription(newsFacade.getFirstNews()));
        request.setAttribute("News", news);
        RequestDispatcher rd = request.getRequestDispatcher("news.jsp");
        rd.forward(request, response);

    }

    private List<News> changeDescription(List<News> news) {
        for (News aux : news) {
            String desc = html2text(aux.getDescription());
            if(desc.length()>300){
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
