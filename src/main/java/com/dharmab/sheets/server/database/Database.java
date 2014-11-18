package com.dharmab.sheets.server.database;

import com.dharmab.sheets.server.character.Character;
import com.google.common.base.Optional;

import java.util.List;

public interface Database {
    /**
     * Prepare the database for application start, including running any pending migrations.
     */
    public void start();

    /**
     * Prepare the database for application stop, including closing any database connections.
     */
    public void stop();

    /**
     * If the given character exists in the database, update the database with the current state. Otherwise, save the
     * character as a new character.
     *
     * @param character Character to persist, identified by ID.
     */
    public void persist(Character character);

    /**
     * Delete the given character from the database.
     *
     * @param character Character to delete, identified by ID.
     */
    public void delete(Character character);

    /**
     * Retreive the given character from the database.
     *
     * @param id ID of character to retrieve.
     * @return Character identified by the given ID, or null if no such character was found.
     */
    public Optional<Character> getCharacter(Integer id);

    List<Character> getAll();
}
