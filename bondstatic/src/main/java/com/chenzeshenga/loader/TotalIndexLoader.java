package com.chenzeshenga.loader;

import java.io.IOException;
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
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import redis.clients.jedis.Jedis;

@Component
public class TotalIndexLoader {

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private Jedis jedis;

	@PostConstruct
	@Scheduled(fixedRate = 60 * 1000 * 1000)
	public void init() throws JsonParseException, JsonMappingException, IOException {
		String str = restTemplate.getForObject(
				"http://nufm.dfcfw.com/EM_Finance2014NumericApplication/JS.aspx?type=CT&cmd=3994812,3950322,3950312,3950222,3950212,0000611,0000221,0000131,0000121&sty=MPICTTA&sortType=(ChangePercent)&sortRule=-1&page=1&pageSize=20&token=7bc05d0d4c3c22ef9fca8c2a912d779c&jsName=quote_123&_g=0.628606915911589&_=1519825747271",
				String.class);
		String reg1 = "\"" + "," + "\"";
		String rep1 = "\"" + ";" + "\"";
		str = str.replaceAll("\\(\\[", "").replaceAll("\\]\\)", "").replaceAll(reg1, rep1);
		String[] strs = str.split(";");

		for (int i = 0; i < strs.length; i++) {
			String[] childs = strs[i].replaceAll("\"", "").split(",");
			Map<String, String> map = new HashMap<String, String>();
			map.put("indexcode", childs[1]);
			map.put("indexname", childs[2]);
			map.put("openprice", childs[3]);
			map.put("lastdayprice", childs[4]);
			map.put("latestprice", childs[5]);
			map.put("raiseamount", childs[6]);
			map.put("raiserate", childs[7]);
			map.put("highestprice", childs[9]);
			map.put("lowestprice", childs[10]);
			map.put("timestamp", new Date().toString());
			jedis.hmset(childs[1] + ":" + DateUtil.getDate(new Date()), map);
			System.out.println(map);
		}
	}
}
