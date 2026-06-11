package com.shrooms.scaffold.service.scaffold;

import com.shrooms.scaffold.mapper.scaffold.ScaffoldMapper;
import com.shrooms.scaffold.model.dto.scaffold.ScaffoldRequest;
import com.shrooms.scaffold.model.entity.scaffold.Scaffold;
import com.shrooms.scaffold.repository.scaffold.ScaffoldRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
public class ScaffoldService {

    private final ScaffoldRepository scaffoldRepository;

    public ScaffoldService(ScaffoldRepository scaffoldRepository) {
        this.scaffoldRepository = scaffoldRepository;
    }
    public Scaffold findById(UUID id) {
        return scaffoldRepository.findById(id)
                .orElseThrow();
    }

    public List<Scaffold> findAll() {
        return scaffoldRepository.findAll();
    }

    public void editScaffold(UUID id, ScaffoldRequest scaffoldRequest) {
       Scaffold scaffold = scaffoldRepository.findById(id).orElseThrow(()
               -> new RuntimeException("Scaffold not found"));

        ScaffoldMapper.updateScaffoldFromRequest(scaffold, scaffoldRequest);

        scaffoldRepository.save(scaffold);
    }

    public ScaffoldRequest getScaffoldForEdit(UUID id) {
        Scaffold scaffold = scaffoldRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Scaffold not found"));

        return ScaffoldMapper.toScaffoldRequest(scaffold);
    }



}
