package pl.debememe.demo.maps;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class LocationsResponse {

    private String totalResults;

    @JsonProperty("Search")
    private List<Location> search;

    @JsonProperty("Response")
    private String response;

    public String getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(String totalResults) {
        this.totalResults = totalResults;
    }

    public List<Location> getSearch() {
        return search;
    }

    public void setSearch(List<Location> search) {
        this.search = search;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}
