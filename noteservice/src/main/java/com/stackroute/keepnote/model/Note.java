package com.stackroute.keepnote.model;

import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

//@Document(collection = "note")
public class Note extends NoteUser{

	/*
	 * This class should have eight fields
	 * (noteId,noteTitle,noteContent,noteStatus,createdAt,
	 * category,reminder,createdBy). This class should also contain the
	 * getters and setters for the fields along with the no-arg , parameterized
	 * constructor and toString method. The value of createdAt should not be
	 * accepted from the user but should be always initialized with the system date.
	 * 
	 */

	private int noteId;

	private String noteTitle;

	private String noteContent;

	private String noteStatus;

	private Date noteCreationDate;

	private String noteCreatedBy;

	private List<Category> categories;

	private List<Reminder> reminders;
	
	private boolean isCategoriesExist;
	
	private boolean isRemindersExist;

	public Note() {
		super();
	}

	public Note(String userId, List<Note> notes, int noteId, String noteTitle, String noteContent, String noteStatus, Date createdAt,
				String createdBy, List<Category> categories, List<Reminder> reminders) {
		super(createdBy, notes);
		this.noteId = (int)(Math.random() * 50 + 1);
		this.noteTitle = noteTitle;
		this.noteContent = noteContent;
		this.noteStatus = noteStatus;
		this.noteCreationDate = createdAt;
		this.noteCreatedBy = createdBy;
		this.categories = categories;
		this.reminders = reminders;
	}

	// getters & setters
	public int getNoteId() {	
		return noteId;
	}

	public void setNoteId(int noteId) {
		this.noteId = (int)(Math.random() * 50 + 1);
	}

	public String getNoteTitle() {
		return noteTitle;
	}

	public void setNoteTitle(String noteTitle) {
		this.noteTitle = noteTitle;
	}

	public String getNoteContent() {
		return noteContent;
	}

	public void setNoteContent(String noteContent) {
		this.noteContent = noteContent;
	}

	public String getNoteStatus() {
		return noteStatus;
	}

	public void setNoteStatus(String noteStatus) {
		this.noteStatus = noteStatus;
	}

	public Date getNoteCreationDate() {
		return noteCreationDate;
	}

	public void setNoteCreationDate(Date noteCreationDate) {
		this.noteCreationDate = noteCreationDate;
	}

	public String getNoteCreatedBy() {
		return noteCreatedBy;
	}

	public void setNoteCreatedBy(String noteCreatedBy) {
		this.noteCreatedBy = noteCreatedBy;
	}



	public List<Category> getCategories() {
		return categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}

	public List<Reminder> getReminders() {
		return reminders;
	}

	public void setReminders(List<Reminder> reminders) {
		this.reminders = reminders;
	}
		
	public boolean isCategoriesExist() {
		return isCategoriesExist;
	}

	public void setCategoriesExist(boolean isCategoriesExist) {
		this.isCategoriesExist = isCategoriesExist;
	}

	public boolean isRemindersExist() {
		return isRemindersExist;
	}

	public void setRemindersExist(boolean isRemindersExist) {
		this.isRemindersExist = isRemindersExist;
	}

	@Override
	public String toString() {
		return "Note{" +
				"noteId=" + noteId +
				", noteTitle='" + noteTitle + '\'' +
				", noteContent='" + noteContent + '\'' +
				", noteStatus='" + noteStatus + '\'' +
				", createdAt=" + noteCreationDate +
				", createdBy='" + noteCreatedBy + '\'' +
				", categories=" + categories +
				", reminders=" + reminders +
				'}';
	}
}
