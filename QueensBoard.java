public class QueensBoard{
  private static int[][] board;
  public QueensBoard(int n){
    board = new int[n][n];}
  public  static void edit(int r, int c, boolean add){
    int output = -1;
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

  public static int count(){
    int counter = 0;
    for(int i = 0; i < board.length; i++){
      for(int j = 0; j < board.length; j++){
        if(board[i][j] == -1){
          counter ++;}}}
    return counter;}
  public static int countSums(){
    return countSumsH(0, 0, 0);
  }
  public static int countSumsH(int r, int c, int counter){
    int startOff = 0;
    if(count() == board.length){
      System.out.println(printArray());
      while(startOff < board.length && board[startOff][c - 1] != -1){
        startOff ++;}
      edit(startOff, c - 1, false);
      return countSumsH(startOff + 1, c - 1, counter += 1);
    }
    if(board[board.length - 1][0] == -1){
      return counter;}
    if(r >= board.length){
      while(startOff < board.length && board[startOff][c - 1] != -1){
        startOff ++;}
      edit(startOff, c - 1, false);
      return countSumsH(startOff + 1, c - 1, counter);}
    if(board[r][c] == 0){
      edit(r, c, true);
      return countSumsH(0, c  + 1, counter);}
    return countSumsH(r + 1, c, counter);}

  public static boolean nQueensH(int r, int c){
    int startOff = 0;
    if(count() == board.length){
      return true;}
    if(r >= board.length && c >= board.length){
      return false;}
    if(r >= board.length){
      while(startOff < board.length && board[startOff][c - 1] != -1){
        startOff ++;}
      edit(startOff, c - 1, false);
      return nQueensH(startOff + 1, c - 1);}
    if(board[r][c] == 0){
      edit(r, c, true);
      return nQueensH(0, c  + 1);}
    return nQueensH(r + 1, c);}

  public static  boolean nQueens(){
    if(board.length == 2 || board.length == 3){
      return false;}
    return nQueensH(0, 0);}
  public static String printArray(){
    String output = "";
    for(int i = 0; i < board.length; i++){
      for(int j = 0; j < board.length; j++){
        output += " " + board[i][j];
      }
      output += "\n";
    }
    return output;
  }
  public static void main(String[] args){
    QueensBoard test1 = new QueensBoard(5);
    test1.countSumsH(0, 0, 0);
    System.out.println(printArray());
  }
}
