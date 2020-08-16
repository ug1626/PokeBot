package com.example.demo.pokebot.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.pokebot.domain.model.PokeInfo;
import com.example.demo.pokebot.domain.repository.PokeBotDao;

@Service
public class PokeBotService {
	@Autowired
	PokeBotDao dao;

	public List<PokeInfo> selectPokeInfo(String x_bookNumber) {
		return dao.selectPokeInfo(x_bookNumber);
	}
}
