package com.cy.service.daynote;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cy.dao.check.NewCheckDao;
import com.cy.dao.daynote.DayNoteDao;
import com.cy.dao.line.OverTimeDao;
import com.cy.dao.line.ProductWorkerDao;
import com.cy.domain.check.NewCheck;
import com.cy.domain.daynote.DayNote;
import com.cy.domain.line.OverTime;
import com.cy.domain.line.ProductWorker;
import com.cy.utils.CyResult;
import com.cy.utils.CyUtil;

@Service
public class DayNoteServiceImpl implements DayNoteService {

	@Resource
	private DayNoteDao noteDao;
	@Resource
	private NewCheckDao newDao;
	@Resource
	private OverTimeDao otDao;
	@Resource
	private ProductWorkerDao pwDao;

	@Override
	public CyResult loadDayNotes() throws ParseException {
		CyResult result = new CyResult();
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		SimpleDateFormat sd = new SimpleDateFormat("HH:mm");
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		DecimalFormat df = new DecimalFormat("#.00");
		NewCheck ck = new NewCheck();
		List<NewCheck> list = newDao.findNewCheckByWorkerCode(ck);
		HashSet<String> hs = new HashSet<String>();
		List<List<NewCheck>> finalList = new ArrayList<List<NewCheck>>();
		for (int i = 0; i < list.size(); i++) {
			hs.add(list.get(i).getPname() + "," + list.get(i).getLine().getLineCode() + "," + list.get(i).getNowDate());
		}
		for (String d : hs) {
			List<NewCheck> listNck = new ArrayList<NewCheck>();
			for (int j = 0; j < list.size(); j++) {
				if ((list.get(j).getPname() + "," + list.get(j).getLine().getLineCode() + ","
						+ list.get(j).getNowDate()).equals(d)) {
					listNck.add(list.get(j));
				}
			}
			finalList.add(listNck);

		}

		List<DayNote> dlist = new ArrayList<DayNote>();

		for (List<NewCheck> listOne : finalList) {

			List<NewCheck> newList = listOne;
			DayNote dnote = new DayNote();
			dnote.setWorkDate(listOne.get(0).getNowDate());// 日期
			dnote.setPname(listOne.get(0).getPname());// 产品
			dnote.setLineCode(listOne.get(0).getLine().getLineCode());// 产线
			Long sumtime = 0L;
			for (NewCheck nc : listOne) {
				if (nc.getDownLine() != null) {
					sumtime += CyUtil.parseTime(nc.getDownLine(), nc.getUpLine());
				} else if (nc.getNowDate().equals(CyUtil.getTime2())) {
					sumtime += CyUtil.parseTime(CyUtil.getTime1(), nc.getUpLine());
				}
			}
			Double dt = (double) (sumtime * 1.0 / 1000 / 60 / 60);
			if (dt.toString().substring(0, 1).equals("0")) {
				dnote.setWorkTimes("0" + df.format(dt) + "小时");// 工时
			} else {
				dnote.setWorkTimes(df.format(dt) + "小时");// 工时
			}
			//System.out.println(dnote);
			OverTime ovt = new OverTime();
			ovt.setCreatime(listOne.get(0).getNowDate());
			List<OverTime> ovtlist = otDao.findOverTime(ovt);// 查询出当天的加班申请
			// System.out.println(ovtlist);
			new ProductWorker();
			Double SumPrice1 = 0.0;
			Double SumPrice2 = 0.0;
			List<NewCheck> jlist = new ArrayList<NewCheck>();
			for (NewCheck nc : listOne) {
				for (OverTime ot : ovtlist) {
					if (ot.getWorker().getWorkerCode().equals(nc.getWorker().getWorkerCode())) {
						ProductWorker worker = pwDao.findWorkerByCode(ot.getWorker().getWorkerCode());
						if (worker == null) {
							continue;
						}

						String stime = ot.getStartime();
						String etime = ot.getEndtime();
						Long s = sd.parse(stime).getTime();
						Long e = sd.parse(etime).getTime();
						Long u = sdf.parse(nc.getUpLine()).getTime();
						Long d = 0L;
						if (nc.getDownLine() != null) {
							d = sdf.parse(nc.getDownLine()).getTime();
						} else if (nc.getNowDate().equals(sf.format(new Date().getTime()))) {
							d = sdf.parse(CyUtil.getTime1()).getTime();
						} else {
							break;
						}
						Double workerPrice = worker.getWorkerPrice();// 正常班
						Double workerOvertimePay = worker.getWorkerOvertimePay();// 1.5倍
						Double workerWeekenPay = worker.getWorkerWeekenPay();// 2倍
						Double workerHolidayPay = worker.getWorkerHolidayPay();// 3倍
						Double nb = (double) ((e - s) * 1.0 / 1000 / 60 / 60);
						int wp = ot.getWorkPay();
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
						jlist.add(nc);
						// newList.remove(nc)vfdxnvgbkjldfbnk ;
						System.out.println(nc);
					}

					
				}
				//System.out.println("SumPrice1:"+SumPrice1);
				int n = jlist.size();
				//System.out.println("n=" + n);
				if (jlist != null && jlist.size() < 0) {
					for (int i = 0; i < n; i++) {
						newList.remove(jlist.get(i));
					}
				}
				
				if (newList != null && newList.size() > 0) {
                    
					for (NewCheck nck : newList) {
						System.out.println(nck);
						// worker.setWorkerCode(nck.getWorker().getWorkerCode());
						ProductWorker worker = pwDao.findWorkerByCode(nck.getWorker().getWorkerCode());
						if (worker == null) {
							continue;
						}
						// worker=plist.get(0);
						System.out.println(nck.getUpLine());
						System.out.println(nck.getDownLine());
						Long u = sdf.parse(nck.getUpLine()).getTime();
						System.out.println(u);
						Long d = 0L;
						if (nck.getDownLine() != null) {
							d = sdf.parse(nck.getDownLine()).getTime();
						} else if (nck.getNowDate().equals(sf.format(new Date().getTime()))) {
							d = sdf.parse(CyUtil.getTime1()).getTime();
						} else {
							continue;
						}
						System.out.println(d);
						// System.out.println((d-u)*1.0/1000/60/60);
						Double count = ((double) (d - u) * 1.0 / 1000 / 60 / 60) * worker.getWorkerPrice();
						SumPrice2 += count;
						//System.out.println(SumPrice2);
						
					}
				}

				Double cost = SumPrice1 + SumPrice2;
				if (cost.toString().substring(0, 1).equals("0")) {
					dnote.setPcost("0" + df.format(cost) + "元");
				} else {
					dnote.setPcost(df.format(cost) + "元");
				}
             //System.out.println(dnote);
             //int i=1/0;
			}

			dlist.add(dnote);
		}

			
			/*
			 * 收入产出
			 */
			
			
			
			
			
			
		
	    

		result.setData(dlist);
		result.setState(1);
		result.setMsg("加载成功!");
		return result;

	}

}
