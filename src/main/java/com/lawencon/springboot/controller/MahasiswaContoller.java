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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.lawencon.springboot.model.Mahasiswa;
import com.lawencon.springboot.service.MahasiswaService;
import com.lawencon.util.JasperUtil;

/**
 * @author lawencon05
 */
@RestController
@RequestMapping("/mhs")
public class MahasiswaContoller {

	@Autowired
	private MahasiswaService mahasiswaService;

	@GetMapping("all")
	public ResponseEntity<?> getAllMhs() {
		List<Mahasiswa> listMhs = new ArrayList<>();
		try {
			listMhs = mahasiswaService.getAllMhsCustom();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(listMhs, HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<?> getMhs(@RequestParam("id") String id, @RequestParam("name") String name) {
		Long realId = Long.valueOf(id);
		return new ResponseEntity<>("Get data : " + realId + " with " + name, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<?> insert(@RequestBody String body) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			mapper.registerModule(new JavaTimeModule()); // for localdate or localdatetime
			Mahasiswa mhs = mapper.readValue(body, Mahasiswa.class);
			mahasiswaService.insert(mhs);
			return new ResponseEntity<>(mhs, HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Failed", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

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
