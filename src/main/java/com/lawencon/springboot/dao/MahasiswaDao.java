package com.lawencon.springboot.dao;

import java.util.List;

import com.lawencon.springboot.model.Mahasiswa;

public interface MahasiswaDao {

	List<Mahasiswa> getAllMhs() throws Exception;

}
