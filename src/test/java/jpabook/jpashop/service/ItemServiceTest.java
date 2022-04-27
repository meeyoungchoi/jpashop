package jpabook.jpashop.service;

import jpabook.jpashop.domain.item.Book;

import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.domain.item.Movie;
import jpabook.jpashop.repository.ItemRepository;



import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest
@RunWith(SpringRunner.class)
class ItemServiceTest {


    @Autowired
    private ItemService itemService;


    @Autowired
    private ItemRepository itemRepository;

    @Test
    @Rollback(value = false)
    public void 상품등록() {

        // given
        Book book  = new Book();
        book.setAuthor("authorA");
        book.setIsbn("1111");
        book.setStockQuantity(100);
        book.setName("bookA");
        // when
        Long itemId = itemService.saveItem(book);
        System.out.println("itemId: " + itemId);

        // then
        assertThat(itemId).isSameAs(itemRepository.findOne(itemId).getId());

        Book findedBook = (Book) itemRepository.findOne(itemId);
        System.out.println(findedBook.toString());

        System.out.println("이름 비교: " + book.getName().equals(findedBook.getName()));
        System.out.println("결과: " + book.equals(findedBook));
        boolean status = book.equals(findedBook);
        assertTrue(status);
    }


    @Test
    @Rollback(value = false)
    public void 상품조회() {

        // given
        Movie movie = new Movie();
        movie.setActor("actorA");
        movie.setDirecotr("directorA");
        movie.setName("movieA");
        movie.setPrice(28000);
        movie.setStockQuantity(10000);

        // when
        Long itemId = itemService.saveItem(movie);

        // then
        assertThat(itemId).isEqualTo(itemRepository.findOne(itemId).getId());



    }

}