package com.cy.service.check;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cy.dao.check.NewCheckDao;
import com.cy.dao.line.OverTimeDao;
import com.cy.dao.line.ProductWorkerDao;
import com.cy.dao.line.WorkLineDao;
import com.cy.domain.check.NewCheck;
import com.cy.domain.daynote.LineRecord;
import com.cy.domain.daynote.ProductRecord;
import com.cy.domain.line.OverTime;
import com.cy.domain.line.ProductDetails;
import com.cy.domain.line.ProductWorker;
import com.cy.domain.line.WorkLine;
import com.cy.utils.CyResult;
import com.cy.utils.CyUtil;
import com.google.gson.Gson;

@Service
public class CheckAppServiceImpl implements CheckAppService {
	@Resource
	private WorkLineDao lineDao;

	@Resource
	private NewCheckDao newDao;

	@Resource
	private OverTimeDao otDao;

	@Resource
	private ProductWorkerDao pwDao;

	@Override
	public CyResult loadAppProducts(ProductDetails pd) throws ParseException {
		Gson gson = new Gson();
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		SimpleDateFormat sd = new SimpleDateFormat("HH:mm");
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		DecimalFormat df = new DecimalFormat("#.00");
		CyResult result = new CyResult();
		NewCheck nc = new NewCheck();
		nc.setPname(pd.getPname());
		List<NewCheck> checkList = newDao.findNewCheckByWorkerCode(nc);
		if (checkList == null || checkList.size() < 1) {
			result.setMsg("暂无生产记录！");
			result.setState(0);
			return result;
		}
		ProductRecord record = new ProductRecord();
		record.setPname(pd.getPname());
		String workTime = null;
		Long timeNum = 0L;
		for (NewCheck ncheck : checkList) {
			Long time = 0L;
			if (ncheck.getDownLine() != null) {
				time = CyUtil.parseTime(ncheck.getDownLine(), ncheck.getUpLine());

			} else if (ncheck.getDownLine() == null && ncheck.getNowDate().equals(CyUtil.getTime2())) {
				time = CyUtil.parseTime(CyUtil.getTime1(), ncheck.getUpLine());

			}
			timeNum += time;
		}

		Double timeStr = ((double) (timeNum * 1.0 / 1000 / 60 / 60));
		if (timeStr.toString().substring(0, 1).equals("0")) {
			workTime = "0" + df.format(timeStr);
		} else {
			workTime = df.format(timeStr);
		}
		record.setWorkTime(workTime + "小時");
		ProductWorker worker = new ProductWorker();
		List<NewCheck> zuList = new ArrayList<NewCheck>();// 组装打卡记录
		List<NewCheck> packList = new ArrayList<NewCheck>();
		for (NewCheck nck : checkList) {
			// System.out.println(nck);
			if (nck.getScstate().contains("组装")) {
				zuList.add(nck);
			} else {
				packList.add(nck);
			}
		}
		System.out.println("组装线=" + zuList);
		System.out.println("包装线=" + packList);
		/*
		 * 计算组装投入
		 */
		OverTime ot = new OverTime();
		List<OverTime> ovtlist = null;
		Double SumPrice1 = 0.0;
		Double SumPrice2 = 0.0;
		List<NewCheck> jlist = new ArrayList<NewCheck>();
		// Iterator<NewCheck> it=zuList.iterator();
		if (zuList != null && zuList.size() > 0) {
			for (NewCheck check : zuList) {
				ot.setCreatime(check.getNowDate());
				ovtlist = otDao.findOverTime(ot);

				if (ovtlist != null && ovtlist.size() > 0) {
					for (OverTime ovt : ovtlist) {
						if (check.getWorker().getWorkerCode().equals(ovt.getWorker().getWorkerCode())) {
							// System.out.println("---------"+ovt.getWorker());
							worker.setWorkerCode(ovt.getWorker().getWorkerCode());
							// System.out.println(w);
							ProductWorker pw = pwDao.findWorkerByCode(ovt.getWorker().getWorkerCode());
							if (pw == null) {// 如果
								continue;
							}
							// pw = plist.get(0);// 取出用户
							String stime = ovt.getStartime();// 当前加班申请的开始时间
							String etime = ovt.getEndtime();// 当前加班申请的结束时间
							Long s = sd.parse(stime).getTime();
							Long e = sd.parse(etime).getTime();
							// System.out.println("异常"+check.getUpLine());
							Long u = sdf.parse(check.getUpLine()).getTime();// 当前打卡记录的开始时间
							Long d = 0L;
							if (check.getDownLine() != null) {
								d = sdf.parse(check.getDownLine()).getTime();// 当前打卡时间的结束时间
							} else if (check.getNowDate().equals(sf.format(new Date().getTime()))) {
								d = sdf.parse(CyUtil.getTime1()).getTime();
							} else {
								break;
							}
							Double workerPrice = pw.getWorkerPrice();// 正常班
							Double workerOvertimePay = pw.getWorkerOvertimePay();// 1.5倍
							Double workerWeekenPay = pw.getWorkerWeekenPay();// 2倍
							Double workerHolidayPay = pw.getWorkerHolidayPay();// 3倍
							Double nb = (double) ((e - s) * 1.0 / 1000 / 60 / 60);
							int wp = ovt.getWorkPay();
							Double count;
							if (s > u && e < d) {
								if (wp == 1) {
									count = nb * workerOvertimePay;
								} else if (wp == 2) {
									count = nb * workerWeekenPay;
								} else if (wp == 3) {
									count = nb * workerHolidayPay;
								} else {
									count = nb * workerPrice;
								}
								SumPrice1 += count;
							} else if (s < u && e > d) {
								Double mul1 = (double) (d - u) / 1000 / 60 / 60;
								Double num1;
								if (wp == 1) {
									num1 = mul1 * workerOvertimePay;
								} else if (wp == 2) {
									num1 = mul1 * workerWeekenPay;
								} else if (wp == 3) {
									num1 = mul1 * workerHolidayPay;
								} else {
									num1 = mul1 * workerPrice;
								}
								count = num1 + ((double) ((u - s) + (e - d)) / 1000 / 60 / 60) * workerPrice;
								SumPrice1 += count;
							} else if (s < u && e < d && e > u) {
								Double mul1 = (double) (e - u) / 1000 / 60 / 60;
								Double num1;
								if (wp == 1) {
									num1 = mul1 * workerOvertimePay;
								} else if (wp == 2) {
									num1 = mul1 * workerWeekenPay;
								} else if (wp == 3) {
									num1 = mul1 * workerHolidayPay;
								} else {
									num1 = mul1 * workerPrice;
								}
								count = num1 + ((double) ((d - e)) / 1000 / 60 / 60) * workerPrice;
								SumPrice1 += count;
							} else if (s > u && e > d && s < d) {
								Double mul1 = (double) (d - s) / 1000 / 60 / 60;
								Double num1;
								if (wp == 1) {
									num1 = mul1 * workerOvertimePay;
								} else if (wp == 2) {
									num1 = mul1 * workerWeekenPay;
								} else if (wp == 3) {
									num1 = mul1 * workerHolidayPay;
								} else {
									num1 = mul1 * workerPrice;
								}
								count = num1 + ((double) ((s - u)) / 1000 / 60 / 60) * workerPrice;
								SumPrice1 += count;
							} else {
								Double mul1 = (double) (e - s) / 1000 / 60 / 60;
								count = mul1 * workerPrice;
								SumPrice1 += count;
							}
							// System.out.println(SumPrice1);
							jlist.add(check);
							// zuList.remove(check);
						}
					}
				}
			}
		}
		System.out.println("加班的钱：" + SumPrice1 + "元");
		// System.out.println("组装"zuList);
		// System.out.println(jlist);
		int n = jlist.size();
		System.out.println("n=" + n);
		if (jlist != null && jlist.size() < 0) {
			for (int i = 0; i < n; i++) {
				zuList.remove(jlist.get(i));
			}
		}
		System.out.println("不加班的线：" + zuList);
		if (zuList != null && zuList.size() > 0) {
			for (NewCheck nck : zuList) {
				// System.out.println(nck);
				worker.setWorkerCode(nck.getWorker().getWorkerCode());
				System.out.println("组装人员:" + worker);
				ProductWorker pw = pwDao.findWorkerByCode(nck.getWorker().getWorkerCode());

				if (pw == null) {
					continue;
				}
				// worker = plist.get(0);
				// System.out.println(nck.getUpLine());
				// System.out.println(nck.getDownLine());
				Long u = sdf.parse(nck.getUpLine()).getTime();
				// System.out.println("上線:"+u);
				Long d = 0L;
				// System.out.println("下線:"+(sdf.parse(nck.getDownLine()).getTime()));
				// System.out.println(nck.getDownLine());
				if (nck.getDownLine() != null) {
					d = sdf.parse(nck.getDownLine()).getTime();
				} else if (nck.getNowDate().equals(sf.format(new Date().getTime()))) {
					d = sdf.parse(CyUtil.getTime1()).getTime();
				} else {
					continue;
				}

				Double count = ((double) (d - u) * 1.0 / 1000 / 60 / 60) * pw.getWorkerPrice();
				SumPrice2 += count;
			}
			System.out.println("SumPrice2:" + SumPrice2);
		}
		System.out.println(SumPrice2 + "元");
		Double cost = SumPrice1 + SumPrice2;
		if (cost.toString().substring(0, 1).equals("0")) {
			record.setAssemblyInput("0" + df.format(cost) + "元");
		} else {
			record.setAssemblyInput(df.format(cost) + "元");
		}

		/*
		 * 计算包装投入
		 */
		OverTime otp = new OverTime();
		List<OverTime> otplist = null;
		Double SumPrice3 = 0.0;
		Double SumPrice4 = 0.0;
		List<NewCheck> palist = new ArrayList<NewCheck>();
		if (packList != null && packList.size() > 0) {
			for (NewCheck check : packList) {
				otp.setCreatime(check.getNowDate());
				otplist = otDao.findOverTime(otp);

				if (otplist != null && otplist.size() > 0) {
					for (OverTime ovt : otplist) {
						if (check.getWorker().getWorkerCode().equals(ovt.getWorker().getWorkerCode())) {
							worker.setWorkerCode(ovt.getWorker().getWorkerCode());
							ProductWorker pw = pwDao.findWorkerByCode(ovt.getWorker().getWorkerCode());
							if (pw == null) {// 如果
								continue;
							}
							// worker = plist.get(0);// 取出用户
							String stime = ovt.getStartime();// 当前加班申请的开始时间
							String etime = ovt.getEndtime();// 当前加班申请的结束时间
							Long s = sd.parse(stime).getTime();
							Long e = sd.parse(etime).getTime();
							Long u = sdf.parse(check.getUpLine()).getTime();// 当前打卡记录的开始时间
							Long d = 0L;
							if (nc.getDownLine() != null) {
								d = sdf.parse(check.getDownLine()).getTime();// 当前打卡时间的结束时间
							} else if (check.getNowDate().equals(sf.format(new Date().getTime()))) {
								d = sdf.parse(CyUtil.getTime1()).getTime();
							} else {
								break;
							}
							Double workerPrice = pw.getWorkerPrice();// 正常班
							Double workerOvertimePay = pw.getWorkerOvertimePay();// 1.5倍
							Double workerWeekenPay = pw.getWorkerWeekenPay();// 2倍
							Double workerHolidayPay = pw.getWorkerHolidayPay();// 3倍
							Double nb = (double) ((e - s) * 1.0 / 1000 / 60 / 60);
							int wp = ovt.getWorkPay();
							Double count;
							if (s > u && e < d) {
								if (wp == 1) {
									count = nb * workerOvertimePay;
								} else if (wp == 2) {
									count = nb * workerWeekenPay;
								} else if (wp == 3) {
									count = nb * workerHolidayPay;
								} else {
									count = nb * workerPrice;
								}
								SumPrice3 += count;
							} else if (s < u && e > d) {
								Double mul1 = (double) (d - u) / 1000 / 60 / 60;
								Double num1;
								if (wp == 1) {
									num1 = mul1 * workerOvertimePay;
								} else if (wp == 2) {
									num1 = mul1 * workerWeekenPay;
								} else if (wp == 3) {
									num1 = mul1 * workerHolidayPay;
								} else {
									num1 = mul1 * workerPrice;
								}
								count = num1 + ((double) ((u - s) + (e - d)) / 1000 / 60 / 60) * workerPrice;
								SumPrice3 += count;
							} else if (s < u && e < d && e > u) {
								Double mul1 = (double) (e - u) / 1000 / 60 / 60;
								Double num1;
								if (wp == 1) {
									num1 = mul1 * workerOvertimePay;
								} else if (wp == 2) {
									num1 = mul1 * workerWeekenPay;
								} else if (wp == 3) {
									num1 = mul1 * workerHolidayPay;
								} else {
									num1 = mul1 * workerPrice;
								}
								count = num1 + ((double) ((d - e)) / 1000 / 60 / 60) * workerPrice;
								SumPrice3 += count;
							} else if (s > u && e > d && s < d) {
								Double mul1 = (double) (d - s) / 1000 / 60 / 60;
								Double num1;
								if (wp == 1) {
									num1 = mul1 * workerOvertimePay;
								} else if (wp == 2) {
									num1 = mul1 * workerWeekenPay;
								} else if (wp == 3) {
									num1 = mul1 * workerHolidayPay;
								} else {
									num1 = mul1 * workerPrice;
								}
								count = num1 + ((double) ((s - u)) / 1000 / 60 / 60) * workerPrice;
								SumPrice3 += count;
							} else {
								Double mul1 = (double) (e - s) / 1000 / 60 / 60;
								count = mul1 * workerPrice;
								SumPrice3 += count;
							}
							// zuList.remove(check);
							palist.add(check);
						}
					}
				}
			}
		}
		int m = palist.size();
		// System.out.println("m="+m);
		for (int i = 0; i < m; i++) {
			System.out.println(palist);
			packList.remove(palist.get(i));
		}
		System.out.println("包装线的元素个数:" + packList.size());

		List<NewCheck> list = new ArrayList<NewCheck>();
		for (int i = 0; i < packList.size(); i++) {
			if (packList.get(i).getDownLine() == null
					&& !packList.get(i).getNowDate().equals(sf.format(new Date().getTime()))) {
				continue;
			}
			list.add(packList.get(i));
		}
		System.out.println("筛选后的元素个数:" + list.size());
		if (list != null && list.size() > 0) {
			for (NewCheck nck : list) {
				// System.out.println("1111:"+nck);
				// System.out.println("人员"+nck.getWorker().getWorkerCode());
				worker.setWorkerCode(nck.getWorker().getWorkerCode());
				ProductWorker pw = pwDao.findWorkerByCode(nck.getWorker().getWorkerCode());
				System.out.println(pw);
				if (pw == null) {
					continue;
				}
				// worker = plist.get(0);

				Long u = sdf.parse(nck.getUpLine()).getTime();

				Long d = 0L;
				// System.out.println("当前系统时间:"+sf.format(new
				// Date().getTime()));
				// System.out.println("实际的时间"+nck.getNowDate());

				if (nck.getDownLine() == null) {
					d = sdf.parse(CyUtil.getTime1()).getTime();

				} else {
					d = sdf.parse(nck.getDownLine()).getTime();
				}

				System.out.println(nck);
				System.out.println("每段时间的时间差:" + ((double) ((d - u) * 1.0 / 1000 / 60 / 60)));
				Double count = ((double) ((d - u) * 1.0 / 1000 / 60 / 60)) * pw.getWorkerPrice();
				// System.out.println("单挑数据工价:"+count);
				SumPrice4 += count;
			}
			System.out.println("SumPrice4:" + SumPrice4);
		}
		Double costP = SumPrice3 + SumPrice4;
		if (costP.toString().substring(0, 1).equals("0")) {
			record.setPackagInput("0" + df.format(costP) + "元");
		} else {
			record.setPackagInput(df.format(costP) + "元");
		}

		if (record.getAssemblyInput() == null) {
			record.setAssemblyInput("");
		}
		if (record.getAssemblyOutput() == null) {
			record.setAssemblyOutput("");
		}
		if (record.getPackagInput() == null) {
			record.setPackageOutput("");
		}
		if (record.getPackageOutput() == null) {
			record.setPackageOutput("");
		}
		if (record.getIncome() == null) {
			record.setIncome("");
		}
		result.setData(gson.toJson(record));
		result.setState(1);
		result.setMsg("加載成功!");
		return result;
	}

