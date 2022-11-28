package com.example.kiosk.controller;

import com.example.kiosk.dto.SubjectDTO;
import com.example.kiosk.entity.TypeSubject;
import com.example.kiosk.exception.BookException;
import com.example.kiosk.exception.JournalException;
import com.example.kiosk.exception.NewspaperException;
import com.example.kiosk.exception.SubjectException;
import com.example.kiosk.mapper.SubjectMapper;
import com.example.kiosk.service.SubjectService;
import java.security.Principal;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
@RequestMapping("/subjects")
public class SubjectController {

	private final List<SubjectService> subjectServices;

	private final SubjectMapper subjectMapper;

	@GetMapping
	public String getAll(Principal principal, Map<String, Object> model) {
		List<SubjectDTO> subjects = subjectMapper.mapListToDTO(subjectServices.get(0).findAll(principal.getName()));
		model.put("subjects", subjects);
		return "subjects";
	}

	@GetMapping("/category")
	public String getAllByCategory(Principal principal, Map<String, Object> model,
			@RequestParam TypeSubject typeSubject) {
		List<SubjectDTO> subjects = subjectMapper.mapListToDTO(subjectServices.stream()
				.filter(subjectService -> subjectService.getTypeSubject().equals(typeSubject))
				.findFirst().get()
				.findAllCategory(principal.getName(), typeSubject));
		model.put("subjects", subjects);
		return "subjects";
	}

	@GetMapping("/{id}")
	public String get(@PathVariable Long id, Map<String, Object> model) {
		try {
			SubjectDTO subject = subjectMapper.map(subjectServices.get(0).findById(id));
			model.put("subject", subject);
		} catch (SubjectException e) {
			model.put("error", e.getMessage());
			return "subjects";
		}
		return "subject";
	}

	@GetMapping("/new")
	public String newSubject(@ModelAttribute("subject") SubjectDTO subject) {
		return "newSubject";
	}

	@PostMapping("/new")
	public String newSubject(@ModelAttribute("subject") SubjectDTO subject, @RequestParam String typeSubject,
			Map<String, Object> model) {
		model.put("typeSubject", typeSubject);
		return "newSubject";
	}

	@PostMapping
	public String add(Principal principal, Map<String, Object> model,
			@ModelAttribute("subject") SubjectDTO subject, @RequestParam TypeSubject typeSubject) {
		subject.setTypeSubject(typeSubject);
		try {
			subjectServices.stream()
					.filter(subjectService -> subjectService.getTypeSubject().equals(typeSubject))
					.findFirst().get()
					.save(subjectMapper.map(subject), principal.getName());
			model.put("message", "Subject was added");
		} catch (SubjectException | BookException | JournalException | NewspaperException e) {
			model.put("error", e.getMessage());
		}
		return getAll(principal, model);
	}

	@DeleteMapping("/{id}")
	public String delete(@PathVariable Long id, Map<String, Object> model, Principal principal,
			@ModelAttribute("subject") SubjectDTO subject) {
		try {
			subjectServices.get(0).delete(id);
			model.put("message", "Subject deleted");
		} catch (SubjectException e) {
			model.put("error", e.getMessage());
		}
		return getAll(principal, model);
	}

	@PutMapping("/{id}")
	public String update(@PathVariable Long id, @ModelAttribute("subject") SubjectDTO subject,
			Map<String, Object> model, TypeSubject typeSubject) {
		try {
			subjectServices.stream()
					.filter(subjectService -> subjectService.getTypeSubject().equals(typeSubject))
					.findFirst().get()
					.update(id, subjectMapper.map(subject));
			model.put("message", "Subject updated");
		} catch (JournalException | SubjectException | BookException | NewspaperException e) {
			model.put("error", e.getMessage());
		}
		return get(id, model);
	}

}
