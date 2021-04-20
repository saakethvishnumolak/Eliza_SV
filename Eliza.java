import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Random;

/**
 * A simple Eliza program where the user can converse with the program.
 * 
 * @author saake
 *
 */
public class Eliza 
{
	String [] positive = new String[178];
	String [] negative = new String[133];
	String [] positivePhrases = new String[3];
	String [] negativePhrases = new String[2];
	String [] verbs = new String[38];
	String [] interrogativePhrases = new String[7];
	String [] randomQuestions = new String[3];
	
	//Constructor - Read and Scan txt files
	public Eliza(String positiveFileName, String negativeFileName, String positivePhrasesFileName, String negativePhrasesFileName, String verbsFileName, String interrogativePhrasesFileName, String randomQuestionsFileName) throws FileNotFoundException
	{
		try
		{
			//positiveFileName
			File myFile = new File(positiveFileName);
			Scanner scan = new Scanner(myFile);

			int input = 0;
			while(scan.hasNextLine())
			{
				positive[input] = scan.nextLine();
				input++;
			}
			scan.close();
			
			//negativeFileName
			myFile = new File(negativeFileName);
			scan = new Scanner(myFile);
			
			input = 0;
			while(scan.hasNextLine())
			{
				negative[input] = scan.nextLine();
				input++;
			}
			scan.close();
			
			//positivePhrasesFileName
			myFile = new File(positivePhrasesFileName);
			scan = new Scanner(myFile);
			
			input = 0;
			while(scan.hasNextLine())
			{
				positivePhrases[input] = scan.nextLine();
				input++;
			}
			scan.close();
			
			//negativePhrasesFileName
			myFile = new File(negativePhrasesFileName);
			scan = new Scanner(myFile);
			
			input = 0;
			while(scan.hasNextLine())
			{
				negativePhrases[input] = scan.nextLine();
				input++;
			}
			scan.close();
			
			//verbsFileName
			myFile = new File(verbsFileName);
			scan = new Scanner(myFile);
			
			input = 0;
			while(scan.hasNextLine())
			{
				verbs[input] = scan.nextLine();
				input++;
			}
			scan.close();
			
			//interrogativePhrasesFileName
			myFile = new File(interrogativePhrasesFileName);
			scan = new Scanner(myFile);
			
			input = 0;
			while(scan.hasNextLine())
			{
				interrogativePhrases[input] = scan.nextLine();
				input++;
			}
			scan.close();
			
			//randomQuestionsFileName
			myFile = new File(randomQuestionsFileName);
			scan = new Scanner(myFile);
			
			input = 0;
			while(scan.hasNextLine())
			{
				randomQuestions[input] = scan.nextLine();
				input++;
			}
			scan.close();
		}
		catch (FileNotFoundException e)
		{
			throw e;
		}
		
	}
	/**
	 * Method to process user input and respond with some comment back to user
	 * @param input
	 * @return
	 */
	public String processUserResponse(String input)
	{
		String verb = null;
		String verbPhrase = null;
		//Checking for verb and verbPhrase in user input
		String [] verbArray = input.split(" ");
		for(int i = 0; i < verbArray.length; i++)
		{
			for(int j = 0; j < verbs.length; j++)
			{
				if(verbArray[i].equals(verbs[j]))
				{
					verb = verbArray[i];
					String [] verbPhraseArray = input.split(verb, 2);
					verbPhrase = verbPhraseArray[1].trim();
					break;
				}
			}
			if(verb != null)
				break;
		}
		
		//Identifying if verbPhrase is positive or negative
		if(verbPhrase != null)
		{
			boolean isPositivePhrase = false;
			String [] verbPhraseArray = verbPhrase.split(" ");
			
			for(int j = 0; j < verbPhraseArray.length; j++)
			{
				for(int i = 0; i < positive.length; i++)
				{
					if(verbPhraseArray[j].equals(positive[i]))
					{
						isPositivePhrase = true;
						break;
					}
				}
			}
			
			if(isPositivePhrase == false)
			{
				for(int i = 0; i < verbPhraseArray.length; i++)
				{
					for(int j = 0; j < negative.length; j++)
					{
						if(verbPhraseArray[i].equals(negative[j]))
						{
							break;
						}
					}
				}
			}
		
		
		
			//Based on verbPhrase we are pulling positive or negative phrase and building response phrase
			String phrase = null;
			
			Random random = new Random();
			
			if(isPositivePhrase == true)
			{
				int index = random.nextInt(positivePhrases.length);
				phrase = positivePhrases[index];
			}
			else
			{
				int index = random.nextInt(negativePhrases.length);
				phrase = negativePhrases[index];
			}
		
			String responsePhrase = phrase +" "+ verb +" "+ verbPhrase + ". ";
			
			//interrogative phrase - We are pulling random interrogative phrase and building the interrogativePhrase to respond back to user
			int index = random.nextInt(interrogativePhrases.length);
			String interPhase = interrogativePhrases[index];
			String interrogativePhrase = interPhase + " " + verb + " " + verbPhrase + "?";
			
			return responsePhrase + interrogativePhrase;
		}
		else
		{
			return startConversation();
		}
	}

	/**
	 * Method to start a new conversation
	 * @return
	 */
	public String startConversation()
	{
		//random generator number to pick from randomQuestion array to start conversation
		Random random = new Random();
		int index = random.nextInt(randomQuestions.length);
		return randomQuestions[index];
		
	}
}
