package com.demo.configurations;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
import com.demo.helpers.UrlHelper;
import com.demo.staticHelper.AttributeHelper;
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
	        Converter<Set<Bill>, Long> converterCountBillNoComment = new AbstractConverter<Set<Bill>, Long>() {
	            @Override
	            protected Long convert(Set<Bill> source) {
	            	Optional<Set<Bill>> optionalBills = Optional.ofNullable(source);

	            	long count = optionalBills
	            	                .map(Set::stream)
	            	                .orElse(Stream.empty())
	            	                .filter(bill -> bill.getName().contains("no-comment"))
	            	                .count();
	                return count;
	            }
	        };
	        Converter<String, String> converterImageMain = new AbstractConverter<String, String>() {
	            @Override
	            protected String convert(String source) {

	                return environment.getProperty("BASE_URL") + AttributeHelper.urlImagesHotelMain +"/" +source;
	            }
	        };
	        Converter<String, String> converterImageCategoHotel = new AbstractConverter<String, String>() {
	            @Override
	            protected String convert(String source) {

	                return environment.getProperty("BASE_URL") + AttributeHelper.urlImagesHotelCategory +"/" +source;
	            }
	        };
	        Converter<String, String> converterAdress = new AbstractConverter<String, String>() {
	            @Override
	            protected String convert(String source) {
	            	int index = source.indexOf(";");
	            	
	                return source.substring(0, index);
	            }
	        };
	        
	        // Trường hợp mapper đặc biệt
//	        mapper.typeMap(Product.class, ProductDTO.class).addMappings(m -> {
//	            m.using(converterBooleanTOString).map(Product::isStatus, ProductDTO::setPhone);
//	            m.using(converterImage).map(Product::getImage, ProductDTO::setPhone);
//	        });
	        
	        mapper.addMappings(new PropertyMap<Evaluate, EvaluateDTO>() {

	            @Override
	            protected void configure() {
	            	
	            	map().setId(source.getId());
	             
	                map().setNumber(source.getNumber());
	                map().setComment(source.getComment());
	                map().setCreated(source.getCreated());
	                map().setStatus(source.isStatus()); // boolean uses isStatus()
	                map().setIdAccount(source.getIdAccount());
	                map().setIdBill(source.getIdBill());
	                map().setNameAccount(source.getNameAccount());
	            }

	        });
	        mapper.addMappings(new PropertyMap<EvaluateDTO, EvaluateDTO>() {

	            @Override
	            protected void configure() {
	            	
	            	map().setId(source.getId());
	             
	                map().setNumber(source.getNumber());
	                map().setComment(source.getComment());
	                map().setCreated(source.getCreated());
	                map().setStatus(source.getStatus()); // boolean uses isStatus()
	                map().setIdAccount(source.getIdAccount());
	                map().setIdBill(source.getIdBill());
	                map().setNameAccount(source.getNameAccount());
	            }

	        });
	        mapper.addMappings(new PropertyMap<Image, ImageDTO>() {

	            @Override
	            protected void configure() {
	           	 map().setId(source.getId());
	           	map().setName(source.getName());
	            
	            }

	        });
	        mapper.addMappings(new PropertyMap<ImageDTO, Image>() {

	            @Override
	            protected void configure() {
	           	 map().setId(source.getId());
	           	map().setName(source.getName());
	            
	            }

	        });
	        mapper.typeMap(Image.class, ImageDTO.class).addMappings(m -> {
	            m.map(Image::getId, ImageDTO::setId);
	            m.using(converterImageCategoHotel).map(Image::getName, ImageDTO::setName);
	           
	        });
	        mapper.addMappings(new PropertyMap<Categoryimage, CategoryimageDTO>() {

	            @Override
	            protected void configure() {
	           	 map().setId(source.getId());
	           	map().setName(source.getName());
	           }

	        });
	        mapper.addMappings(new PropertyMap<CategoryimageDTO, Categoryimage>() {

	            @Override
	            protected void configure() {
	           	 map().setId(source.getId());
	           	map().setName(source.getName());
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
	               // map().setHotel(new Hotel(source.getHotelId()));
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

	       
//	      
//	       

	        mapper.typeMap(Hotel.class, HotelDTO.class).addMappings(m -> {
	            m.map(Hotel::getIdAccount, HotelDTO::setIdAccount);
	            m.map(Hotel::getName, HotelDTO::setName);
	            m.map(Hotel::getCancellationPolicy, HotelDTO::setCancellationPolicy);
	            m.map(Hotel::getDescription, HotelDTO::setDescription);
	            m.map(Hotel::getRating, HotelDTO::setRating);
	            m.map(Hotel::getManager, HotelDTO::setManager);
	            m.map(Hotel::getStatus, HotelDTO::setStatus); // Use converter for boolean
	            m.using(converterImageMain).map(Hotel::getMainPhoto, HotelDTO::setMainPhoto);
	            m.using(converterImageMain).map(Hotel::getSecondaryPhoto, HotelDTO::setSecondaryPhoto);
	            m.map(Hotel::getPapers, HotelDTO::setPapers);
	            m.map(Hotel::getRegulation, HotelDTO::setRegulation);
	            m.map(Hotel::getIdHandler, HotelDTO::setIdHandler);
	            m.using(converterAdress).map(Hotel::getAddress, HotelDTO::setAddress);
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
	        mapper.typeMap(Account.class, AccountDTO.class).addMappings(m -> {
	            m.map(Account::getId, AccountDTO::setId);
	            m.map(account -> account.getRole().getName(), AccountDTO::setRoleName);
	            // ... (Các mapping khác tương tự)
	            m.map(Account::getFirstName, AccountDTO::setFirstName);
	            m.map(Account::getLastName, AccountDTO::setLastName);
	            m.map(Account::getEmail, AccountDTO::setEmail);
	            // ... (Xem xét bảo mật khi mapping password)
	            m.map(Account::getPhone, AccountDTO::setPhone);
	            m.map(Account::getImage, AccountDTO::setImage);
	            m.map(Account::getActive, AccountDTO::setActive);
	            m.using(converterCountBillNoComment).map(Account::getBills, AccountDTO::setCountNoCommentBill);
	          
	            // ... (Nếu có các chuyển đổi khác)
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
	       

	        
	        
	        
	       
	        

	        return mapper;
	      
	       
	    }

}
