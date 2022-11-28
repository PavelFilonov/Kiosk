package com.example.kiosk.repository;

import com.example.kiosk.entity.Subject;
import com.example.kiosk.entity.TypeSubject;
import com.example.kiosk.entity.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Long> {

	List<Subject> findAllByUser(User user);

	List<Subject> findByTitle(String title);

	List<Subject> findByAuthor(String author);

	List<Subject> findByPublisher(String publisher);

	List<Subject> findByUserAndTypeSubject(User user, TypeSubject typeSubject);

}
