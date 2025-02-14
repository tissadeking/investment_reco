package com.servlet;
import java.util.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/result")
public class CheckServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        // get PrintWriter
        PrintWriter pw = res.getWriter();
        // set res type
        res.setContentType("text/html");
        //res.setContentType("image/jpeg");
        
        //INPUT DATA PROCESSING
        // read the form data
        String name = req.getParameter("name");
        
        String field = req.getParameter("field");
        String amount = req.getParameter("amount");
        
        String profit = req.getParameter("profit");
        String dependents = req.getParameter("dependents");
        String land = req.getParameter("land");
        String investment = req.getParameter("investment");
        String crime = req.getParameter("crime");
        
        String property_worth;
        if (land.equals("no") == true) {
        	property_worth = "0.6";
        }
        else {
        	property_worth = req.getParameter("property_worth");
        }
        String investment_worth;
        if (investment.equals("no") == true) {
        	investment_worth = "0.6";
        }
        else {
        	investment_worth = req.getParameter("investment_worth");
        }
        //String investment_worth = req.getParameter("investment_worth");
        String incarceration;
        if (crime.equals("no") == true) {
        	incarceration = "0";
        }
        else {
        	incarceration = req.getParameter("incarceration");
        }
        //String incarceration = req.getParameter("incarceration");
        if (amount.contains(",") == true) {
        	amount = amount.replace(",", "");
        }
        if (profit.contains(",") == true) {
        	profit = profit.replace(",", "");
        }
        
        //String qlfy = req.getParameter("qlfy");
        double profit_2 = Double.parseDouble(profit);
        double amt = Double.parseDouble(amount);
        //int amt = Integer.parseInt(amount);
        //int roi = (int)(amt * 0.1);
               
        //INVESTMENT FUNCTION EVALUATION
        // profitability level calculation
        //Evaluation_app eval = new Evaluation_app();
        //double profitability_level = eval.calculateInvestment(amt, profit_2, dependents, property_worth, investment_worth, incarceration, field);

        double profitability_level = Evaluation_app.calculateInvestment(amt, profit_2, dependents, property_worth, investment_worth, incarceration, field);

        
        //DATA PROCESSING AND OUTPUT
        // add bootstrap
        pw.println(
                "<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css\">\r\n"
                        + "<link rel=\"stylesheet\" href=\"style_result.css\">\r\n"
                		+ "\r\n" + "<!-- jQuery library -->\r\n"
                        + "<script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js\"></script>\r\n"
                        + "\r\n" + "<!-- Popper JS -->\r\n"
                        + "<script src=\"https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js\"></script>\r\n"
                        + "\r\n" + "<!-- Latest compiled JavaScript -->\r\n"
                        + "<script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js\"></script>");
        
        // print data
        pw.println("<div style='width:800px;height:800px;margin:auto;margin-top:50px;'>");
        //pw.println("<table class='table table-hover table-striped'>");
        pw.println("<table class='table-hover table-striped'>");
        pw.println("<tr>");
        pw.println("<td>Name</td>");
        pw.println("<td>" + name + "</td>");
        pw.println("</tr>");
        pw.println("<tr>");
        pw.println("<td>Field of Business</td>");
        pw.println("<td>" + field + "</td>");
        pw.println("</tr>");
        pw.println("<tr>");
        pw.println("<td>Requested Amount</td>");
        pw.println("<td>" + amount + "</td>");
        pw.println("</tr>");
        pw.println("<tr>");
        pw.println("<td>Profitability Level</td>");
        pw.println("<td>" + profitability_level + "</td>");
        pw.println("</tr>");
        double reco_amt = 0;
        String status = "";
        if (profitability_level >= 22.69) {
        	pw.println("<tr>");
	        pw.println("<td>Status</td>");
	        status = "Approve Investment";
	        pw.println("<td>" + status + "</td>");
	        pw.println("</tr>");
	        reco_amt = amt;
        }
        else {
        	reco_amt = amt * profitability_level/22.69;
        	pw.println("<tr>");
        	pw.println("<td>Status</td>");
        	status = "Reject Investment";
	        pw.println("<td>" + status + "</td>");
	        pw.println("</tr>");
	        pw.println("<tr>");
	        pw.println("<td>Recommended Maximum Amount</td>");
	        pw.println("<td>" + reco_amt + "</td>");
	        pw.println("</tr>");
        }
	        
        pw.println("</table>");
        pw.println("</div>");
        // close the stream
        pw.close();
        
        //STORE DATA IN DATABASE
        SQL_store store = new SQL_store();
        /*try {
			store.createTable();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
        store.insertData(name, field, amt, profitability_level, status, reco_amt);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        doGet(req, res);
    }
    
}
