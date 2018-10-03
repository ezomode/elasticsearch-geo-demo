package controllers;

import models.Apartment;
import models.dto.ApartmentDTO;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import services.ApartmentService;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

public class ApartmentController extends Controller {

	@Inject
	private ApartmentService apartmentService;

	public Result indexAllApartments() {

		Apartment.find.findEach(apartmentService::saveToElastic);

		return ok("Scheduled indexing of all apartments.");
	}

	public Result indexApartment(@Valid @NotNull Long id) {

		Apartment apartment = Apartment.find.byId(id);
		if (apartment != null) {
			apartmentService.saveToElastic(apartment);
			return ok("Scheduled indexing for id=" + id);
		}

		return badRequest("Apartment with id=" + id + " not found");
	}

	// Generic search for _all meta field.
	public Result search(String query) {
		List<ApartmentDTO> result = apartmentService.search(query);

		return ok(Json.toJson(result));
	}

	public Result searchByDistance(Long distance, Double lat, Double lon) {
		List<ApartmentDTO> apartments = apartmentService.searchByDistance(distance, lat, lon);

		return ok(Json.toJson(apartments));
	}
}
