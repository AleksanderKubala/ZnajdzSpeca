package com.example.model.Opinion;

import com.example.model.Job.Job;
import lombok.Data;

import java.util.Date;

/**
 * Created by Paweł on 2017-05-01.
 */
@Data
public class OpinionRequest {

    private String text;
    private int rate;

    public OpinionRequest(Opinion opinion){
        this.text = opinion.getText();
        this.rate = opinion.getRate();
    }

    public OpinionRequest(){}
}
