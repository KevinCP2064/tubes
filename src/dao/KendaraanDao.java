package dao;

import Entity.Kendaraan;
import util.DaoService;
import util.MySQLConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class KendaraanDao implements DaoService<Kendaraan> {
    @Override
    public List<Kendaraan> fetchAll() throws SQLException, ClassNotFoundException {
        List<Kendaraan> kendaraans = new ArrayList<>();
        try {
            Connection connection = MySQLConnection.createConnection();
            String query ="Select * from kendaraan";
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs =ps.executeQuery();
            while(rs.next()){
                Kendaraan kendaraan = new Kendaraan();
                kendaraan.setNo_plat(rs.getString("no_plat"));
                kendaraan.setTipe_kendaraan(rs.getString("tipe_kendaraan"));
                kendaraan.setMerk(rs.getString("merk"));
                kendaraan.setJns_kendaraan(rs.getString("jns_kendaraan"));
                kendaraan.setId_customer(rs.getInt("id_customer"));
            }
        }catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return kendaraans;
    }

    @Override
    public int addData(Kendaraan object) throws SQLException, ClassNotFoundException {
        int result = 0;
        try {
            Connection connection = MySQLConnection.createConnection();
            String query = "INSERT INTO Kendaraan(no_plat, id_customer, tipe_kendaraan, merk,jns_kendaraan) VALUE (?, ?, ?, ?)";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, object.getNo_plat());
            ps.setInt(2, object.getId_customer());
            ps.setString(3, object.getTipe_kendaraan());
            ps.setString(4, object.getMerk());
            ps.setString(5, object.getJns_kendaraan());
            if (ps.executeUpdate() != 0){
                connection.commit();
                result = 1;
            } else {
                connection.rollback();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public int deleteData(Kendaraan object) throws SQLException, ClassNotFoundException {
        int result = 0;
        try {
            Connection connection = MySQLConnection.createConnection();
            String query = "DELETE FROM kendaraan WHERE no_plat= ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, object.getNo_plat() );
            if (ps.executeUpdate() != 0){
                connection.commit();
                result = 1;
            } else {
                connection.rollback();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public int updateData(Kendaraan object) throws SQLException, ClassNotFoundException {
        int result = 0;
        try {
            Connection connection = MySQLConnection.createConnection();
            String query = "UPDATE Kendaraan SET id_customer = ?, tipe_kendaraan = ?, merk = ?, jns_kendaraan = ? WHERE no_plat= ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, object.getId_customer());
            ps.setString(2, object.getTipe_kendaraan());
            ps.setString(3, object.getMerk());
            ps.setString(4, object.getJns_kendaraan());
            ps.setString(5, object.getNo_plat());
            if (ps.executeUpdate() != 0){
                connection.commit();
                result = 1;
            } else {
                connection.rollback();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}
