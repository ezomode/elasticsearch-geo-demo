package services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import models.Apartment;
import models.dto.ApartmentDTO;
import org.elasticsearch.action.admin.indices.exists.indices.IndicesExistsRequest;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.IndicesAdminClient;
import org.elasticsearch.common.unit.DistanceUnit;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.GeoDistanceQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import play.Logger;
import play.libs.Json;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Singleton
public class ApartmentService {

	public static final String INDEX = "geodemo";
	public static final String APARTMENT_TYPE = "apartment";

	private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

	private final ElasticsearchService elasticsearchService;

	@Inject
	public ApartmentService(ElasticsearchService elasticsearchService) {
		this.elasticsearchService = elasticsearchService;
		this.setApartmentMapping();
	}

	public void saveToElastic(Apartment apartment) {
		setApartmentMapping();

		Client client = elasticsearchService.getClient();

		try {
			String apartmentJson = OBJECT_MAPPER.writeValueAsString(ApartmentDTO.valueOf(apartment));

			Logger.info("Adding apartment to ES: {}", apartmentJson);

			client.prepareIndex(INDEX, APARTMENT_TYPE, apartment.getId().toString())
							.setSource(apartmentJson, XContentType.JSON)
							.execute();

		} catch (JsonProcessingException e) {
			e.printStackTrace();
			Logger.error("Error serializing apartment to JSON, id was {}", apartment.getId());
		}
	}

	public List<ApartmentDTO> search(String query) {
		Client client = elasticsearchService.getClient();

		SearchHit[] searchHits = client.prepareSearch(INDEX)
						.setTypes(APARTMENT_TYPE)
						.setQuery(QueryBuilders.queryStringQuery(query))
						.get()
						.getHits()
						.getHits();

		return searchHitsToApartmentDto(searchHits);
	}

	public List<ApartmentDTO> searchByDistance(Long distance, Double lat, Double lon) {
		Client client = elasticsearchService.getClient();

		GeoDistanceQueryBuilder query = QueryBuilders.geoDistanceQuery("location")
						.point(lat, lon)
						.distance(distance, DistanceUnit.KILOMETERS);

		SearchHit[] searchHits = client.prepareSearch(INDEX)
						.setTypes(APARTMENT_TYPE)
						.setQuery(query)
						.get()
						.getHits()
						.getHits();

		return searchHitsToApartmentDto(searchHits);
	}

	private List<ApartmentDTO> searchHitsToApartmentDto(SearchHit[] searchHits) {

		return Arrays.stream(searchHits)
						.map(SearchHit::getSource)
						.map(Json::toJson)
						.map(jsonString -> Json.fromJson(jsonString, ApartmentDTO.class))
						.collect(Collectors.toList());
	}

	// Sets initial type mapping for Apartment, iff index is not there yet.
	// The template enables _all meta field, and sets "location" field type to "geo_point".
	private void setApartmentMapping() {
		IndicesAdminClient indicesAdminClient = elasticsearchService.getClient().admin().indices();

		boolean indexExists = indicesAdminClient
						.exists(new IndicesExistsRequest(INDEX))
						.actionGet()
						.isExists();

		if (!indexExists) {
			indicesAdminClient
							.prepareCreate(INDEX)
							.addMapping(APARTMENT_TYPE,
													"{" +
													"        \"_all\": {" +
													"          \"enabled\": true" +
													"        }," +
													"        \"properties\": {" +
													"          \"location\": {" +
													"            \"type\": \"geo_point\"" +
													"          }" +
													"        }" +
													"}",
													XContentType.JSON)
							.get();
		}
	}
}
