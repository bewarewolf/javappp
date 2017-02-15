package com.nixsolutions.university.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nixsolutions.university.dao.JournalDAO;
import com.nixsolutions.university.dto.JournalDTO;

@Service("journalService")
public class JournalService {

  @Autowired
  JournalDAO journalDao;
  
  public List<JournalDTO> getAll() {
    return JournalDTO.convertFromJournal(journalDao.getAll());
  }
  
  @Transactional
  public void delete(Integer id) {
    journalDao.delete(id);
  }
}
