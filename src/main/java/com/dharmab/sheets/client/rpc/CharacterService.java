package com.dharmab.sheets.client.rpc;

import com.dharmab.sheets.shared.character.Character;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import java.util.List;

@RemoteServiceRelativePath("character")
public interface CharacterService extends RemoteService {
    Boolean merge(Character character);

    void delete(Character character);

    void delete(Integer id);

    Character get(Integer id);

    List<Character> getAll();
}
