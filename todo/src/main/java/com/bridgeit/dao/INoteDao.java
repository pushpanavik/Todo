package com.bridgeit.dao;

import java.util.List;

import com.bridgeit.model.Note;

public interface INoteDao {
	public long addNote(Note note);
	public void deleteNode(Note note,long id);
	public void updateNode(Note note ,long id);
	public List<Note> displayAllNote(Note note);
}
