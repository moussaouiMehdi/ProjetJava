package be.moussaoui.dao;

import java.sql.Connection;
import java.util.ArrayList;

import be.moussaoui.pojo.VideoGame;

public class VideoGameDAO implements DAO<VideoGame> {
	private Connection conn;
	
	public VideoGameDAO() {
		this.conn = DataBaseConnection.getInstance();
	}


	@Override
	public boolean insert(VideoGame obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(VideoGame obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(VideoGame obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public VideoGame find(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<VideoGame> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
