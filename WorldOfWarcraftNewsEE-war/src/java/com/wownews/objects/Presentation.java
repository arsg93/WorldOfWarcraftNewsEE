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
package com.wownews.objects;

import com.wownews.entities.News;
import com.wownews.sessionBeans.NewsFacade;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import org.jsoup.Jsoup;

/**
 *
 * @author admin
 */
public class Presentation {

    NewsFacade newsFacade = lookupNewsFacadeBean();
    private List<News> news;

    public Presentation() {
        news = newsFacade.getFirstNews();
         changeDescription();

    }

    public List<News> getNews() {

        return news;
    }

    private void changeDescription(){
        for(News aux: news){
            String desc = html2text(aux.getDescription()).substring(0,300);
            aux.setDescription(desc.substring(0, desc.lastIndexOf(' '))+"...");
        }
    }

    private String html2text(String html) {
        return Jsoup.parse(html).text();
    }

    private NewsFacade lookupNewsFacadeBean() {
        try {
            Context c = new InitialContext();
            return (NewsFacade) c.lookup("java:global/WorldOfWarcraftNewsEE/WorldOfWarcraftNewsEE-ejb/NewsFacade!com.wownews.sessionBeans.NewsFacade");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

}
