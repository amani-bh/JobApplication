package com.job.application.services;

import java.io.IOException;
import java.util.List;


import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsOperations;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.job.application.entities.CandidateApp;
import com.job.application.entities.LoadFile;
import com.job.application.iservices.IServiceCandidateApp;
import com.job.application.repositories.CandidateAppRepository;
import com.job.application.repositories.JobOfferRepository;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.client.gridfs.model.GridFSFile;
import org.apache.commons.io.IOUtils;

@Service
public class CandidateAppServiceImpl implements IServiceCandidateApp{

	@Autowired
	private CandidateAppRepository candidateAppRepository;
	@Autowired
    private  JobOfferRepository jobRepository;
	@Autowired
    private GridFsTemplate template;
    @Autowired
	private GridFsOperations operations;
	
	@Override
	public CandidateApp addApp(CandidateApp candidateApp, String idJob,String id) {
		candidateApp.setJob(jobRepository.findById(idJob).get());
		candidateApp.setCv(id);
		return candidateAppRepository.save(candidateApp);
	}

	@Override
	public List<CandidateApp> getCandidateByJob(String idJob) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CandidateApp> getAllapplications() {
		return candidateAppRepository.findAll();
	}

	@Override
	public CandidateApp getCandidateById(String idCandidateApp) {
		// TODO Auto-generated method stub
		return null;
	}
	
	  
	@Override
	    public String addFile(MultipartFile upload) throws IOException {
		 DBObject metadata = new BasicDBObject();
	        metadata.put("fileSize", upload.getSize());

	        Object fileID = template.store(upload.getInputStream(), upload.getOriginalFilename(), upload.getContentType(), metadata);

	        System.out.println("****"+upload.getOriginalFilename());
	        return fileID.toString();
	    }
	
	@Override
	public LoadFile downloadFile(String id) throws IOException {

        GridFSFile gridFSFile = template.findOne( new Query(Criteria.where("_id").is(id)) );

        LoadFile loadFile = new LoadFile();

        if (gridFSFile!= null && gridFSFile.getMetadata() != null) {
            loadFile.setFilename( gridFSFile.getFilename() );

            loadFile.setFileType( gridFSFile.getMetadata().get("_contentType").toString() );

            loadFile.setFileSize( gridFSFile.getMetadata().get("fileSize").toString() );

            loadFile.setFile( IOUtils.toByteArray(operations.getResource(gridFSFile).getInputStream()) );
        }

        return loadFile;
    }
	

}
