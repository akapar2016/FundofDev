package labs;

/* 
 * Author: Aayush Kapar, akapar2016@my.fit.edu 
 * Course: CSE 1001, Section 01/02, Spring 2017 
 * Project: tictactoe 
 */

import java.util.Scanner;

	public class TicTacToe {


	/*
	 * Prints out full board
	 */
	public static void printBoard(char[][] board, int size){
	    
		for(int i = 0; i < size; i++){
			System.out.print("   ");
			System.out.print(i);
		}
		
		System.out.println();
		
		for(int i = 0; i < size; i++){
			
			System.out.print(i + "  ");
			System.out.print(board[i][0]);
			
			for(int j = 1; j < size; j++){
				System.out.print(" | ");
				System.out.print(board[i][j]);
			}
			System.out.println();
			
			if(i < size-1){
			
				System.out.print("  ---");
				
				for(int j = 0; j < size-1; j++){
					System.out.print("|---");
				}
				System.out.println();
			}
		}
		System.out.println();
	}	
		
	
	/*
	 * changes player every turn
	 */
	public static char changePlayer(char player) {
	    char newTurn='e';
	    if (player == 'o')
	        newTurn='x';
	    if (player == 'x')
	        newTurn='o';
	    return newTurn;
	}
	
	
	/*
	 * checks if input from user is possible on the board:
	 * True - If the game board update is successful.
	 * False - If the either one or both the row and column 
	 * 			values given to the method are NOT valid 
	 * 			OR if the board location board[row][col] is occupied. 
	 */
	public static boolean updateBoard(char[][] board, int size, int row, int col, char player){
		if( (row>=size || row<0) && (col>=size || col <0) ){
			System.out.println("Both row and column indices are out of bounds.");
	         return false;	
		}else if( row>=size || row<0 ){
			System.out.println("Row index is out of bounds.");	
	        return false;
		}else if( col>=size || col <0 ){
			System.out.println("Column index is out of bounds.");
	         return false;		
		}else if(board[row][col]=='x' || board[row][col]=='o'){
			System.out.println("The board position is occupied.");
	         return false;	
		}else{
			return true;
		}
	}
	
	
	/*
	 * Method called out by checkWinner method 
	 * Checks if everything in array are the equal
	 */
	public static boolean allEqual(char[] array){
        for(int i=1; i<array.length; i++){
            if(array[0] != array[i]){
                return false;
            }
        }
        return true;
    }
	
	
	/*
	 * Checks who the winner is and returns a char:
	 * 'x' - if player x won the game.
	 * 'o' - if player o won the game.
	 * 'd' - if the game ended in a draw.
	 * 'n' - if the game is not over.
	 */
	public static char checkWinner(char[][] board, int size){
		char[] array = new char[size];
		
		//put each column into an array and test to see if they are all equal
		for(int col = 0; col < size; col++){
			for(int row = 0; row< size; row++){
				array [row] = board [row][col];
			}
			if(allEqual(array) == true && array[0] != ' '){
				return array[0];
			}	
		}
		
		//put each row into an array and test to see if they are all equal
		for(int row = 0; row < size; row++){
			for(int col = 0; col< size; col++){
				array [col] = board [row][col];
			}
			if(allEqual(array) == true && array[0] != ' '){
				return array[0];
			}	
		}
		// put first diagonal into array and test to see if they are all equal
		for(int i = 0; i < size; i++){
			array[i] = board[i][i];
		}
		if(allEqual(array) == true && array[0] != ' '){
			return array[0];
		}	
		
		// put second diagonal into array and test to see if they are all equal
		for(int i = 0,  j = size-1 ; i< size;i++,j--){
			array[i] = board[i][j];
		}
		if(allEqual(array) == true && array[0] != ' '){
			return array[0];
		}	
		
		// test to see if all boxes have been filled
		for (int i = 0; i < size; i++){
			 for (int j=0; j < size; j++){
				 if(board [i][j]==' ')
					 return 'n';
			 }
		 }
		return 'd'; // returns if all boxes have been filled and no winner
	}	
	

	public static void main(String[] args) {
		char player='o'; //who is the current player
	    int row; // row position in board
	    int col; // colum position in board
	    int bSize = 0;
	    
	    //check if there are any argument inputs from user
	    switch (args.length){
	    	case 0: 
	    		bSize = 3; //no arguments, default size = 3
	    		break;
	    	case 1: 
	    		if(Integer.parseInt(args[0])>= 3 && Integer.parseInt(args[0]) <=9){
	    			bSize = Integer.parseInt(args[0]);
	    		}else{
	    			//if input is not between 3 and 9
	    			System.out.println("Input not between 3 and 9, Input invalid");
	    			return;
	    		}
	    		
	    		break;
	    }
	    
	    Scanner k = new Scanner (System.in);
	    
	    //assigning board
	    char [][] board = new char[bSize][bSize]; 
	    for (int i = 0; i < bSize; i++)
	        for (int j=0; j < bSize; j++)
	            board [i][j]= ' ';

	    while(checkWinner(board, bSize) == 'n'){   
	    	
	    	printBoard(board, bSize);//prints out board
	    	
	    	player = changePlayer(player);//changes player after every turn
	    	
	    	//gets input from player
			System.out.println("Player : " + player);
			System.out.println("Enter a space separated row and column numbers:");
			row = k.nextInt();
			col = k.nextInt();
			
			//checks if this postion is valid, if not, terminates rest of loop and restarts
			if(updateBoard(board, bSize, row, col, player) == false){
				player = changePlayer(player);
				continue;
			}
			
			board[row][col] = player;
	
		}
	    
	    printBoard(board,bSize);//prints out board
	    
	    //check the winner or tie
	    if(checkWinner(board, bSize) == 'x'){
	    	System.out.println("Player x won!");
	    	return;
	    }
	    if(checkWinner(board, bSize) == 'o'){
	    	System.out.println("Player o won!");
	    	return;
	    }
	    if(checkWinner(board, bSize) == 'd'){
	    	System.out.println("The game ended in a draw.");
	    	return;
	    }

	}
}
