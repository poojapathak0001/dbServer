package dbServer.dbServer;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;


@RunWith(JUnitPlatform.class)
class ReadFileTest {
	ReadFile rf = null;
	
	@BeforeEach
    void beforeEach() {
    	
    	rf = new ReadFile();
    	rf.setFileName("ipl.csv");
    	
        System.out.println("Before each test method");
    }

	@Test(expected = FileNotFoundException.class)
	public void readFileTest() {
		
		String[] header = {};
		
		assertEquals(header, rf.getHeader());
	}

}
