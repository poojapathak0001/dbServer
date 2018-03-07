package dbServer.dbServer;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;

@RunWith(Suite.class)
@SuiteClasses({QuerySelectorTest.class,RestrictionsTest.class})
class AggregateFunctionsTest {
	
	AggregateFunctions af1 = null;
	AggregateFunctions af2 = null;
	
	@BeforeEach
    void beforeEach() {
    	
    	af1 = new AggregateFunctions();
    	af2 = new AggregateFunctions();
 
        System.out.println("aggregate Test");
    }

	@Test
	public void aggregationFuncTest() {
				
		//assert statements to test aggregate function values
		assertEquals("count(city)", af1.extractAggregateFunc("Select count(city) from ipl.csv where id > 300").get(0));
		assertEquals(new ArrayList<String>(), af2.extractAggregateFunc("Select city from ipl.csv"));
	}

}