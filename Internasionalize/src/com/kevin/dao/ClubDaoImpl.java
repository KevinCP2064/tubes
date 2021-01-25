package com.kevin.dao;

import com.kevin.entity.Club;
import com.kevin.util.DaoService;
import com.kevin.util.MySQLConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClubDaoImpl implements DaoService<Club> {
    @Override
    public List<Club> fetchAll() throws SQLException, ClassNotFoundException {
        List<Club> clubs=new ArrayList<>();
        try (Connection connection = MySQLConnection.createConnection()) {
            String query = "SELECT * FROM club";
            try (PreparedStatement ps = connection.prepareStatement(query)) {
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        Club club = new Club();
                        club.setId(rs.getInt("id"));
                        club.setName(rs.getString("Name"));
                        clubs.add(club);
                    }
                }
            }
        }
        return clubs;
    }

    @Override
    public int addData(Club object) throws SQLException, ClassNotFoundException {
        int result=0;
        try (Connection connection = MySQLConnection.createConnection()) {
            String query = "INSERT INTO club(Name) VALUES(?)";
            try (PreparedStatement ps = connection.prepareStatement(query)) {
                ps.setString(1, object.getName());
                if (ps.executeUpdate() != 0) {
                    connection.commit();
                    result = 1;
                } else {
                    connection.rollback();
                }
            }
        }
        return result;
    }

    @Override
    public List<Club> showAllData(){
        List<Club> clubs=new ArrayList<>();
        try (Connection connection = MySQLConnection.createConnection()) {
            String query = "SELECT * FROM club";
            try (PreparedStatement ps = connection.prepareStatement(query)) {
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        Club club = new Club();
                        club.setId(rs.getInt("id"));
                        club.setName(rs.getString("Name"));
                        clubs.add(club);
                    }
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return clubs;
    }
}
