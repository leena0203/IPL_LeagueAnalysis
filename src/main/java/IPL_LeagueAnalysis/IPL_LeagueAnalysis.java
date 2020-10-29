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
	private<E> void sort(List<E> list, Comparator<E> censusComparator) {
		for (int i =0; i < list.size(); i++) {
			for(int j =0; j < list.size() - i - 1; j++) {
				E census1 = list.get(j);
				E census2 = list.get(j+1);
				if (censusComparator.compare(census1, census2) < 0) {
					list.set(j, census2);
					list.set(j + 1, census1);
				}
			}
		}
	}
	/**
	 * UC1_Return Top Batting Avg.
	 * @return
	 */
	public String getTopBatting() {
		Comparator<IPLMostRuns> censusComparator = Comparator.comparing(census -> census.average);
		this.sort(csvRuns,censusComparator);
		String sortedAvg = new Gson().toJson(csvRuns);
		return sortedAvg;
	}
	/**
	 * UC2_Return Highest Strike rate
	 * @return
	 */
	public String getTopStrike() {
		Comparator<IPLMostRuns> censusComparator = Comparator.comparing(census -> census.strikeRate);
		this.sort(csvRuns,censusComparator);
		String sortedStrikeRate = new Gson().toJson(csvRuns);
		return sortedStrikeRate;
	}
}

