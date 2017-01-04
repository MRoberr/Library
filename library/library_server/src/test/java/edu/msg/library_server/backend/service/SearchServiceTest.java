package edu.msg.library_server.backend.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

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
	 * Tests the publication view (Publication List) search by title at the
	 * moment is testing in local database, any time is used from a different
	 * computer the test field it need's to be changed
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
			assertFalse(searchlist.isEmpty());
			assertEquals("zoli", searchlist.get(0).getTitle());
		} catch (Exception e) {

		}
	}

	/**
	 * Tests the publication view (Publication List) search by UUid at the
	 * moment is testing in local database, any time is used from a different
	 * computer the test field it need's to be changed
	 */
	@Test
	public void TestSearchPublicationByUUID() {
		try {
			searchService = new SearchService();
		} catch (RemoteException e1) {
			e1.printStackTrace();
		}
		try {
			List<Publication> searchlist = searchService.searchPublicationByUUID("23");
			assertEquals("23", searchlist.get(0).getUUID());
		} catch (Exception e) {

		}
	}

}
