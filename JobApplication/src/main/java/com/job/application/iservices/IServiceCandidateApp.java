package com.job.application.iservices;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.job.application.entities.CandidateApp;
import com.job.application.entities.LoadFile;

public interface IServiceCandidateApp {

	public CandidateApp addApp(CandidateApp candidateApp,String idJob,String id);
	public List<CandidateApp> getCandidateByJob(String idJob );
	public List<CandidateApp> getAllapplications();
	public CandidateApp getCandidateById(String idCandidateApp );
	 public String addFile(MultipartFile file) throws IOException;
	public LoadFile downloadFile(String id) throws IOException;
}
