
package pl.debememe.demo.maps.JSON;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "distance",
    "duration",
    "end_location",
    "html_instructions",
    "polyline",
    "start_location",
    "travel_mode",
    "maneuver"
})
public class Step {

    @JsonProperty("distance")
    private Distance_ distance;
    @JsonProperty("duration")
    private Duration_ duration;
    @JsonProperty("end_location")
    private EndLocation_ endLocation;
    @JsonProperty("html_instructions")
    private String htmlInstructions;
    @JsonProperty("polyline")
    private Polyline polyline;
    @JsonProperty("start_location")
    private StartLocation_ startLocation;
    @JsonProperty("travel_mode")
    private String travelMode;
    @JsonProperty("maneuver")
    private String maneuver;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("distance")
    public Distance_ getDistance() {
        return distance;
    }

    @JsonProperty("distance")
    public void setDistance(Distance_ distance) {
        this.distance = distance;
    }

    @JsonProperty("duration")
    public Duration_ getDuration() {
        return duration;
    }

    @JsonProperty("duration")
    public void setDuration(Duration_ duration) {
        this.duration = duration;
    }

    @JsonProperty("end_location")
    public EndLocation_ getEndLocation() {
        return endLocation;
    }

    @JsonProperty("end_location")
    public void setEndLocation(EndLocation_ endLocation) {
        this.endLocation = endLocation;
    }

    @JsonProperty("html_instructions")
    public String getHtmlInstructions() {
        return htmlInstructions;
    }

    @JsonProperty("html_instructions")
    public void setHtmlInstructions(String htmlInstructions) {
        this.htmlInstructions = htmlInstructions;
    }

    @JsonProperty("polyline")
    public Polyline getPolyline() {
        return polyline;
    }

    @JsonProperty("polyline")
    public void setPolyline(Polyline polyline) {
        this.polyline = polyline;
    }

    @JsonProperty("start_location")
    public StartLocation_ getStartLocation() {
        return startLocation;
    }

    @JsonProperty("start_location")
    public void setStartLocation(StartLocation_ startLocation) {
        this.startLocation = startLocation;
    }

    @JsonProperty("travel_mode")
    public String getTravelMode() {
        return travelMode;
    }

    @JsonProperty("travel_mode")
    public void setTravelMode(String travelMode) {
        this.travelMode = travelMode;
    }

    @JsonProperty("maneuver")
    public String getManeuver() {
        return maneuver;
    }

    @JsonProperty("maneuver")
    public void setManeuver(String maneuver) {
        this.maneuver = maneuver;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
