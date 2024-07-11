package inventory;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/add-item")
public class AddItem extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ItemDAO itemDAO;

    @Override
    public void init() {
        itemDAO = new ItemDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String name = request.getParameter("name");
        String category = request.getParameter("category");
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        double price = Double.parseDouble(request.getParameter("price"));
        String supplier = request.getParameter("supplier");
        String description = request.getParameter("description");
        
        Item newItem = new Item();
        newItem.setName(name);
        newItem.setCategory(category);
        newItem.setQuantity(quantity);
        newItem.setPrice(price);
        newItem.setSupplier(supplier);
        newItem.setDescription(description);

        boolean result = itemDAO.addItem(newItem);
        
        if (result) {
        	
            response.sendRedirect("view.html"); // Redirect to view items page
        } else {
            response.getWriter().println("Failed to add item.");
        }
    }
}
