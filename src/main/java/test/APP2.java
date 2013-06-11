package test;

import java.util.Date;

import domain.exceptions.BookIsExist;
import domain.exceptions.PersonNotFoundException;
import domain.model.Library;

public class APP2 {
	public static void main(String args[]){
		Date end= new Date();
		end.setDate(end.getDate()+2);
		end.setSeconds(end.getSeconds()+20);
		/*try {
			Library.createAuction(1, "hasanbook", "hasan",  1990, "BAD",  new Date(), end, 1000);
		} catch (BookIsExist | PersonNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
	}
}
