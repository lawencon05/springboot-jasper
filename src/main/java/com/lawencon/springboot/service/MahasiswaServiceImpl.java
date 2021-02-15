package com.lawencon.springboot.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.springboot.dao.MahasiswaDao;
import com.lawencon.springboot.model.Mahasiswa;

@Service
@Transactional
public class MahasiswaServiceImpl implements MahasiswaService {

	@Autowired
	private MahasiswaDao mahasiswaDao;

	@Override
	public List<Mahasiswa> getAllMhs() throws Exception {
		return mahasiswaDao.getAllMhs();
	}
}
