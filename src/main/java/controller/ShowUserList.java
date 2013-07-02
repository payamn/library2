package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.model.Library;

public class ShowUserList {

	public String execute(HttpServletRequest req, HttpServletResponse response) {
		System.out.print("show UserList .action");
		req.setAttribute("users", Library.getAllPersons());
	/*	req.setAttribute("Ids", Library.getAllPersonIds());*/
		req.setAttribute("personId",Integer.parseInt(req.getParameter("personId")));
		return "ListOfUsers.jsp";
	}
}
