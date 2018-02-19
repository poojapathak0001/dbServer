package dbServer.dbServer;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.*;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

@RunWith(JUnitPlatform.class)
class RestrictionsTest {
	
	@Test
	public void filterTest() {
		
		Restrictions rs1 = new Restrictions("Select city from ipl.csv where id > 300");
		Restrictions rs2 = new Restrictions("Select city from ipl.csv");
		
		//calling setter functions
		rs1.setFilter();
		rs2.setFilter();
		
		//assert statements
		assertEquals("id > 300", rs1.getFilter());
		assertEquals(null, rs2.getFilter());
	}
	
	@Test
	public void conditionsTest() {
		
		Restrictions rs1 = new Restrictions("Select city from ipl.csv where id > 300 and city like 'Delhi'");
		Restrictions rs2 = new Restrictions("Select city from ipl.csv");
		
		//calling setter functions
		rs1.setConditions();
		rs2.setConditions();
		
		//assertStatements
		assertEquals("id > 300", rs1.getConditions().get(0));
		assertEquals("city like 'Delhi'", rs1.getConditions().get(1));
		assertEquals(new ArrayList<String>(), rs2.getConditions());
	}

	@Test
	public void operatorsTest() {
		
		Restrictions rs1 = new Restrictions("Select city from ipl.csv where id > 300 and city like 'Delhi'");
		Restrictions rs2 = new Restrictions("Select city from ipl.csv");
		
		//calling setter functions
		rs1.setOperators();
		rs2.setOperators();
		
		//assertStatements
		assertEquals("and", rs1.getOperators().get(0));
		assertEquals(new ArrayList<String>(), rs2.getOperators());
	}
	
	@Test
	public void orderByFieldTest() {
		
		Restrictions rs1 = new Restrictions("Select city from ipl.csv where id > 300 and city like 'Delhi' order by city");
		Restrictions rs2 = new Restrictions("Select city from ipl.csv");
		
		//calling setter functions
		rs1.setOrderByField();
		rs2.setOrderByField();
		
		//assertStatements
		assertEquals("city", rs1.getOrderByField());
		assertEquals(null, rs2.getOrderByField());
	}
	
	@Test
	public void groupByFieldTest() {
		
		Restrictions rs1 = new Restrictions("Select count(*) from ipl.csv where id > 300 and city like 'Delhi' group by city order by season");
		Restrictions rs2 = new Restrictions("Select city from ipl.csv");
		
		//calling setter functions
		rs1.setGroupByField();
		rs2.setGroupByField();
				
		//assertStatements
		assertEquals("city", rs1.getGroupByField());
		assertEquals(null, rs2.getGroupByField());		
	}
}
