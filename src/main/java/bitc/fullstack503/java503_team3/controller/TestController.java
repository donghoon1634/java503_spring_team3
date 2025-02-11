package bitc.fullstack503.java503_team3.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
@Controller
public class TestController
{
  @GetMapping ("/")
  public ModelAndView main () throws Exception
  {
    return new ModelAndView ("/main");
  }
  
  @GetMapping ("/login")
  public ModelAndView login () throws Exception
  {
    return new ModelAndView ("/login/login");
  }
  
  @GetMapping ("/signup")
  public ModelAndView singup () throws Exception
  {
    return new ModelAndView ("/login/signup");
  }
  
  @GetMapping ("/join")
  public ModelAndView join () throws Exception
  {
    return new ModelAndView ("/login/join");
  }
  
  @GetMapping ("/footer")
  public ModelAndView footer () throws Exception
  {
    return new ModelAndView ("/layout/footer");
  }
  
  @GetMapping ("/header")
  public ModelAndView header () throws Exception
  {
    return new ModelAndView ("/layout/header");
  }
}
