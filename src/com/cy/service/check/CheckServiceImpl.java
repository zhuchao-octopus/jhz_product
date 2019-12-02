package com.cy.service.check;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.w3c.dom.ranges.RangeException;

import com.cy.dao.check.CheckDao;
import com.cy.dao.check.NewCheckDao;
import com.cy.dao.line.ProductWorkerDao;
import com.cy.dao.line.WorkLineDao;
import com.cy.domain.check.CheckModel;
import com.cy.domain.check.NewCheck;
import com.cy.domain.line.ProductDetails;
import com.cy.domain.line.ProductWorker;
import com.cy.domain.line.WorkLine;
import com.cy.domain.line.WorkLocation;
import com.cy.utils.CyResult;
import com.cy.utils.CyUtil;
import com.google.gson.Gson;
@Service("checkService")
@Transactional
public  class CheckServiceImpl implements CheckService {
    
	@Resource
	private CheckDao checkDao;
	@Resource
	private WorkLineDao lineDao;
	@Resource
	private ProductWorkerDao workerDao;
	@Resource
	private NewCheckDao newDao;
	
	@Override
	public CyResult findCheckByLine(WorkLine line) {
		CyResult result=new CyResult();
		if(line.getLineCode()==null||line.getLineCode().trim().isEmpty()){
			result.setMsg("产线编码不能为空!");
			result.setState(0);
			return result;
		}
		List<WorkLine> list=lineDao.findLineByCode(line);
		if(list==null||list.size()<1){
			result.setMsg("产线编码无效!");
			result.setState(0);
			return result;
		}
		WorkLine lineCheck=checkDao.findObjectByLine(line);
		
		System.out.println(lineCheck);
		Gson gs=new Gson();
		String listcheck=gs.toJson(lineCheck);		
		result.setMsg("信息加载成功!");
		result.setState(1);
		result.setData(listcheck);
		return result;
	}


	


