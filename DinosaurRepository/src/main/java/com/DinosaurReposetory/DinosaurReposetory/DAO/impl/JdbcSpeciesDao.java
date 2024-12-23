package com.DinosaurReposetory.DinosaurReposetory.DAO.impl;

import com.DinosaurReposetory.DinosaurReposetory.DAO.Interfaces.SpeciesDao;
import com.DinosaurReposetory.DinosaurReposetory.Models.Species;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcSpeciesDao implements SpeciesDao {
    private final DataSource dataSource;

    @Autowired
    public JdbcSpeciesDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<Species> getAllSpecies() {
        List<Species> species = new ArrayList<>();

        String sql =
                """
                SELECT * 
                FROM dinosaur_zoo.species;
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

                species.add(new Species(speciesId, speciesName, period, dietType, description));
            }

        }catch(SQLException e){
            e.printStackTrace();
        }
        return species;
    }

    @Override
    public Species getSpeciesByName(String speciesName) {
        return null;
    }
}
