package com.dharmab.sheets.server.database;

import org.hibernate.Session;

public interface SessionManager {

    public Session openSession();

    public void close();
}
