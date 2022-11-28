package com.example.kiosk.validator;

import com.example.kiosk.entity.Subject;
import com.example.kiosk.exception.NewspaperException;
import java.time.LocalDate;
import org.springframework.stereotype.Service;

@Service
public class NewspaperValidator {

	public void validateNewspaper(Subject newspaper) throws NewspaperException {
		validateNumber(newspaper.getNumber());
		validateDateRelease(newspaper.getDateRelease());
	}

	private void validateNumber(int number) throws NewspaperException {
		if (number < 1) {
			throw new NewspaperException("Number can`t be negative or zero");
		}
	}

	private void validateDateRelease(LocalDate dateRelease) throws NewspaperException {
		if (LocalDate.now().isBefore(dateRelease)) {
			throw new NewspaperException("Date release can`t be early than now");
		}
	}

}
