package com.example.demo.pokebot.domain.model;

import lombok.Data;

//domainクラス（検索結果を入れるクラス。リポジトリークラスやサービスクラスのなどの間で渡すクラス。）
//別名：モデルクラス、DTO
@Data
public class PokeInfo {
	private String bookNumber;		//図鑑番号
	private String nameJp; 			//ポケモン名（日本語）
	private String typeId1;			//タイプ1
	private String typeId2;			//タイプ2
}
