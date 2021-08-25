package com.codecool.shop.dao.implementation;

import com.codecool.shop.controller.Database.DatabaseManager;
import com.codecool.shop.dao.UserDao;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.sql.*;
import java.util.List;

public class UserDaoDatabase implements UserDao {

    private DataSource dataSource = new DatabaseManager().connect();
    private static final Logger logger = LoggerFactory.getLogger(ProductDaoDatabase.class);
    private String username;
    private String password;
    private String email;


    public UserDaoDatabase(String username, String password, String email) throws SQLException {
        this.username = username;
        this.password = password;
        this.email = email;

    }

    public UserDaoDatabase() throws SQLException {

    }


    @Override
    public void add(User player) {
        System.out.println("itt nem jó");
        try (Connection conn = dataSource.getConnection()) {
            String sql = "INSERT INTO customer (username, email, password, first_name, last_name," +
                    "phone_number,billing_address) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement st = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            st.setString(1, username);
            st.setString(2, email);
            st.setString(3, password);
            st.setString(4, player.getFirstName());
            st.setString(5, player.getLastName());
            st.setString(6, player.getPhoneNumber());
            st.setString(7, player.getBillingAddress());
            st.executeUpdate();
            ResultSet rs = st.getGeneratedKeys();
            rs.next();
        } catch (SQLException throwables) {
            logger.info("Error while adding new user: " + throwables.getMessage());
            throw new RuntimeException("Error while adding new user: ", throwables);
        }
    }

    @Override
    public User find(int id) {
        return null;
    }

    @Override
    public User find_by_name(String username) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT * FROM customer WHERE username = ?";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, username);
            ResultSet rs = st.executeQuery();
            if (!rs.next()) { // first row was not found == no data was returned by the query
                return null;
            }
            return new User(rs.getString(1),rs.getString(2),rs.getString(3),
                    rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7), rs.getString(8));
        } catch (SQLException e) {
            logger.info("Error while finding ProductCategories: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public void remove(int id) {

    }

    @Override
    public List<User> getAll() {
        return null;
    }



    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
