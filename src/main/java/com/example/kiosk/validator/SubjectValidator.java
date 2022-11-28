package com.example.kiosk.validator;

import com.example.kiosk.entity.Subject;
import com.example.kiosk.exception.SubjectException;
import java.util.Objects;
import org.springframework.stereotype.Service;

@Service
public class SubjectValidator {

	public void validateSubject(Subject subject) throws SubjectException {
		validateTitle(subject.getTitle());
		validateAmount(subject.getAmount());
	}

	private void validateTitle(String title) throws SubjectException {
		if (Objects.isNull(title) || title.isBlank()) {
			throw new SubjectException("Blank title");
		}
	}

	private void validateAmount(int amount) throws SubjectException {
		if (amount < 0) {
			throw new SubjectException("Amount can`t be negative");
		}
	}

}
