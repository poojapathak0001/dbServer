package dbServer.dbServer;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

@RunWith(JUnitPlatform.class)
class AggregateFunctionsTest {

	@Test
	public void aggregationFuncTest() {
		
		AggregateFunctions af1 = new AggregateFunctions("Select count(city) from ipl.csv where id > 300");
		AggregateFunctions af2 = new AggregateFunctions("Select city from ipl.csv");
		
		//calling setter functions
		af1.setAggregateFunc();
		af2.setAggregateFunc();
		
		//assert statements
		assertEquals("count(city)", af1.getAggregateFunc().get(0));
		assertEquals(new ArrayList<String>(), af2.getAggregateFunc());
	}

}
