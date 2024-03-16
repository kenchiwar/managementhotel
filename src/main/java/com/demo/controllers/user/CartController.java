package com.demo.controllers.user;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.demo.entities.*;
import com.demo.services.CartService;
import com.demo.services.RoomService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("cart")
public class CartController {

    @Autowired
    private RoomService roomService;

    @Autowired
    private CartService cartService;

     @RequestMapping(value = {"index", "", "/"}, method = RequestMethod.GET)
        public String index(HttpSession session, ModelMap modelMap, @ModelAttribute("bill") Bill bill) {
            if(session.getAttribute("cart") == null){
            
                List<Item> cart = null;
                
                // session.setAttribute("cart_item", cart);
                modelMap.put("carts", cart);
            }else{
                Item item = (Item) session.getAttribute("cart");
                List<Item> cart = (List<Item>) session.getAttribute("cart");

                // cart.add(item);
                int index = cartService.exists(item.getRoom().getId(), cart);
                if(index == -1){
                    cart.add(item);
                    modelMap.put("carts", cart);

                }
                else{

                    // int quantity = cart.get(index).getQuantity_item() + 1;
                    // cart.get(index).setQuantity_item(quantity);
                    modelMap.put("cart_item", item);
                }
                // session.setAttribute("cart_item", cart);
            }
    
        return "user/cart/index";
    }

     @RequestMapping(value = {"update"}, method = RequestMethod.POST)
    public String update(@RequestParam("quantities") int[] quantities,HttpSession session, @ModelAttribute("bill") Bill bill){
         List<Item> cart = (List<Item>)session.getAttribute("cart");
        for(int i=0; i<cart.size(); i++){
            cart.get(i).setQuantity_item(quantities[i]);
        }
        session.setAttribute("cart", cart);
        return "redirect:/cart/index";
    }

    @RequestMapping(value = {"bills"}, method = RequestMethod.GET)
    public String addToCart( HttpSession session, ModelMap modelMap){
        // Room room = roomService.find(id);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
            
 	        if(session.getAttribute("cart") == null){
            
            List<Item> cart = null;
            
            session.setAttribute("cart_item", cart);
        }else{
            Item item = (Item) session.getAttribute("cart");
            List<Item> cart = (List<Item>) session.getAttribute("cart");
            cart.add(item);
            // int index = cartService.exists(item.getRoom().getId(), cart);
            // if(index == -1){
            //     cart.add(item);
            // }else{
            //     int quantity = cart.get(index).getQuantity_item() + 1;
            //     cart.get(index).setQuantity_item(quantity);
            // }
            session.setAttribute("cart_item", cart);
        }

        
        return "redirect:/cart/index";
    }

    @RequestMapping(value = {"remove/{index}"}, method = RequestMethod.GET)
    public String remove(@PathVariable("index") int index, HttpSession session){
         List<Item> cart = (List<Item>)session.getAttribute("cart");
         cart.remove(index);
         session.setAttribute("cart_item", cart);
        return "redirect:/cart/index";
    }

}
