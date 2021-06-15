package com.aprendizagem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aprendizagem.models.Tool;

@Repository
public interface ToolRepository extends JpaRepository<Tool, Long>{

}
