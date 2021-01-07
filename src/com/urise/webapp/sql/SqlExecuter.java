package com.urise.webapp.sql;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface SqlExecuter<T> {
    T execute (PreparedStatement ps) throws SQLException;
}
