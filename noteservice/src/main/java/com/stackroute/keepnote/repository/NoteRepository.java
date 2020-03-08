package com.stackroute.keepnote.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.stackroute.keepnote.model.Category;
import com.stackroute.keepnote.model.Note;
import com.stackroute.keepnote.model.NoteUser;

/*
* This class is implementing the MongoRepository interface for Note.
* Annotate this class with @Repository annotation
* */

public interface NoteRepository extends MongoRepository<Note, String> {
	
	List<Note> findAllNotesByNoteCreatedBy(String noteCreatedBy);
	
	Long deleteNotesByNoteId(int noteId);

}
