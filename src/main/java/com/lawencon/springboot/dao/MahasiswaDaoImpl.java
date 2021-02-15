package com.lawencon.springboot.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.lawencon.springboot.model.Mahasiswa;
import com.lawencon.springboot.repo.MahasiswaRepo;

@Repository
public class MahasiswaDaoImpl implements MahasiswaDao {

	@Autowired
	private MahasiswaRepo mhsRepo;

	@Override
	public List<Mahasiswa> getAllMhs() throws Exception {
		return mhsRepo.findAll();
	}
}
