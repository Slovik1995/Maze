package maze;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Labirynt {
	
	static boolean wypisane=false;
	static int i=0;
	static int kierunekStartowy=0;
	int index=0;
	static int[] tablica = new int[10000];
	static int kier=0;
	static int dlugoscwiersza=0,wysokosc=0;
	
	String s="";
	static int dlugoscplanszy=0;
	static String plansza="";
	
	public void wczytaj() throws IOException{
	File file;
	FileReader f;
	file = new File("C:\\Users\\Jack\\Desktop\\labirynt.txt");
	f = new FileReader(file);
	BufferedReader r = new BufferedReader(f);
	while((s=r.readLine())!=null){
		plansza+=s;
		dlugoscwiersza=s.length();
		dlugoscplanszy=plansza.length();
	}
	wysokosc=dlugoscplanszy/dlugoscwiersza;
	r.close();
	f.close();
	}
	
	public void wpisz_do_pliku(String s) throws IOException{
		File file;
		file = new File("C:\\Users\\Jack\\Desktop\\labirynt_droga.txt");
		FileWriter f;
		f = new FileWriter(file);
		BufferedWriter r = new BufferedWriter(f);
		for(String str : s.split("\n")){
			r.write(str);
			r.write(System.getProperty( "line.separator" ));
		}
		r.flush();
		r.close();
		f.close();
	}
	
	public void znajdzpoczatek(){
		while(plansza.charAt(i)!='E') i++;	
	}
	
	public void szukaj(int kierunek, int indx){		
		int i=indx;
		int kier=kierunek;
		boolean entrance=false;
		if(kier==1) tablica[index]=1;
		else if(kier==2) tablica[index]=2;
		else if(kier==3) tablica[index]=3;
		else if(kier==4) tablica[index]=4;

		index++;
		if((plansza.charAt(i)=='X')) {
			wypisz_droge();
		}
		while((plansza.charAt(i)!='X')&&(plansza.charAt(i)!='#')){
			
			if((kier!=1)&&(kier!=3)&&(i>=dlugoscwiersza)){
				if((plansza.charAt(i-dlugoscwiersza)=='0')||(plansza.charAt(i-dlugoscwiersza)=='X')){
					entrance=true;
					int y=(i-dlugoscwiersza);
					szukaj(1, y);
				}
			}
			if((kier!=3)&&(kier!=1)&&(i<dlugoscplanszy-dlugoscwiersza)){
				if((plansza.charAt(i+dlugoscwiersza)=='0')||(plansza.charAt(i+dlugoscwiersza)=='X')){
					entrance=true;
					int y=(i+dlugoscwiersza);
					szukaj(3, y);
				}
			}
			if((kier!=4)&&(kier!=2)&&(((i+1)%dlugoscwiersza)!=0)&&(i<dlugoscplanszy-1)){
				if((plansza.charAt(i+1)=='0')||(plansza.charAt(i+1)=='X')){
					entrance=true;
					int y=i+1;
					szukaj(2, y);
				}
			}
			if((kier!=2)&&(kier!=4)&&((i%dlugoscwiersza)!=0)){
				if((plansza.charAt(i-1)=='0')||(plansza.charAt(i-1)=='X')){
					entrance=true;
					int y=i-1;
					szukaj(4, y);
				}
			}
			if(kier==1) {
				
				if(i>=dlugoscwiersza){
					i=i-dlugoscwiersza;
					if(entrance==true){
						if(plansza.charAt(i)!='#'){
							tablica[index]=5;
							index++;
						}
						entrance=false;
					}
				}
				else break;
			}
			
			if(kier==3) {
				if(i<dlugoscplanszy-dlugoscwiersza){
					i=i+dlugoscwiersza;
					if(entrance==true){
						if(plansza.charAt(i)!='#'){
							tablica[index]=5;
							index++;
						}
						entrance=false;
					}
				}
				else break;
			}
			
			if(kier==2) {
				if(((i+1)%dlugoscwiersza)!=0){
					i++;
					if(entrance==true){
						if(plansza.charAt(i)!='#'){
							tablica[index]=5;
							index++;
						}
						entrance=false;
			    	}
		 	    }
				else break;
			}
			
			if(kier==4) {
				if((i%dlugoscwiersza)!=0){
					i--;
					if(entrance==true){
						if(plansza.charAt(i)!='#'){
							tablica[index]=5;
							index++;
						}
						entrance=false;
					}
				}
				else break;
			}
			
		}
		index--;
		if((plansza.charAt(i)=='X')) {
			wypisz_droge();
		}
		if(tablica[index]==5)
			while(tablica[index]==5)
			index--;
	}
	
	public void wypisz_droge(){
if(wypisane==false){
	StringBuilder s=new StringBuilder();
		for(int j=0; j <= index; j++)
			{
			if(tablica[j]==1) s.append("GÓRA\n");//System.out.print("GÓRA ");
			else if(tablica[j]==2) s.append("PRAWO\n");//System.out.print("PRAWO ");
			else if(tablica[j]==3) s.append("DÓ£\n");//System.out.print("DÓ£ ");
			else if(tablica[j]==4) s.append("LEWO\n");//System.out.print("LEWO ");
			else if(tablica[j]==5) s.append("PROSTO\n");//System.out.print("PROSTO ");
			}
		String droga = s.toString();
		System.out.println(droga);
		try {
			wpisz_do_pliku(droga);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println();
		wypisane=true;
	  }
	}
	
	public static void wyznaczKierunekStartowy(int kierunek){
		if(kierunek==0){
			if(plansza.charAt(kierunek+1)=='0'){
				kierunekStartowy=2;
				return;
			}
		}
		else if(kierunek==dlugoscwiersza-1){
			if(plansza.charAt(kierunek-1)=='0'){
				kierunekStartowy=4;
				return;
			}
		}
		else if(kierunek==dlugoscplanszy-dlugoscwiersza){
			if(plansza.charAt(kierunek+1)=='0'){
				kierunekStartowy=2;
				return;
			}
		}
		else if(kierunek==dlugoscplanszy-1){
			if(plansza.charAt(kierunek-1)=='0'){
				kierunekStartowy=4;
				return;
			}
		}
		if(kierunek<dlugoscwiersza) kierunekStartowy=3;
		else if(kierunek>=dlugoscplanszy-dlugoscwiersza) kierunekStartowy=1;
		else if((kierunek%dlugoscwiersza)==0) kierunekStartowy=2;
		else if (((kierunek+1)%dlugoscwiersza)==0) kierunekStartowy=4;
	
	}
	
	public static void main(String[] arg) throws IOException
	{
		Labirynt t = new Labirynt();
		t.wczytaj();
		t.znajdzpoczatek();
		int x=i;
		wyznaczKierunekStartowy(x);
		t.szukaj(kierunekStartowy, x);
		DrawMaze m = new DrawMaze();
		m.draw(wysokosc, dlugoscwiersza, plansza);
		
	}
}
