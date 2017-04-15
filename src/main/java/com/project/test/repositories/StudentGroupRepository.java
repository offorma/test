package com.project.test.repositories;

import java.util.List;


import com.project.test.model.StudentGroup;

public interface StudentGroupRepository {
	
	public void create(StudentGroup user);
	public void update(long sgId, StudentGroup sg);
	public StudentGroup findStudentGroup(Long tutorId);
	public StudentGroup findByGroupName(String un);
	public void delete (Long Id);
	public List<StudentGroup> getAll();
}
