package com.chenzeshenga.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import redis.clients.jedis.Jedis;

@RestController
@RequestMapping("/bondstatic")
public class BondStaticController {

	@Autowired
	private Jedis jedis;

	@RequestMapping("/hisindex")
	@ResponseBody
	public String getHisIndex(@RequestParam String indexcode, @RequestParam String days) {
		System.err.println("indexcode=" + indexcode + ",days=" + days);
		Iterator<String> iter = jedis.hkeys(indexcode).iterator();
		List<String> data = new ArrayList<String>();

		while (iter.hasNext()) {
			String key = iter.next();
			List<String> value = jedis.hmget(indexcode, key);
			System.out.println(key + ":" + value.get(0));
			data.add(value.get(0));
		}

		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < data.size(); i++) {
			sb.append(",");
			sb.append(data.get(i));
		}

		System.err.println(sb);
		return sb.toString().replaceFirst(",", "");

	}

	@RequestMapping("/index")
	@ResponseBody
	public String getIndex(@RequestParam String indexcode) {
		System.err.println("indexcode=" + indexcode);
		Iterator<String> iter = jedis.hkeys(indexcode).iterator();
		List<String> data = new ArrayList<String>();

		while (iter.hasNext()) {
			String key = iter.next();
			List<String> value = jedis.hmget(indexcode, key);
			System.out.println(key + ":" + value.get(0));
			data.add(value.get(0));
		}

		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < data.size(); i++) {
			sb.append(",");
			sb.append(data.get(i));
		}

		System.err.println(sb);
		return sb.toString().replaceFirst(",", "");

	}

	@RequestMapping("/bond")
	@ResponseBody
	public String getBond(@RequestParam String indexcode) {
		System.err.println("indexcode=" + indexcode);
		Iterator<String> iter = jedis.hkeys(indexcode).iterator();
		List<String> data = new ArrayList<String>();

		while (iter.hasNext()) {
			String key = iter.next();
			List<String> value = jedis.hmget(indexcode, key);
			System.out.println(key + ":" + value.get(0));
			data.add(value.get(0));
		}

		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < data.size(); i++) {
			sb.append(",");
			sb.append(data.get(i));
		}

		System.err.println(sb);
		return sb.toString().replaceFirst(",", "");

	}

	@RequestMapping("/hisBond")
	@ResponseBody
	public String getHisBond(@RequestParam String indexcode, @RequestParam String days) {
		System.err.println("indexcode=" + indexcode + ",days=" + days);
		Iterator<String> iter = jedis.hkeys(indexcode).iterator();
		List<String> data = new ArrayList<String>();

		while (iter.hasNext()) {
			String key = iter.next();
			List<String> value = jedis.hmget(indexcode, key);
			System.out.println(key + ":" + value.get(0));
			data.add(value.get(0));
		}

		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < data.size(); i++) {
			sb.append(",");
			sb.append(data.get(i));
		}

		System.err.println(sb);
		return sb.toString().replaceFirst(",", "");

	}
}
