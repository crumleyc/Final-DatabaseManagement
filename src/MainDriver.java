import java.util.Scanner;

public class MainDriver {

	public static void main(String[] args) {
		Scanner keyboard = new Scanner(System.in);
		char command = 0;
		String playerID1 = "";
		String playerID2 = "";
		String firstnName = "";
		String lastName = "";
		String teamName = "";
		String teamYear = "";
		DBHelper dao = new DBHelper();

		while (command != 'q') {
			System.out.println("Use one of the following commands to execute a predefined query or to quit the program: ");
			System.out.println("(a) Find player's best season and compare BA to historical average");
			System.out.println("(b) Show the top 10 average homerun stats for players above and below average weight.");
			System.out.println("(c) World Series analysis");
			System.out.println("(d) MVP analysis");
			System.out.println("(e) Get previous annual salaries for a player");
			System.out.println("(s) Search for player's playerID by last name.");
			System.out.println("(t) Search based on team name and year");
			System.out.println("(q) Quit the program");
			System.out.print("\nEnter command here: ");
			command = keyboard.next().charAt(0);

			// Process user input
			switch (command)
			{
				case 'a':
				  System.out.print("Enter a playerID: ");
					playerID1 = keyboard.next();
					dao.getHistoricalAvgComparison(playerID1);
					break;
				case 'b':
					dao.getHomerunWeightAnalysis();
					break;
				case 'c':
					dao.getWorldSeriesAnalysis();
					break;
				case 'd':
					dao.getMVPAnalysis();
					break;
				case 'e':
					System.out.print("Enter a playerID: ");
					playerID1 = keyboard.next();
					System.out.println("stored string");
					dao.getPlayerSalaryStats(playerID1);
					break;
				case 's':
					System.out.println("Enter player's last name: ");
					lastName = keyboard.next();
					dao.getPlayerID(lastName);
					break;
				case 't':
					System.out.print("Enter a team name: ");
					teamName = keyboard.next();
					System.out.print("Enter a year: ");
					teamYear = keyboard.next();
					dao.getTeamInfo(teamName, teamYear);
					break;
				case 'q':
					System.out.println("Exiting program...");
					keyboard.close();
					break;

				default:
					System.out.println(command + " is an invalid command.");
			}

		}
	}

}
