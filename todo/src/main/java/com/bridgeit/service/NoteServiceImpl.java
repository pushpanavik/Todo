package com.bridgeit.service;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bridgeit.controller.NoteController;
import com.bridgeit.dao.INoteDao;
import com.bridgeit.model.Note;
import com.bridgeit.model.User;

@Service
public class NoteServiceImpl implements INoteService {

	@Autowired
	private INoteDao noteDao;
	
	
	@Transactional

	public void addNote(Note note,int userId) {
	
		User u =new User();
		u.setUserId(userId);
		note.setUser(u);
		
		Date createdAt=new Date();
		note.setCreatedAt(createdAt);
		note.setUpdatedAt(createdAt);
		
		noteDao.addNote(note);
	
	}
	
	@Transactional
	
	public void deleteNode(Note note,long id) {
		noteDao.deleteNode(note, id);
		
	}
	
	@Transactional
	
	public void updateNode(Note note,long id) {
		noteDao.updateNode(note,id);
		
	}
	
	
	
	public List<Note> displayAllNote(Note note) {
		List<Note> displaylistOfNotes=noteDao.displayAllNote(note);
		for(Note displayAll: displaylistOfNotes)
		{
			System.out.println(displayAll.toString());
		}
		return displaylistOfNotes;
	}

	
}
