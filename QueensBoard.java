public class QueensBoard{
  private int[][] board;
  public QueensBoard(int n){
    board = new int[n][n];}
  public  void edit(int r, int c, boolean add){
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

  public int count(){
    int counter = 0;
    for(int i = 0; i < board.length; i++){
      for(int j = 0; j < board.length; j++){
        if(board[i][j] == -1){
          counter ++;}}}
    return counter;}
  public static boolean nQueensH(int r, int c){
    int startOff = 0;
    if(count(-1) == board.length){
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

  public static boolean nQueens(int n){
    if(n == 2 || n == 3){
      return false;}
    board = new int[n][n];
    return nQueensH(0, 0);}
}
