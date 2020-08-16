package com.example.demo.pokebot.domain.repository.jdbc;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.pokebot.domain.model.PokeInfo;
import com.example.demo.pokebot.domain.repository.PokeBotDao;

@Repository
public class PokeBotJdbcImpl implements PokeBotDao {

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Override
	public List<PokeInfo> selectPokeInfo(String x_bookNumber) throws DataAccessException {
		String query = "SELECT"
				+ " A.book_number,"
				+ " A.name_jp,"
				+ " B.type_name_jp AS type_id1,"
				+ " C.type_name_jp AS type_id2"
				+ " FROM m_pokemons A"
				+ " LEFT JOIN m_types B"
				+ " ON A.type_id1 = B.type_id"
				+ " LEFT JOIN m_types C"
				+ " ON A.type_id2 = C.type_id"
				+ " WHERE book_number = ?";

		List<Map<String, Object>> lists = jdbcTemplate.queryForList(query, x_bookNumber);

		List<PokeInfo> pokeList = new ArrayList<>();
		for(Map<String, Object> map: lists) {
			PokeInfo pokeInfo = new PokeInfo();
			pokeInfo.setBookNumber((String)map.get("book_number"));
			pokeInfo.setNameJp((String)map.get("name_jp"));
			pokeInfo.setTypeId1((String)map.get("type_id1"));
			pokeInfo.setTypeId2((String)map.get("type_id2"));
			pokeList.add(pokeInfo);
		}
		return pokeList;
	}

}
