package com.watchmoreonline.services;

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
			return responses.setMsg(serialDao.findAll(serial.getPage()));
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
			return responses.setMsg(serialDao.getTvByStatus(serial.getStatus(),serial.getPage()));
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
			s.setName(serial.getName());
			s.setImage(serial.getImage());
			s.setStatus(serial.getStatus());
			s.setLanguage(serial.getLanguage());
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
}
