package IPL_LeagueAnalysis;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import com.google.gson.Gson;

import CSVReader.CSVBuilderExecption;
import CSVReader.CSVBuilderFactory;
import CSVReader.ICSVBuilder;

public class IPL_LeagueAnalysis {
	List<IPLMostRuns> csvRuns = null;
	List<IPLMostWickets> csvWickets = null;

	public static void main(String[] args) {
		System.out.println("Welcome to IPL League Analysis");
	}
	/**
	 * Load CSVFiles
	 * @param fileName
	 * @return
	 * @throws IOException
	 * @throws CSVBuilderExecption
	 */
	public int loadMostRunsCSVFile(String fileName) throws IOException, CSVBuilderExecption{
		Reader read = Files.newBufferedReader(Paths.get(fileName));
		ICSVBuilder csv = CSVBuilderFactory.createCSVBuilder();
		csvRuns = csv.getCSVFileList(read, IPLMostRuns.class);
		return csvRuns.size();
	}
	public int loadMostWicketsCSVFile(String fileName) throws IOException, CSVBuilderExecption{
		Reader read = Files.newBufferedReader(Paths.get(fileName));
		ICSVBuilder csv = CSVBuilderFactory.createCSVBuilder();
		csvWickets = csv.getCSVFileList(read, IPLMostWickets.class);
		return csvWickets.size();
	}
	/**
	 * Sort method in descnding order
	 * @param <E>
	 * @param list
	 * @param censusComparator
	 */
	private<E> void sort(List<E> list, Comparator<E> iplComparator) {
		for (int i =0; i < list.size(); i++) {
			for(int j =0; j < list.size() - i - 1; j++) {
				E player1 = list.get(j);
				E player2 = list.get(j+1);
				if (iplComparator.compare(player1, player2) > 0) {
					list.set(j, player2);
					list.set(j + 1, player1);
				}
			}
		}
	}
	
	/**
	 * UC1_Return Top Batting Avg.
	 * @return
	 */
	public String getTopBatting() {
		Comparator<IPLMostRuns> iplComparator = Comparator.comparing(ipl -> ipl.average);
		this.sort(csvRuns,iplComparator.reversed());
		String sortedAvg = new Gson().toJson(csvRuns);
		return sortedAvg;
	}
	/**
	 * UC2_Return Highest Strike rate
	 * @return
	 */
	public String getTopStrike() {
		Comparator<IPLMostRuns> iplComparator = Comparator.comparing(ipl -> ipl.strikeRate);
		this.sort(csvRuns,iplComparator.reversed());
		String sortedStrikeRate = new Gson().toJson(csvRuns);
		return sortedStrikeRate;
	}
	/**
	 * UC3_Return sorted list according to max 4s and 6s
	 * @return
	 */
	public String getMax6sAnd4s() {
		Comparator<IPLMostRuns> iplComparator = Comparator.comparing(ipl -> (ipl.fours)+(ipl.sixes));
		this.sort(csvRuns,iplComparator.reversed());
		String sortedHit = new Gson().toJson(csvRuns);
		return sortedHit;
	}
	
	/**
	 * UC4_Return player with highest strike
	 * @return
	 */
	public String bestStrikeWith4s6s() {
		double max = 0;
		double strike = 0;
		double temp = 0;
		double tempSR = 0;
		String name ="";
		for (int i =0; i< csvRuns.size();i++) {
			temp = (csvRuns.get(i).fours + csvRuns.get(i).sixes);
					tempSR = temp / csvRuns.get(i).bf;
					if( temp> max && tempSR>strike) {
						max = temp;
						strike = tempSR;
						name = csvRuns.get(i).player;
					}
		}
		return name;
	}
	/**
	 * UC5_Return player with highest strike rate and highest average
	 * @return
	 */
	public String bestAvgWithStrikeRate() {
		Comparator<IPLMostRuns> iplComparator = Comparator.comparing(ipl -> ipl.average);
		this.sort(csvRuns,iplComparator.thenComparing(ipl -> ipl.strikeRate).reversed());
		String sort = new Gson().toJson(csvRuns);
		return sort;
	}
	/**
	 * UC6_Return player who hit max runs and have highest strike rate
	 * @return
	 */
	public String bestAvgWithMaxRuns() {
		Comparator<IPLMostRuns> iplComparator = Comparator.comparing(ipl -> ipl.runs);
		this.sort(csvRuns,iplComparator.thenComparing(ipl -> ipl.average).reversed());
		String sort = new Gson().toJson(csvRuns);
		return sort;
	}
	
	private <E> void sortForBowling(List<IPLMostWickets> csvList, Comparator<IPLMostWickets> iplCSVComparator) {
		for (int i = 0; i < csvList.size(); i++) {
			for (int j = 0; j < csvList.size() - i - 1; j++) {
				IPLMostWickets player1 = csvList.get(j);
				IPLMostWickets player2 = csvList.get(j + 1);
				if (iplCSVComparator.compare(player1, player2) > 0 && (player1.wickets != 0 && player2.wickets != 0)) {
					csvList.set(j, player2);
					csvList.set(j + 1, player1);
				}
			}
		}
	}
	/**
	 * UC7_Return player with top bowling avg
	 * @return
	 */
	public String getSortedOnBowlingAvg() {
		Comparator<IPLMostWickets> iplCSVComparator = Comparator.comparing(entry -> entry.avg);
		this.sortForBowling(csvWickets, iplCSVComparator);
		String sorted = new Gson().toJson(csvWickets);
		return sorted;
	}
	/**
	 * UC8_Return player with top strike rate on bowling
	 * @return
	 */
	public String getSortedOnStrikeRate() {
		Comparator<IPLMostWickets> iplCSVComparator = Comparator.comparing(entry -> entry.strikeRate);
		this.sortForBowling(csvWickets, iplCSVComparator);
		String sorted = new Gson().toJson(csvWickets);
		return sorted;
	}
	/**
	 * UC9_Return player with best economy
	 * @return
	 */
	public String getSortedOnEconomy() {
		Comparator<IPLMostWickets> iplCSVComparator = Comparator.comparing(entry -> entry.economy);
		this.sort(csvWickets, iplCSVComparator);
		String sorted = new Gson().toJson(csvWickets);
		return sorted;
	}
	/**
	 * UC10_Player with best strike with  5w and 4w
	 * @return
	 */
	private Double calculateStrikeRateWith4w5w(IPLMostWickets player) {
		double numOfWicketsWith4w5w = player.fourWickets * 4 + player.fiveWickets * 5;
		if (numOfWicketsWith4w5w == 0)
			return Double.MAX_VALUE;
		int numOfBalls = (int) player.overs;
		numOfBalls = numOfBalls * 6 + (int) ((player.overs - numOfBalls) * 10);
		return numOfBalls / numOfWicketsWith4w5w;
	}
	public String getPlayerWithBestStrikeRateWith4w5w() {
		IPLMostWickets topEconomyPlayer = csvWickets.stream()
				.min((x, y) -> Double.compare(calculateStrikeRateWith4w5w(x), calculateStrikeRateWith4w5w(y))).get();
		return topEconomyPlayer.player;
	}
	/**
	 * UC11_Player with top bowling avg and max strike rate
	 * @return
	 */
	public String getSortedOnBowlingAvgAndStrikeRate() {
		Comparator<IPLMostWickets> iplCSVComparator = Comparator.comparing(entry -> entry.avg);
		this.sortForBowling(csvWickets, iplCSVComparator.thenComparing(entry -> entry.strikeRate));
		String sorted = new Gson().toJson(csvWickets);
		return sorted;
	}
}


