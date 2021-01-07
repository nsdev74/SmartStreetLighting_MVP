package com.emsi.mvp;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class postsController {
	private String result;
	private String result2;
	private final AtomicLong counter= new AtomicLong();
	SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
	posts defaultPost1 = new posts(counter.incrementAndGet(),34.005,-6.855,"Broken glass;<br> Unavailable since: "+formatter.format(System.currentTimeMillis()),0);
	posts defaultPost2 = new posts(counter.incrementAndGet(),34.027,-6.813,"Idle",1);
	posts defaultPost3 = new posts(counter.incrementAndGet(),34.01,-6.824,"Idle",1);
	posts defaultPost4 = new posts(counter.incrementAndGet(),34.013,-6.81,"Active",2);
	posts defaultPost5 = new posts(counter.incrementAndGet(),34.02,-6.82,"Active",2);
	posts defaultPost6 = new posts(counter.incrementAndGet(),34.007,-6.85,"Idle",1);
	posts defaultPost7 = new posts(counter.incrementAndGet(),34.03,-6.79,"Broken LED;<br> Unavailable since: "+formatter.format(System.currentTimeMillis()),0);
	
	public List<posts> postes = new ArrayList<>(Arrays.asList(defaultPost1,defaultPost2,defaultPost3,defaultPost4,defaultPost5,defaultPost6,defaultPost7));
	
	@GetMapping("/Postes")
	public ResponseEntity<String> getAllCourses(@RequestParam(required=false) String title)
	{
		final HttpHeaders httpHeaders= new HttpHeaders();
	    httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		
		result = "[\n";
		postes.forEach( 
				(n) -> result+=n.toString()+",\n"
				);
		result2= result.substring(0, result.length()-2);
		result2+="\n]";
		
		return new ResponseEntity<>(result2, httpHeaders, HttpStatus.OK);
	}
	
	@PostMapping("/Postes")
	public ResponseEntity<String> register(@RequestBody posts newPost) {
				newPost.setId(counter.incrementAndGet());
				postes.add(newPost);
				return new ResponseEntity<>("Post created.", HttpStatus.CREATED);
	}

	@PutMapping("/Postes/{id}")
	public ResponseEntity<String> update(@PathVariable("id") long reqId, @RequestBody posts updatedPost) {

		final HttpHeaders httpHeaders= new HttpHeaders();
	    httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		
	    postes.forEach((n)-> 
	    {
	    	if(n.getId()==reqId)
	    	{
	    		n.setIcon(updatedPost.getIcon());
	    		n.setLongitude(updatedPost.getLongitude());
	    		n.setLatitude(updatedPost.getLatitude());
	    		n.setMessage(updatedPost.getMessage());
	    	}
	    });
		return new ResponseEntity<>("Post Updated.", HttpStatus.OK);

	}
	 
}
