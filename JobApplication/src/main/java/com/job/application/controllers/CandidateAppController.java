package com.job.application.controllers;

import java.io.IOException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.job.application.entities.LoadFile;
import com.job.application.entities.CandidateApp;
import com.job.application.iservices.IServiceCandidateApp;


@CrossOrigin(origins = "*")
@RequestMapping( "/CandidateApp" )
@RestController
public class CandidateAppController {

	@Autowired
	private IServiceCandidateApp iCandidateApp;
	
	@GetMapping(path = "")
	public List<CandidateApp> getAllApp() {
		return iCandidateApp.getAllapplications(); 
	}
	
	@GetMapping(path = "/{idCandidateApp}")
	public CandidateApp getCandidateAppById(@PathVariable(name = "idCandidateApp") String idCandidateApp) {
		return iCandidateApp.getCandidateById(idCandidateApp); 
	}
	
	@GetMapping(path = "/byJob/{idJob}")
	public List<CandidateApp> getCandidateAppByIdJob(@PathVariable(name = "idJob") String idJob) {
		return iCandidateApp.getCandidateByJob(idJob); 
	}
	
	@PostMapping(path ="/{idJob}/{id}")
    public ResponseEntity<?> addApp(@RequestBody  @Valid  CandidateApp app,@PathVariable(name = "idJob") String idJob,@PathVariable(name = "id") String id) throws IOException {
		try {
			
			CandidateApp appResponse = iCandidateApp.addApp(app,idJob,id);
			
			return new ResponseEntity<CandidateApp>(appResponse, HttpStatus.CREATED);
		}
		catch(Exception e)
		{  
		   return new ResponseEntity<>( e.getMessage(),HttpStatus.ACCEPTED);
		}
		  
		
    }
	
	
	 @PostMapping("/upload")
	    public ResponseEntity<?> upload(@RequestParam("file")MultipartFile file) throws IOException {
	        return new ResponseEntity<>(iCandidateApp.addFile(file), HttpStatus.OK);
	    }

  
	  @GetMapping("/download/{id}")
	    public ResponseEntity<ByteArrayResource> download(@PathVariable String id) throws IOException {
	        LoadFile loadFile = iCandidateApp.downloadFile(id);

	        return ResponseEntity.ok()
	                .contentType(MediaType.parseMediaType(loadFile.getFileType() ))
	                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + loadFile.getFilename() + "\"")
	                .body(new ByteArrayResource(loadFile.getFile()));
	    }
	  
	
}
