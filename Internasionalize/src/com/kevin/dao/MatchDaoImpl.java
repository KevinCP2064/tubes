package com.kevin.dao;

import com.kevin.entity.Club;
import com.kevin.entity.Match;
import com.kevin.util.DaoService;
import com.kevin.util.MySQLConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MatchDaoImpl implements DaoService<Match> {
    @Override
    public List<Match> fetchAll() throws SQLException, ClassNotFoundException {
        List<Match> matches=new ArrayList<>();
        try (Connection connection = MySQLConnection.createConnection()) {
            String query = "SELECT m.id,m.id_team1,m.id_team2,c.Name AS Club1,c2.Name AS Club2,m.Score1,m.Score2 FROM matchresult m JOIN club c ON m.id_team1=c.id JOIN club c2 ON m.id_team2=c2.id";
            try (PreparedStatement ps = connection.prepareStatement(query)) {
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        Club club = new Club();
                        club.setId(rs.getInt("id_team1"));
                        club.setName(rs.getString("Club1"));

                        Club club2 = new Club();
                        club2.setId(rs.getInt("id_team2"));
                        club2.setName(rs.getString("Club2"));

                        Match match = new Match();
                        match.setId(rs.getInt("id"));
                        match.setScore1(rs.getInt("Score1"));
                        match.setScore2(rs.getInt("Score2"));
                        match.setClub1(club);
                        match.setClub2(club2);
                        matches.add(match);

                    }
                }
            }
        }
        return matches;
    }

    @Override
    public int addData(Match object) throws SQLException, ClassNotFoundException {
        int result=0;
        try (Connection connection = MySQLConnection.createConnection()) {
            String query = "INSERT INTO matchresult(Score1,Score2,id_team1,id_team2) VALUES(?,?,?,?)";
            try (PreparedStatement ps = connection.prepareStatement(query)) {
                ps.setInt(1, object.getScore1());
                ps.setInt(2, object.getScore2());
                ps.setInt(3, object.getClub1().getId());
                ps.setInt(4, object.getClub2().getId());
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
    public List<Match> showAllData() {
        List<Match> matches=new ArrayList<>();
        try (Connection connection = MySQLConnection.createConnection()) {
            String query = "SELECT m.id,m.id_team1,m.id_team2,c.Name AS Club1,c2.Name AS Club2,m.Score1,m.Score2 FROM matchresult m JOIN club c ON m.id_team1=c.id JOIN club c2 ON m.id_team2=c2.id";
            try (PreparedStatement ps = connection.prepareStatement(query)) {
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        Club club = new Club();
                        club.setId(rs.getInt("id_team1"));
                        club.setName(rs.getString("Club1"));

                        Club club2 = new Club();
                        club2.setId(rs.getInt("id_team2"));
                        club2.setName(rs.getString("Club2"));

                        Match match = new Match();
                        match.setId(rs.getInt("id"));
                        match.setScore1(rs.getInt("Score1"));
                        match.setScore2(rs.getInt("Score2"));
                        match.setClub1(club);
                        match.setClub2(club2);
                        matches.add(match);

                    }
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return matches;
    }
}
