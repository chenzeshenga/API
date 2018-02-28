package com.chenzeshenga.loader;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import redis.clients.jedis.Jedis;

@Component
public class CorpBondShanghaiLoader {

	private static Logger logger = Logger.getLogger(CorpBondShanghaiLoader.class);

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private Jedis jedis;

	@PostConstruct
	@Scheduled(fixedRate = 60 * 1000 * 1000)
	public void init() {
		String restAPI = "http://nufm.dfcfw.com/EM_Finance2014NumericApplication/JS.aspx?type=CT&cmd=C._DEBT_SH_Q&sty=FCOIATA&sortType=(ChangePercent)&sortRule=-1&page=1&pageSize=20&token=7bc05d0d4c3c22ef9fca8c2a912d779c&jsName=quote_123&_g=0.628606915911589&_=1519830348062";
		for (int i = 0; i < 100; i++) {
			String str = restTemplate.getForObject(restAPI.replace("page=1", "page=" + (i + 1)), String.class);
			String reg1 = "\"" + "," + "\"";
			String rep1 = "\"" + ";" + "\"";
			str = str.replaceAll("\\(\\[", "").replaceAll("\\]\\)", "").replaceAll(reg1, rep1);
			String[] strs = str.split(";");

			for (int j = 0; j < strs.length; j++) {
				String[] childs = strs[j].replaceAll("\"", "").split(",");
				Map<String, String> map = new HashMap<String, String>();
				map.put("indexcode", childs[1]);
				map.put("indexname", childs[2]);
				map.put("latestprice", childs[3]);
				map.put("raisequantity", childs[4]);
				map.put("raiserate", childs[5]);
				map.put("openprice", childs[6]);
				map.put("highestprice", childs[7]);
				map.put("lowestprice", childs[8]);
				map.put("closeprice", childs[9]);
				map.put("lastcloseprice", childs[10]);
				map.put("dealamount", childs[11]);
				map.put("dealquantity", childs[12]);
				map.put("timestamp", new Date().toString());
				jedis.hmset(childs[1], map);
				logger.info(childs[1]);
				logger.info(map);
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
