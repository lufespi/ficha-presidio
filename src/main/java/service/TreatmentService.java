package service;

import configs.DBConnection;
import entities.Treatment;
import entities.User;
import utils.DateUtils;

import java.sql.*;

public class TreatmentService {
  private DBConnection dbConnection;

  public TreatmentService() {
    this.dbConnection = new DBConnection();
  }

  public int saveTreatment (User username, java.util.Date treatmentDate,
                            java.util.Date entryDate, boolean isTransfer, String sourceTransfer) {

    String sql = "INSERT INTO tb_treatment (" +
                 "user_id, treatment_date, entry_date, " +
                 "is_transfer, source_transfer) " +
                 "VALUES ((SELECT id FROM tb_user WHERE username = ?), ?, ?, ?, ?)";

    try (PreparedStatement stmt = dbConnection.getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
      stmt.setString(1, username.getUsername());
      stmt.setString(2, DateUtils.getFormattedDateHour(treatmentDate));

      if (entryDate != null) {
        stmt.setString(3, DateUtils.getFormattedDate(entryDate));
      } else {
        stmt.setNull(3, Types.DATE);
      }

      stmt.setBoolean(4, isTransfer);
      stmt.setString(5, sourceTransfer);

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
