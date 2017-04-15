package com.project.test.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.test.model.StudentGroup;
import com.project.test.repositories.StudentGroupRepositoryImpl;

@Service
public class StudentGroupServiceImpl implements StudentGroupService {
	@Autowired
	StudentGroupRepositoryImpl studentRepo;
	
	public void create(StudentGroup user) {
		// TODO Auto-generated method stub
		studentRepo.create(user);
	}

	@Override
	public void update(long sgId, StudentGroup sg) {
		studentRepo.update(sgId, sg);
	}

	@Override
	public StudentGroup findStudentGroup(Long studentId) {
		return studentRepo.findStudentGroup(studentId);
	}

	@Override
	public StudentGroup findByGroupName(String un) {
		return studentRepo.findByGroupName(un);
	}

	@Override
	public void delete(Long Id) {
		studentRepo.delete(Id);
	}

	@Override
	public List<StudentGroup> getAll() {
		return studentRepo.getAll();
	}

}
