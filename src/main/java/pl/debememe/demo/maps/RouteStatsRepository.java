package pl.debememe.demo.maps;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public class RouteStatsRepository {

    public void save(RouteStats stats) {
    }

    @Repository
    public interface NoteRepository extends JpaRepository<RouteStats, Long> {

    }
}
