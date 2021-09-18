package com.yl.myblog.web.admin;

import com.yl.myblog.entity.Tag;
import com.yl.myblog.service.TagService;
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
public class TagController {

    @Autowired
    private TagService tagService;

    /**
     * 每页10条数据
     * 根据id,倒序排序
     * @param pageable
     * @return
     */
    @GetMapping("/tags")
    public String tags(@PageableDefault(size = 5,sort = {"id"},
            direction = Sort.Direction.DESC) Pageable pageable, Model model){
        model.addAttribute("page",tagService.listTag(pageable));
        return "admin/tags";
    }

    /**
     * 跳转到标签页面
     * @return
     */
    @GetMapping("/tags/input")
    public String input(Model model){
        //保证前端能获取到tag,否则接收不到错误提示
        model.addAttribute("tag",new Tag());
        return "admin/tags-input";
    }

    /**
     * 编辑分类名称
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/tags/{id}/input")
    public String editInput(@PathVariable Long id, Model model){
        model.addAttribute("tag",tagService.addTag(id));
        return "admin/tags-input";
    }

    /**
     * 新增分类
     * @param tag
     * @return
     */
    @PostMapping("/tags")
    public String submitTag(@Valid Tag tag, BindingResult result, RedirectAttributes redirectAttributes){

        Tag tagByName = tagService.getTagByName(tag.getName());
        if (tagByName != null){
            result.rejectValue("name","nameError","分类名称不能重复！");
        }
        if (result.hasErrors()){
            return "admin/tags-input";
        }
        Tag tag1 = tagService.saveTag(tag);
        if (tag1 == null){
            redirectAttributes.addFlashAttribute("message","新增失败！");
        }else {
            redirectAttributes.addFlashAttribute("message","新增成功！");
        }
        return "redirect:/admin/tags";
    }

    /**
     * 更新分类
     * @param tag
     * @param result
     * @param id
     * @param redirectAttributes
     * @return
     */
    @PostMapping("/tags/{id}")
    public String editPost(@Valid Tag tag, BindingResult result, @PathVariable Long id,RedirectAttributes redirectAttributes){

        Tag tagByName = tagService.getTagByName(tag.getName());
        if (tagByName != null){
            result.rejectValue("name","nameError","分类名称不能重复！");
        }
        if (result.hasErrors()){
            return "admin/tags-input";
        }
        Tag tag1 = tagService.updateTag(id,tag);
        if (tag1 == null){
            redirectAttributes.addFlashAttribute("message","更新失败！");
        }else {
            redirectAttributes.addFlashAttribute("message","更新成功！");
        }
        return "redirect:/admin/tags";
    }

    /**
     * 删除分类
     * @param id
     * @return
     */
    @GetMapping("/tags/{id}/delete")
    public String delete(@PathVariable Long id,RedirectAttributes redirectAttributes){
        tagService.deleteTag(id);
        redirectAttributes.addFlashAttribute("message","删除成功！");
        return "redirect:/admin/tags";
    }

}
