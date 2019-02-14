package othello;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
/*To Do:
  *Code for the loony peice on the board-.i.e.if the user selects a random looly btn it is not 
   considered to be valid and the turn must remain same.:Done
  *Code for the finish of a game.i.e. even if the all the cells are not occupied the game might be over and declare the winner
  *May be we can use a count variable
  *Code to alert in place of clicking an already existing coin. : Done
*/

class Board extends JPanel{
    static Tile s[][] = new Tile[8][8];
    static String player[][]=new String[8][8];
    static String start="W";
    static int coincnt=59;
    Board()
    {   super(new GridLayout(8,8));
        for(int i=0;i<8;i++)
            for(int j=0;j<8;j++)
        {   
            s[i][j]=new Tile(i,j);
            s[i][j].setBackground(Color.green);
            this.add(s[i][j]);
            player[i][j]="";
        } 
        s[3][3].setBackground(Color.BLACK);player[3][3]="B";
        s[4][4].setBackground(Color.BLACK);player[4][4]="B";
        s[3][4].setBackground(Color.WHITE);player[3][4]="W";
        s[4][3].setBackground(Color.WHITE);player[4][3]="W";
    }
    private static class Tile extends JButton
    {  Integer m[][]=new Integer[][]{{0,-1},{0,+1},{-1,0},{+1,0},{-1,-1},{-1,+1},{+1,+1},{+1,-1}};
       ArrayList<Coin> coins=new ArrayList<Coin>();
       int validcnt=0;
       public Tile(int x,int y) {
       String labele= "btn".concat(""+x+y);
       JLabel label=new JLabel(labele);
       super.add(label);
       this.addActionListener(new ActionListener()
                {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                    
                    if(player[x][y].equals("")&&(coincnt!=0))
                    { System.out.println("player is "+start);
                      System.out.println(" btn "+x+y+" is pressed");
                      plays(x,y);}
                    else if(!player[x][y].equals(""))
                        JOptionPane.showMessageDialog(null,"Coin already existing");
                    }
                }
        );
        }
        void end()
        {   int bcnt=0,wcnt=0;
            for(int i=0;i<8;i++)
                for(int j=0;j<8;j++)
                {
                    if("B".equals(player[i][j])){bcnt++;} 
                    if("W".equals(player[i][j])){wcnt++;} 
                }
            if(bcnt!=wcnt)
                JOptionPane.showMessageDialog(null," Winner is "+((bcnt<wcnt?wcnt:bcnt))+" ");
            else JOptionPane.showMessageDialog(null," It is a tie W is "+wcnt+", B is "+bcnt);
        }
        void changePlayer()
        {
            if(start.equals("W")) start="B"; 
            else start="W";
            JOptionPane.showMessageDialog(null," Now "+start+" turn ");
            return;
        }
        void plays(int x,int y)
        {   //calculate the moves
            for(int i=0;i<8;i++)
            { moves(x,y,m[i][0],m[i][1]);
            }
            System.out.println("cnt="+validcnt);
            if(validcnt>=8)
                        JOptionPane.showMessageDialog(null,"Donot choose a loony piece"+validcnt);
            else{ // set the color of the tiles
                    if(start.equals("W")){ player[x][y]="W"; this.setBackground(Color.white);} 
                    else{ player[x][y]="B"; this.setBackground(Color.black);}
                    coincnt--;
            //then change the dplayer's turn
                    changePlayer();
                    if(coincnt==0)
                    {JOptionPane.showMessageDialog(null,"Game ends");
                     end();}
            }
        }
         boolean isLegal(int x,int y)
        {   if(x<0||x>7)return false;
            if(y<0||y>7)return false;
            if(player[x][y].equals(""))return false;
            else return true;
        }    
        void moves(int x,int y,int i,int j)
        {   System.out.println("the directions are"+i+" "+j+" and button is "+x+" "+y);
            x=x+i;
            y=y+j;
            if(isLegal(x,y)&&player[x][y].equals(start))
            {   //Iterator<Coin> it=coins.iterator();
                //this method rums for immediate same coins also but at least the dir should be considered loony
                //therefore if previously array coins is empty then validcnt increments
                if(coins.isEmpty())
                    {validcnt++; return;}
                System.out.println("Checking for same coin");
                for(Coin co : coins){ int r=co.getRow();
                                      int c=co.getCol();
                    System.out.println("coin turned is "+r+" "+c);
                    if(start.equals("W")){ player[r][c]="W"; s[r][c].setBackground(Color.white);} 
                    else{ player[r][c]="B"; s[r][c].setBackground(Color.black);}    
                    System.out.println("\t player[][] is"+player[r][c]);
                }
                //validcnt++;
                coins.clear();
                return;
            }
            if(isLegal(x,y)&&(!player[x][y].equals(start)))//the coins of unselected button are also be checked in the same direction
            {System.out.println("Checking for legal opp coin");
             Coin c=new Coin();
             c.addRow(x);c.addCol(y);
             coins.add(c);
             moves(x,y,i,j);
            }
            else
            {   validcnt++;//validcnt increments when there is no conquest in this direction
                System.out.println("ArrayList is cleared");
                coins.clear();
            }
        }
    }
}
