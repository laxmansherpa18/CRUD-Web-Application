package inventory;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/update-item")
public class UpdateItem extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String category = request.getParameter("category");
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        double price = Double.parseDouble(request.getParameter("price"));
        String supplier = request.getParameter("supplier");
        String description = request.getParameter("description");

        Item item = new Item();
        item.setId(id);
        item.setName(name);
        item.setCategory(category);
        item.setQuantity(quantity);
        item.setPrice(price);
        item.setSupplier(supplier);
        item.setDescription(description);

        ItemDAO itemDAO = new ItemDAO();
        itemDAO.updateItem(item);

        response.sendRedirect("view.html");
    }
}
