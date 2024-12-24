package com.DinosaurReposetory.DinosaurReposetory.DAO.impl;

import com.DinosaurReposetory.DinosaurReposetory.DAO.Interfaces.DinosaursDao;
import com.DinosaurReposetory.DinosaurReposetory.Models.Dinosaurs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class JdbcDinosaursDao implements DinosaursDao {
    private final DataSource dataSource;

    @Autowired
    public JdbcDinosaursDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<Dinosaurs> getAllDinosaurs() {
        List<Dinosaurs> dinosaurs = new ArrayList<>();
        String sql =
                """
                SELECT *
                FROM dinosaur_zoo.dinosaurs;
                """;

        try(
                Connection connection = dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                ResultSet resultSet = preparedStatement.executeQuery();
        ){
            while(resultSet.next()){
                int speciesId = resultSet.getInt(1);
                String speciesName = resultSet.getString(2);
                String period = resultSet.getString(3);
                String dietType = resultSet.getString(4);
                String description = resultSet.getString(5);

                dinosaurs.add(new Dinosaurs(speciesId, speciesName, period, dietType, description));
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return dinosaurs;
    }

    @Override
    public Dinosaurs getDinosaursByName(String dinosaurName) {
        Dinosaurs dinosaurs;
        String sql =
                """
                SELECT *\s
                FROM dinosaur_zoo.dinosaurs
                WHERE dinosaur_name = ?
                """;

        try(
                Connection connection = dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ){
            preparedStatement.setString(1, dinosaurName);

            try(ResultSet resultSet = preparedStatement.executeQuery()){
                while(resultSet.next()){
                    int speciesId = resultSet.getInt(1);
                    String speciesName = resultSet.getString(2);
                    String period = resultSet.getString(3);
                    String dietType = resultSet.getString(4);
                    String description = resultSet.getString(5);

                    return new Dinosaurs(speciesId, speciesName, period, dietType, description);
                }
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }
}
