package no.arktekk.training.spring;

import no.arktekk.training.spring.domain.Auction;
import no.arktekk.training.spring.repository.AuctionRepository;
import no.arktekk.training.spring.service.AuctionService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * @author <a href="mailto:kaare.nilsen@arktekk.no">Kaare Nilsen</a>
 */
public class DependencyInjectionTest {


    /**
     * Configure neccesary bean in applicationContext.xml and complete the
     * helper methods in order to get this test to run.
     * <p/>
     * TIP: The repository implementation is:
     * no.arktekk.training.spring.repository.impl.StubAuctionRepository
     */
    @Test public void step_1() {
        ApplicationContext ctx = createSpringContainer();
        assertNotNull(ctx);
        AuctionRepository auctionRepository = lookupAuctionRepository(ctx);
        assertNotNull(auctionRepository);
    }


    /**
     * Configure neccesary beans in applicationContext.xml and complete the
     * helper methods in order to get this test to run.
     * <p/>
     * TIP: The repository implementation is:
     * no.arktekk.training.spring.service.impl.DefaultAuctionService
     * TIP: The service needs the repository in order to work.
     */
    @Test public void step_2() {
        ApplicationContext ctx = createSpringContainer();
        AuctionService auctionService = lookupAuctionService(ctx);
        assertNotNull(auctionService);
    }

   /**
     * Complete TODO's
     * Replace all calls to fail() with proper test code
     */
    @Test public void step_3() {
        ApplicationContext ctx = createSpringContainer();
        AuctionRepository auctionRepository = lookupAuctionRepository(ctx);
        AuctionService auctionService = lookupAuctionService(ctx);

        //TODO: Check that there are no running auctions present using the allRunningAuctions() in AuctionService
        assertEquals(0, auctionService.allRunningAuctions().size());

        //TODO: Add 2 new Auction objects, using the auctionRepository
        auctionRepository.createNewAuction(new Auction(1, "My first auction"));
        auctionRepository.createNewAuction(new Auction(2, "My second auction"));

        //TODO: Check that there are now 2 running auctions
        assertEquals(2, auctionService.allRunningAuctions().size());

        //TODO: Use the auction service to query by id, and verify that the result is as expected
        Auction auction = auctionService.findById(1D);
        assertEquals("My first auction", auction.description());
    }


    private ApplicationContext createSpringContainer() {
        return new ClassPathXmlApplicationContext("applicationContext.xml");
    }


    private AuctionRepository lookupAuctionRepository(ApplicationContext ctx) {
        return ctx.getBean("auctionRepository", AuctionRepository.class);
    }

    private AuctionService lookupAuctionService(ApplicationContext ctx) {
        return ctx.getBean("auctionService", AuctionService.class);
    }
}
