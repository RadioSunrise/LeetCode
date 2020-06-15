// 第一次做本题的差方法，用了二维数组存储，在每次判断，时间复杂度高+空间复杂度高
class Solution {
    public String tictactoe(int[][] moves) {
        int[][] board = new int[3][3];
        int finish_flag = 0;
        int current_status = 0;
        int step = 0;
        int player = 1;
        String winner = "0";
        for(step = 0; step < moves.length; step++)
        {
        	if(step % 2 == 0)
        	{
        		player = 1;
                board[moves[step][0]][moves[step][1]] = 1;
        	}
        	else
        	{
        		player = 10;
        		board[moves[step][0]][moves[step][1]] = 10;
        	}
            current_status = check_win(board, player);
            if(current_status == 1)
            {
                if(step % 2 == 0)
                {
                    winner = "A";
                }
                else
                {
                    winner = "B";
                }
                finish_flag = 1;
                break;
            }
        }
        if(step < 9 && finish_flag == 0)
        {
            winner = "Pending";
        }
        if(step == 9 && finish_flag == 0)
        {
            winner = "Draw";
        }
        return winner;
    }
	public static int check_win(int[][] board, int player)
	{
		int win_flag = 0;
		// row detect
		for(int row = 0; row <3; row++)
		{
			if(board[row][0]+board[row][1]+board[row][2] == 3 * player)
			{
				win_flag = 1;
			}
		}
		//column detect
		for(int col = 0; col < 3; col++)
		{
			if(board[0][col]+board[1][col]+board[2][col] == 3 * player)
			{
				win_flag = 1;
			}
		}
		//diag detect
		{
			if(board[0][0]+board[1][1]+board[2][2] == 3 * player || board[0][2]+board[1][1]+board[2][0] == 3 * player)
			{
				win_flag = 1;
			}
		}
		return win_flag;
	}
}

//待增加好的解法
