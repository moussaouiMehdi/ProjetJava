package be.moussaoui.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import be.moussaoui.pojo.Administrator;
import be.moussaoui.pojo.User;

public class UserDAO implements DAO<User>{
	private Connection conn;
	
	
	public UserDAO() {
		this.conn = DataBaseConnection.getInstance();
	}

	@Override
	public boolean insert(User obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(User obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(User obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public User find(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<User> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public static User login(String username, String password) {
		Connection conn = DataBaseConnection.getInstance();
		User user = null;
		String query="SELECT * FROM user WHERE username='"+username+"'";
		try{
			ResultSet result = conn.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY).executeQuery(query);
			if(result.first()){
				String pwd=result.getString("password");
				if(pwd.equals(password)) {
					int id=result.getInt("userId");
					String type=result.getString("type");
					//admin case
					if(type.equals("Admin")) {
						//Comme admin pas d'attribut spécifique, pas besoin d'appeler la adminDAO, on a déjà récup toutes les infos 
						Administrator admin = new Administrator(id,username, pwd);
						user = admin;
					}else {
						//player case
						PlayerDAO playerDAO = new PlayerDAO();
						user = playerDAO.find(id);
					}
				}
			}
			
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return user;
	}
	
	public static boolean check(String username) {
		Connection conn = DataBaseConnection.getInstance();
		String query="SELECT userId FROM user WHERE username='"+username+"'";
		try{
			ResultSet result = conn.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY).executeQuery(query);
			if(result.next()){
				return true;
			}
			
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return false;
	}
}