	/*
	 * 产线计算成本收入
	 */
	@Override
	public CyResult loadAppline(WorkLine line) throws ParseException {
		Gson gson = new Gson();
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		SimpleDateFormat sd = new SimpleDateFormat("HH:mm");
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		DecimalFormat df = new DecimalFormat("#.00");
		CyResult result = new CyResult();
		NewCheck nc = new NewCheck();
		nc.setLine(line);
		List<NewCheck> checkList = newDao.findNewCheckByWorkerCode(nc);
		if (checkList == null || checkList.size() < 1) {
			result.setMsg("暂无生产记录！");
			result.setState(0);
			return result;
		}
		LineRecord record = new LineRecord();
		record.setLineCode(line.getLineCode());
		String workTimes = null;
		Long timeNum = 0L;
		for (NewCheck ncheck : checkList) {
			Long time = 0L;
			if (ncheck.getDownLine() != null) {
				time = CyUtil.parseTime(ncheck.getDownLine(), ncheck.getUpLine());

			} else if (ncheck.getDownLine() == null && ncheck.getNowDate().equals(CyUtil.getTime2())) {
				time = CyUtil.parseTime(CyUtil.getTime1(), ncheck.getUpLine());

			}
			timeNum += time;
		}

		Double timeStr = ((double) (timeNum * 1.0 / 1000 / 60 / 60));
		if (timeStr.toString().substring(0, 1).equals("0")) {
			workTimes = "0" + df.format(timeStr);
		} else {
			workTimes = df.format(timeStr);
		}
		record.setWorkTimes(workTimes + "小時");

		OverTime otp = new OverTime();
		List<OverTime> otplist = null;
		Double SumPrice3 = 0.0;
		Double SumPrice4 = 0.0;
		ProductWorker worker = new ProductWorker();
		List<NewCheck> palist = new ArrayList<NewCheck>();
		for (NewCheck check : checkList) {
			otp.setCreatime(check.getNowDate());
			otplist = otDao.findOverTime(otp);

			if (otplist != null && otplist.size() > 0) {
				for (OverTime ovt : otplist) {
					if (check.getWorker().getWorkerCode().equals(ovt.getWorker().getWorkerCode())) {
						worker.setWorkerCode(ovt.getWorker().getWorkerCode());
						ProductWorker pw = pwDao.findWorkerByCode(ovt.getWorker().getWorkerCode());
						if (pw == null) {// 如果
							continue;
						}
						// worker = plist.get(0);// 取出用户
						String stime = ovt.getStartime();// 当前加班申请的开始时间
						String etime = ovt.getEndtime();// 当前加班申请的结束时间
						Long s = sd.parse(stime).getTime();
						Long e = sd.parse(etime).getTime();
						Long u = sdf.parse(check.getUpLine()).getTime();// 当前打卡记录的开始时间
						Long d = 0L;
						if (nc.getDownLine() != null) {
							d = sdf.parse(check.getDownLine()).getTime();// 当前打卡时间的结束时间
						} else if (check.getNowDate().equals(sf.format(new Date().getTime()))) {
							d = sdf.parse(CyUtil.getTime1()).getTime();
						} else {
							break;
						}
						Double workerPrice = pw.getWorkerPrice();// 正常班
						Double workerOvertimePay = pw.getWorkerOvertimePay();// 1.5倍
						Double workerWeekenPay = pw.getWorkerWeekenPay();// 2倍
						Double workerHolidayPay = pw.getWorkerHolidayPay();// 3倍
						Double nb = (double) ((e - s) * 1.0 / 1000 / 60 / 60);
						int wp = ovt.getWorkPay();
						Double count;
						if (s > u && e < d) {
							if (wp == 1) {
								count = nb * workerOvertimePay;
							} else if (wp == 2) {
								count = nb * workerWeekenPay;
							} else if (wp == 3) {
								count = nb * workerHolidayPay;
							} else {
								count = nb * workerPrice;
							}
							SumPrice3 += count;
						} else if (s < u && e > d) {
							Double mul1 = (double) (d - u) / 1000 / 60 / 60;
							Double num1;
							if (wp == 1) {
								num1 = mul1 * workerOvertimePay;
							} else if (wp == 2) {
								num1 = mul1 * workerWeekenPay;
							} else if (wp == 3) {
								num1 = mul1 * workerHolidayPay;
							} else {
								num1 = mul1 * workerPrice;
							}
							count = num1 + ((double) ((u - s) + (e - d)) / 1000 / 60 / 60) * workerPrice;
							SumPrice3 += count;
						} else if (s < u && e < d && e > u) {
							Double mul1 = (double) (e - u) / 1000 / 60 / 60;
							Double num1;
							if (wp == 1) {
								num1 = mul1 * workerOvertimePay;
							} else if (wp == 2) {
								num1 = mul1 * workerWeekenPay;
							} else if (wp == 3) {
								num1 = mul1 * workerHolidayPay;
							} else {
								num1 = mul1 * workerPrice;
							}
							count = num1 + ((double) ((d - e)) / 1000 / 60 / 60) * workerPrice;
							SumPrice3 += count;
						} else if (s > u && e > d && s < d) {
							Double mul1 = (double) (d - s) / 1000 / 60 / 60;
							Double num1;
							if (wp == 1) {
								num1 = mul1 * workerOvertimePay;
							} else if (wp == 2) {
								num1 = mul1 * workerWeekenPay;
							} else if (wp == 3) {
								num1 = mul1 * workerHolidayPay;
							} else {
								num1 = mul1 * workerPrice;
							}
							count = num1 + ((double) ((s - u)) / 1000 / 60 / 60) * workerPrice;
							SumPrice3 += count;
						} else {
							Double mul1 = (double) (e - s) / 1000 / 60 / 60;
							count = mul1 * workerPrice;
							SumPrice3 += count;
						}
						palist.add(check);
					}
				}
			}

		}
		int m = palist.size();
		// System.out.println("m="+m);
		for (int i = 0; i < m; i++) {
			System.out.println(palist);
			checkList.remove(palist.get(i));
		}
		System.out.println("包装线的元素个数:" + checkList.size());

		List<NewCheck> list = new ArrayList<NewCheck>();
		for (int i = 0; i < checkList.size(); i++) {
			if (checkList.get(i).getDownLine() == null
					&& !checkList.get(i).getNowDate().equals(sf.format(new Date().getTime()))) {
				continue;
			}
			list.add(checkList.get(i));
		}
		System.out.println("筛选后的元素个数:" + list.size());
		if (list != null && list.size() > 0) {
			for (NewCheck nck : list) {
				
				worker.setWorkerCode(nck.getWorker().getWorkerCode());
				ProductWorker pw = pwDao.findWorkerByCode(nck.getWorker().getWorkerCode());
				System.out.println(pw);
				if (pw == null) {
					continue;
				}

				Long u = sdf.parse(nck.getUpLine()).getTime();

				Long d = 0L;
				

				if (nck.getDownLine() == null) {
					d = sdf.parse(CyUtil.getTime1()).getTime();

				} else {
					d = sdf.parse(nck.getDownLine()).getTime();
				}

				System.out.println(nck);
				System.out.println("每段时间的时间差:" + ((double) ((d - u) * 1.0 / 1000 / 60 / 60)));
				Double count = ((double) ((d - u) * 1.0 / 1000 / 60 / 60)) * pw.getWorkerPrice();

				SumPrice4 += count;
			}
			System.out.println("SumPrice4:" + SumPrice4);
		}
		Double costP = SumPrice3 + SumPrice4;
		if (costP.toString().substring(0, 1).equals("0")) {
			record.setIutput("0" + df.format(costP) + "元");
		} else {
			record.setIutput(df.format(costP) + "元");
		}
		if(record.getIutput()==null){
			record.setIutput("");
		}
		if(record.getOutput()==null){
			record.setOutput("");
		}
		if(record.getIncome()==null){
			record.setIncome("");
		}
		if(record.getWorkTimes()==null){
			record.setWorkTimes("");
		}
		result.setData(gson.toJson(record));
		result.setState(1);
		result.setMsg("数据加载成功!");
		return result;
	}

	@Override
	public CyResult loadApplineAndPname() {
		Gson gson=new Gson();
		CyResult result=new CyResult();
		List<String> lineList=newDao.findAllLineCode();
		List<ProductDetails> pnameList=newDao.findAllPname();
		result.setData1(gson.toJson(lineList));
		result.setData2(gson.toJson(pnameList));
		result.setState(1);
		result.setMsg("数据加载成功！");
		return result;
	}
	
	
	
	
	
	

}