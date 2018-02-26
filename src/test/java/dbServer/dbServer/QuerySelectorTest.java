package dbServer.dbServer;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.platform.runner.*;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;

@RunWith(JUnitPlatform.class)
class QuerySelectorTest {
		
		QuerySelector qs1 = null;
		QuerySelector qs2 = null;
		@BeforeEach
	    void beforeEach() {
	    	
	    	qs1 = new QuerySelector();
	    	
	    	
	    	qs2 = new QuerySelector();
	    	
	    	
	        System.out.println("Before each test method");
	    }
	
	    @Test
	    public void fileNameTest() {
	        // assert statements to test filename
            assertEquals("ipl.csv", qs1.extractFileName("select city,season from ipl.csv where id>100 ;"));
            assertNotSame("[]", qs2.extractFileName("select * from jkl"));  
	    }
	
		@Test
		public void baseTest() {
			
			// assert statements to test base value
	        assertEquals("select city,season from ipl.csv", qs1.extractBase("select city,season from ipl.csv where id>100 ;"));
	        assertEquals("select * from jkl.csv", qs2.extractBase("select * from jkl"));
	        
	    }
		
		@Test
		public void fieldsTest() {
			
			//assert statements to test field values
			assertEquals("city", qs1.extractFields("select city,season from ipl.csv where id>100 ;")[0]);
			assertEquals("season", qs1.extractFields("select city,season from ipl.csv where id>100 ;")[1]);		
	        assertEquals("*", qs2.extractFields("select * from jkl")[0]);
		}

}