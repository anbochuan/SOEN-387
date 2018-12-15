package org.soen387.domain.game.mapper;

import java.sql.SQLException;

import org.dsrg.soenea.domain.MapperException;
import org.dsrg.soenea.domain.mapper.GenericOutputMapper;
import org.dsrg.soenea.domain.mapper.LostUpdateException;
import org.soen387.domain.game.Game;
import org.soen387.service.GameTDG;
import org.soen387.service.UserTDG;

public class GameOutputMapper extends GenericOutputMapper<Long, Game>{

	@Override
	public void insert(Game game) throws MapperException {
		// TODO Auto-generated method stub
		Long id = game.getId();
		Long version = game.getVersion();
		Long challenger = game.getChallenger();
		Long challengee = game.getChallengee();
		Long challengerDeck = game.getChallengerDeck();
		Long challengeeDeck = game.getChallengeeDeck();
		
		try {
			int affectedRow = GameTDG.insert(id, version, challenger, challengee, challengerDeck, challengeeDeck);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			throw new MapperException("Something wrong, can not insert game with id: " + id, e);
		}
	}

	@Override
	public void update(Game game) throws MapperException {
		// TODO Auto-generated method stub
		Long id = game.getId();
		Long version = game.getVersion();
		Long challenger = game.getChallenger();
		Long challengee = game.getChallengee();
		Long challengerDeck = game.getChallengerDeck();
		Long challengeeDeck = game.getChallengeeDeck();
		
		try {
			int affectedRow = GameTDG.update(id, version, challenger, challengee, challengerDeck, challengeeDeck);
			if(affectedRow == 0) {
				throw new LostUpdateException("LostUpdate: id " + id + " version " + version);
			}
			game.setVersion(game.getVersion()+1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			throw new MapperException("Something wrong, can not update Game " + id,e);
		}
	}

	@Override
	public void delete(Game game) throws MapperException {
		// TODO Auto-generated method stub
		Long id = game.getId();
		Long version = game.getVersion();
		try {
			int affectedRow = UserTDG.delete(id, version);
			if(affectedRow == 0) {
				throw new LostUpdateException("LostUpdate: id " + id + " version " + version);
			}
			//game.setVersion(game.getVersion()+1);
		} catch(SQLException e) {
			throw new MapperException("Something wrong, can not delete game " + id, e);
		}
	}

}
