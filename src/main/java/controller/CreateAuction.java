package controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.exceptions.BookIsExist;
import domain.exceptions.PersonNotFoundException;
import domain.model.Library;


public class CreateAuction  {


	public String execute(HttpServletRequest req, HttpServletResponse response)  {
		
		String p=req.getParameter("leastPrice");
		int minPrice=Integer.parseInt(p);
		String bookName=req.getParameter("bookName");
		String y=req.getParameter("publishYear");
		int publishYear=Integer.parseInt(y);
		String quality=req.getParameter("quality");
		String writerName=req.getParameter("writerName");
		String writerSurName=req.getParameter("writerSurname");
		String startDayStr=req.getParameter("startDay");
		String startMonthStr=req.getParameter("startMonth");
		String startYearStr=req.getParameter("startYear");
		//int startYear=Integer.parseInt(startYearStr);
		String endDayStr=req.getParameter("endDay");
		//int endDay=Integer.parseInt(endDayStr);
		String endMonthStr=req.getParameter("endMonth");
		//int endMonth=Integer.parseInt(endMonthStr);
		String endYearStr=req.getParameter("endYear");
		//int endYear=Integer.parseInt(endYearStr);
		//(int sellerId,String bookName,String bookWriter ,int publishYear,Quality quality,Date startDate,Date endDate,int price )
		
		try{
			Date endDate=makeDate(endDayStr,endMonthStr,endYearStr);
			Date startDate=makeDate(startDayStr,startMonthStr,startYearStr);
			Library.createAuction(Integer.parseInt(req.getParameter("personId")),bookName,writerName+" "+writerSurName,publishYear,quality,startDate,endDate,minPrice);
			req.setAttribute("message","auction has been created successfully!");
		}catch(ParseException e){
			req.setAttribute("message","auction has not been created successfully!" + " " + e.getMessage());
		} catch (BookIsExist e) {
			req.setAttribute("message", e.getMessage());
			e.printStackTrace();
		} catch (PersonNotFoundException e) {
			req.setAttribute("message", e.getMessage());
			e.printStackTrace();
		}
		return "Bookstore.jsp";
	}
	
	Date makeDate(String  dStr,String mStr,String yStr ) throws ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		StringBuilder builder= new StringBuilder();
		int d=Integer.parseInt(dStr);
		int m=Integer.parseInt(mStr);
		if(d<10)
			builder.append("0");
		builder.append(dStr+"-");
		if(m<10)
			builder.append("0");
		builder.append(mStr+"-"+yStr);
		return sdf.parse(builder.toString());
	}
}
