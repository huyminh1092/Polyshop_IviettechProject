package poly.edu.shop.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import poly.edu.shop.domain.CartItem;
import poly.edu.shop.domain.Category;

@Service
public class SessionService {
	
	@Autowired
	HttpSession session;
	
	public void setAttributeMap(String name, Map<Long, CartItem> velueMap) {
		session.setAttribute(name, velueMap);
	}
	
	public void setAttributeList(String name, List<Category> value) {
		session.setAttribute(name, value);
	}
	
	public void setAttribute(String name, Object value) {
		session.setAttribute(name, value);
	}
	
	public Object getAttribute(String name) {
		return session.getAttribute(name);
	}
	
	public void removeAttribute(String name) {
		session.removeAttribute(name);
	}
}
