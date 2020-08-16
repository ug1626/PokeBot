package com.example.demo.pokebot.domain.repository;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.example.demo.pokebot.domain.model.PokeInfo;

//中身の実装クラスをJdbcTemplateとNamedParameterJdbcTemplate切り替え出来るようにインターフェースを作成
public interface PokeBotDao {

	//m_pokemonsのデータを取得
	public List<PokeInfo> selectPokeInfo(String x_bookNumber) throws DataAccessException;
}
