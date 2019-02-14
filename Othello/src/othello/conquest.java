package othello;

import java.util.ArrayList;

public class conquest {
     ArrayList<Coin> coins=new ArrayList<Coin>();
    conquest()
    {
    }
    void addCoin(int x,int y)
    {
        Coin c=new Coin();
        c.addCol(y);
        c.addRow(x);
    }
    void clearCoins()
    {
        coins.clear();
    }
    boolean isEmpty()
    {
        return coins.isEmpty();
    }
}
