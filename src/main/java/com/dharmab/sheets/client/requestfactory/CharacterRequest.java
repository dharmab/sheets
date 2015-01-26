package com.dharmab.sheets.client.requestfactory;

import com.dharmab.sheets.server.character.CharacterService;
import com.dharmab.sheets.server.requestfactory.InjectingServiceLocator;
import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.RequestContext;
import com.google.web.bindery.requestfactory.shared.Service;

import java.util.List;

@Service(value = CharacterService.class, locator = InjectingServiceLocator.class)
public interface CharacterRequest extends RequestContext {
    Request<CharacterProxy> get(int id);

    Request<Void> persist(CharacterProxy character);

    Request<Void> delete(CharacterProxy character);

    Request<Void> delete(Integer id);

    Request<List<CharacterProxy>> getAll();

    Request<Void> reset(CharacterProxy character);
}
