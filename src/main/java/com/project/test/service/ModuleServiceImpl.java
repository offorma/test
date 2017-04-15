package com.project.test.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.test.model.Module;
import com.project.test.repositories.ModuleRepositoryImpl;
@Service
public class ModuleServiceImpl implements ModuleService {
@Autowired
ModuleRepositoryImpl moduleRepo;
	@Override
	public void create(Module module) {
		// TODO Auto-generated method stub
		moduleRepo.create(module);
	}

	@Override
	public void update(long moduleId, Module module) {
		// TODO Auto-generated method stub
		moduleRepo.update(moduleId, module);
	}

	@Override
	public Module edit(Long Id) {
		// TODO Auto-generated method stub
		return moduleRepo.edit(Id);
	}

	@Override
	public Module findModule(Long Id) {
		// TODO Auto-generated method stub
		return moduleRepo.findModule(Id);
	}

	@Override
	public Module findByModuleName(String un) {
		// TODO Auto-generated method stub
		return moduleRepo.findByModuleName(un);
	}

	@Override
	public void delete(Long Id) {
		// TODO Auto-generated method stub
		moduleRepo.delete(Id);
	}

	@Override
	public List<Module> getAll() {
		// TODO Auto-generated method stub
		return moduleRepo.getAll();
	}

}