	@Override
	public CyResult checkDone(WorkLine line, ProductWorker worker,Integer status) throws Exception {
		System.out.println("-----------------");
		CyResult result=new CyResult();
		if(line.getLineCode()==null||line.getLineCode().trim().isEmpty()||worker.getWorkerCode()==null||
				worker.getWorkerCode().trim().isEmpty()){
			result.setMsg("产线编码或人员不能为空!");
			result.setState(7);
			return result;
		}
		List<WorkLine> wrl=lineDao.findLineByCode(line);
		System.out.println(wrl);
		if(wrl==null||wrl.size()<1){
			result.setMsg("未能查到相关产线，请确认！");
			result.setState(30);
			return result;
		}
		
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("lineCode", line.getLineCode());
		map.put("workerCode", worker.getWorkerCode());
		List<WorkLine> linelist=lineDao.findLineByCodeAndWorker(map);
		List<WorkLocation> list=checkDao.findWorkerByCode(line);
		System.out.println(list);
		
		Gson gs=new Gson();
		if(linelist==null||linelist.size()<1){
			result.setMsg("该产线未能查到相关人员,请确认!");
			List<WorkLocation> newList=new ArrayList<WorkLocation>();
			for(WorkLocation w:list){
				if(w.getWorker()!=null&&w.getWorker().getWid()!=null){
					newList.add(w);
				}
			}
			System.out.println(newList.size());
			
			List<CheckModel> lslist=new ArrayList<CheckModel>();
		    for(WorkLocation w:newList){
		    	List<CheckModel> clist=workerDao.findCheckByWid(w.getWorker()); 
		    	for(CheckModel c:clist){
		    		if(c.getWriteTime().equals(CyUtil.getTime2())){
		    			lslist.add(c);
		    		}
		    	}
		    }
		  
		    for(CheckModel model:lslist){
		    	 List<Long> strlist=new ArrayList<Long>();
					if(model.getUpTime1()!=null&&model.getDownTime1()!=null){
						Long lonTime=CyUtil.parseTime(model.getDownTime1(),model.getUpTime1());
						strlist.add(lonTime);
					}
					if(model.getUpTime2()!=null&&model.getDownTime2()!=null){
						Long lonTime=CyUtil.parseTime(model.getDownTime2(),model.getUpTime2());
						strlist.add(lonTime);
					}
					if(model.getUpTime3()!=null&&model.getDownTime3()!=null){
						Long lonTime=CyUtil.parseTime(model.getDownTime3(),model.getUpTime3());
						
						strlist.add(lonTime);
					}
					if(model.getUpTime4()!=null&&model.getDownTime4()!=null){
						Long lonTime=CyUtil.parseTime(model.getDownTime4(),model.getUpTime4());
						
						strlist.add(lonTime);
					}
					if(model.getUpTime5()!=null&&model.getDownTime5()!=null){
						Long lonTime=CyUtil.parseTime(model.getDownTime5(),model.getUpTime5());
						strlist.add(lonTime);
					}
					if(model.getUpTime6()!=null&&model.getDownTime6()!=null){
						Long lonTime=CyUtil.parseTime(model.getDownTime6(),model.getUpTime6());
						strlist.add(lonTime);
					}
					if(model.getUpTime7()!=null&&model.getDownTime7()!=null){
						Long lonTime=CyUtil.parseTime(model.getDownTime7(),model.getUpTime7());
					
						strlist.add(lonTime);
					}
					if(model.getUpTime8()!=null&&model.getDownTime8()!=null){
						Long lonTime=CyUtil.parseTime(model.getDownTime8(),model.getUpTime8());
					
						strlist.add(lonTime);
					}
					if(model.getUpTime9()!=null&&model.getDownTime9()!=null){
						Long lonTime=CyUtil.parseTime(model.getDownTime9(),model.getUpTime9());
					
						strlist.add(lonTime);
					}
					if(model.getUpTime10()!=null&&model.getDownTime10()!=null){
						Long lonTime=CyUtil.parseTime(model.getDownTime10(),model.getUpTime10());
			
						strlist.add(lonTime);
					}
					Long time=0L;
					for(int i=0;i<strlist.size();i++){
						time+=strlist.get(i);				
					}
					Long hour=time/1000/60/60;
					Long minutes=time/1000/60-(60*hour);
					Long second=time/1000-(60*minutes+3600*hour);
					model.setWorktime(hour+"h"+minutes+"m"+second+"s");
					model.getWorker().setObjThing(hour+"h"+minutes+"m"+second+"s");
					//int n=workerDao.updateWorker(model.getWorker());
				}
		   System.out.println(lslist);
		    
		   for(CheckModel model:lslist){ 
		       for(WorkLocation w:newList){
		    	   
		    	   System.out.println(model.getWorker().getWid());
		    	   System.out.println(w.getWorker().getWid());
		    	 
				  if(model.getWorker().getWid().equals(w.getWorker().getWid())){
					  w.getWorker().setObjThing(model.getWorktime());
				  }
			  }
		   }
			
		   for(WorkLocation w:newList){
			   if(w.getWorker().getObjThing()==null){
				   w.getWorker().setObjThing("");
			   }
		   }
			
			
			if(newList.size()>20){
				result.setData(gs.toJson(newList.subList(0, 20)));
				result.setData1(gs.toJson(newList.subList(20,newList.size())));
			}else{
				result.setData(gs.toJson(newList));
			}
			List<WorkLocation> locationList=new ArrayList<WorkLocation>();
			for (WorkLocation wl:newList) {
				if (wl.getLocationState()!=null && wl.getLocationState()==1) {
					locationList.add(wl);
				}
			}
			result.setData3(newList.size()+"@"+locationList.size()+"@"+(newList.size()-locationList.size()));
			
			result.setState(0);
			return result;
		}
		
		
		
		
		
		CheckModel model=new CheckModel();
		model.setWorker(worker);
		model.setWriteTime(CyUtil.getTime2());
		CheckModel mode=checkDao.findCheckByWid(model);
		List<ProductWorker> workerList=workerDao.findWorker(worker);
		worker=workerList.get(0);
		
		
		
		if(mode==null){
			if(status==1){
				
				//查询出当前产线下所有的 人员,工位，以及状态
				Map<String,Object> cmap=new HashMap<String,Object>();
				for(WorkLocation w:list){
					System.out.println(worker.getWorkerCode());
					System.out.println();
					if(w.getWorker()!=null&&worker.getWorkerCode().equals(w.getWorker().getWorkerCode())){
						cmap.put("locationState",1);
						cmap.put("sid", w.getSid());
						cmap.put("nowTime",CyUtil.getTime1());
						lineDao.updateLocation(cmap);
					}
				}
                model.setUpTime1(CyUtil.getTime1());
				model.setWorker(worker);
				int n=checkDao.addCheck(model);
				if(n!=1){
					throw new RuntimeException("打卡失败!");
				}
				result.setMsg("打卡成功！");
				list=checkDao.findWorkerByCode(line);
				List<WorkLocation> newList=new ArrayList<WorkLocation>();
				for(WorkLocation w:list){
					if(w.getWorker()!=null&&w.getWorker().getWid()!=null){
						newList.add(w);
					}
				}
				
				List<CheckModel> lslist=new ArrayList<CheckModel>();
			    for(WorkLocation w:newList){
			    	List<CheckModel> clist=workerDao.findCheckByWid(w.getWorker());
			    	for(CheckModel c:clist){
			    		if(c.getWriteTime().equals(CyUtil.getTime2())){
			    			lslist.add(c);
			    		}
			    	}
			    }
			  
			    for(CheckModel mod:lslist){
			    	 List<Long> strlist=new ArrayList<Long>();
						if(mod.getUpTime1()!=null&&mod.getDownTime1()!=null){
							Long lonTime=CyUtil.parseTime(mod.getDownTime1(),mod.getUpTime1());
							strlist.add(lonTime);
						}
						if(mod.getUpTime2()!=null&&mod.getDownTime2()!=null){
							Long lonTime=CyUtil.parseTime(mod.getDownTime2(),mod.getUpTime2());
							strlist.add(lonTime);
						}
						if(mod.getUpTime3()!=null&&mod.getDownTime3()!=null){
							Long lonTime=CyUtil.parseTime(mod.getDownTime3(),mod.getUpTime3());
							
							strlist.add(lonTime);
						}
						if(mod.getUpTime4()!=null&&mod.getDownTime4()!=null){
							Long lonTime=CyUtil.parseTime(mod.getDownTime4(),mod.getUpTime4());
							
							strlist.add(lonTime);
						}
						if(mod.getUpTime5()!=null&&mod.getDownTime5()!=null){
							Long lonTime=CyUtil.parseTime(mod.getDownTime5(),mod.getUpTime5());
							strlist.add(lonTime);
						}
						if(mod.getUpTime6()!=null&&mod.getDownTime6()!=null){
							Long lonTime=CyUtil.parseTime(mod.getDownTime6(),mod.getUpTime6());
							strlist.add(lonTime);
						}
						if(mod.getUpTime7()!=null&&mod.getDownTime7()!=null){
							Long lonTime=CyUtil.parseTime(mod.getDownTime7(),mod.getUpTime7());
						
							strlist.add(lonTime);
						}
						if(mod.getUpTime8()!=null&&mod.getDownTime8()!=null){
							Long lonTime=CyUtil.parseTime(mod.getDownTime8(),mod.getUpTime8());
						
							strlist.add(lonTime);
						}
						if(mod.getUpTime9()!=null&&mod.getDownTime9()!=null){
							Long lonTime=CyUtil.parseTime(mod.getDownTime9(),mod.getUpTime9());
						
							strlist.add(lonTime);
						}
						if(mod.getUpTime10()!=null&&mod.getDownTime10()!=null){
							Long lonTime=CyUtil.parseTime(mod.getDownTime10(),mod.getUpTime10());
				
							strlist.add(lonTime);
						}
						Long time=0L;
						for(int i=0;i<strlist.size();i++){
							time+=strlist.get(i);				
						}
						Long hour=time/1000/60/60;
						Long minutes=time/1000/60-(60*hour);
						Long second=time/1000-(60*minutes+3600*hour);
						mod.setWorktime(hour+"h"+minutes+"m"+second+"s");
						mod.getWorker().setObjThing(hour+"h"+minutes+"m"+second+"s");
						//int n=workerDao.updateWorker(model.getWorker());
					}
			   System.out.println(lslist);
			    
			   for(CheckModel mod:lslist){ 
			       for(WorkLocation w:newList){
			    	   
			    	   System.out.println(mod.getWorker().getWid());
			    	   System.out.println(w.getWorker().getWid());
			    	  System.out.println(mod.getWorker().getWid().equals(w.getWorker().getWid()));
					  if(mod.getWorker().getWid().equals(w.getWorker().getWid())){
						  w.getWorker().setObjThing(mod.getWorktime());
					  }
				  }
			   }
				
			   for(WorkLocation w:newList){
				   if(w.getWorker().getObjThing()==null){
					   w.getWorker().setObjThing("");
				   }
			   }
				
				
				
				
				if(newList.size()>20){
					result.setData(gs.toJson(newList.subList(0, 20)));
					result.setData1(gs.toJson(newList.subList(20,newList.size())));
				}else{
					result.setData(gs.toJson(newList));
				}
				List<WorkLocation> locationList=new ArrayList<WorkLocation>();
				for (WorkLocation wl:newList) {
					if (wl.getLocationState()!=null && wl.getLocationState()==1) {
						locationList.add(wl);
					}
				}
				result.setData3(newList.size()+"@"+locationList.size()+"@"+(newList.size()-locationList.size()));
				result.setData2(worker.getWorkerName());
				result.setState(1);
				return result;
			}
			result.setMsg("当天还未上线,无法下线!");
			list=checkDao.findWorkerByCode(line);
			List<WorkLocation> newList=new ArrayList<WorkLocation>();
			for(WorkLocation w:list){
				if(w.getWorker()!=null&&w.getWorker().getWid()!=null){
					newList.add(w);
				}
			}
			
			List<CheckModel> lslist=new ArrayList<CheckModel>();
		    for(WorkLocation w:newList){
		    	List<CheckModel> clist=workerDao.findCheckByWid(w.getWorker());
		    	for(CheckModel c:clist){
		    		if(c.getWriteTime().equals(CyUtil.getTime2())){
		    			lslist.add(c);
		    		}
		    	}
		    }
		  
		    for(CheckModel mod:lslist){
		    	 List<Long> strlist=new ArrayList<Long>();
					if(mod.getUpTime1()!=null&&mod.getDownTime1()!=null){
						Long lonTime=CyUtil.parseTime(mod.getDownTime1(),mod.getUpTime1());
						strlist.add(lonTime);
					}
					if(mod.getUpTime2()!=null&&mod.getDownTime2()!=null){
						Long lonTime=CyUtil.parseTime(mod.getDownTime2(),mod.getUpTime2());
						strlist.add(lonTime);
					}
					if(mod.getUpTime3()!=null&&mod.getDownTime3()!=null){
						Long lonTime=CyUtil.parseTime(mod.getDownTime3(),mod.getUpTime3());
						
						strlist.add(lonTime);
					}
					if(mod.getUpTime4()!=null&&mod.getDownTime4()!=null){
						Long lonTime=CyUtil.parseTime(mod.getDownTime4(),mod.getUpTime4());
						
						strlist.add(lonTime);
					}
					if(mod.getUpTime5()!=null&&mod.getDownTime5()!=null){
						Long lonTime=CyUtil.parseTime(mod.getDownTime5(),mod.getUpTime5());
						strlist.add(lonTime);
					}
					if(mod.getUpTime6()!=null&&mod.getDownTime6()!=null){
						Long lonTime=CyUtil.parseTime(mod.getDownTime6(),mod.getUpTime6());
						strlist.add(lonTime);
					}
					if(mod.getUpTime7()!=null&&mod.getDownTime7()!=null){
						Long lonTime=CyUtil.parseTime(mod.getDownTime7(),mod.getUpTime7());
					
						strlist.add(lonTime);
					}
					if(mod.getUpTime8()!=null&&mod.getDownTime8()!=null){
						Long lonTime=CyUtil.parseTime(mod.getDownTime8(),mod.getUpTime8());
					
						strlist.add(lonTime);
					}
					if(mod.getUpTime9()!=null&&mod.getDownTime9()!=null){
						Long lonTime=CyUtil.parseTime(mod.getDownTime9(),mod.getUpTime9());
					
						strlist.add(lonTime);
					}
					if(mod.getUpTime10()!=null&&mod.getDownTime10()!=null){
						Long lonTime=CyUtil.parseTime(mod.getDownTime10(),mod.getUpTime10());
			
						strlist.add(lonTime);
					}
					Long time=0L;
					for(int i=0;i<strlist.size();i++){
						time+=strlist.get(i);				
					}
					Long hour=time/1000/60/60;
					Long minutes=time/1000/60-(60*hour);
					Long second=time/1000-(60*minutes+3600*hour);
					mod.setWorktime(hour+"h"+minutes+"m"+second+"s");
					mod.getWorker().setObjThing(hour+"h"+minutes+"m"+second+"s");
					//int n=workerDao.updateWorker(model.getWorker());
				}
		   System.out.println(lslist);
		    
		   for(CheckModel mod:lslist){ 
		       for(WorkLocation w:newList){
		    	   System.out.println("**********************");
		    	   System.out.println(mod.getWorker().getWid());
		    	   System.out.println(w.getWorker().getWid());
		    	   System.out.println(mod.getWorker().getWid().equals(w.getWorker().getWid()));
				  if(mod.getWorker().getWid().equals(w.getWorker().getWid())){
					  w.getWorker().setObjThing(mod.getWorktime());
				  }
			  }
		   }
			
		   for(WorkLocation w:newList){
			   if(w.getWorker().getObjThing()==null){
				   w.getWorker().setObjThing("");
			   }
			   System.out.println(w);
		   }
			
			
			
			
			if(newList.size()>20){
				result.setData(gs.toJson(newList.subList(0, 20)));
				result.setData1(gs.toJson(newList.subList(20,newList.size())));
			}else{
				result.setData(gs.toJson(newList));
			}
			List<WorkLocation> locationList=new ArrayList<WorkLocation>();
			for (WorkLocation wl:newList) {
				if (wl.getLocationState()!=null && wl.getLocationState()==1) {
					locationList.add(wl);
				}
			}
			result.setData3(newList.size()+"@"+locationList.size()+"@"+(newList.size()-locationList.size()));
			result.setData2(worker.getWorkerName());
			result.setState(5);
			return result;
		}
		
		
		
		/*
		 * 上线
		 */
	    if(status==1){
	    	if(mode.getUpTime2()==null&&mode.getDownTime1()!=null){
	    		
	    		//查询出当前产线下所有的 人员,工位，以及状态
				Map<String,Object> cmap=new HashMap<String,Object>();
				for(WorkLocation w:list){
					if(w.getWorker()!=null&&worker.getWorkerCode().equals(w.getWorker().getWorkerCode())){
						cmap.put("locationState",1);
						cmap.put("sid", w.getSid());
						cmap.put("nowTime",CyUtil.getTime1());
						lineDao.updateLocation(cmap);
					}
				}
				model.setUpTime2(CyUtil.getTime1());
	    		model.setWorker(worker);
	    		int n=checkDao.updateCheck(model);
	    		if(n!=1){
					throw new RuntimeException("上线失败!");
				}

				result.setMsg("打卡成功！");
				list=checkDao.findWorkerByCode(line);
				List<WorkLocation> newList=new ArrayList<WorkLocation>();
				for(WorkLocation w:list){
					if(w.getWorker()!=null&&w.getWorker().getWid()!=null){
						newList.add(w); 
					}
				}
				
				List<CheckModel> lslist=new ArrayList<CheckModel>();
			    for(WorkLocation w:newList){
			    	List<CheckModel> clist=workerDao.findCheckByWid(w.getWorker());
			    	for(CheckModel c:clist){
			    		if(c.getWriteTime().equals(CyUtil.getTime2())){
			    			lslist.add(c);
			    		}
			    	}
			    }
			  
			    for(CheckModel mod:lslist){
			    	 List<Long> strlist=new ArrayList<Long>();
						if(mod.getUpTime1()!=null&&mod.getDownTime1()!=null){
							Long lonTime=CyUtil.parseTime(mod.getDownTime1(),mod.getUpTime1());
							strlist.add(lonTime);
						}
						if(mod.getUpTime2()!=null&&mod.getDownTime2()!=null){
							Long lonTime=CyUtil.parseTime(mod.getDownTime2(),mod.getUpTime2());
							strlist.add(lonTime);
						}
						if(mod.getUpTime3()!=null&&mod.getDownTime3()!=null){
							Long lonTime=CyUtil.parseTime(mod.getDownTime3(),mod.getUpTime3());
							
							strlist.add(lonTime);
						}
						if(mod.getUpTime4()!=null&&mod.getDownTime4()!=null){
							Long lonTime=CyUtil.parseTime(mod.getDownTime4(),mod.getUpTime4());
							
							strlist.add(lonTime);
						}
						if(mod.getUpTime5()!=null&&mod.getDownTime5()!=null){
							Long lonTime=CyUtil.parseTime(mod.getDownTime5(),mod.getUpTime5());
							strlist.add(lonTime);
						}
						if(mod.getUpTime6()!=null&&mod.getDownTime6()!=null){
							Long lonTime=CyUtil.parseTime(mod.getDownTime6(),mod.getUpTime6());
							strlist.add(lonTime);
						}
						if(mod.getUpTime7()!=null&&mod.getDownTime7()!=null){
							Long lonTime=CyUtil.parseTime(mod.getDownTime7(),mod.getUpTime7());
						
							strlist.add(lonTime);
						}
						if(mod.getUpTime8()!=null&&mod.getDownTime8()!=null){
							Long lonTime=CyUtil.parseTime(mod.getDownTime8(),mod.getUpTime8());
						
							strlist.add(lonTime);
						}
						if(mod.getUpTime9()!=null&&mod.getDownTime9()!=null){
							Long lonTime=CyUtil.parseTime(mod.getDownTime9(),mod.getUpTime9());
						
							strlist.add(lonTime);
						}
						if(mod.getUpTime10()!=null&&mod.getDownTime10()!=null){
							Long lonTime=CyUtil.parseTime(mod.getDownTime10(),mod.getUpTime10());
				
							strlist.add(lonTime);
						}
						Long time=0L;
						for(int i=0;i<strlist.size();i++){
							time+=strlist.get(i);				
						}
						Long hour=time/1000/60/60;
						Long minutes=time/1000/60-(60*hour);
						Long second=time/1000-(60*minutes+3600*hour);
						mod.setWorktime(hour+"h"+minutes+"m"+second+"s");
						mod.getWorker().setObjThing(hour+"h"+minutes+"m"+second+"s");
						//int n=workerDao.updateWorker(model.getWorker());
					}
			   System.out.println(lslist);
			    
			   for(CheckModel mod:lslist){ 
			       for(WorkLocation w:newList){
			    	   
			    	   System.out.println(mod.getWorker().getWid());
			    	   System.out.println(w.getWorker().getWid());
			    	 
					  if(mod.getWorker().getWid().equals(w.getWorker().getWid())){
						  w.getWorker().setObjThing(mod.getWorktime());
					  }
				  }
			   }
				
			   for(WorkLocation w:newList){
				   if(w.getWorker().getObjThing()==null){
					   w.getWorker().setObjThing("");
				   }
			   }
				
				
				if(newList.size()>20){
					result.setData(gs.toJson(newList.subList(0, 20)));
					result.setData1(gs.toJson(newList.subList(20,newList.size())));
				}else{
					result.setData(gs.toJson(newList));
				}
				List<WorkLocation> locationList=new ArrayList<WorkLocation>();
				for (WorkLocation wl:newList) {
					if (wl.getLocationState()!=null && wl.getLocationState()==1) {
						locationList.add(wl);
					}
				}
				result.setData3(newList.size()+"@"+locationList.size()+"@"+(newList.size()-locationList.size()));
				result.setData2(worker.getWorkerName());
				result.setState(1);
				return result;
	    	}else if(mode.getUpTime3()==null&&mode.getDownTime2()!=null){
	    		
	    		//查询出当前产线下所有的 人员,工位，以及状态
				Map<String,Object> cmap=new HashMap<String,Object>();
				for(WorkLocation w:list){
					if(w.getWorker()!=null&&worker.getWorkerCode().equals(w.getWorker().getWorkerCode())){
						cmap.put("locationState",1);
						cmap.put("sid", w.getSid());
						cmap.put("nowTime",CyUtil.getTime1());
						lineDao.updateLocation(cmap);
					}
				}
			
				model.setUpTime3(CyUtil.getTime1());
	    		model.setWorker(worker);
	    		int n=checkDao.updateCheck(model);
	    		if(n!=1){
					throw new RuntimeException("上线失败!");
				}
				
				
				result.setMsg("打卡成功！");
				list=checkDao.findWorkerByCode(line);
				List<WorkLocation> newList=new ArrayList<WorkLocation>();
				for(WorkLocation w:list){
					if(w.getWorker()!=null&&w.getWorker().getWid()!=null){
						newList.add(w);
					}
				}
				
				List<CheckModel> lslist=new ArrayList<CheckModel>();
			    for(WorkLocation w:newList){
			    	List<CheckModel> clist=workerDao.findCheckByWid(w.getWorker());
			    	for(CheckModel c:clist){
			    		if(c.getWriteTime().equals(CyUtil.getTime2())){
			    			lslist.add(c);
			    		}
			    	}
			    }
			  
			    for(CheckModel mod:lslist){
			    	 List<Long> strlist=new ArrayList<Long>();
						if(mod.getUpTime1()!=null&&mod.getDownTime1()!=null){
							Long lonTime=CyUtil.parseTime(mod.getDownTime1(),mod.getUpTime1());
							strlist.add(lonTime);
						}
						if(mod.getUpTime2()!=null&&mod.getDownTime2()!=null){
							Long lonTime=CyUtil.parseTime(mod.getDownTime2(),mod.getUpTime2());
							strlist.add(lonTime);
						}
						if(mod.getUpTime3()!=null&&mod.getDownTime3()!=null){
							Long lonTime=CyUtil.parseTime(mod.getDownTime3(),mod.getUpTime3());
							
							strlist.add(lonTime);
						}
						if(mod.getUpTime4()!=null&&mod.getDownTime4()!=null){
							Long lonTime=CyUtil.parseTime(mod.getDownTime4(),mod.getUpTime4());
							
							strlist.add(lonTime);
						}
						if(mod.getUpTime5()!=null&&mod.getDownTime5()!=null){
							Long lonTime=CyUtil.parseTime(mod.getDownTime5(),mod.getUpTime5());
							strlist.add(lonTime);
						}
						if(mod.getUpTime6()!=null&&mod.getDownTime6()!=null){
							Long lonTime=CyUtil.parseTime(mod.getDownTime6(),mod.getUpTime6());
							strlist.add(lonTime);
						}
						if(mod.getUpTime7()!=null&&mod.getDownTime7()!=null){
							Long lonTime=CyUtil.parseTime(mod.getDownTime7(),mod.getUpTime7());
						
							strlist.add(lonTime);
						}
						if(mod.getUpTime8()!=null&&mod.getDownTime8()!=null){
							Long lonTime=CyUtil.parseTime(mod.getDownTime8(),mod.getUpTime8());
						
							strlist.add(lonTime);
						}
						if(mod.getUpTime9()!=null&&mod.getDownTime9()!=null){
							Long lonTime=CyUtil.parseTime(mod.getDownTime9(),mod.getUpTime9());
						
							strlist.add(lonTime);
						}
						if(mod.getUpTime10()!=null&&mod.getDownTime10()!=null){
							Long lonTime=CyUtil.parseTime(mod.getDownTime10(),mod.getUpTime10());
				
							strlist.add(lonTime);
						}
						Long time=0L;
						for(int i=0;i<strlist.size();i++){
							time+=strlist.get(i);				
						}
						Long hour=time/1000/60/60;
						Long minutes=time/1000/60-(60*hour);
						Long second=time/1000-(60*minutes+3600*hour);
						mod.setWorktime(hour+"h"+minutes+"m"+second+"s");
						mod.getWorker().setObjThing(hour+"h"+minutes+"m"+second+"s");
						//int n=workerDao.updateWorker(model.getWorker());
					}
			   System.out.println(lslist);
			    
			   for(CheckModel mod:lslist){ 
			       for(WorkLocation w:newList){
			    	   
			    	   System.out.println(mod.getWorker().getWid());
			    	   System.out.println(w.getWorker().getWid());
			    	 
					  if(mod.getWorker().getWid().equals(w.getWorker().getWid())){
						  w.getWorker().setObjThing(mod.getWorktime());
					  }
				  }
			   }
				
			   for(WorkLocation w:newList){
				   if(w.getWorker().getObjThing()==null){
					   w.getWorker().setObjThing("");
				   }
			   }
				
				
				
				if(newList.size()>20){
					result.setData(gs.toJson(newList.subList(0, 20)));
					result.setData1(gs.toJson(newList.subList(20,newList.size())));
				}else{
					result.setData(gs.toJson(newList));
				}
				List<WorkLocation> locationList=new ArrayList<WorkLocation>();
				for (WorkLocation wl:newList) {
					if (wl.getLocationState()!=null && wl.getLocationState()==1) {
						locationList.add(wl);
					}
				}
				result.setData3(newList.size()+"@"+locationList.size()+"@"+(newList.size()-locationList.size()));
				result.setData2(worker.getWorkerName());
				result.setState(1);
				return result;
	    	}else if(mode.getUpTime4()==null&&mode.getDownTime3()!=null){
	    		
	    		//查询出当前产线下所有的 人员,工位，以及状态
				Map<String,Object> cmap=new HashMap<String,Object>();
				for(WorkLocation w:list){
					if(w.getWorker()!=null&&worker.getWorkerCode().equals(w.getWorker().getWorkerCode())){
						cmap.put("locationState",1);
						cmap.put("sid", w.getSid());
						cmap.put("nowTime",CyUtil.getTime1());
						lineDao.updateLocation(cmap);
					}
				}
				model.setUpTime4(CyUtil.getTime1());
	    		model.setWorker(worker);
	    		int n=checkDao.updateCheck(model);
	    		if(n!=1){
					throw new RuntimeException("上线失败!");
				}

				result.setMsg("打卡成功！");
				list=checkDao.findWorkerByCode(line);
				List<WorkLocation> newList=new ArrayList<WorkLocation>();
				for(WorkLocation w:list){
					if(w.getWorker()!=null&&w.getWorker().getWid()!=null){
						newList.add(w);
					}
				}
				
				List<CheckModel> lslist=new ArrayList<CheckModel>();
			    for(WorkLocation w:newList){
			    	List<CheckModel> clist=workerDao.findCheckByWid(w.getWorker());
			    	for(CheckModel c:clist){
			    		if(c.getWriteTime().equals(CyUtil.getTime2())){
			    			lslist.add(c);
			    		}
			    	}
			    }
			  
			    for(CheckModel mod:lslist){
			    	 List<Long> strlist=new ArrayList<Long>();
						if(mod.getUpTime1()!=null&&mod.getDownTime1()!=null){
							Long lonTime=CyUtil.parseTime(mod.getDownTime1(),mod.getUpTime1());
							strlist.add(lonTime);
						}
						if(mod.getUpTime2()!=null&&mod.getDownTime2()!=null){
							Long lonTime=CyUtil.parseTime(mod.getDownTime2(),mod.getUpTime2());
							strlist.add(lonTime);
						}
						if(mod.getUpTime3()!=null&&mod.getDownTime3()!=null){
							Long lonTime=CyUtil.parseTime(mod.getDownTime3(),mod.getUpTime3());
							
							strlist.add(lonTime);
						}
						if(mod.getUpTime4()!=null&&mod.getDownTime4()!=null){
							Long lonTime=CyUtil.parseTime(mod.getDownTime4(),mod.getUpTime4());
							
							strlist.add(lonTime);
						}
						if(mod.getUpTime5()!=null&&mod.getDownTime5()!=null){
							Long lonTime=CyUtil.parseTime(mod.getDownTime5(),mod.getUpTime5());
							strlist.add(lonTime);
						}
						if(mod.getUpTime6()!=null&&mod.getDownTime6()!=null){
							Long lonTime=CyUtil.parseTime(mod.getDownTime6(),mod.getUpTime6());
							strlist.add(lonTime);
						}
						if(mod.getUpTime7()!=null&&mod.getDownTime7()!=null){
							Long lonTime=CyUtil.parseTime(mod.getDownTime7(),mod.getUpTime7());
						
							strlist.add(lonTime);
						}
						if(mod.getUpTime8()!=null&&mod.getDownTime8()!=null){
							Long lonTime=CyUtil.parseTime(mod.getDownTime8(),mod.getUpTime8());
						
							strlist.add(lonTime);
						}
						if(mod.getUpTime9()!=null&&mod.getDownTime9()!=null){
							Long lonTime=CyUtil.parseTime(mod.getDownTime9(),mod.getUpTime9());
						
							strlist.add(lonTime);
						}
						if(mod.getUpTime10()!=null&&mod.getDownTime10()!=null){
							Long lonTime=CyUtil.parseTime(mod.getDownTime10(),mod.getUpTime10());
				
							strlist.add(lonTime);
						}
						Long time=0L;
						for(int i=0;i<strlist.size();i++){
							time+=strlist.get(i);				
						}
						Long hour=time/1000/60/60;
						Long minutes=time/1000/60-(60*hour);
						Long second=time/1000-(60*minutes+3600*hour);
						mod.setWorktime(hour+"h"+minutes+"m"+second+"s");
						mod.getWorker().setObjThing(hour+"h"+minutes+"m"+second+"s");
						//int n=workerDao.updateWorker(model.getWorker());
					}
			   System.out.println(lslist);
			    
			   for(CheckModel mod:lslist){ 
			       for(WorkLocation w:newList){
			    	   
			    	   System.out.println(mod.getWorker().getWid());
			    	   System.out.println(w.getWorker().getWid());
			    	 
					  if(mod.getWorker().getWid().equals(w.getWorker().getWid())){
						  w.getWorker().setObjThing(mod.getWorktime());
					  }
				  }
			   }
				
			   for(WorkLocation w:newList){
				   if(w.getWorker().getObjThing()==null){
					   w.getWorker().setObjThing("");
				   }
			   }
				
				
				if(newList.size()>20){
					result.setData(gs.toJson(newList.subList(0, 20)));
					result.setData1(gs.toJson(newList.subList(20,newList.size())));
				}else{
					result.setData(gs.toJson(newList));
				}
				List<WorkLocation> locationList=new ArrayList<WorkLocation>();
				for (WorkLocation wl:newList) {
					if (wl.getLocationState()!=null && wl.getLocationState()==1) {
						locationList.add(wl);
					}
				}
				result.setData3(newList.size()+"@"+locationList.size()+"@"+(newList.size()-locationList.size()));
				result.setData2(worker.getWorkerName());
				result.setState(1);
				return result;
	    	}else if(mode.getUpTime5()==null&&mode.getDownTime4()!=null){
	    		
	    		//查询出当前产线下所有的 人员,工位，以及状态
				Map<String,Object> cmap=new HashMap<String,Object>();
				for(WorkLocation w:list){
					if(w.getWorker()!=null&&worker.getWorkerCode().equals(w.getWorker().getWorkerCode())){
						cmap.put("locationState",1);
						cmap.put("sid", w.getSid());
						cmap.put("nowTime",CyUtil.getTime1());
						lineDao.updateLocation(cmap);
					}
				}
			
				model.setUpTime5(CyUtil.getTime1());
	    		model.setWorker(worker);
	    		int n=checkDao.updateCheck(model);
	    		if(n!=1){
					throw new RuntimeException("上线失败!");
				}
				
				
				result.setMsg("打卡成功！");
				list=checkDao.findWorkerByCode(line);
				List<WorkLocation> newList=new ArrayList<WorkLocation>();
				for(WorkLocation w:list){
					if(w.getWorker()!=null&&w.getWorker().getWid()!=null){
						newList.add(w);
					}
				}
				List<CheckModel> lslist=new ArrayList<CheckModel>();
			    for(WorkLocation w:newList){
			    	List<CheckModel> clist=workerDao.findCheckByWid(w.getWorker());
			    	for(CheckModel c:clist){
			    		if(c.getWriteTime().equals(CyUtil.getTime2())){
			    			lslist.add(c);
			    		}
			    	}
			    }
			  
			    for(CheckModel mod:lslist){
			    	 List<Long> strlist=new ArrayList<Long>();
						if(mod.getUpTime1()!=null&&mod.getDownTime1()!=null){
							Long lonTime=CyUtil.parseTime(mod.getDownTime1(),mod.getUpTime1());
							strlist.add(lonTime);
						}
						if(mod.getUpTime2()!=null&&mod.getDownTime2()!=null){
							Long lonTime=CyUtil.parseTime(mod.getDownTime2(),mod.getUpTime2());
							strlist.add(lonTime);
						}
						if(mod.getUpTime3()!=null&&mod.getDownTime3()!=null){
							Long lonTime=CyUtil.parseTime(mod.getDownTime3(),mod.getUpTime3());
							
							strlist.add(lonTime);
						}
						if(mod.getUpTime4()!=null&&mod.getDownTime4()!=null){
							Long lonTime=CyUtil.parseTime(mod.getDownTime4(),mod.getUpTime4());
							
							strlist.add(lonTime);
						}
						if(mod.getUpTime5()!=null&&mod.getDownTime5()!=null){
							Long lonTime=CyUtil.parseTime(mod.getDownTime5(),mod.getUpTime5());
							strlist.add(lonTime);
						}
						if(mod.getUpTime6()!=null&&mod.getDownTime6()!=null){
							Long lonTime=CyUtil.parseTime(mod.getDownTime6(),mod.getUpTime6());
							strlist.add(lonTime);
						}
						if(mod.getUpTime7()!=null&&mod.getDownTime7()!=null){
							Long lonTime=CyUtil.parseTime(mod.getDownTime7(),mod.getUpTime7());
						
							strlist.add(lonTime);
						}
						if(mod.getUpTime8()!=null&&mod.getDownTime8()!=null){
							Long lonTime=CyUtil.parseTime(mod.getDownTime8(),mod.getUpTime8());
						
							strlist.add(lonTime);
						}
						if(mod.getUpTime9()!=null&&mod.getDownTime9()!=null){
							Long lonTime=CyUtil.parseTime(mod.getDownTime9(),mod.getUpTime9());
						
							strlist.add(lonTime);
						}
						if(mod.getUpTime10()!=null&&mod.getDownTime10()!=null){
							Long lonTime=CyUtil.parseTime(mod.getDownTime10(),mod.getUpTime10());
				
							strlist.add(lonTime);
						}
						Long time=0L;
						for(int i=0;i<strlist.size();i++){
							time+=strlist.get(i);				
						}
						Long hour=time/1000/60/60;
						Long minutes=time/1000/60-(60*hour);
						Long second=time/1000-(60*minutes+3600*hour);
						mod.setWorktime(hour+"h"+minutes+"m"+second+"s");
						mod.getWorker().setObjThing(hour+"h"+minutes+"m"+second+"s");
						//int n=workerDao.updateWorker(model.getWorker());
					}
			   System.out.println(lslist);
			    
			   for(CheckModel mod:lslist){ 
			       for(WorkLocation w:newList){
			    	   
			    	   System.out.println(mod.getWorker().getWid());
			    	   System.out.println(w.getWorker().getWid());
			    	 
					  if(mod.getWorker().getWid().equals(w.getWorker().getWid())){
						  w.getWorker().setObjThing(mod.getWorktime());
					  }
				  }
			   }
				
			   for(WorkLocation w:newList){
				   if(w.getWorker().getObjThing()==null){
					   w.getWorker().setObjThing("");
				   }
			   }
				
				
				
				
				if(newList.size()>20){
					result.setData(gs.toJson(newList.subList(0, 20)));
					result.setData1(gs.toJson(newList.subList(20,newList.size())));
				}else{
					result.setData(gs.toJson(newList));
				}
				List<WorkLocation> locationList=new ArrayList<WorkLocation>();
				for (WorkLocation wl:newList) {
					if (wl.getLocationState()!=null && wl.getLocationState()==1) {
						locationList.add(wl);
					}
				}
				result.setData3(newList.size()+"@"+locationList.size()+"@"+(newList.size()-locationList.size()));
				result.setData2(worker.getWorkerName());
				result.setState(1);
				return result;
	    	}else if(mode.getUpTime6()==null&&mode.getDownTime5()!=null){
	    		
	    		//查询出当前产线下所有的 人员,工位，以及状态
				Map<String,Object> cmap=new HashMap<String,Object>();
				for(WorkLocation w:list){
					if(w.getWorker()!=null&&worker.getWorkerCode().equals(w.getWorker().getWorkerCode())){
						cmap.put("locationState",1);
						cmap.put("sid", w.getSid());
						cmap.put("nowTime",CyUtil.getTime1());
						lineDao.updateLocation(cmap);
					}
				}
			    
				model.setUpTime6(CyUtil.getTime1());
	    		model.setWorker(worker);
	    		int n=checkDao.updateCheck(model);
	    		if(n!=1){
					throw new RuntimeException("上线失败!");
				}
				
				
				
				result.setMsg("打卡成功！");
				list=checkDao.findWorkerByCode(line);
				List<WorkLocation> newList=new ArrayList<WorkLocation>();
				for(WorkLocation w:list){
					if(w.getWorker()!=null&&w.getWorker().getWid()!=null){
						newList.add(w);
					}
				}
				
				List<CheckModel> lslist=new ArrayList<CheckModel>();
			    for(WorkLocation w:newList){
			    	List<CheckModel> clist=workerDao.findCheckByWid(w.getWorker());
			    	for(CheckModel c:clist){
			    		if(c.getWriteTime().equals(CyUtil.getTime2())){
			    			lslist.add(c);
			    		}
			    	}
			    }
			  
			    for(CheckModel mod:lslist){
			    	 List<Long> strlist=new ArrayList<Long>();
						if(mod.getUpTime1()!=null&&mod.getDownTime1()!=null){
							Long lonTime=CyUtil.parseTime(mod.getDownTime1(),mod.getUpTime1());
							strlist.add(lonTime);
						}
						if(mod.getUpTime2()!=null&&mod.getDownTime2()!=null){
							Long lonTime=CyUtil.parseTime(mod.getDownTime2(),mod.getUpTime2());
							strlist.add(lonTime);
						}
						if(mod.getUpTime3()!=null&&mod.getDownTime3()!=null){
							Long lonTime=CyUtil.parseTime(mod.getDownTime3(),mod.getUpTime3());
							
							strlist.add(lonTime);
						}
						if(mod.getUpTime4()!=null&&mod.getDownTime4()!=null){
							Long lonTime=CyUtil.parseTime(mod.getDownTime4(),mod.getUpTime4());
							
							strlist.add(lonTime);
						}
						if(mod.getUpTime5()!=null&&mod.getDownTime5()!=null){
							Long lonTime=CyUtil.parseTime(mod.getDownTime5(),mod.getUpTime5());
							strlist.add(lonTime);
						}
						if(mod.getUpTime6()!=null&&mod.getDownTime6()!=null){
							Long lonTime=CyUtil.parseTime(mod.getDownTime6(),mod.getUpTime6());
							strlist.add(lonTime);
						}
						if(mod.getUpTime7()!=null&&mod.getDownTime7()!=null){
							Long lonTime=CyUtil.parseTime(mod.getDownTime7(),mod.getUpTime7());
						
							strlist.add(lonTime);
						}
						if(mod.getUpTime8()!=null&&mod.getDownTime8()!=null){
							Long lonTime=CyUtil.parseTime(mod.getDownTime8(),mod.getUpTime8());
						
							strlist.add(lonTime);
						}
						if(mod.getUpTime9()!=null&&mod.getDownTime9()!=null){
							Long lonTime=CyUtil.parseTime(mod.getDownTime9(),mod.getUpTime9());
						
							strlist.add(lonTime);
						}
						if(mod.getUpTime10()!=null&&mod.getDownTime10()!=null){
							Long lonTime=CyUtil.parseTime(mod.getDownTime10(),mod.getUpTime10());
				
							strlist.add(lonTime);
						}
						Long time=0L;
						for(int i=0;i<strlist.size();i++){
							time+=strlist.get(i);				
						}
						Long hour=time/1000/60/60;
						Long minutes=time/1000/60-(60*hour);
						Long second=time/1000-(60*minutes+3600*hour);
						mod.setWorktime(hour+"h"+minutes+"m"+second+"s");
						mod.getWorker().setObjThing(hour+"h"+minutes+"m"+second+"s");
						//int n=workerDao.updateWorker(model.getWorker());
					}
			   System.out.println(lslist);
			    
			   for(CheckModel mod:lslist){ 
			       for(WorkLocation w:newList){
			    	   
			    	   System.out.println(mod.getWorker().getWid());
			    	   System.out.println(w.getWorker().getWid());
			    	 
					  if(mod.getWorker().getWid().equals(w.getWorker().getWid())){
						  w.getWorker().setObjThing(mod.getWorktime());
					  }
				  }
			   }
				
			   for(WorkLocation w:newList){
				   if(w.getWorker().getObjThing()==null){
					   w.getWorker().setObjThing("");
				   }
			   }
				
				
				if(newList.size()>20){
					result.setData(gs.toJson(newList.subList(0, 20)));
					result.setData1(gs.toJson(newList.subList(20,newList.size())));
				}else{
					result.setData(gs.toJson(newList));
				}
				List<WorkLocation> locationList=new ArrayList<WorkLocation>();
				for (WorkLocation wl:newList) {
					if (wl.getLocationState()!=null && wl.getLocationState()==1) {
						locationList.add(wl);
					}
				}
				result.setData3(newList.size()+"@"+locationList.size()+"@"+(newList.size()-locationList.size()));
				result.setData2(worker.getWorkerName());
				result.setState(1);
				return result;
	    	}else if(mode.getUpTime7()==null&&mode.getDownTime6()!=null){
		    	
		    	//查询出当前产线下所有的 人员,工位，以及状态
				Map<String,Object> cmap=new HashMap<String,Object>();
				for(WorkLocation w:list){
					if(w.getWorker()!=null&&worker.getWorkerCode().equals(w.getWorker().getWorkerCode())){
						cmap.put("locationState",1);
						cmap.put("sid", w.getSid());
						cmap.put("nowTime",CyUtil.getTime1());
						lineDao.updateLocation(cmap);
					}
				}
			    
				model.setUpTime7(CyUtil.getTime1());
		    	model.setWorker(worker);
		    	int n=checkDao.updateCheck(model);
		    	if(n!=1){
					throw new RuntimeException("上线失败!");
				}
				
				
				result.setData2(worker.getWorkerName());
				result.setMsg("打卡成功！");
				list=checkDao.findWorkerByCode(line);
				List<WorkLocation> newList=new ArrayList<WorkLocation>();
				for(WorkLocation w:list){
					if(w.getWorker()!=null&&w.getWorker().getWid()!=null){
						newList.add(w);
					}
				}
				
				List<CheckModel> lslist=new ArrayList<CheckModel>();
			    for(WorkLocation w:newList){
			    	List<CheckModel> clist=workerDao.findCheckByWid(w.getWorker());
			    	for(CheckModel c:clist){
			    		if(c.getWriteTime().equals(CyUtil.getTime2())){
			    			lslist.add(c);
			    		}
			    	}
			    }
			  
			    for(CheckModel mod:lslist){
			    	 List<Long> strlist=new ArrayList<Long>();
						if(mod.getUpTime1()!=null&&mod.getDownTime1()!=null){
							Long lonTime=CyUtil.parseTime(mod.getDownTime1(),mod.getUpTime1());
							strlist.add(lonTime);
						}
						if(mod.getUpTime2()!=null&&mod.getDownTime2()!=null){
							Long lonTime=CyUtil.parseTime(mod.getDownTime2(),mod.getUpTime2());
							strlist.add(lonTime);
						}
						if(mod.getUpTime3()!=null&&mod.getDownTime3()!=null){
							Long lonTime=CyUtil.parseTime(mod.getDownTime3(),mod.getUpTime3());
							
							strlist.add(lonTime);
						}
						if(mod.getUpTime4()!=null&&mod.getDownTime4()!=null){
							Long lonTime=CyUtil.parseTime(mod.getDownTime4(),mod.getUpTime4());
							
							strlist.add(lonTime);
						}
						if(mod.getUpTime5()!=null&&mod.getDownTime5()!=null){
							Long lonTime=CyUtil.parseTime(mod.getDownTime5(),mod.getUpTime5());
							strlist.add(lonTime);
						}
						if(mod.getUpTime6()!=null&&mod.getDownTime6()!=null){
							Long lonTime=CyUtil.parseTime(mod.getDownTime6(),mod.getUpTime6());
							strlist.add(lonTime);
						}
						if(mod.getUpTime7()!=null&&mod.getDownTime7()!=null){
							Long lonTime=CyUtil.parseTime(mod.getDownTime7(),mod.getUpTime7());
						
							strlist.add(lonTime);
						}
						if(mod.getUpTime8()!=null&&mod.getDownTime8()!=null){
							Long lonTime=CyUtil.parseTime(mod.getDownTime8(),mod.getUpTime8());
						
							strlist.add(lonTime);
						}
						if(mod.getUpTime9()!=null&&mod.getDownTime9()!=null){
							Long lonTime=CyUtil.parseTime(mod.getDownTime9(),mod.getUpTime9());
						
							strlist.add(lonTime);
						}
						if(mod.getUpTime10()!=null&&mod.getDownTime10()!=null){
							Long lonTime=CyUtil.parseTime(mod.getDownTime10(),mod.getUpTime10());
				
							strlist.add(lonTime);
						}
						Long time=0L;
						for(int i=0;i<strlist.size();i++){
							time+=strlist.get(i);				
						}
						Long hour=time/1000/60/60;
						Long minutes=time/1000/60-(60*hour);
						Long second=time/1000-(60*minutes+3600*hour);
						mod.setWorktime(hour+"h"+minutes+"m"+second+"s");
						mod.getWorker().setObjThing(hour+"h"+minutes+"m"+second+"s");
						//int n=workerDao.updateWorker(model.getWorker());
					}
			   System.out.println(lslist);
			    
			   for(CheckModel mod:lslist){ 
			       for(WorkLocation w:newList){
			    	   
			    	   System.out.println(mod.getWorker().getWid());
			    	   System.out.println(w.getWorker().getWid());
			    	 
					  if(mod.getWorker().getWid().equals(w.getWorker().getWid())){
						  w.getWorker().setObjThing(mod.getWorktime());
					  }
				  }
			   }
				
			   for(WorkLocation w:newList){
				   if(w.getWorker().getObjThing()==null){
					   w.getWorker().setObjThing("");
				   }
			   }
				
				
				
				if(newList.size()>20){
					result.setData(gs.toJson(newList.subList(0, 20)));
					result.setData1(gs.toJson(newList.subList(20,newList.size())));
				}else{
					result.setData(gs.toJson(newList));
				}
				List<WorkLocation> locationList=new ArrayList<WorkLocation>();
				for (WorkLocation wl:newList) {
					if (wl.getLocationState()!=null && wl.getLocationState()==1) {
						locationList.add(wl);
					}
				}
				result.setData3(newList.size()+"@"+locationList.size()+"@"+(newList.size()-locationList.size()));
				result.setState(1);
				return result;
	    	}else if(mode.getUpTime8()==null&&mode.getDownTime7()!=null){
	    		
	    		//查询出当前产线下所有的 人员,工位，以及状态
				Map<String,Object> cmap=new HashMap<String,Object>();
				for(WorkLocation w:list){
					if(w.getWorker()!=null&&worker.getWorkerCode().equals(w.getWorker().getWorkerCode())){
						cmap.put("locationState",1);
						cmap.put("sid", w.getSid());
						cmap.put("nowTime",CyUtil.getTime1());
						lineDao.updateLocation(cmap);
					}
				}
			     
				model.setUpTime8(CyUtil.getTime1());
	    		model.setWorker(worker);
	    		int n=checkDao.updateCheck(model);
	    		if(n!=1){
					throw new RuntimeException("上线失败!");
				}
				
				
				result.setData2(worker.getWorkerName());
				result.setMsg("打卡成功！");
				list=checkDao.findWorkerByCode(line);
				List<WorkLocation> newList=new ArrayList<WorkLocation>();
				for(WorkLocation w:list){
					if(w.getWorker()!=null&&w.getWorker().getWid()!=null){
						newList.add(w);
					}
				}
				
				List<CheckModel> lslist=new ArrayList<CheckModel>();
			    for(WorkLocation w:newList){
			    	List<CheckModel> clist=workerDao.findCheckByWid(w.getWorker());
			    	for(CheckModel c:clist){
			    		if(c.getWriteTime().equals(CyUtil.getTime2())){
			    			lslist.add(c);
			    		}
			    	}
			    }
			  
			    for(CheckModel mod:lslist){
			    	 List<Long> strlist=new ArrayList<Long>();
						if(mod.getUpTime1()!=null&&mod.getDownTime1()!=null){
							Long lonTime=CyUtil.parseTime(mod.getDownTime1(),mod.getUpTime1());
							strlist.add(lonTime);
						}
						if(mod.getUpTime2()!=null&&mod.getDownTime2()!=null){
							Long lonTime=CyUtil.parseTime(mod.getDownTime2(),mod.getUpTime2());
							strlist.add(lonTime);
						}
						if(mod.getUpTime3()!=null&&mod.getDownTime3()!=null){
							Long lonTime=CyUtil.parseTime(mod.getDownTime3(),mod.getUpTime3());
							
							strlist.add(lonTime);
						}
						if(mod.getUpTime4()!=null&&mod.getDownTime4()!=null){
							Long lonTime=CyUtil.parseTime(mod.getDownTime4(),mod.getUpTime4());
							
							strlist.add(lonTime);
						}
						if(mod.getUpTime5()!=null&&mod.getDownTime5()!=null){
							Long lonTime=CyUtil.parseTime(mod.getDownTime5(),mod.getUpTime5());
							strlist.add(lonTime);
						}
						if(mod.getUpTime6()!=null&&mod.getDownTime6()!=null){
							Long lonTime=CyUtil.parseTime(mod.getDownTime6(),mod.getUpTime6());
							strlist.add(lonTime);
						}
						if(mod.getUpTime7()!=null&&mod.getDownTime7()!=null){
							Long lonTime=CyUtil.parseTime(mod.getDownTime7(),mod.getUpTime7());
						
							strlist.add(lonTime);
						}
						if(mod.getUpTime8()!=null&&mod.getDownTime8()!=null){
							Long lonTime=CyUtil.parseTime(mod.getDownTime8(),mod.getUpTime8());
						
							strlist.add(lonTime);
						}
						if(mod.getUpTime9()!=null&&mod.getDownTime9()!=null){
							Long lonTime=CyUtil.parseTime(mod.getDownTime9(),mod.getUpTime9());
						
							strlist.add(lonTime);
						}
						if(mod.getUpTime10()!=null&&mod.getDownTime10()!=null){
							Long lonTime=CyUtil.parseTime(mod.getDownTime10(),mod.getUpTime10());
				
							strlist.add(lonTime);
						}
						Long time=0L;
						for(int i=0;i<strlist.size();i++){
							time+=strlist.get(i);				
						}
						Long hour=time/1000/60/60;
						Long minutes=time/1000/60-(60*hour);
						Long second=time/1000-(60*minutes+3600*hour);
						mod.setWorktime(hour+"h"+minutes+"m"+second+"s");
						mod.getWorker().setObjThing(hour+"h"+minutes+"m"+second+"s");
						//int n=workerDao.updateWorker(model.getWorker());
					}
			   System.out.println(lslist);
			    
			   for(CheckModel mod:lslist){ 
			       for(WorkLocation w:newList){
			    	   
			    	   System.out.println(mod.getWorker().getWid());
			    	   System.out.println(w.getWorker().getWid());
			    	 
					  if(mod.getWorker().getWid().equals(w.getWorker().getWid())){
						  w.getWorker().setObjThing(mod.getWorktime());
					  }
				  }
			   }
				
			   for(WorkLocation w:newList){
				   if(w.getWorker().getObjThing()==null){
					   w.getWorker().setObjThing("");
				   }
			   }
				
				
				if(newList.size()>20){
					result.setData(gs.toJson(newList.subList(0, 20)));
					result.setData1(gs.toJson(newList.subList(20,newList.size())));
				}else{
					result.setData(gs.toJson(newList));
				}
				List<WorkLocation> locationList=new ArrayList<WorkLocation>();
				for (WorkLocation wl:newList) {
					if (wl.getLocationState()!=null && wl.getLocationState()==1) {
						locationList.add(wl);
					}
				}
				result.setData3(newList.size()+"@"+locationList.size()+"@"+(newList.size()-locationList.size()));
				result.setState(1);
				return result;
	    	}else if(mode.getUpTime9()==null&&mode.getDownTime8()!=null){
	    		
	    		//查询出当前产线下所有的 人员,工位，以及状态
				Map<String,Object> cmap=new HashMap<String,Object>();
				for(WorkLocation w:list){
					if(w.getWorker()!=null&&worker.getWorkerCode().equals(w.getWorker().getWorkerCode())){
						cmap.put("locationState",1);
						cmap.put("sid", w.getSid());
						cmap.put("nowTime",CyUtil.getTime1());
						lineDao.updateLocation(cmap);
					}
				}
			    
				model.setUpTime9(CyUtil.getTime1());
	    		model.setWorker(worker);
	    		int n=checkDao.updateCheck(model);
	    		if(n!=1){
					throw new RuntimeException("上线失败!");
				}
				
				
				result.setData2(worker.getWorkerName());
				result.setMsg("打卡成功！");
				list=checkDao.findWorkerByCode(line);
				List<WorkLocation> newList=new ArrayList<WorkLocation>();
				for(WorkLocation w:list){
					if(w.getWorker()!=null&&w.getWorker().getWid()!=null){
						newList.add(w);
					}
				}
				
				List<CheckModel> lslist=new ArrayList<CheckModel>();
			    for(WorkLocation w:newList){
			    	List<CheckModel> clist=workerDao.findCheckByWid(w.getWorker());
			    	for(CheckModel c:clist){
			    		if(c.getWriteTime().equals(CyUtil.getTime2())){
			    			lslist.add(c);
			    		}
			    	}
			    }
			  
			    for(CheckModel mod:lslist){
			    	 List<Long> strlist=new ArrayList<Long>();
						if(mod.getUpTime1()!=null&&mod.getDownTime1()!=null){
							Long lonTime=CyUtil.parseTime(mod.getDownTime1(),mod.getUpTime1());
							strlist.add(lonTime);
						}
						if(mod.getUpTime2()!=null&&mod.getDownTime2()!=null){
							Long lonTime=CyUtil.parseTime(mod.getDownTime2(),mod.getUpTime2());
							strlist.add(lonTime);
						}
						if(mod.getUpTime3()!=null&&mod.getDownTime3()!=null){
							Long lonTime=CyUtil.parseTime(mod.getDownTime3(),mod.getUpTime3());
							
							strlist.add(lonTime);
						}
						if(mod.getUpTime4()!=null&&mod.getDownTime4()!=null){
							Long lonTime=CyUtil.parseTime(mod.getDownTime4(),mod.getUpTime4());
							
							strlist.add(lonTime);
						}
						if(mod.getUpTime5()!=null&&mod.getDownTime5()!=null){
							Long lonTime=CyUtil.parseTime(mod.getDownTime5(),mod.getUpTime5());
							strlist.add(lonTime);
						}
						if(mod.getUpTime6()!=null&&mod.getDownTime6()!=null){
							Long lonTime=CyUtil.parseTime(mod.getDownTime6(),mod.getUpTime6());
							strlist.add(lonTime);
						}
						if(mod.getUpTime7()!=null&&mod.getDownTime7()!=null){
							Long lonTime=CyUtil.parseTime(mod.getDownTime7(),mod.getUpTime7());
						
							strlist.add(lonTime);
						}
						if(mod.getUpTime8()!=null&&mod.getDownTime8()!=null){
							Long lonTime=CyUtil.parseTime(mod.getDownTime8(),mod.getUpTime8());
						
							strlist.add(lonTime);
						}
						if(mod.getUpTime9()!=null&&mod.getDownTime9()!=null){
							Long lonTime=CyUtil.parseTime(mod.getDownTime9(),mod.getUpTime9());
						
							strlist.add(lonTime);
						}
						if(mod.getUpTime10()!=null&&mod.getDownTime10()!=null){
							Long lonTime=CyUtil.parseTime(mod.getDownTime10(),mod.getUpTime10());
				
							strlist.add(lonTime);
						}
						Long time=0L;
						for(int i=0;i<strlist.size();i++){
							time+=strlist.get(i);				
						}
						Long hour=time/1000/60/60;
						Long minutes=time/1000/60-(60*hour);
						Long second=time/1000-(60*minutes+3600*hour);
						mod.setWorktime(hour+"h"+minutes+"m"+second+"s");
						mod.getWorker().setObjThing(hour+"h"+minutes+"m"+second+"s");
						//int n=workerDao.updateWorker(model.getWorker());
					}
			   System.out.println(lslist);
			    
			   for(CheckModel mod:lslist){ 
			       for(WorkLocation w:newList){
			    	   
			    	   System.out.println(mod.getWorker().getWid());
			    	   System.out.println(w.getWorker().getWid());
			    	 
					  if(mod.getWorker().getWid().equals(w.getWorker().getWid())){
						  w.getWorker().setObjThing(mod.getWorktime());
					  }
				  }
			   }
				
			   for(WorkLocation w:newList){
				   if(w.getWorker().getObjThing()==null){
					   w.getWorker().setObjThing("");
				   }
			   }
				
				
				if(newList.size()>20){
					result.setData(gs.toJson(newList.subList(0, 20)));
					result.setData1(gs.toJson(newList.subList(20,newList.size())));
				}else{
					result.setData(gs.toJson(newList));
				}
				List<WorkLocation> locationList=new ArrayList<WorkLocation>();
				for (WorkLocation wl:newList) {
					if (wl.getLocationState()!=null && wl.getLocationState()==1) {
						locationList.add(wl);
					}
				}
				result.setData3(newList.size()+"@"+locationList.size()+"@"+(newList.size()-locationList.size()));
				result.setState(1);
				return result;
	    	}else if(mode.getUpTime10()==null&&mode.getDownTime9()!=null){
	    		
	    		//查询出当前产线下所有的 人员,工位，以及状态
				Map<String,Object> cmap=new HashMap<String,Object>();
				for(WorkLocation w:list){
					if(w.getWorker()!=null&&worker.getWorkerCode().equals(w.getWorker().getWorkerCode())){
						cmap.put("locationState",1);
						cmap.put("sid", w.getSid());
						cmap.put("nowTime",CyUtil.getTime1());
						lineDao.updateLocation(cmap);
					}
				}
			    
				model.setUpTime10(CyUtil.getTime1());
	    		model.setWorker(worker);
	    		int n=checkDao.updateCheck(model);
	    		if(n!=1){
					throw new RuntimeException("上线失败!");
				}
				
				
				result.setData2(worker.getWorkerName());
				result.setMsg("打卡成功！");
				list=checkDao.findWorkerByCode(line);
				List<WorkLocation> newList=new ArrayList<WorkLocation>();
				for(WorkLocation w:list){
					if(w.getWorker()!=null&&w.getWorker().getWid()!=null){
						newList.add(w);
					}
				}
				
				List<CheckModel> lslist=new ArrayList<CheckModel>();
			    for(WorkLocation w:newList){
			    	List<CheckModel> clist=workerDao.findCheckByWid(w.getWorker());
			    	for(CheckModel c:clist){
			    		if(c.getWriteTime().equals(CyUtil.getTime2())){
			    			lslist.add(c);
			    		}
			    	}
			    }
			  
			    for(CheckModel mod:lslist){
			    	 List<Long> strlist=new ArrayList<Long>();
						if(mod.getUpTime1()!=null&&mod.getDownTime1()!=null){
							Long lonTime=CyUtil.parseTime(mod.getDownTime1(),mod.getUpTime1());
							strlist.add(lonTime);
						}
						if(mod.getUpTime2()!=null&&mod.getDownTime2()!=null){
							Long lonTime=CyUtil.parseTime(mod.getDownTime2(),mod.getUpTime2());
							strlist.add(lonTime);
						}
						if(mod.getUpTime3()!=null&&mod.getDownTime3()!=null){
							Long lonTime=CyUtil.parseTime(mod.getDownTime3(),mod.getUpTime3());
							
							strlist.add(lonTime);
						}
						if(mod.getUpTime4()!=null&&mod.getDownTime4()!=null){
							Long lonTime=CyUtil.parseTime(mod.getDownTime4(),mod.getUpTime4());
							
							strlist.add(lonTime);
						}
						if(mod.getUpTime5()!=null&&mod.getDownTime5()!=null){
							Long lonTime=CyUtil.parseTime(mod.getDownTime5(),mod.getUpTime5());
							strlist.add(lonTime);
						}
						if(mod.getUpTime6()!=null&&mod.getDownTime6()!=null){
							Long lonTime=CyUtil.parseTime(mod.getDownTime6(),mod.getUpTime6());
							strlist.add(lonTime);
						}
						if(mod.getUpTime7()!=null&&mod.getDownTime7()!=null){
							Long lonTime=CyUtil.parseTime(mod.getDownTime7(),mod.getUpTime7());
						
							strlist.add(lonTime);
						}
						if(mod.getUpTime8()!=null&&mod.getDownTime8()!=null){
							Long lonTime=CyUtil.parseTime(mod.getDownTime8(),mod.getUpTime8());
						
							strlist.add(lonTime);
						}
						if(mod.getUpTime9()!=null&&mod.getDownTime9()!=null){
							Long lonTime=CyUtil.parseTime(mod.getDownTime9(),mod.getUpTime9());
						
							strlist.add(lonTime);
						}
						if(mod.getUpTime10()!=null&&mod.getDownTime10()!=null){
							Long lonTime=CyUtil.parseTime(mod.getDownTime10(),mod.getUpTime10());
				
							strlist.add(lonTime);
						}
						Long time=0L;
						for(int i=0;i<strlist.size();i++){
							time+=strlist.get(i);				
						}
						Long hour=time/1000/60/60;
						Long minutes=time/1000/60-(60*hour);
						Long second=time/1000-(60*minutes+3600*hour);
						mod.setWorktime(hour+"h"+minutes+"m"+second+"s");
						mod.getWorker().setObjThing(hour+"h"+minutes+"m"+second+"s");
						//int n=workerDao.updateWorker(model.getWorker());
					}
			   System.out.println(lslist);
			    
			   for(CheckModel mod:lslist){ 
			       for(WorkLocation w:newList){
			    	   
			    	   System.out.println(mod.getWorker().getWid());
			    	   System.out.println(w.getWorker().getWid());
			    	 
					  if(mod.getWorker().getWid().equals(w.getWorker().getWid())){
						  w.getWorker().setObjThing(mod.getWorktime());
					  }
				  }
			   }
				
			   for(WorkLocation w:newList){
				   if(w.getWorker().getObjThing()==null){
					   w.getWorker().setObjThing("");
				   }
			   }
				
				
				
				
				if(newList.size()>20){
					result.setData(gs.toJson(newList.subList(0, 20)));
					result.setData1(gs.toJson(newList.subList(20,newList.size())));
				}else{
					result.setData(gs.toJson(newList));
				}
				List<WorkLocation> locationList=new ArrayList<WorkLocation>();
				for (WorkLocation wl:newList) {
					if (wl.getLocationState()!=null && wl.getLocationState()==1) {
						locationList.add(wl);
					}
				}
				result.setData3(newList.size()+"@"+locationList.size()+"@"+(newList.size()-locationList.size()));
				result.setState(1);
				return result;
	    	}else{
				result.setMsg("请先下线!");
				list=checkDao.findWorkerByCode(line);
				List<WorkLocation> newList=new ArrayList<WorkLocation>();
				for(WorkLocation w:list){
					if(w.getWorker()!=null&&w.getWorker().getWid()!=null){
						newList.add(w);
					}
				}
				
				List<CheckModel> lslist=new ArrayList<CheckModel>();
			    for(WorkLocation w:newList){
			    	List<CheckModel> clist=workerDao.findCheckByWid(w.getWorker());
			    	for(CheckModel c:clist){
			    		if(c.getWriteTime().equals(CyUtil.getTime2())){
			    			lslist.add(c);
			    		}
			    	}
			    }
			  
			    for(CheckModel mod:lslist){
			    	 List<Long> strlist=new ArrayList<Long>();
						if(mod.getUpTime1()!=null&&mod.getDownTime1()!=null){
							Long lonTime=CyUtil.parseTime(mod.getDownTime1(),mod.getUpTime1());
							strlist.add(lonTime);
						}
						if(mod.getUpTime2()!=null&&mod.getDownTime2()!=null){
							Long lonTime=CyUtil.parseTime(mod.getDownTime2(),mod.getUpTime2());
							strlist.add(lonTime);
						}
						if(mod.getUpTime3()!=null&&mod.getDownTime3()!=null){
							Long lonTime=CyUtil.parseTime(mod.getDownTime3(),mod.getUpTime3());
							
							strlist.add(lonTime);
						}
						if(mod.getUpTime4()!=null&&mod.getDownTime4()!=null){
							Long lonTime=CyUtil.parseTime(mod.getDownTime4(),mod.getUpTime4());
							
							strlist.add(lonTime);
						}
						if(mod.getUpTime5()!=null&&mod.getDownTime5()!=null){
							Long lonTime=CyUtil.parseTime(mod.getDownTime5(),mod.getUpTime5());
							strlist.add(lonTime);
						}
						if(mod.getUpTime6()!=null&&mod.getDownTime6()!=null){
							Long lonTime=CyUtil.parseTime(mod.getDownTime6(),mod.getUpTime6());
							strlist.add(lonTime);
						}
						if(mod.getUpTime7()!=null&&mod.getDownTime7()!=null){
							Long lonTime=CyUtil.parseTime(mod.getDownTime7(),mod.getUpTime7());
						
							strlist.add(lonTime);
						}
						if(mod.getUpTime8()!=null&&mod.getDownTime8()!=null){
							Long lonTime=CyUtil.parseTime(mod.getDownTime8(),mod.getUpTime8());
						
							strlist.add(lonTime);
						}
						if(mod.getUpTime9()!=null&&mod.getDownTime9()!=null){
							Long lonTime=CyUtil.parseTime(mod.getDownTime9(),mod.getUpTime9());
						
							strlist.add(lonTime);
						}
						if(mod.getUpTime10()!=null&&mod.getDownTime10()!=null){
							Long lonTime=CyUtil.parseTime(mod.getDownTime10(),mod.getUpTime10());
				
							strlist.add(lonTime);
						}
						Long time=0L;
						for(int i=0;i<strlist.size();i++){
							time+=strlist.get(i);				
						}
						Long hour=time/1000/60/60;
						Long minutes=time/1000/60-(60*hour);
						Long second=time/1000-(60*minutes+3600*hour);
						mod.setWorktime(hour+"h"+minutes+"m"+second+"s");
						mod.getWorker().setObjThing(hour+"h"+minutes+"m"+second+"s");
						//int n=workerDao.updateWorker(model.getWorker());
					}
			   System.out.println(lslist);
			    
			   for(CheckModel mod:lslist){ 
			       for(WorkLocation w:newList){
			    	   
			    	   System.out.println(mod.getWorker().getWid());
			    	   System.out.println(w.getWorker().getWid());
			    	 
					  if(mod.getWorker().getWid().equals(w.getWorker().getWid())){
						  w.getWorker().setObjThing(mod.getWorktime());
					  }
				  }
			   }
				
			   for(WorkLocation w:newList){
				   if(w.getWorker().getObjThing()==null){
					   w.getWorker().setObjThing("");
				   }
			   }
				
				
				if(newList.size()>20){
					result.setData(gs.toJson(newList.subList(0, 20)));
					result.setData1(gs.toJson(newList.subList(20,newList.size())));
				}else{
					result.setData(gs.toJson(newList));
				}
				List<WorkLocation> locationList=new ArrayList<WorkLocation>();
				for (WorkLocation wl:newList) {
					if (wl.getLocationState()!=null && wl.getLocationState()==1) {
						locationList.add(wl);
					}
				}
				result.setData3(newList.size()+"@"+locationList.size()+"@"+(newList.size()-locationList.size()));
				result.setData2(worker.getWorkerName());
				result.setState(2);
				return result;
			
	    	}
	    	
	    }
	    	/*
	    	 * 下线
	    	 */
	    	if(status==0){
	    		if(mode.getDownTime1()==null){
	    			System.out.println("第一次下线");
	    			
	    			Map<String,Object> cmap=new HashMap<String,Object>();
					for(WorkLocation w:list){
						if(w.getWorker()!=null&&worker.getWorkerCode().equals(w.getWorker().getWorkerCode())){
							cmap.put("locationState",0);
							cmap.put("sid", w.getSid());
							cmap.put("nowTime",CyUtil.getTime1());
							lineDao.updateLocation(cmap);
						}
					}
					model.setDownTime1(CyUtil.getTime1());
	    			model.setWorker(worker);
	    			int n=checkDao.updateCheck(model);
	    			System.out.println(n);
	    			if(n!=1){
	    				throw new RuntimeException("下线失败!");
	    			}
					
					result.setMsg("打卡成功！");
					list=checkDao.findWorkerByCode(line);
					List<WorkLocation> newList=new ArrayList<WorkLocation>();
					for(WorkLocation w:list){
						if(w.getWorker()!=null&&w.getWorker().getWid()!=null){
							newList.add(w);
						}
					}
					
					List<CheckModel> lslist=new ArrayList<CheckModel>();
				    for(WorkLocation w:newList){
				    	List<CheckModel> clist=workerDao.findCheckByWid(w.getWorker());
				    	for(CheckModel c:clist){
				    		if(c.getWriteTime().equals(CyUtil.getTime2())){
				    			lslist.add(c);
				    		}
				    	}
				    }
				  
				    for(CheckModel mod:lslist){
				    	 List<Long> strlist=new ArrayList<Long>();
							if(mod.getUpTime1()!=null&&mod.getDownTime1()!=null){
								Long lonTime=CyUtil.parseTime(mod.getDownTime1(),mod.getUpTime1());
								strlist.add(lonTime);
							}
							if(mod.getUpTime2()!=null&&mod.getDownTime2()!=null){
								Long lonTime=CyUtil.parseTime(mod.getDownTime2(),mod.getUpTime2());
								strlist.add(lonTime);
							}
							if(mod.getUpTime3()!=null&&mod.getDownTime3()!=null){
								Long lonTime=CyUtil.parseTime(mod.getDownTime3(),mod.getUpTime3());
								
								strlist.add(lonTime);
							}
							if(mod.getUpTime4()!=null&&mod.getDownTime4()!=null){
								Long lonTime=CyUtil.parseTime(mod.getDownTime4(),mod.getUpTime4());
								
								strlist.add(lonTime);
							}
							if(mod.getUpTime5()!=null&&mod.getDownTime5()!=null){
								Long lonTime=CyUtil.parseTime(mod.getDownTime5(),mod.getUpTime5());
								strlist.add(lonTime);
							}
							if(mod.getUpTime6()!=null&&mod.getDownTime6()!=null){
								Long lonTime=CyUtil.parseTime(mod.getDownTime6(),mod.getUpTime6());
								strlist.add(lonTime);
							}
							if(mod.getUpTime7()!=null&&mod.getDownTime7()!=null){
								Long lonTime=CyUtil.parseTime(mod.getDownTime7(),mod.getUpTime7());
							
								strlist.add(lonTime);
							}
							if(mod.getUpTime8()!=null&&mod.getDownTime8()!=null){
								Long lonTime=CyUtil.parseTime(mod.getDownTime8(),mod.getUpTime8());
							
								strlist.add(lonTime);
							}
							if(mod.getUpTime9()!=null&&mod.getDownTime9()!=null){
								Long lonTime=CyUtil.parseTime(mod.getDownTime9(),mod.getUpTime9());
							
								strlist.add(lonTime);
							}
							if(mod.getUpTime10()!=null&&mod.getDownTime10()!=null){
								Long lonTime=CyUtil.parseTime(mod.getDownTime10(),mod.getUpTime10());
					
								strlist.add(lonTime);
							}
							Long time=0L;
							for(int i=0;i<strlist.size();i++){
								time+=strlist.get(i);				
							}
							Long hour=time/1000/60/60;
							Long minutes=time/1000/60-(60*hour);
							Long second=time/1000-(60*minutes+3600*hour);
							mod.setWorktime(hour+"h"+minutes+"m"+second+"s");
							mod.getWorker().setObjThing(hour+"h"+minutes+"m"+second+"s");
							//int n=workerDao.updateWorker(model.getWorker());
						}
				   System.out.println(lslist);
				    
				   for(CheckModel mod:lslist){ 
				       for(WorkLocation w:newList){
				    	   
				    	   System.out.println(mod.getWorker().getWid());
				    	   System.out.println(w.getWorker().getWid());
				    	 
						  if(mod.getWorker().getWid().equals(w.getWorker().getWid())){
							  w.getWorker().setObjThing(mod.getWorktime());
						  }
					  }
				   }
					
				   for(WorkLocation w:newList){
					   if(w.getWorker().getObjThing()==null){
						   w.getWorker().setObjThing("");
					   }
				   }
					
					
					
					if(newList.size()>20){
						result.setData(gs.toJson(newList.subList(0, 20)));
						result.setData1(gs.toJson(newList.subList(20,newList.size())));
					}else{
						result.setData(gs.toJson(newList));
					}
					List<WorkLocation> locationList=new ArrayList<WorkLocation>();
					for (WorkLocation wl:newList) {
						if (wl.getLocationState()!=null && wl.getLocationState()==1) {
							locationList.add(wl);
						}
					}
					result.setData3(newList.size()+"@"+locationList.size()+"@"+(newList.size()-locationList.size()));
					result.setData2(worker.getWorkerName());
					result.setState(1);
					return result;
	    		}else if(mode.getUpTime2()!=null&&mode.getDownTime2()==null){
	    			System.out.println("第二次下线");
	    			
	    			Map<String,Object> cmap=new HashMap<String,Object>();
					for(WorkLocation w:list){
						if(w.getWorker()!=null&&worker.getWorkerCode().equals(w.getWorker().getWorkerCode())){
							cmap.put("locationState",0);
							cmap.put("sid", w.getSid());
							cmap.put("nowTime",CyUtil.getTime1());
							lineDao.updateLocation(cmap);
						}
					}
					
					
					
					
					model.setDownTime2(CyUtil.getTime1());
	    			model.setWorker(worker);
	    			int n=checkDao.updateCheck(model);
	    			if(n!=1){
	    				throw new RuntimeException("下线失败!");
	    			}
					
					result.setMsg("打卡成功！");
					list=checkDao.findWorkerByCode(line);
					List<WorkLocation> newList=new ArrayList<WorkLocation>();
					for(WorkLocation w:list){
						if(w.getWorker()!=null&&w.getWorker().getWid()!=null){
							newList.add(w);
						}
					}
					List<CheckModel> lslist=new ArrayList<CheckModel>();
				    for(WorkLocation w:newList){
				    	List<CheckModel> clist=workerDao.findCheckByWid(w.getWorker());
				    	for(CheckModel c:clist){
				    		if(c.getWriteTime().equals(CyUtil.getTime2())){
				    			lslist.add(c);
				    		}
				    	}
				    }
				  
				    for(CheckModel mod:lslist){
				    	 List<Long> strlist=new ArrayList<Long>();
							if(mod.getUpTime1()!=null&&mod.getDownTime1()!=null){
								Long lonTime=CyUtil.parseTime(mod.getDownTime1(),mod.getUpTime1());
								strlist.add(lonTime);
							}
							if(mod.getUpTime2()!=null&&mod.getDownTime2()!=null){
								Long lonTime=CyUtil.parseTime(mod.getDownTime2(),mod.getUpTime2());
								strlist.add(lonTime);
							}
							if(mod.getUpTime3()!=null&&mod.getDownTime3()!=null){
								Long lonTime=CyUtil.parseTime(mod.getDownTime3(),mod.getUpTime3());
								
								strlist.add(lonTime);
							}
							if(mod.getUpTime4()!=null&&mod.getDownTime4()!=null){
								Long lonTime=CyUtil.parseTime(mod.getDownTime4(),mod.getUpTime4());
								
								strlist.add(lonTime);
							}
							if(mod.getUpTime5()!=null&&mod.getDownTime5()!=null){
								Long lonTime=CyUtil.parseTime(mod.getDownTime5(),mod.getUpTime5());
								strlist.add(lonTime);
							}
							if(mod.getUpTime6()!=null&&mod.getDownTime6()!=null){
								Long lonTime=CyUtil.parseTime(mod.getDownTime6(),mod.getUpTime6());
								strlist.add(lonTime);
							}
							if(mod.getUpTime7()!=null&&mod.getDownTime7()!=null){
								Long lonTime=CyUtil.parseTime(mod.getDownTime7(),mod.getUpTime7());
							
								strlist.add(lonTime);
							}
							if(mod.getUpTime8()!=null&&mod.getDownTime8()!=null){
								Long lonTime=CyUtil.parseTime(mod.getDownTime8(),mod.getUpTime8());
							
								strlist.add(lonTime);
							}
							if(mod.getUpTime9()!=null&&mod.getDownTime9()!=null){
								Long lonTime=CyUtil.parseTime(mod.getDownTime9(),mod.getUpTime9());
							
								strlist.add(lonTime);
							}
							if(mod.getUpTime10()!=null&&mod.getDownTime10()!=null){
								Long lonTime=CyUtil.parseTime(mod.getDownTime10(),mod.getUpTime10());
					
								strlist.add(lonTime);
							}
							Long time=0L;
							for(int i=0;i<strlist.size();i++){
								time+=strlist.get(i);				
							}
							Long hour=time/1000/60/60;
							Long minutes=time/1000/60-(60*hour);
							Long second=time/1000-(60*minutes+3600*hour);
							mod.setWorktime(hour+"h"+minutes+"m"+second+"s");
							mod.getWorker().setObjThing(hour+"h"+minutes+"m"+second+"s");
							//int n=workerDao.updateWorker(model.getWorker());
						}
				   System.out.println(lslist);
				    
				   for(CheckModel mod:lslist){ 
				       for(WorkLocation w:newList){
				    	   
				    	   System.out.println(mod.getWorker().getWid());
				    	   System.out.println(w.getWorker().getWid());
				    	 
						  if(mod.getWorker().getWid().equals(w.getWorker().getWid())){
							  w.getWorker().setObjThing(mod.getWorktime());
						  }
					  }
				   }
					
				   for(WorkLocation w:newList){
					   if(w.getWorker().getObjThing()==null){
						   w.getWorker().setObjThing("");
					   }
				   }
					
					
					if(newList.size()>20){
						result.setData(gs.toJson(newList.subList(0, 20)));
						result.setData1(gs.toJson(newList.subList(20,newList.size())));
					}else{
						result.setData(gs.toJson(newList));
					}
					List<WorkLocation> locationList=new ArrayList<WorkLocation>();
					for (WorkLocation wl:newList) {
						if (wl.getLocationState()!=null && wl.getLocationState()==1) {
							locationList.add(wl);
						}
					}
					result.setData3(newList.size()+"@"+locationList.size()+"@"+(newList.size()-locationList.size()));
					result.setData2(worker.getWorkerName());
					result.setState(1);
					return result;
	    		}else if(mode.getUpTime3()!=null&&mode.getDownTime3()==null){
	    			System.out.println("第三次下线");
	    			
	    			Map<String,Object> cmap=new HashMap<String,Object>();
					for(WorkLocation w:list){
						if(w.getWorker()!=null&&worker.getWorkerCode().equals(w.getWorker().getWorkerCode())){
							cmap.put("locationState",0);
							cmap.put("sid", w.getSid());
							cmap.put("nowTime",CyUtil.getTime1());
							lineDao.updateLocation(cmap);
						}
					}
					
					model.setDownTime3(CyUtil.getTime1());
	    			model.setWorker(worker);
	    			int n=checkDao.updateCheck(model);
	    			if(n!=1){
	    				throw new RuntimeException("下线失败!");
	    			}
					
					
					
					
					result.setMsg("打卡成功！");
					list=checkDao.findWorkerByCode(line);
					List<WorkLocation> newList=new ArrayList<WorkLocation>();
					for(WorkLocation w:list){
						if(w.getWorker()!=null&&w.getWorker().getWid()!=null){
							newList.add(w);
						}
					}
					List<CheckModel> lslist=new ArrayList<CheckModel>();
				    for(WorkLocation w:newList){
				    	List<CheckModel> clist=workerDao.findCheckByWid(w.getWorker());
				    	for(CheckModel c:clist){
				    		if(c.getWriteTime().equals(CyUtil.getTime2())){
				    			lslist.add(c);
				    		}
				    	}
				    }
				  
				    for(CheckModel mod:lslist){
				    	 List<Long> strlist=new ArrayList<Long>();
							if(mod.getUpTime1()!=null&&mod.getDownTime1()!=null){
								Long lonTime=CyUtil.parseTime(mod.getDownTime1(),mod.getUpTime1());
								strlist.add(lonTime);
							}
							if(mod.getUpTime2()!=null&&mod.getDownTime2()!=null){
								Long lonTime=CyUtil.parseTime(mod.getDownTime2(),mod.getUpTime2());
								strlist.add(lonTime);
							}
							if(mod.getUpTime3()!=null&&mod.getDownTime3()!=null){
								Long lonTime=CyUtil.parseTime(mod.getDownTime3(),mod.getUpTime3());
								
								strlist.add(lonTime);
							}
							if(mod.getUpTime4()!=null&&mod.getDownTime4()!=null){
								Long lonTime=CyUtil.parseTime(mod.getDownTime4(),mod.getUpTime4());
								
								strlist.add(lonTime);
							}
							if(mod.getUpTime5()!=null&&mod.getDownTime5()!=null){
								Long lonTime=CyUtil.parseTime(mod.getDownTime5(),mod.getUpTime5());
								strlist.add(lonTime);
							}
							if(mod.getUpTime6()!=null&&mod.getDownTime6()!=null){
								Long lonTime=CyUtil.parseTime(mod.getDownTime6(),mod.getUpTime6());
								strlist.add(lonTime);
							}
							if(mod.getUpTime7()!=null&&mod.getDownTime7()!=null){
								Long lonTime=CyUtil.parseTime(mod.getDownTime7(),mod.getUpTime7());
							
								strlist.add(lonTime);
							}
							if(mod.getUpTime8()!=null&&mod.getDownTime8()!=null){
								Long lonTime=CyUtil.parseTime(mod.getDownTime8(),mod.getUpTime8());
							
								strlist.add(lonTime);
							}
							if(mod.getUpTime9()!=null&&mod.getDownTime9()!=null){
								Long lonTime=CyUtil.parseTime(mod.getDownTime9(),mod.getUpTime9());
							
								strlist.add(lonTime);
							}
							if(mod.getUpTime10()!=null&&mod.getDownTime10()!=null){
								Long lonTime=CyUtil.parseTime(mod.getDownTime10(),mod.getUpTime10());
					
								strlist.add(lonTime);
							}
							Long time=0L;
							for(int i=0;i<strlist.size();i++){
								time+=strlist.get(i);				
							}
							Long hour=time/1000/60/60;
							Long minutes=time/1000/60-(60*hour);
							Long second=time/1000-(60*minutes+3600*hour);
							mod.setWorktime(hour+"h"+minutes+"m"+second+"s");
							mod.getWorker().setObjThing(hour+"h"+minutes+"m"+second+"s");
							//int n=workerDao.updateWorker(model.getWorker());
						}
				   System.out.println(lslist);
				    
				   for(CheckModel mod:lslist){ 
				       for(WorkLocation w:newList){
				    	   
				    	   System.out.println(mod.getWorker().getWid());
				    	   System.out.println(w.getWorker().getWid());
				    	 
						  if(mod.getWorker().getWid().equals(w.getWorker().getWid())){
							  w.getWorker().setObjThing(mod.getWorktime());
						  }
					  }
				   }
					
				   for(WorkLocation w:newList){
					   if(w.getWorker().getObjThing()==null){
						   w.getWorker().setObjThing("");
					   }
				   }
					
					
					
					if(newList.size()>20){
						result.setData(gs.toJson(newList.subList(0, 20)));
						result.setData1(gs.toJson(newList.subList(20,newList.size())));
					}else{
						result.setData(gs.toJson(newList));
					}
					List<WorkLocation> locationList=new ArrayList<WorkLocation>();
					for (WorkLocation wl:newList) {
						if (wl.getLocationState()!=null && wl.getLocationState()==1) {
							locationList.add(wl);
						}
					}
					result.setData3(newList.size()+"@"+locationList.size()+"@"+(newList.size()-locationList.size()));
					result.setData2(worker.getWorkerName());
					result.setState(1);
					return result;
	    		}else if(mode.getUpTime4()!=null&&mode.getDownTime4()==null){
	    			System.out.println(mode.getUpTime4());
	    			System.out.println("第四次下线");
	    			
	    			Map<String,Object> cmap=new HashMap<String,Object>();
					for(WorkLocation w:list){
						if(w.getWorker()!=null&&worker.getWorkerCode().equals(w.getWorker().getWorkerCode())){
							cmap.put("locationState",0);
							cmap.put("sid", w.getSid());
							cmap.put("nowTime",CyUtil.getTime1());
							lineDao.updateLocation(cmap);
						}
					}
					
					model.setDownTime4(CyUtil.getTime1());
	    			model.setWorker(worker);
	    			int n=checkDao.updateCheck(model);
	    			if(n!=1){
	    				throw new RuntimeException("下线失败!");
	    			}
					
		
					result.setMsg("打卡成功！");
					list=checkDao.findWorkerByCode(line);
					List<WorkLocation> newList=new ArrayList<WorkLocation>();
					for(WorkLocation w:list){
						if(w.getWorker()!=null&&w.getWorker().getWid()!=null){
							newList.add(w);
						}
					}
					List<CheckModel> lslist=new ArrayList<CheckModel>();
				    for(WorkLocation w:newList){
				    	List<CheckModel> clist=workerDao.findCheckByWid(w.getWorker());
				    	for(CheckModel c:clist){
				    		if(c.getWriteTime().equals(CyUtil.getTime2())){
				    			lslist.add(c);
				    		}
				    	}
				    }
				  
				    for(CheckModel mod:lslist){
				    	 List<Long> strlist=new ArrayList<Long>();
							if(mod.getUpTime1()!=null&&mod.getDownTime1()!=null){
								Long lonTime=CyUtil.parseTime(mod.getDownTime1(),mod.getUpTime1());
								strlist.add(lonTime);
							}
							if(mod.getUpTime2()!=null&&mod.getDownTime2()!=null){
								Long lonTime=CyUtil.parseTime(mod.getDownTime2(),mod.getUpTime2());
								strlist.add(lonTime);
							}
							if(mod.getUpTime3()!=null&&mod.getDownTime3()!=null){
								Long lonTime=CyUtil.parseTime(mod.getDownTime3(),mod.getUpTime3());
								
								strlist.add(lonTime);
							}
							if(mod.getUpTime4()!=null&&mod.getDownTime4()!=null){
								Long lonTime=CyUtil.parseTime(mod.getDownTime4(),mod.getUpTime4());
								
								strlist.add(lonTime);
							}
							if(mod.getUpTime5()!=null&&mod.getDownTime5()!=null){
								Long lonTime=CyUtil.parseTime(mod.getDownTime5(),mod.getUpTime5());
								strlist.add(lonTime);
							}
							if(mod.getUpTime6()!=null&&mod.getDownTime6()!=null){
								Long lonTime=CyUtil.parseTime(mod.getDownTime6(),mod.getUpTime6());
								strlist.add(lonTime);
							}
							if(mod.getUpTime7()!=null&&mod.getDownTime7()!=null){
								Long lonTime=CyUtil.parseTime(mod.getDownTime7(),mod.getUpTime7());
							
								strlist.add(lonTime);
							}
							if(mod.getUpTime8()!=null&&mod.getDownTime8()!=null){
								Long lonTime=CyUtil.parseTime(mod.getDownTime8(),mod.getUpTime8());
							
								strlist.add(lonTime);
							}
							if(mod.getUpTime9()!=null&&mod.getDownTime9()!=null){
								Long lonTime=CyUtil.parseTime(mod.getDownTime9(),mod.getUpTime9());
							
								strlist.add(lonTime);
							}
							if(mod.getUpTime10()!=null&&mod.getDownTime10()!=null){
								Long lonTime=CyUtil.parseTime(mod.getDownTime10(),mod.getUpTime10());
					
								strlist.add(lonTime);
							}
							Long time=0L;
							for(int i=0;i<strlist.size();i++){
								time+=strlist.get(i);				
							}
							Long hour=time/1000/60/60;
							Long minutes=time/1000/60-(60*hour);
							Long second=time/1000-(60*minutes+3600*hour);
							mod.setWorktime(hour+"h"+minutes+"m"+second+"s");
							mod.getWorker().setObjThing(hour+"h"+minutes+"m"+second+"s");
							//int n=workerDao.updateWorker(model.getWorker());
						}
				   System.out.println(lslist);
				    
				   for(CheckModel mod:lslist){ 
				       for(WorkLocation w:newList){
				    	   
				    	   System.out.println(mod.getWorker().getWid());
				    	   System.out.println(w.getWorker().getWid());
				    	 
						  if(mod.getWorker().getWid().equals(w.getWorker().getWid())){
							  w.getWorker().setObjThing(mod.getWorktime());
						  }
					  }
				   }
					
				   for(WorkLocation w:newList){
					   if(w.getWorker().getObjThing()==null){
						   w.getWorker().setObjThing("");
					   }
				   }
					
					
					if(newList.size()>20){
						result.setData(gs.toJson(newList.subList(0, 20)));
						result.setData1(gs.toJson(newList.subList(20,newList.size())));
					}else{
						result.setData(gs.toJson(newList));
					}
					List<WorkLocation> locationList=new ArrayList<WorkLocation>();
					for (WorkLocation wl:newList) {
						if (wl.getLocationState()!=null && wl.getLocationState()==1) {
							locationList.add(wl);
						}
					}
					result.setData3(newList.size()+"@"+locationList.size()+"@"+(newList.size()-locationList.size()));
					result.setData2(worker.getWorkerName());
					result.setState(1);
					return result;
	    		}else if(mode.getUpTime5()!=null&&mode.getDownTime5()==null){
	    			System.out.println("第五次下线");
	    			
	    			Map<String,Object> cmap=new HashMap<String,Object>();
					for(WorkLocation w:list){
						if(w.getWorker()!=null&&worker.getWorkerCode().equals(w.getWorker().getWorkerCode())){
							cmap.put("locationState",0);
							cmap.put("sid", w.getSid());
							cmap.put("nowTime",CyUtil.getTime1());
							lineDao.updateLocation(cmap);
						}
					}
					
					model.setDownTime5(CyUtil.getTime1());
	    			model.setWorker(worker);
	    			int n=checkDao.updateCheck(model);
	    			if(n!=1){
	    				throw new RuntimeException("下线失败!");
	    			}
					
					
					result.setMsg("打卡成功！");
					list=checkDao.findWorkerByCode(line);
					List<WorkLocation> newList=new ArrayList<WorkLocation>();
					for(WorkLocation w:list){
						if(w.getWorker()!=null&&w.getWorker().getWid()!=null){
							newList.add(w);
						}
					}
					
					List<CheckModel> lslist=new ArrayList<CheckModel>();
				    for(WorkLocation w:newList){
				    	List<CheckModel> clist=workerDao.findCheckByWid(w.getWorker());
				    	for(CheckModel c:clist){
				    		if(c.getWriteTime().equals(CyUtil.getTime2())){
				    			lslist.add(c);
				    		}
				    	}
				    }
				  
				    for(CheckModel mod:lslist){
				    	 List<Long> strlist=new ArrayList<Long>();
							if(mod.getUpTime1()!=null&&mod.getDownTime1()!=null){
								Long lonTime=CyUtil.parseTime(mod.getDownTime1(),mod.getUpTime1());
								strlist.add(lonTime);
							}
							if(mod.getUpTime2()!=null&&mod.getDownTime2()!=null){
								Long lonTime=CyUtil.parseTime(mod.getDownTime2(),mod.getUpTime2());
								strlist.add(lonTime);
							}
							if(mod.getUpTime3()!=null&&mod.getDownTime3()!=null){
								Long lonTime=CyUtil.parseTime(mod.getDownTime3(),mod.getUpTime3());
								
								strlist.add(lonTime);
							}
							if(mod.getUpTime4()!=null&&mod.getDownTime4()!=null){
								Long lonTime=CyUtil.parseTime(mod.getDownTime4(),mod.getUpTime4());
								
								strlist.add(lonTime);
							}
							if(mod.getUpTime5()!=null&&mod.getDownTime5()!=null){
								Long lonTime=CyUtil.parseTime(mod.getDownTime5(),mod.getUpTime5());
								strlist.add(lonTime);
							}
							if(mod.getUpTime6()!=null&&mod.getDownTime6()!=null){
								Long lonTime=CyUtil.parseTime(mod.getDownTime6(),mod.getUpTime6());
								strlist.add(lonTime);
							}
							if(mod.getUpTime7()!=null&&mod.getDownTime7()!=null){
								Long lonTime=CyUtil.parseTime(mod.getDownTime7(),mod.getUpTime7());
							
								strlist.add(lonTime);
							}
							if(mod.getUpTime8()!=null&&mod.getDownTime8()!=null){
								Long lonTime=CyUtil.parseTime(mod.getDownTime8(),mod.getUpTime8());
							
								strlist.add(lonTime);
							}
							if(mod.getUpTime9()!=null&&mod.getDownTime9()!=null){
								Long lonTime=CyUtil.parseTime(mod.getDownTime9(),mod.getUpTime9());
							
								strlist.add(lonTime);
							}
							if(mod.getUpTime10()!=null&&mod.getDownTime10()!=null){
								Long lonTime=CyUtil.parseTime(mod.getDownTime10(),mod.getUpTime10());
					
								strlist.add(lonTime);
							}
							Long time=0L;
							for(int i=0;i<strlist.size();i++){
								time+=strlist.get(i);				
							}
							Long hour=time/1000/60/60;
							Long minutes=time/1000/60-(60*hour);
							Long second=time/1000-(60*minutes+3600*hour);
							mod.setWorktime(hour+"h"+minutes+"m"+second+"s");
							mod.getWorker().setObjThing(hour+"h"+minutes+"m"+second+"s");
							//int n=workerDao.updateWorker(model.getWorker());
						}
				   System.out.println(lslist);
				    
				   for(CheckModel mod:lslist){ 
				       for(WorkLocation w:newList){
				    	   
				    	   System.out.println(mod.getWorker().getWid());
				    	   System.out.println(w.getWorker().getWid());
				    	 
						  if(mod.getWorker().getWid().equals(w.getWorker().getWid())){
							  w.getWorker().setObjThing(mod.getWorktime());
						  }
					  }
				   }
					
				   for(WorkLocation w:newList){
					   if(w.getWorker().getObjThing()==null){
						   w.getWorker().setObjThing("");
					   }
				   }
					
					
					if(newList.size()>20){
						result.setData(gs.toJson(newList.subList(0, 20)));
						result.setData1(gs.toJson(newList.subList(20,newList.size())));
					}else{
						result.setData(gs.toJson(newList));
					}
					List<WorkLocation> locationList=new ArrayList<WorkLocation>();
					for (WorkLocation wl:newList) {
						if (wl.getLocationState()!=null && wl.getLocationState()==1) {
							locationList.add(wl);
						}
					}
					result.setData3(newList.size()+"@"+locationList.size()+"@"+(newList.size()-locationList.size()));
					result.setData2(worker.getWorkerName());
					result.setState(1);
					return result;
	    		}else if(mode.getUpTime6()!=null&&mode.getDownTime6()==null){
	    			
	    			Map<String,Object> cmap=new HashMap<String,Object>();
					for(WorkLocation w:list){
						if(w.getWorker()!=null&&worker.getWorkerCode().equals(w.getWorker().getWorkerCode())){
							cmap.put("locationState",0);
							cmap.put("sid", w.getSid());
							cmap.put("nowTime",CyUtil.getTime1());
							lineDao.updateLocation(cmap);
						}
					}
					model.setDownTime6(CyUtil.getTime1());
	    			model.setWorker(worker);
	    			int n=checkDao.updateCheck(model);
	    			if(n!=1){
	    				throw new RuntimeException("下线失败!");
	    			}
					result.setMsg("打卡成功！");
					list=checkDao.findWorkerByCode(line);
					List<WorkLocation> newList=new ArrayList<WorkLocation>();
					for(WorkLocation w:list){
						if(w.getWorker()!=null&&w.getWorker().getWid()!=null){
							newList.add(w);
						}
					}
					
					List<CheckModel> lslist=new ArrayList<CheckModel>();
				    for(WorkLocation w:newList){
				    	List<CheckModel> clist=workerDao.findCheckByWid(w.getWorker());
				    	for(CheckModel c:clist){
				    		if(c.getWriteTime().equals(CyUtil.getTime2())){
				    			lslist.add(c);
				    		}
				    	}
				    }
				  
				    for(CheckModel mod:lslist){
				    	 List<Long> strlist=new ArrayList<Long>();
							if(mod.getUpTime1()!=null&&mod.getDownTime1()!=null){
								Long lonTime=CyUtil.parseTime(mod.getDownTime1(),mod.getUpTime1());
								strlist.add(lonTime);
							}
							if(mod.getUpTime2()!=null&&mod.getDownTime2()!=null){
								Long lonTime=CyUtil.parseTime(mod.getDownTime2(),mod.getUpTime2());
								strlist.add(lonTime);
							}
							if(mod.getUpTime3()!=null&&mod.getDownTime3()!=null){
								Long lonTime=CyUtil.parseTime(mod.getDownTime3(),mod.getUpTime3());
								
								strlist.add(lonTime);
							}
							if(mod.getUpTime4()!=null&&mod.getDownTime4()!=null){
								Long lonTime=CyUtil.parseTime(mod.getDownTime4(),mod.getUpTime4());
								
								strlist.add(lonTime);
							}
							if(mod.getUpTime5()!=null&&mod.getDownTime5()!=null){
								Long lonTime=CyUtil.parseTime(mod.getDownTime5(),mod.getUpTime5());
								strlist.add(lonTime);
							}
							if(mod.getUpTime6()!=null&&mod.getDownTime6()!=null){
								Long lonTime=CyUtil.parseTime(mod.getDownTime6(),mod.getUpTime6());
								strlist.add(lonTime);
							}
							if(mod.getUpTime7()!=null&&mod.getDownTime7()!=null){
								Long lonTime=CyUtil.parseTime(mod.getDownTime7(),mod.getUpTime7());
							
								strlist.add(lonTime);
							}
							if(mod.getUpTime8()!=null&&mod.getDownTime8()!=null){
								Long lonTime=CyUtil.parseTime(mod.getDownTime8(),mod.getUpTime8());
							
								strlist.add(lonTime);
							}
							if(mod.getUpTime9()!=null&&mod.getDownTime9()!=null){
								Long lonTime=CyUtil.parseTime(mod.getDownTime9(),mod.getUpTime9());
							
								strlist.add(lonTime);
							}
							if(mod.getUpTime10()!=null&&mod.getDownTime10()!=null){
								Long lonTime=CyUtil.parseTime(mod.getDownTime10(),mod.getUpTime10());
					
								strlist.add(lonTime);
							}
							Long time=0L;
							for(int i=0;i<strlist.size();i++){
								time+=strlist.get(i);				
							}
							Long hour=time/1000/60/60;
							Long minutes=time/1000/60-(60*hour);
							Long second=time/1000-(60*minutes+3600*hour);
							mod.setWorktime(hour+"h"+minutes+"m"+second+"s");
							mod.getWorker().setObjThing(hour+"h"+minutes+"m"+second+"s");
							//int n=workerDao.updateWorker(model.getWorker());
						}
				   System.out.println(lslist);
				    
				   for(CheckModel mod:lslist){ 
				       for(WorkLocation w:newList){
				    	   
				    	   System.out.println(mod.getWorker().getWid());
				    	   System.out.println(w.getWorker().getWid());
				    	 
						  if(mod.getWorker().getWid().equals(w.getWorker().getWid())){
							  w.getWorker().setObjThing(mod.getWorktime());
						  }
					  }
				   }
					
				   for(WorkLocation w:newList){
					   if(w.getWorker().getObjThing()==null){
						   w.getWorker().setObjThing("");
					   }
				   }
					
					
					if(newList.size()>20){
						result.setData(gs.toJson(newList.subList(0, 20)));
						result.setData1(gs.toJson(newList.subList(20,newList.size())));
					}else{
						result.setData(gs.toJson(newList));
					}
					List<WorkLocation> locationList=new ArrayList<WorkLocation>();
					for (WorkLocation wl:newList) {
						if (wl.getLocationState()!=null && wl.getLocationState()==1) {
							locationList.add(wl);
						}
					}
					result.setData3(newList.size()+"@"+locationList.size()+"@"+(newList.size()-locationList.size()));
					result.setData2(worker.getWorkerName());
					result.setState(1);
					return result;
	    		}else if(mode.getUpTime7()!=null&&mode.getDownTime7()==null){
	    			
	    			
	    			Map<String,Object> cmap=new HashMap<String,Object>();
					for(WorkLocation w:list){
						if(w.getWorker()!=null&&worker.getWorkerCode().equals(w.getWorker().getWorkerCode())){
							cmap.put("locationState",0);
							cmap.put("sid", w.getSid());
							cmap.put("nowTime",CyUtil.getTime1());
							lineDao.updateLocation(cmap);
						}
					}
					model.setDownTime7(CyUtil.getTime1());
	    			model.setWorker(worker);
	    			int n=checkDao.updateCheck(model);
	    			if(n!=1){
	    				throw new RuntimeException("下线失败!");
	    			}
					
					
					result.setMsg("打卡成功！");
					list=checkDao.findWorkerByCode(line);
					List<WorkLocation> newList=new ArrayList<WorkLocation>();
					for(WorkLocation w:list){
						if(w.getWorker()!=null&&w.getWorker().getWid()!=null){
							newList.add(w);
						}
					}
					
					List<CheckModel> lslist=new ArrayList<CheckModel>();
				    for(WorkLocation w:newList){
				    	List<CheckModel> clist=workerDao.findCheckByWid(w.getWorker());
				    	for(CheckModel c:clist){
				    		if(c.getWriteTime().equals(CyUtil.getTime2())){
				    			lslist.add(c);
				    		}
				    	}
				    }
				  
				    for(CheckModel mod:lslist){
				    	 List<Long> strlist=new ArrayList<Long>();
							if(mod.getUpTime1()!=null&&mod.getDownTime1()!=null){
								Long lonTime=CyUtil.parseTime(mod.getDownTime1(),mod.getUpTime1());
								strlist.add(lonTime);
							}
							if(mod.getUpTime2()!=null&&mod.getDownTime2()!=null){
								Long lonTime=CyUtil.parseTime(mod.getDownTime2(),mod.getUpTime2());
								strlist.add(lonTime);
							}
							if(mod.getUpTime3()!=null&&mod.getDownTime3()!=null){
								Long lonTime=CyUtil.parseTime(mod.getDownTime3(),mod.getUpTime3());
								
								strlist.add(lonTime);
							}
							if(mod.getUpTime4()!=null&&mod.getDownTime4()!=null){
								Long lonTime=CyUtil.parseTime(mod.getDownTime4(),mod.getUpTime4());
								
								strlist.add(lonTime);
							}
							if(mod.getUpTime5()!=null&&mod.getDownTime5()!=null){
								Long lonTime=CyUtil.parseTime(mod.getDownTime5(),mod.getUpTime5());
								strlist.add(lonTime);
							}
							if(mod.getUpTime6()!=null&&mod.getDownTime6()!=null){
								Long lonTime=CyUtil.parseTime(mod.getDownTime6(),mod.getUpTime6());
								strlist.add(lonTime);
							}
							if(mod.getUpTime7()!=null&&mod.getDownTime7()!=null){
								Long lonTime=CyUtil.parseTime(mod.getDownTime7(),mod.getUpTime7());
							
								strlist.add(lonTime);
							}
							if(mod.getUpTime8()!=null&&mod.getDownTime8()!=null){
								Long lonTime=CyUtil.parseTime(mod.getDownTime8(),mod.getUpTime8());
							
								strlist.add(lonTime);
							}
							if(mod.getUpTime9()!=null&&mod.getDownTime9()!=null){
								Long lonTime=CyUtil.parseTime(mod.getDownTime9(),mod.getUpTime9());
							
								strlist.add(lonTime);
							}
							if(mod.getUpTime10()!=null&&mod.getDownTime10()!=null){
								Long lonTime=CyUtil.parseTime(mod.getDownTime10(),mod.getUpTime10());
					
								strlist.add(lonTime);
							}
							Long time=0L;
							for(int i=0;i<strlist.size();i++){
								time+=strlist.get(i);				
							}
							Long hour=time/1000/60/60;
							Long minutes=time/1000/60-(60*hour);
							Long second=time/1000-(60*minutes+3600*hour);
							mod.setWorktime(hour+"h"+minutes+"m"+second+"s");
							mod.getWorker().setObjThing(hour+"h"+minutes+"m"+second+"s");
							//int n=workerDao.updateWorker(model.getWorker());
						}
				   System.out.println(lslist);
				    
				   for(CheckModel mod:lslist){ 
				       for(WorkLocation w:newList){
				    	   
				    	   System.out.println(mod.getWorker().getWid());
				    	   System.out.println(w.getWorker().getWid());
				    	 
						  if(mod.getWorker().getWid().equals(w.getWorker().getWid())){
							  w.getWorker().setObjThing(mod.getWorktime());
						  }
					  }
				   }
					
				   for(WorkLocation w:newList){
					   if(w.getWorker().getObjThing()==null){
						   w.getWorker().setObjThing("");
					   }
				   }
					
					if(newList.size()>20){
						result.setData(gs.toJson(newList.subList(0, 20)));
						result.setData1(gs.toJson(newList.subList(20,newList.size())));
					}else{
						result.setData(gs.toJson(newList));
					}
					List<WorkLocation> locationList=new ArrayList<WorkLocation>();
					for (WorkLocation wl:newList) {
						if (wl.getLocationState()!=null && wl.getLocationState()==1) {
							locationList.add(wl);
						}
					}
					result.setData3(newList.size()+"@"+locationList.size()+"@"+(newList.size()-locationList.size()));
					result.setData2(worker.getWorkerName());
					result.setState(1);
					return result;
	    		}else if(mode.getUpTime8()!=null&&mode.getDownTime8()==null){
	    			
	    			Map<String,Object> cmap=new HashMap<String,Object>();
					for(WorkLocation w:list){
						if(w.getWorker()!=null&&worker.getWorkerCode().equals(w.getWorker().getWorkerCode())){
							cmap.put("locationState",0);
							cmap.put("sid", w.getSid());
							cmap.put("nowTime",CyUtil.getTime1());
							lineDao.updateLocation(cmap);
						}
					}
					model.setDownTime8(CyUtil.getTime1());
	    			model.setWorker(worker);
	    			int n=checkDao.updateCheck(model);
	    			if(n!=1){
	    				throw new RuntimeException("下线失败!");
	    			}
					
					
					result.setMsg("打卡成功！");
					list=checkDao.findWorkerByCode(line);
					List<WorkLocation> newList=new ArrayList<WorkLocation>();
					for(WorkLocation w:list){
						if(w.getWorker()!=null&&w.getWorker().getWid()!=null){
							newList.add(w);
						}
					}
					
					List<CheckModel> lslist=new ArrayList<CheckModel>();
				    for(WorkLocation w:newList){
				    	List<CheckModel> clist=workerDao.findCheckByWid(w.getWorker());
				    	for(CheckModel c:clist){
				    		if(c.getWriteTime().equals(CyUtil.getTime2())){
				    			lslist.add(c);
				    		}
				    	}
				    }
				  
				    for(CheckModel mod:lslist){
				    	 List<Long> strlist=new ArrayList<Long>();
							if(mod.getUpTime1()!=null&&mod.getDownTime1()!=null){
								Long lonTime=CyUtil.parseTime(mod.getDownTime1(),mod.getUpTime1());
								strlist.add(lonTime);
							}
							if(mod.getUpTime2()!=null&&mod.getDownTime2()!=null){
								Long lonTime=CyUtil.parseTime(mod.getDownTime2(),mod.getUpTime2());
								strlist.add(lonTime);
							}
							if(mod.getUpTime3()!=null&&mod.getDownTime3()!=null){
								Long lonTime=CyUtil.parseTime(mod.getDownTime3(),mod.getUpTime3());
								
								strlist.add(lonTime);
							}
							if(mod.getUpTime4()!=null&&mod.getDownTime4()!=null){
								Long lonTime=CyUtil.parseTime(mod.getDownTime4(),mod.getUpTime4());
								
								strlist.add(lonTime);
							}
							if(mod.getUpTime5()!=null&&mod.getDownTime5()!=null){
								Long lonTime=CyUtil.parseTime(mod.getDownTime5(),mod.getUpTime5());
								strlist.add(lonTime);
							}
							if(mod.getUpTime6()!=null&&mod.getDownTime6()!=null){
								Long lonTime=CyUtil.parseTime(mod.getDownTime6(),mod.getUpTime6());
								strlist.add(lonTime);
							}
							if(mod.getUpTime7()!=null&&mod.getDownTime7()!=null){
								Long lonTime=CyUtil.parseTime(mod.getDownTime7(),mod.getUpTime7());
							
								strlist.add(lonTime);
							}
							if(mod.getUpTime8()!=null&&mod.getDownTime8()!=null){
								Long lonTime=CyUtil.parseTime(mod.getDownTime8(),mod.getUpTime8());
							
								strlist.add(lonTime);
							}
							if(mod.getUpTime9()!=null&&mod.getDownTime9()!=null){
								Long lonTime=CyUtil.parseTime(mod.getDownTime9(),mod.getUpTime9());
							
								strlist.add(lonTime);
							}
							if(mod.getUpTime10()!=null&&mod.getDownTime10()!=null){
								Long lonTime=CyUtil.parseTime(mod.getDownTime10(),mod.getUpTime10());
					
								strlist.add(lonTime);
							}
							Long time=0L;
							for(int i=0;i<strlist.size();i++){
								time+=strlist.get(i);				
							}
							Long hour=time/1000/60/60;
							Long minutes=time/1000/60-(60*hour);
							Long second=time/1000-(60*minutes+3600*hour);
							mod.setWorktime(hour+"h"+minutes+"m"+second+"s");
							mod.getWorker().setObjThing(hour+"h"+minutes+"m"+second+"s");
							//int n=workerDao.updateWorker(model.getWorker());
						}
				   System.out.println(lslist);
				    
				   for(CheckModel mod:lslist){ 
				       for(WorkLocation w:newList){
				    	   
				    	   System.out.println(mod.getWorker().getWid());
				    	   System.out.println(w.getWorker().getWid());
				    	 
						  if(mod.getWorker().getWid().equals(w.getWorker().getWid())){
							  w.getWorker().setObjThing(mod.getWorktime());
						  }
					  }
				   }
					
				   for(WorkLocation w:newList){
					   if(w.getWorker().getObjThing()==null){
						   w.getWorker().setObjThing("");
					   }
				   }
					
					
					if(newList.size()>20){
						result.setData(gs.toJson(newList.subList(0, 20)));
						result.setData1(gs.toJson(newList.subList(20,newList.size())));
					}else{
						result.setData(gs.toJson(newList));
					}
					List<WorkLocation> locationList=new ArrayList<WorkLocation>();
					for (WorkLocation wl:newList) {
						if (wl.getLocationState()!=null && wl.getLocationState()==1) {
							locationList.add(wl);
						}
					}
					result.setData3(newList.size()+"@"+locationList.size()+"@"+(newList.size()-locationList.size()));
					result.setData2(worker.getWorkerName());
					result.setState(1);
					return result;
	    		}else if(mode.getUpTime9()!=null&&mode.getDownTime9()==null){
	    			
	    			Map<String,Object> cmap=new HashMap<String,Object>();
					for(WorkLocation w:list){
						if(w.getWorker()!=null&&worker.getWorkerCode().equals(w.getWorker().getWorkerCode())){
							cmap.put("locationState",0);
							cmap.put("sid", w.getSid());
							cmap.put("nowTime",CyUtil.getTime1());
							lineDao.updateLocation(cmap);
						}
					}
					
					model.setDownTime9(CyUtil.getTime1());
	    			model.setWorker(worker);
	    			int n=checkDao.updateCheck(model);
	    			if(n!=1){
	    				throw new RuntimeException("下线失败!");
	    			}
					
					result.setMsg("打卡成功！");
					list=checkDao.findWorkerByCode(line);
					List<WorkLocation> newList=new ArrayList<WorkLocation>();
					for(WorkLocation w:list){
						if(w.getWorker()!=null&&w.getWorker().getWid()!=null){
							newList.add(w);
						}
					}
					
					List<CheckModel> lslist=new ArrayList<CheckModel>();
				    for(WorkLocation w:newList){
				    	List<CheckModel> clist=workerDao.findCheckByWid(w.getWorker());
				    	for(CheckModel c:clist){
				    		if(c.getWriteTime().equals(CyUtil.getTime2())){
				    			lslist.add(c);
				    		}
				    	}
				    }
				  
				    for(CheckModel mod:lslist){
				    	 List<Long> strlist=new ArrayList<Long>();
							if(mod.getUpTime1()!=null&&mod.getDownTime1()!=null){
								Long lonTime=CyUtil.parseTime(mod.getDownTime1(),mod.getUpTime1());
								strlist.add(lonTime);
							}
							if(mod.getUpTime2()!=null&&mod.getDownTime2()!=null){
								Long lonTime=CyUtil.parseTime(mod.getDownTime2(),mod.getUpTime2());
								strlist.add(lonTime);
							}
							if(mod.getUpTime3()!=null&&mod.getDownTime3()!=null){
								Long lonTime=CyUtil.parseTime(mod.getDownTime3(),mod.getUpTime3());
								
								strlist.add(lonTime);
							}
							if(mod.getUpTime4()!=null&&mod.getDownTime4()!=null){
								Long lonTime=CyUtil.parseTime(mod.getDownTime4(),mod.getUpTime4());
								
								strlist.add(lonTime);
							}
							if(mod.getUpTime5()!=null&&mod.getDownTime5()!=null){
								Long lonTime=CyUtil.parseTime(mod.getDownTime5(),mod.getUpTime5());
								strlist.add(lonTime);
							}
							if(mod.getUpTime6()!=null&&mod.getDownTime6()!=null){
								Long lonTime=CyUtil.parseTime(mod.getDownTime6(),mod.getUpTime6());
								strlist.add(lonTime);
							}
							if(mod.getUpTime7()!=null&&mod.getDownTime7()!=null){
								Long lonTime=CyUtil.parseTime(mod.getDownTime7(),mod.getUpTime7());
							
								strlist.add(lonTime);
							}
							if(mod.getUpTime8()!=null&&mod.getDownTime8()!=null){
								Long lonTime=CyUtil.parseTime(mod.getDownTime8(),mod.getUpTime8());
							
								strlist.add(lonTime);
							}
							if(mod.getUpTime9()!=null&&mod.getDownTime9()!=null){
								Long lonTime=CyUtil.parseTime(mod.getDownTime9(),mod.getUpTime9());
							
								strlist.add(lonTime);
							}
							if(mod.getUpTime10()!=null&&mod.getDownTime10()!=null){
								Long lonTime=CyUtil.parseTime(mod.getDownTime10(),mod.getUpTime10());
					
								strlist.add(lonTime);
							}
							Long time=0L;
							for(int i=0;i<strlist.size();i++){
								time+=strlist.get(i);				
							}
							Long hour=time/1000/60/60;
							Long minutes=time/1000/60-(60*hour);
							Long second=time/1000-(60*minutes+3600*hour);
							mod.setWorktime(hour+"h"+minutes+"m"+second+"s");
							mod.getWorker().setObjThing(hour+"h"+minutes+"m"+second+"s");
							//int n=workerDao.updateWorker(model.getWorker());
						}
				   System.out.println(lslist);
				    
				   for(CheckModel mod:lslist){ 
				       for(WorkLocation w:newList){
				    	   
				    	   System.out.println(mod.getWorker().getWid());
				    	   System.out.println(w.getWorker().getWid());
				    	 
						  if(mod.getWorker().getWid().equals(w.getWorker().getWid())){
							  w.getWorker().setObjThing(mod.getWorktime());
						  }
					  }
				   }
					
				   for(WorkLocation w:newList){
					   if(w.getWorker().getObjThing()==null){
						   w.getWorker().setObjThing("");
					   }
				   }
					
					if(newList.size()>20){
						result.setData(gs.toJson(newList.subList(0, 20)));
						result.setData1(gs.toJson(newList.subList(20,newList.size())));
					}else{
						result.setData(gs.toJson(newList));
					}
					List<WorkLocation> locationList=new ArrayList<WorkLocation>();
					for (WorkLocation wl:newList) {
						if (wl.getLocationState()!=null && wl.getLocationState()==1) {
							locationList.add(wl);
						}
					}
					result.setData3(newList.size()+"@"+locationList.size()+"@"+(newList.size()-locationList.size()));
					result.setData2(worker.getWorkerName());
					result.setState(1);
					return result;
	    		}else if(mode.getUpTime10()!=null&&mode.getDownTime10()==null){
	    			
	    			Map<String,Object> cmap=new HashMap<String,Object>();
					for(WorkLocation w:list){
						if(w.getWorker()!=null&&worker.getWorkerCode().equals(w.getWorker().getWorkerCode())){
							cmap.put("locationState",0);
							cmap.put("sid", w.getSid());
							cmap.put("nowTime",CyUtil.getTime1());
							lineDao.updateLocation(cmap);
						}
					}
					
					model.setDownTime10(CyUtil.getTime1());
	    			model.setWorker(worker);
	    			int n=checkDao.updateCheck(model);
	    			if(n!=1){
	    				throw new RuntimeException("下线失败!");
	    			}
					
					
					result.setMsg("打卡成功！");
					list=checkDao.findWorkerByCode(line);
					List<WorkLocation> newList=new ArrayList<WorkLocation>();
					for(WorkLocation w:list){
						if(w.getWorker()!=null&&w.getWorker().getWid()!=null){
							newList.add(w);
						}
					}
					List<CheckModel> lslist=new ArrayList<CheckModel>();
				    for(WorkLocation w:newList){
				    	List<CheckModel> clist=workerDao.findCheckByWid(w.getWorker());
				    	for(CheckModel c:clist){
				    		if(c.getWriteTime().equals(CyUtil.getTime2())){
				    			lslist.add(c);
				    		}
				    	}
				    }
				  
				    for(CheckModel mod:lslist){
				    	 List<Long> strlist=new ArrayList<Long>();
							if(mod.getUpTime1()!=null&&mod.getDownTime1()!=null){
								Long lonTime=CyUtil.parseTime(mod.getDownTime1(),mod.getUpTime1());
								strlist.add(lonTime);
							}
							if(mod.getUpTime2()!=null&&mod.getDownTime2()!=null){
								Long lonTime=CyUtil.parseTime(mod.getDownTime2(),mod.getUpTime2());
								strlist.add(lonTime);
							}
							if(mod.getUpTime3()!=null&&mod.getDownTime3()!=null){
								Long lonTime=CyUtil.parseTime(mod.getDownTime3(),mod.getUpTime3());
								
								strlist.add(lonTime);
							}
							if(mod.getUpTime4()!=null&&mod.getDownTime4()!=null){
								Long lonTime=CyUtil.parseTime(mod.getDownTime4(),mod.getUpTime4());
								
								strlist.add(lonTime);
							}
							if(mod.getUpTime5()!=null&&mod.getDownTime5()!=null){
								Long lonTime=CyUtil.parseTime(mod.getDownTime5(),mod.getUpTime5());
								strlist.add(lonTime);
							}
							if(mod.getUpTime6()!=null&&mod.getDownTime6()!=null){
								Long lonTime=CyUtil.parseTime(mod.getDownTime6(),mod.getUpTime6());
								strlist.add(lonTime);
							}
							if(mod.getUpTime7()!=null&&mod.getDownTime7()!=null){
								Long lonTime=CyUtil.parseTime(mod.getDownTime7(),mod.getUpTime7());
							
								strlist.add(lonTime);
							}
							if(mod.getUpTime8()!=null&&mod.getDownTime8()!=null){
								Long lonTime=CyUtil.parseTime(mod.getDownTime8(),mod.getUpTime8());
							
								strlist.add(lonTime);
							}
							if(mod.getUpTime9()!=null&&mod.getDownTime9()!=null){
								Long lonTime=CyUtil.parseTime(mod.getDownTime9(),mod.getUpTime9());
							
								strlist.add(lonTime);
							}
							if(mod.getUpTime10()!=null&&mod.getDownTime10()!=null){
								Long lonTime=CyUtil.parseTime(mod.getDownTime10(),mod.getUpTime10());
					
								strlist.add(lonTime);
							}
							Long time=0L;
							for(int i=0;i<strlist.size();i++){
								time+=strlist.get(i);				
							}
							Long hour=time/1000/60/60;
							Long minutes=time/1000/60-(60*hour);
							Long second=time/1000-(60*minutes+3600*hour);
							mod.setWorktime(hour+"h"+minutes+"m"+second+"s");
							mod.getWorker().setObjThing(hour+"h"+minutes+"m"+second+"s");
							//int n=workerDao.updateWorker(model.getWorker());
						}
				   System.out.println(lslist);
				    
				   for(CheckModel mod:lslist){ 
				       for(WorkLocation w:newList){
				    	   
				    	   System.out.println(mod.getWorker().getWid());
				    	   System.out.println(w.getWorker().getWid());
				    	 
						  if(mod.getWorker().getWid().equals(w.getWorker().getWid())){
							  w.getWorker().setObjThing(mod.getWorktime());
						  }
					  }
				   }
					
				   for(WorkLocation w:newList){
					   if(w.getWorker().getObjThing()==null){
						   w.getWorker().setObjThing("");
					   }
				   }
					
					
					if(newList.size()>20){
						result.setData(gs.toJson(newList.subList(0, 20)));
						result.setData1(gs.toJson(newList.subList(20,newList.size())));
					}else{
						result.setData(gs.toJson(newList));
					}
					List<WorkLocation> locationList=new ArrayList<WorkLocation>();
					for (WorkLocation wl:newList) {
						if (wl.getLocationState()!=null && wl.getLocationState()==1) {
							locationList.add(wl);
						}
					}
					result.setData3(newList.size()+"@"+locationList.size()+"@"+(newList.size()-locationList.size()));
					result.setData2(worker.getWorkerName());
					result.setState(1);
					return result;
	    		}else{
	    			result.setMsg("请先上线");
	    			
					list=checkDao.findWorkerByCode(line);
					List<WorkLocation> newList=new ArrayList<WorkLocation>();
					for(WorkLocation w:list){
						if(w.getWorker()!=null&&w.getWorker().getWid()!=null){
							newList.add(w);
						}
					}
					
					List<CheckModel> lslist=new ArrayList<CheckModel>();
				    for(WorkLocation w:newList){
				    	List<CheckModel> clist=workerDao.findCheckByWid(w.getWorker());
				    	for(CheckModel c:clist){
				    		if(c.getWriteTime().equals(CyUtil.getTime2())){
				    			lslist.add(c);
				    		}
				    	}
				    }
				  
				    for(CheckModel mod:lslist){
				    	 List<Long> strlist=new ArrayList<Long>();
							if(mod.getUpTime1()!=null&&mod.getDownTime1()!=null){
								Long lonTime=CyUtil.parseTime(mod.getDownTime1(),mod.getUpTime1());
								strlist.add(lonTime);
							}
							if(mod.getUpTime2()!=null&&mod.getDownTime2()!=null){
								Long lonTime=CyUtil.parseTime(mod.getDownTime2(),mod.getUpTime2());
								strlist.add(lonTime);
							}
							if(mod.getUpTime3()!=null&&mod.getDownTime3()!=null){
								Long lonTime=CyUtil.parseTime(mod.getDownTime3(),mod.getUpTime3());
								
								strlist.add(lonTime);
							}
							if(mod.getUpTime4()!=null&&mod.getDownTime4()!=null){
								Long lonTime=CyUtil.parseTime(mod.getDownTime4(),mod.getUpTime4());
								
								strlist.add(lonTime);
							}
							if(mod.getUpTime5()!=null&&mod.getDownTime5()!=null){
								Long lonTime=CyUtil.parseTime(mod.getDownTime5(),mod.getUpTime5());
								strlist.add(lonTime);
							}
							if(mod.getUpTime6()!=null&&mod.getDownTime6()!=null){
								Long lonTime=CyUtil.parseTime(mod.getDownTime6(),mod.getUpTime6());
								strlist.add(lonTime);
							}
							if(mod.getUpTime7()!=null&&mod.getDownTime7()!=null){
								Long lonTime=CyUtil.parseTime(mod.getDownTime7(),mod.getUpTime7());
							
								strlist.add(lonTime);
							}
							if(mod.getUpTime8()!=null&&mod.getDownTime8()!=null){
								Long lonTime=CyUtil.parseTime(mod.getDownTime8(),mod.getUpTime8());
							
								strlist.add(lonTime);
							}
							if(mod.getUpTime9()!=null&&mod.getDownTime9()!=null){
								Long lonTime=CyUtil.parseTime(mod.getDownTime9(),mod.getUpTime9());
							
								strlist.add(lonTime);
							}
							if(mod.getUpTime10()!=null&&mod.getDownTime10()!=null){
								Long lonTime=CyUtil.parseTime(mod.getDownTime10(),mod.getUpTime10());
					
								strlist.add(lonTime);
							}
							Long time=0L;
							for(int i=0;i<strlist.size();i++){
								time+=strlist.get(i);				
							}
							Long hour=time/1000/60/60;
							Long minutes=time/1000/60-(60*hour);
							Long second=time/1000-(60*minutes+3600*hour);
							mod.setWorktime(hour+"h"+minutes+"m"+second+"s");
							mod.getWorker().setObjThing(hour+"h"+minutes+"m"+second+"s");
							//int n=workerDao.updateWorker(model.getWorker());
						}
				   System.out.println(lslist);
				    
				   for(CheckModel mod:lslist){ 
				       for(WorkLocation w:newList){
				    	   
				    	   System.out.println(mod.getWorker().getWid());
				    	   System.out.println(w.getWorker().getWid());
				    	 
						  if(mod.getWorker().getWid().equals(w.getWorker().getWid())){
							  w.getWorker().setObjThing(mod.getWorktime());
						  }
					  }
				   }
					
				   for(WorkLocation w:newList){
					   if(w.getWorker().getObjThing()==null){
						   w.getWorker().setObjThing("");
					   }
				   }
					
					if(newList.size()>20){
						result.setData(gs.toJson(newList.subList(0, 20)));
						result.setData1(gs.toJson(newList.subList(20,newList.size())));
					}else{
						result.setData(gs.toJson(newList));
					}
					List<WorkLocation> locationList=new ArrayList<WorkLocation>();
					for (WorkLocation wl:newList) {
						if (wl.getLocationState()!=null && wl.getLocationState()==1) {
							locationList.add(wl);
						}
					}
					result.setData3(newList.size()+"@"+locationList.size()+"@"+(newList.size()-locationList.size()));
					result.setData2(worker.getWorkerName());
					result.setState(3);
					return result;
	    		}
	    	}else{
	    		result.setMsg("打卡失败");
	    		result.setState(4);
	    		return result;
	    	}
	    	
	    }





