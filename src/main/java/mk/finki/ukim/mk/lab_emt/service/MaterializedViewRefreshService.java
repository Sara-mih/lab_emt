package mk.finki.ukim.mk.lab_emt.service;

import jakarta.transaction.Transactional;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class MaterializedViewRefreshService {

    private final JdbcTemplate jdbcTemplate;

    public MaterializedViewRefreshService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Scheduled(fixedRateString = "${materialized.view.refresh.rate:60000}")
    @Transactional
    public void refreshMaterializedView() {
        jdbcTemplate.execute("REFRESH MATERIALIZED VIEW accommodation_stats_view");
        System.out.println("Materialized view refreshed!");
    }
}