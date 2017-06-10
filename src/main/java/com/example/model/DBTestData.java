package com.example.model;


import com.example.model.Client.Client;
import com.example.model.Client.ClientRepository;
import com.example.model.Company.Company;
import com.example.model.Company.CompanyRepository;
import com.example.model.Job.Job;
import com.example.model.Job.JobRepository;
import com.example.model.Opinion.Opinion;
import com.example.model.Opinion.OpinionRepository;
import com.example.model.Specialization.Specialization;
import com.example.model.Specialization.SpecializationRepository;
import com.example.model.Submission.Submission;
import com.example.model.Submission.SubmissionRepository;
import com.example.model.Tag.Tag;
import com.example.model.Tag.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

@Component
public class DBTestData implements CommandLineRunner {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private SpecializationRepository specializationRepository;

    @Autowired
    private SubmissionRepository submissionRepository;

    @Autowired
    private OpinionRepository opinionRepository;

    @Override
    public void run(String... args) throws Exception {

        generateClients();
        generateCompanies();
        generateActualTags();
        generateJobs();
        generateSpecsForJobs();
        generateSpecsForCompanies();
        generateSubmissions();
        generateOpinions();
    }

    private void generateActualTags() {

        List<Tag> tags = new ArrayList<>();

        tags.add(new Tag("Architekt"));
        tags.add(new Tag("Dekarz"));
        tags.add(new Tag("Elektryka"));
        tags.add(new Tag("Garaż"));
        tags.add(new Tag("Glazura"));
        tags.add(new Tag("Hydraulika"));
        tags.add(new Tag("Klimatyzacja"));
        tags.add(new Tag("Kuchnia"));
        tags.add(new Tag("Łazienka"));
        tags.add(new Tag("Malarz"));
        tags.add(new Tag("Murarstwo"));
        tags.add(new Tag("Ogrodnictwo"));
        tags.add(new Tag("Ogrodzenie"));
        tags.add(new Tag("Ogrzewanie"));
        tags.add(new Tag("Okna"));
        tags.add(new Tag("Oświetlenie"));
        tags.add(new Tag("Panele"));
        tags.add(new Tag("Prace ziemne"));
        tags.add(new Tag("Przebudowa"));
        tags.add(new Tag("Schody"));
        tags.add(new Tag("Sprzątanie"));
        tags.add(new Tag("Stolarz"));
        tags.add(new Tag("Szafy"));
        tags.add(new Tag("Zabudowa"));
        tags.add(new Tag("Złota rączka"));

        tagRepository.save(tags);

    }

    private void generateClients() {

        Client client = new Client();
        client.setName("Jan");
        client.setLastname("Kowalski");
        client.setEmail("jankowalski@example.com");
        client.setPassword("12345678");
        client.setPhoneNumber("123456789");
        clientRepository.save(client);

        Client client1 = new Client();
        client1.setName("Adam");
        client1.setLastname("Malinowski");
        client1.setEmail("adammalinowski@example.com");
        client1.setPassword("12345678");
        clientRepository.save(client1);

        Client client2 = new Client();
        client2.setName("Piotr");
        client2.setLastname("Leszczyński");
        client2.setEmail("piotrleszczynski@example.com");
        client2.setPassword("12345678");
        clientRepository.save(client2);

        Client client3 = new Client();
        client3.setName("Karolina");
        client3.setLastname("Zawadzka");
        client3.setEmail("karolinazawadzka@example.com");
        client3.setPassword("12345678");
        clientRepository.save(client3);

        Client client4 = new Client();
        client4.setName("Marzena");
        client4.setLastname("Wtorek");
        client4.setEmail("marzenawtorek@example.com");
        client4.setPassword("12345678");
        clientRepository.save(client4);

    }

