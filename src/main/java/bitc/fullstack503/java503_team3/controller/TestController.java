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
