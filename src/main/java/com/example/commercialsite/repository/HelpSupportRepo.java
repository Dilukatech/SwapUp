package com.example.commercialsite.repository;

import com.example.commercialsite.entity.HelpSupport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@EnableJpaRepositories
public interface HelpSupportRepo extends JpaRepository<HelpSupport,Long> {

    boolean existsByHelpRequestIdEquals(Long helpRequestId);
    List<HelpSupport> findAllByStatusEquals(boolean status);
}
