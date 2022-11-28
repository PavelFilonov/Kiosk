package com.example.kiosk.service;

import com.example.kiosk.entity.Subject;
import com.example.kiosk.entity.TypeSubject;
import com.example.kiosk.exception.BookException;
import com.example.kiosk.exception.SubjectException;
import com.example.kiosk.repository.SubjectRepository;
import com.example.kiosk.repository.UserRepository;
import com.example.kiosk.validator.BookValidator;
import com.example.kiosk.validator.SubjectValidator;
import org.springframework.stereotype.Service;

@Service
public class BookService extends SubjectService {

	private final BookValidator bookValidator;

	public BookService(SubjectRepository subjectRepository, UserRepository userRepository,
			SubjectValidator subjectValidator, BookValidator bookValidator) {
		super(subjectRepository, userRepository, subjectValidator);
		this.bookValidator = bookValidator;
	}

	@Override
	public TypeSubject getTypeSubject() {
		return TypeSubject.BOOK;
	}

	@Override
	void proxySave(Subject subject) throws BookException {
		bookValidator.validateBook(subject);
	}

	@Override
	void proxyUpdate(Long id, Subject subject) throws BookException, SubjectException {
		bookValidator.validateBook(subject);
		Subject subjectFromDb = findById(id);
		subjectFromDb.setAmount(subject.getAmount());
		subjectFromDb.setTitle(subject.getTitle());
		subjectFromDb.setAuthor(subject.getAuthor());
		subjectFromDb.setPublisher(subject.getPublisher());
		subjectFromDb.setNumberPage(subject.getNumberPage());
	}

}
