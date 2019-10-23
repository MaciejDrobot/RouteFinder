package pl.debememe.demo.maps;

import com.fasterxml.jackson.annotation.JsonProperty;
import pl.debememe.demo.movie.Movie;

import java.util.List;

public class LocationsResponse {

    private String totalResults;

    @JsonProperty("Search")
    private List<Locations> search;

    @JsonProperty("Response")
    private String response;

    public String getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(String totalResults) {
        this.totalResults = totalResults;
    }

    public List<Locations> getSearch() {
        return search;
    }

    public void setSearch(List<Locations> search) {
        this.search = search;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}
