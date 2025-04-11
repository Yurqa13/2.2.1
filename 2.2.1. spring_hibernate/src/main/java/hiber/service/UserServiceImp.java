package hiber.service;

import hiber.dao.UserDao;

import hiber.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Service
public class UserServiceImp implements UserService {

   private final UserDao userDao;

   @PersistenceContext
   private EntityManager entityManager;

   @Autowired
   public UserServiceImp(UserDao userDao) {
      this.userDao = userDao;
   }

   @Transactional
   @Override
   public void add(User user) {
      userDao.add(user);
   }

   @Transactional(readOnly = true)
   @Override
   public List<User> listUsers() {
      return userDao.listUsers();
   }

   @Transactional(readOnly = true)
   @Override
   public User getUserByCar(String model, int series) {

      TypedQuery<User> query = entityManager.createQuery(
              "FROM User u WHERE u.car.model = :model AND u.car.series = :series", User.class);
      query.setParameter("model", model);
      query.setParameter("series", series);
      List<User> users = query.getResultList();
      if(users.size() > 0) {
         return users.get(0);
      }
      return null;

   }
   @Transactional
   @Override
   public void clearUsersTable() {
      userDao.clearUsersTable();
   }
   @Transactional
   @Override
   public void clearCarTable() {
      userDao.clearCarsTable();
   }
   @Transactional
   @Override
   public void resetAutoIncrement() {
      userDao.resetAutoIncrement();
   }

}
