package com.secure.notes.repositories;

import com.secure.notes.models.Note;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.FluentQuery;

import java.util.List;
import java.util.function.Function;

public interface NoteRepository extends JpaRepository<Note, Long> {

   List<Note> findByOwnerUsername(String ownerUsername);
}
