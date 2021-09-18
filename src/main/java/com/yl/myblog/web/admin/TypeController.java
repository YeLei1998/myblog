package com.yl.myblog.web.admin;

import com.yl.myblog.NotFoundException;
import com.yl.myblog.entity.Type;
import com.yl.myblog.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

/**
 * @Author YeLei
 * @Date 2021/09/18 9:06
 * @Version 1.0
 */

@Controller
@RequestMapping("/admin")
public class TypeController {

    @Autowired
    private TypeService typeService;

    /**
     * 每页10条数据
     * 根据id,倒序排序
     * @param pageable
     * @return
     */
    @GetMapping("/types")
    public String types(@PageableDefault(size = 5,sort = {"id"},
            direction = Sort.Direction.DESC) Pageable pageable, Model model){
        model.addAttribute("page",typeService.listType(pageable));
        return "admin/types";
    }

    /**
     * 跳转到分类页面
     * @return
     */
    @GetMapping("/types/input")
    public String input(Model model){
        //保证前端能获取到type,否则接收不到错误提示
        model.addAttribute("type",new Type());
        return "admin/types-input";
    }

    /**
     * 编辑分类名称
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/types/{id}/input")
    public String editInput(@PathVariable Long id, Model model){
        model.addAttribute("type",typeService.addType(id));
        return "admin/types-input";
    }

    /**
     * 新增分类
     * @param type
     * @return
     */
    @PostMapping("/types")
    public String submitType(@Valid Type type, BindingResult result, RedirectAttributes redirectAttributes){

        Type typeByName = typeService.getTypeByName(type.getName());
        if (typeByName != null){
            result.rejectValue("name","nameError","分类名称不能重复！");
        }
        if (result.hasErrors()){
            return "admin/types-input";
        }
        Type type1 = typeService.saveType(type);
        if (type1 == null){
            redirectAttributes.addFlashAttribute("message","新增失败！");
        }else {
            redirectAttributes.addFlashAttribute("message","新增成功！");
        }
        return "redirect:/admin/types";
    }

    /**
     * 更新分类
     * @param type
     * @param result
     * @param id
     * @param redirectAttributes
     * @return
     */
    @PostMapping("/types/{id}")
    public String editPost(@Valid Type type, BindingResult result, @PathVariable Long id,RedirectAttributes redirectAttributes){

        Type typeByName = typeService.getTypeByName(type.getName());
        if (typeByName != null){
            result.rejectValue("name","nameError","分类名称不能重复！");
        }
        if (result.hasErrors()){
            return "admin/types-input";
        }
        Type type1 = typeService.updateType(id,type);
        if (type1 == null){
            redirectAttributes.addFlashAttribute("message","更新失败！");
        }else {
            redirectAttributes.addFlashAttribute("message","更新成功！");
        }
        return "redirect:/admin/types";
    }

    /**
     * 删除分类
     * @param id
     * @return
     */
    @GetMapping("/types/{id}/delete")
    public String delete(@PathVariable Long id,RedirectAttributes redirectAttributes){
        typeService.deleteTpye(id);
        redirectAttributes.addFlashAttribute("message","删除成功！");
        return "redirect:/admin/types";
    }

}
