package com.controller;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Vector;

/**
 * Created by Lenovo on 2015/6/7.
 */
public class SpecialsServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {
        ServletContext context = getServletContext();
        request.setAttribute("specials", getSpecials());
        context.getRequestDispatcher("/showspecials.jsp")
                .forward(request, response);
    }

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    private List getSpecials() {
        List retval = new Vector();
        retval.add(new Special("Coq au Vin", 15));
        retval.add(new Special("Pad Thai", 10));
        retval.add(new Special("Lobster Thermador", 10));
        retval.add(new Special("Baked Alaska", 8));
        return retval;
    }

    public class Special {
        int price;
        String menuItem;

        public Special(String item, int inPrice) {
            menuItem = item;
            price = inPrice;
        }

        public int getPrice() {
            return price;
        }

        public String getMenuItem() {
            return menuItem;
        }
    }
}
