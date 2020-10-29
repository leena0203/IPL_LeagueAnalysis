package IPL_LeagueAnalysis;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

import com.google.gson.Gson;

import CSVReader.CSVBuilderExecption;


public class IPL_LeagueAnalysisTest {
	String FileName_MostRuns = "C:\\Users\\leena\\eclipse-workspace\\IPL_LeagueAnalysis\\WP DP Data_01 IPL2019FactsheetMostRuns.csv";
	String FileName_MostWickets = "C:\\\\Users\\\\leena\\\\eclipse-workspace\\\\IPL_LeagueAnalysis\\\\WP DP Data_02 IPL2019FactsheetMostWkts.csv";

	@Test
	public void givenCSVFileOfRuns_ReturnsNumOfRecord() {
		IPL_LeagueAnalysis test = new IPL_LeagueAnalysis();
		try {
			assertEquals(101, test.loadMostRunsCSVFile(FileName_MostRuns));
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	@Test
	public void givenCSVFileOfWickets_ReturnsNumOfRecord() {
		IPL_LeagueAnalysis test = new IPL_LeagueAnalysis();
		try {
			assertEquals(99, test.loadMostWicketsCSVFile(FileName_MostWickets));
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	@Test
	public void givenCSVFileOfRuns_IfMatchTopBattingAvg_ReturnsThePlayer() throws IOException, CSVBuilderExecption {
		IPL_LeagueAnalysis test = new IPL_LeagueAnalysis();
		test.loadMostRunsCSVFile(FileName_MostRuns);
		String result = test.getTopBatting();
		IPLMostRuns[] mostRuns = new Gson().fromJson(result, IPLMostRuns[].class);
		assertEquals(83.2, mostRuns[0].average, 0.0);
		System.out.println("Cricketer with top batting avg is: "+mostRuns[0].player+" with batting average: "+mostRuns[0].average );
	}
	@Test
	public void givenCSVFileOfRuns_IfMatchTopStrikingRate_ReturnsThePlayer() throws IOException, CSVBuilderExecption {
		IPL_LeagueAnalysis test = new IPL_LeagueAnalysis();
		test.loadMostRunsCSVFile(FileName_MostRuns);
		String result = test.getTopStrike();
		IPLMostRuns[] mostRuns = new Gson().fromJson(result, IPLMostRuns[].class);
		assertEquals(333.33, mostRuns[0].strikeRate, 0.0);
		System.out.println("Batsman with Highest Strike Rate is: "+mostRuns[0].player+" with StrikeRate: "+mostRuns[0].strikeRate);
	}
	@Test
	public void givenCSVFileOfRuns_whenSortedOnMax4sAnd6s_ReturnsThePlayer() throws IOException, CSVBuilderExecption {
		IPL_LeagueAnalysis test = new IPL_LeagueAnalysis();
		test.loadMostRunsCSVFile(FileName_MostRuns);
		String result = test.getMax6sAnd4s();
		IPLMostRuns[] mostRuns = new Gson().fromJson(result, IPLMostRuns[].class);
		assertEquals("Andre Russell", mostRuns[0].player);
		System.out.println("Criketer who hit maximum 6s and 4s: "+mostRuns[0].player+" with 4s: "+mostRuns[0].fours+" aand 6s: "+mostRuns[0].sixes);
}
}
