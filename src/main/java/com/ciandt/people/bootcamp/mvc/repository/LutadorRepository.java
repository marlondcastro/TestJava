package com.ciandt.people.bootcamp.mvc.repository;

import com.ciandt.people.bootcamp.mvc.repository.model.LutadorModel;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LutadorRepository extends JpaRepository<LutadorModel, Long> {
     List<LutadorModel> findAll();
}