	@Override
	public CyResult loadTimeByWorker(ProductWorker worker) {
		CyResult result=new CyResult();
		if(worker.getWorkerCode()==null){
			result.setMsg("工号不能为空!");
			result.setState(0);
			return result;
		}
		List<ProductWorker> list=workerDao.findWorker(worker);
		if(list==null||list.size()<1){
			result.setMsg("查无此人");
			result.setState(2);
			return result;
		}
		CheckModel model=new CheckModel();
		model.setWorker(worker);
		model.setWriteTime(CyUtil.getTime2());
		CheckModel mode=checkDao.findCheckByWid(model);
		if(mode==null){
			result.setMsg("该人当天尚未打卡!");
			result.setState(3);
			return result;
		}
		String[] arr=new String[20];
		if(mode.getUpTime1()==null){
			mode.setUpTime1("");
		
		}
		if(mode.getDownTime1()==null){
			mode.setDownTime1("");
			
		}
		if(mode.getUpTime2()==null){
			mode.setUpTime2("");
			
		}
		if(mode.getDownTime2()==null){
			mode.setDownTime2("");
			
		}
		if(mode.getUpTime3()==null){
			mode.setUpTime3("");
		
		}
		if(mode.getDownTime3()==null){
			mode.setDownTime3("");
		
		}
		if(mode.getUpTime4()==null){
			mode.setUpTime4("");
			
		}
		if(mode.getDownTime4()==null){
			mode.setDownTime4("");
		}
		if(mode.getUpTime5()==null){
			mode.setUpTime5("");
		}
		if(mode.getDownTime5()==null){
			mode.setDownTime5("");
		}
		if(mode.getUpTime6()==null){
			mode.setUpTime6("");
		}
		if(mode.getDownTime6()==null){
			mode.setDownTime6("");
		}
		if(mode.getUpTime7()==null){
			mode.setUpTime7("");
		}
		if(mode.getDownTime7()==null){
			mode.setDownTime7("");
		}
		if(mode.getUpTime8()==null){
			mode.setUpTime8("");
		}
		if(mode.getDownTime8()==null){
			mode.setDownTime8("");
		}
		if(mode.getUpTime9()==null){
			mode.setUpTime9("");
		}
		if(mode.getDownTime9()==null){
			mode.setDownTime9("");
		}
		if(mode.getUpTime10()==null){
			mode.setUpTime10("");
		}
		if(mode.getDownTime10()==null){
			mode.setDownTime10("");
		}
//		arr[0]="upTime1";
//	    arr[1]=mode.getUpTime1();
//		arr[2]="downTime1";
//	    arr[3]=mode.getDownTime1();
//		
//		arr[4]="upTime2";
//		arr[5]=mode.getUpTime2();
//		arr[6]="downTime2:";
//		arr[7]=mode.getDownTime2();
//		
//		arr[8]="upTime3";
//		arr[9]=mode.getUpTime3();
//		arr[10]="downTime3";
//		arr[11]=mode.getDownTime3();
//		
//		arr[12]="upTime4";
//		arr[13]=mode.getUpTime4();
//		arr[14]="downTime4";
//		arr[15]=mode.getDownTime4();
//		
//		arr[16]="upTime5:";
//		arr[17]=mode.getUpTime5();
//		arr[18]="downTime5";
//		arr[19]=mode.getDownTime5();
		
		Gson gs=new Gson();
	   
	    result.setData1(mode.getWorker().getWorkerName());
	    
	    mode.setWorker(null);
	    List<CheckModel> cliset=new ArrayList<CheckModel>();
	    cliset.add(mode);
	    result.setData(gs.toJson(cliset));
	    result.setMsg("打卡时间加载成功");
	    result.setState(1);
		return result;
	}




