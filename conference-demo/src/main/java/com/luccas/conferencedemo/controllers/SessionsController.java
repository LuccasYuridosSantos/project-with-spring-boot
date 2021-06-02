package com.luccas.conferencedemo.controllers;

import com.luccas.conferencedemo.models.Session;
import com.luccas.conferencedemo.repositories.SessionRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/sessions")
public class SessionsController {

	@Autowired
	private SessionRepository sessionRepository;

	@GetMapping
	public List<Session> list (){
		return sessionRepository.findAll();
	}

	@GetMapping("/{id}")
	public Optional<Session> get(@PathVariable Long id) {
		return sessionRepository.findById(id);
	}

	@PostMapping
	public Session create(@RequestBody final Session session) {
		return sessionRepository.saveAndFlush(session);
	}

	@PutMapping("/{id}")
	public Session update(@PathVariable Long id, @RequestBody final Session session){
		Session existingSession = sessionRepository.getOne(id);
		BeanUtils.copyProperties(session, existingSession, "session_id");
		return sessionRepository.saveAndFlush(existingSession);
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		sessionRepository.deleteById(id);
	}

}
