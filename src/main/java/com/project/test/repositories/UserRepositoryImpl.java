package com.project.test.repositories;


import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.project.test.model.User;


@Repository("userRepository")
public class UserRepositoryImpl implements UserRepository {
		@Autowired
		SessionFactory sessionFactory;
		
		Session session;
		
		
		public void setSessionFactory(SessionFactory sessionFactory){
			this.sessionFactory=sessionFactory;
		}
		@Override
		public void create(User user) {
			 session = sessionFactory.openSession();
			Transaction tx= session.beginTransaction();
			if(user !=null){
			
				try{
					session.save(user);
		            tx.commit();
			        session.close();
			}catch(Exception e){
				tx.rollback();
				session.close();
				e.printStackTrace();
			}
			}
			
		}

		@Override
		public void update(User user) {
			session = sessionFactory.openSession();
			Transaction tx= session.beginTransaction();
			try{
				session.update(user);
				tx.commit();
				session.close();
			}catch(Exception e){
				tx.rollback();
				session.close();
				e.printStackTrace();
			}
		}

		@Override
		public User edit(Long userId) {
			
			return findUser(userId);
		}

		@Override
		public void delete(Long userId) {
			session = sessionFactory.openSession();
			Transaction tx= session.beginTransaction();
			try{
				session.delete(userId);
				tx.commit();
				session.close();
			}catch(Exception e){
				tx.rollback();
				session.close();
				e.printStackTrace();
			}
				
		}

		@Override
		public User findUser(Long userId) {
			session = sessionFactory.openSession();
			Transaction tx= session.beginTransaction();
			User u = new User();
			try{
			
			 u=(User) session.get(User.class, userId);
			tx.commit();
			session.close();
			
		}catch(Exception e){
			tx.rollback();
			session.close();
			e.printStackTrace();
			
		}
			return u;
			
		}
		@Override
		public User findByUsername(String un){
			User u=null;
			  String hql = "from User where userName =?";
		        session = sessionFactory.openSession();
		        Transaction tx= session.beginTransaction();
		       try{ 
		        Query query = session.createQuery(hql);
		        query.setParameter(0, un);
		        
		       u= (User) query.uniqueResult();
		       tx.commit();
	    		session.close();
		       }
		        catch (Exception e){
		        	tx.rollback();
		    		session.close();
		    		e.printStackTrace();
		    		
		        }
			return u;
		}

	
		@SuppressWarnings("unchecked")
		@Override
		public List<User> getAll() {	
			 
			
			 session = sessionFactory.openSession();
		     Transaction tx= session.beginTransaction();
		try{
			
			Query query = session.createQuery("from User");
			ArrayList<User> u = (ArrayList<User>) query.list();
					//(List<User>) session.createCriteria(User.class).list();
		
			    if(u.size() > 0){
			    	 
			         for (int i = 0; i < u.size(); i++) {
			            User label = (User) u.get(i);
			             System.out.println("Label = " + label.toString());}
			        return u;
			    }
			    return null;  
			  }
		catch(Exception e){
			tx.rollback();
	    		session.close();
	    		e.printStackTrace();
    		
				    }
		return null;
		}
}
