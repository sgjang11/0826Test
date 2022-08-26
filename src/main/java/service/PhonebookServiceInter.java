package service;

import java.util.List;

import vo.PhonebookVO;

public interface PhonebookServiceInter {
	public int insert(PhonebookVO pb);
	public List<PhonebookVO> getList();
	public List<PhonebookVO> find(String search);
	public PhonebookVO findOne(int _idx);
	public int update(PhonebookVO pb);
	public int delete(int idx);
}
