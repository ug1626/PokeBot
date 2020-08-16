package com.example.demo.pokebot.controller;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.example.demo.pokebot.domain.model.PokeInfo;
import com.example.demo.pokebot.domain.service.PokeBotService;
import com.linecorp.bot.model.event.Event;
import com.linecorp.bot.model.event.MessageEvent;
import com.linecorp.bot.model.event.message.TextMessageContent;
import com.linecorp.bot.model.message.TextMessage;
import com.linecorp.bot.spring.boot.annotation.EventMapping;
import com.linecorp.bot.spring.boot.annotation.LineMessageHandler;

@Controller
@LineMessageHandler
public class PokeInfoController {

	@Autowired
	PokeBotService pokeBotService;

	@EventMapping
	public TextMessage handleText(MessageEvent<TextMessageContent> event) {
		System.out.println("event: " + event);
		TextMessageContent tmc = event.getMessage();
		String text = tmc.getText();
		return replyMsg(text);
	}

	@EventMapping
	public void handleEvent(Event event) {
		System.out.println("event: " + event);
	}

	// オウム返しをする
//	private TextMessage parrot(String text) {
//		return new TextMessage(text);
//	}

	private TextMessage replyMsg(String text) {
//		selectPoke();
//		System.out.println(selectPokeInfo(text));

//		String pokemonName = null;
//		if ("1".equals(text)) {
//			pokemonName = "フシギバナ";
//			List<PokeInfo> selectPokeInfo(text)
//		}
//		return new TextMessage("TEST");

		String pokeInfo = getReplyMsg(text);
		if (Objects.isNull(pokeInfo)) {
			pokeInfo = "該当するポケモンの情報がありません。";
		}
		return new TextMessage(pokeInfo);
	}

	//SQLで取得
//	public void selectPoke() {
//		List<Map<String,Object>> list;
//		list = jdbcTemplate.queryForList("select * from m_pokemons");
//		System.out.println(list.toString());
//	}

	//図鑑番号をキーにポケモン情報を取得
	public String getReplyMsg(String x_bookNumber) {
		List<PokeInfo> lists = pokeBotService.selectPokeInfo(x_bookNumber);
		StringBuilder sb = new StringBuilder();
		for (PokeInfo pokeInfo: lists) {
			if (sb.length() > 1) {
				sb.append("\n");
			}
			if (Objects.nonNull(pokeInfo.getBookNumber())) {
				sb.append(pokeInfo.getBookNumber());
			}
			if (Objects.nonNull(pokeInfo.getNameJp())) {
				sb.append("：" + pokeInfo.getNameJp());
			}

			if (Objects.nonNull(pokeInfo.getTypeId1())) {
				sb.append("\nタイプ1：" + pokeInfo.getTypeId1());
			}

			if (Objects.nonNull(pokeInfo.getTypeId2())) {
				sb.append("\nタイプ2：" + pokeInfo.getTypeId2());
			}
		}

		if (sb.length() < 1) {
			sb.append("該当するポケモンがいません。");
		}
		return sb.toString();
	}
}
