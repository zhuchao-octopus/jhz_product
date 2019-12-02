package com.cy.utils;

import java.util.Scanner;
/**
 * 比较俩字符串相似度的方法
 * @author CD_小鬼
 *
 */
public class SimilarGreeds {
	
	private int compare(String str, String target) {
		int d[][]; // 矩阵
		int n = str.length();
		int m = target.length();
		int i; // 遍历str的
		int j; // 遍历target的
		char ch1; // str的
		char ch2; // target的
		int temp; // 记录相同字符,在某个矩阵位置值的增量,不是0就是1
		if (n == 0) {
		return m;
		}
		if (m == 0) {
		return n;
		}
		d = new int[n + 1][m + 1];
		for (i = 0; i <= n; i++) { // 初始化第一列
		d[i][0] = i;
		}

		for (j = 0; j <= m; j++) { // 初始化第一行
		d[0][j] = j;
		}

		for (i = 1; i <= n; i++) { // 遍历str
		ch1 = str.charAt(i - 1);
		// 去匹配target
		for (j = 1; j <= m; j++) {
		ch2 = target.charAt(j - 1);
		if (ch1 == ch2) {
		temp = 0;
		} else {
		temp = 1;
		}

		// 左边+1,上边+1, 左上角+temp取最小
		d[i][j] = min(d[i - 1][j] + 1, d[i][j - 1] + 1, d[i - 1][j - 1] + temp);
		}
		}
		return d[n][m];
		}

		private int min(int one, int two, int three) {
		return (one = one < two ? one : two) < three ? one : three;
		}
		/**
		 * 获取两字符串的相似度
		 * 哥哥三
		 * @param str
		 * @param target
		 * @return
		 */
		public float getSimilarityRatio(String str, String target) {
		return 1 - (float)compare(str, target)/Math.max(str.length(), target.length());
		}

		public static void main(String[] args) {
			SimilarGreeds lt = new SimilarGreeds();
		//String str = "123456";
		//String target = "128562";
		
		String str="Sn9TUzAAADPEEECAUHCc7QAAAbPWkBAAAAg+EWwzwnAJkPoQDoAIAz6wBFAK4PpgBOPFcPygBnAGgPajxvANsPcgC0AEozjQCDADsOvACuPEMPTgCuAA0Pdzy7AMYPXAAHADkzjQDIADQPjADQPE0PzQDYAHQPsTzaACkPigAmALUzlADlACwPsQAAPasPiQAZAWIPwjwjAZQOvQDqARIyYxgD7SPvM+xoEybbMwBvFaYvZUFfDlMfswBKF0BLtZZehb6iAXJRLOv23vTeYl74jkBnEKYJ4ZI7fcU6iIQtjtGXKHY6SqjykYPhc0cIGir7hSOJTYuChlhDVHvK/NJ2FY+OuycO3HsK+I8E+Tiz9rqDevxifZjOOIR37qdv+HwAUh7nBPgMIPUBAS8axwcAsADTMEc7Ab4AGv7/OkYJPMsAIDhEXc8A1zwhMMFVwAnEFQN1f0rCDgChxBD8wUdMVsAEACIIJAQVAFwY5//v/v3C/8A3/v/CgAoDgiKawZHCwk0JA6EthsLEwcEHfgw8TDva/zH9Oyn9wlYIAM1HKYNWBzztSS1SFgCNS9/9MCMqK0pbBAkDXU5XdVjAE8XJZ4x2xMSOg8EEw/3//xYAQWXTOsD9w/7/+//9/jv+/MP+wMDAVhHFamnr//38+/wrOlXDsQsAjW1exAZnw0QHAGZzScC3EQNNdEnB/4SEoYzDMgGQdhP5QjppgP0MAIp3LT2u/pP9CQCReCkvpwYDu3pA/8FvCMWMhH/DalcLAJFUOmn9/8DE/8AXxU6o9cFTNf39IPA3ZjcBerFDwsOcwJUwABK2PULABcDD/FgVAHS3xq3A/sX3/P/+wf4Ewfz/w//BHADjf7TCw2VwwsGpwwbBwU1IwiIZAFIBwPzCcv7/wP39Pv3/wv7//sH+wQXC/DoBWsU6esLAAFz5O4QHAGT84sL8/cDCBgCLzPLAhToBkMw0wcKWHQPz1bBKwGaOA8XH/MVcd2lqHMXJ1pFHYsHCw8YDrPxJwYdnwwQQbR8QSwIA0dwkwMAAteImwIAFALEaK8O4AgCW6TDDxhBAO/b6BBBJDcP7wzgRjBwcb1KHAAh/AAAAC0VS";
		String target="T2tTUzIxAAAGKC0ECAUHCc7QAAAeKWkBAAAAhtVCnigmAP4PdADyAF4mBgFAAKIOrgBBKNoOBQFMAHEPfChSAFoNwQCTAIMnZQBeAE4OxgFYKKsP9QBiAGEPVihvANQNBAGyAKsnZACBAEsMbQCHKEYOIwCKACAMBCmLAKsPLQBLAGQlJQCaAG4N8ACYKNcNAAGmAGsPAymzADIPKABzAFQmvAC3ACkPrAC8KEEOngC+APUP1yi+AK4PeQAGAD0nVgDOAGgMNwDIKLoO0gDRAGwOZyjUAFgMBAETADEnHADXANMOHgDfKLcNNADkACUNQCjoAF8MXQAoAG0jMADyAOMNwgHyKE0PKAD2ABENYyj2AFkLkgA/ADcmeAD+AEENlAAEKVYLPQAKAZwM/igKAdUOcQDIAWQlBAEOAXAOUwAfKSUNvwAcAdMP1SgdAZ4PXQDbAeclHAAoAeIOjgAqKd4N6QAuATgOtygzAaUOmwDxAZkmrQA3AaMOwAE/KYIOxgBAAVwN7ihDAYgOKQCGAe0mUQBGAfMOzQFNKX4PwgBXAUQN9ShZAQsP8qoape0OfH1hAxpe4fMg0DMCfYXBgTSOkj8QDSUTwYFQDtsvUkEH4Z+LxHYn3ph/TQid+dD+19QE+7XzkYsIiwlcGAWRBnkLEHvw1QrxE8VrI0Ybqq/YeL0TmQHc/xBSrX959E2NKAzXoByYNWhZdYjzAleUByL3AIdveZTVBH/p536BrH7U0H+AVQWh20jrTCDP9VOFEQWkhgPT3P2u5LrV9RP/A1P82YU1BocU8t+U/dbwwPO07vMz3H2G7A4FAPfkKNfxaA+5/rgL79SsgfkOrYFUfi/eORedDm0WyRNjLIyGed+NezcUmtuE9s1/zPPwGZMuyPKKDLrSUODLwTgSmQIVAsTuHy7Ue8mHtGdAi/jy9CDJCoGDXZ/TD293lYca9LuBa1HAc8Z0ZYnEozQuBAaFg1KMC/iEo6IEiQLZ75eJw10gY5Z8PAacCvMm6Pzd/pl/eQJjLOwPOQWOiLf8NzKA9nX7vY3gfugsRwia+n4OgBcUIlsXvf1tdkv4z8";
		
		
		
		
		
		System.out.println("相似度="+ lt.getSimilarityRatio(str.trim(), target.trim()));
		}
}
