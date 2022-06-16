package peaksoft;

import peaksoft.dao.UserDao;
import peaksoft.dao.UserDaoHibernateImpl;
import peaksoft.dao.UserDaoJdbcImpl;
import peaksoft.util.Util;

public class Main {
    public static void main(String[] args) {
//        UserDao userDao = new UserDaoJdbcImpl();
//        userDao.createUsersTable();
//        userDao.saveUser("Samat", "Beganov", (byte) 26);
//        userDao.saveUser("Muhammed", "Almazbekov", (byte) 25);
//        userDao.saveUser("Bayaman", "Baraev", (byte) 23);
//        userDao.saveUser("Timurlann", "Kasymnaev", (byte) 29);
//        System.out.println(userDao.getAllUsers());
//        userDao.cleanUsersTable();
//        userDao.dropUsersTable();

        UserDao userDao= new UserDaoHibernateImpl();
       userDao.createUsersTable();
        userDao.saveUser("Tom","Anderson",(byte) 26);

//        userDao.removeUserById(2);
//        userDao.cleanUsersTable();
        System.out.println(userDao.getAllUsers());
//        userDao.dropUsersTable();
        Util.shutDown();
    }
}
