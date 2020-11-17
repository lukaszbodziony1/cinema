package com.ssi.cinema.repository;

import com.ssi.cinema.entity.Genre;
import com.ssi.cinema.entity.Movie;
import com.ssi.cinema.exception.genric.CreatingNewObjectException;
import com.ssi.cinema.exception.genric.DeleteObjectException;
import com.ssi.cinema.exception.genric.GetSingleObjectException;
import com.ssi.cinema.exception.genric.GettingObjectsException;
import com.ssi.cinema.request.common.CommonGetRequest;
import com.ssi.cinema.request.movie.AddMovieRequest;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Slf4j
public class MovieRepository extends CommonRepository {
    private final GenreRepository genreRepository;

    @Autowired
    public MovieRepository(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    public void addNewMovie(AddMovieRequest request) {
        SessionFactory factory = new Configuration()
                .configure()
                .addAnnotatedClass(Movie.class)
                .addAnnotatedClass(Genre.class)
                .buildSessionFactory();
        Session session = factory.getCurrentSession();

        Genre genre = genreRepository.getGenreById(request.getGenreId());

        log.info("Creating new Movie...");
        Movie newMovie = new Movie(
                request.getTitle(),
                request.getPremiere(),
                request.getDirector(),
                request.getMinAge(),
                genre);

        try {
            session.beginTransaction();

            log.info("Saving the Movie...");

            session.save(newMovie);
            session.getTransaction().commit();

            log.info("Movie successfully created!");
        } catch (Exception e) {
            log.error("Error while saving movie!", e);
            throw new CreatingNewObjectException("There was a problem while creating new Movie!");
        } finally {
            session.close();
            factory.close();
        }
    }

    public List<Movie> getAllMovies(CommonGetRequest request) {
        SessionFactory factory = new Configuration()
                .configure()
                .addAnnotatedClass(Movie.class)
                .addAnnotatedClass(Genre.class)
                .buildSessionFactory();
        Session session = factory.getCurrentSession();

        List<Movie> movies;
        String query = buildGetQuery(request.getFilters(), request.getOrder(), "Movie");

        log.info("Getting movies from database...");
        try {
            session.beginTransaction();

            movies = session.createQuery(query).getResultList();
            session.getTransaction().commit();

            log.info("Movies successfully get!");
        } catch (Exception e) {
            log.error("Error while getting movies from database!", e);
            throw new GettingObjectsException("There was a problem while processing movies from database! " + e);
        } finally {
            session.close();
            factory.close();
        }

        return movies;
    }

    public Movie getMovieById(int id) {
        SessionFactory factory = new Configuration()
                .configure()
                .addAnnotatedClass(Movie.class)
                .addAnnotatedClass(Genre.class)
                .buildSessionFactory();
        Session session = factory.getCurrentSession();

        Movie movie;

        log.info("Getting movie with id " + id + " from database...");
        try {
            session.beginTransaction();
            movie = session.get(Movie.class, id);
        } catch (Exception e) {
            log.error("Error while getting movie with id " + id +" from database!", e);
            throw new GetSingleObjectException("Error while getting movie with id " + id +" from database!");
        } finally {
            session.close();
            factory.close();
        }
        log.info("Successfully get movie with id " + id + " from database!");

        return movie;
    }

    public void deleteMovie(int id) {
        SessionFactory factory = new Configuration()
                .configure()
                .addAnnotatedClass(Movie.class)
                .addAnnotatedClass(Genre.class)
                .buildSessionFactory();
        Session session = factory.getCurrentSession();

        Movie movie = getMovieById(id);

        log.info("Deleting movie with id " + id + " from database...");

        try {
            session.beginTransaction();
            session.delete(movie);
            session.getTransaction().commit();
        } catch (Exception e) {
            log.error("Error while deleting movie with id " + id +" from database!", e);
            throw new DeleteObjectException("Error while getting movie with id " + id +" from database!");
        } finally {
            session.close();
            factory.close();
        }

        log.info("Movie with id " + id + " successfully deleted!");
    }
}
