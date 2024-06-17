package mk.ukim.finki.wp.teamprojects.service.impl;


import lombok.AllArgsConstructor;
import mk.ukim.finki.wp.teamprojects.model.Professor;
import mk.ukim.finki.wp.teamprojects.model.exceptions.ProfessorNotFoundException;
import mk.ukim.finki.wp.teamprojects.repository.ProfessorRepository;
import mk.ukim.finki.wp.teamprojects.service.ProfessorService;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProfessorServiceImpl implements ProfessorService {
    private final ProfessorRepository professorRepository;

    @Override
    public List<Professor> getAllProfessors() {
        return professorRepository.findAll(Sort.by("email"));
    }

    @Override
    public Professor getProfessorById(String id) throws ProfessorNotFoundException {
        return professorRepository.findById(id)
                .orElseThrow(() -> new ProfessorNotFoundException("Professor with id " + id + " doesn't exist"));
    }

}
