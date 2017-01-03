package edu.msg.library_server.backend.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import java.rmi.RemoteException;
import java.util.List;
import org.junit.Test;
import edu.msg.library_common.model.Publication;

/**
 * @author nagyz
 *
 */
public class SearchServiceTest {
	public SearchService searchService;

	
	/**
	 * Tests the publication view (Publication List) search by title 
	 * At the moment is testing in local database, any time is used from a different computer the test field it need's to be changed
	 */
	@Test
	public void testSearchPublicationByTitles() {
		try {
			searchService = new SearchService();
		} catch (RemoteException e1) {
			e1.printStackTrace();
		}
		try {
			List<Publication> searchlist = searchService.searchPublicationByTitles("zoli");
			assertTrue(!searchlist.isEmpty());
			Publication pb = new Publication();
			pb.setTitle("zoli");
			assertEquals(pb.getTitle(), searchlist.get(0).getTitle());
		} catch (Exception e) {

		}
	}

}
