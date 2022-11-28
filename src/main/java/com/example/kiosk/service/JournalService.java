package com.example.kiosk.service;

import com.example.kiosk.entity.Subject;
import com.example.kiosk.entity.TypeSubject;
import com.example.kiosk.exception.JournalException;
import com.example.kiosk.exception.SubjectException;
import com.example.kiosk.repository.SubjectRepository;
import com.example.kiosk.repository.UserRepository;
import com.example.kiosk.validator.JournalValidator;
import com.example.kiosk.validator.SubjectValidator;
import org.springframework.stereotype.Service;

@Service
public class JournalService extends SubjectService {

	private final JournalValidator journalValidator;

	public JournalService(SubjectRepository subjectRepository, UserRepository userRepository,
			SubjectValidator subjectValidator, JournalValidator journalValidator) {
		super(subjectRepository, userRepository, subjectValidator);
		this.journalValidator = journalValidator;
	}

	@Override
	public TypeSubject getTypeSubject() {
		return TypeSubject.JOURNAL;
	}

	@Override
	void proxySave(Subject subject) throws JournalException {
		journalValidator.validateJournal(subject);
	}

	@Override
	void proxyUpdate(Long id, Subject subject) throws SubjectException, JournalException {
		journalValidator.validateJournal(subject);
		Subject subjectFromDb = findById(id);
		subjectFromDb.setAmount(subject.getAmount());
		subjectFromDb.setTitle(subject.getTitle());
		subjectFromDb.setNumber(subject.getNumber());
		subjectFromDb.setDateRelease(subject.getDateRelease());
		subjectFromDb.setNumberPage(subject.getNumberPage());
	}

}
