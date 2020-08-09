package jp.kobe_u.cs.daikibo.tsubuyaki.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import jp.kobe_u.cs.daikibo.tsubuyaki.service.TsubuyakiService;
import jp.kobe_u.cs.daikibo.tsubuyaki.entity.Tsubuyaki;
@Controller

public class TsubuyakiController {
    @Autowired
	public
    TsubuyakiService ts;
    @GetMapping("/")
    String showIndex(){
        return "index";
    }
    @GetMapping("/read")
    String showTsubuyakiList(Model model) {
        ArrayList<Tsubuyaki> list = ts.getAllTsubuyaki(); //全つぶやきを取得
        model.addAttribute("tsubuyakiList", list);   //モデル属性にリストをセット
        model.addAttribute("tsubuyakiForm", new TsubuyakiForm());  //空フォームをセット
        model.addAttribute("searchForm", new SearchForm());  //空フォームをセット
        return "tsubuyaki_list"; //リスト画面を返す
    }
    @PostMapping("/read")
    String postTsubuyaki(@ModelAttribute("tsubuyakiForm") TsubuyakiForm form, Model model) {
        Tsubuyaki t = new Tsubuyaki();
        t.setName(form.getName());
        t.setComment(form.getComment());
        ts.postTsubuyaki(t);
        return "redirect:/read";
    }
    @PostMapping("/search")
    String showSearchedList(@ModelAttribute("searchForm") SearchForm form,Model model) {
        ArrayList<Tsubuyaki> list = ts.searchTsubuyaki(form.getWord()); //全つぶやきを取得
        model.addAttribute("searchedList", list);   //モデル属性にリストをセット
        return "searched_list"; //リスト画面を返す
    }


}
