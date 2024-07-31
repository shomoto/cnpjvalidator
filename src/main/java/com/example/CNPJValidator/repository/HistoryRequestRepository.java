package com.example.CNPJValidator.repository;


import com.example.CNPJValidator.model.HistoryRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HistoryRequestRepository extends JpaRepository<HistoryRequest, Long> {


}
