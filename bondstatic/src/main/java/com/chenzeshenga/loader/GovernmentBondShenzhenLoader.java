package com.chenzeshenga.loader;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

//import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.chenzeshenga.util.DateUtil;

import redis.clients.jedis.Jedis;

@Component
public class GovernmentBondShenzhenLoader {


	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private Jedis jedis;

	@PostConstruct
	@Scheduled(fixedRate = 60 * 1000 * 1000)
	public void init() {
		String restAPI = "http://nufm.dfcfw.com/EM_Finance2014NumericApplication/JS.aspx?type=CT&cmd=C._DEBT_SZ_G&sty=FCOIATA&sortType=(ChangePercent)&sortRule=-1&page=1&pageSize=20&token=7bc05d0d4c3c22ef9fca8c2a912d779c&jsName=quote_123&_g=0.628606915911589&_=1519830563167";
		for (int i = 0; i < 10; i++) {
			String str = restTemplate.getForObject(restAPI.replace("page=1", "page=" + (i + 1)), String.class);
			String reg1 = "\"" + "," + "\"";
			String rep1 = "\"" + ";" + "\"";
			str = str.replaceAll("\\(\\[", "").replaceAll("\\]\\)", "").replaceAll(reg1, rep1);
			String[] strs = str.split(";");

			for (int j = 0; j < strs.length; j++) {
				String[] childs = strs[j].replaceAll("\"", "").split(",");
				Map<String, String> map = new HashMap<String, String>();
				map.put("bondcode", childs[1]);
				map.put("bondname", childs[2]);
				map.put("openprice", childs[10]);
				map.put("latestprice", childs[3]);
				map.put("highestprice", childs[11]);
				map.put("lowestprice", childs[12]);
				map.put("timestamp", new Date().toString());
				jedis.hmset(childs[1]+":"+DateUtil.getDate(new Date()), map);
				System.out.println(map);
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
