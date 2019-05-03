import java.sql.*;

public class DBHelper {
	private String jdbcURL;
	private String jdbcUsername;
	private String jdbcPassword;
	ResultSet rs = null;
	Connection conn = null;
	PreparedStatement compareHistoricalAvg = null;
	PreparedStatement getAboveAvgWeightHRs = null;
	PreparedStatement getBelowAvgWeightHRs = null;
	PreparedStatement getMVPAnalysis = null;
	PreparedStatement getWorldSeriesAnalysis = null;
	PreparedStatement getPlayerID = null;
	PreparedStatement getSalaryStats = null;
	PreparedStatement getTeamInfo = null;

	public DBHelper() {
		this.jdbcURL = "jdbc:mysql://localhost:3306/lahmansbaseballdb?serverTimezone=UTC";
		this.jdbcUsername = "root";
		this.jdbcPassword = "TheHannah@96";
	}

	public void getPlayerID(String input){

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);

			getPlayerID = conn.prepareStatement("select playerID, nameFirst, nameLast from people where nameLast like '%" + input + "%';");
			rs = getPlayerID.executeQuery();
			printResultSet(rs);
		} catch (Exception e) {
			System.out.println(e.getClass().getName() +": " + e.getMessage());
		}
		finally {
			try {
				if (getMVPAnalysis != null) {
					getMVPAnalysis.close();
				}
				if (rs != null) {
					rs.close();
				}
				if (conn != null) {
					conn.close();
				}
			}
			catch (SQLException e) {
				System.out.println(e.getClass().getName() +": " + e.getMessage());
			}
		}
	}


	public void getHistoricalAvgComparison(String playerID) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);

			// Initialize prepared statement
			compareHistoricalAvg = conn.prepareStatement("SELECT b.playerID, p.nameFirst, p.nameLast, b.yearID, b.stint, MAX(b.H/b.AB) as playerAvg, b3.hAvg, (MAX(b.H/b.AB) - b3.hAvg) as diff_from_avg " +
					"FROM batting b " +
					"CROSS JOIN (SELECT AVG(b2.battingAverage) as hAvg  FROM ( " +
					"			SELECT (H/AB) as battingAverage FROM batting WHERE AB >= 500) as b2 " +
					") as b3 " +
					"INNER JOIN people p ON b.playerID = p.playerID " +
					"WHERE b.playerID = ? " +
					"GROUP BY b.yearID, b.stint " +
					"ORDER BY MAX(b.H/b.AB) DESC " +
					"LIMIT 1");

			// Bind playerID to compareBattingAvg and execute query
			compareHistoricalAvg.setString(1, playerID);
			rs = compareHistoricalAvg.executeQuery();

			//Print result set to console
			printResultSet(rs);
		}
		catch (ClassNotFoundException e) {
			System.out.println(e.getClass().getName() +": " + e.getMessage());
		}
		catch (SQLException e) {
			System.out.println(e.getClass().getName() +": " + e.getMessage());
		}
		finally {
			try {
				if (compareHistoricalAvg != null) {
					compareHistoricalAvg.close();
				}
				if (rs != null) {
					rs.close();
				}
				if (conn != null) {
					conn.close();
				}
			}
			catch (SQLException e) {
				System.out.println(e.getClass().getName() +": " + e.getMessage());
			}
		}
	}

	public void getHomerunWeightAnalysis() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);

			// Initialize prepared statements
			getAboveAvgWeightHRs = conn.prepareStatement("SELECT b.playerID, p.nameFirst, p.nameLast, AVG(HR), AVG(weight) as weight " +
					"FROM batting b " +
					"INNER JOIN people p ON b.playerID = p.playerID " +
					"WHERE b.playerID IN ( " +
					"	SELECT DISTINCT(playerID) FROM people " +
					"	WHERE weight > (SELECT AVG(weight) FROM people) " +
					") " +
					"GROUP BY b.playerID " +
					"ORDER BY AVG(HR) desc " +
					"LIMIT 10");

			getBelowAvgWeightHRs = conn.prepareStatement("SELECT b.playerID, p.nameFirst, p.nameLast, AVG(HR), AVG(weight) as weight " +
					"FROM batting b " +
					"INNER JOIN people p ON b.playerID = p.playerID " +
					"WHERE b.playerID IN ( " +
					"	SELECT DISTINCT(playerID) FROM people  " +
					"	WHERE weight <= (SELECT AVG(weight) FROM people) " +
					") " +
					"GROUP BY b.playerID " +
					"ORDER BY AVG(HR) desc " +
					"LIMIT 10");

			// Query the top 10 above average weight player's average home runs and print to console.
			rs = getAboveAvgWeightHRs.executeQuery();
			System.out.println("Top 10 average homerun stats of players above average weight.\n");
			printResultSet(rs);

			rs = getBelowAvgWeightHRs.executeQuery();
			System.out.println("Top 10 average homerun stas of players below average weight.\n");
			printResultSet(rs);

		}
		catch (ClassNotFoundException e) {
			System.out.println(e.getClass().getName() +": " + e.getMessage());
		}
		catch (SQLException e) {
			System.out.println(e.getClass().getName() +": " + e.getMessage());
		}
		finally {
			try {
				if (getAboveAvgWeightHRs != null) {
					getAboveAvgWeightHRs.close();
				}
				if (getBelowAvgWeightHRs != null) {
					getBelowAvgWeightHRs.close();
				}
				if (rs != null) {
					rs.close();
				}
				if (conn != null) {
					conn.close();
				}
			}
			catch (SQLException e) {
				System.out.println(e.getClass().getName() +": " + e.getMessage());
			}
		}
	}

	public void getPlayerSalaryStats(String playerID){
		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);

			// Initialize prepared statements
			getSalaryStats = conn.prepareStatement("SELECT playerID, salary, yearID " +
																						"FROM salaries " +
																						"WHERE playerID= ? ;");

			//Grab query resuls of a player's salary and performance stats
			getSalaryStats.setString(1,playerID);
			rs = getSalaryStats.executeQuery();
			printResultSet(rs);
		}
		catch (ClassNotFoundException e) {
			System.out.println(e.getClass().getName() +": " + e.getMessage());
		}
		catch (SQLException e) {
			System.out.println(e.getClass().getName() +": " + e.getMessage());
		}
		finally {
			try {
				if (getSalaryStats != null) {
					getSalaryStats.close();
				}
				if (rs != null) {
					rs.close();
				}
				if (conn != null) {
					conn.close();
				}
			}
			catch (SQLException e) {
				System.out.println(e.getClass().getName() +": " + e.getMessage());
			}
		}
	}

	public void getWorldSeriesAnalysis(){

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);

			getWorldSeriesAnalysis = conn.prepareStatement("with averages (yearID, BA) " +
			"as (select distinct yearID, avg(H/AB) from teams group by yearID), " +
			"WorldSeries (yearID, teamID, name, franchName, hits, AB) " +
			"as (select yearID, teamID, name, franchName, H, AB from teams T, teamsfranchises F where T.franchID = F.franchID and T.WSWin = 'Y') " +
			"select yearID, teamID, name, franchName, hits, AB, (hits/AB) teamBA, BA from averages natural join WorldSeries where (hits/AB) < BA;"
			);

			rs = getWorldSeriesAnalysis.executeQuery();
			System.out.print("List of World Series winning teams who batted worse than the league average that year.");
			printResultSet(rs);

		} catch (ClassNotFoundException e) {
			System.out.println(e.getClass().getName() +": " + e.getMessage());
		}
		catch (SQLException e) {
			System.out.println(e.getClass().getName() +": " + e.getMessage());
		}
		finally {
			try {
				if (getWorldSeriesAnalysis != null) {
					getWorldSeriesAnalysis.close();
				}
				if (rs != null) {
					rs.close();
				}
				if (conn != null) {
					conn.close();
				}
			}
			catch (SQLException e) {
				System.out.println(e.getClass().getName() +": " + e.getMessage());
			}
		}
	}

	public void getMVPAnalysis(){

	try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		conn = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);

		getMVPAnalysis = conn.prepareStatement("with averagesSecond (yearID, lgBA, lgSlugging) " +
							"as (select distinct yearID, avg(H/AB), avg(((H + (2 * 2B) + (3 * 3B) + (4 * HR))/AB)) from teams group by yearID), " +
							"MVPBatting (playerID, yearID, H, 2B, 3B, HR, AB) " +
							"as (select playerID, yearID, sum(H), sum(2B), sum(3B), sum(HR), sum(AB) from batting B natural join awardsplayers A where awardID = 'Most Valuable Player' group by B.playerID, B.yearID), " +
							"MVPBattingPlus (yearID, nameFirst, nameLast, MVPBA, MVPSlugging) " +
							"as (select yearID, nameFirst, nameLast, (H/AB), ((H + (2 * 2B) + (3 * 3B) + (4 * HR))/AB) from MVPBatting M, people P where P.playerID = M.playerID order by yearID DESC) " +
							"select * from MVPBattingPlus natural join averagesSecond order by yearID DESC;"
		);

		rs = getMVPAnalysis.executeQuery();
		System.out.print("List of the MVP winners of each year and how their batting stats compare to the rest of the league.");
		printResultSet(rs);

	} catch (ClassNotFoundException e) {
		System.out.println(e.getClass().getName() +": " + e.getMessage());
	}
	catch (SQLException e) {
		System.out.println(e.getClass().getName() +": " + e.getMessage());
	}
	finally {
		try {
			if (getMVPAnalysis != null) {
				getMVPAnalysis.close();
			}
			if (rs != null) {
				rs.close();
			}
				if (conn != null) {
					conn.close();
				}
			}
			catch (SQLException e) {
				System.out.println(e.getClass().getName() +": " + e.getMessage());
			}
		}
	}

	public void getTeamInfo(String inputTeam, String inputYear){

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);

			getTeamInfo = conn.prepareStatement("select yearID, T.name, lgID, divID, T.Rank, G, W, L, R, H, HR, SO, FP, DivWin, WCWin, LgWin, WSWin from teams T where name like '%" + inputTeam + "%' and yearID = " + inputYear + ";");
			rs = getTeamInfo.executeQuery();
			printResultSet(rs);
		} catch (Exception e) {
			System.out.println(e.getClass().getName() +": " + e.getMessage());
		}
		finally {
			try {
				if (getMVPAnalysis != null) {
					getTeamInfo.close();
				}
				if (rs != null) {
					rs.close();
				}
				if (conn != null) {
					conn.close();
				}
			}
			catch (SQLException e) {
				System.out.println(e.getClass().getName() +": " + e.getMessage());
			}
		}
	}





	/**
	 * A helper method for printing ResultSets to the console or displaying a message
	 * to indicate an empty Result Set. Uses the free, open source DBTablePrinter.java
	 * redistributable class by Hami Galip Torun to format the ResultSets into an easily readable table.
	 *
	 *
	 * @param rs The result set to print
	 * @throws SQLException
	 */
	private void printResultSet(ResultSet rs) throws SQLException {
		// Check to see if the ResultSet is empty, and print a message if it is.
		// Otherwise, move back to the starting position

		if (rs.next() == false) {
			System.out.println("\nThe query returned an empty result set\n");
		}
		else {
			rs.previous();
			DBTablePrinter.printResultSet(rs);
		}

	}
}
