package inventory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemDAO {
	private Connection conn;

	public ItemDAO() {
		try {
			conn = DBConnection.getConnection();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public boolean addItem(Item item) {
		String query = "INSERT INTO items (name, category, quantity, price, supplier, description) "
				+ "VALUES (?, ?, ?, ?, ?, ?)";
		try (PreparedStatement preparedStatement = conn.prepareStatement(query)) {
			preparedStatement.setString(1, item.getName());
			preparedStatement.setString(2, item.getCategory());
			preparedStatement.setInt(3, item.getQuantity());
			preparedStatement.setDouble(4, item.getPrice());
			preparedStatement.setString(5, item.getSupplier());
			preparedStatement.setString(6, item.getDescription());

			int rowsInserted = preparedStatement.executeUpdate();

			return rowsInserted > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	
	public List<Item> getAllItems() {
        List<Item> items = new ArrayList<>();
        String sql = "SELECT * FROM items";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Item item = new Item();
                item.setId(rs.getInt("item_id"));
                item.setName(rs.getString("name"));
                item.setCategory(rs.getString("category"));
                item.setQuantity(rs.getInt("quantity"));
                item.setPrice(rs.getDouble("price"));
                item.setSupplier(rs.getString("supplier"));
                item.setDescription(rs.getString("description"));
                items.add(item);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return items;
    }
	


    public void deleteItem(int id) {
        String sql = "DELETE FROM items WHERE item_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void updateItem(Item item) {
        String sql = "UPDATE items SET name = ?, category = ?, quantity = ?, price = ?, supplier = ?, description = ? WHERE item_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, item.getName());
            stmt.setString(2, item.getCategory());
            stmt.setInt(3, item.getQuantity());
            stmt.setDouble(4, item.getPrice());
            stmt.setString(5, item.getSupplier());
            stmt.setString(6, item.getDescription());
            stmt.setInt(7, item.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    
    
}
