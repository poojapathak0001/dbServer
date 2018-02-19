package dbServer.dbServer;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;


@RunWith(JUnitPlatform.class)
class ReadFileTest {

	@Test(expected = FileNotFoundException.class)
	public void readFileTest() {
		
		ReadFile rf = new ReadFile("select * from ip.csv");
		
	}

}
