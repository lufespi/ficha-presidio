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
  public int saveVaccinationStatus(boolean covid, boolean influenza, boolean tetanus,
                                   boolean hepatitisB, String offerVaccination,
                                   boolean offerPortfolioCopy) {

    String sql = """
        INSERT INTO tb_vaccination_status (
            covid, influenza, tetanus, hepatitis_b,
            offer_vaccination, offer_portfolio_copy
        ) VALUES (?, ?, ?, ?, ?, ?)
    """;

    try (PreparedStatement stmt = dbConnection.getConnection()
            .prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

      stmt.setBoolean(1, covid);
      stmt.setBoolean(2, influenza);
      stmt.setBoolean(3, tetanus);
      stmt.setBoolean(4, hepatitisB);
      stmt.setString(5, offerVaccination);
      stmt.setBoolean(6, offerPortfolioCopy);

      stmt.executeUpdate();

      ResultSet rs = stmt.getGeneratedKeys();
      if (rs.next()) {
        return rs.getInt(1);
      } else {
        throw new SQLException("Erro ao obter o ID do status de vacinação inserido.");
      }

    } catch (SQLException e) {
      e.printStackTrace();
      return 0;
    }
  }

  public boolean updateVaccinationStatus(int id, boolean covid, boolean influenza, boolean tetanus,
                                         boolean hepatitisB, String offerVaccination,
                                         boolean offerPortfolioCopy) {

    String sql = """
        UPDATE tb_vaccination_status SET
            covid = ?, influenza = ?, tetanus = ?, hepatitis_b = ?,
            offer_vaccination = ?, offer_portfolio_copy = ?
        WHERE id = ?
    """;

    try (PreparedStatement stmt = dbConnection.getConnection().prepareStatement(sql)) {
      stmt.setBoolean(1, covid);
      stmt.setBoolean(2, influenza);
      stmt.setBoolean(3, tetanus);
      stmt.setBoolean(4, hepatitisB);
      stmt.setString(5, offerVaccination);
      stmt.setBoolean(6, offerPortfolioCopy);
      stmt.setInt(7, id);

      return stmt.executeUpdate() > 0;

    } catch (SQLException e) {
      e.printStackTrace();
      return false;
    }
  }


}
