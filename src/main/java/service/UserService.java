package service;

import configs.DBConnection;

public class UserService {
  private DBConnection connection;

  public UserService() {
    this.connection = new DBConnection();
  }



}
