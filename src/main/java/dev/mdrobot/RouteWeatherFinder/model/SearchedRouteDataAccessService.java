package dev.mdrobot.RouteWeatherFinder.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


@Repository
public class SearchedRouteDataAccessService {

        private final JdbcTemplate jdbcTemplate;

        @Autowired
        public SearchedRouteDataAccessService(JdbcTemplate jdbcTemplate) {
            this.jdbcTemplate = jdbcTemplate;
        }


}
