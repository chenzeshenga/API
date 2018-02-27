package com.chenzeshenga.loader;

import java.util.Date;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import redis.clients.jedis.Jedis;

@Component
public class GovernmentBondShanghaiLoader {

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private Jedis jedis;

	@PostConstruct
	@Scheduled(fixedRate = 60 * 1000 * 1000)
	public void init() {
		String str = restTemplate.getForObject(
				"http://nufm.dfcfw.com/EM_Finance2014NumericApplication/JS.aspx?type=CT&cmd=C._DEBT_SH_G&sty=FCOIATA&sortType=(ChangePercent)&sortRule=-1&page=2&pageSize=20&token=7bc05d0d4c3c22ef9fca8c2a912d779c&jsName=quote_123&_g=0.628606915911589&_=1519746540141",
				String.class);
		jedis.set(new Date().toString(), str);
	}
}
