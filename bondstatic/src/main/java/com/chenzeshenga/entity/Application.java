package com.chenzeshenga.entity;

import java.util.Date;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;

import redis.clients.jedis.Jedis;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		RestTemplate restTemplate = new RestTemplate();
		String str = restTemplate.getForObject(
				"http://nufm.dfcfw.com/EM_Finance2014NumericApplication/JS.aspx?type=CT&cmd=P.(x),(x)|0000011|3990012&sty=SHSTD|SZSTD&st=z&sr=&p=&ps=&cb=&token=beb0a0047196124721f56b0f0ff5a27c",
				String.class);
		// restTemplate.getForObject("http://nufm.dfcfw.com/EM_Finance2014NumericApplication/JS.aspx?type=CT&cmd=C._DEBT_SH_Q&sty=FCOIATA&sortType=(ChangePercent)&sortRule=-1&page=1&pageSize=20&js=var%20yaWyWfrP={rank:[(x)],pages:(pc),total:(tot)}&token=7bc05d0d4c3c22ef9fca8c2a912d779c&jsName=quote_123&_g=0.628606915911589&_=1519659693484",
		// Map.class);
		// String
		// str=restTemplate.getForObject("http://nufm.dfcfw.com/EM_Finance2014NumericApplication/JS.aspx?type=CT&cmd=C._DEBT_SH_Q&sty=FCOIATA&sortType=(ChangePercent)&sortRule=-1&page=1&pageSize=20&token=7bc05d0d4c3c22ef9fca8c2a912d779c&jsName=quote_123&_g=0.628606915911589&_=1519659693484",
		// String.class);
		// System.err.println(json);
		System.err.println(str);

		Jedis jedis = new Jedis("47.93.248.132");
		System.out.println("连接成功");
		jedis.set(new Date().toString(), str);
		// 查看服务是否运行
		System.out.println("服务正在运行: " + jedis.ping());
	}
}
