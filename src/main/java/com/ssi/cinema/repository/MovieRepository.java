package com.ssi.cinema.repository;

import com.ssi.cinema.entity.Genre;
import com.ssi.cinema.entity.Movie;
import com.ssi.cinema.exception.genric.CreatingNewObjectException;
import com.ssi.cinema.request.movie.AddMovieRequest;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
}
