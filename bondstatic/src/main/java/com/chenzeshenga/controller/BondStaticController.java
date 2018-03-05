package com.chenzeshenga.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import redis.clients.jedis.Jedis;

@RestController
@RequestMapping("/bondstatic")
public class BondStaticController {

	@Autowired
	private Jedis jedis;

	@RequestMapping("/index")
	public String getIndex(@RequestParam String indexcode, @RequestParam String days) {
		System.err.println("indexcode=" + indexcode + ",days=" + days);
		String result = jedis.get(indexcode);
		return result;

	}
}
