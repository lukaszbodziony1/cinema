package com.ssi.cinema.repository;

import com.ssi.cinema.entity.Cinema;
import com.ssi.cinema.entity.Genre;
import com.ssi.cinema.entity.Movie;
import com.ssi.cinema.entity.Role;
import com.ssi.cinema.entity.Spectacle;
import com.ssi.cinema.entity.User;
import com.ssi.cinema.exception.genric.CreatingNewObjectException;
import com.ssi.cinema.exception.genric.DeleteObjectException;
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
public class SpectacleRepository extends CommonRepository {

    public void addNewSpectacle(Spectacle spectacle) {
        SessionFactory factory = new Configuration()
                .configure()
                .addAnnotatedClass(Spectacle.class)
                .addAnnotatedClass(Movie.class)
                .addAnnotatedClass(Cinema.class)
                .addAnnotatedClass(Genre.class)
                .buildSessionFactory();
        Session session = factory.getCurrentSession();

        log.info("Creating new spectacle...");

        try {
            session.beginTransaction();
            log.info("Saving the Spectacle...");
            session.save(spectacle);
            session.getTransaction().commit();

            log.info("Spectacle successfully created!");
        } catch (Exception e) {
            log.error("Error while saving spectacle!", e);
            throw new CreatingNewObjectException("There was a problem while creating new Spectacle!");
        } finally {
            session.close();
            factory.close();
        }
    }

    public List<Spectacle> getAllSpectacles(CommonGetRequest request) {
        SessionFactory factory = new Configuration()
                .configure()
                .addAnnotatedClass(Spectacle.class)
                .addAnnotatedClass(Movie.class)
                .addAnnotatedClass(Cinema.class)
                .addAnnotatedClass(Genre.class)
                .buildSessionFactory();
        Session session = factory.getCurrentSession();

        List<Spectacle> spectacles;
        String query = buildGetQuery(request.getFilters(), request.getOrder(), "Spectacle");

        log.info("Getting spectacles from database...");
        try {
            session.beginTransaction();

            spectacles = session.createQuery(query).getResultList();
            session.getTransaction().commit();

            log.info("Spectacles successfully get!");
        } catch (Exception e) {
            log.error("Error while getting spectacles from database!", e);
            throw new GettingObjectsException("There was a problem while processing spectacles from database! " + e);
        } finally {
            session.close();
            factory.close();
        }

        return spectacles;
    }

    public List<Spectacle> getAllSpectaclesFromCinema(int cinemaId) {
        SessionFactory factory = new Configuration()
                .configure()
                .addAnnotatedClass(Spectacle.class)
                .addAnnotatedClass(Movie.class)
                .addAnnotatedClass(Cinema.class)
                .addAnnotatedClass(Genre.class)
                .buildSessionFactory();
        Session session = factory.getCurrentSession();

        List<Spectacle> spectacles;
        String query = "from Spectacle where cinema.id='" + cinemaId + "'";

        log.info("Getting spectacles from database...");
        try {
            session.beginTransaction();

            spectacles = session.createQuery(query).getResultList();
            session.getTransaction().commit();

            log.info("Spectacles successfully get!");
        } catch (Exception e) {
            log.error("Error while getting spectacles from database!", e);
            throw new GettingObjectsException("There was a problem while processing spectacles from database! " + e);
        } finally {
            session.close();
            factory.close();
        }

        return spectacles;
    }

    public Spectacle getSpectacleById(int id) {
        SessionFactory factory = new Configuration()
                .configure()
                .addAnnotatedClass(Spectacle.class)
                .addAnnotatedClass(Movie.class)
                .addAnnotatedClass(Cinema.class)
                .addAnnotatedClass(Genre.class)
                .buildSessionFactory();
        Session session = factory.getCurrentSession();

        Spectacle spectacle;

        log.info("Getting spectacle with id " + id + " from database...");

        try {
            session.beginTransaction();
            spectacle = session.get(Spectacle.class, id);
            session.getTransaction().commit();

            log.info("Spectacle successfully get!");
        } catch (Exception e) {
            log.error("Error while getting spectacle from database!", e);
            throw new GettingObjectsException("There was a problem while processing spectacle from database! " + e);
        } finally {
            session.close();
            factory.close();
        }

        return spectacle;
    }

    public void deleteSpectacle(Spectacle spectacle) {
        SessionFactory factory = new Configuration()
                .configure()
                .addAnnotatedClass(Spectacle.class)
                .addAnnotatedClass(Movie.class)
                .addAnnotatedClass(Cinema.class)
                .addAnnotatedClass(Genre.class)
                .buildSessionFactory();
        Session session = factory.getCurrentSession();

        log.info("Deleting spectacle " + spectacle.getId() + " ...");

        try {
            session.beginTransaction();
            session.delete(spectacle);
            session.getTransaction().commit();
        } catch (Exception e) {
            log.error("Error while deleting spectacle with id " + spectacle.getId() +"!", e);
            throw new DeleteObjectException("Error while deleting spectacle with id " + spectacle.getId() +"!");
        } finally {
            session.close();
            factory.close();
        }

        log.info("Spectacle with id " + spectacle.getId() + " successfully deleted!");
    }

























}
