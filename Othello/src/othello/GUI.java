package othello;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
public final class GUI {
    JFrame jf =new JFrame();
    Board bb= new Board();
    public static void main(String args[])
    {  
        EventQueue.invokeLater(new Runnable(){
            @Override
            public void run()
            { try{
                 GUI win = new GUI();
                 win.jf.show(true);
                 JOptionPane.showMessageDialog(null,"Rules are....W's turn first");
                 }
             catch(Exception e){
                 System.out.println("xxxx"+e);
             }
            }
            });
    }
    GUI()
    {   jf.setSize(600,600);
        jf.add(bb);
    }
  
    
}
