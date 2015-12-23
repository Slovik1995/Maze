package maze;



import java.awt.Color;
import java.awt.GridLayout;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class DrawMaze{
	String droga="";
	static int direction=0;
	static int X=0,Y=0, count=0;
	public void draw(int wys, int szer, String plansza) throws IOException{
		int index=0;
				
    char[][] tablica = new char[wys][szer];
	for(int i=0; i<wys; i++)
		for(int j=0; j<szer; j++){
			tablica[i][j]=plansza.charAt(index);
			if(tablica[i][j]=='E'){
				X=j;
				Y=i;
			}
			index++;
		}
	
	
	Tile[][] tiles = new Tile[wys][szer];	
	JFrame frame = new JFrame();
	frame.setLayout(new GridLayout(wys, szer));
	for(int i=0; i<wys; i++)
		for(int j=0; j<szer; j++){
			tiles[i][j]=new Tile();
			tiles[i][j].settile(tablica[i][j]);
			
	        frame.add(tiles[i][j]);
	}
	
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.setSize(1370, 730);
	frame.setVisible(true);

	File file = new File("C:\\Users\\Jack\\Desktop\\labirynt_droga.txt");
	FileReader r = new FileReader(file);
	BufferedReader b = new BufferedReader(r);
	String line="";
	while((line=b.readLine())!=null){
		if(line.equals("GÓRA"))
			droga+=1;
		else if(line.equals("PRAWO"))
			droga+=2;
		else if(line.equals("DÓ£"))
			droga+=3;
		else if(line.equals("LEWO"))
			droga+=4;
		else if(line.equals("PROSTO"))
			droga+=5;
		
	}

	StringBuilder bui = new StringBuilder();
	for(int i=0; i<droga.length(); i++)
	{
		if(droga.charAt(i)!='5')
			bui.append(droga.charAt(i));
		else bui.append(bui.charAt(bui.length()-1));
	}
	droga=bui.toString();
	
	
	Thread t = new Thread(){
			public void run(){
				while(count!=droga.length()){
					if(tablica[Y][X]=='X') break;
				if(droga.charAt(count)=='1'){
					direction=1;
					tiles[Y][X].settile('r');
					Y=Y-1;
					if (((X>0)&&(tablica[Y][X-1]=='0'))||((X<szer-1)&&(tablica[Y][X+1]=='0'))||((X>0)&&(tablica[Y][X-1]=='X'))||((X<szer-1)&&(tablica[Y][X+1]=='X')))
						count++;
				}
				else if(droga.charAt(count)=='2'){
					direction=2;
					tiles[Y][X].settile('r');
					X=X+1;
					if (((Y<wys-1)&&(tablica[Y+1][X]=='0'))||((Y>0)&&(tablica[Y-1][X]=='0'))||((Y<wys-1)&&(tablica[Y+1][X]=='X'))||((Y>0)&&(tablica[Y-1][X]=='X')))
						count++;
				}
				else if(droga.charAt(count)=='3'){
					direction=3;
					tiles[Y][X].settile('r');
					Y=Y+1;
					if (((X>0)&&(tablica[Y][X-1]=='0'))||((X<szer-1)&&(tablica[Y][X+1]=='0'))||((X>0)&&(tablica[Y][X-1]=='X'))||((X<szer-1)&&(tablica[Y][X+1]=='X')))
						count++;
				}
				else if(droga.charAt(count)=='4'){
					direction=4;
					tiles[Y][X].settile('r');
					X=X-1;
					if (((Y<wys-1)&&(tablica[Y+1][X]=='0'))||((Y>0)&&(tablica[Y-1][X]=='0'))||((Y<wys-1)&&(tablica[Y+1][X]=='X'))||((Y<wys-1)&&(tablica[Y-1][X]=='X')))
						count++;
				}

				
				try {
					sleep(70);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				}
			}
		};
		t.start();
	}

}

class Tile extends JButton{
	public void settile(char c){
		if(c=='#')
			this.setBackground(Color.DARK_GRAY);
		else if(c=='0')
			this.setBackground(Color.WHITE);
		else if(c=='X')
			this.setBackground(Color.green);
		else this.setBackground(Color.red);
	
	
}
}