    private void generateCompanies() {

        Random random = new Random();

        Company company = new Company();
        company.setName("Kowalscy");
        company.setAreaRange(25);
        company.setLocalization("Warszawa");
        company.setEmail("kowalscy@example.com");
        company.setPassword("12345678");
        company.setNumberOpinions(0);
        company.setNumberJobs(0);
        company.setRating(0.0f);
        companyRepository.save(company);

        Company company2 = new Company();
        company2.setName("Malinowscy");
        company2.setAreaRange(75);
        company2.setLocalization("Poznań");
        company2.setEmail("malinowscy@example.com");
        company2.setPassword("12345678");
        company2.setNumberOpinions(1);
        company2.setNumberJobs(1);
        company2.setRating(5.0f);
        companyRepository.save(company2);

        Company company3 = new Company();
        company3.setName("Leszczyńscy");
        company3.setAreaRange(62);
        company3.setLocalization("Katowice");
        company3.setEmail("leszczynscy@example.com");
        company3.setPassword("12345678");
        company3.setNumberJobs(1);
        company3.setNumberOpinions(1);
        company3.setRating(3.0f);
        companyRepository.save(company3);

        Company company4 = new Company();
        company4.setName("Zawadzcy");
        company4.setAreaRange(40);
        company4.setLocalization("Gdynia");
        company4.setEmail("zawadzcy@example.com");
        company4.setPassword("12345678");
        company4.setNumberOpinions(0);
        company4.setNumberJobs(1);
        company4.setRating(0.0f);
        companyRepository.save(company4);

        Company company5 = new Company();
        company5.setName("Januszex");
        company5.setAreaRange(120);
        company5.setLocalization("Wrocław");
        company5.setEmail("januszex@example.com");
        company5.setPassword("12345678");
        company5.setNumberOpinions(0);
        company5.setNumberJobs(0);
        company5.setRating(0.0f);
        companyRepository.save(company5);

    }

    private void generateJobs() {

        Calendar calendar = Calendar.getInstance();
        calendar.set(2017, Calendar.MAY, 1);
        Date current = new Date(calendar.getTime().getTime());
        calendar.set(2017, Calendar.JUNE, 15);

        Job job = new Job();
        job.setClient(clientRepository.findById(1));
        job.setTitle("Pierwsze ogłoszenie kowalskiego");
        job.setVisible(true);
        job.setBeginDate(new Date(calendar.getTime().getTime()));
        calendar.add(5, 21);
        job.setEndDate(new Date(calendar.getTime().getTime()));
        job.setAddedAt(current);
        job.setLocalization("Warszawa");
        jobRepository.save(job);

        calendar.set(2017, Calendar.MAY, 31);

        Job job1 = new Job();
        job1.setClient(clientRepository.findById(1));
        job1.setTitle("Drugie ogłoszenie kowalskiego");
        job1.setVisible(false);
        job1.setBeginDate(new Date(calendar.getTime().getTime()));
        calendar.add(5, 6);
        job1.setEndDate(new Date(calendar.getTime().getTime()));
        job1.setAddedAt(current);
        job1.setLocalization("Płock");
        job1.setCompany(companyRepository.findOne(2));
        jobRepository.save(job1);

        calendar.set(2017, Calendar.JUNE, 1);

        Job job2 = new Job();
        job2.setClient(clientRepository.findById(2));
        job2.setTitle("Pierwsze ogłoszenie malinowskiego");
        job2.setVisible(false);
        job2.setBeginDate(new Date(calendar.getTime().getTime()));
        calendar.add(5, 10);
        job2.setEndDate(new Date(calendar.getTime().getTime()));
        job2.setAddedAt(current);
        job2.setLocalization("Gdynia");
        job2.setCompany(companyRepository.findOne(4));
        jobRepository.save(job2);

        calendar.set(2017, Calendar.JUNE, 31);

        Job job3= new Job();
        job3.setClient(clientRepository.findById(2));
        job3.setTitle("Drugie ogłoszenie malinowskiego");
        job3.setVisible(true);
        job3.setBeginDate(new Date(calendar.getTime().getTime()));
        calendar.add(5, 5);
        job3.setEndDate(new Date(calendar.getTime().getTime()));
        job3.setAddedAt(current);
        job3.setLocalization("Gdańsk");
        jobRepository.save(job3);

        calendar.add(5, -14);

        Job job4 = new Job();
        job4.setClient(clientRepository.findById(5));
        job4.setTitle("Ogłoszenie wtorków");
        job4.setVisible(true);
        job4.setBeginDate(new Date(calendar.getTime().getTime()));
        calendar.add(5, 31);
        job4.setEndDate(new Date(calendar.getTime().getTime()));
        job4.setAddedAt(current);
        job4.setLocalization("Toruń");
        jobRepository.save(job4);

        calendar.set(2017,Calendar.JUNE,14);

        Job job5 = new Job();
        job5.setClient(clientRepository.findById(1));
        job5.setTitle("Trzecie ogłoszenie kowalskiego");
        job5.setVisible(false);
        job5.setBeginDate(new Date(calendar.getTime().getTime()));
        calendar.add(5, 10);
        job5.setEndDate(new Date(calendar.getTime().getTime()));
        job5.setAddedAt(current);
        job5.setLocalization("Warszawa");
        job5.setCompany(companyRepository.findOne(3));
        jobRepository.save(job5);

        calendar.add(5, -9);

        Job job6 = new Job();
        job6.setClient(clientRepository.findById(1));
        job6.setTitle("Czwarte ogłoszenie kowalskiego");
        job6.setVisible(true);
        job6.setBeginDate(new Date(calendar.getTime().getTime()));
        calendar.add(5, 3);
        job6.setEndDate(new Date(calendar.getTime().getTime()));
        job6.setAddedAt(current);
        job6.setLocalization("Warszawa");
        jobRepository.save(job6);

    }

