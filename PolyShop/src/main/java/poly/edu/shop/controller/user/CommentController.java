package poly.edu.shop.controller.user;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import poly.edu.shop.domain.Account;
import poly.edu.shop.domain.Comments;
import poly.edu.shop.domain.Product;
import poly.edu.shop.service.AccountService;
import poly.edu.shop.service.CommentService;
import poly.edu.shop.service.SessionService;

@Controller
@RequestMapping("product")
public class CommentController {
	
	@Autowired
	SessionService sessionService;
	
	@Autowired
	AccountService accountService;
	
	@Autowired
	CommentService commentService;
	
	@Autowired
	HttpServletRequest httpRequest;
    
	@PostMapping("comment")
	public String comment(Model model, @RequestParam("comment") String comment) {
     Account account = (Account) sessionService.getAttribute("username");
		
		if (account == null) {
			return "redirect:/login";
		}
		Comments com=new Comments();
		Product pro=(Product)sessionService.getAttribute("product_comment");
		com.setDate(new Date());
		com.setContent(comment);
		com.setStatus(0);
		com.setAccount(account);
	    com.setProduct(pro);
	    com.setStar(5);
		commentService.save(com);
		List<Comments> listcm=commentService.findProductComment(pro.getProductId());
		model.addAttribute("product", pro);
		model.addAttribute("lscomment", listcm);
		return "site/products/detail";
	}
}
