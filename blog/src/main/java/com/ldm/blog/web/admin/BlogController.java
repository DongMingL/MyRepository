package com.ldm.blog.web.admin;

import com.ldm.blog.po.Blog;
import com.ldm.blog.po.Tag;
import com.ldm.blog.po.User;
import com.ldm.blog.service.TagService;
import com.ldm.blog.vo.BlogQuery;
import com.ldm.blog.service.BlogService;
import com.ldm.blog.service.TypeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class BlogController {


    @Autowired
    private BlogService blogService;

    @Autowired
    private TypeService typeService;

    @Autowired
    private TagService tagService;



    @GetMapping("/blogs")
    public String Blog(@PageableDefault(size = 2, sort = {"updateTime"}, direction = Sort.Direction.DESC)
                                   Pageable pageable,BlogQuery blog, Model model){
        model.addAttribute("types",typeService.listType());
        model.addAttribute("page",blogService.listBlog(pageable, blog));
        return "/admin/blogs";
    }
    //查询表内数据
    @PostMapping("/blogs/search")
    public String search(@PageableDefault(size = 2, sort = {"updateTime"}, direction = Sort.Direction.DESC)
                                     Pageable pageable,Model model,BlogQuery blog ){
        model.addAttribute("page",blogService.listBlog(pageable, blog));
        return "/admin/blogs :: blogList";
    }
//跳转新增页面
    @GetMapping("/blogs/input")
    public String input(Model model) {
        model.addAttribute("types" , typeService.listType());
        model.addAttribute("tags" ,  tagService.listTag());
        model.addAttribute("blog",new Blog());
        return "/admin/blogs-input";
    }

    private void setTypeAndTag(Model model){
        model.addAttribute("types" , typeService.listType());
        model.addAttribute("tags" ,  tagService.listTag());

    }
    //编辑页面
    @GetMapping("/blogs/{id}/input")
    public String editInput(@PathVariable Long id,Model model) {
        setTypeAndTag(model);
        Blog blog = blogService.getBlog(id);
        blog.init();
        model.addAttribute("blog",blogService.getBlog(id));
        return "/admin/blogs-input";
    }



    //新增博客啦，我自己写的。
    //可以自己做一个非空校验
    @PostMapping("/blogs")
    public String post(Blog blog,  RedirectAttributes attributes,HttpSession session){
        //当前登陆用户的对象
        blog.setUser((User) session.getAttribute("user"));
        //拿到type和tag去保存
        blog.setType(typeService.getType(blog.getType().getId()));
        blog.setTags(tagService.listTag(blog.getTagIds()));

        Blog b = blogService.saveBlog(blog);
        if(b == null){
            attributes.addFlashAttribute("message","操作失败");
        }
        else{
            attributes.addFlashAttribute("message","操作成功");
        }
        //提交成功，返回到这个页面
        return "redirect:/admin/blogs";
    }


    @GetMapping("/blogs/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes attributes){
        blogService.deleteBlog(id);
        attributes.addFlashAttribute("message","删除成功");
        return "redirect:/admin/blogs";
    }
}
