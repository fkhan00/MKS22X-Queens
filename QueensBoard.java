public class QueensBoard{
  private int[][] board;
  private int counter;
  public QueensBoard(int n){
    board = new int[n][n];
    counter = 0;}
  public void edit(int r, int c, boolean add){
    int output = -1;
    if(! add && board[r][c] != -1){
      return;}
    if(add){
      output = 1;}
    for(int i = 0; i < board.length; i++){
      if(board[i][c] != -1 || board[r][i] != -1){
        board[i][c] += output;
        board[r][i] += output;}}
    for(int i = 0; i + c < board.length && r + i < board.length; i++){
      if(board[r + i][c + i] != -1){
        board[r + i][c + i] += output;}}
    for(int i = 0; c - i >= 0 && r - i >= 0; i++){
      if(board[r - i][c - i] != -1){
        board[r-i][c-i] += output;}}
    for(int i = 0; r + i < board.length && c - i >= 0; i++){
      if(board[r + i][c - i] != -1){
        board[r + i][c - i] += output;}}
    for(int i = 0; c + i < board.length && r - i >= 0; i++){
      if(board[r - i][c + i] != -1){
        board[r - i][c + i] += output;}}
    board[r][c] = 0;
    if(add){
      board[r][c] = -1;}
      }
  public int countSolutions(){
    for(int i = 0; i < board.length; i++){
      for(int j = 0; j < board.length; j++){
        if(board[i][j] != 0){
          throw new IllegalArgumentException("please enter an empty board and try again");}}}
    countSolutionsH(0);
    return counter;}
  public void countSolutionsH(int c){
    // if you reached end of board add one
    if(c == board.length){
      counter ++;
    return;}
    for(int i = 0; i < board.length; i++){
      // if free space add a queen
      if(board[i][c] == 0){
        edit(i, c, true);
        //move to next column
        countSolutionsH(c + 1);
        // backtrack and try row underneath
        edit(i, c, false);}}
  }

  public boolean solveH(int r, int c, int placed){
    // records queen position of previous column
    int startOff = 0;
    if(placed == board.length){
      // if all queens are on board return true
      return true;}
    if(c >= board.length){
      // if end is reached an no queens
      return false;}
    if(r >= board.length){
      // if you can't place a queen on this column
      while(startOff < board.length && board[startOff][c - 1] != -1){
        // find the position of the queen in the last column
        startOff ++;}
        // remove that queen
      edit(startOff, c - 1, false);
      // try putting a queen directly below previous position
      return solveH(startOff + 1, c - 1, placed - 1);}
    if(board[r][c] == 0){
      // if no intersections in queens' paths
      edit(r, c, true);
      // add queen and move to next column
      return solveH(0, c  + 1, placed + 1);}
      // else try adding queen in next row
    return solveH(r + 1, c, placed);}

  public boolean solve(){
    return solveH(0, 0, 0);}

  public String toString(){
    String output = "";
    for(int i = 0; i < board.length; i++){
      for(int j = 0; j < board.length; j++){
        if(board[i][j] == -1){
          output += " Q ";
        }
        else{
          output += " _ ";
        }
      }
      output += "\n";
    }
    return output;
  }
}
