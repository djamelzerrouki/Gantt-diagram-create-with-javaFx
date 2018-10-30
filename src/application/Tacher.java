package application;

import java.util.ArrayList;
import java.util.List;

public class Tacher {
String name ;
ArrayList<String> previousList;
int length;
int datefin;
	public int getDatefin() {
	return datefin;
}
public void setDatefin(int datefin) {
	this.datefin = datefin;
}
	public Tacher(String name, ArrayList<String> previousList, int length) {
	super();
	this.name = name;
	this.previousList = previousList;
	this.length = length;
}
	public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public ArrayList<String> getPreviousList() {
	return previousList;
}
public void setPreviousList(ArrayList<String> previousList) {
	this.previousList = previousList;
}
public int getLength() {
	return length;
}
public void setLength(int length) {
	this.length = length;
}
	public Tacher() {
		// TODO Auto-generated constructor stub
	}
public static void main(String[] args) {
	  ArrayList l1= new ArrayList<String >() ;
	  l1.add("A");
	  l1.add("B");
	  ArrayList l2= new ArrayList<String >() ;
	  l2.add("D");
	  l2.add("B");
     List<Tacher> list= new ArrayList<Tacher>();
	 list.add(new Tacher("A", null, 2));
	 list.add(new Tacher("B",null, 6));
	 list.add(new Tacher("C", l1, 3));
 	 list.add(new Tacher("D", l1, 5));
	 list.add(new Tacher("E", l2, 3));
	  ArrayList lengthList= new ArrayList<Integer >() ;
	  l1.add("A");
	  l1.add("B");
	 list.forEach(x ->{
		 //System.out.println(x.length);
		 lengthList.add(x.length);
		
	});
	 System.out.println(max(lengthList));
}
public static int max(ArrayList<Integer> lengthList) {
    if (lengthList.size() == 0) {
        // ...
    }

    int max =   (int) lengthList.get(0);

  for (Integer a : lengthList) {
  if (a > max)
          max = a;
}

    return max;
}
}
