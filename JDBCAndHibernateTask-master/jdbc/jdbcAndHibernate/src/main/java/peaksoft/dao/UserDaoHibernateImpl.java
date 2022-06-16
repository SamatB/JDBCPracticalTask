package peaksoft.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import peaksoft.model.User;
import peaksoft.util.Util;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() {
        String sql = "create table if not exists users(" +
                "id serial primary key," +
                "name varchar(50) not null," +
                "last_name varchar(50) not null," +
                "age int2);";
        SessionFactory factory = Util.getSessionFactory();
        Session session = factory.openSession();
        session.beginTransaction();
        session.createSQLQuery(sql).executeUpdate();
        session.getTransaction().commit();
        System.out.println("User table created");
        session.close();

    }

    @Override
    public void dropUsersTable() {
        String sql = "drop table if exists users";
        SessionFactory factory =Util.getSessionFactory();
        Session session = factory.openSession();
        session.beginTransaction();
        session.createSQLQuery(sql).executeUpdate();
        session.getTransaction().commit();
        System.out.println("Users table successfully dropped!");
        session.close();
    }

    @Override
    public void saveUser(String name, String last_name, byte age) {
        SessionFactory factory = Util.getSessionFactory();
        Session session = factory.openSession();
        session.beginTransaction();
        User user = new User();
        user.setName(name);
        user.setLastName(last_name);
        user.setAge(age);
        session.save(user);
        session.getTransaction().commit();
        System.out.println(user + " saved!");
        session.close();
    }

    @Override
    public void removeUserById(long id) {
        SessionFactory factory =Util.getSessionFactory();
        Session session = factory.openSession();
        session.beginTransaction();
        User user = (User) session.get(User.class, id);
        session.delete(user);
        session.getTransaction().commit();
        System.out.println("User with id - " + id + " deleted");
        session.close();
    }

    @Override
    public List<User> getAllUsers() {
        List users;
        SessionFactory factory =Util.getSessionFactory();
        Session session = factory.openSession();
        session.beginTransaction();
        users = session.createQuery("from User").list();
        session.getTransaction().commit();
        session.close();
        return users;
    }

    @Override
    public void cleanUsersTable() {
        String sql = "truncate users";
        SessionFactory factory = Util.getSessionFactory();
        Session session = factory.openSession();
        session.beginTransaction();
        session.createSQLQuery(sql).executeUpdate();
        session.getTransaction().commit();
        System.out.println("User table was cleaned");
        session.close();
    }
}
