package com.watchmoreonline.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.watchmoreonline.sequals.SequalsDao;

@Service
public class SequalsService {
	
	@Autowired
	SequalsDao sequalsDao;
	
}
