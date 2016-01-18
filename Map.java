import java.io.*;

public class Map{
    
    public Map(String filename){
	map = readMap(filename);
    } 

    /****************
     boundaries - holds default map bounds in form loX, loY, hiX, hiY
    *****************/
    private int[] boundaries = {0, 0, 70, 30};
    private char[][] map;
	
    public char[][] readMap(String fileName) {
	map = new char[102][302];

	try {
	    FileReader fileReader = new FileReader(fileName);
	    BufferedReader bufferedReader = new BufferedReader(fileReader);

	    int linectr = 0; //couldn't use for loop with readLine()
	    String line;
		
	    while((line = bufferedReader.readLine()) != null) {
		char[] charArr = line.toCharArray(); //convert to array of chars
		for (int i = 0; i < charArr.length; i++) 
		    map[linectr][i] = charArr[i]; //copy over to map		
		linectr++; //increment line counter
	    }
	    bufferedReader.close();
	}

	catch(FileNotFoundException ex) {
	    System.out.println("Unable to open file '" + fileName + "'");
	}
	catch(IOException ex) {
	    System.out.println("Error reading file '" + fileName + "'");
	}

	return map;
    }

    public String toString(){
	String retStr = "";
	int max1 = Math.min(boundaries[3], map.length);
	int max2 = Math.min(boundaries[2], map[0].length);
	for (int lineNum = Math.min(boundaries[1], 0); lineNum < max1; lineNum++) {
	    for (int chNum = Math.min(boundaries[0], 0); chNum < max2; chNum++) {
		retStr += map[lineNum][chNum];
	    }		
	    retStr += "\n";
	}
	return retStr;
    }
	
	
    
    //zoom in, out. adjusts boundaries. in = + or -1. + (zoom in) - (zoom out)
    public void zoom(int in){
	boundaries[0] += 5 * in;
	boundaries[1] += 5 * in;
	boundaries[2] -= 5 * in;
	boundaries[3] -= 5 * in;
    }

    //pans boundaries. takes half of old map, half of new direction. -2 - down, -1 - left, 1 - right, 2 - up
    //DEFAULT SPACING OF BOUNDARY SHOULD BE EVEN!!! -loY and hiY are opposite parity
    //-2 - down -1 - left 1 - right 2 - up
    public void pan(int dir){
		
	switch(dir){
	case -2://e.g. 1,4 as Y's become 3,6 
	    boundaries[1] =  (boundaries[3] + boundaries[1]) / 2 + 1;
	    boundaries[3] += (boundaries[3] - boundaries[1]) + 1;
	    break;
	case 2://e.g. 3,6 as Y's become 1,4
	    boundaries[3] = (boundaries[3] + boundaries[1]) / 2;
	    boundaries[1] -= (boundaries[3] - boundaries[1]) + 1;
	    break;
	case -1://same as case 2 for x.
	    boundaries[2] = (boundaries[0] + boundaries[2]) / 2;
	    boundaries[0] -= (boundaries[2] - boundaries[0]) + 1;
	    break;
	case 1:
	    boundaries[0] = (boundaries[2] + boundaries[0]) / 2 + 1;
	    boundaries[2] += (boundaries[2] - boundaries[0]) + 1;
	    break;	
	default:
	    System.out.println("Invalid pan");
	    break;
	}
		
    }
	
	public char get(int x, int y){
		return map[y][x];
	}
	
	public char set(int x, int y, char c){
		char temp = get(x,y);
		map[y][x] = c;
		return temp;
	}

}
