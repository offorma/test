package com.project.test.repositories;

import java.util.List;

import com.project.test.model.Module;


public interface ModuleRepository {
	public void create(Module module);
	public void update(long moduleId, Module module);
	public Module edit (Long Id);
	public Module findModule (Long Id);
	public Module findByModuleName(String un);
	public void delete (Long Id);
	public List<Module> getAll();
}
