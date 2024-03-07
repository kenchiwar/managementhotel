package com.demo.configurations;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.AbstractConverter;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.TypeToken;
import org.modelmapper.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import com.demo.dtos.AccountDTO;
import com.demo.dtos.BillDTO;
import com.demo.dtos.BillDetailDTO;
import com.demo.dtos.CategoryimageDTO;
import com.demo.dtos.EvaluateDTO;
import com.demo.dtos.HotelDTO;
import com.demo.dtos.ImageDTO;
import com.demo.dtos.RoomDTO;
import com.demo.entities.Account;
import com.demo.entities.Bill;
import com.demo.entities.BillDetail;
import com.demo.entities.Categoryimage;
import com.demo.entities.Evaluate;
import com.demo.entities.Hotel;
import com.demo.entities.Image;
import com.demo.entities.Payment;
import com.demo.entities.Room;
@Configuration
public class ModelMapperConfiguration {
	   @Autowired
	    Environment environment;
	   @Bean
	    public ModelMapper modelMapper() {
	        ModelMapper mapper = new ModelMapper();
	        // import org.modelmapper.Converter; import org.modelmapper.AbstractConverter;
	        // Trường hợp cần import phức tạp
//	        Converter<List<T>, Set<A>> converterBooleanTOString = new AbstractConverter<List<T>, Set<A>>() {
//	            @Override
//	            protected String convert(Boolean source) {
//	                // TODO Auto-generated method stub
//	                return "fff";
//	            }
//	        };
	        Converter<Boolean, Boolean> converterImage = new AbstractConverter<Boolean, Boolean>() {
	            @Override
	            protected Boolean convert(Boolean source) {

	                return source!=null ?source :false;
	            }
	        };
	        
	        // Trường hợp mapper đặc biệt
//	        mapper.typeMap(Product.class, ProductDTO.class).addMappings(m -> {
//	            m.using(converterBooleanTOString).map(Product::isStatus, ProductDTO::setPhone);
//	            m.using(converterImage).map(Product::getImage, ProductDTO::setPhone);
//	        });
//	        mapper.addMappings(new PropertyMap<Evaluate, EvaluateDTO>() {
//
//	            @Override
//	            protected void configure() {
//	            	
//	            	map().setId(source.getId());
//	              //Thiếu hotel valute cần chỉnh
//	                map().setNumber(source.getNumber());
//	                map().setComment(source.getComment());
//	                map().setCreated(source.getCreated());
//	                map().setStatus(source.isStatus()); // boolean uses isStatus()
//	                map().setIdAccount(source.getIdAccount());
//	                map().setIdBill(source.getIdBill());
//	                map().setNameAccount(source.getNameAccount());
//	            }
//
//	        });
	        mapper.addMappings(new PropertyMap<Image, ImageDTO>() {

	            @Override
	            protected void configure() {
	           	 map().setId(source.getId());
	           	map().setName(source.getName()+"bbbbbbb");
	            
	            }

	        });
	        mapper.addMappings(new PropertyMap<Categoryimage, CategoryimageDTO>() {

	            @Override
	            protected void configure() {
	           	 map().setId(source.getId());
	           	map().setName(source.getName());
	           	
	           
	          //	List<ImageDTO> a = mapper.map(source.getImages(),  new TypeToken<List<ImageDTO>>(){}.getType());
	          	//map().setImages(a);
	           	
	            }

	        });
	        mapper.addMappings(new PropertyMap<Room, RoomDTO>() {

	            @Override
	            protected void configure() {
	            	  map().setId(source.getId());
	            	 
	                  map().setName(source.getName());
	                  map().setStatus(source.getStatus());
	                  map().setPrice(source.getPrice());
	                  map().setPriceDiscount(source.getPriceDiscount());
	                  map().setReasonDiscount(source.getReasonDiscount());
	                  map().setRoomMax(source.getRoomMax());
	                  map().setRoomNow(source.getRoomNow());
	                  map().setPeopleMin(source.getPeopleMin());
	                  map().setPeopleMax(source.getPeopleMax());
	                  map().setDescribes(source.getDescribes());
	            }

	        });
	        mapper.addMappings(new PropertyMap<RoomDTO, Room>() {

	            @Override
	            protected void configure() {
	                map().setId(source.getId());
	                map().setHotel(new Hotel(source.getHotelId()));
	                map().setName(source.getName());
	                map().setStatus(source.getStatus());
	                map().setPrice(source.getPrice());
	                map().setPriceDiscount(source.getPriceDiscount());
	                map().setReasonDiscount(source.getReasonDiscount());
	                map().setRoomMax(source.getRoomMax());
	                map().setRoomNow(source.getRoomNow());
	                map().setPeopleMin(source.getPeopleMin());
	                map().setPeopleMax(source.getPeopleMax());
	                map().setDescribes(source.getDescribes());
	            }

	        });

//	        mapper.typeMap(Evaluate.class, EvaluateDTO.class).addMappings(m -> {
//	            // Direct mappings
//	            m.map(Evaluate::getId, EvaluateDTO::setId);
//	            m.map(Evaluate::getNumber, EvaluateDTO::setNumber);
//	            m.map(Evaluate::getComment, EvaluateDTO::setComment);
//	            m.map(Evaluate::getCreated, EvaluateDTO::setCreated);
//	           
//	            m.map(Evaluate::getIdAccount, EvaluateDTO::setIdAccount);
//	            m.map(Evaluate::getIdBill, EvaluateDTO::setIdBill);
//	            m.map(Evaluate::getNameAccount, EvaluateDTO::setNameAccount);
//	            // Optional null safety (consider adding based on requirements)
//	            
//	        });
	        mapper.addMappings(new PropertyMap<Evaluate, EvaluateDTO>() {

	            @Override
	            protected void configure() {
	            	 map().setId(source.getId());
	               
	                 map().setNumber(source.getNumber()); 
	                 map().setComment(source.getComment()); 
	                 map().setCreated(source.getCreated()); 
	                 map().setStatus(source.isStatus());
	                 map().setIdAccount(source.getIdAccount()); 
	                 map().setIdBill(source.getIdBill()); 
	                 map().setNameAccount(source.getNameAccount()); 
	            }

	        });
	      
	        mapper.addMappings(new PropertyMap<EvaluateDTO, Evaluate>() {

	            @Override
	            protected void configure() {
	                map().setId(source.getId());
	                map().setNumber(source.getNumber());
	                map().setComment(source.getComment());
	                map().setCreated(source.getCreated());
	                map().setStatus(source.isStatus());
	                map().setIdAccount(source.getIdAccount());
	                map().setIdBill(source.getIdBill());
	                map().setNameAccount(source.getNameAccount());
	            }

	        });

	        mapper.addMappings(new PropertyMap<Hotel, HotelDTO>() {

	            @Override
	            protected void configure() {
	            	  map().setIdAccount(source.getIdAccount());
	                  map().setName(source.getName()); // Assuming you want to map name again
	                  map().setCancellationPolicy(source.getCancellationPolicy());
	                  map().setDescription(source.getDescription());
	                  map().setRating(source.getRating());
	                  map().setManager(source.getManager());
	                 // map().setStatus(source.getStatus()); // boolean uses isStatus()
	                  map().setMainPhoto(source.getMainPhoto());
	                  map().setSecondaryPhoto(source.getSecondaryPhoto());
	                  map().setPapers(source.getPapers());
	                  map().setRegulation(source.getRegulation());
	                  map().setIdHandler(source.getIdHandler());
	                  map().setAddress(source.getAddress());
	                 
	            }

	        });
	        mapper.addMappings(new PropertyMap<HotelDTO, Hotel>() {

	            @Override
	            protected void configure() {
	            	  map().setIdAccount(source.getIdAccount());
	                  map().setName(source.getName()); // Assuming you want to map name again
	                  map().setCancellationPolicy(source.getCancellationPolicy());
	                  map().setDescription(source.getDescription());
	                  map().setRating(source.getRating());
	                  map().setManager(source.getManager());
	                 // map().setStatus(source.getStatus()); // boolean uses isStatus()
	                  map().setMainPhoto(source.getMainPhoto());
	                  map().setSecondaryPhoto(source.getSecondaryPhoto());
	                  map().setPapers(source.getPapers());
	                  map().setRegulation(source.getRegulation());
	                  map().setIdHandler(source.getIdHandler());
	                  map().setAddress(source.getAddress());
	                 
	            }

	        });
	        mapper.addMappings(new PropertyMap<Account, AccountDTO>() {

	            @Override
	            protected void configure() {
	            	map().setId(source.getId());
	            	map().setRoleId(source.getRole().getId()); // Assuming source has a getter for roleName
	                map().setRoleName(source.getRole().getName()); // Assuming source has a getter for roleName
	                map().setFirstName(source.getFirstName()); // Assuming source has a getter for firstName
	                map().setLastName(source.getLastName()); // Assuming source has a getter for lastName
	                map().setEmail(source.getEmail()); // Assuming source has a getter for email
	                map().setPassword(source.getPassword()); // Assuming source has a getter for password (security concern, consider hashing password before mapping)
	                map().setPhone(source.getPhone()); // Assuming source has a getter for phone
	                map().setImage(source.getImage()); // Assuming source has a getter for image
	                map().setActive(source.getActive()); // Assuming source has a getter for active
	                 
	            }

	        });
	        mapper.addMappings(new PropertyMap<AccountDTO, Account>() {

	            @Override
	            protected void configure() {
	            	map().setId(source.getId());
	                
	               
	                map().setFirstName(source.getFirstName()); // Assuming source has a getter for firstName
	                map().setLastName(source.getLastName()); // Assuming source has a getter for lastName
	                map().setEmail(source.getEmail()); // Assuming source has a getter for email
	                map().setPassword(source.getPassword()); // Assuming source has a getter for password (security concern, consider hashing password before mapping)
	                map().setPhone(source.getPhone()); // Assuming source has a getter for phone
	                map().setImage(source.getImage()); // Assuming source has a getter for image
	                map().setActive(source.getActive()); // Assuming source has a getter for active
	                 
	            }

	        });
	        mapper.addMappings(new PropertyMap<BillDetail, BillDetailDTO>() {

	            @Override
	            protected void configure() {
	                map().setId(source.getId());
	                
	                map().setRoomName(source.getRoom().getName());
	                // Map remaining properties
	                map().setPrice(source.getPrice());
	                map().setPriceDiscount(source.getPriceDiscount());
	                map().setNumberDay(source.getNumberDay());
	                map().setTotal(source.getTotal());
	                map().setReasonDiscount(source.getReasonDiscount());
	                map().setNumberHour(source.getNumberHour());

	                 
	            }

	        });
	        mapper.addMappings(new PropertyMap<BillDetailDTO,BillDetail>() {

	            @Override
	            protected void configure() {
	                map().setId(source.getId());
	                
	                
	                // Map remaining properties
	                map().setPrice(source.getPrice());
	                map().setPriceDiscount(source.getPriceDiscount());
	                map().setNumberDay(source.getNumberDay());
	                map().setTotal(source.getTotal());
	                map().setReasonDiscount(source.getReasonDiscount());
	                map().setNumberHour(source.getNumberHour());

	                 
	            }

	        });
	       
	        mapper.addMappings(new PropertyMap<Bill, BillDTO>() {

	            @Override
	            protected void configure() {
	            	 map().setId(source.getId());
	                 map().setName(source.getName());
	                 map().setAccountId(source.getAccount().getId());
	                 map().setPaymentId(source.getPayment().getId());
	                 map().setPaymentName(source.getPayment().getMethod());
	                 map().setService(source.getService());
	                 map().setCheckInFrom(source.getCheckInFrom());
	                 map().setCheckInUntil(source.getCheckInUntil());
	                 map().setCheckOutFrom(source.getCheckOutFrom());
	                 map().setCheckOutUntil(source.getCheckOutUntil());
	                 map().setMainGuest(source.getMainGuest());
	                 map().setStatus(source.getStatus());
	                 map().setEmail(source.getEmail());
	                 map().setPhone(source.getPhone());
	                 map().setNote(source.getNote());
	                 map().setSecurityCode(source.getSecurityCode());
	                 map().setTotal(source.getTotal());

	                 
	            }

	        });
	        mapper.addMappings(new PropertyMap<BillDTO, Bill>() {

	            @Override
	            protected void configure() {
	            	 map().setId(source.getId());
	                 map().setName(source.getName());
	                 map().setAccount(new Account(source.getAccountId()));
	                 map().setPayment(new Payment(source.getPaymentId()));
	                 map().setService(source.getService());
	                 map().setCheckInFrom(source.getCheckInFrom());
	                 map().setCheckInUntil(source.getCheckInUntil());
	                 map().setCheckOutFrom(source.getCheckOutFrom());
	                 map().setCheckOutUntil(source.getCheckOutUntil());
	                 map().setMainGuest(source.getMainGuest());
	                 map().setStatus(source.getStatus());
	                 map().setEmail(source.getEmail());
	                 map().setPhone(source.getPhone());
	                 map().setNote(source.getNote());
	                 map().setSecurityCode(source.getSecurityCode());
	                 map().setTotal(source.getTotal());

	                 
	            }

	        });
	        
	        
	        
	        
	       
	        
//	        mapper.addMappings(new PropertyMap<Account, AccountDTO>() {
//
//	            @Override
//	            protected void configure() {
//	            	 map().setId(source.getId());
//	                 map().setRoleId(source.getRole().getId());
//	                 map().setFirstName(source.getFirstName());
//	                 map().setLastName(source.getLastName());
//	                 map().setEmail(source.getEmail());
//	                 map().setPassword(source.getPassword()); // Assuming password mapping is desired
//	                 map().setPhone(source.getPhone());
//	                 map().setImage(source.getImage());
//	                 map().setActive(source.getActive());
//	                 map().setStatus(source.getStatus());
//	                 // Ignore mapping bills collection due to potential complexity
//	                 // Consider separate mapping for Bill if needed
//	                //thiếu comment với evalute mapper 
//	            }
//
//	        });
	        return mapper;
	      
	       
	    }

}
