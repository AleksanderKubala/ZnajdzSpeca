package com.example.model.Client;

import com.example.model.Job.Job;
import com.example.model.Job.JobRepository;
import com.example.model.Job.JobResponse;
import com.example.model.Specialization.Specialization;
import com.example.model.Specialization.SpecializationRepository;
import com.example.model.Tag.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ClientController {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private SpecializationRepository specializationRepository;

    public ClientController() {

    }


    @PostMapping(value = "/client")
    public ResponseEntity<ClientResponse> registerNewClient(@RequestBody Client newClient) {

        Boolean correct = checkIfCorrectRequest(newClient);

        if(!correct)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        if(clientRepository.findByEmail(newClient.getEmail()) != null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        clientRepository.save(newClient);
        ClientResponse response = new ClientResponse(newClient);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping(value = "/client/{id}")
    public ResponseEntity<ClientResponse> updateExistingClient(@PathVariable Integer id, @RequestBody Client updatedClient) {

        Boolean correct = checkIfCorrectRequest(updatedClient);

        if(!correct)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        Client client = clientRepository.findById(id);

        if(client == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        if(!SecurityContextHolder.getContext().getAuthentication().getName().equals(client.getEmail()))
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        //Client checkedClient = clientRepository.findByEmail(updatedClient.getEmail());

        //if(checkedClient != null)
         //   if(checkedClient.getId() != id)
          //      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        client.copy(updatedClient);
        clientRepository.save(client);
        ClientResponse response = new ClientResponse(client);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(value = "/client/{id}")
    public ResponseEntity<ClientResponse> getClientData(@PathVariable Integer id) {

        Client client = clientRepository.findById(id);

        if(client == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        ClientResponse response = new ClientResponse(client);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(value = "/client/{id}/jobs")
    public ResponseEntity<List<JobResponse>> getClientJobs(@PathVariable Integer id) {

        Client client = clientRepository.findById(id);

        if(client == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        List<Job> queryResult = jobRepository.findAllByClient(client);

        if(queryResult.isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        List<JobResponse> response = generateListJobResponse(queryResult);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    private Boolean checkIfCorrectRequest(Client client) {

        //if(client.getEmail() == null)   return false;
        if(client.getName() == null)    return false;
        if(client.getLastname() == null)    return false;
        if(client.getPassword() == null)    return false;

        return true;
    }

    private List<JobResponse> generateListJobResponse(List<Job> jobs) {

        List<JobResponse> jobResponses = new ArrayList<>();

        for(Job job : jobs) {
            List<Specialization> specs = specializationRepository.findAllByJob(job);
            List<Tag> tags = new ArrayList<>();

            for(Specialization spec : specs) {
                tags.add(spec.getTag());
            }

            JobResponse response = new JobResponse(job, tags);
            jobResponses.add(response);
        }

        return jobResponses;
    }

}