    private void generateSpecsForJobs() {

        Specialization spec1 = new Specialization();
        spec1.setJob(jobRepository.findOne(1));
        spec1.setTag(tagRepository.findById(3));
        specializationRepository.save(spec1);

        Specialization spec2 = new Specialization();
        spec2.setJob(jobRepository.findOne(2));
        spec2.setTag(tagRepository.findById(5));
        specializationRepository.save(spec2);

        Specialization spec3 = new Specialization();
        spec3.setJob(jobRepository.findOne(2));
        spec3.setTag(tagRepository.findById(9));
        specializationRepository.save(spec3);

        Specialization spec4 = new Specialization();
        spec4.setJob(jobRepository.findOne(3));
        spec4.setTag(tagRepository.findById(3));
        specializationRepository.save(spec4);

        Specialization spec5 = new Specialization();
        spec5.setJob(jobRepository.findOne(3));
        spec5.setTag(tagRepository.findById(4));
        specializationRepository.save(spec5);

        Specialization spec6 = new Specialization();
        spec6.setJob(jobRepository.findOne(4));
        spec6.setTag(tagRepository.findById(19));
        specializationRepository.save(spec6);

        Specialization spec7 = new Specialization();
        spec7.setJob(jobRepository.findOne(4));
        spec7.setTag(tagRepository.findById(20));
        specializationRepository.save(spec7);

        Specialization spec8 = new Specialization();
        spec8.setJob(jobRepository.findOne(5));
        spec8.setTag(tagRepository.findById(12));
        specializationRepository.save(spec8);

        Specialization spec9 = new Specialization();
        spec9.setJob(jobRepository.findOne(5));
        spec9.setTag(tagRepository.findById(18));
        specializationRepository.save(spec9);

        Specialization spec10 = new Specialization();
        spec10.setJob(jobRepository.findOne(6));
        spec10.setTag(tagRepository.findById(3));
        specializationRepository.save(spec10);

        Specialization spec11 = new Specialization();
        spec11.setJob(jobRepository.findOne(6));
        spec11.setTag(tagRepository.findById(11));
        specializationRepository.save(spec11);

        Specialization spec12 = new Specialization();
        spec12.setJob(jobRepository.findOne(6));
        spec12.setTag(tagRepository.findById(16));
        specializationRepository.save(spec12);

        Specialization spec13 = new Specialization();
        spec13.setJob(jobRepository.findOne(7));
        spec13.setTag(tagRepository.findById(21));
        specializationRepository.save(spec13);

    }

