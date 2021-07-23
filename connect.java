public class Connect 
{
	public static char[][] gameArray = new char[6][7]; //game board
	public static boolean computer = false; //true if user is playing against computer
	
	//to initialize the empty display array
	public static void startDisplay()
	{
		//store spaces in the game array as default values
		for(int i = 0; i < gameArray.length; i++)
		{
			for(int j = 0; j < gameArray[i].length; j++)
				gameArray[i][j] = ' ';
		}
	}

	//prints the display array
	public static void printDisplay()
	{
		System.out.println("\n 0 1 2 3 4 5 6");
		System.out.print("|");
		for(int i = 0; i < gameArray.length; i++)
		{
			for(int j = 0; j < gameArray[i].length; j++)
				System.out.print(gameArray[i][j] + "|");
			if(i == gameArray.length - 1)
			{
				System.out.println();
				break;
			}
			System.out.print("\n|");
		}
	}

	//prints main menu
	public static void printMenu()
	{
		System.out.println("............................");
		System.out.println("CONNECT FOUR GAME");
		System.out.println("  (1) Rules");
		System.out.println("  (2) Play against opponent");
		System.out.println("  (3) Play against computer");
		System.out.println("  (4) Exit");
		System.out.println("............................");
		System.out.print("Enter choice number: ");
		Scanner input = new Scanner(System.in);
		int choice = input.nextInt();
		switch(choice)
			{
				case 1: printRules(); break;
				case 2: playOpponent(); break;
				case 3: playComputer(); break;
				case 4: System.out.println("Goodbye!"); System.exit(1); break;
				default: System.out.println("Incorrect Choice Number"); break;
			}
	}
	//prints the game rules
	public static void printRules()
	{
		System.out.println("\nRULES");
		System.out.println("\nConnect Four is a two-player board game in which the players \nalternately drop colored disks into seven-column, six-row \nvertically suspended grid. The objective of the game is to connect four\nsame-colored disks in a row, a column, or a diagonal \nbefore your opponent can do likewise. \nThe program prompts two players to drop a red or yellow disks alternately. \nWhenever a disk is dropped, the program redisplays the board on the console and determines the status of the game (win, draw, or continue).");
		System.out.println("\nPress any key to go back to the menu.");
		Scanner input = new Scanner(System.in);
		char rand = input.next().charAt(0);
		printMenu();
	}

	//prints the game layout for player 1 and stores their move
	public static void startRound1()
	{
		System.out.println("\nPlayer 1's Turn");
		printDisplay();	
		System.out.println("...............");
		System.out.print("Drop a red disk at column (0-6): ");
		if(computer)
		{
			int position = (int)(Math.random() * 7); //choose a random position if playing against computer
			store1(position);
		}
		else
		{
			Scanner input = new Scanner(System.in);
			int position = input.nextInt();
			store1(position);
		}
		
	}

	//prints the game layout for player 2 and stores their move
	public static void startRound2()
	{
		System.out.println("\nPlayer 2's Turn");
		printDisplay();
		System.out.println("...............");
		System.out.print("Drop a yellow disk at column (0-6): ");
		Scanner input = new Scanner(System.in);
		int position = input.nextInt();
		store2(position);
	}

	//game against computer
	public static void playComputer()
	{
		computer = true;
		startDisplay();
		System.out.println("\nPlayer 1 - RED (Computer)");
		System.out.println("Player 2 - YELLOW (You)");
		System.out.println("START...");
		startRound1();
		startRound2();
	}

	//game against another player
	public static void playOpponent()
	{
		computer = false;
		startDisplay(); //sets the display array ready
		System.out.println("\nPlayer 1 - RED");
		System.out.println("Player 2 - YELLOW");
		System.out.println("START...");
		startRound1();
		startRound2();
	}

	//stores player 1's disks in the game array
	public static void store1(int pos1)
	{
		boolean colFull = false;
		boolean wrongPosition = false;
		
		//check if position is valid
		if(pos1 < 0 || pos1 > 6)
			wrongPosition = true;
		
		//store player 1's disk
		if(!wrongPosition)
		{
			for(int i = gameArray.length - 1; i >= 0; i--)
			{
				if(gameArray[i][pos1] == ' ')
				{
					gameArray[i][pos1] = 'R';
					break;
				}
				else if(i == 0 && gameArray[i][pos1] != ' ')
					colFull = true; //the column is full
				else
					continue;
			}
		}

		else
		{
			System.out.println("Please enter a correct position (0-6)");
			startRound1();
		}
		
		if(colFull)
		{
			System.out.println("The column is full. Please choose a different position");
			startRound1();
		}
		
		printDisplay();
	}
	public static void store2(int pos2)
	{
		boolean colFull = false;
		//check if position is valid
		if(pos2 < 0 || pos2 > 6)
		{
			System.out.println("Please enter a correct position (0-6)");
			startRound2();
		}
		//store player 2's disk
		for(int i = gameArray.length - 1; i >= 0; i--)
		{
			if(gameArray[i][pos2] == ' ')
			{
				gameArray[i][pos2] = 'Y';
				break;
			}
			else if(i == 0 && gameArray[i][pos2] != ' ')
				colFull = true; //the column is full
			else
				continue;
		}
		//check if the column is full
		if(colFull)
		{
			System.out.println("The column is full. Please choose a different position");
			startRound2();
		}	
		printDisplay();
		checkStatus();
	}

	//checks whether a player has won, drawn or to continue the game
	public static void checkStatus()
	{
		//check if any player has won
		boolean player1Wins = false;
		boolean player2Wins = false;
		boolean winnerExists = false;
		
		 //check columns
		 String temp = "";
		for(int j = 0; j < gameArray[0].length; j++)
		{
			for(int i = 0; i < gameArray.length; i++)
			{
				temp += gameArray[i][j];
			}
			if(temp.contains("YYYY"))
			{
				player2Wins = true;
				winnerExists = true;
			}
			if(temp.contains("RRRR"))
			{
				winnerExists = true;
				player1Wins = true;
			}
			temp = "";
		}
		temp = "";

		//check rows
		for(int i = 0; i < gameArray.length; i++)
		{
			for(int j = 0; j < gameArray[i].length; j++)
			{
				temp += gameArray[i][j];
			}
			if(temp.contains("YYYY"))
			{
				player2Wins = true;
				winnerExists = true;
			}
			if(temp.contains("RRRR"))
			{
				winnerExists = true;
				player1Wins = true;
			}
			temp = "";
		}
		temp = "";

		//check right diagonal
		/*
		Right Diagonal indexes to check(row column)

	check:	 5    4    0    1    2    3 
			2 0  1 0  0 0  0 1  0 2  0 3 
			3 1  2 1  1 1  1 2  1 3  1 4
			4 2  3 2  2 2  2 3  2 4  2 5
			5 3  4 3  3 3  3 4  3 5  3 6
		    	 5 4  4 4  4 5  4 6
		         	  5 5  5 6
	check diagonal 0, 1, 2, 3, 4 then 5 in that order for a winner
		*/
		int check = 0;
		int m = 0, l = 0;
		int stopPoint = gameArray.length;
		while(check != 32)//32 is a sentinel value
		{
			while(m < stopPoint)
			{
				temp += gameArray[m][m + l];
				m++;
			}
			if(temp.contains("YYYY"))
			{
				player2Wins = true;
				winnerExists = true;
			}
			if(temp.contains("RRRR"))
			{
				winnerExists = true;
				player1Wins = true;
			}
			check++;
			l++; 
			m = 0;
			temp = "";

			switch (check)
			{
				case 2: stopPoint = gameArray.length - 1; break;
				case 3: stopPoint = gameArray.length - 2; break;
				case 4: stopPoint = gameArray.length; m = 1; l = -1; break;
				case 5: stopPoint = gameArray.length; m = 2; l = -2; break;
				case 6: check = 32; break; //end loop
			}
		}
		temp = "";
		
		//check left diagonal 
		/*
		check:	 5    4    0    1    2    3 
				0 3  0 4  0 5  0 6  1 6  2 6
				1 2  1 3  1 4  1 5  2 5  3 5
				2 1  2 2  2 3  2 4  3 4  4 4
				3 0  3 1  3 2  3 3  4 3  5 3
		    		 4 0  4 1  4 2  5 2
		         		  5 0  5 1
		check diagonal 0, 1, 2, 3, 4 then 5 in that order for a winner

		*/
		check = 0;
		m = 0;
		l = gameArray.length - 1;
		stopPoint = gameArray.length;
		while(check != 32)//32 is a sentinel value
		{
			while(m < stopPoint)
			{
				temp += gameArray[m][l - m];
				m++;
			}
			if(temp.contains("YYYY"))
			{
				player2Wins = true;
				winnerExists = true;
			}
			if(temp.contains("RRRR"))
			{
				winnerExists = true;
				player1Wins = true;
			}
			check++;
			l++; 
			m = 0;
			temp = "";

			switch (check)
			{
				case 2: stopPoint = gameArray.length; m = 1; break;
				case 3: stopPoint = gameArray.length; m = 2; break;
				case 4: stopPoint = gameArray.length - 1; m = 0; l = 4; break;
				case 5: stopPoint = gameArray.length - 2; m = 0; l = 3; break;
				case 6: check = 32; break; //end loop
			}
		}
		temp = "";
		
		//check if there is a draw (you can draw if both players win or if the board is full and no one won)
		boolean drawExists = false;
		if(player1Wins && player2Wins)
		{
			drawExists = true;
			System.out.println("\nIt's a draw!");
			printMenu();
		}
		boolean isFull = true; //check if the board is full
		for(int i = 0; i < gameArray[0].length; i++)
		{
			if(gameArray[0][i] == ' ')
			{
				isFull = false;
				break;
			}
		}

		if(!winnerExists && isFull)
		{
			drawExists = true;
			System.out.println("\nIt's a draw!");
			printMenu();
		}

		if(player1Wins)
		{
			System.out.println("\nPlayer 1 (RED) wins!");
			printMenu();
		}
		else if(player2Wins)
		{
			System.out.println("\nPlayer 2 (YELLOW) wins!");
			printMenu();
		}

		//game continues if there is no winner or draw
		if(!winnerExists && !drawExists)
		{
			startRound1();
			startRound2();
		}	
	}
	
	public static void main(String[] args)
	{
		printMenu();
	}
}