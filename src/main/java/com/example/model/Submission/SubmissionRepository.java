package com.example.model.Submission;


import com.example.model.Company.Company;
import com.example.model.Job.Job;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SubmissionRepository extends CrudRepository<Submission, Integer>{

    List<Submission> findAllByCompany(Company company);

    List<Submission> findAllByCompanyIn(List<Company> company);

    List<Submission> findAllByJob(Job job);

    List<Submission> findAllByJobIn(List<Job> job);

    Submission findByJobAndCompany(Job job, Company company);
}
