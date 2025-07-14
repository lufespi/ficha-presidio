package service;

import configs.DBConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class VaccinationStatusService {
  private DBConnection dbConnection;
  public VaccinationStatusService() {
    this.dbConnection = new DBConnection();
  }
  public int saveVaccinationStatus(
          boolean covid,
          boolean influenza,
          boolean tetanus,
          boolean hepatitisB,
          boolean offerCovid,
          boolean offerHepatitis,
          boolean offerInfluenza,
          boolean offerFever,
          boolean offerAdultDuo,
          boolean offerSrc,
          String offerOtherVaccination,
          boolean offerPortfolioCopy) {

    String sql = """
        INSERT INTO tb_vaccination_status (
            covid, influenza, tetanus, hepatitis_b,
            offer_covid, offer_hepatitis, offer_influenza, offer_fever,
            offer_adult_duo, offer_src, offer_other_vaccination, offer_portfolio_copy
        ) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
    """;

    try (PreparedStatement stmt = dbConnection.getConnection()
            .prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

      stmt.setBoolean(1, covid);
      stmt.setBoolean(2, influenza);
      stmt.setBoolean(3, tetanus);
      stmt.setBoolean(4, hepatitisB);
      stmt.setBoolean(5, offerCovid);
      stmt.setBoolean(6, offerHepatitis);
      stmt.setBoolean(7, offerInfluenza);
      stmt.setBoolean(8, offerFever);
      stmt.setBoolean(9, offerAdultDuo);
      stmt.setBoolean(10, offerSrc);
      stmt.setString(11, offerOtherVaccination);
      stmt.setBoolean(12, offerPortfolioCopy);

      stmt.executeUpdate();

      ResultSet rs = stmt.getGeneratedKeys();
      if (rs.next()) {
        return rs.getInt(1);
      } else {
        throw new SQLException("Erro ao obter o ID de tb_vaccination_status inserido.");
      }

    } catch (SQLException e) {
      e.printStackTrace();
      return 0;
    }
  }


  public int updateVaccinationStatus(
          int id,
          boolean covid,
          boolean influenza,
          boolean tetanus,
          boolean hepatitisB,
          boolean offerCovid,
          boolean offerHepatitis,
          boolean offerInfluenza,
          boolean offerFever,
          boolean offerAdultDuo,
          boolean offerSrc,
          String offerOtherVaccination,
          boolean offerPortfolioCopy) {

    String sql = """
        UPDATE tb_vaccination_status SET
            covid = ?, influenza = ?, tetanus = ?, hepatitis_b = ?,
            offer_covid = ?, offer_hepatitis = ?, offer_influenza = ?, offer_fever = ?,
            offer_adult_duo = ?, offer_src = ?, offer_other_vaccination = ?, offer_portfolio_copy = ?
        WHERE id = ?
    """;

    try (PreparedStatement stmt = dbConnection.getConnection().prepareStatement(sql)) {

      stmt.setBoolean(1, covid);
      stmt.setBoolean(2, influenza);
      stmt.setBoolean(3, tetanus);
      stmt.setBoolean(4, hepatitisB);
      stmt.setBoolean(5, offerCovid);
      stmt.setBoolean(6, offerHepatitis);
      stmt.setBoolean(7, offerInfluenza);
      stmt.setBoolean(8, offerFever);
      stmt.setBoolean(9, offerAdultDuo);
      stmt.setBoolean(10, offerSrc);
      stmt.setString(11, offerOtherVaccination);
      stmt.setBoolean(12, offerPortfolioCopy);
      stmt.setInt(13, id);

      stmt.executeUpdate();

      return id;

    } catch (SQLException e) {
      e.printStackTrace();
      return -1;
    }
  }



}
