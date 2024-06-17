package mk.ukim.finki.wp.teamprojects.service;



import mk.ukim.finki.wp.teamprojects.model.Professor;
import mk.ukim.finki.wp.teamprojects.model.exceptions.ProfessorNotFoundException;

import java.util.List;

public interface ProfessorService {


    List<Professor> getAllProfessors();

    Professor getProfessorById(String id) throws ProfessorNotFoundException;
}
