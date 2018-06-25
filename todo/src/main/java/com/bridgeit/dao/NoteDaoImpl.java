package com.bridgeit.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bridgeit.model.Note;

@Repository
public class NoteDaoImpl implements INoteDao {

	@Autowired
	private SessionFactory factory;
	
	
	public long addNote(Note note) {
		
		Session getSession=(Session) factory.getCurrentSession();
		getSession.save(note);
		return note.getId();
	}

	
	public void deleteNode(Note note,long id) {
		Session getSession=factory.getCurrentSession();
		Note note2=getSession.byId(Note.class).load(id);
		getSession.delete(note2);

	}

	
	public void updateNode(Note note,long id) {
		Session getSession=factory.getCurrentSession();
		Note note2=getSession.byId(Note.class).load(id);
		getSession.update(note2);		
	}

	@SuppressWarnings({ "deprecation", "unchecked" })
	
	
	public List<Note> displayAllNote(Note note) {
		Session session = factory.getCurrentSession();
		Criteria cr=session.createCriteria(Note.class);
		List<Note> results=cr.list();
		return results;
	}


}
