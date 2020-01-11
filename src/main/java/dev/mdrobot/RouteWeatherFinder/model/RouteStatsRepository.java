package dev.mdrobot.RouteWeatherFinder.model;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RouteStatsRepository extends JpaRepository<RouteStats, Long>, CrudRepository<RouteStats, Long> {


}