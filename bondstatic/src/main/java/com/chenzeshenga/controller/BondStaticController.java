package com.chenzeshenga.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.chenzeshenga.util.DateUtil;

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
			List<String> value = jedis.hmget(indexcode + ":" + DateUtil.getDate(new Date()), key);
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
		List<String> data = new ArrayList<String>();
		data.add(DateUtil.getDate(new Date()));
		List<String> value1 = jedis.hmget(indexcode + ":" + DateUtil.getDate(new Date()), "openprice");
		System.out.println("openprice" + ":" + value1.get(0));
		data.add(value1.get(0));
		List<String> value2 = jedis.hmget(indexcode + ":" + DateUtil.getDate(new Date()), "latestprice");
		System.out.println("latestprice" + ":" + value2.get(0));
		data.add(value2.get(0));
		List<String> value3 = jedis.hmget(indexcode + ":" + DateUtil.getDate(new Date()), "highestprice");
		System.out.println("highestprice" + ":" + value3.get(0));
		data.add(value3.get(0));
		List<String> value4 = jedis.hmget(indexcode + ":" + DateUtil.getDate(new Date()), "lowestprice");
		System.out.println("lowestprice" + ":" + value4.get(0));
		data.add(value4.get(0));

		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < data.size(); i++) {
			sb.append(",");
			sb.append(data.get(i));
		}

		System.err.println(sb);
		sb = new StringBuilder("['").append(sb.toString().replaceFirst(",", "")).append("]");
		return sb.toString().replaceFirst(",", "',");

	}

	@RequestMapping("/bond")
	@ResponseBody
	public String getBond(@RequestParam String indexcode) {
		System.err.println("indexcode=" + indexcode);
		Iterator<String> iter = jedis.hkeys(indexcode).iterator();
		List<String> data = new ArrayList<String>();

		while (iter.hasNext()) {
			String key = iter.next();
			List<String> value = jedis.hmget(indexcode + ":" + DateUtil.getDate(new Date()), key);
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
			List<String> value = jedis.hmget(indexcode + ":" + DateUtil.getDate(new Date()), key);
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
