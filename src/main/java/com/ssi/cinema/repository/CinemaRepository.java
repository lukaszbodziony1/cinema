package com.ssi.cinema.repository;

import com.ssi.cinema.entity.Cinema;
import com.ssi.cinema.entity.Genre;
import com.ssi.cinema.exception.genric.CreatingNewObjectException;
import com.ssi.cinema.exception.genric.DeleteObjectException;
import com.ssi.cinema.exception.genric.GettingObjectsException;
import com.ssi.cinema.exception.genric.UpdateObjectException;
import com.ssi.cinema.request.cinema.CreateCinemaRequest;
import com.ssi.cinema.request.common.CommonGetRequest;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Slf4j
public class CinemaRepository extends CommonRepository {

    public void addCinema(CreateCinemaRequest request) {
        SessionFactory factory = new Configuration()
                .configure()
                .addAnnotatedClass(Cinema.class)
                .buildSessionFactory();
        Session session = factory.getCurrentSession();

        log.info("Creating new Cinema...");

        try {
            Cinema newCinema = new Cinema(request.getName(), request.getAddress());
            session.beginTransaction();

            log.info("Saving the Cinema...");

            session.save(newCinema);
            session.getTransaction().commit();

            log.info("Cinema successfully created!");
        } catch (Exception e) {
            log.error("Error while saving cinema!", e);
            throw new CreatingNewObjectException("There was a problem while creating new cinema!");
        } finally {
            session.close();
            factory.close();
        }
    }

    public List<Cinema> getAllCinemas(CommonGetRequest request) {
        SessionFactory factory = new Configuration()
                .configure()
                .addAnnotatedClass(Cinema.class)
                .buildSessionFactory();
        Session session = factory.getCurrentSession();

        List<Cinema> cinemas;
        String query = buildGetQuery(request.getFilters(), request.getOrder(), "Cinema");

        log.info("Getting cinemas from database...");
        try {
            session.beginTransaction();

            cinemas = session.createQuery(query).getResultList();
            session.getTransaction().commit();

            log.info("Cinemas successfully get!");
        } catch (Exception e) {
            log.error("Error while getting cinemas from database!", e);
            throw new GettingObjectsException("There was a problem while processing cinemas from database! " + e);
        } finally {
            session.close();
            factory.close();
        }

        return cinemas;
    }

    public Cinema getCinemaById(int id) {
        SessionFactory factory = new Configuration()
                .configure()
                .addAnnotatedClass(Cinema.class)
                .buildSessionFactory();
        Session session = factory.getCurrentSession();

        Cinema cinema;

        log.info("Getting cinema from database...");
        try {
            session.beginTransaction();

            cinema = session.get(Cinema.class, id);
            session.getTransaction().commit();

            log.info("Cinema successfully get!");
        } catch (Exception e) {
            log.error("Error while getting cinema from database!", e);
            throw new GettingObjectsException("There was a problem while processing cinema from database! " + e);
        } finally {
            session.close();
            factory.close();
        }

        return cinema;
    }

    public void deleteCinema(int id) {
        SessionFactory factory = new Configuration()
                .configure()
                .addAnnotatedClass(Cinema.class)
                .buildSessionFactory();
        Session session = factory.getCurrentSession();

        Cinema cinema = getCinemaById(id);

        log.info("Deleting cinema from database...");
        try {
            session.beginTransaction();
            session.delete(cinema);
            session.getTransaction().commit();

            log.info("Cinema successfully deleted!");
        } catch (Exception e) {
            log.error("Error while deleting cinema from database!", e);
            throw new DeleteObjectException("There was a problem while deleting cinema from database! " + e);
        } finally {
            session.close();
            factory.close();
        }
    }

    public void updateCinema(Cinema cinema) {
        SessionFactory factory = new Configuration()
                .configure()
                .addAnnotatedClass(Cinema.class)
                .buildSessionFactory();
        Session session = factory.getCurrentSession();

        log.info("Updating cinema...");
        try {
            session.beginTransaction();
            session.update(cinema);
            session.getTransaction().commit();

            log.info("Cinema successfully updated!");
        } catch (Exception e) {
            log.error("Error while updating cinema!", e);
            throw new UpdateObjectException("There was a problem while updating cinema! " + e);
        } finally {
            session.close();
            factory.close();
        }
    }
}
