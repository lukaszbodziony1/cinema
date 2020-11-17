package com.ssi.cinema.repository;

import com.ssi.cinema.entity.Genre;
import com.ssi.cinema.exception.genre.CreateNewGenreEmptyNameException;
import com.ssi.cinema.exception.genre.CreatingNewGenreException;
import com.ssi.cinema.exception.genric.GettingObjectsException;
import com.ssi.cinema.request.common.CommonGetRequest;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Slf4j
public class GenreRepository extends CommonRepository {

    public void addGenre(String name) {
        SessionFactory factory = new Configuration()
                .configure()
                .addAnnotatedClass(Genre.class)
                .buildSessionFactory();
        Session session = factory.getCurrentSession();

        if (name == null || name.isEmpty())
            throw new CreateNewGenreEmptyNameException("There was a problem while creating new Genre, name can't be empty!");

        log.info("Creating new Genre...");
        try {
            Genre newGenre = new Genre(name);
            session.beginTransaction();

            log.info("Saving the Genre...");

            session.save(newGenre);
            session.getTransaction().commit();

            log.info("Genre successfully created!");
        } catch (Exception e) {
            log.error("Error while saving genre!", e);
            throw new CreatingNewGenreException("There was a problem while creating new Genre!");
        } finally {
            factory.close();
        }
    }

    public List<Genre> getAllGenres(CommonGetRequest request) {
        SessionFactory factory = new Configuration()
                .configure()
                .addAnnotatedClass(Genre.class)
                .buildSessionFactory();
        Session session = factory.getCurrentSession();

        List<Genre> genres;
        String query = buildGetQuery(request.getFilters(), request.getOrder(), "Genre");

        log.info("Getting genres from database...");
        try {
            session.beginTransaction();

            genres = session.createQuery(query).getResultList();
            session.getTransaction().commit();

            log.info("Genres successfully get!");
        } catch (Exception e) {
            log.error("Error while getting genres from database!", e);
            throw new GettingObjectsException("There was a problem while processing genres from database! " + e);
        } finally {
            factory.close();
        }

        return genres;
    }
}
