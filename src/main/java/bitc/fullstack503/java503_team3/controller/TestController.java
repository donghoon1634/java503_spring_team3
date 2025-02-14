package bitc.fullstack503.java503_team3.controller;
import bitc.fullstack503.java503_team3.service.LoadAddrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
@Controller
public class TestController
{
  @Autowired
  private LoadAddrService loadAddrService;
  
  @GetMapping ("/")
  public ModelAndView main () throws Exception
  {
    return new ModelAndView ("/main");
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
  
  @GetMapping ("/test")
  public ModelAndView test () throws Exception
  {
    ModelAndView mv = new ModelAndView ("/test");
    List<String> guList = loadAddrService.selectLoadAddrGu ();
    mv.addObject ("guList", guList);
    return mv;
  }
  
  @GetMapping ("/test/getDong")
  @ResponseBody
  public List<String> getSelectedValue (@RequestParam ("gu") String gu) throws Exception
  {
    return loadAddrService.selectLoadAddrDong (gu);
  }
  
  @GetMapping ("/test/getRo")
  @ResponseBody
  public List<String> getSelectedValue (@RequestParam ("gu") String gu, @RequestParam ("dong") String dong) throws Exception
  {
    return loadAddrService.selectLoadAddrRo (gu, dong);
  }
  
  @GetMapping ("/test/getMainNum")
  @ResponseBody
  public List<String> getSelectedValue (@RequestParam ("gu") String gu, @RequestParam ("dong") String dong, @RequestParam ("ro") String ro) throws Exception
  {
    return loadAddrService.selectLoadAddrMainNum (gu, dong, ro);
  }
  
  @GetMapping ("/test/getSubNum")
  @ResponseBody
  public List<String> getSelectedValue (@RequestParam ("gu") String gu, @RequestParam ("dong") String dong, @RequestParam ("ro") String ro, @RequestParam ("mainNum") String mainNum) throws Exception
  {
    return loadAddrService.selectLoadAddrSubNum (gu, dong, ro, mainNum);
  }
}
