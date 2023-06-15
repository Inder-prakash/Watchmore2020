package com.watchmoreonline.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.watchmoreonline.serials.Serial;
import com.watchmoreonline.serials.SerialDao;

@Service
public class SerialService {
	
	@Autowired
	Responses responses;
	
	@Autowired
	SerialDao serialDao;
	
	
	public void countep() {
		List<Serial> s = serialDao.findAll();
		for(Serial ss:s) {
			List<String> li = ss.getElink();
			ss.setEpisodes(li.size());
			serialDao.update(ss);
		}
	}

	
	public Object addserial(Serial serial) {
		try {
			serialDao.insert(serial);
			return responses.setMsg(serial);
		}
		catch (Exception e) {
			return responses.setMsg(e.getMessage());
		}
	}
	
	public Object viewtv(Serial serial) {
		try {
			return serialDao.findAll(serial);
		}
		catch (Exception e) {
			return responses.setMsg(e.getMessage());
		}
	}

	public Object gettv(Serial serial) {
		try {
			return responses.setMsg(serialDao.find(serial.getId()));
		}
		catch (Exception e) {
			return responses.setMsg(e.getMessage());
		}
	}
	
	public Object getTvByStatus(Serial serial) {
		try {
			return serialDao.getTvByStatus(serial);
		}
		catch (Exception e) {
			return responses.setMsg(e.getMessage());
		}
	}
	
	public Object updatepisodes(Serial serial) {
		try {
			Serial s = serialDao.find(serial.getId());
			s.setElink(serial.getElink());
			s.setEname(serial.getEname());
			serialDao.update(s);
			return responses.setMsg(s);
		}
		catch (Exception e) {
			return responses.setMsg(e.getMessage());
		}
	}
	
	public Object updatetv(Serial serial) {
		try {
			Serial s = serialDao.find(serial.getId());
			if(serial.getName() != null) {
				s.setName(serial.getName());
			}
			if(serial.getImage() != null) {
				s.setImage(serial.getImage());
			}
			if(serial.getStatus() != null ) {
				s.setStatus(serial.getStatus());
			}
			if(serial.getLanguage() != null ) {
				s.setLanguage(serial.getLanguage());
			}
			serialDao.update(s);
			return responses.setMsg(s);
		}
		catch (Exception e) {
			return responses.setMsg(e.getMessage());
		}
	}
	
	public Object deletetv(Serial serial) {
		try {
			Serial s = serialDao.find(serial.getId());
			serialDao.delete(s);
			return responses.setMsg("Data deleted");
		}
		catch (Exception e) {
			return responses.setMsg(e.getMessage());
		}
	}
	
	public Object Serialsearch(Integer page ,String text) {
			return serialDao.search(page,text); 
	}
	public Object Serialsearch2(Integer page ,String text) {
		return serialDao.search2(page,text); 
	}

}
