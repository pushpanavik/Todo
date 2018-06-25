package com.bridgeit.service;

import java.util.List;

import com.bridgeit.model.Note;

public interface INoteService {

	public void addNote(Note note,int userId);
	public void deleteNode(Note note,long id);
	public void updateNode(Note note,long id);
	public List<Note> displayAllNote(Note note);
}
