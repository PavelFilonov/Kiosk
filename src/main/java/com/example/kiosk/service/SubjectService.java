package com.example.kiosk.service;

import com.example.kiosk.entity.Subject;
import com.example.kiosk.entity.TypeSubject;
import com.example.kiosk.exception.BookException;
import com.example.kiosk.exception.JournalException;
import com.example.kiosk.exception.NewspaperException;
import com.example.kiosk.exception.SubjectException;
import com.example.kiosk.repository.SubjectRepository;
import com.example.kiosk.repository.UserRepository;
import com.example.kiosk.validator.SubjectValidator;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class SubjectService {

	protected final SubjectRepository subjectRepository;

	protected final UserRepository userRepository;

	private final SubjectValidator subjectValidator;

	public List<Subject> findAll(String username) {
		return subjectRepository.findAllByUser(userRepository.findByUsername(username));
	}

	public List<Subject> findAllCategory(String username, TypeSubject typeSubject) {
		return subjectRepository.findByUserAndTypeSubject(userRepository.findByUsername(username), typeSubject);
	}

	public abstract TypeSubject getTypeSubject();

	public Subject save(Subject subject, String username)
			throws SubjectException, BookException, JournalException, NewspaperException {
		subjectValidator.validateSubject(subject);
		proxySave(subject);
		subject.setUser(userRepository.findByUsername(username));
		return subjectRepository.save(subject);
	}

	abstract void proxySave(Subject subject) throws BookException, NewspaperException, JournalException;

	public void delete(Long id) throws SubjectException {
		findById(id);
		subjectRepository.deleteById(id);
	}

	public void update(Long id, Subject subject)
			throws SubjectException, BookException, JournalException, NewspaperException {
		subjectValidator.validateSubject(subject);
		proxyUpdate(id, subject);
	}

	abstract void proxyUpdate(Long id, Subject subject)
			throws BookException, SubjectException, NewspaperException, JournalException;

	public Subject findById(Long id) throws SubjectException {
		Optional<Subject> subject = subjectRepository.findById(id);
		if (subject.isEmpty()) {
			throw new SubjectException(String.format("Not found subject with id %s", id));
		}
		return subject.get();
	}

}
