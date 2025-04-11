package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   @Autowired
   private SessionFactory sessionFactory;

   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }
   @Override
   public void clearUsersTable() {
      sessionFactory.getCurrentSession().createQuery("delete from User").executeUpdate();
   }
   @Override
   public void clearCarsTable() {
      sessionFactory.getCurrentSession().createQuery("delete from Car").executeUpdate();
   }
   @Override
   public void resetAutoIncrement() {
      sessionFactory.getCurrentSession().createNativeQuery("ALTER TABLE users AUTO_INCREMENT = 1").executeUpdate();
      sessionFactory.getCurrentSession().createNativeQuery("ALTER TABLE cars AUTO_INCREMENT = 1").executeUpdate();

   }
}
