package com.gusalbukrk.demo.config;

import java.util.Calendar;
import java.sql.Date;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.gusalbukrk.demo.model.Buyer;
import com.gusalbukrk.demo.model.Category;
import com.gusalbukrk.demo.model.ImageFile;
import com.gusalbukrk.demo.model.Listing;
import com.gusalbukrk.demo.model.OrderListing;
import com.gusalbukrk.demo.model.OrderListingId;
import com.gusalbukrk.demo.model.Review;
import com.gusalbukrk.demo.model.Order;
import com.gusalbukrk.demo.model.Seller;
import com.gusalbukrk.demo.model.Tag;
import com.gusalbukrk.demo.repository.BuyerRepository;
import com.gusalbukrk.demo.repository.CategoryRepository;
import com.gusalbukrk.demo.repository.ImageFileRepository;
import com.gusalbukrk.demo.repository.OrderListingRepository;
import com.gusalbukrk.demo.repository.ListingRepository;
import com.gusalbukrk.demo.repository.OrderRepository;
import com.gusalbukrk.demo.repository.ReviewRepository;
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
  private OrderRepository orderRepository;
  private OrderListingRepository orderListingRepository;
  private ReviewRepository reviewRepository;
  private ImageFileRepository imageFileRepository;

  public void run(String ...args) throws Exception {
    Buyer buyer1 = Buyer.builder().email("buyer1@gmail.com").password("pass").cpf("000.111.222-33").build();

    // System.out.println("!!!");
    // System.out.println(b);
    // System.out.println("!!!");

    Seller seller1 = Seller.builder().email("seller1@gmail.com").password("pass").cnpj("11.222.333/0001-44").build();

    // https://stackoverflow.com/q/48784923
    // using this approach, POJO creation requires explicitly setting id which is incorrect because
    // the id must be auto-generated
    // Listing listing1 = new Listing(2, "Listing 1", null, 3.14F, 10, seller1, Collections.<Tag>emptyList(), Collections.<OrderListing>emptyList());
    Listing listing1 = Listing.builder().name("Listing 1").description("Listing 1 description").price(3.14F).quantity(10).seller(seller1).build();
    Listing listing2 = Listing.builder().name("Listing 2").price(4.99F).quantity(5).seller(seller1).build();
    Listing listing3 = Listing.builder().name("Listing 3").price(9.99F).quantity(1).seller(seller1).build();

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

    listing1.setCategory(cat3);

    Calendar cal = Calendar.getInstance();
    cal.set(Calendar.YEAR, 2022);
    cal.set(Calendar.MONTH, 11);
    cal.set(Calendar.DATE, 17);
    Order order1 = Order.builder().date(new Date(cal.getTimeInMillis())).total(9.99F).buyer(buyer1).build();
    Order order2 = Order.builder().date(new Date(Calendar.getInstance().getTimeInMillis())).total(6.28F).buyer(buyer1).build();

    // order #1 has 2 listings
    OrderListingId orderListing1Id = OrderListingId.builder().orderId(1).listingId(1).build();
    OrderListingId orderListing2Id = OrderListingId.builder().orderId(1).listingId(3).build();
    //
    OrderListing orderListing1 = OrderListing.builder().id(orderListing1Id).quantity(1).build();
    OrderListing orderListing2 = OrderListing.builder().id(orderListing2Id).quantity(5).build();

    // order #2 has 1 listing
    OrderListingId orderListing3Id = OrderListingId.builder().orderId(2).listingId(1).build();
    //
    OrderListing orderListing3 = OrderListing.builder().id(orderListing3Id).quantity(2).build();

    Review review1 = Review.builder().rating(5).comment("Amazing seller! I'd certainly recommend.").order(order2).build();

    ImageFile image1 = ImageFile.builder().name("l1.jpg").build();
    ImageFile image12 = ImageFile.builder().name("l12.jpg").build();
    ImageFile image2 = ImageFile.builder().name("l2.jpg").build();
    ImageFile image3 = ImageFile.builder().name("l3.jpg").build();

    listing1.setImages(List.of(image1, image12));
    listing2.setImages(List.of(image2));
    listing3.setImages(List.of(image3));

    buyerRepository.save(buyer1);
    sellerRepository.save(seller1);
    listingRepository.saveAll(List.of(listing1, listing2, listing3));
    tagRepository.saveAll(List.of(tag1, tag2, tag3));
    categoryRepository.saveAll(List.of(cat1, cat2, cat3));
    orderRepository.saveAll(List.of(order1, order2));
    orderListingRepository.saveAll(List.of(orderListing1, orderListing2, orderListing3));
    reviewRepository.saveAll(List.of(review1));
    imageFileRepository.saveAll(List.of(image1, image12, image2, image3));
  }
}
