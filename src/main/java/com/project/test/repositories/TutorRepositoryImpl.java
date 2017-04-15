package com.project.test.repositories;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.project.test.model.Tutor;
@Repository("tutorRepository")
public class TutorRepositoryImpl implements TutorRepository {

	@Autowired
	SessionFactory sessionFactory;
	
	Session session;
	
	
	public void setSessionFactory(SessionFactory sessionFactory){
		this.sessionFactory=sessionFactory;
	}
	@Override
	public void create(Tutor tutor) {
		 session = sessionFactory.openSession();
			Transaction tx= session.beginTransaction();
			if(tutor !=null){
				try{
					session.save(tutor);
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
	public void update(long tutorId, Tutor tutor) {
		session = sessionFactory.openSession();
		Transaction tx= session.beginTransaction();
		Tutor t = new Tutor();
		try{
			t=(Tutor) session.get(Tutor.class, tutorId);
			t.setName(tutor.getName());
			t.setDepartment(tutor.getDepartment());
			session.update(t); 
	        tx.commit();
	     	session.close();
		}catch(Exception e){
			tx.rollback();
			session.close();
			e.printStackTrace();
			
		}
	
	}

	@Override
	public Tutor findTutor(Long sgId) {
		session = sessionFactory.openSession();
		Transaction tx= session.beginTransaction();
		Tutor sg = new Tutor();
		try{
		
		 sg=(Tutor) session.get(Tutor.class, sgId);
		tx.commit();
		session.close();
		
	}catch(Exception e){
		tx.rollback();
		session.close();
		e.printStackTrace();
		
	}
		return sg;
		
	}

	@Override
	public void delete(Long Id) {
		session = sessionFactory.openSession();
		Transaction tx= session.beginTransaction();
	      try{
	    	  Tutor tutor = 
	                   (Tutor)session.get(Tutor.class, Id); 
	         session.delete(tutor); 
	         tx.commit();
	      }catch(Exception e){
	  		tx.rollback();
	  		session.close();
	  		e.printStackTrace();
	  		
		  	}
		
	}

	@Override
	public List<Tutor> getAll() {
		 session = sessionFactory.openSession();
	     Transaction tx= session.beginTransaction();
	try{
		
		Query query = session.createQuery("from Tutor");
		@SuppressWarnings("unchecked")
		ArrayList<Tutor> tutor = (ArrayList<Tutor>) query.list();
				//(List<User>) session.createCriteria(User.class).list();
	
		    if(tutor.size() > 0){ 
		    	/* for (int i = 0; i < r.size(); i++) {
       		Room label = (Room) r.get(i);
        		System.out.println("Label = " + label.toString());}*/
		        return tutor;
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
