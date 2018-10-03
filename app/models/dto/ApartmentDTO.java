package models.dto;

import models.Apartment;
import models.Facility;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ApartmentDTO {

	public Long id;
	public String name;
	public Map<String, Double> location;
	public String apartmentType;
	public List<String> facilities;

	public static ApartmentDTO valueOf(Apartment apartment) {
		ApartmentDTO dto = new ApartmentDTO();

		dto.id = apartment.getId();
		dto.name = apartment.getName();

		dto.location = new HashMap<String, Double>() {{
			put("lat", apartment.getLatitude());
			put("lon", apartment.getLongitude());
		}};

		dto.apartmentType = apartment.getApartmentType().getName();

		dto.facilities = apartment.getFacilities()
						.stream()
						.map(Facility::getName)
						.collect(Collectors.toList());

		return dto;
	}
}
