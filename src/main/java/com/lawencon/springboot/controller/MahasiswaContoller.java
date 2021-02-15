package com.lawencon.springboot.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.springboot.model.Mahasiswa;
import com.lawencon.springboot.service.MahasiswaService;
import com.lawencon.springboot.util.JasperUtil;

/**
 * @author lawencon05
 */
@RestController
@RequestMapping("/mhs")
public class MahasiswaContoller {

	@Autowired
	private MahasiswaService mahasiswaService;

	@GetMapping("/report")
	public HttpEntity<?> reportSample() {
		List<Mahasiswa> listData = new ArrayList<>();
		byte[] out;
		try {
			listData = mahasiswaService.getAllMhs();
			out = JasperUtil.responseToByteArray(listData, "sample1", null);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_PDF);
		return new HttpEntity<>(out, headers);
	}

}
