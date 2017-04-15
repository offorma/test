package com.project.test.repositories;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.project.test.model.StudentGroup;
@Repository("studentGroupRepository")
public class StudentGroupRepositoryImpl implements StudentGroupRepository {

	@Autowired
	SessionFactory sessionFactory;
	
	Session session;
	
	
	public void setSessionFactory(SessionFactory sessionFactory){
		this.sessionFactory=sessionFactory;
	}
	@Override
	public void create(StudentGroup student) {
		 session = sessionFactory.openSession();
			Transaction tx= session.beginTransaction();
			if(student !=null){
				try{
					session.save(student);
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
	public void update(long sgId, StudentGroup sg) {
		session = sessionFactory.openSession();
		Transaction tx= session.beginTransaction();
		StudentGroup s = new StudentGroup();
		try{
			s=(StudentGroup) session.get(StudentGroup.class, sgId);
			s.setGroupName(sg.getGroupName());
			s.setNumberOfStudent(sg.getNumberOfStudent());
			session.update(s); 
	        tx.commit();
	     	session.close();
		}catch(Exception e){
			tx.rollback();
			session.close();
			e.printStackTrace();		
		}	
	}

	@Override
	public StudentGroup findStudentGroup(Long sgId) {
		session = sessionFactory.openSession();
		Transaction tx= session.beginTransaction();
		StudentGroup sg = new StudentGroup();
		try{
		
		 sg=(StudentGroup) session.get(StudentGroup.class, sgId);
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
	public StudentGroup findByGroupName(String un) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Long Id) {
		session = sessionFactory.openSession();
		Transaction tx= session.beginTransaction();
	      try{
	    	  StudentGroup sg = 
	                   (StudentGroup)session.get(StudentGroup.class, Id); 
	         session.delete(sg); 
	         tx.commit();
	      }catch(Exception e){
	  		tx.rollback();
	  		session.close();
	  		e.printStackTrace();
	  		
	  	}
		
	}


	@Override
	public List<StudentGroup> getAll() {

		 session = sessionFactory.openSession();
	     Transaction tx= session.beginTransaction();
	try{
		
		Query query = session.createQuery("from StudentGroup");
		@SuppressWarnings("unchecked")
		ArrayList<StudentGroup> sg = (ArrayList<StudentGroup>) query.list();
				//(List<User>) session.createCriteria(User.class).list();
	
		    if(sg.size() > 0){ 
		    	/* for (int i = 0; i < r.size(); i++) {
       		Room label = (Room) r.get(i);
        		System.out.println("Label = " + label.toString());}*/
		        return sg;
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
