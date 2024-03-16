package com.demo.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.demo.entities.Item;

@Service
public class CartServiceImpl implements CartService{

    @Override
    public int exists(int id, List<Item> cart) {
        for(int i = 0; i < cart.size(); i++){
            if(cart.get(i).getRoom().getId() == id){
                return i;
            }
        }
        return -1;
    }

    // @Override
    // public double total(List<Item> cart) {
    //     double s = 0;
    //     for(Item i : cart){
    //         s += i.getRoom().getPriceDiscount() * i.getQuantity_item();
    //     }
    //     return s;
    // }
   
}
