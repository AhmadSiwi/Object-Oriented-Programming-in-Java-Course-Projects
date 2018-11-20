import java.util.*;
public class Main {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String [] movies = {"TheGodFather", "TheShawshankRedemption", "PulpFiction", "StarWars", "TheDarkKnight"};
		Random rand = new Random();
		int n = rand.nextInt(5);
		String movie = movies[n];
		String output = "";
		for(int i=0; i<movie.length(); i++){
			output += "_";
		}
		Scanner s = new Scanner(System.in);
		char g;
		boolean found;
		int wrongletters = 0;
		System.out.println("Java GuessTheMovie");
		while(true){
			if(wrongletters==10){
				System.out.println("You have guessed ("+wrongletters+") wrong letters:");
				System.out.println("You lose!");
				break;
			}
			if(output.indexOf('_')==-1){
				System.out.println("You have guessed '"+output+"' correctly.");
				System.out.println("You win!");
				System.out.println("The correct film is "+movie+".");
				break;
			}
			System.out.println("You are guessing:"+output);
			System.out.println("You have guessed ("+wrongletters+") wrong letters:");
			System.out.print("Guess a letter:");
			g = s.next().charAt(0);
			found = false;
			for(int i=0; i<movie.length(); i++){
				if(movie.charAt(i)==g){
					output = output.substring(0, i)+g+output.substring(i+1);
					found = true;
				}
			}
			if(found==false){
				wrongletters += 1;
			}
		}
	}
}
