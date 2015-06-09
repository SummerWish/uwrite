package edu.tongji.article;

import edu.tongji.account.Account;
import org.pegdown.PegDownProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Transient;
import java.util.List;

@Repository
//@Transactional(readOnly = true)
public class ArticleRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    @Transient
    private PegDownProcessor pegDownProcessor;

    @Transactional
    public Article save(Article article) {
        article.setHtml(pegDownProcessor.markdownToHtml(article.getMarkdown()));
        article.setUrl(article.getTitle());
        entityManager.persist(article);
        return article;
    }

    public List<Article> listAll(Account account) {
        try {
            return entityManager.createNamedQuery(Article.FIND_MINE, Article.class)
                    .setParameter("uid", account.getId())
                    .getResultList();
        } catch (PersistenceException e) {
            return null;
        }
    }

    public Article getArticle(Account account, Long id) {
        try {
            return entityManager.createNamedQuery(Article.FIND_BY_UID_ID, Article.class)
                    .setParameter("uid", account.getId())
                    .setParameter("id", id)
                    .getSingleResult();
        } catch (PersistenceException e) {
            return null;
        }
    }

    @Transactional
    public Boolean delete(Account account, Long id) {
        Article article = getArticle(account, id);
        entityManager.remove(article);
        return true;
    }

    @Transactional
    public Article update(Account account, Long id, String markdown, String title) {
        Article article = getArticle(account, id);
        article.setMarkdown(markdown);
        article.setTitle(title);
        entityManager.merge(article);
        return article;
    }
}
