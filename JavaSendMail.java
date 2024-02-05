
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.java;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 *
 * @author RAMESH
 */
@WebServlet(name = "JavaSendMail", urlPatterns = {"/JavaSendMail"})
public class JavaSendMail extends HttpServlet {

    String name, subject, email, from,to,price;
   

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        response.setContentType("text/html");
        name = request.getParameter("name");
        email = request.getParameter("email");
        subject ="Bus Ticket";
        from = request.getParameter("stationsf");
        to=request.getParameter("stationst");
        price=request.getParameter("ticket");
       

        final String username = "bhithisher@gmail.com";//your email id
        final String password = "awxlsktuzirpivab";// your password
        Properties props = new Properties();
        props.put("mail.smtp.auth", true);
        props.put("mail.smtp.starttls.enable", true);
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(email));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));

            MimeBodyPart textPart = new MimeBodyPart();
            Multipart multipart = new MimeMultipart();
            String final_Text = "Name: " + name + "\n" + "Email: " + email + " \n " + "Subject: " + subject + "\n " + "From: " + from+ "\n "+ "To: " + to+ "\n "+ "Price: " +price;
            textPart.setText(final_Text);
            message.setSubject(subject);
            multipart.addBodyPart(textPart);
            message.setContent(multipart);
            message.setSubject("Contact Details");
            //out.println("Sending");
            Transport.send(message);
            out.println("<center><h2 style='color:green;'>Email Sent Successfully.</h2>");
            out.println("Thank you " + name + ",The  ticket has been sent.</center>");
        } catch (Exception e) {
            out.println(e);
        }
    }
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet JavaSendMail</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet JavaSendMail at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
   // @Override
    //protected void doPost(HttpServletRequest request, HttpServletResponse response)
       //     throws ServletException, IOException {
        //processRequest(request, response);
    //}

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
