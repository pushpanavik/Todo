package com.bridgeit.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bridgeit.model.Note;
import com.bridgeit.service.INoteService;
import com.bridgeit.util.Response;
import com.bridgeit.util.ValidateNote;

@RestController
public class NoteController {

	@Autowired
	private INoteService noteService;
	
	
	@RequestMapping( value="/addNote", method=RequestMethod.POST)
	public ResponseEntity<?> createNote(@RequestBody  Note note,HttpServletRequest request ) {
		System.out.println(1);
	int userId=(Integer) request.getAttribute("userId");
	System.out.println(2);
	Response response=new Response();
	System.out.println(3);
	boolean noteStatus	=ValidateNote.validateNote(note);
	System.out.println(4);
	if(noteStatus==true)
	{
		System.out.println(5);
		noteService.addNote(note,userId);
	
		response.setMsg("Note successfuuly added");
		response.setStatus(200);
		return new ResponseEntity<String>("Note successfully added", HttpStatus.CREATED);
	}
	else
	{
		System.out.println(6);
		response.setMsg("Note cannot be empty");
		response.setStatus(-9);
		return new ResponseEntity<String>("Note is Empty",HttpStatus.NO_CONTENT);
	}
	}	
	

	@RequestMapping(value="/update/{id}", method=RequestMethod.PUT)
	public ResponseEntity<?> updateNote(@RequestBody Note note, @PathVariable("id") long id,HttpServletRequest request){
		//int userId=(Integer)request.getAttribute("userId");
		
		noteService.updateNode(note,id);
		
		return new ResponseEntity<String>("Note Succesfully updated",HttpStatus.ACCEPTED);
		
	}
	
	@RequestMapping(value="/delete/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<?> deleteNote(@RequestBody Note note, @PathVariable("id") long id, HttpServletResponse response){
		
		noteService.deleteNode(note, id);
		return new ResponseEntity<String>("Specified note successfully deleted",HttpStatus.OK);
	}
	
	
	@GetMapping("/displayNote")
	public ResponseEntity<?> ListNote(Note note)
	{ 
		List<Note> not = noteService.displayAllNote(note);
		return ResponseEntity.ok().body(not);
		
	}

}
