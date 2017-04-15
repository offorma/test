package com.project.test.service;

import java.util.List;

import com.project.test.model.StudentGroup;

public interface StudentGroupService {
	public void create(StudentGroup student);
	public void update(long sgId, StudentGroup sg);
	public StudentGroup findStudentGroup(Long studentId);
	public StudentGroup findByGroupName(String gname);
	public void delete (Long Id);
	public List<StudentGroup> getAll();
}
