package com.example.model.Company;

import com.example.model.Job.Job;
import com.example.model.Job.JobRepository;
import com.example.model.Job.JobResponse;
import com.example.model.Opinion.Opinion;
import com.example.model.Opinion.OpinionRepository;
import com.example.model.Opinion.OpinionResponse;
import com.example.model.Specialization.Specialization;
import com.example.model.Specialization.SpecializationRepository;
import com.example.model.Submission.Submission;
import com.example.model.Submission.SubmissionRepository;
import com.example.model.Tag.Tag;
import com.example.model.Tag.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Paweł on 2017-04-04.
 */
@RestController
public class CompanyController {

    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private JobRepository jobRepository;
    @Autowired
    private OpinionRepository opinionRepository;
    @Autowired
    private SubmissionRepository submissionRepository;
    @Autowired
    private SpecializationRepository specializationRepository;
	
    @PostMapping("/company")
    public ResponseEntity<CompanyResponse> createCompany(@RequestBody Company company){

	       if(companyRepository.exists(company.getId()) || !checkIfCorrectRequest(company)) {
               return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
           }

           company.setRating(0.0f);
	       company.setNumberJobs(0);
	       company.setNumberOpinions(0);
           companyRepository.save(company);
        return new ResponseEntity<>(new CompanyResponse(company), HttpStatus.OK);
    }

    @GetMapping("/company/{id}")
    public ResponseEntity<CompanyResponse> getCompanyById(@PathVariable Integer id){
        Company company = companyRepository.findOne(id);
        if(company!=null)
            return new ResponseEntity<>(new CompanyResponse(company), HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/company/{id}")
    public ResponseEntity<CompanyResponse> editCompanyById(@PathVariable Integer id, @RequestBody Company newCompany){

        if(!checkIfCorrectRequest(newCompany))
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        Company company = companyRepository.findOne(id);

        if(company == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        if(!SecurityContextHolder.getContext().getAuthentication().getName().equals(company.getEmail()))
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        Company companyCheck = companyRepository.findByEmail(newCompany.getEmail());

        if(companyCheck != null)
            if(companyCheck.getId() != id)
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        company.copy(newCompany);
        companyRepository.save(company);
        CompanyResponse response = new CompanyResponse(company);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/company/{id}/jobs")
    public ResponseEntity<List<JobResponse>> getCompanyJobsById(@PathVariable Integer id){
        Company company = companyRepository.findOne(id);
        if(company == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        List<Job> jobs = jobRepository.findAllByCompany(company);

        if(jobs.size() == 0)
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        return new ResponseEntity<>(convertJobToJobResponse(jobs),HttpStatus.OK);
    }

    @GetMapping("company/{id}/opinions")
    public ResponseEntity<List<OpinionResponse>> getOpinionByCompanyId(@PathVariable Integer id){
        Company company = companyRepository.findOne(id);
        if(company == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        List<Job> jobs = jobRepository.findAllByCompany(company);

        if(jobs.size() == 0)
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        List<JobResponse> jobResponses = convertJobToJobResponse(jobs);

        return new ResponseEntity<>(findOpinionResponseByJobs(jobs), HttpStatus.OK);
    }

    @GetMapping(value = "/company/offers")
    public ResponseEntity<List<JobResponse>> getOfferedJobs() {

        Company company = companyRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());

        if(company == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        List<Submission> offers = submissionRepository.findAllByCompanyAndAcceptedIsNull(company);
        List<JobResponse> response = new ArrayList<>();

        for(Submission sub : offers) {

            List<Specialization> specs = specializationRepository.findAllByJob(sub.getJob());
            List<Tag> tags = getJobTags(sub.getJob());

            response.add(new JobResponse(sub.getJob(), tags));
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(value = "/company/accepted")
    public ResponseEntity<List<JobResponse>> getAcceptedJobs() {

        Company company = companyRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());

        if(company == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        List<Submission> offers = submissionRepository.findAllByCompanyAndAcceptedIsTrue(company);
        List<JobResponse> response = new ArrayList<>();

        for(Submission sub : offers) {

            List<Specialization> specs = specializationRepository.findAllByJob(sub.getJob());
            List<Tag> tags = getJobTags(sub.getJob());

            response.add(new JobResponse(sub.getJob(), tags));
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    private List<JobResponse> convertJobToJobResponse(List<Job> jobs) {
        List<JobResponse> jobResponses = new ArrayList<>();

        for(Job job : jobs)
            jobResponses.add(new JobResponse(job, getJobTags(job)));

        return jobResponses;
    }

    private List<Tag> getJobTags(Job job) {
        List<Specialization> specializations = specializationRepository.findAllByJob(job);
        List<Tag> tags = new ArrayList<>();

        for(Specialization spec : specializations)
            tags.add(spec.getTag());

        return tags;
    }


    private Boolean checkIfCorrectRequest(Company company) {

        //if(company.getEmail() == null)   return false;
        if(company.getName() == null)    return false;
        if(company.getPassword() == null)    return false;
        if(company.getLocalization() == null)    return false;

        return true;
    }

    private List<OpinionResponse> findOpinionResponseByJobs(List<Job> jobs){
        List <Opinion> opinions = new ArrayList<>();
        List <JobResponse> jobResponses = new ArrayList<>();

        for(Job job : jobs) {
            opinions.add(opinionRepository.findByJob(job));
            jobResponses.add(new JobResponse(job ,getJobTags(job)));
        }

        List<OpinionResponse> opinionResponses = new ArrayList<>();

        for(int i = 0; i < opinions.size(); i++) {
            opinionResponses.add(new OpinionResponse(opinions.get(i), jobResponses.get(i)));
        }

        return opinionResponses;
    }
}
