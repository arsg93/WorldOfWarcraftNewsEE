/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wownews.sessionBeans;

import com.wownews.entities.News;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author admin
 */
@Stateless
public class NewsFacade extends AbstractFacade<News> {

    @PersistenceContext(unitName = "WorldOfWarcraftNewsEE-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public NewsFacade() {
        super(News.class);
    }

    /**
     * Obtiene las 4 noticias mas nuevas.
     *
     * @return
     */
    public List<News> getFirstNews() {
        List<News> list = em.createNamedQuery("News.getByDate").setMaxResults(4).getResultList();
        return list;
    }

    /**
     * Obtiene por Slug.
     *
     * @param slug
     * @return
     */
    public News getBySlug(String slug) {
        News news = (News) em.createNamedQuery("News.findBySlug").setParameter("slug", slug).getSingleResult();
        return news;
    }

    /**
     * Devuelve true si ya existe false si no.
     *
     * @param slug
     * @return
     */
    public boolean slugExists(String slug) {
        List<News> list = em.createNamedQuery("News.findBySlug").setParameter("slug", slug).getResultList();
        return !list.isEmpty();
    }
    


}
