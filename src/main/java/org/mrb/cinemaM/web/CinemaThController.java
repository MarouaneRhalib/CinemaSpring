package org.mrb.cinemaM.web;

import javax.validation.Valid;

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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CinemaThController {
		
		@Autowired
		private VilleRepository villeRepository;
		@Autowired
		private CinemaRepository cinemaRepository;
		@Autowired
		private SalleRepository salleRepository;
		@Autowired
		private PlaceRepository placeRepository;
		@Autowired
		private FilmRepository filmRepository;
		@Autowired
		private SeanceRepository seanceRepository;
		@Autowired
		private TicketRepository ticketRepository;
		@Autowired
		private CategorieRepository categorieRepository;
		@Autowired
		private ProjectionRepository projectionRepository; 
		
		/*
		 * ------------------------------------------------------------------ Ville ------------------------------------------------
		 */
		
		@GetMapping(path = "/ville")
		public String villes(Model model,
				@RequestParam(name="page",defaultValue = "0")int page,
				@RequestParam(name="size",defaultValue = "4")int size,
				@RequestParam(name="keyword",defaultValue = "")String mc) {
			Page<Ville> pageVilles= villeRepository.findByNameContains(mc, PageRequest.of(page, size));
			model.addAttribute("villes",pageVilles.getContent());
			model.addAttribute("pages",new int[pageVilles.getTotalPages()]);
			model.addAttribute("currentPage", page);
			model.addAttribute("size", size);
			model.addAttribute("keyword", mc);
			return "ville";
		}
		
		
		@GetMapping(path = "/deleteVille")
		public String delete(Long id,String keyword,int page,int size) {
			villeRepository.deleteById(id);
			return "redirect:/ville?page="+page+"&size="+size+"&keyword="+keyword;
		}
		
		@GetMapping(path = "/editVille")
		public String editVille(Model model, Long id) {
			Ville v=villeRepository.findById(id).get();
			model.addAttribute("ville", v);
			model.addAttribute("mode", "edit");
			return "formVille";
		}
	
		@GetMapping(path = "/formVille")
		public String formVille(Model model) {
			model.addAttribute("ville", new Ville());
			model.addAttribute("mode", "new");
			return "formVille";
		}
		
		@PostMapping(path ="/saveVille" )
		public String saveVille(@Valid Ville ville, BindingResult bindingResult) {
			if(bindingResult.hasErrors()) return "formVille";
			villeRepository.save(ville);
			return "formVille";
		}
		
		
		/*
		 * ------------------------------------------------------------------ CINEMA ------------------------------------------------
		 */
		
		@GetMapping(path = "/cinema")
		public String cinemas(Model model,
				@RequestParam(name="page",defaultValue = "0")int page,
				@RequestParam(name="size",defaultValue = "4")int size,
				@RequestParam(name="keyword",defaultValue = "")String mc) {
			Page<Cinema> pageCinemas= cinemaRepository.findByNameContains(mc, PageRequest.of(page, size));
			model.addAttribute("cinemas",pageCinemas.getContent());
			model.addAttribute("pages",new int[pageCinemas.getTotalPages()]);
			model.addAttribute("currentPage", page);
			model.addAttribute("size", size);
			model.addAttribute("keyword", mc);
			return "cinema";
		}
		
		@GetMapping(path = "/deleteCinema")
		public String deletecinema(Long id,String keyword,int page,int size) {
			cinemaRepository.deleteById(id);
			return "redirect:/cinema?page="+page+"&size="+size+"&keyword="+keyword;
	}
	
	
	
		@GetMapping(path = "/formCinema")
		public String formCinema(Model model) {
			model.addAttribute("cinema", new Cinema());
			model.addAttribute("mode", "new");
			return "formCinema";
		}
		
		@PostMapping(path ="/saveCinema" )
		public String saveCinema(@Valid Cinema cinema, BindingResult bindingResult) {
			if(bindingResult.hasErrors()) return "formCinema";
			cinemaRepository.save(cinema);
			return "formCinema";
		}
		
		@GetMapping(path = "/editCinema")
		public String editCinema(Model model, Long id) {
			Cinema v=cinemaRepository.findById(id).get();
			model.addAttribute("cinema", v);
			model.addAttribute("mode", "edit");
			return "formCinema";
		}
		
		/*
		 * ------------------------------------------------------------------ SALLE ------------------------------------------------
		 */
		
		@GetMapping(path = "/salle")
		public String salles(Model model,
				@RequestParam(name="page",defaultValue = "0")int page,
				@RequestParam(name="size",defaultValue = "10")int size,
				@RequestParam(name="keyword",defaultValue = "")String mc) {
			Page<Salle> pageSalle= salleRepository.findByNameContains(mc, PageRequest.of(page, size));
			model.addAttribute("salles",pageSalle.getContent());
			model.addAttribute("pages",new int[pageSalle.getTotalPages()]);
			model.addAttribute("currentPage", page);
			model.addAttribute("size", size);
			model.addAttribute("keyword", mc);
			return "salle";
		}
		
		@GetMapping(path = "/deleteSalle")
		public String deletesalle(Long id,String keyword,int page,int size) {
			salleRepository.deleteById(id);
			return "redirect:/salle?page="+page+"&size="+size+"&keyword="+keyword;
	}
	
		@GetMapping(path = "/formSalle")
		public String formSalle(Model model) {
			model.addAttribute("salle", new Salle());
			model.addAttribute("mode", "new");
			return "formSalle";
		}
		
		@PostMapping(path ="/saveSalle" )
		public String saveSalle(@Valid Salle salle, BindingResult bindingResult) {
			if(bindingResult.hasErrors()) return "formSalle";
			salleRepository.save(salle);
			return "formSalle";
		}
		
		@GetMapping(path = "/editSalle")
		public String editSalle(Model model, Long id) {
			Salle v=salleRepository.findById(id).get();
			model.addAttribute("salle", v);
			model.addAttribute("mode", "edit");
			return "formSalle";
		}
	
		/*
		 * ------------------------------------------------------------------ Place ------------------------------------------------
		 */
		
		@GetMapping(path = "/place")
		public String places(Model model,
				@RequestParam(name="page",defaultValue = "0")int page,
				@RequestParam(name="size",defaultValue = "26")int size,
				@RequestParam(name="keyword",defaultValue = "")String mc) {
			Page<Place> pagePlace= placeRepository.findAll(PageRequest.of(page, size));
			model.addAttribute("places",pagePlace.getContent());
			model.addAttribute("pages",new int[pagePlace.getTotalPages()]);
			model.addAttribute("currentPage", page);
			model.addAttribute("size", size);
			model.addAttribute("keyword", mc);
			return "place";
		}
		
		@GetMapping(path = "/deletePlace")
		public String deleteplace(Long id,String keyword,int page,int size) {
			placeRepository.deleteById(id);
			return "redirect:/place?page="+page+"&size="+size+"&keyword="+keyword;
	}
		@GetMapping(path = "/formPlace")
		public String formPlace(Model model) {
			model.addAttribute("place", new Place());
			model.addAttribute("mode", "new");
			return "formPlace";
		}
		
		@PostMapping(path ="/savePlace" )
		public String savePlace(@Valid Place place, BindingResult bindingResult) {
			if(bindingResult.hasErrors()) return "formPlace";
			placeRepository.save(place);
			return "formPlace";
		}
		
		@GetMapping(path = "/editPlace")
		public String editPlace(Model model, Long id) {
			Place v=placeRepository.findById(id).get();
			model.addAttribute("place", v);
			model.addAttribute("mode", "edit");
			return "formPlace";
		}
		/*
		 * ------------------------------------------------------------------ Film ------------------------------------------------
		 */
		
		@GetMapping(path = "/film")
		public String films(Model model,
				@RequestParam(name="page",defaultValue = "0")int page,
				@RequestParam(name="size",defaultValue = "4")int size,
				@RequestParam(name="keyword",defaultValue = "")String mc) {
			Page<Film> pageFilm= filmRepository.findByTitreContains(mc, PageRequest.of(page, size));
			model.addAttribute("films",pageFilm.getContent());
			model.addAttribute("pages",new int[pageFilm.getTotalPages()]);
			model.addAttribute("currentPage", page);
			model.addAttribute("size", size);
			model.addAttribute("keyword", mc);
			return "film";
		}
		
		@GetMapping(path = "/deleteFilm")
		public String deletefilm(Long id,String keyword,int page,int size) {
			filmRepository.deleteById(id);
			return "redirect:/film?page="+page+"&size="+size+"&keyword="+keyword;
	}
		@GetMapping(path = "/formFilm")
		public String formFilm(Model model) {
			model.addAttribute("film", new Film());
			model.addAttribute("mode", "new");
			return "formFilm";
		}
		
		@PostMapping(path ="/saveFilm" )
		public String saveFilm(@Valid Film film, BindingResult bindingResult) {
			if(bindingResult.hasErrors()) return "formFilm";
			filmRepository.save(film);
			return "formFilm";
		}
		
		@GetMapping(path = "/editFilm")
		public String editFilm(Model model, Long id) {
			Film v=filmRepository.findById(id).get();
			model.addAttribute("film", v);
			model.addAttribute("mode", "edit");
			return "formFilm";
		}
		
		/*
		 * --------------------------------------------------------------------SEANCE------------------------------------------------------
		 */		
		
		@GetMapping(path = "/seance")
		public String seances(Model model,
				@RequestParam(name="page",defaultValue = "0")int page,
				@RequestParam(name="size",defaultValue = "26")int size,
				@RequestParam(name="keyword",defaultValue = "")String mc) {
			Page<Seance> pageSeance= seanceRepository.findAll(PageRequest.of(page, size));
			model.addAttribute("seances",pageSeance.getContent());
			model.addAttribute("pages",new int[pageSeance.getTotalPages()]);
			model.addAttribute("currentPage", page);
			model.addAttribute("size", size);
			model.addAttribute("keyword", mc);
			return "seance";
		}
		
		@GetMapping(path = "/deleteSeance")
		public String deleteseance(Long id,String keyword,int page,int size) {
			seanceRepository.deleteById(id);
			return "redirect:/seance?page="+page+"&size="+size+"&keyword="+keyword;
	}
		
		
		@GetMapping(path = "/formSeance")
		public String formSeance(Model model) {
			model.addAttribute("seance", new Seance());
			model.addAttribute("mode", "new");
			return "formSeance";
		}
		
		@PostMapping(path ="/saveSeance" )
		public String saveSeance(@Valid Seance seance, BindingResult bindingResult) {
			if(bindingResult.hasErrors()) return "formSeance";
			seanceRepository.save(seance);
			return "formSeance";
		}
		
		@GetMapping(path = "/editSeance")
		public String editSeance(Model model, Long id) {
			Seance v=seanceRepository.findById(id).get();
			model.addAttribute("seance", v);
			model.addAttribute("mode", "edit");
			return "formSeance";
		}
		
		/*
		 * ---------------------------------TICKET-----------------------------------------------------------------------------------
		 */		
		
		
		@GetMapping(path = "/ticket")
		public String tickets(Model model,
				@RequestParam(name="page",defaultValue = "0")int page,
				@RequestParam(name="size",defaultValue = "100")int size,
				@RequestParam(name="keyword",defaultValue = "")String mc) {
			Page<Ticket> pageTickets= ticketRepository.findAll(PageRequest.of(page, size));
			model.addAttribute("tickets",pageTickets.getContent());
			model.addAttribute("pages",new int[pageTickets.getTotalPages()]);
			model.addAttribute("currentPage", page);
			model.addAttribute("size", size);
			model.addAttribute("keyword", mc);
			return "ticket";
		}
		
		
		@GetMapping(path = "/deleteTicket")
		public String deleteticket(Long id,String keyword,int page,int size) {
			ticketRepository.deleteById(id);
			return "redirect:/ticket?page="+page+"&size="+size+"&keyword="+keyword;
		}
		
		@GetMapping(path = "/formTicket")
		public String formTicket(Model model) {
			model.addAttribute("ticket", new Ticket());
			model.addAttribute("mode", "new");
			return "formTicket";
		}
		
		@PostMapping(path ="/saveTicket" )
		public String saveTicket(@Valid Ticket ticket, BindingResult bindingResult) {
			if(bindingResult.hasErrors()) return "formTicket";
			ticketRepository.save(ticket);
			return "formTicket";
		}
		
		@GetMapping(path = "/editTicket")
		public String editTicket(Model model, Long id) {
			Ticket v=ticketRepository.findById(id).get();
			model.addAttribute("tickt", v);
			model.addAttribute("mode", "edit");
			return "formTicket";
		}
		
		
		/*
		 * ------------------------------------------------------------------ Categorie ------------------------------------------------
		 */
		
		
		@GetMapping(path = "/categorie")
		public String categories(Model model,
				@RequestParam(name="page",defaultValue = "0")int page,
				@RequestParam(name="size",defaultValue = "4")int size,
				@RequestParam(name="keyword",defaultValue = "")String mc) {
			Page<Categorie> pageCategorie= categorieRepository.findByNameContains(mc, PageRequest.of(page, size));
			model.addAttribute("categories",pageCategorie.getContent());
			model.addAttribute("pages",new int[pageCategorie.getTotalPages()]);
			model.addAttribute("currentPage", page);
			model.addAttribute("size", size);
			model.addAttribute("keyword", mc);
			return "categorie";
		}
		
		@GetMapping(path = "/deleteCategorie")
		public String deletecategorie(Long id,String keyword,int page,int size) {
			categorieRepository.deleteById(id);
			return "redirect:/categorie?page="+page+"&size="+size+"&keyword="+keyword;
	}
	
		@GetMapping(path = "/formCategorie")
		public String formCategorie(Model model) {
			model.addAttribute("categorie", new Categorie());
			model.addAttribute("mode", "new");
			return "formCategorie";
		}
		
		@PostMapping(path ="/saveCategorie" )
		public String saveCategorie(@Valid Categorie categorie, BindingResult bindingResult) {
			if(bindingResult.hasErrors()) return "formCategorie";
			categorieRepository.save(categorie);
			return "formCategorie";
		}
		
		@GetMapping(path = "/editCategorie")
		public String editCategorie(Model model, Long id) {
			Categorie v=categorieRepository.findById(id).get();
			model.addAttribute("categorie", v);
			model.addAttribute("mode", "edit");
			return "formCategorie";
		}
		
	
		/*
		 * ------------------------------------------------------------------ Projection ------------------------------------------------
		 */
	
	
	  @GetMapping(path = "/projection") 
	  public String projections(Model model,
			  @RequestParam(name="page",defaultValue = "0")int page,
			  @RequestParam(name="size",defaultValue = "75")int size,
			  @RequestParam(name="keyword",defaultValue = "")String mc) {
		 Page<Projection> pageProjection= projectionRepository.findAll(PageRequest.of(page, size));
		 model.addAttribute("projections",pageProjection.getContent());
		 model.addAttribute("pages",new int[pageProjection.getTotalPages()]);
		 model.addAttribute("currentPage", page); 
		 model.addAttribute("size", size);
		 model.addAttribute("keyword", mc);
		 return "projection"; 
	}
	  
	  @GetMapping(path = "/deleteProjection") 
	  public String deleteprojection(Long id,String keyword,int page,int size) { 
		  projectionRepository.deleteById(id);
	  return "redirect:/projection?page="+page+"&size="+size+"&keyword="+keyword; }
	  
		@GetMapping(path = "/formProjection")
		public String formProjection(Model model) {
			model.addAttribute("projection", new Projection());
			model.addAttribute("mode", "new");
			return "formProjection";
		}
		
		@PostMapping(path ="/saveProjection" )
		public String saveProjection(@Valid Projection projection, BindingResult bindingResult) {
			if(bindingResult.hasErrors()) return "formProjection";
			projectionRepository.save(projection);
			return "formProjection";
		}
		
		@GetMapping(path = "/editProjection")
		public String editProjection(Model model, Long id) {
			Projection v=projectionRepository.findById(id).get();
			model.addAttribute("projection", v);
			model.addAttribute("mode", "edit");
			return "formProjection";
		}

		
	@GetMapping(path = "/index")
	public String index() {
		return "index";
	}

}
