package com.ssi.cinema.repository;

import com.ssi.cinema.entity.Genre;
import com.ssi.cinema.entity.Movie;
import com.ssi.cinema.entity.Role;
import com.ssi.cinema.entity.User;
import com.ssi.cinema.exception.genric.CreatingNewObjectException;
import com.ssi.cinema.exception.genric.GettingObjectsException;
import com.ssi.cinema.exception.genric.UpdateObjectException;
import com.ssi.cinema.request.common.CommonGetRequest;
import com.ssi.cinema.request.user.AddUserRequest;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Slf4j
public class UserRepository extends CommonRepository {

    public void addNewUser(AddUserRequest request, String encodedPassword, List<Role> roles) {
        SessionFactory factory = new Configuration()
                .configure()
                .addAnnotatedClass(User.class)
                .addAnnotatedClass(Role.class)
                .addAnnotatedClass(Movie.class)
                .addAnnotatedClass(Genre.class)
                .buildSessionFactory();
        Session session = factory.getCurrentSession();

        log.info("Creating new User...");
        User newUser = new User(
                request.getLogin(),
                encodedPassword,
                request.getName(),
                request.getSurname(),
                roles,
                request.getAge());
        try {
            session.beginTransaction();

            log.info("Saving the User...");

            session.save(newUser);
            session.getTransaction().commit();

            log.info("User successfully created!");
        } catch (Exception e) {
            log.error("Error while saving user!", e);
            throw new CreatingNewObjectException("There was a problem while creating new User!");
        } finally {
            session.close();
            factory.close();
        }
    }

    public List<User> getAllUsers(CommonGetRequest request) {
        SessionFactory factory = new Configuration()
                .configure()
                .addAnnotatedClass(User.class)
                .addAnnotatedClass(Role.class)
                .addAnnotatedClass(Movie.class)
                .addAnnotatedClass(Genre.class)
                .buildSessionFactory();
        Session session = factory.getCurrentSession();

        List<User> users;
        String query = buildGetQuery(request.getFilters(), request.getOrder(), "User");

        log.info("Getting users from database...");
        try {
            session.beginTransaction();

            users = session.createQuery(query).getResultList();
            session.getTransaction().commit();

            log.info("Users successfully get!");
        } catch (Exception e) {
            log.error("Error while getting users from database!", e);
            throw new GettingObjectsException("There was a problem while processing users from database! " + e);
        } finally {
            session.close();
            factory.close();
        }

        return users;
    }

    public boolean checkIfUserExists(String login) {
        SessionFactory factory = new Configuration()
                .configure()
                .addAnnotatedClass(User.class)
                .addAnnotatedClass(Role.class)
                .addAnnotatedClass(Movie.class)
                .addAnnotatedClass(Genre.class)
                .buildSessionFactory();
        Session session = factory.getCurrentSession();

        User user = null;

        try {
            session.beginTransaction();

            String query = "from User where login='" + login + "'";
            user = (User) session.createQuery(query).getSingleResult();
            session.getTransaction().commit();

            log.info("User with login " + login + " successfully get!");
        } catch (Exception e) {
            log.error("Error while getting user with login " + login + " from database!", e);
        } finally {
            session.close();
            factory.close();
        }

        if(user == null)
            return false;
        return true;
    }

    public User getUserByLogin(String login) {
        SessionFactory factory = new Configuration()
                .configure()
                .addAnnotatedClass(User.class)
                .addAnnotatedClass(Role.class)
                .addAnnotatedClass(Movie.class)
                .addAnnotatedClass(Genre.class)
                .buildSessionFactory();
        Session session = factory.getCurrentSession();

        User user;

        log.info("Getting user with login " + login + " from database...");
        try {
            session.beginTransaction();

            String query = "from User where login='" + login + "'";
            user = (User) session.createQuery(query).getSingleResult();
            session.getTransaction().commit();

            log.info("User with login " + login + " successfully get!");
        } catch (Exception e) {
            log.error("Error while getting user with login " + login + " from database!", e);
            throw new GettingObjectsException("There was a problem while processing user with name " + login + " from database! " + e);
        } finally {
            session.close();
            factory.close();
        }

        return user;
    }

    public void updateUser(User user) {
        SessionFactory factory = new Configuration()
                .configure()
                .addAnnotatedClass(User.class)
                .addAnnotatedClass(Movie.class)
                .addAnnotatedClass(Role.class)
                .addAnnotatedClass(Genre.class)
                .buildSessionFactory();
        Session session = factory.getCurrentSession();

        log.info("Updating user with id " + user.getId() + " ...");

        try {
            session.beginTransaction();
            session.update(user);
            session.getTransaction().commit();
        } catch (Exception e) {
            log.error("Error while updating user with id " + user.getId() +"!", e);
            throw new UpdateObjectException("Error while updating user with id " + user.getId() +"!");
        } finally {
            session.close();
            factory.close();
        }

        log.info("User with id " + user.getId() + " successfully updated!");
    }

    public void deleteUser(User user) {
        SessionFactory factory = new Configuration()
                .configure()
                .addAnnotatedClass(User.class)
                .addAnnotatedClass(Movie.class)
                .addAnnotatedClass(Role.class)
                .addAnnotatedClass(Genre.class)
                .buildSessionFactory();
        Session session = factory.getCurrentSession();

        log.info("Deleting user " + user.getLogin() + " ...");

        try {
            session.beginTransaction();
            session.delete(user);
            session.getTransaction().commit();
        } catch (Exception e) {
            log.error("Error while deleting user with id " + user.getId() +"!", e);
            throw new UpdateObjectException("Error while deleting user with id " + user.getId() +"!");
        } finally {
            session.close();
            factory.close();
        }

        log.info("User with id " + user.getId() + " successfully deleted!");
    }
}
