package dev.mdrobot.RouteWeatherFinder.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RouteQuery {

    public String start;
    public String end;
}
