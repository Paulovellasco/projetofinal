package br.iesb.grupo1.projetofinal.util;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public class Job {

    private String Title;
    private String profile;
    private String salary;
    private String nameCity;
    private String uf;
    private String amount;
    private String recruiterName;
    private String recruiterBranch;
    private String recruiterNacionality;
    private String recruiterSize;
    private String recruiterDescription;
    private String requireExperience;
    private String requireEducation;
    private String requiredKnowledge;
    private String requiredLanguage;


    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getNameCity() {
        return nameCity;
    }

    public void setNameCity(String nameCity) {
        this.nameCity = nameCity;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getRecruiterName() {
        return recruiterName;
    }

    public void setRecruiterName(String recruiterName) {
        this.recruiterName = recruiterName;
    }

    public String getRecruiterBranch() {
        return recruiterBranch;
    }

    public void setRecruiterBranch(String recruiterBranch) {
        this.recruiterBranch = recruiterBranch;
    }

    public String getRecruiterNacionality() {
        return recruiterNacionality;
    }

    public void setRecruiterNacionality(String recruiterNacionality) {
        this.recruiterNacionality = recruiterNacionality;
    }

    public String getRecruiterSize() {
        return recruiterSize;
    }

    public void setRecruiterSize(String recruiterSize) {
        this.recruiterSize = recruiterSize;
    }

    public String getRecruiterDescription() {
        return recruiterDescription;
    }

    public void setRecruiterDescription(String recruiterDescription) {
        this.recruiterDescription = recruiterDescription;
    }

    public String getRequireExperience() {
        return requireExperience;
    }

    public void setRequireExperience(String requireExperience) {
        this.requireExperience = requireExperience;
    }

    public String getRequireEducation() {
        return requireEducation;
    }

    public void setRequireEducation(String requireEducation) {
        this.requireEducation = requireEducation;
    }

    public String getRequiredKnowledge() {
        return requiredKnowledge;
    }

    public void setRequiredKnowledge(String requiredKnowledge) {
        this.requiredKnowledge = requiredKnowledge;
    }

    public String getRequiredLanguage() {
        return requiredLanguage;
    }

    public void setRequiredLanguage(String requiredLanguage) {
        this.requiredLanguage = requiredLanguage;
    }


    public interface JobService {
        @GET("/vagas")
        Call<List<Job>> listJobs();
    }
}
