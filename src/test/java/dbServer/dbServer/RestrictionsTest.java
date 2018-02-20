package dbServer.dbServer;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

@RunWith(JUnitPlatform.class)
class RestrictionsTest {
	
	Restrictions rs1 = null;
	Restrictions rs2 = null;
	
	@BeforeEach
    void beforeEach() {
    	
    	rs1 = new Restrictions();
    	rs2 = new Restrictions();
    	
    	rs1.setQuery("Select city from ipl.csv where id > 300");
 		rs2.setQuery("Select city from ipl.csv");
    	
        System.out.println("Before each test method");
    }
    

	@Test
	public void filterTest() {
		
		//assert statements
		assertEquals("id > 300", rs1.getFilter());
		assertEquals(null, rs2.getFilter());
	}
	
	@Test
	public void conditionsTest() {
		
		//assertStatements
		assertEquals("id > 300", rs1.getConditions().get(0));
		assertEquals("city like 'Delhi'", rs1.getConditions().get(1));
		assertEquals(new ArrayList<String>(), rs2.getConditions());
	}

	@Test
	public void operatorsTest() {
		
		//assertStatements
		assertEquals("and", rs1.getOperators().get(0));
		assertEquals(new ArrayList<String>(), rs2.getOperators());
	}
	
	@Test
	public void orderByFieldTest() {
		
		//assertStatements
		assertEquals("city", rs1.getOrderByField());
		assertEquals(null, rs2.getOrderByField());
	}
	
	@Test
	public void groupByFieldTest() {
		
		//assertStatements
		assertEquals("city", rs1.getGroupByField());
		assertEquals(null, rs2.getGroupByField());		
	}
}
