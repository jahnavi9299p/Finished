package othello;

public class Coin {
    Integer row,col;
    Integer getRow()
    {
        return row;
    }
    Integer getCol()
    {
        return col;
    }
    void addRow(int r)
    {
        row=r;
    }
    void addCol(int c)
    {
        col=c;
    }
    Coin()
    {
        
    }
}
