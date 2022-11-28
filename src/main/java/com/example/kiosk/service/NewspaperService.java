package com.example.kiosk.service;

import com.example.kiosk.entity.Subject;
import com.example.kiosk.entity.TypeSubject;
import com.example.kiosk.exception.NewspaperException;
import com.example.kiosk.exception.SubjectException;
import com.example.kiosk.repository.SubjectRepository;
import com.example.kiosk.repository.UserRepository;
import com.example.kiosk.validator.NewspaperValidator;
import com.example.kiosk.validator.SubjectValidator;
import org.springframework.stereotype.Service;

@Service
public class NewspaperService extends SubjectService {

	private final NewspaperValidator newspaperValidator;

	public NewspaperService(SubjectRepository subjectRepository,
			UserRepository userRepository,
			SubjectValidator subjectValidator, NewspaperValidator newspaperValidator) {
		super(subjectRepository, userRepository, subjectValidator);
		this.newspaperValidator = newspaperValidator;
	}


	@Override
	public TypeSubject getTypeSubject() {
		return TypeSubject.NEWSPAPER;
	}

	@Override
	void proxySave(Subject subject) throws NewspaperException {
		newspaperValidator.validateNewspaper(subject);
	}

	@Override
	void proxyUpdate(Long id, Subject subject) throws NewspaperException, SubjectException {
		newspaperValidator.validateNewspaper(subject);
		Subject subjectFromDb = findById(id);
		subjectFromDb.setAmount(subject.getAmount());
		subjectFromDb.setTitle(subject.getTitle());
		subjectFromDb.setNumber(subject.getNumber());
		subjectFromDb.setDateRelease(subject.getDateRelease());
	}

}
