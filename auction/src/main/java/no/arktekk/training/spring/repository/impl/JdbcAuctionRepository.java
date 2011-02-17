package no.arktekk.training.spring.repository.impl;

import no.arktekk.training.spring.domain.Auction;
import no.arktekk.training.spring.mappers.AuctionMapper;
import no.arktekk.training.spring.repository.AuctionRepository;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

import static no.arktekk.training.spring.util.DatabaseUtils.no_NO;
import static no.arktekk.training.spring.util.DatabaseUtils.timeStampFormatter;


/**
 * @author <a href="mailto:kaare.nilsen@arktekk.no">Kaare Nilsen</a>
 */
@Repository
public class JdbcAuctionRepository implements AuctionRepository {
    private final SimpleJdbcTemplate template;

    @Autowired
    public JdbcAuctionRepository(DataSource dataSource) {
        this.template = new SimpleJdbcTemplate(dataSource);
    }

    public List<Auction> listAllRunningAuctions() {
        DateTime now = new DateTime();
        return template.query(
                "select * from Auction where ? between starts and expires",
                new AuctionMapper(),
                timeStampFormatter.print(now.toDate(), no_NO));
    }
}
