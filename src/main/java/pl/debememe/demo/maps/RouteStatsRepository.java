package pl.debememe.demo.maps;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RouteStatsRepository extends JpaRepository<RouteStats, Long> {

}