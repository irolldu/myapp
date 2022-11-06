package com.mycompany.myapp.component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.stereotype.Component;

@Component
public class PostFilterList extends ArrayList<Object> {
	{
		add(new HashMap<String, Object> () {
			{
				put("type", "categoryIDs");
				put("items", new ArrayList<Object>() {
					{
						add(new HashMap<String, Object>() {
							{
								put("dataID", 1);
								put("name", "국내봉사");
							}
						});
						add(new HashMap<String, Object>() {
							{
								put("dataID", 2);
								put("name", "해외봉사/탐방");
							}
						});
						add(new HashMap<String, Object>() {
							{
								put("dataID", 3);
								put("name", "서포터즈");
							}
						});
						add(new HashMap<String, Object>() {
							{
								put("dataID", 4);
								put("name", "기자단");
							}
						});
						add(new HashMap<String, Object>() {
							{
								put("dataID", 5);
								put("name", "마케터");
							}
						});
						add(new HashMap<String, Object>() {
							{
								put("dataID", 6);
								put("name", "강연/행사");
							}
						});
						add(new HashMap<String, Object>() {
							{
								put("dataID", 7);
								put("name", "기타");
							}
						});
					}
				});
			}
		});
		add(new HashMap<String, Object> () {
			{
				put("type", "companyTypeIDs");
				put("items", new ArrayList<Object>() {
					{
						add(new HashMap<String, Object>() {
							{
								put("dataID", 1);
								put("name", "중앙정부/기관");
							}
						});
						add(new HashMap<String, Object>() {
							{
								put("dataID", 2);
								put("name", "지자체/기관");
							}
						});
						add(new HashMap<String, Object>() {
							{
								put("dataID", 3);
								put("name", "학교/재단/협회");
							}
						});
						add(new HashMap<String, Object>() {
							{
								put("dataID", 4);
								put("name", "비영리단체");
							}
						});
						add(new HashMap<String, Object>() {
							{
								put("dataID", 5);
								put("name", "공기업");
							}
						});
						add(new HashMap<String, Object>() {
							{
								put("dataID", 6);
								put("name", "일반기업");
							}
						});
						add(new HashMap<String, Object>() {
							{
								put("dataID", 7);
								put("name", "신문/방송/언론");
							}
						});
						add(new HashMap<String, Object>() {
							{
								put("dataID", 8);
								put("name", "기타");
							}
						});
					}
				});
			}
		});
		add(new HashMap<String, Object> () {
			{
				put("type", "prizeTotalIDs");
				put("items", new ArrayList<Object>() {
					{
						add(new HashMap<String, Object>() {
							{
								put("dataID", 1);
								put("name", "없음");
							}
						});
						add(new HashMap<String, Object>() {
							{
								put("dataID", 2);
								put("name", "5천만원이상");
							}
						});
						add(new HashMap<String, Object>() {
							{
								put("dataID", 3);
								put("name", "5천만원~3천만원");
							}
						});
						add(new HashMap<String, Object>() {
							{
								put("dataID", 4);
								put("name", "3천만원~1천만원");
							}
						});
						add(new HashMap<String, Object>() {
							{
								put("dataID", 5);
								put("name", "1천만원이하");
							}
						});
					}
				});
			}
		});
		add(new HashMap<String, Object> () {
			{
				put("type", "prizeBenefitIDs");
				put("items", new ArrayList<Object>() {
					{
						add(new HashMap<String, Object>() {
							{
								put("dataID", 1);
								put("name", "입사가산점");
							}
						});
						add(new HashMap<String, Object>() {
							{
								put("dataID", 2);
								put("name", "인턴채용");
							}
						});
						add(new HashMap<String, Object>() {
							{
								put("dataID", 3);
								put("name", "정규직채용");
							}
						});
						add(new HashMap<String, Object>() {
							{
								put("dataID", 4);
								put("name", "해외연수");
							}
						});
						add(new HashMap<String, Object>() {
							{
								put("dataID", 5);
								put("name", "작품전시");
							}
						});
						add(new HashMap<String, Object>() {
							{
								put("dataID", 6);
								put("name", "기타");
							}
						});
					}
				});
			}
		});
	}
	
	private Stream<Object> streamItems(String type) {
		return this
				.stream()
				.filter(t -> ((Map<String, Object>) t).get("type").equals(type))
				.map(t -> ((Map<String, Object>) t).get("items"))
				.flatMap(t -> ((List<Object>) t).stream());
	}
	
	public List<Object> getCategoryItems() {
		return this
				.streamItems("categoryIDs")
				.collect(Collectors.toList());
	}
	
	public List<Object> getCompanyTypeItems() {
		return this
				.streamItems("companyTypeIDs")
				.collect(Collectors.toList());
	}
	
	public List<Object> getPrizeTotalItems() {
		return this
				.streamItems("prizeTotalIDs")
				.collect(Collectors.toList());
	}
	
	public List<Object> getPrizeBenefitItems() {
		return this
				.streamItems("prizeBenefitIDs")
				.collect(Collectors.toList());
	}
	
	private String getName(String type, int dataID) {
		return (String) this
				.streamItems(type)
				.filter(t -> ((Map<String, Object>) t).get("dataID").equals(dataID))
				.map(t -> ((Map<String, Object>) t).get("name"))
				.findAny()
				.orElse(null);
	}
	
	public String getCategoryName(int dataID) {
		return getName("categoryIDs", dataID);
	}
	
	public String getCompanyTypeName(int dataID) {
		return getName("companyTypeIDs", dataID);
	}
	
	public String getPrizeTotalName(int dataID) {
		return getName("prizeTotalIDs", dataID);
	}
	
	public String getPrizeBenefitName(int dataID) {
		return getName("prizeBenefitIDs", dataID);
	}
	
}
