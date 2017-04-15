package com.project.test.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.test.model.Tutor;
import com.project.test.repositories.TutorRepositoryImpl;

@Service
public class TutorServiceImpl implements TutorService {
@Autowired
TutorRepositoryImpl tutorRepo;
	
@Override
	public void create(Tutor tutor) {
		// TODO Auto-generated method stub
		tutorRepo.create(tutor);
	}

@Override
	public void update(long tutorId, Tutor tutor) {
		// TODO Auto-generated method stub
		tutorRepo.update(tutorId, tutor);
	}

@Override
	public Tutor findTutor(Long tuId) {
		// TODO Auto-generated method stub
		return tutorRepo.findTutor(tuId);
	}
@Override
	public void delete(Long Id) {
		// TODO Auto-generated method stub
		tutorRepo.delete(Id);
	}
@Override
	public List<Tutor> getAll() {
		// TODO Auto-generated method stub
		return tutorRepo.getAll();
	}

}
