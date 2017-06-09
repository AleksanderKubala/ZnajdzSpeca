package com.example.model.Opinion;

import com.example.model.Job.Job;
import com.example.model.Job.JobResponse;
import lombok.Data;

import java.util.Date;

/**
 * Created by Paweł on 2017-04-26.
 */

@Data
public class OpinionResponse {
    private int id;
    private Date date;
    private JobResponse jobResponse;
    private String text;
    private int rate;

    public OpinionResponse(){}

    public OpinionResponse(Opinion opinion, JobResponse jobResponse){
        this.id = opinion.getId();
        this.date = opinion.getDate();
        this.jobResponse = jobResponse;
        this.text = opinion.getText();
        this.rate = opinion.getRate();
    }

    public OpinionResponse(int id, Date date, JobResponse jobResponse, String text, int rate){
        this.id = id;
        this.date = date;
        this.jobResponse = jobResponse;
        this.text = text;
        this.rate = rate;
    }
}
