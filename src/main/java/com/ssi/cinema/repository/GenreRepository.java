package com.ssi.cinema.repository;

import com.ssi.cinema.entity.Genre;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Repository;

@Repository
@Slf4j
public class GenreRepository {

    public void addGenre(String name) {
        SessionFactory factory = new Configuration()
                .configure()
                .addAnnotatedClass(Genre.class)
                .buildSessionFactory();

        Session session = factory.getCurrentSession();

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
        } finally {
            factory.close();
        }
    }
}
