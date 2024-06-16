package hw2;
//-----------------------------------------------------
// Title: Homework2 solution class
// Author: Oğuzhan Altın
// ID: 15052066386
// Section: 1
// Assignment: 2
// Description: This class is for changing data type of the file to integer.
//-----------------------------------------------------

public class Valuefinder {
	
	public static int returnInt(String value) {
	 //--------------------------------------------------------
     // Summary: Change the data type of the parameter.
     // Precondition: String v is the data type which will change.
     // Postcondition: Returns int which changed from string.
     //--------------------------------------------------------
		  
	        return Integer.parseInt(value);  //Change values data type to integer and returns value.
	    }
	}