    //app调线功能
	@Override
	public CyResult lineUp(ProductWorker worker, WorkLine line) {
		CyResult result=new CyResult();
		Gson gs=new Gson();
		if(worker.getWorkerCode()==null||"".equals(worker.getWorkerCode())||line.getLineCode()==null||"".equals(line.getLineCode())){
			result.setMsg("工号产线不能为空!");
			result.setState(0);
			return result;
		}
		List<ProductWorker> wlist=workerDao.findWorker(worker);
		if(wlist==null||wlist.size()<1){
			result.setMsg("未能查询到该人员，请确认是否已录入！");
			result.setState(2);
			return result;
		}
		System.out.println(wlist);
		ProductWorker workerOne=wlist.get(0);//找到这个人
		System.out.println(workerOne.getLocation());//输出这个的工位状态
		List<WorkLine> linelist=lineDao.findLineByCode(line);
		if(linelist==null||linelist.size()<1){
			result.setMsg("未能查到目标产线!");
			result.setData1(workerOne.getWorkerName());
			result.setState(3);
			return result;
		}
		/////////
		System.out.println();
		
		List<WorkLocation> newList=new ArrayList<WorkLocation>();
		List<WorkLocation> clist=checkDao.findWorkerByCode(line);
		
		for(WorkLocation w:clist){
			if(w.getWorker()!=null&&w.getWorker().getWid()!=null){
				newList.add(w);
			}
		}
		
		if(newList.size()<21){
			result.setData(gs.toJson(newList));
		}else{
			result.setData(gs.toJson(newList.subList(0, 21)));
			result.setData2(gs.toJson(newList.subList(21, newList.size())));
		}
	    //System.out.println(result.getData());
		line.setLid(linelist.get(0).getLid());
		String lncode=null;
		if(wlist.get(0).getLocation()!=null){
		   lncode=wlist.get(0).getLocation().getLine().getLineCode();
		   if(wlist.get(0).getLocation().getLocationState()==4){
			    result.setMsg("该员工处于请假状态，无法调线!");
				result.setState(5);
				List<WorkLocation> newList5=new ArrayList<WorkLocation>();
				clist=checkDao.findWorkerByCode(line);
				for(WorkLocation w:clist){
					if(w.getWorker()!=null&&w.getWorker().getWid()!=null){
						newList5.add(w);
					}
				}
				if(newList5.size()<21){
					result.setData(gs.toJson(newList5));
				}else{
					result.setData(gs.toJson(newList5.subList(0, 21)));
					result.setData2(gs.toJson(newList5.subList(21, newList5.size())));
				}
				result.setData1(workerOne.getWorkerName());
				return result;
		   }
		}
		if(line.getLineCode().equals(lncode)){
			result.setMsg("该员工已是目标产线所属员工，无需调线！");
			result.setState(4);
			result.setData1(workerOne.getWorkerName());
			return result;
		}
		WorkLocation loc=workerOne.getLocation();
		System.out.println(loc);
		if(loc!=null){
			WorkLocation location=workerOne.getLocation();
			int n=lineDao.updateWorkerLocation(location.getSid());
			Map<String,Object> map=new HashMap<String,Object>();
			map.put("locationState", 0);
			map.put("sid",location.getSid());
			int m=lineDao.updateLocation(map);
			System.out.println(n);
			if(n!=1&&m!=1){
				throw new RuntimeException("工位置空失败！");
			}
			
			if(loc.getLocationState()==1){
			   NewCheck check=new NewCheck();
			   check.setNowDate(CyUtil.getTime2());
			   check.setLine(loc.getLine());
			   check.setWorker(workerOne);
			   ProductDetails pd=lineDao.findProductByLine(loc.getLine());
			   List<ProductDetails> pdList=lineDao.findProductById(pd);
			   System.out.println("pdList:"+pdList);
			   check.setProduct(pdList.get(0));
			   List<NewCheck> list=newDao.findNewCheckByWorkerCode(check);
			   //将当日 当条产线 单个产品的打卡信息结算
			   for(NewCheck ck:list){
				  System.out.println("ck:"+ck);
			      if(ck.getUpLine()!=null&&ck.getDownLine()==null) {
					 check.setCcid(ck.getCcid());
					 check.setDownLine(CyUtil.getTime1());
					 int u=newDao.updateNewCheck(check);
					 System.out.println("u="+u);
				  }
			   }
			   //结算之后,添加一条新的打卡记录
			   check.setLine(line);
			   check.setUpLine(CyUtil.getTime1());
			   check.setDownLine(null);
			   pd=lineDao.findProductByLine(line);
			   pdList=lineDao.findProductById(pd);
			   check.setProduct(pdList.get(0));
			   check.setScstate(linelist.get(0).getScstate());
			   int r=newDao.addNewCheck(check);
			   System.out.println("r="+r);
			}
		}
		
		
		
		
		
		List<WorkLocation> locationList=lineDao.findLocationBylineCode(line);
		if(locationList!=null)
			if(locationList.size()>0){
				List<Integer> icode=null;
			    for(WorkLocation location:locationList){
			    	String lcode=location.getLocationCode();
			    	icode=new ArrayList<Integer>();
			    	try {
			    		icode.add(Integer.parseInt(lcode.substring(1)));
					}catch (ClassCastException e){
						e.printStackTrace();
						List<WorkLocation> newList1=new ArrayList<WorkLocation>();
						clist=checkDao.findWorkerByCode(line);
						for(WorkLocation w:clist){
							if(w.getWorker()!=null&&w.getWorker().getWid()!=null){
								newList1.add(w);
							}
						}
						for(WorkLocation w:newList1){
							System.out.println(w);
						}
						if(newList1.size()<21){
							result.setData(gs.toJson(newList1));
						}else{
							result.setData(gs.toJson(newList1.subList(0, 21)));
							result.setData2(gs.toJson(newList1.subList(21, newList1.size())));
						}
						
						result.setMsg("调线失败,请检查系统内工位编码设置是否有按照要求!");
						result.setData1(workerOne.getWorkerName());
						result.setState(5);
						return result;
					}
				    if(location.getWorker()==null||"".equals(location.getWorker().getWorkerCode())){
						Map<String,Object> map=new HashMap<String,Object>();
						map.put("sid", location.getSid());
						map.put("workerCode",worker.getWorkerCode());
						if(loc!=null&&loc.getLocationState()!=null){
						   map.put("locationState", loc.getLocationState());
						}else{
						   map.put("locationState", 0);
						}
						int n=lineDao.updateLocation(map);
						if(n!=1){
							throw new RuntimeException("调线失败！");
						}
						List<WorkLocation> newList2=new ArrayList<WorkLocation>();
						clist=checkDao.findWorkerByCode(line);
						for(WorkLocation w:clist){
							System.out.println(w);
						}
						System.out.println("---------------------");
						for(WorkLocation w:clist){
							if(w.getWorker()!=null&&w.getWorker().getWid()!=null){
								newList2.add(w);
							}
						}
						for(WorkLocation w:newList2){
							System.out.println(w);
						}
						if(newList2.size()<21){
							result.setData(gs.toJson(newList2));
						}else{
							result.setData(gs.toJson((newList2.subList(0, 21))));
							result.setData2(gs.toJson(newList2.subList(21, newList2.size())));
						}
						
						if(loc!=null&&loc.getLocationState()==1){
						    result.setMsg("调线成功,请即刻上线作业!");
						}else{
						    result.setMsg("调线成功，请前往"+line.getLineCode()+"线打卡上线!");
						}
						result.setData1(workerOne.getWorkerName());
						result.setState(1);
						return result;
				    }
			   }
			  Collections.sort(icode);
			  String newLocation=line.getLineCode()+(icode.get(icode.size()-1)+1);
			  String sjob="待定";
			  String lineCode=line.getLineCode();
			  Integer locationState=null;
              if(loc!=null&&loc.getLocationState()!=null){
            	  locationState=loc.getLocationState();
              }else{
                  locationState=0;
              }
			  Map<String,Object> map=new HashMap<String,Object>();
			  map.put("sjob", sjob);
			  map.put("lineCode", lineCode);
			  map.put("workerCode", worker.getWorkerCode());
			  map.put("locationState",locationState);
			  map.put("locationCode", newLocation);
			  int n=lineDao.addWorkLocation(map);
			  
			  if(n!=1){
			      throw new RuntimeException("新增工位失败!");
			  }
			  List<WorkLocation> newList3=new ArrayList<WorkLocation>();
			  clist=checkDao.findWorkerByCode(line);
			  for(WorkLocation w:clist){
				if(w.getWorker()!=null&&w.getWorker().getWid()!=null){
					newList3.add(w);
					}
				}
				for(WorkLocation w:newList3){
					System.out.println(w);
				}
				if(newList3.size()<21){
					result.setData(gs.toJson(newList3));
				}else{
					result.setData(gs.toJson(newList3.subList(0, 21)));
					result.setData2(gs.toJson(newList3.subList(21, newList3.size())));
				}
			  
			  if(locationState==1){
				  result.setMsg("调线成功,请即刻上线作业!");
			  }else{
				  result.setMsg("调线成功，请前往"+line.getLineCode()+"线打卡上线!");
			  }
			  result.setData1(workerOne.getWorkerName());
			  result.setState(1);
			  return result;
	       }
		  Map<String,Object> map=new HashMap<String,Object>();
		  map.put("sjob","待定");
		  map.put("lineCode", line.getLineCode());
		  map.put("workerCode", worker.getWorkerCode());
		  Integer locationState=null;
		  if(loc!=null&&loc.getLocationState()!=null){
        	  locationState=loc.getLocationState();
          }else{
              locationState=0;
          }
		  map.put("locationState",locationState);
		  map.put("locationCode",line.getLineCode()+1);
		  int n=lineDao.addWorkLocation(map);
		  List<WorkLocation> newList4=new ArrayList<WorkLocation>();
		    clist=checkDao.findWorkerByCode(line);
			for(WorkLocation w:clist){
				if(w.getWorker()!=null&&w.getWorker().getWid()!=null){
					newList4.add(w);
				}
			}
			for(WorkLocation w:newList4){
				System.out.println(w);
			}
			if(newList4.size()<21){
				result.setData(gs.toJson(newList4));
			}else{
				result.setData(gs.toJson(newList4.subList(0, 21)));
				result.setData2(gs.toJson(newList4.subList(21, newList4.size())));
			}
		  if(n!=1){
			  throw new RuntimeException("调线失败!");
		  }
		  if(locationState==1){
			  result.setMsg("调线成功,请即刻上线作业!");
		  }else{
			  result.setMsg("调线成功，请前往"+line.getLineCode()+"线打卡上线!");
		  }
		  result.setData1(workerOne.getWorkerName());
		  result.setState(1);
		  return result;
	}




