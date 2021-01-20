package org.mrb.cinemaM.service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

import org.mrb.cinemaM.dao.CategorieRepository;
import org.mrb.cinemaM.dao.CinemaRepository;
import org.mrb.cinemaM.dao.FilmRepository;
import org.mrb.cinemaM.dao.PlaceRepository;
import org.mrb.cinemaM.dao.ProjectionRepository;
import org.mrb.cinemaM.dao.SalleRepository;
import org.mrb.cinemaM.dao.SeanceRepository;
import org.mrb.cinemaM.dao.TicketRepository;
import org.mrb.cinemaM.dao.VilleRepository;
import org.mrb.cinemaM.entities.Categorie;
import org.mrb.cinemaM.entities.Cinema;
import org.mrb.cinemaM.entities.Film;
import org.mrb.cinemaM.entities.Place;
import org.mrb.cinemaM.entities.Projection;
import org.mrb.cinemaM.entities.Salle;
import org.mrb.cinemaM.entities.Seance;
import org.mrb.cinemaM.entities.Ticket;
import org.mrb.cinemaM.entities.Ville;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CinemaInitServiceImpl implements ICinemaInitService {

	@Autowired
	private VilleRepository villeRepository;
	@Autowired
	private CinemaRepository cinemaRepository;
	@Autowired
	private SalleRepository salleRepository;
	@Autowired
	private PlaceRepository placeRepository;
	@Autowired
	private SeanceRepository seanceRepository;
	@Autowired
	private FilmRepository filmRepository;
	@Autowired
	private ProjectionRepository projectionRepository;
	@Autowired
	private TicketRepository ticketRepository;
	@Autowired
	private CategorieRepository categorieRepository;
	@Override
	public void initVilles() {
		Stream.of("Casablanca","Merrakech","Rabat","Agadir","Tanger").forEach(nameVille->{
			Ville ville=new Ville();
			ville.setName(nameVille);
			villeRepository.save(ville);			
			
		});
	}

	@Override
	public void initCinemas() {
		villeRepository.findAll().forEach(v->{
			Stream.of("MegaRama","IMax","Founoun","CHAHRAZAD","DAWLIZ")
			.forEach(nameCinema->{
				Cinema cinema=new Cinema();
				cinema.setName(nameCinema);
				cinema.setNombreSalles(3+(int)(Math.random()*7));
				cinema.setVille(v);
				cinemaRepository.save(cinema);
			});
		});	
	}

	@Override
	public void initSalles() {
		cinemaRepository.findAll().forEach(cinema->{
			for(int i=0;i<cinema.getNombreSalles();i++) {
				Salle salle = new Salle();
				salle.setName("Salle"+(i+1));
				salle.setCinema(cinema);
				salle.setNombrePlace(15+(int)(Math.random()*20));
				salleRepository.save(salle);
			}
		});	
	}

	@Override
	public void initPlaces() {
		salleRepository.findAll().forEach(salle->{
			for(int i=0;i<salle.getNombrePlace();i++) {
				Place place = new Place();
				place.setNumero(i+1);
				place.setSalle(salle);
				placeRepository.save(place);
			}
		});
	}

	@Override
	public void initSeances() {
		DateFormat dateF =new SimpleDateFormat("HH:mm");
		Stream.of("15:00","17:00","19:00","21:00","23:00")
		.forEach(s->{
			Seance seance = new Seance();
			try {
				seance.setHeureDebut(dateF.parse(s));
				seanceRepository.save(seance);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
	}

	@Override
	public void initCategories() {
		Stream.of("Histoire","Aventures","Action","Fantastique","Policier","Science-Fiction","Horreur","Thriller")
		.forEach(cat->{
			Categorie categorie = new Categorie();
			categorie.setName(cat);
			categorieRepository.save(categorie);
		});
	}

	@Override
	public void initFilms() {
		double[] durees=new double[] {1,1.5,2,2.5,3};
		List<Categorie> categories= categorieRepository.findAll();
		Stream.of("Slumdog Millionaire","Jocker","The Lord of the Rings","Gladiator","Braveheart","12 Angry Men","The Pursuit Of Happiness","The Davinci Code")
		.forEach(titreFilm->{
			Film film = new Film();
			film.setTitre(titreFilm);
			film.setDuree(durees[new Random().nextInt(durees.length)]);
			film.setPhoto(titreFilm.replaceAll(" ","")+".jpg");
			film.setCategorie(categories.get(new Random().nextInt(categories.size())));
			filmRepository.save(film);
		});
		
	}

	@Override
	public void initProjections() {
		double[] prix=new double[] {30,50,70,90,120};
		List<Film> films=filmRepository.findAll();
		villeRepository.findAll().forEach(ville->{
			ville.getCinemas().forEach(cinema->{
				cinema.getSalles().forEach(salle->{
					int index= new Random().nextInt(films.size());
					Film film=films.get(index);
						seanceRepository.findAll().forEach(seance->{
							Projection projection = new Projection();
							projection.setDateProjection(new Date());
							projection.setFilm(film);
							projection.setPrix(prix[new Random().nextInt(prix.length)]);
							projection.setSalle(salle);
							projection.setSeance(seance);
							projectionRepository.save(projection);
						});
						
					
				});
			});
		});
	}

	@Override
	public void initTickets() {
		projectionRepository.findAll().forEach(p->{
			p.getSalle().getPlaces().forEach(place->{
				Ticket ticket = new Ticket();
				ticket.setPlace(place);
				ticket.setPrix(p.getPrix());
				ticket.setProjection(p);
				ticket.setReservee(false);
				ticketRepository.save(ticket);
				});
			});
	}

}
