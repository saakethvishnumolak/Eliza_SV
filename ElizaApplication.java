import java.io.FileNotFoundException;
import java.util.Scanner;

public class ElizaApplication 
{

	public static void main(String[] args) 
	{
		try 
		{
			Eliza eliza = new Eliza("positive.txt", "negative.txt", "positivePhrases.txt", "negativePhrases.txt",  "verbs.txt", "interrogativePhrases.txt", "randomQuestions.txt");
			System.out.println("Eliza: " + eliza.startConversation());
			
			while(true)
			{
				Scanner scan = new Scanner(System.in);
				String userInput = scan.nextLine();
				if(userInput.toLowerCase().equals("bye") || userInput.toLowerCase().equals("goodbye"))
					break;
					
				String response = eliza.processUserResponse(userInput);
				System.out.println("Eliza: " + response);
				//scan.close();
			}
			
			
		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		}
	}

}
