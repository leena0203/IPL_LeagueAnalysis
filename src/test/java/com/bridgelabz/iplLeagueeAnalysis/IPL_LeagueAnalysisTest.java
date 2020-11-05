package com.bridgelabz.iplLeagueeAnalysis;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.List;

import org.junit.Test;

import com.bridgelabz.iplLeagueAnalysis.IPLMostRuns;
import com.bridgelabz.iplLeagueAnalysis.IPLMostWickets;
import com.bridgelabz.iplLeagueAnalysis.IPL_LeagueAnalysis;
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
	public void givenCSVFileOfRuns_IfMatchTopBattingAvg_ReturnsThePlayer() 
			throws IOException, CSVBuilderExecption {
		IPL_LeagueAnalysis test = new IPL_LeagueAnalysis();
		test.loadMostRunsCSVFile(FileName_MostRuns);
		String result = test.getTopBatting();
		IPLMostRuns[] mostRuns = new Gson().fromJson(result, IPLMostRuns[].class);
		assertEquals(83.2, mostRuns[0].average, 0.0);
		System.out.println("1)Cricketer with top batting avg is: \n"+mostRuns[0].player+" with batting average: "+mostRuns[0].average );
	}
	@Test
	public void givenCSVFileOfRuns_IfMatchTopStrikingRate_ReturnsThePlayer() 
			throws IOException, CSVBuilderExecption {
		IPL_LeagueAnalysis test = new IPL_LeagueAnalysis();
		test.loadMostRunsCSVFile(FileName_MostRuns);
		String result = test.getTopStrike();
		IPLMostRuns[] mostRuns = new Gson().fromJson(result, IPLMostRuns[].class);
		assertEquals(333.33, mostRuns[0].strikeRate, 0.0);
		System.out.println("2)Batsman with Highest Strike Rate is: \n"+mostRuns[0].player+" with StrikeRate: "+mostRuns[0].strikeRate);
	}
	@Test
	public void givenCSVFileOfRuns_whenSortedOnMax4sAnd6s_ReturnsThePlayer() 
			throws IOException, CSVBuilderExecption {
		IPL_LeagueAnalysis test = new IPL_LeagueAnalysis();
		test.loadMostRunsCSVFile(FileName_MostRuns);
		String result = test.getMax6sAnd4s();
		IPLMostRuns[] mostRuns = new Gson().fromJson(result, IPLMostRuns[].class);
		assertEquals("Andre Russell", mostRuns[0].player);
		System.out.println("3)Criketer who hit maximum 6s and 4s:\n"+mostRuns[0].player+" with 4s: "+mostRuns[0].fours+" aand 6s: "+mostRuns[0].sixes);
	}
	@Test
	public void givenCSVFileOfRuns_whenSortedOnMaxStrikeRate4sAnd6s_ReturnsThePlayer() throws IOException, CSVBuilderExecption {
		IPL_LeagueAnalysis test = new IPL_LeagueAnalysis();
		test.loadMostRunsCSVFile(FileName_MostRuns);
		String result = test.bestStrikeWith4s6s();
		assertEquals("Andre Russell", result);
		System.out.println("4)Criketer with highest StrikeRate and maximum 6s and 4s: \n"+result);
	}
	@Test
	public void givenCSVFileOfRuns_whenSortedOnBestAvgWithStrikeRate_ReturnsThePlayer() 
			throws IOException, CSVBuilderExecption {
		IPL_LeagueAnalysis test = new IPL_LeagueAnalysis();
		test.loadMostRunsCSVFile(FileName_MostRuns);
		String result = test.bestAvgWithStrikeRate();
		IPLMostRuns[] mostRuns = new Gson().fromJson(result, IPLMostRuns[].class);
		assertEquals("MS Dhoni", mostRuns[0].player);
		System.out.println("5)Criketer with highest batting avg with best strike rate: \n"+mostRuns[0].player);
	}
	@Test
	public void givenCSVFileOfRuns_whenSortedOnBestAvgWithMaxRuns_ReturnsThePlayer() 
			throws IOException, CSVBuilderExecption {
		IPL_LeagueAnalysis test = new IPL_LeagueAnalysis();
		test.loadMostRunsCSVFile(FileName_MostRuns);
		String result = test.bestAvgWithMaxRuns();
		IPLMostRuns[] mostRuns = new Gson().fromJson(result, IPLMostRuns[].class);
		assertEquals("David Warner", mostRuns[0].player);
		System.out.println("6)Criketer with best average and max runs: \n"+mostRuns[0].player);
	}
	@Test
	public void givenCSVFileOfWickets_IfMatchTopBowlingAvg_ReturnsThePlayer() 
			throws IOException, CSVBuilderExecption {
		IPL_LeagueAnalysis test = new IPL_LeagueAnalysis();
		test.loadMostWicketsCSVFile(FileName_MostWickets);
		String result = test.getSortedOnBowlingAvg();
		IPLMostWickets[] mostRuns = new Gson().fromJson(result, IPLMostWickets[].class);
		assertEquals("Anukul Roy", mostRuns[0].player);
		System.out.println("7)Cricketer with top bowling avg is: \n"+mostRuns[0].player+" with bowling average: "+mostRuns[0].avg );
	}
	@Test
	public void givenCSVFileOfWickets_IfMatchTopBowlingStrikeRate_ReturnsThePlayer() 
			throws IOException, CSVBuilderExecption {
		IPL_LeagueAnalysis test = new IPL_LeagueAnalysis();
		test.loadMostWicketsCSVFile(FileName_MostWickets);
		String result = test.getSortedOnStrikeRate();
		IPLMostWickets[] mostRuns = new Gson().fromJson(result, IPLMostWickets[].class);
		assertEquals("Alzarri Joseph", mostRuns[0].player);
		System.out.println("8)Cricketer with top Strike Rate is: \n"+mostRuns[0].player+" with strike rate: "+mostRuns[0].strikeRate );
}
	@Test
	public void givenCSVFileOfWickets_IfMatchTopBowlingEconomy_ReturnsThePlayer() 
			throws IOException, CSVBuilderExecption {
		IPL_LeagueAnalysis test = new IPL_LeagueAnalysis();
		test.loadMostWicketsCSVFile(FileName_MostWickets);
		String result = test.getSortedOnEconomy();
		IPLMostWickets[] mostRuns = new Gson().fromJson(result, IPLMostWickets[].class);
		assertEquals("Shivam Dube", mostRuns[0].player);
		System.out.println("9)Cricketer with top Strike Rate is: \n"+mostRuns[0].player+" with strike rate: "+mostRuns[0].strikeRate );
}
	@Test
	public void givenCSVFileOfWickets_IfMatchTopStrikeWith5wAnd4w_ReturnsThePlayer() 
			throws IOException, CSVBuilderExecption {
		IPL_LeagueAnalysis test = new IPL_LeagueAnalysis();
		test.loadMostWicketsCSVFile(FileName_MostWickets);
		String result = test.getPlayerWithBestStrikeRateWith4w5w();
		assertEquals("Alzarri Joseph", result);
		System.out.println("10)Cricketer with top Strike Rate with 4w and 5w is: \n"+result );
}
	@Test
	public void givenCSVFileOfWickets_IfMatchTopBowlingAvgAndStrikeRate_ReturnsThePlayer() 
			throws IOException, CSVBuilderExecption {
		IPL_LeagueAnalysis test = new IPL_LeagueAnalysis();
		test.loadMostWicketsCSVFile(FileName_MostWickets);
		String result = test.getSortedOnBowlingAvgAndStrikeRate();
		IPLMostWickets[] mostRuns = new Gson().fromJson(result, IPLMostWickets[].class);
		assertEquals("Anukul Roy", mostRuns[0].player);
		System.out.println("11)Cricketer with top bowling avg and high strike rate is: \n"+mostRuns[0].player);
	}
	@Test
	public void givenCSVFileOfWickets_PlayerTakesMaxWktsAndHaveMaxBowlingAvg_ReturnsThePlayer() 
			throws IOException, CSVBuilderExecption {
		IPL_LeagueAnalysis test = new IPL_LeagueAnalysis();
		test.loadMostWicketsCSVFile(FileName_MostWickets);
		String sortedCSVData = test.getSortedOnWktsAndAvg();
		IPLMostWickets[] iplCSV = new Gson().fromJson(sortedCSVData, IPLMostWickets[].class);
		assertEquals("Anukul Roy", iplCSV[0].player);
		System.out.println("12)Cricketer with top bowling avg and high strike rate is: \n"+iplCSV[0].player);
	}
	public void givenWktsData_WhenSortedOnBattingAndBowlingAvg_ShouldReturnTrue()
			throws IOException, CSVBuilderExecption{
		IPL_LeagueAnalysis test = new IPL_LeagueAnalysis();
		test.loadMostRunsCSVFile(FileName_MostRuns);
		test.loadMostWicketsCSVFile(FileName_MostWickets);
		List<String> sortedCSVData = test.getSortedOnBestBattingAndBowlingAvg();
		assertEquals("Andre Russell", sortedCSVData.get(0));
		assertEquals("Marcus Stoinis", sortedCSVData.get(1));
	}
	@Test
	public void givenWktsData_WhenSortedOnMaxRunsAndWkts_ShouldReturnTrue()
			throws IOException, CSVBuilderExecption {
		IPL_LeagueAnalysis test = new IPL_LeagueAnalysis();
		test.loadMostRunsCSVFile(FileName_MostRuns);
		test.loadMostWicketsCSVFile(FileName_MostWickets);
		List<String> sortedCSVData = test.getSortedOnMaxRunsAndWkts();
		assertEquals("Andre Russell", sortedCSVData.get(0));
		assertEquals("Hardik Pandya", sortedCSVData.get(1));
	}
	@Test
	public void givenWktsData_WhenSortedOnMaxWicketsAndBowlingAvg_ShouldReturnTrue()
			throws IOException, CSVBuilderExecption {
		IPL_LeagueAnalysis test = new IPL_LeagueAnalysis();
		test.loadMostRunsCSVFile(FileName_MostRuns);
		List<IPLMostRuns> sortedCSVData = test.getSortedOnMaxHundredsAndBattingAverage();
		assertEquals("David Warner", sortedCSVData.get(0).player);
		assertEquals("Jonny Bairstow", sortedCSVData.get(1).player);
	}
	@Test
	public void givenWktsData_WhenSortedOnZeroCenturiesAndBestBattingAvg()
			throws IOException, CSVBuilderExecption{
		IPL_LeagueAnalysis test = new IPL_LeagueAnalysis();
		test.loadMostRunsCSVFile(FileName_MostRuns);
		List<IPLMostRuns> sortedCSVData = test.getSortedOnZeroCenturiesAndBestBattingAvg();
		assertEquals("Marcus Stoinis", sortedCSVData.get(0).player);
	}
}