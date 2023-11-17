package com.gusalbukrk.demo.config;

import java.util.Calendar;
import java.sql.Date;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.gusalbukrk.demo.model.Buyer;
import com.gusalbukrk.demo.model.Category;
import com.gusalbukrk.demo.model.Listing;
import com.gusalbukrk.demo.model.Purchase;
import com.gusalbukrk.demo.model.Seller;
import com.gusalbukrk.demo.model.Tag;
import com.gusalbukrk.demo.repository.BuyerRepository;
import com.gusalbukrk.demo.repository.CategoryRepository;
import com.gusalbukrk.demo.repository.ListingRepository;
import com.gusalbukrk.demo.repository.PurchaseRepository;
import com.gusalbukrk.demo.repository.SellerRepository;
import com.gusalbukrk.demo.repository.TagRepository;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class DataLoader implements CommandLineRunner {
  private BuyerRepository buyerRepository;
  private SellerRepository sellerRepository;
  private ListingRepository listingRepository;
  private CategoryRepository categoryRepository;
  private TagRepository tagRepository;
  private PurchaseRepository purchaseRepository;

  public void run(String ...args) throws Exception {
    Buyer buyer1 = Buyer.builder().email("buyer1@gmail.com").password("pass").cpf("000.111.222-33").build();

    // System.out.println("!!!");
    // System.out.println(b);
    // System.out.println("!!!");

    Seller seller1 = Seller.builder().email("seller1@gmail.com").password("pass").cnpj("11.222.333/0001-44").build();

    // https://stackoverflow.com/q/48784923
    // using this approach, POJO creation requires explicitly setting id which is incorrect because
    // the id must be auto-generated
    // Listing listing1 = new Listing(2, "Listing 1", null, 3.14F, 10, seller1, Collections.<Tag>emptyList(), Collections.<ListingPurchase>emptyList());
    Listing listing1 = Listing.builder().name("Listing 1").price(3.14F).quantity(10).seller(seller1).build();

    Tag tag1 = Tag.builder().name("Tag 1").build();
    Tag tag2 = Tag.builder().name("Tag 2").build();
    Tag tag3 = Tag.builder().name("Tag 3").build();
    
    // error `object references an unsaved transient instance - save the transient instance before flushing`
    // if Listing's `tag` field is not set to `CascadeType.PERSIST`
    // because at this point tags are not saved in the database yet
    listing1.setTags(List.of(tag1, tag3));

    Category cat1 = Category.builder().name("Category 1").build();
    Category cat2 = Category.builder().name("Category 2").parent(cat1).build();
    Category cat3 = Category.builder().name("Category 3").parent(cat2).build();

    Calendar cal = Calendar.getInstance();
    cal.set(Calendar.YEAR, 2022);
    cal.set(Calendar.MONTH, 11);
    cal.set(Calendar.DATE, 17);
    Purchase purchase1 = Purchase.builder().date(new Date(cal.getTimeInMillis())).total(9.99F).buyer(buyer1).build();



    buyerRepository.save(buyer1);
    sellerRepository.save(seller1);
    listingRepository.save(listing1);
    tagRepository.saveAll(List.of(tag1, tag2, tag3));
    categoryRepository.saveAll(List.of(cat1, cat2, cat3));
    purchaseRepository.save(purchase1);
  }
}
