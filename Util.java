/*******************
 class Util - holds constants and driver's static methods 
******************/


public class Util{
    
    public static final String[] turnStateNames = {"Reinforce","Attack", "Fortify"};

    public static final String[] cardTypes = {"Soldier", "Horse", "Cannon", "Wildcard"};

    public static final String userChars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
	
	public static final int[] initialTroops = {0, 40, 35, 30, 25, 20};
	
    public static final Continent nAm = new Continent("North America", 5, 0, 9);
    public static final Continent sAm = new Continent ("South America", 2, 9, 13);
    public static final Continent africa = new Continent("Africa", 3, 13, 19);
    public static final Continent europe = new Continent("Europe", 5, 19, 26);
    public static final Continent australia = new Continent("Australia", 2, 26, 30);
    public static final Continent asia = new Continent("Asia", 7, 30, 42);

    public static final Continent[] continents = {nAm, sAm, africa, europe, australia, asia};
	
	
    public static final int[] cardBonus = {4, 6, 8, 10, 12, 15};

    public static int cardBonusCtr = 0; //num sets traded in so far
	
    public static int getCardReinforce(){
	cardBonusCtr++;
	if (cardBonusCtr > cardBonus.length)
	    return (cardBonusCtr - cardBonus.length) * 5 + cardBonus[cardBonus.length - 1];
	return cardBonus[cardBonusCtr - 1];
    }
	
	public static int getCardReinforcePredict(){
		int ret = getCardReinforce();
		cardBonusCtr--;
		return ret;
	}

    //Wait function, for that authentic feel.
    public static void wait(int ms){try {Thread.sleep(ms);} catch (InterruptedException ie) {}}
	
    //@William - it'd be nice to make this generic, but I'm not sure how
    private static void swap( int i, int j , Country[] arr) {
	Country temp = arr[i];
	arr[i] = arr[j];
	arr[j] = temp;
    }
	
    public static void shuffle(Country[] arr) {
	int len = arr.length;
	//len - i. Elements to choose from.
	for (int i = 0; i < len; i++){
	    swap(i, i + (int)(Math.random()*(len-i)), arr);
	}
    }

    //assuming same length;
    public static int[] average(int[] i1, int[] i2){
	int[] ret = new int[i1.length];
	for (int i = 0; i < i1.length; i++)
	    ret[i] = (i1[i] + i2[i])/2;
	return ret;
    }
	
    //good oppportunity to use generics but nooo i'm dumb - Joel
    public static boolean contains(int[] a, int b){
	for (int i: a)
	    if (i == b)
		return true;
	return false;
    }

    public static int rollDie(){
	return (int)(Math.random() * 6) + 1;
    }
	
	public static boolean validTrade(Card[] input){
		if (input.length != 3)
			return false;
		int type1 = input[0].getType();
		int type2 = input[1].getType();
		int type3 = input[2].getType();
		if (type1 == 3 || type2 == 3 || type3 == 3)
			return true;
		if (type1 == type2)
			return type1 == type3;
		else
			return type1 != type3 && type2 != type3;
	}
	
}
