package com.project.test.repositories;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.project.test.model.Module;


@Repository("moduleRepository")
public class ModuleRepositoryImpl implements ModuleRepository{

	@Autowired
	SessionFactory sessionFactory;
	
	Session session;
	
	
	public void setSessionFactory(SessionFactory sessionFactory){
		this.sessionFactory=sessionFactory;
	}
	@Override
	public void create(Module module) {
		 session = sessionFactory.openSession();
		Transaction tx= session.beginTransaction();
		if(module !=null){
		
			try{
				session.save(module);
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
	public void update(long moduleId, Module module) {
		session = sessionFactory.openSession();
		Transaction tx= session.beginTransaction();
		Module m = new Module();
		try{
			m=(Module) session.get(Module.class, moduleId);
			m.setModuleCode(module.getModuleCode());
			m.setModuleName(module.getModuleName());
			m.setTotalWeeklyHour(module.getTotalWeeklyHour());
			
			session.update(m); 
	        tx.commit();
	     	session.close();
		}catch(Exception e){
			tx.rollback();
			session.close();
			e.printStackTrace();
			
		}
	
	}
	@Override
	public Module edit(Long Id) {
		// TODO Auto-generated method stub
		return null;
	}
	public Module findModule(Long moduleId) {
		session = sessionFactory.openSession();
		Transaction tx= session.beginTransaction();
		Module m = new Module();
		try{
		
		 m=(Module) session.get(Module.class, moduleId);
		tx.commit();
		session.close();
		
	}catch(Exception e){
		tx.rollback();
		session.close();
		e.printStackTrace();
		
	}
		return m;
		
	}
	@Override
	public Module findByModuleName(String un) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void delete(Long Id) {
		// TODO Auto-generated method stub
		session = sessionFactory.openSession();
		Transaction tx= session.beginTransaction();
	      try{
	         Module module= 
	                   (Module)session.get(Module.class, Id); 
	         session.delete(module); 
	         tx.commit();
	      }catch(Exception e){
	  		tx.rollback();
	  		session.close();
	  		e.printStackTrace();
	  		
	  	}
		
	}

	@Override
	public List<Module> getAll() {
		session = sessionFactory.openSession();
	     Transaction tx= session.beginTransaction();
	try{
		
		Query query = session.createQuery("from Module");
		@SuppressWarnings("unchecked")
		ArrayList<Module> m = (ArrayList<Module>) query.list();
				//(List<User>) session.createCriteria(User.class).list();
	
		    if(m.size() > 0){ 
		    	/* for (int i = 0; i < r.size(); i++) {
       		Room label = (Room) r.get(i);
        		System.out.println("Label = " + label.toString());}*/
		        return m;
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
