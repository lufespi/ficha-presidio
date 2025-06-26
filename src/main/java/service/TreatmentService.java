package service;

import configs.DBConnection;
import entities.Treatment;

import java.sql.*;

public class TreatmentService {
  private DBConnection dbConnection;

  public TreatmentService() {
    this.dbConnection = new DBConnection();
  }

  public int saveTreatment (String username, java.util.Date treatmentDate, String treatmentHour,
                           java.util.Date entryDate, boolean isTransfer, String sourceTransfer) {

    String sql = "INSERT INTO tb_treatment (" +
                 "user_id, treatment_date, treatment_hour, entry_date, " +
                 "is_transfer, source_transfer) " +
                 "VALUES ((SELECT id FROM tb_user WHERE username = ?), ?, ?, ?, ?, ?)";

    try (PreparedStatement stmt = dbConnection.getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
      stmt.setString(1, username);
      stmt.setDate(2, Date.valueOf(String.valueOf(treatmentDate)));
      stmt.setTime(3, Time.valueOf(treatmentHour));

      if (entryDate != null) {
        stmt.setDate(4, Date.valueOf(String.valueOf(entryDate)));
      } else {
        stmt.setNull(4, Types.DATE);
      }

      stmt.setBoolean(5, isTransfer);
      stmt.setString(6, sourceTransfer);

      stmt.executeUpdate();

      ResultSet rs = stmt.getGeneratedKeys();
      if (rs.next()) {
        return rs.getInt(1);
      } else {
        throw new SQLException("Erro ao obter o ID do tratamento inserido.");
      }

    } catch (SQLException e) {
      e.printStackTrace();
      return 0;
    }
  }

}
