package poly.edu.shop.controller.admin;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import poly.edu.shop.domain.Category;
import poly.edu.shop.domain.Product;
import poly.edu.shop.model.CategoryDTO;
import poly.edu.shop.model.ProductDTO;
import poly.edu.shop.service.CategoryService;
import poly.edu.shop.service.ProductService;
import poly.edu.shop.service.StorageService;
import poly.edu.shop.service.UploadService;

@Controller
@RequestMapping("admin/product")
public class ProductController {
	
	@Autowired
	CategoryService categoryService;
	@Autowired
	ProductService productService;
	@Autowired
	StorageService storageService;
	@Autowired
	UploadService uploadService;
	
	
	@ModelAttribute("categories")
	public List<CategoryDTO> getCategories() {
		return categoryService.findAll().stream().map(item->{
			CategoryDTO dto = new CategoryDTO();
			BeanUtils.copyProperties(item, dto);
			return dto;
		}).toList();
	}
	
	@GetMapping("add")
	public String add(Model model) {
		ProductDTO dto = new ProductDTO();
		dto.setCheckEdit(false);
		model.addAttribute("product", dto);
		
		return "admin/products/addOrEdit";
	}
	
	@GetMapping("edit/{productId}")
	public ModelAndView edit(@PathVariable("productId") Long productId,
		ModelMap model) {
		Optional<Product> opt = productService.findById(productId);
		ProductDTO dto = new ProductDTO();
		
		if (opt.isPresent()) {
			Product entity = opt.get();
			BeanUtils.copyProperties(entity, dto);
			
			dto.setCategory(entity.getCategory().getCategoryId());
			dto.setCheckEdit(true);
			
			model.addAttribute("product", dto);
			return new ModelAndView("admin/products/addOrEdit", model);
		}
		
		model.addAttribute("error", "Mã danh mục không tồn tại");
		return new ModelAndView("forward:/admin/product", model); 
	}
	
//	@GetMapping("/image/{filename:.+}")
//	@ResponseBody
//	public ResponseEntity<Resource> serveFile(@PathVariable String filename){
//		Resource file = storageService.loadAsResource(filename);
//		
//		return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, 
//				"attachment; filename=\""+file.getFilename() + "\"").body(file);
//	}
	
	@PostMapping("saveOrUpdate")
	public ModelAndView saveOrUpdate(ModelMap model,
			@Valid @ModelAttribute("product") ProductDTO dto, 
			BindingResult result) throws IOException {
		if (result.hasErrors() || dto.getImageFile().isEmpty()) {
			model.addAttribute("errorImage", "Vui lòng chọn hình ảnh!");
			
			return new ModelAndView("admin/products/addOrEdit", model);
		}
		
		Product entity = new Product();
		BeanUtils.copyProperties(dto, entity);
		
		Category category = new Category();
		category.setCategoryId(dto.getCategory());
		entity.setCategory(category);
		
		//xu ly Image
//		UUID uuid = UUID.randomUUID();
//		String uuString = uuid.toString();
//		
//		entity.setImage(storageService.getStoredFileName(dto.getImageFile(), uuString));
//		storageService.store(dto.getImageFile(), entity.getImage());
		String fileName = StringUtils.cleanPath(dto.getImageFile().getOriginalFilename());
		String uploadDir = "upload/";
		uploadService.saveFile(dto.getImageFile(), uploadDir, fileName);

		entity.setImage(fileName);
		
		productService.save(entity);
		
		model.addAttribute("message", "Lưu thành công");
		
		return new ModelAndView("redirect:/admin/product", model);
	}
	
	@GetMapping("delete/{productId}")
	public ModelAndView delete(@PathVariable("productId") Long productId, 
			ModelMap model) {
		
		Optional<Product> opt = productService.findById(productId);
		if (opt.isPresent()) {
			productService.delete(opt.get());
			model.addAttribute("message", "Xóa thành công");
		}else {
			model.addAttribute("error", "Sản phẩm không tồn tại. Xóa thất bại!");
		}
		
		return new ModelAndView("forward:/admin/product", model); 
	}
	
	
	@GetMapping("")
	public String search(ModelMap model,
			@RequestParam(name = "name", required = false) String name,
			@RequestParam("page") Optional<Integer> page,
			@RequestParam("size") Optional<Integer> size) {
		
		int currentPage = page.orElse(1);
		Integer pageSize = size.orElse(5);
		
		Pageable pageable = PageRequest.of(currentPage - 1, pageSize, Sort.by("productId"));
		Page<Product> resultPage = null;
		
		
			resultPage = productService.findAll(pageable);
		
		
		int totalPages = resultPage.getTotalPages();
		if (totalPages > 0) {
			int start = Math.max(1, currentPage - 2);
			int end = Math.min(currentPage + 2, totalPages); 
			
			if (totalPages > 5) {
				if (end == totalPages){
					start = end - 5;
				}else if (start == 1) {
					end = start + 5;
				}
			}
			List<Integer> pageNumbers = IntStream.rangeClosed(start, end)
					.boxed()
					.collect(Collectors.toList());
			
			model.addAttribute("pageNumbers", pageNumbers);
		}
		
		model.addAttribute("productPage", resultPage);
		
		return "admin/products/list";
	}
	
	
}
