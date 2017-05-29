package com.example.model.Specialization;


import com.example.model.Company.Company;
import com.example.model.Job.Job;
import com.example.model.Tag.Tag;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SpecializationRepository extends CrudRepository<Specialization, Integer>{

    List<Specialization> findAllByJob(Job job);

    List<Specialization> findAllByJobIn(List<Job> job);

    List<Specialization> findAllByCompany(Company company);

    List<Specialization> findAllByCompanyIn(List<Company> company);

    List<Specialization> findAllByTag(Tag tag);

    List<Specialization> findAllByTagIn(List<Tag> tag);
}