    //app请假
	@Override
	public CyResult askForLeave(ProductWorker worker, Integer state) {
		CyResult result=new CyResult();
		Gson gs=new Gson();
		if(worker.getWorkerCode()==null||"".equals(worker.getWorkerCode())){
			result.setMsg("人员的工号不能为空");
			result.setState(0);
            return result;
		}
		if(state!=4){
			result.setMsg("状态码有误");
			result.setState(2);
            return result;
		}
		List<ProductWorker> wlist=workerDao.findWorker(worker);
		if(wlist==null||wlist.size()<1){
			result.setMsg("查无此人!请确认!");
			result.setState(3);
            return result;
		}
		ProductWorker workerOne=wlist.get(0);
		workerOne.setStateTwo(2);
		//System.out.println(workerOne.getLocation());
		WorkLocation loc=workerOne.getLocation();
		System.out.println(loc);
		if(loc!=null&&loc.getLocationState()==1){
		       NewCheck check=new NewCheck();
		       check.setNowDate(CyUtil.getTime2());
			   check.setLine(loc.getLine());
			   check.setWorker(workerOne);
			   ProductDetails pd=lineDao.findProductByLine(loc.getLine());
			   List<ProductDetails> pdList=lineDao.findProductById(pd);
			   check.setProduct(pdList.get(0));
			   List<NewCheck> list=newDao.findNewCheckByWorkerCode(check);
			   //将当日 当条产线 单个产品的打卡信息结算
			   for(NewCheck ck:list){
			      if(ck.getUpLine()!=null&&ck.getDownLine()==null) {
					 check.setCcid(ck.getCcid());
					 check.setDownLine(CyUtil.getTime1());
					 
					try {
					  int u=newDao.updateNewCheck(check);
					  System.out.println("u="+u);
					} catch (Exception e) {
					  throw new RuntimeException("工时结算异常!");
					}
				  }
			   }
		}
		if(workerOne.getLocation()!=null&&workerOne.getLocation().getLocationCode()!=null){
			Map<String,Object> map=new HashMap<String,Object>();
			map.put("locationState", 4);
			map.put("sid", workerOne.getLocation().getSid()); 
			int n=lineDao.updateLocation(map);
			workerOne.setStateTwo(2);
			int m=workerDao.updateWorker(workerOne); 
			if(n!=1&&m!=1){
				throw new RuntimeException("状态修改失败");
			}
			List<WorkLocation> newList=new ArrayList<WorkLocation>();
			List<WorkLocation> clist=checkDao.findWorkerByCode(workerOne.getLocation().getLine());
			for(WorkLocation w:clist){
				if(w.getWorker()!=null&&w.getWorker().getWid()!=null){
					newList.add(w);
				}
			}
			for(WorkLocation w:newList){
				System.out.println(w);
			}
			if(newList.size()<21){
				result.setData1(gs.toJson(newList));
			}else{
				result.setData1(gs.toJson(newList.subList(0, 21)));
				result.setData2(gs.toJson(newList.subList(21, newList.size())));
			}
			result.setMsg("工号"+workerOne.getWorkerCode()+workerOne.getWorkerName()+"请假成功!");
			result.setData(workerOne.getWorkerName());
			result.setState(1);
			return result;
		}
		
		result.setMsg("工号"+workerOne.getWorkerCode()+workerOne.getWorkerName()+"请假成功!");
		int m=workerDao.updateWorker(workerOne);
		if(m!=1){
			throw new RuntimeException("状态修改失败");
		}
		result.setData(workerOne.getWorkerName());
		result.setState(1);
		return result;
	}
	
	
}


   
	   