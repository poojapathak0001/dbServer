package dbServer.dbServer;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RestrictionsTest {
	
	Restrictions rs1 = null;
	Restrictions rs2 = null;
	
	@BeforeEach
    void beforeEach() {
    	
    	rs1 = new Restrictions();
    	rs2 = new Restrictions();
    	
        System.out.println("Restriction test");
    }
    

	@Test
	public void filterTest() {
		
		//assert statements to test filter value
		assertEquals("id > 300", rs1.extractFilter("Select city from ipl.csv where id > 300"));
		assertEquals(null, rs2.extractFilter("Select city from ipl.csv"));
	}
	
	@Test
	public void conditionsTest() {
		
		//assertStatements to test conditions value
		assertEquals("id > 300", rs1.extractConditions("Select city from ipl.csv where id > 300").get(0));
		assertEquals("city like 'Delhi'", rs1.extractConditions("Select city from ipl.csv where id > 300").get(1));
		assertEquals(new ArrayList<String>(), rs2.extractConditions("Select city from ipl.csv"));
	}

	@Test
	public void operatorsTest() {
		
		//assertStatements to test operators value
		assertEquals("and", rs1.extractOperators("Select city from ipl.csv where id > 300").get(0));
		assertEquals(new ArrayList<String>(), rs2.extractOperators("Select city from ipl.csv"));
	}
	
	@Test
	public void orderByFieldTest() {
		
		//assertStatements to test order by value
		assertEquals("city", rs1.extractOrderByField("Select city from ipl.csv where id > 300"));
		assertEquals(null, rs2.extractOrderByField("Select city from ipl.csv"));
	}
	
	@Test
	public void groupByFieldTest() {
		
		//assertStatements to test group by value
		assertEquals("city", rs1.extractGroupByField("Select city from ipl.csv where id > 300"));
		assertEquals(null, rs2.extractGroupByField("Select city from ipl.csv"));		
	}
}