package com.projeto.oficina.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projeto.oficina.services.StatusService;

@RestController
@RequestMapping("/status")
public class StatusController {
	
	@Autowired
	StatusService statusService;
	
	@GetMapping("/{id}")
	public ResponseEntity<?> findById(@PathVariable Integer id){
		HttpHeaders headers = new HttpHeaders();
		return new ResponseEntity<>(statusService.findById(id), headers, HttpStatus.OK);
	}
}
