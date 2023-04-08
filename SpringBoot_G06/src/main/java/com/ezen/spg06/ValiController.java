package com.ezen.spg06;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ValiController {
	@RequestMapping("/")
	public String main() {
		return "startPage";
	}
	
	@RequestMapping("/create")
	public String create(@ModelAttribute("dto") ContentDto contentdto,
			Model model, BindingResult result) {
		// 매개변수에 Dto 객체를 변수로 넣으면 전달되어 지는 파라미터들이 객체내의 동일한 이름의
		// 멤버변수에 자동 대입됩니다.
		// wireter -> contentdto.writer       content -> contentdto.content
		
		// 추가로 contentdto 라는 이름으로 객체를 model 객체에 추가되서 최종 jsp 파일에 전달됩니다.
		
		// 매개변수 객체(매개변수) 앞에 ModelAttribute(전달이름)을 붙이면,
		// return 되는 페이지에 해당 객체가 . model.addAttribute 로 넣은것처럼
		// 같이 전달됩니다. model.addAttribute("dto", contentdto); 와 동일한 동작
		
		//ContenValidator:오류 검출기능을갖춘 사용자 지정클래스
	      
		ContentValidator validator= new ContentValidator();
		validator.validate(contentdto,result);
	      
	    //BindinResult:키값(제목)과 벨류값(내용)으로 구성된 오류 내용 저장 클래스
	    //validate 메서드는 contentdto와  BindingResult 형태의 래퍼런스 변수를 매개변수로 넣고시작
	    //validator의 멤버메서드인 validate가 contentdto내용을 검사한후 result에 오류내용을 담아주고
	    //리턴되지않아도 call by reference 이기때문에 오류내용을 현재 위치에서도result 라는 이름으로 사용이가능합니다
		
		// 전달된 파라미터가 비어있더니 널이라면, 그 값을 갖고 원래의 페이지로 돌아가고,
		// 유효안 데이터라면 DonePage.jsp 로 전송됩니다.
		/*
		 * if(true) { model.addAttribute("message","writer 또는 content를 입력하세요."); return
		 * "startPage"; }
		 */
		if(result.hasErrors()) {
			model.addAttribute("message","writer 또는 content를 입력하세요");
			return "startPage";
		}
		return "DonePage";
	}
	
	
}
