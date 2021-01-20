package org.mrb.cinemaM.dao;

import org.mrb.cinemaM.entities.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;
@RepositoryRestResource
@CrossOrigin("*")
public interface TicketRepository extends JpaRepository<Ticket, Long> {
	/* public Page<Ticket> findByNomClientContains(String mc,Pageable pageable); */


}
