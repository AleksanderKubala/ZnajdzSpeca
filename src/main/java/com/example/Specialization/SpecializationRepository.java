package com.example.Specialization;


import com.example.Company.Company;
import com.example.Job.Job;
import com.example.Tag.Tag;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SpecializationRepository extends CrudRepository<Specialization, Integer>{

    List<Specialization> findAllByJob(Job job);

    List<Specialization> findAllByJobIn(List<Job> job);

    List<Specialization> findAllByCompany(Company company);

    List<Specialization> findAllByCompanyIn(List<Company> company);

    List<Specialization> findAllByTag(Tag tag);

    List<Specialization> findAllByTagIn(List<Tag> tag);

    /*
    List<Tag> findAllTagByJob(Job job);

    List<Tag> findAllTagByCompany(Company company);

    List<Job> findAllJobByTagIn(List<Tag> tag);

    List<Company> findAllCompanyByTagIn(List<Tag> tag);
    */
}