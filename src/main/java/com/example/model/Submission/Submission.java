package com.example.model.Submission;

import com.example.model.Company.Company;
import com.example.model.Job.Job;

import javax.persistence.*;

@Entity
public class Submission {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "public")
    @SequenceGenerator(name = "public", sequenceName = "submission_seq", initialValue = 1, allocationSize = 1)

    private int id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "companyId",
                referencedColumnName = "ID")
    private Company company;

    @ManyToOne(optional = false)
    @JoinColumn(name = "jobId",
                referencedColumnName = "ID")
    private Job job;

    private Boolean accepted;

    public Submission() {
    }

    public Submission(Company company, Job job) {
        this.company = company;
        this.job = job;
    }

    public Submission(Company company, Job job, Boolean accepted) {
        this(company, job);
        this.accepted = accepted;
    }



    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public Boolean getAccepted() {
        return accepted;
    }

    public void setAccepted(Boolean accepted) {
        this.accepted = accepted;
    }

    @Override
    public String toString() {
        return "Submission{" +
                "company=" + company +
                ", job=" + job +
                '}';
    }
}
