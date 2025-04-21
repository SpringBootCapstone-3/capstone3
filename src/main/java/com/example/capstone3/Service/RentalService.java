package com.example.capstone3.Service;

import com.example.capstone3.Api.ApiException;
import com.example.capstone3.Model.Bid;
import com.example.capstone3.Model.Rental;
import com.example.capstone3.Repository.RentalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RentalService {
    private final RentalRepository rentalRepository;

   public List<Rental>getAllRental(){
       return rentalRepository.findAll();
   }
   public void addRental(Rental rental){
       rentalRepository.save(rental);
   }
   public void updateRental(Integer id,Rental rental){
       Rental rental1=rentalRepository.findRentalById(id);
       if(rental1==null){
           throw new ApiException("Rental Not Found");
       }
       rental1.setMonth_price(rental.getMonth_price());
       rental1.setStart_date(rental.getStart_date());
       rental1.setEnd_date(rental.getEnd_date());
       rental1.setIsActive(rental.getIsActive());
       rentalRepository.save(rental1);
   }
    public void deleteRental(Integer id){
       Rental rental=rentalRepository.findRentalById(id);
        if(rental==null){
            throw new ApiException("Rental Not Found");
        }
        rentalRepository.delete(rental);
    }
}
