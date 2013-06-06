package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.model.Library;

public class ShowUserList {

	public String execute(HttpServletRequest req, HttpServletResponse response) {

		req.setAttribute("users", Library.getProfiles());
		req.setAttribute("personId",Integer.parseInt(req.getParameter("personId")));
		return "ListOfUsers.jsp";
	}
}
