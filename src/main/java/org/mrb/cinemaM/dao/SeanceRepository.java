package org.mrb.cinemaM.dao;

import java.util.Date;

import org.mrb.cinemaM.entities.Seance;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;
@RepositoryRestResource
@CrossOrigin("*")
public interface SeanceRepository extends JpaRepository<Seance, Long> {
	public Page<Seance> findByHeureDebut(Date heureDebut,Pageable pageable);
}
