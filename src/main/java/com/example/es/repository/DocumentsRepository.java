package com.example.es.repository;

import com.example.es.domain.Documents;
import org.springframework.data.jpa.repository.JpaRepository;



public interface DocumentsRepository extends JpaRepository<Documents, Long> {


}
