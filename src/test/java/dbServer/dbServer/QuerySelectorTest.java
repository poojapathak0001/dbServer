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
	    	qs1.setQuery("select * from ipl.csv where id>100 ;");
	    	
	    	qs2 = new QuerySelector();
	    	qs2.setQuery("select * from jkl");
	    	
	        System.out.println("Before each test method");
	    }
	    
	    @Test
	    public void queryTest() {
	    	
	    	assertEquals("select * from ipl.csv where id>100 ;",qs1.getQuery());
	    }
	
	    @Test
	    public void fileNameTest() {
	        // assert statements
            assertEquals("ipl.csv", qs1.getFileName().get(0));
            assertNotSame("[]", qs2.getFileName());  
	    }
	
		@Test
		public void baseTest() {
			
			// assert statements
	        assertEquals("select city,season from ipl.csv", qs1.getBase());
	        assertEquals("select * from jkl.csv", qs2.getBase());
	        
	    }
		
		@Test
		public void fieldsTest() {
			
			//assert statements
			assertEquals("city", qs1.getFields()[0]);
			assertEquals("season", qs1.getFields()[1]);		
	        assertEquals("*", qs2.getFields()[0]);
		}

}