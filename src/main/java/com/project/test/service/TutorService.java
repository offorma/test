package com.project.test.service;

import java.util.List;

import com.project.test.model.Tutor;

public interface TutorService {
	public void create(Tutor tutor);
	public void update(long tutorId, Tutor tutor);
	public Tutor findTutor(Long tuId);
	public void delete (Long Id);
	public List<Tutor> getAll();
}
