package dbServer.dbServer;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.platform.runner.*;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;

@RunWith(JUnitPlatform.class)
class QuerySelectorTest {

	@Test
	//Test to check fileName 
	public void fileNameTest() {
		
		QuerySelector qs1 = new QuerySelector("select city,season from ipl.csv");
		
	    QuerySelector qs2 = new QuerySelector("select * from jkl");
	    
	    qs1.setFileName();
        qs2.setFileName();
        // assert statements
        assertEquals("ipl.csv", qs1.getFileName().get(0));
        assertNotSame("[]", qs2.getFileName());
        
    }
	
	@Test
	public void baseTest() {
		
		QuerySelector qs1 = new QuerySelector("select city,season from ipl.csv");
	    QuerySelector qs2 = new QuerySelector("select * from jkl.csv where city = 'Dehradun'");
	    
	    qs1.setBase();
        qs2.setBase();
        // assert statements
        assertEquals("select city,season from ipl.csv", qs1.getBase());
        assertEquals("select * from jkl.csv", qs2.getBase());
        
    }
	
	@Test
	public void fieldsTest() {
		
		QuerySelector qs1 = new QuerySelector("select city,season from ipl.csv");
		QuerySelector qs2 = new QuerySelector("select * from jkl.csv where city = 'Dehradun'");
		
		qs1.setFields();
		qs2.setFields();
		
		//assert statements
		assertEquals("city", qs1.getFields()[0]);
		assertEquals("season", qs1.getFields()[1]);		
        assertEquals("*", qs2.getFields()[0]);
	}

}