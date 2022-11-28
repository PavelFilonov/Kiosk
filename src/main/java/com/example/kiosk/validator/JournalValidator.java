package com.example.kiosk.validator;

import com.example.kiosk.entity.Subject;
import com.example.kiosk.exception.JournalException;
import java.time.LocalDate;
import org.springframework.stereotype.Service;

@Service
public class JournalValidator {

	public void validateJournal(Subject journal) throws JournalException {
		validateNumber(journal.getNumber());
		validateDateRelease(journal.getDateRelease());
		validateNumberPage(journal.getNumberPage());
	}

	private void validateNumber(int number) throws JournalException {
		if (number < 1) {
			throw new JournalException("Number can`t be negative or zero");
		}
	}

	private void validateDateRelease(LocalDate dateRelease) throws JournalException {
		if (LocalDate.now().isBefore(dateRelease)) {
			throw new JournalException("Date release can`t be early than now");
		}
	}

	private void validateNumberPage(int numberPage) throws JournalException {
		if (numberPage < 1) {
			throw new JournalException("Number page can`t be negative");
		}
	}

}
