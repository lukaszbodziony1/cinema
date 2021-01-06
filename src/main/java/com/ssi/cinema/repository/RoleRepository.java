package com.ssi.cinema.repository;

import com.ssi.cinema.entity.Role;
import com.ssi.cinema.exception.genric.GettingObjectsException;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Slf4j
public class RoleRepository {
    public List<Role> getAllRoles() {
        SessionFactory factory = new Configuration()
                .configure()
                .addAnnotatedClass(Role.class)
                .buildSessionFactory();
        Session session = factory.getCurrentSession();

        List<Role> roles;

        log.info("Getting roles from database...");
        try {
            session.beginTransaction();

            String query = "from Role";
            roles = session.createQuery(query).getResultList();
            session.getTransaction().commit();

            log.info("Roles successfully get!");
        } catch (Exception e) {
            log.error("Error while getting roles from database!", e);
            throw new GettingObjectsException("There was a problem while processing roles from database! " + e);
        } finally {
            session.close();
            factory.close();
        }

        return roles;
    }

    public Role getRoleById(int id) {
        SessionFactory factory = new Configuration()
                .configure()
                .addAnnotatedClass(Role.class)
                .buildSessionFactory();
        Session session = factory.getCurrentSession();

        Role role;

        log.info("Getting role with id " + id + " from database...");
        try {
            session.beginTransaction();

            role = session.get(Role.class, id);
            session.getTransaction().commit();

            log.info("Role with id " + id + " successfully get!");
        } catch (Exception e) {
            log.error("Error while getting role with id " + id + " from database!", e);
            throw new GettingObjectsException("There was a problem while processing role  with id " + id + " from database! " + e);
        } finally {
            session.close();
            factory.close();
        }

        return role;
    }

    public List<Role> getRolesByIds(List<Integer> ids) {
        SessionFactory factory = new Configuration()
                .configure()
                .addAnnotatedClass(Role.class)
                .buildSessionFactory();
        Session session = factory.getCurrentSession();

        List<Role> roles;

        log.info("Getting roles from database...");
        try {
            session.beginTransaction();

            String query = "from Role where id in (" + buildIdInList(ids) + ")";
            roles = session.createQuery(query).getResultList();
            session.getTransaction().commit();

            log.info("Roles successfully get!");
        } catch (Exception e) {
            log.error("Error while getting role from database!", e);
            throw new GettingObjectsException("There was a problem while processing role from database! " + e);
        } finally {
            session.close();
            factory.close();
        }

        return roles;
    }

    private StringBuilder buildIdInList(List<Integer> ids) {
        StringBuilder idsString = new StringBuilder();
        int i = 0;
        while(i < ids.size()) {
            idsString.append(ids.get(i));
            if(i < ids.size() - 1) {
                idsString.append(", ");
            }
            i++;
        }
        return idsString;
    }
}
