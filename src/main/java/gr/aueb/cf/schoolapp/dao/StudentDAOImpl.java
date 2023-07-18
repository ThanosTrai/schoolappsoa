package gr.aueb.cf.schoolapp.dao;

import gr.aueb.cf.schoolapp.dao.IStudentDAO;
import gr.aueb.cf.schoolapp.dao.exceptions.StudentDAOException;
import gr.aueb.cf.schoolapp.model.Student;
import gr.aueb.cf.schoolapp.service.util.DBUtil;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentDAOImpl implements IStudentDAO {
    @Override
    public Student insert(Student student) throws StudentDAOException {
        String sql = "INSERT INTO STUDENTS (FIRSTNAME, LASTNAME) VALUES (?, ?)";

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql);) {
            String firstname = student.getFirstname();
            String lastname = student.getLastname();

            ps.setString(1, firstname);
            ps.setString(2, lastname);

            int n = ps.executeUpdate();
            if (n >= 1) {
                JOptionPane.showMessageDialog(null, n + " rows affected", "Successful Insert", JOptionPane.PLAIN_MESSAGE);
                return student;
            } else {
                return null;
            }
        } catch (SQLException e) {
//            e.printStackTrace();
            throw new StudentDAOException("Error in Student Insert: " + student);
        }
    }

    @Override
    public Student update(Student student) throws StudentDAOException {
        String sql = "UPDATE STUDENTS SET FIRSTNAME = ?, LASTNAME = ? WHERE ID = ?";

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql);) {

            int id = student.getId();
            String firstname = student.getFirstname();
            String lastname = student.getLastname();

            ps.setString(1, firstname);
            ps.setString(2, lastname);
            ps.setInt(3, id);

            int n = ps.executeUpdate();
            if (n >= 1) {
                JOptionPane.showMessageDialog(null, n + " rows affected", "Successful Update", JOptionPane.PLAIN_MESSAGE);
                return student;
            } else {
                return null;
            }
        } catch (SQLException e) {
//            e.printStackTrace();
            throw new StudentDAOException("Error in Student Update: " + student);
        }
    }

    @Override
    public void delete(int id) throws StudentDAOException {
        String sql = "DELETE FROM STUDENTS WHERE ID = ?";

        try (Connection connection = DBUtil.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (SQLException e) {
//            e.printStackTrace();
            throw new StudentDAOException("Error in Student with id: " + id);
        }
    }

    @Override
    public List<Student> getByLastname(String lastname) throws StudentDAOException {
        String sql = "SELECT ID, FIRSTNAME, LASTNAME FROM STUDENTS WHERE LASTNAME LIKE ?";
        ResultSet rs;
        List<Student> students = new ArrayList<>();

        try (Connection connection = DBUtil.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, lastname + '%');
            rs = ps.executeQuery();

            while (rs.next()) {

                Student student = new Student(
                rs.getInt("ID"),
                rs.getString("FIRSTNAME"),
                rs.getString("LASTNAME")
                );
                students.add(student);
            }
            return students;
        } catch (SQLException e) {
//            e.printStackTrace();
            throw new StudentDAOException("SQL Error in Student with lastname = " + lastname);
        }
    }

    @Override
    public Student getById(int id) throws StudentDAOException {
        Student student = null;
        ResultSet rs;
        String sql = "SELECT ID, FIRSTNAME, LASTNAME FROM STUDENTS WHERE ID = ?";

        try (Connection connection = DBUtil.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, id);
            rs = ps.executeQuery();

            while(rs.next()) {
                student = new Student(
                        rs.getInt("ID"),
                        rs.getString("FIRSTNAME"),
                        rs.getString("LASTNAME")
                );
            }
            return student;
        } catch (SQLException e) {
//            e.printStackTrace();
        } throw new StudentDAOException("SQL Error is Student with Id = " + id);
    }
}
