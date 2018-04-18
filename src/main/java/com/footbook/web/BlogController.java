package com.footbook.web;

import com.footbook.model.Blog;
import com.footbook.model.User;
import com.footbook.repository.UserRepository;
import com.footbook.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.List;


@Controller
public class BlogController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BlogService blogService;
    @Autowired
    private ServletContext servletContext;

    @RequestMapping(value="/addPost",method= RequestMethod.POST)
    public String post(@ModelAttribute("newBlog") Blog newBlog,@RequestParam("file") MultipartFile file) throws IOException {
        User user = userRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        Long userId=user.getId();
        newBlog.setUserId(userId);
        // Upload image to server
        String contextPath=servletContext.getRealPath("/");
        List<Blog> blogs=blogService.findByUserId(userId);
        int blogSize=blogs.size();
        String fileName=user.getUsername()+"Blog"+blogSize+".jpg";
        String destinationPath=contextPath+"/resources/img/blogImages/"+fileName;
        BufferedImage src = ImageIO.read(new ByteArrayInputStream(file.getBytes()));
        File destination = new File(destinationPath);
        ImageIO.write(src, "jpg", destination);

        //Save Image URL to DB
        newBlog.setImage("/resources/img/blogImages/"+fileName);
        blogService.save(newBlog);

        return "redirect:/welcome";
    }

}