    private void generateSpecsForCompanies() {

        Specialization spec = new Specialization();
        spec.setCompany(companyRepository.findOne(1));
        spec.setTag(tagRepository.findById(22));
        specializationRepository.save(spec);

        Specialization spec1 = new Specialization();
        spec1.setCompany(companyRepository.findOne(1));
        spec1.setTag(tagRepository.findById(23));
        specializationRepository.save(spec1);

        Specialization spec2 = new Specialization();
        spec2.setCompany(companyRepository.findOne(1));
        spec2.setTag(tagRepository.findById(25));
        specializationRepository.save(spec2);

        Specialization spec3 = new Specialization();
        spec3.setCompany(companyRepository.findOne(2));
        spec3.setTag(tagRepository.findById(3));
        specializationRepository.save(spec3);

        Specialization spec4 = new Specialization();
        spec4.setCompany(companyRepository.findOne(2));
        spec4.setTag(tagRepository.findById(5));
        specializationRepository.save(spec4);

        Specialization spec5 = new Specialization();
        spec5.setCompany(companyRepository.findOne(2));
        spec5.setTag(tagRepository.findById(6));
        specializationRepository.save(spec5);

        Specialization spec6 = new Specialization();
        spec6.setCompany(companyRepository.findOne(2));
        spec6.setTag(tagRepository.findById(8));
        specializationRepository.save(spec6);

        Specialization spec7 = new Specialization();
        spec7.setCompany(companyRepository.findOne(2));
        spec7.setTag(tagRepository.findById(9));
        specializationRepository.save(spec7);

        Specialization spec8 = new Specialization();
        spec8.setCompany(companyRepository.findOne(3));
        spec8.setTag(tagRepository.findById(3));
        specializationRepository.save(spec8);

        Specialization spec9 = new Specialization();
        spec9.setCompany(companyRepository.findOne(3));
        spec9.setTag(tagRepository.findById(7));
        specializationRepository.save(spec9);

        Specialization spec10 = new Specialization();
        spec10.setCompany(companyRepository.findOne(3));
        spec10.setTag(tagRepository.findById(11));
        specializationRepository.save(spec10);

        Specialization spec11 = new Specialization();
        spec11.setCompany(companyRepository.findOne(3));
        spec11.setTag(tagRepository.findById(15));
        specializationRepository.save(spec11);

        Specialization spec12 = new Specialization();
        spec12.setCompany(companyRepository.findOne(3));
        spec12.setTag(tagRepository.findById(16));
        specializationRepository.save(spec12);

        Specialization spec13 = new Specialization();
        spec13.setCompany(companyRepository.findOne(3));
        spec13.setTag(tagRepository.findById(19));
        specializationRepository.save(spec13);

        Specialization spec14 = new Specialization();
        spec14.setCompany(companyRepository.findOne(4));
        spec14.setTag(tagRepository.findById(3));
        specializationRepository.save(spec14);

        Specialization spec15 = new Specialization();
        spec15.setCompany(companyRepository.findOne(4));
        spec15.setTag(tagRepository.findById(4));
        specializationRepository.save(spec15);

        Specialization spec16 = new Specialization();
        spec16.setCompany(companyRepository.findOne(4));
        spec16.setTag(tagRepository.findById(16));
        specializationRepository.save(spec16);

        Specialization spec17 = new Specialization();
        spec17.setCompany(companyRepository.findOne(4));
        spec17.setTag(tagRepository.findById(19));
        specializationRepository.save(spec17);

        Specialization spec18 = new Specialization();
        spec18.setCompany(companyRepository.findOne(5));
        spec18.setTag(tagRepository.findById(17));
        specializationRepository.save(spec18);

        Specialization spec19 = new Specialization();
        spec19.setCompany(companyRepository.findOne(5));
        spec19.setTag(tagRepository.findById(22));
        specializationRepository.save(spec19);

        Specialization spec20 = new Specialization();
        spec20.setCompany(companyRepository.findOne(5));
        spec20.setTag(tagRepository.findById(23));
        specializationRepository.save(spec20);
    }

    private void generateSubmissions() {

        Submission sub = new Submission();
        sub.setAccepted(true);
        sub.setJob(jobRepository.findOne(1));
        sub.setCompany(companyRepository.findOne(2));
        submissionRepository.save(sub);

        Submission sub1 = new Submission();
        sub1.setAccepted(true);
        sub1.setJob(jobRepository.findOne(1));
        sub1.setCompany(companyRepository.findOne(3));
        submissionRepository.save(sub1);

        Submission sub2 = new Submission();
        sub2.setJob(jobRepository.findOne(1));
        sub2.setCompany(companyRepository.findOne(4));
        submissionRepository.save(sub2);

    }

    private void generateOpinions() {

        Calendar calendar = Calendar.getInstance();
        calendar.set(2017, Calendar.JUNE, 1);

        Opinion op = new Opinion();
        op.setDate(new Date(calendar.getTime().getTime()));
        op.setRate(5);
        op.setJob(jobRepository.findOne(2));
        op.setText("Super robota. Polecam i pozdrawiam: Jan Kowalski");
        opinionRepository.save(op);

        calendar.set(2017, Calendar.JUNE, 12);

        Opinion op1 = new Opinion();
        op1.setDate(new Date(calendar.getTime().getTime()));
        op1.setRate(3);
        op1.setJob(jobRepository.findOne(3));
        op1.setText("Firma spóźniła się z wykonaniem.");
        opinionRepository.save(op1);

    }

}
