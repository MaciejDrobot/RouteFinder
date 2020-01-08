package dev.mdrobot.RouteWeatherFinder.model;
import dev.mdrobot.RouteWeatherFinder.model.RouteStats;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RouteStatsRepository extends JpaRepository<RouteStats, Long> {

}