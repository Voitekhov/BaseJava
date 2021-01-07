package com.urise.webapp.storage;

import com.urise.webapp.exeption.NotExistStorageException;
import com.urise.webapp.model.Resume;
import com.urise.webapp.sql.ConnectionFactory;
import com.urise.webapp.sql.SqlExecuter;
import com.urise.webapp.sql.SqlHelper;

import java.sql.*;
import java.util.List;

public class SqlStorage implements Storage {
    //public final ConnectionFactory cf;
    final SqlHelper sqlHelper;

    public SqlStorage(String dbUrl, String dbUser, String dbPassword) {
        sqlHelper = new SqlHelper(() -> {
            return DriverManager.getConnection(dbUrl, dbUser, dbPassword);
        });
    }

    @Override
    public void clear() {
      // sqlHelper.Execute("Delete FROM resumes");
    }

    @Override
    public void update(Resume r) {

    }

    @Override
    public void save(Resume r) {
        sqlHelper.<Void>execute("INSERT INTO resume VALUE (?,?)", new SqlExecuter<Void>() {
            @Override
            public Void execute(PreparedStatement ps) throws SQLException {
                ps.setString(1, r.getUuid());
                ps.setString(2, r.getFullName());
                ps.execute();
                return null;
            }
        });

    }

    @Override
    public Resume get(String uuid) {
         return sqlHelper.execute("SELECT * FROM resume WHERE uuid = ?", new SqlExecuter<Resume>() {
           @Override
           public Resume execute(PreparedStatement ps) throws SQLException {
               ps.setString(1, uuid);
               ResultSet rs = ps.executeQuery();
               if (!rs.next()) {
                   throw new NotExistStorageException(uuid);
               }
               return new Resume(uuid, rs.getString("full_name"));
           }
       });
    }

    @Override
    public void delete(String uuid) {
    }

    @Override
    public List<Resume> getAllSorted() {
        return null;
    }

    @Override
    public int size() {
        return 0;
    }
}
